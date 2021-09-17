package ru.mipt.bit.platformer.util.render.action;

import ru.mipt.bit.platformer.util.render.RenderImpl;

public class RecordingAllDrawingCommandAction implements RenderAction{
    @Override
    public void doAction(Object render) {
        // start recording all drawing commands
        if (render instanceof RenderImpl) {
            ((RenderImpl) render).getBatch().begin();
        }
    }
}
