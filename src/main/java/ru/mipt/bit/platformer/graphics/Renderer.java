package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.event.EventListener;
import ru.mipt.bit.platformer.event.EventType;
import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.generator.RendererBuilder;
import ru.mipt.bit.platformer.util.GdxGameUtils;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class Renderer implements EventListener {
    private final Batch batch;
    private final MapRenderer levelRenderer;
    private final TiledMapTileLayer groundLayer;
    private final TileMovement tileMovement;
    private final List<GDXDrawable> drawables;
    private final RendererBuilder rendererBuilder;

    public Renderer(RendererBuilder rendererBuilder, Batch batch, TiledMap level, List<GDXDrawable> drawables) {
        this.rendererBuilder = rendererBuilder;
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

    @Override
    public void update(EventType eventType, Object object) {
        if (eventType.equals(EventType.CHANGE_UI_RENDER_MODE)) {
            drawables.forEach(d -> d.changeDrawingUIState());
        }
        if (eventType.equals(EventType.ADD_BULLET)) {
            rendererBuilder.generateBulletGraphics((Bullet) object);
        }
        if (eventType.equals(EventType.REMOVE_BULLET) || eventType.equals(EventType.REMOVE_TANK)) {
            for (GDXDrawable drawable : drawables) {
                if (drawable.getDrawnObject() == object) {
                    removeDrawableObject(drawable);
                }
            }
        }
    }

    private void removeDrawableObject(GDXDrawable drawable) {
        drawables.remove(drawable);
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
