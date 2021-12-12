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

    public Renderer getRenderer() {
        return renderer;
    }

    public void generateTreeGraphics(Tree tree) {
        TreeGraphics treeGraphics = new TreeGraphics(tree, treeTexture, renderer.getTileMovement());
        renderer.addDrawableObject(treeGraphics);
        renderer.moveRectangleAtTileCenter(treeGraphics.getRectangle(), tree.getCoordinates());
    }

    public void generateTankGraphics(Tank tank) {
        TankGraphics tankGraphics = new TankGraphics(tank, tankTexture, renderer.getTileMovement());
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
