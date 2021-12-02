package ru.mipt.bit.platformer.generator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.Tree;
import ru.mipt.bit.platformer.graphics.BulletGraphics;
import ru.mipt.bit.platformer.graphics.Renderer;
import ru.mipt.bit.platformer.graphics.TankGraphics;
import ru.mipt.bit.platformer.graphics.TreeGraphics;
import ru.mipt.bit.platformer.graphics.ui.TankHealthBarGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Use-case
 */
public class RendererBuilder {
    private final Renderer renderer;
    private final List<Texture> textures = new ArrayList<>();
    private final Texture tankTexture;
    private final Texture healthTexture;
    private final Texture treeTexture;
    private final Texture bulletTexture;

    public RendererBuilder(String levelConfigFileName,
                           String tankTextureFile,
                           String heathTextureFile,
                           String treeTextureFile,
                           String bulletTextureFile) {
        renderer = new Renderer(this, new SpriteBatch(), new TmxMapLoader().load(levelConfigFileName), new CopyOnWriteArrayList<>());
        tankTexture = new Texture(tankTextureFile);
        textures.add(tankTexture);
        healthTexture = new Texture(heathTextureFile);
        textures.add(healthTexture);
        treeTexture = new Texture(treeTextureFile);
        textures.add(treeTexture);
        bulletTexture = new Texture(bulletTextureFile);
        textures.add(bulletTexture);
    }

    public Renderer generateRenderer(LevelGenerator randomLevelGenerator) {
        generateTankGraphics(randomLevelGenerator);
        generateTreeGraphics(randomLevelGenerator);
        return renderer;
    }

    private void generateTreeGraphics(LevelGenerator randomLevelGenerator) {
        List<Tree> trees = randomLevelGenerator.getLevel().getTrees();
        for (Tree tree : trees) {
            TreeGraphics treeGraphics = new TreeGraphics(tree, treeTexture, renderer.getTileMovement());
            renderer.addDrawableObject(treeGraphics);
            renderer.moveRectangleAtTileCenter(treeGraphics.getRectangle(), tree.getCoordinates());
        }
    }

    private void generateTankGraphics(LevelGenerator randomLevelGenerator) {
        generatePlayerTankGraphics(randomLevelGenerator);
        List<Tank> tanks = randomLevelGenerator.getLevel().getTanks();
        for (Tank tank : tanks) {
            TankGraphics tankGraphics = new TankGraphics(tank, tankTexture, renderer.getTileMovement());
            TankHealthBarGraphics tankHealthBarGraphics = new TankHealthBarGraphics(tankGraphics, healthTexture, renderer.getTileMovement());
            renderer.addDrawableObject(tankHealthBarGraphics);
        }
    }

    private void generatePlayerTankGraphics(LevelGenerator randomLevelGenerator) {
        Tank playerTank = randomLevelGenerator.getLevel().getPlayerTank();
        TankGraphics tankGraphics = new TankGraphics(playerTank, tankTexture, renderer.getTileMovement());
        TankHealthBarGraphics tankHealthBarGraphics = new TankHealthBarGraphics(tankGraphics, healthTexture, renderer.getTileMovement());
        renderer.addDrawableObject(tankHealthBarGraphics);
    }

    public void generateBulletGraphics(Bullet bullet) {
        BulletGraphics bulletGraphics = new BulletGraphics(bullet, bulletTexture, renderer.getTileMovement());
        renderer.addDrawableObject(bulletGraphics);
    }

    public List<Texture> getTextures() {
        return textures;
    }
}
