package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Port
 */
public interface GDXDrawable {
    void drawTexture(Batch batch);

    void drawMovement();

    Object getDrawnObject();

    void changeDrawingUIState();
}
