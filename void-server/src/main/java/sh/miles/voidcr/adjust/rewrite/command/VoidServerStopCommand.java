package sh.miles.voidcr.adjust.rewrite.command;

import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.Threads;
import finalforeach.cosmicreach.chat.IChat;
import finalforeach.cosmicreach.chat.commands.Command;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.networking.server.ServerSingletons;

public final class VoidServerStopCommand extends Command {

    @Override
    public void run(final IChat chat) {
        final Player player = getCallingPlayer();

        if (player == null || ServerSingletons.OP_LIST.hasAccount(player.getAccount())) {
            Threads.runOnMainThread(() -> {
                GameSingletons.playersToUniqueIds.keySet().forEach((p) -> {
                    ServerSingletons.SERVER.kick("Server has closed", ServerSingletons.getConnection(p));
                });

                ServerSingletons.running.set(false);
            });

        } else {
            chat.addMessage(player.getAccount(), "Only operators may run this command");
        }
    }

    @Override
    public String getShortDescription() {
        return "Stops the server";
    }
}
