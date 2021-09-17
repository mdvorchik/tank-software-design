package ru.mipt.bit.platformer.util.render.action;

import ru.mipt.bit.platformer.util.render.Render;

public class RenderEachTileOfLevelAction implements RenderAction {
    @Override
    public void doAction(Render render) {
        // render each tile of the level
        render.getLevelRenderer().render();
    }
}
