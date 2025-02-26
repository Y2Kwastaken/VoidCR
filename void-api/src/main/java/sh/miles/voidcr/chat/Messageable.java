package sh.miles.voidcr.chat;

/**
 * Marks some object that they can receive and send messages
 *
 * @since 0.3.27
 */
public interface Messageable {
    /**
     * Sends a message to THIS messageable object from an unknown source
     *
     * @param message the message to send
     * @since 0.3.27
     */
    void sendMessage(String message);
}
