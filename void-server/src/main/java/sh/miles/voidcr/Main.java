package sh.miles.voidcr;

import finalforeach.cosmicreach.server.ServerLauncher;

public final class Main {

    public static void main(String[] args) {
        try {
            ServerLauncher.main(args);
        } catch (Exception e) {
            System.out.println("Unable to start cosmic reach");
        }
    }

}
