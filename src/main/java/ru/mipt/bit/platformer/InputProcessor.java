package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.ai.TankCommand;
import ru.mipt.bit.platformer.ai.commands.*;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.event.EventListener;
import ru.mipt.bit.platformer.event.EventPublisher;
import ru.mipt.bit.platformer.event.EventType;
import ru.mipt.bit.platformer.gameobjects.Tank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.badlogic.gdx.Input.Keys.*;

/**
 * Port
 */
public class InputProcessor implements EventPublisher {

    private final Tank tank;
    private final Map<EventType, List<EventListener>> listeners = new HashMap<>();

    public InputProcessor(Tank tank, List<EventType> eventTypes) {
        this.tank = tank;
        eventTypes.forEach(e -> listeners.put(e, new ArrayList<>()));
    }

    public TankCommand processInputs() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            return new TankMoveCommand(tank, Direction.UP);
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            return new TankMoveCommand(tank, Direction.LEFT);
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return new TankMoveCommand(tank, Direction.DOWN);
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return new TankMoveCommand(tank, Direction.RIGHT);
        }
        if (Gdx.input.isKeyPressed(SPACE)) {
            return new TankShootCommand(tank);
        }
        if (Gdx.input.isKeyJustPressed(L)) {
            notifySubs(EventType.CHANGE_UI_RENDER_MODE, new Object());
        }
        return new TankStayCommand(tank);
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
