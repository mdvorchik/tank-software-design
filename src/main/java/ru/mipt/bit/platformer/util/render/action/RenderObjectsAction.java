package ru.mipt.bit.platformer.util.render.action;

import ru.mipt.bit.platformer.gameobjects.GDXDrawable;
import ru.mipt.bit.platformer.util.render.Render;

import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class RenderObjectsAction implements RenderAction {
    private final List<GDXDrawable> drawableList;

    public RenderObjectsAction(List<GDXDrawable> drawableList) {
        this.drawableList = drawableList;
    }

    @Override
    public void doAction(Render render) {
        // render drawable objects
        for (GDXDrawable gdxDrawable : drawableList) {
            drawTextureRegionUnscaled(render.getBatch(), gdxDrawable.getGraphics(),
                    gdxDrawable.getRectangle(), gdxDrawable.getRotation());
        }

    }
}
