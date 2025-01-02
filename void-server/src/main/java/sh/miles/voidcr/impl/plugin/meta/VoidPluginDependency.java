package sh.miles.voidcr.impl.plugin.meta;

import sh.miles.voidcr.plugin.meta.PluginDependency;

public record VoidPluginDependency(String name, boolean required, boolean loadBefore,
                                   boolean mergeClasspath) implements PluginDependency {
}
