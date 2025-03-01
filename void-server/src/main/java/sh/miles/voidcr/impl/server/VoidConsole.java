package sh.miles.voidcr.impl.server;

import finalforeach.cosmicreach.chat.IChat;
import sh.miles.voidcr.server.Console;

public class VoidConsole implements Console {

    private final IChat consoleChat;

    public VoidConsole(final IChat consoleChat) {
        this.consoleChat = consoleChat;
    }

    @Override
    public void sendMessage(final String message) {
        this.consoleChat.addMessage(null, message);
    }
}
