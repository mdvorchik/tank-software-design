package ru.mipt.bit.platformer.util.render.action;

import ru.mipt.bit.platformer.actors.action.ActionFromPlayer;
import ru.mipt.bit.platformer.actors.event.EventFromPlayer;
import ru.mipt.bit.platformer.util.render.Render;

import java.util.List;

public class ProcessKeyPressedAction implements RenderAction {

    private final List<EventFromPlayer> eventFromPlayerList;

    public ProcessKeyPressedAction(List<EventFromPlayer> eventFromPlayerList) {
        this.eventFromPlayerList = eventFromPlayerList;
    }

    @Override
    public void doAction(Render render) {
        for (EventFromPlayer eventFromPlayer : eventFromPlayerList) {
            ActionFromPlayer actionFromPlayer = eventFromPlayer.makeAction();
            if (actionFromPlayer != null) actionFromPlayer.doAction();
        }
    }
}
