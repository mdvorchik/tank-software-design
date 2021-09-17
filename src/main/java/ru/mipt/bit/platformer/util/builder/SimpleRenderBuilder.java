package ru.mipt.bit.platformer.util.builder;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import ru.mipt.bit.platformer.actors.Playable;
import ru.mipt.bit.platformer.gameobjects.CollideAble;
import ru.mipt.bit.platformer.gameobjects.CollisionChecker;
import ru.mipt.bit.platformer.gameobjects.CollisionCheckerImpl;
import ru.mipt.bit.platformer.gameobjects.GDXDrawable;
import ru.mipt.bit.platformer.util.render.Render;
import ru.mipt.bit.platformer.util.render.RenderImpl;
import ru.mipt.bit.platformer.util.render.action.*;

import java.util.ArrayList;
import java.util.List;

public class SimpleRenderBuilder implements RenderBuilder {

    private final Playable player;
    private final List<GDXDrawable> gdxDrawables;
    private final List<CollideAble> collideAbles;
    private final Batch batch;
    private final TiledMap level;

    public SimpleRenderBuilder(Playable player, List<GDXDrawable> gdxDrawables, List<CollideAble> collideAbles, Batch batch, TiledMap level) {
        this.player = player;
        this.gdxDrawables = gdxDrawables;
        this.collideAbles = collideAbles;
        this.batch = batch;
        this.level = level;
    }

    @Override
    public Render buildRender() {
        CollisionChecker collisionChecker = new CollisionCheckerImpl(collideAbles);
        KeyEventBuilder keyEventBuilder = new SimpleKeyEventBuilder(player, collisionChecker);
        List<RenderAction> renderActions = new ArrayList<>();
        renderActions.add(new ClearScreenAction());
        renderActions.add(new ComputeDeltaTimeAction());
        renderActions.add(new ProcessKeyPressedAction(keyEventBuilder.buildKeyEvents()));
        renderActions.add(new CalculateInterpolatedCoordinatesAction(player));
        renderActions.add(new RenderEachTileOfLevelAction());
        renderActions.add(new RecordingAllDrawingCommandAction());
        renderActions.add(new RenderObjectsAction(gdxDrawables));
        renderActions.add(new SubmitAllDrawingRequestsAction());
        return new RenderImpl(renderActions, batch, level);
    }
}
