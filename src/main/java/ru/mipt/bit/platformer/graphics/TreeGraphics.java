package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.gameobjects.Tree;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class TreeGraphics implements GDXDrawable {
    private final Tree tree;
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final TileMovement tileMovement;

    public TreeGraphics(Tree tree, Texture texture, TileMovement tileMovement) {
        this.tree = tree;
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.tileMovement = tileMovement;
        this.rectangle = createBoundingRectangle(textureRegion);
    }

    @Override
    public void drawTexture(Batch batch) {
        drawTextureRegionUnscaled(batch, textureRegion, rectangle, tree.getRotation());
    }

    @Override
    public void drawMovement() {

    }

    @Override
    public Object getDrawnObject() {
        return tree;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
