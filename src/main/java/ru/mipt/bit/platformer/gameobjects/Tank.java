package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.generator.Level;
import ru.mipt.bit.platformer.physics.CollisionChecker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank implements Collidable {
    private int health = 3;
    private final Level level;
    private final float movementSpeed;
    private final CollisionChecker collisionChecker;
    private final GridPoint2 tankCoordinates;
    private GridPoint2 tankDestinationCoordinates;
    private float tankMovementProgress = 1f;
    private float tankRotation;
    private Direction lastDirection = Direction.UP;
    private long lastShoot = new Date().getTime();

    public Tank(Level level, float movementSpeed, CollisionChecker collisionChecker, GridPoint2 playerCoordinates, GridPoint2 playerDestinationCoordinates) {
        this.level = level;
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
        lastDirection = direction;
    }

    public void shoot() {
        if (!canChootInThisTick()) return;
        Bullet bullet = new Bullet(level, this, lastDirection);
        level.registerBulletCreation(bullet);
        collisionChecker.addCollidable(bullet);
    }

    private boolean canChootInThisTick() {
        long nowDate = new Date().getTime();
        if (nowDate - lastShoot > 1000) {
            lastShoot = nowDate;
            return true;
        }
        return false;
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

    @Override
    public void registerHarmfulCollision() {
        health--;
        if (health <= 0) {
            level.registerTankDestruction(this);
        }
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
}
