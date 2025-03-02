package sh.miles.voidcr.impl.server;

import finalforeach.cosmicreach.chat.IChat;
import sh.miles.voidcr.server.Console;

import java.util.function.Supplier;

public class VoidConsole implements Console {

    private final Supplier<IChat> consoleChat;

    public VoidConsole(final Supplier<IChat> consoleChat) {
        this.consoleChat = consoleChat;
    }

    @Override
    public void sendMessage(final String message) {
        this.consoleChat.get().addMessage(null, message);
    }
}
