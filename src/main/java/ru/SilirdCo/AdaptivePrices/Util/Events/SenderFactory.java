package ru.SilirdCo.AdaptivePrices.Util.Events;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SenderFactory {
    private ApplicationContext context = new ClassPathXmlApplicationContext("Factories/SenderFactory.xml");

    private static SenderFactory instance;

    public static SenderFactory getInstance() {
        if (instance == null) {
            instance = new SenderFactory();
        }
        return instance;
    }

    public ISender<Event> getEvents() {
        return context.getBean("sender", Sender.class);
    }
}
