package ru.mipt.bit.platformer.actors;

import ru.mipt.bit.platformer.actors.action.ActionFromPlayer;

public interface Playable {
    void makeCommandFromPlayer(ActionFromPlayer action);
}
