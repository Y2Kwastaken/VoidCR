package sh.miles.voidcr.impl.plugin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import sh.miles.voidcr.Main;
import sh.miles.voidcr.impl.plugin.meta.VoidPluginApiVersion;
import sh.miles.voidcr.impl.plugin.meta.VoidPluginDependency;
import sh.miles.voidcr.impl.plugin.meta.VoidPluginMeta;
import sh.miles.voidcr.impl.server.VoidServer;
import sh.miles.voidcr.plugin.exception.InvalidPluginMetaException;
import sh.miles.voidcr.plugin.exception.PluginLoadException;
import sh.miles.voidcr.plugin.meta.PluginApiVersion;
import sh.miles.voidcr.plugin.meta.PluginDependency;
import sh.miles.voidcr.plugin.meta.PluginMeta;
import sh.miles.voidcr.plugin.type.StandardPlugin;
import sh.miles.voidcr.util.VoidGson;
import sh.miles.voidcr.util.function.SupplyingSilencer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.zip.ZipFile;

public final class VoidPluginLoader {
    private final Map<String, PluginMeta> metas = new HashMap<>();
    private final List<VoidPluginClassLoader> loaders = new ArrayList<>();
    private final VoidServer server;

    public VoidPluginLoader(VoidServer server) {
        this.server = server;
    }

    public void enablePlugins() {
        for (final VoidPluginClassLoader loader : loaders) {
            if (loader.getStandardPlugin() != null) {
                loader.getStandardPlugin().initialize(server);
                server.getLogger().info("Enabled plugin {}", loader.getPluginMeta().name());
            }
        }
    }

    public void disablePlugins() {
        for (final VoidPluginClassLoader loader : loaders) {
            if (loader.getStandardPlugin() != null) {
                loader.getStandardPlugin().disable(server);
                server.getLogger().info("Disabled plugin {}", loader.getPluginMeta().name());
            }
        }
    }

    public void loadPlugins() {
        Main.LOGGER.info("Loading Plugins...");
        final PluginLoadGraph graph = new PluginLoadGraph(getMetas().size());
        final List<PluginMeta> loadOrder;
        try {
            loadOrder = graph.generate(getMetas());
        } catch (PluginLoadException e) {
            throw new IllegalStateException(e);
        }

        for (final PluginMeta meta : loadOrder) {
            loadClassLoader((VoidPluginMeta) meta);
            Main.LOGGER.info("Loaded Plugin Classloader {}", meta.name());
        }
    }

    public void loadPluginFiles(final Path folder) {
        try {
            if (Files.notExists(folder)) {
                Files.createDirectories(folder);
            }
            try (Stream<Path> files = Files.list(folder)) {
                files.filter((path) -> path.getFileName().toString().endsWith(".jar")).forEach(this::loadMeta);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMeta(final Path jarFile) throws InvalidPluginMetaException {
        try (final ZipFile zip = new ZipFile(jarFile.toFile())) {
            final JsonObject object = VoidGson.GSON.fromJson(new InputStreamReader(zip.getInputStream(zip.getEntry("plugin.json"))), JsonObject.class);

            final String name = getStringField(object, "name", jarFile);
            final String main = getStringField(object, "main", jarFile);
            final String version = getStringField(object, "version", jarFile);
            final String author = getStringField(object, "author", jarFile);
            final List<String> contributors = getListField(object, "contributors", JsonElement::getAsString);
            final PluginApiVersion apiVersion = new VoidPluginApiVersion(getStringField(object, "apiVersion", jarFile));
            final ImmutableSet<PluginDependency> dependencies = ImmutableSet.copyOf(getListField(object, "dependencies", (element) -> {
                final JsonObject dependencyObject = element.getAsJsonObject();
                if (!dependencyObject.has("name")) {
                    throw new InvalidPluginMetaException("Declared dependency within jarFile %s has no field name".formatted(jarFile));
                }
                final boolean required;
                if (dependencyObject.has("required")) {
                    required = dependencyObject.get("required").getAsBoolean();
                } else required = true;
                final boolean loadBefore;
                if (dependencyObject.has("loadBefore")) {
                    loadBefore = dependencyObject.get("loadBefore").getAsBoolean();
                } else loadBefore = false;
                final boolean mergeClasspath;
                if (dependencyObject.has("mergeClasspath")) {
                    mergeClasspath = dependencyObject.get("mergeClasspath").getAsBoolean();
                } else mergeClasspath = true;
                return new VoidPluginDependency(dependencyObject.get("name").getAsString(), required, loadBefore, mergeClasspath);
            }));
            Main.LOGGER.info("Loaded {} from {}", name, jarFile);
            metas.put(name, new VoidPluginMeta(jarFile, name, main, version, author, contributors, apiVersion, dependencies));
        } catch (IOException exception) {
            throw new IllegalStateException(exception);
        }
    }

    public void loadClassLoader(final VoidPluginMeta meta) {
        final VoidPluginClassLoader loader = VoidPluginClassLoader.create(meta.pluginFile(), getClass().getClassLoader(), this, meta);
        meta.mainClass((Class<? extends StandardPlugin>) SupplyingSilencer.silence(() -> Class.forName(meta.main(), true, loader)));
        try {
            Constructor<? extends StandardPlugin> constructor = meta.mainClass().getDeclaredConstructor();
            loader.setStandardPlugin(constructor.newInstance());
            loaders.add(loader);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    Class<?> getClassByName(final String name, final boolean resolve, final PluginMeta meta) {
        for (final VoidPluginClassLoader loader : loaders) {
            try {
                return loader.loadClass(name, resolve, loader.getPluginMeta().getDependency(meta.name()).mergeClasspath());
            } catch (ClassNotFoundException ignored) {
            }
        }
        return null;
    }

    public Map<String, PluginMeta> getMetas() {
        return ImmutableMap.copyOf(this.metas);
    }

    public List<VoidPluginClassLoader> getLoaders() {
        return ImmutableList.copyOf(this.loaders);
    }

    private String getStringField(JsonObject object, String field, Path jarFile) throws InvalidPluginMetaException {
        if (!object.has(field)) {
            throw new InvalidPluginMetaException(jarFile, field);
        }

        return object.get(field).getAsString();
    }

    private <T> List<T> getListField(JsonObject object, String field, Function<JsonElement, T> mapper) {
        if (!object.has(field)) {
            return new ArrayList<>();
        }

        return object.getAsJsonArray(field).asList().stream().map(mapper).toList();
    }

}
