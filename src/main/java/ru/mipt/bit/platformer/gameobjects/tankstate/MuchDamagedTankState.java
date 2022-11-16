package ru.mipt.bit.platformer.gameobjects.tankstate;

import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.TankState;
import ru.mipt.bit.platformer.generator.Level;
import ru.mipt.bit.platformer.physics.CollisionChecker;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

/**
 * Use-case
 */
public class MuchDamagedTankState implements TankState {
    private final Tank tank;
    private final CollisionChecker collisionChecker;
    private final Level level;
    private final float movementSpeed;
    private final long lastShoot;
    private float tankMovementProgress;
    private Direction lastDirection;


    public MuchDamagedTankState(Tank tank, CollisionChecker collisionChecker, Level level, float movementSpeed, long lastShoot, Direction lastDirection) {
        this.tank = tank;
        this.collisionChecker = collisionChecker;
        this.level = level;
        this.tankMovementProgress = tank.getTankMovementProgress();
        this.lastDirection = lastDirection;
        this.movementSpeed = movementSpeed;
        this.lastShoot = lastShoot;
    }

    @Override
    public long shoot() {
        if (!canChootInThisTick()) return lastShoot;
        Bullet bullet = new Bullet(collisionChecker, level, tank, lastDirection);
        level.registerBulletCreation(bullet);
        collisionChecker.addCollidable(bullet);
        return lastShoot;
    }

    @Override
    public Direction move(Direction direction) {
        if (!canMoveInThisTick()) return lastDirection;
        tank.getTankDestinationCoordinates().add(direction.getChangeVector());
        if (isNotCollision()) {
            tankMovementProgress = 0f;
        } else {
            tank.getTankDestinationCoordinates().sub(direction.getChangeVector());
        }
        lastDirection = direction;
        return lastDirection;
    }

    @Override
    public float processMovementProgress(float deltaTime) {
        tankMovementProgress = continueProgress(tankMovementProgress, deltaTime, movementSpeed * 3);
        if (isEqual(tankMovementProgress, 1f)) {
            // record that the player has reached his/her destination
            tank.getTankCoordinates().set(tank.getTankDestinationCoordinates());
        }
        return tankMovementProgress;
    }

    private boolean canChootInThisTick() {
        return false;
    }

    private boolean canMoveInThisTick() {
        return isEqual(tankMovementProgress, 1f);
    }

    private boolean isNotCollision() {
        return !collisionChecker.isCollisionWithAnotherGameObject(tank);
    }
}
