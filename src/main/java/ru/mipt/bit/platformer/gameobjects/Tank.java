package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gameobjects.tankstate.AverageDamagedTankState;
import ru.mipt.bit.platformer.gameobjects.tankstate.MuchDamagedTankState;
import ru.mipt.bit.platformer.gameobjects.tankstate.NoDamagedTankState;
import ru.mipt.bit.platformer.generator.Level;
import ru.mipt.bit.platformer.physics.CollisionChecker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

public class Tank implements Collidable {
    private final Level level;
    private final float movementSpeed;
    private final CollisionChecker collisionChecker;
    private final GridPoint2 tankCoordinates;
    private final GridPoint2 tankDestinationCoordinates;
    private TankState tankState;
    private int health = 2;
    private float tankMovementProgress = 1f;
    private Direction lastDirection = Direction.UP;
    private long lastShoot = new Date().getTime();

    public Tank(Level level, float movementSpeed, CollisionChecker collisionChecker, GridPoint2 playerCoordinates, GridPoint2 playerDestinationCoordinates) {
        this.level = level;
        this.movementSpeed = movementSpeed;
        this.collisionChecker = collisionChecker;
        this.tankCoordinates = playerCoordinates;
        this.tankDestinationCoordinates = playerDestinationCoordinates;
        tankState = new NoDamagedTankState(this, collisionChecker, level, movementSpeed, lastShoot, lastDirection);
    }


    public void move(Direction direction) {
        lastDirection = tankState.move(direction);
    }

    public void shoot() {
        tankState.shoot();
    }

    public void processMovementProgress(float deltaTime) {
        tankMovementProgress = tankState.processMovementProgress(deltaTime);
    }

    @Override
    public Collection<GridPoint2> getCoordinateList() {
        return Arrays.asList(tankCoordinates, tankDestinationCoordinates);
    }

    @Override
    public void registerHarmfulCollision() {
        health--;
        if (health == 2) {
            tankState = new AverageDamagedTankState(this, collisionChecker, level, movementSpeed, lastShoot, lastDirection);
        }
        if (health == 1) {
            tankState = new MuchDamagedTankState(this, collisionChecker, level, movementSpeed, lastShoot, lastDirection);
        }
        if (health <= 0) {
            collisionChecker.removeCollidable(this);
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
        return lastDirection.getRotation();
    }

    public int getHealth() {
        return health;
    }
}