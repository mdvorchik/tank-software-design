package ru.mipt.bit.platformer.util.render.action;

import ru.mipt.bit.platformer.util.render.Render;

public class SubmitAllDrawingRequestsAction implements RenderAction{

    @Override
    public void doAction(Render render) {
        // submit all drawing requests
        render.getBatch().end();
    }
}
