package ru.mipt.bit.platformer.event;

/**
 * Entity
 */
public interface EventListener {
    void update(EventType eventType, Object object);
}
