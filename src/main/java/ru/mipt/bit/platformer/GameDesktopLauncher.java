package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.Tree;
import ru.mipt.bit.platformer.graphics.Renderer;
import ru.mipt.bit.platformer.graphics.TankGraphics;
import ru.mipt.bit.platformer.graphics.TreeGraphics;
import ru.mipt.bit.platformer.physics.GameEngine;

import java.util.ArrayList;
import java.util.List;


public class GameDesktopLauncher implements ApplicationListener {

    private List<Texture> textures;
    private GameEngine gameEngine;
    private Renderer renderer;

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }

    @Override
    public void create() {
        Tree tree = new Tree(new GridPoint2(1, 3), 0f);
        Tank tank = new Tank();

        InputProcessor inputProcessor = new InputProcessor(tank, tree);

        gameEngine = new GameEngine(inputProcessor, tank);
        renderer = new Renderer(new SpriteBatch(), new TmxMapLoader().load("level.tmx"), new ArrayList<>());

        textures = new ArrayList<>();
        // Texture decodes an image file and loads it into GPU memory, it represents a native resource
        Texture blueTankTexture = new Texture("images/tank_blue.png");
        textures.add(blueTankTexture);
        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
        Texture greenTreeTexture = new Texture("images/greenTree.png");
        textures.add(greenTreeTexture);

        TankGraphics tankGraphics = new TankGraphics(tank, blueTankTexture, renderer.getTileMovement());
        TreeGraphics treeGraphics = new TreeGraphics(tree, greenTreeTexture, renderer.getTileMovement());

        renderer.addDrawableObject(tankGraphics);
        renderer.addDrawableObject(treeGraphics);
        renderer.moveRectangleAtTileCenter(treeGraphics.getRectangle(), tree.getCoordinates());
    }

    @Override
    public void render() {
        gameEngine.doCalculations();
        renderer.doRender();
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
        for (Texture texture : textures) {
            texture.dispose();
        }
    }
}
