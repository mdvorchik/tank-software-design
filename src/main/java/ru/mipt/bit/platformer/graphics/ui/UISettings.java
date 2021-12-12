package ru.mipt.bit.platformer.graphics.ui;

import ru.mipt.bit.platformer.event.EventListener;
import ru.mipt.bit.platformer.event.EventPublisher;
import ru.mipt.bit.platformer.event.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UISettings implements EventPublisher {
    private final Map<EventType, List<EventListener>> listeners = new HashMap<>();

    public UISettings(List<EventType> eventTypes) {
        eventTypes.forEach(event -> listeners.put(event, new ArrayList<>()));
    }

    public void changeRenderHealthBarMod() {
        notifySubs(EventType.CHANGE_UI_RENDER_MODE, new Object());
    }


    @Override
    public void subscribe(EventType eventType, EventListener eventListener) {
        List<EventListener> listenersByType = listeners.get(eventType);
        listenersByType.add(eventListener);
    }

    @Override
    public void unsubscribe(EventType eventType, EventListener eventListener) {
        List<EventListener> listenersByType = listeners.get(eventType);
        listenersByType.remove(eventListener);
    }

    @Override
    public void notifySubs(EventType eventType, Object object) {
        List<EventListener> listenersByType = listeners.get(eventType);
        for (EventListener listener : listenersByType) {
            listener.update(eventType, object);
        }
    }
}
