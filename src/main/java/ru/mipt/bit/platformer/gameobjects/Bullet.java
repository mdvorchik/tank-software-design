package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.generator.Level;

import java.util.Collection;
import java.util.Collections;

import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Bullet implements Collidable {
    private final Level level;
    private final Direction direction;
    private final GridPoint2 coordinates;
    private final GridPoint2 destCoordinates;
    private final float rotation;
    private float movementProgress = 0f;
    private float movementProgressCounter = 0f;

    public Bullet(Level level, Tank tank, Direction direction) {
        this.level = level;
        this.coordinates = tank.getTankCoordinates().add(direction.getChangeVector());
        this.destCoordinates = this.coordinates;
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
                    collidable.registerHarmfulCollision();
                    level.registerBulletDestruction(this);
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
        movementProgress = continueProgress(movementProgress, deltaTime, 2f);
        if (movementProgress - movementProgressCounter > 1f) {
            movementProgressCounter += 1f;
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
}
