package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.actors.Tank;
import ru.mipt.bit.platformer.gameobjects.CollideAble;
import ru.mipt.bit.platformer.gameobjects.GDXDrawable;
import ru.mipt.bit.platformer.gameobjects.Tree;
import ru.mipt.bit.platformer.util.builder.RenderBuilder;
import ru.mipt.bit.platformer.util.builder.SimpleRenderBuilder;
import ru.mipt.bit.platformer.util.render.Render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

public class GameDesktopLauncher implements ApplicationListener {

    private final List<Disposable> disposables = new ArrayList<>();
    private Render render;

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }

    @Override
    public void create() {
        Texture blueTankTexture = new Texture("images/tank_blue.png");
        Tank tank = new Tank(blueTankTexture);

        Texture greenTreeTexture = new Texture("images/greenTree.png");
        Tree tree = new Tree(greenTreeTexture, new GridPoint2(3, 3), 0f);

        List<GDXDrawable> drawables = new ArrayList<>(Arrays.asList(tank, tree));
        List<CollideAble> collideAbles = new ArrayList<>(Collections.singletonList(tree));

        Batch batch = new SpriteBatch();
        TiledMap level = new TmxMapLoader().load("level.tmx");
        RenderBuilder renderBuilder = new SimpleRenderBuilder(tank, drawables, collideAbles, batch, level);
        render = renderBuilder.buildRender();

        disposables.add(blueTankTexture);
        disposables.add(greenTreeTexture);
        disposables.add(batch);
        disposables.add(level);

        moveRectangleAtTileCenter(render.getGroundLayer(), tree.getRectangle(), tree.getTreeObstacleCoordinates());
    }

    @Override
    public void render() {
        render.doRender();
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        for (Disposable disposable : disposables) {
            disposable.dispose();
        }
    }
}
