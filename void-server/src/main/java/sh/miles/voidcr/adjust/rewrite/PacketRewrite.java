package sh.miles.voidcr.adjust.rewrite;

import finalforeach.cosmicreach.accounts.Account;
import finalforeach.cosmicreach.chat.commands.Command;
import finalforeach.cosmicreach.networking.NetworkIdentity;
import sh.miles.voidcr.adjust.original.IdentityChat;
import sh.miles.voidcr.impl.server.VoidServer;

public final class PacketRewrite {

    public static void commandPacket(String[] arguments, NetworkIdentity identity) {
        final Account account = identity.getAccount();
        if (arguments == null) return;
        VoidServer.SERVER.getLogger().info("[{}] {}: executed command {}", account.getUniqueId(), account.getUsername(), arguments[0]);
        Command.triggerCommand(new IdentityChat(identity), account, arguments);
    }

}
