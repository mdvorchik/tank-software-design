package ru.mipt.bit.platformer.event;

public interface EventListener {
    void update(EventType eventType, Object object);
}
