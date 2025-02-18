package sh.miles.voidcr.impl.server.configuration;

import com.google.gson.JsonObject;
import finalforeach.cosmicreach.entities.player.Gamemode;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.ItemStack;

import java.util.function.Predicate;

public record VoidBehaviorChangeConfiguration(
        boolean activeDroneTrapsInCreativeWithInteraction
) {

    public boolean activateDroneTrapOnRightClick(Player player, ItemStack heldItemStack) {
        return activeDroneTrapsInCreativeWithInteraction && heldItemStack == null && player.gamemode == finalforeach.cosmicreach.entities.player.Gamemode.CREATIVE;
    }

    public static VoidBehaviorChangeConfiguration parse(JsonObject object) {
        return new VoidBehaviorChangeConfiguration(
                object.get("activate_done_traps_in_creative_with_interact").getAsBoolean()
        );
    }
}
