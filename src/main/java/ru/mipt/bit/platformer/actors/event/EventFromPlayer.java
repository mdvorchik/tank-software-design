package ru.mipt.bit.platformer.actors.event;

import ru.mipt.bit.platformer.actors.action.ActionFromPlayer;

public interface EventFromPlayer {
    ActionFromPlayer makeAction();
}
