package ru.mipt.bit.platformer.physics;

import ru.mipt.bit.platformer.gameobjects.Collidable;

import java.util.List;

public class CollisionChecker {
    private final List<Collidable> collidables;

    public CollisionChecker(List<Collidable> collidables) {
        this.collidables = collidables;
    }

    public void addCollidable(Collidable collidable) {
        collidables.add(collidable);
    }

    public boolean isCollisionWithAnotherGameObject(Collidable collidable) {
        for (Collidable collidable1 : collidables) {
            if (!collidable.equals(collidable1) && collidable.checkCollision(collidable1)) return true;
        }
        return false;
    }
}
