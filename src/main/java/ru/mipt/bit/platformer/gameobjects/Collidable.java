package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Collection;

public interface Collidable {
    default boolean checkCollision(Collidable collidable) {
        for (GridPoint2 coordinateFromThisObject : this.getCoordinateList()) {
            for (GridPoint2 coordinateFromAnotherObject : collidable.getCoordinateList()) {
                if (coordinateFromThisObject.equals(coordinateFromAnotherObject)) return true;
            }
        }
        return false;
    }

    Collection<GridPoint2> getCoordinateList();
}
