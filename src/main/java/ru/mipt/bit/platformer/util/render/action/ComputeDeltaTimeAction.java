package ru.mipt.bit.platformer.util.render.action;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.util.render.RenderImpl;

public class ComputeDeltaTimeAction implements RenderAction{
    @Override
    public void doAction(Object render) {
        if (render instanceof RenderImpl) {
            ((RenderImpl) render).setDeltaTime(Gdx.graphics.getDeltaTime());
        }
    }
}
