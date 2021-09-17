package ru.mipt.bit.platformer.util.render;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.util.TileMovement;
import ru.mipt.bit.platformer.util.render.action.RenderAction;

import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class RenderImpl implements Render {
    private final List<RenderAction> renderActionList;

    private final Batch batch;
    private final MapRenderer levelRenderer;
    private final TileMovement tileMovement;
    private final TiledMapTileLayer groundLayer;

    private float deltaTime;

    public RenderImpl(List<RenderAction> renderActionList, Batch batch, TiledMap level) {
        this.renderActionList = renderActionList;
        this.batch = batch;
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    @Override
    public void doRender() {
        for (RenderAction renderAction : renderActionList) {
            renderAction.doAction(this);
        }
    }

    public void setDeltaTime(float deltaTime) {
        this.deltaTime = deltaTime;
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }

    public float getDeltaTime() {
        return deltaTime;
    }

    public MapRenderer getLevelRenderer() {
        return levelRenderer;
    }

    public Batch getBatch() {
        return batch;
    }

    public TiledMapTileLayer getGroundLayer() {
        return groundLayer;
    }
}
