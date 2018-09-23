package ru.SilirdCo.AdaptivePrices.View.impl.Events;

public class EventSender {
    public static void sendUpdate() {
        EventTransport.getInstance()
                .send(new Event(EventType.UPDATE, ""));
    }

    public static void sendWarn(String message) {
        EventTransport.getInstance()
                .send(new Event(EventType.WARN, message));
    }
}
