package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Collection;
import java.util.Collections;

/**
 * Entity
 */
public class Tree implements Collidable {
    private final GridPoint2 treeObstacleCoordinates;
    private final float rotation;

    public Tree(GridPoint2 treeObstacleCoordinates, float rotation) {
        this.treeObstacleCoordinates = treeObstacleCoordinates;
        this.rotation = rotation;
    }

    @Override
    public Collection<GridPoint2> getCoordinateList() {
        return Collections.singleton(treeObstacleCoordinates);
    }

    public GridPoint2 getCoordinates() {
        return treeObstacleCoordinates;
    }

    public float getRotation() {
        return rotation;
    }
}
