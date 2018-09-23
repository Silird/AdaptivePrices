package ru.SilirdCo.AdaptivePrices.View.impl.Events.Filters;

import ru.SilirdCo.AdaptivePrices.View.impl.Events.Event;
import ru.SilirdCo.AdaptivePrices.View.impl.Events.EventType;
import rx.functions.Func1;

public class WarnFilter implements Func1<Event, Boolean> {
    @Override
    public Boolean call(Event event) {
        if (event.getType() != EventType.WARN) return false;

        return true;
    }
}
