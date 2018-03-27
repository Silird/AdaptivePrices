package ru.SilirdCo.AdaptivePrices.Util.Events;

public class EventSender {
    public static void sendError(String text) {
        SenderFactory.getInstance()
                .getEvents()
                .send(new Event(EventType.ERROR, text));
    }
}
