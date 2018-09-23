package ru.SilirdCo.AdaptivePrices.View.impl.Events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.subjects.PublishSubject;

public class EventTransport {
    private final Logger logger = LoggerFactory.getLogger(EventTransport.class);

    private static EventTransport instance;

    public static EventTransport getInstance() {
        if (instance == null) {
            instance = new EventTransport();
        }
        return instance;
    }

    private PublishSubject<Event> subject = PublishSubject.create();

    public Observable<Event> getObservable() {
        return subject;
    }

    public void send(Event event) {
        subject.onNext(event);
    }
}
