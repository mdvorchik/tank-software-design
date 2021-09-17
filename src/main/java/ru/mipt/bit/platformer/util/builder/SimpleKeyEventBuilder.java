package ru.mipt.bit.platformer.util.builder;

import ru.mipt.bit.platformer.actors.Playable;
import ru.mipt.bit.platformer.actors.event.*;
import ru.mipt.bit.platformer.gameobjects.CollisionChecker;

import java.util.ArrayList;
import java.util.List;

public class SimpleKeyEventBuilder implements KeyEventBuilder{
    private final Playable player;
    private final CollisionChecker collisionChecker;

    public SimpleKeyEventBuilder(Playable player, CollisionChecker collisionChecker) {
        this.player = player;
        this.collisionChecker = collisionChecker;
    }

    @Override
    public List<EventFromPlayer> buildKeyEvents() {
        List<EventFromPlayer> events = new ArrayList<>();
        events.add(new UpKeyPressedEvent(player, collisionChecker, 1f));
        events.add(new DownKeyPressedEvent(player, collisionChecker, 1f));
        events.add(new RightKeyPressedEvent(player, collisionChecker, 1f));
        events.add(new LeftKeyPressedEvent(player, collisionChecker, 1f));
        return events;
    }
}
