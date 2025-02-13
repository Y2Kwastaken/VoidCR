package sh.miles.voidcr.impl.world.inventory.item;

import com.google.common.base.Preconditions;
import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.util.NamedKey;
import sh.miles.voidcr.world.inventory.item.ItemKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class VoidItemKey implements ItemKey {

    private static final String REGEX_PERMISSIBLE = "[a-zA-Z_-\\[\\]\\,\\=]";
    private static final String REGEX = "[a-zA-Z_-]";

    private final String namespace;
    private final String key;
    private final Map<String, String> stateMap;
    private String cache = null;

    public VoidItemKey(final String namespace, final String key) {
        validate(namespace, false);
        validate(key, true);
        this.namespace = namespace;
        int bracket = key.indexOf('[');
        this.key = key.substring(0, bracket == -1 ? key.length() : bracket);
        if (bracket != -1) {
            this.stateMap = generateStateMap(key.substring(bracket));
        } else {
            stateMap = new HashMap<>();
        }
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getNamespace() {
        return this.namespace;
    }

    @Override
    public @Nullable String getStateValue(final String stateKey) {
        return this.stateMap.get(stateKey);
    }

    @Override
    public boolean hasStateValue(final String stateKey) {
        return this.stateMap.containsKey(stateKey);
    }

    public String getCosmicReachId() {
        if (cache == null) {
            this.cache = namespace + ":" + key;
            final String stateMapPackaged = repackageStateMap(this.stateMap);
            if (!stateMapPackaged.equals("]")) {
                this.cache += stateMapPackaged;
            }
        }
        return this.cache;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final VoidItemKey that)) return false;
        return Objects.equals(namespace, that.namespace) && Objects.equals(key, that.key) && Objects.equals(stateMap, that.stateMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, key, stateMap);
    }

    @Override
    public String toString() {
        return getCosmicReachId();
    }

    private static String repackageStateMap(Map<String, String> stateMap) {
        final StringBuilder builder = new StringBuilder().append("[");
        for (final Map.Entry<String, String> entry : stateMap.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append(",");
        }

        builder.setCharAt(builder.length() - 1, ']');
        return builder.toString();
    }

    private static void validate(String string, boolean permissible) {
        for (int i = 0; i < string.length(); i++) {
            Preconditions.checkArgument(isValidChar(string.charAt(i), permissible), "The string '%s' does not match the regex '%s'".formatted(string, REGEX));
        }
    }

    private static boolean isValidChar(char c, boolean permissible) {
        boolean result = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '.' || c == '_' || c == '-';
        if (permissible) {
            result = result || c == '[' || c == ']' || c == ',' || c == '=';
        }
        return result;
    }

    private static Map<String, String> generateStateMap(String data) {
        final Map<String, String> collector = new HashMap<>();

        final StringBuilder keyBuilder = new StringBuilder();
        final StringBuilder valueBuilder = new StringBuilder();

        char current;
        boolean key = true;
        for (int i = 1; i < data.length() - 1; i++) {
            current = data.charAt(i);
            switch (current) {
                case '=' -> key = false;
                case ',' -> {
                    collector.put(keyBuilder.toString(), valueBuilder.toString());
                    keyBuilder.setLength(0);
                    valueBuilder.setLength(0);
                    key = true;
                }
                default -> {
                    if (key) {
                        keyBuilder.append(current);
                    } else {
                        valueBuilder.append(current);
                    }
                }
            }
        }

        if (!keyBuilder.isEmpty() && !valueBuilder.isEmpty()) {
            collector.put(keyBuilder.toString(), valueBuilder.toString());
        }

        return collector;
    }
}
