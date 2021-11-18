package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.ai.internal.TanksCommandGeneratorImpl;
import ru.mipt.bit.platformer.generator.Level;
import ru.mipt.bit.platformer.generator.LevelGenerator;
import ru.mipt.bit.platformer.generator.RendererBuilder;
import ru.mipt.bit.platformer.generator.impl.FromFileLevelGenerator;
import ru.mipt.bit.platformer.graphics.Renderer;
import ru.mipt.bit.platformer.physics.GameEngine;


public class GameDesktopLauncher implements ApplicationListener {

    private RendererBuilder rendererBuilder;
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
//        LevelGenerator levelGenerator = new RandomLevelGenerator(10, 8, 4);
        LevelGenerator levelGenerator = new FromFileLevelGenerator("src/main/resources/level.txt");
        Level level = levelGenerator.getLevel();
        rendererBuilder = new RendererBuilder("level.tmx", "images/tank_blue.png", "images/greenTree.png");
        gameEngine = new GameEngine(level, new TanksCommandGeneratorImpl(level.getTanks(), 1f));
        renderer = rendererBuilder.generateRenderer(levelGenerator);
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
        for (Texture texture : rendererBuilder.getTextures()) {
            texture.dispose();
        }
    }
}
