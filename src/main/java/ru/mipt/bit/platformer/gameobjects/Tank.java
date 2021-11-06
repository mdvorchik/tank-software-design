package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.physics.CollisionChecker;

import java.util.Arrays;
import java.util.Collection;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank implements Collidable {
    private final float movementSpeed;
    private final CollisionChecker collisionChecker;
    private final GridPoint2 playerCoordinates;
    private GridPoint2 playerDestinationCoordinates;
    private float playerMovementProgress = 1f;
    private float playerRotation;

    public Tank(float movementSpeed, CollisionChecker collisionChecker, GridPoint2 playerCoordinates, GridPoint2 playerDestinationCoordinates) {
        this.movementSpeed = movementSpeed;
        this.collisionChecker = collisionChecker;
        this.playerCoordinates = playerCoordinates;
        this.playerDestinationCoordinates = playerDestinationCoordinates;
    }


    public void moveUp() {
        if (!canMoveInThisTick()) return;
        playerDestinationCoordinates = incrementedY(playerDestinationCoordinates);
        if (isNotCollision()) {
            playerMovementProgress = 0f;
        } else {
            playerDestinationCoordinates = decrementedY(playerDestinationCoordinates);
        }
        playerRotation = 90f;
    }

    public void moveLeft() {
        if (!canMoveInThisTick()) return;
        playerDestinationCoordinates = decrementedX(playerDestinationCoordinates);
        if (isNotCollision()) {
            playerMovementProgress = 0f;
        } else {
            playerDestinationCoordinates = incrementedX(playerDestinationCoordinates);
        }
        playerRotation = -180f;
    }

    public void moveDown() {
        if (!canMoveInThisTick()) return;
        playerDestinationCoordinates = decrementedY(playerDestinationCoordinates);
        if (isNotCollision()) {
            playerMovementProgress = 0f;
        } else {
            playerDestinationCoordinates = incrementedY(playerDestinationCoordinates);
        }
        playerRotation = -90f;
    }

    public void moveRight() {
        if (!canMoveInThisTick()) return;
        playerDestinationCoordinates = incrementedX(playerDestinationCoordinates);
        if (isNotCollision()) {
            playerMovementProgress = 0f;
        } else {
            playerDestinationCoordinates = decrementedX(playerDestinationCoordinates);
        }
        playerRotation = 0f;
    }

    public void processMovementProgress(float deltaTime) {
        playerMovementProgress = continueProgress(playerMovementProgress, deltaTime, movementSpeed);
        if (isEqual(playerMovementProgress, 1f)) {
            // record that the player has reached his/her destination
            playerCoordinates.set(playerDestinationCoordinates);
        }
    }

    @Override
    public Collection<GridPoint2> getCoordinateList() {
        return Arrays.asList(playerCoordinates, playerDestinationCoordinates);
    }

    public GridPoint2 getPlayerCoordinates() {
        return playerCoordinates;
    }

    public GridPoint2 getPlayerDestinationCoordinates() {
        return playerDestinationCoordinates;
    }

    public float getPlayerMovementProgress() {
        return playerMovementProgress;
    }

    public float getPlayerRotation() {
        return playerRotation;
    }

    private boolean canMoveInThisTick() {
        return isEqual(playerMovementProgress, 1f);
    }

    private boolean isNotCollision() {
        return !collisionChecker.isCollisionWithAnotherGameObject(this);
    }
}
