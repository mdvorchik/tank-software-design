package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public interface GDXDrawable {
    TextureRegion getGraphics();

    Rectangle getRectangle();

    float getRotation();
}
