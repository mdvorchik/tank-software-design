package ru.mipt.bit.platformer.util.render.action;

import ru.mipt.bit.platformer.util.render.RenderImpl;

public class RenderEachTileOfLevelAction implements RenderAction{
    @Override
    public void doAction(Object render) {
        // render each tile of the level
        if (render instanceof RenderImpl)
            ((RenderImpl) render).getLevelRenderer().render();
    }
}
