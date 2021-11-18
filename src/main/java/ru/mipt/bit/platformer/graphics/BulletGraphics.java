package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class BulletGraphics implements GDXDrawable {
    private final Bullet bullet;
    private final TextureRegion textureRegion;
    private final TileMovement tileMovement;
    private Rectangle rectangle;

    public BulletGraphics(Bullet bullet, Texture texture, TileMovement tileMovement) {
        this.bullet = bullet;
        this.textureRegion = new TextureRegion(texture);
        this.tileMovement = tileMovement;
        this.rectangle = createBoundingRectangle(textureRegion);
    }

    @Override
    public void drawTexture(Batch batch) {
        drawTextureRegionUnscaled(batch, textureRegion, rectangle, bullet.getRotation());
    }

    @Override
    public void drawMovement() {
        rectangle = tileMovement.moveRectangleBetweenTileCenters(rectangle, bullet.getCoordinates(),
                bullet.getDestCoordinates(), bullet.getMovementProgress());
    }

    @Override
    public Object getDrawnObject() {
        return bullet;
    }
}
