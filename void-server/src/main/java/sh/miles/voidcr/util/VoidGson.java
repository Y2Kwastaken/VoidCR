package sh.miles.voidcr.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class VoidGson {
    public static final Gson GSON = new GsonBuilder().create();

    private VoidGson() {
        throw new UnsupportedOperationException("Can not initialize utility class");
    }

    public static JsonObject fromPath(final Path path) {
        try {
            return GSON.fromJson(Files.newBufferedReader(path), JsonObject.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
