package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.generator.Level;
import ru.mipt.bit.platformer.physics.CollisionChecker;

import java.util.Collection;
import java.util.Collections;

import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Bullet implements Collidable {
    private final Level level;
    private final Tank tank;
    private final CollisionChecker collisionChecker;
    private final Direction direction;
    private final GridPoint2 coordinates;
    private final GridPoint2 destCoordinates;
    private final float rotation;
    private float movementProgress = 0f;
    private float movementProgressCounter = 0f;

    public Bullet(CollisionChecker collisionChecker, Level level, Tank tank, Direction direction) {
        this.collisionChecker = collisionChecker;
        this.level = level;
        this.tank = tank;
        GridPoint2 tempCoordinate = new GridPoint2(tank.getTankCoordinates());
        tempCoordinate.add(direction.getChangeVector());
        this.coordinates = tempCoordinate;
        this.destCoordinates = new GridPoint2(this.coordinates);
        this.rotation = tank.getTankRotation();
        this.direction = direction;
        this.destCoordinates.x += direction.getChangeVector().x * 15;
        this.destCoordinates.y += direction.getChangeVector().y * 15;
    }

    @Override
    public boolean checkCollision(Collidable collidable) {
        for (GridPoint2 coordinateFromThisObject : this.getCoordinateList()) {
            for (GridPoint2 coordinateFromAnotherObject : collidable.getCoordinateList()) {
                if (coordinateFromThisObject.equals(coordinateFromAnotherObject)) {
                    if (collidable != tank) {
                        collidable.registerHarmfulCollision();
                    }
                    level.registerBulletDestruction(this);
                    collisionChecker.removeCollidable(this);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Collection<GridPoint2> getCoordinateList() {
        return Collections.singletonList(coordinates);
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public void processMovementProgress(float deltaTime) {
        if (!isNotCollision()) return;
        movementProgress = continueProgress(movementProgress, deltaTime, 1f);
        if (movementProgress - movementProgressCounter > 0.2f) {
            movementProgressCounter += .2f;
            coordinates.add(direction.getChangeVector());
        }
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public GridPoint2 getDestCoordinates() {
        return destCoordinates;
    }

    public float getRotation() {
        return rotation;
    }

    private boolean isNotCollision() {
        return !collisionChecker.isCollisionWithAnotherGameObject(this);
    }
}
