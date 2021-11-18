package ru.mipt.bit.platformer.event;

public interface EventPublisher {
    void subscribe(EventType eventType, EventListener eventListener);

    void unsubscribe(EventType eventType, EventListener eventListener);

    void notifySubs(EventType eventType, Object object);
}
