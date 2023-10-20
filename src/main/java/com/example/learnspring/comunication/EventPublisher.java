package com.example.learnspring.comunication;

import lombok.ToString;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@ToString
public class EventPublisher {


    private final ApplicationEventPublisher applicationEventPublisher;

    public EventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public void checkout() {

        var event = new CheckoutEvent("event name", LocalDateTime.now());

        applicationEventPublisher.publishEvent(event);
    }
}
