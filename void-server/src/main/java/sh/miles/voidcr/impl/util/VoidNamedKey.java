package sh.miles.voidcr.impl.util;

import com.google.common.base.Preconditions;
import sh.miles.voidcr.util.NamedKey;

import java.util.Objects;

public class VoidNamedKey implements NamedKey {

    private static final String REGEX = "[a-zA-Z_-]";

    private final String namespace;
    private final String key;
    private String cache = null;

    public VoidNamedKey(String namespace, String key) {
        validate(namespace, key);
        this.namespace = namespace;
        this.key = key;
    }

    @Override
    public String getNamespace() {
        return this.namespace;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    public String getCosmicReachId() {
        if (cache == null) cache = namespace + ":" + key;
        return this.cache;
    }

    private static void validate(String... strings) {
        for (final String string : strings) {
            Preconditions.checkArgument(string.matches(REGEX));
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final VoidNamedKey that)) return false;
        return Objects.equals(namespace, that.namespace) && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, key);
    }
}
