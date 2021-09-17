package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class Tree implements CollideAble, GDXDrawable {
    private final TextureRegion treeObstacleGraphics;
    private final GridPoint2 treeObstacleCoordinates;
    private final Rectangle treeObstacleRectangle;
    private final float rotation;

    public Tree(Texture greenTreeTexture, GridPoint2 treeObstacleCoordinates, float rotation) {
        this.treeObstacleGraphics = new TextureRegion(greenTreeTexture);
        this.treeObstacleCoordinates = treeObstacleCoordinates;
        this.treeObstacleRectangle = createBoundingRectangle(treeObstacleGraphics);
        this.rotation = rotation;
    }

    public GridPoint2 getTreeObstacleCoordinates() {
        return treeObstacleCoordinates;
    }

    @Override
    public boolean checkCollision(Object coordinates) {
        if (coordinates instanceof GridPoint2) {
            return treeObstacleCoordinates.equals(coordinates);
        }
        return false;
    }

    @Override
    public TextureRegion getGraphics() {
        return treeObstacleGraphics;
    }

    @Override
    public Rectangle getRectangle() {
        return treeObstacleRectangle;
    }

    @Override
    public float getRotation() {
        return rotation;
    }
}
