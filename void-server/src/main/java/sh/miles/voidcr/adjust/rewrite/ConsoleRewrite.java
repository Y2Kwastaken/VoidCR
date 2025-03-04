package sh.miles.voidcr.adjust.rewrite;

import finalforeach.cosmicreach.chat.commands.Command;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import sh.miles.voidcr.Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class ConsoleRewrite {

    private Thread consoleThread;
    private boolean running = true;
    private final BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

    private void loop() {
        try {
            Main.LOGGER.info("Server started, type 'stop' to shutdown and 'help' for commands.");
            String inputString;
            while (true) {
                synchronized (this) {
                    if (!running) {
                        return;
                    }
                    inputString = bufferRead.readLine();
                    Command.triggerCommand(ServerSingletons.SERVER.systemChat, null, inputString.split(" "));
                }
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void spin() {
        consoleThread = Thread.ofVirtual().name("Console Input")
                .uncaughtExceptionHandler((thread, exception) -> {
                    Main.LOGGER.error("An exception occurred while handling console input", exception);
                }).start(this::loop);
    }

    public void halt() {
        if (consoleThread != null) {
            running = false;
            consoleThread.interrupt();
        }
    }
}
