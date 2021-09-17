package ru.mipt.bit.platformer.util.render.action;

import ru.mipt.bit.platformer.util.render.Render;

public class RecordingAllDrawingCommandAction implements RenderAction {
    @Override
    public void doAction(Render render) {
        // start recording all drawing commands
        render.getBatch().begin();
    }
}
