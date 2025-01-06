package sh.miles.voidcr.feature.chat.command;

import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.chat.IChat;
import finalforeach.cosmicreach.chat.commands.Command;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.networking.server.ServerSingletons;

public final class VoidServerStopCommand extends Command {

    @Override
    public void run(final IChat chat) {
        final Player player = getCallingPlayer();

        if (player == null || ServerSingletons.OP_LIST.hasAccount(player.getAccount())) {
            try {
                GameSingletons.playersToUniqueIds.keySet().forEach((p) -> {
                    ServerSingletons.SERVER.kick("Server has closed", ServerSingletons.getConnection(p));
                });

                ServerSingletons.SERVER.running = false;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        } else {
            chat.addMessage(player.getAccount(), "Only operators may run this command");
        }
    }

    @Override
    public String getShortDescription() {
        return "Stops the server";
    }
}
