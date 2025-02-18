package sh.miles.voidcr.impl.server.configuration;

import com.google.gson.JsonObject;
import finalforeach.cosmicreach.entities.player.Gamemode;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.ItemStack;

import java.util.function.Predicate;

public record VoidBehaviorChangeConfiguration(
        boolean passiveDroneTrapsInCreative
) {

    public boolean activateDroneTrapOnRightClick(Player player, ItemStack heldItemStack) {
        return passiveDroneTrapsInCreative && heldItemStack == null && player.gamemode == finalforeach.cosmicreach.entities.player.Gamemode.CREATIVE;
    }

    public Predicate<Player> activateDroneTrapInCreative() {
        return passiveDroneTrapsInCreative ? player -> player.gamemode != Gamemode.CREATIVE : null;
    }

    public static VoidBehaviorChangeConfiguration parse(JsonObject object) {
        return new VoidBehaviorChangeConfiguration(
                object.get("passive_drone_traps_in_creative").getAsBoolean()
        );
    }
}
