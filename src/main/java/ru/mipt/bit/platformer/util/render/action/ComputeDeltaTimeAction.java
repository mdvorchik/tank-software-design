package ru.mipt.bit.platformer.util.render.action;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.util.render.Render;

public class ComputeDeltaTimeAction implements RenderAction {
    @Override
    public void doAction(Render render) {
        render.setDeltaTime(Gdx.graphics.getDeltaTime());
    }
}
