package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.util.GdxGameUtils;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class Renderer {
    private final Batch batch;
    private final MapRenderer levelRenderer;
    private final TiledMapTileLayer groundLayer;
    private final TileMovement tileMovement;
    private final List<GDXDrawable> drawables;

    public Renderer(Batch batch, TiledMap level, List<GDXDrawable> drawables) {
        this.batch = batch;
        this.drawables = drawables;
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    public void doRender() {
        clearScreen();
        drawObjectsMovement();
        renderEachTileOfLevel();
        recordAllDrawingCommands();
    }

    public void moveRectangleAtTileCenter(Rectangle rectangle, GridPoint2 coordinates) {
        GdxGameUtils.moveRectangleAtTileCenter(groundLayer, rectangle, coordinates);
    }

    private void recordAllDrawingCommands() {
        // start recording all drawing commands
        batch.begin();
        // render drawable objects
        for (GDXDrawable drawable : drawables) {
            drawable.drawTexture(batch);
        }
        // submit all drawing requests
        batch.end();
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }

    public void addDrawableObject(GDXDrawable drawable) {
        drawables.add(drawable);
    }

    private void renderEachTileOfLevel() {
        levelRenderer.render();
    }

    private void drawObjectsMovement() {
        for (GDXDrawable drawable : drawables) {
            drawable.drawMovement();
        }
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }
}
