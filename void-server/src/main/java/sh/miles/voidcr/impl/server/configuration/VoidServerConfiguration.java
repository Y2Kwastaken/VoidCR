package sh.miles.voidcr.impl.server.configuration;

import com.google.gson.JsonObject;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.server.configuration.ServerConfiguration;
import sh.miles.voidcr.util.VoidGson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public record VoidServerConfiguration(Path pluginsDirectory) implements ServerConfiguration {

    public static final String CONFIG_NAME = "void.json";

    public static VoidServerConfiguration read(Server server) {
        final var configLocation = server.getServerFolder().resolve(CONFIG_NAME);
        if (Files.notExists(configLocation)) {
            try (final var reader = VoidServerConfiguration.class.getClassLoader().getResourceAsStream("configs/" + CONFIG_NAME)) {
                Files.copy(reader, configLocation, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        final JsonObject object = VoidGson.fromPath(configLocation);
        final Path pluginsDirectory = Path.of(object.get("pluginsDirectory").getAsString());

        return new VoidServerConfiguration(pluginsDirectory);
    }
}
