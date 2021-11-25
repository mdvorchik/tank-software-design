package ru.mipt.bit.platformer.graphics.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.graphics.GDXDrawable;
import ru.mipt.bit.platformer.graphics.TankGraphics;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class TankHealthBarGraphics implements GDXDrawable {
    private final TankGraphics tankGraphics;
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final TileMovement tileMovement;
    private Rectangle rectangle;
    private boolean drawUI = true;

    public TankHealthBarGraphics(TankGraphics tankGraphicsTexture, Texture texture, TileMovement tileMovement) {
        this.tankGraphics = tankGraphicsTexture;
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.tileMovement = tileMovement;
        this.rectangle = createBoundingRectangle(textureRegion);
    }

    @Override
    public void drawTexture(Batch batch) {
        tankGraphics.drawTexture(batch);
        if (drawUI) {
            Tank tank = (Tank) getDrawnObject();
            textureRegion.setRegionWidth(tank.getHealth() * 30);
            drawTextureRegionUnscaled(batch,
                    textureRegion,
                    rectangle.setCenter(rectangle.getX() + 63f, rectangle.getY() - 50f),
                    180f);
        }
    }

    @Override
    public void drawMovement() {
        if (drawUI) {
            Tank tank = (Tank) getDrawnObject();
            rectangle = tileMovement.moveRectangleBetweenTileCenters(rectangle, tank.getTankCoordinates(),
                    tank.getTankDestinationCoordinates(), tank.getTankMovementProgress());
        }
        tankGraphics.drawMovement();
    }

    @Override
    public Object getDrawnObject() {
        return tankGraphics.getDrawnObject();
    }

    @Override
    public void setDrawingUIState(boolean bool) {
        drawUI = bool;
    }
}
