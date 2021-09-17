package ru.mipt.bit.platformer.util.render.action;

import ru.mipt.bit.platformer.util.render.RenderImpl;

public class SubmitAllDrawingRequestsAction implements RenderAction{

    @Override
    public void doAction(Object render) {
        // submit all drawing requests
        if (render instanceof RenderImpl) {
            ((RenderImpl) render).getBatch().end();
        }
    }
}
