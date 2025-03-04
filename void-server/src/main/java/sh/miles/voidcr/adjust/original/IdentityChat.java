package sh.miles.voidcr.adjust.original;

import finalforeach.cosmicreach.accounts.Account;
import finalforeach.cosmicreach.chat.IChat;
import finalforeach.cosmicreach.networking.NetworkIdentity;
import finalforeach.cosmicreach.networking.server.ServerIdentity;
import finalforeach.cosmicreach.networking.server.ServerSingletons;

public final class IdentityChat implements IChat {

    private final NetworkIdentity identity;

    public IdentityChat(NetworkIdentity identity) {
        this.identity = identity;
    }

    @Override
    public void addMessage(final Account account, final String messageText) {
        ServerSingletons.SERVER.systemChat.addMessage(account, messageText);
        ((ServerIdentity) identity).sendChatMessage(messageText);
    }
}
