package sh.miles.voidcr.impl.plugin.meta;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import sh.miles.voidcr.plugin.meta.PluginApiVersion;
import sh.miles.voidcr.plugin.meta.PluginDependency;
import sh.miles.voidcr.plugin.meta.PluginMeta;
import sh.miles.voidcr.plugin.type.StandardPlugin;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public final class VoidPluginMeta implements PluginMeta {
    private final Path pluginFile;
    private final String name;
    private final String main;
    private final String version;
    private final String author;
    private final List<String> contributors;
    private final PluginApiVersion apiVersion;
    private final ImmutableSet<PluginDependency> dependencies;

    public VoidPluginMeta(Path pluginFile, String name, String main, String version,
                          String author, List<String> contributors,
                          PluginApiVersion apiVersion,
                          ImmutableSet<PluginDependency> dependencies) {
        this.pluginFile = pluginFile;
        this.name = name;
        this.main = main;
        this.version = version;
        this.author = author;
        this.contributors = contributors;
        this.apiVersion = apiVersion;
        this.dependencies = dependencies;
    }

    private Class<? extends StandardPlugin> mainClass;

    public void mainClass(final Class<? extends StandardPlugin> mainClass) {
        Preconditions.checkState(this.mainClass == null, "The given plugin mainClass is already set");
        this.mainClass = mainClass;
    }

    @Override
    public Class<? extends StandardPlugin> mainClass() {
        return this.mainClass;
    }

    public Path pluginFile() {
        return this.pluginFile;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String main() {
        return main;
    }

    @Override
    public String version() {
        return version;
    }

    @Override
    public String author() {
        return author;
    }

    @Override
    public List<String> contributors() {
        return contributors;
    }

    @Override
    public PluginApiVersion apiVersion() {
        return apiVersion;
    }

    @Override
    public ImmutableSet<PluginDependency> dependencies() {
        return dependencies;
    }

    @Override
    public PluginDependency getDependency(final String name) {
        for (final PluginDependency dependency : this.dependencies) {
            if (dependency.name().equals(name)) {
                return dependency;
            }
        }

        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (VoidPluginMeta) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.main, that.main) &&
                Objects.equals(this.version, that.version) &&
                Objects.equals(this.author, that.author) &&
                Objects.equals(this.contributors, that.contributors) &&
                Objects.equals(this.apiVersion, that.apiVersion) &&
                Objects.equals(this.dependencies, that.dependencies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, main, version, author, contributors, apiVersion, dependencies);
    }

    @Override
    public String toString() {
        return "VoidPluginMeta[" +
                "name=" + name + ", " +
                "main=" + main + ", " +
                "version=" + version + ", " +
                "author=" + author + ", " +
                "contributors=" + contributors + ", " +
                "apiVersion=" + apiVersion + ", " +
                "dependencies=" + dependencies + ']';
    }

}
