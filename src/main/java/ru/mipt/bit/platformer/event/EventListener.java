package ru.mipt.bit.platformer.event;

/**
 * Port
 */
public interface EventListener {
    void update(EventType eventType, Object object);
}
