package sh.miles.voidcr.impl.plugin;

import com.google.common.base.Preconditions;
import sh.miles.voidcr.plugin.meta.PluginMeta;
import sh.miles.voidcr.plugin.type.StandardPlugin;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Enumeration;

public class VoidPluginClassLoader extends URLClassLoader {

    private final VoidPluginLoader loader;
    private final PluginMeta pluginMeta;
    private StandardPlugin standardPlugin;

    static {
        ClassLoader.registerAsParallelCapable();
    }

    private VoidPluginClassLoader(final URL url, final ClassLoader parent, final VoidPluginLoader loader, final PluginMeta pluginMeta) {
        super(new URL[]{url}, parent);
        this.pluginMeta = pluginMeta;
        this.loader = loader;
    }


    @Override
    public URL getResource(final String name) {
        return findResource(name);
    }

    @Override
    public Enumeration<URL> getResources(final String name) throws IOException {
        return findResources(name);
    }

    @Override
    protected Class<?> loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
        return loadClass(name, resolve, true);
    }

    Class<?> loadClass(final String name, final boolean resolve, final boolean checkTransitive) throws ClassNotFoundException {
        try {
            final Class<?> result = super.loadClass(name, true);

            if (checkTransitive || result.getClassLoader() == this) {
                return result;
            }
        } catch (ClassNotFoundException ignored) {
        }


        if (!checkTransitive) {
            throw new ClassNotFoundException(name);
        }

        final Class<?> result = this.loader.getClassByName(name, resolve, pluginMeta);
        if (result == null) {
            throw new ClassNotFoundException(name);
        }

        if (!(result.getClassLoader() instanceof VoidPluginClassLoader oloader)) {
            throw new ClassNotFoundException(name);
        }

        throw new ClassNotFoundException("Could not load class from plugin %s because it is not a depend of this plugin, or mergeClasspath is false".formatted(oloader.pluginMeta.name()));
    }

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        if (name.startsWith("sh.miles.voidcr") || name.startsWith("finalforeach.cosmicreach")) {
            throw new ClassNotFoundException("%s, this is likely because of inclusion of internal classes.".formatted(name));
        }

        return super.findClass(name);
    }


    public PluginMeta getPluginMeta() {
        return pluginMeta;
    }

    public void setStandardPlugin(final StandardPlugin standardPlugin) {
        this.standardPlugin = standardPlugin;
    }

    public StandardPlugin getStandardPlugin() {
        return standardPlugin;
    }


    public static VoidPluginClassLoader create(final Path jarFile, ClassLoader parent, final VoidPluginLoader loader, final PluginMeta pluginMeta) {
        Preconditions.checkArgument(jarFile != null, "The given jarFile must not be null");
        Preconditions.checkArgument(parent != null, "The given parent classloader must not be null");
        Preconditions.checkArgument(loader != null, "The given plugin loader must not be null");
        Preconditions.checkArgument(pluginMeta != null, "The given plugin meta must not be null");

        try {
            return new VoidPluginClassLoader(jarFile.toUri().toURL(), parent, loader, pluginMeta);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
