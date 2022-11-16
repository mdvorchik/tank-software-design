package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

/**
 * Use-case
 */
public class TankGraphics implements GDXDrawable {
    private final Tank tank;
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final TileMovement tileMovement;
    private Rectangle rectangle;

    public TankGraphics(Tank tank, Texture texture, TileMovement tileMovement) {
        this.tank = tank;
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.tileMovement = tileMovement;
        this.rectangle = createBoundingRectangle(textureRegion);
    }

    @Override
    public void drawTexture(Batch batch) {
        drawTextureRegionUnscaled(batch, textureRegion, rectangle, tank.getTankRotation());
    }

    @Override
    public void drawMovement() {
        rectangle = tileMovement.moveRectangleBetweenTileCenters(rectangle, tank.getTankCoordinates(),
                tank.getTankDestinationCoordinates(), tank.getTankMovementProgress());
    }

    @Override
    public Object getDrawnObject() {
        return tank;
    }

    @Override
    public void changeDrawingUIState() {

    }
}
