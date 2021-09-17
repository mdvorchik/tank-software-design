package ru.mipt.bit.platformer.util.render.action;

import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class ClearScreenAction implements RenderAction {
    @Override
    public void doAction(Object render) {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }
}
