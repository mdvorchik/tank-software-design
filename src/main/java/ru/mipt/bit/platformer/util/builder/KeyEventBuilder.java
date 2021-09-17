package ru.mipt.bit.platformer.util.builder;

import ru.mipt.bit.platformer.actors.event.EventFromPlayer;

import java.util.List;

public interface KeyEventBuilder {
    List<EventFromPlayer> buildKeyEvents();
}
