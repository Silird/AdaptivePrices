package ru.SilirdCo.AdaptivePrices.View.impl.Events;

public class Event {
    private EventType type;
    private String message;

    public Event(EventType type, String message) {
        this.type = type;
        this.message = message;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
