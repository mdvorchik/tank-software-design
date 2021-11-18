package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.physics.CollisionChecker;

import java.util.Arrays;
import java.util.Collection;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank implements Collidable {
    private final float movementSpeed;
    private final CollisionChecker collisionChecker;
    private final GridPoint2 tankCoordinates;
    private GridPoint2 tankDestinationCoordinates;
    private float tankMovementProgress = 1f;
    private float tankRotation;

    public Tank(float movementSpeed, CollisionChecker collisionChecker, GridPoint2 playerCoordinates, GridPoint2 playerDestinationCoordinates) {
        this.movementSpeed = movementSpeed;
        this.collisionChecker = collisionChecker;
        this.tankCoordinates = playerCoordinates;
        this.tankDestinationCoordinates = playerDestinationCoordinates;
    }


    public void move(Direction direction) {
        if (!canMoveInThisTick()) return;
        tankDestinationCoordinates.add(direction.getChangeVector());
        if (isNotCollision()) {
            tankMovementProgress = 0f;
        } else {
            tankDestinationCoordinates.sub(direction.getChangeVector());
        }
        tankRotation = direction.getRotation();
    }

    public void processMovementProgress(float deltaTime) {
        tankMovementProgress = continueProgress(tankMovementProgress, deltaTime, movementSpeed);
        if (isEqual(tankMovementProgress, 1f)) {
            // record that the player has reached his/her destination
            tankCoordinates.set(tankDestinationCoordinates);
        }
    }

    @Override
    public Collection<GridPoint2> getCoordinateList() {
        return Arrays.asList(tankCoordinates, tankDestinationCoordinates);
    }

    public GridPoint2 getTankCoordinates() {
        return tankCoordinates;
    }

    public GridPoint2 getTankDestinationCoordinates() {
        return tankDestinationCoordinates;
    }

    public float getTankMovementProgress() {
        return tankMovementProgress;
    }

    public float getTankRotation() {
        return tankRotation;
    }

    private boolean canMoveInThisTick() {
        return isEqual(tankMovementProgress, 1f);
    }

    private boolean isNotCollision() {
        return !collisionChecker.isCollisionWithAnotherGameObject(this);
    }

    public void shoot() {
        //todo shoot
    }
}
