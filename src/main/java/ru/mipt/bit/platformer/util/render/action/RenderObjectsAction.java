package ru.mipt.bit.platformer.util.render.action;

import ru.mipt.bit.platformer.gameobjects.GDXDrawable;
import ru.mipt.bit.platformer.util.render.RenderImpl;

import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class RenderObjectsAction implements RenderAction {
    private final List<GDXDrawable> drawableList;

    public RenderObjectsAction(List<GDXDrawable> drawableList) {
        this.drawableList = drawableList;
    }

    @Override
    public void doAction(Object render) {
        // render drawable objects
        if (render instanceof RenderImpl) {
            for (GDXDrawable gdxDrawable : drawableList) {
                drawTextureRegionUnscaled(((RenderImpl) render).getBatch(), gdxDrawable.getGraphics(),
                        gdxDrawable.getRectangle(), gdxDrawable.getRotation());
            }
        }
    }
}
