package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;

import java.util.List;

public class CollisionCheckerImpl implements CollisionChecker {
    private final List<CollideAble> colliders;

    public CollisionCheckerImpl(List<CollideAble> colliders) {
        this.colliders = colliders;
    }

    @Override
    public boolean checkNotCollision(Object movableDirection) {
        boolean canMove = true;
        if (movableDirection instanceof GridPoint2) {
            for (CollideAble collider : colliders) {
                if (collider.checkCollision(movableDirection)) canMove = false;
            }
        }
        return canMove;
    }
}
