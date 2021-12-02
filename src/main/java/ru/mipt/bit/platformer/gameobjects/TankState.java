package ru.mipt.bit.platformer.gameobjects;

import ru.mipt.bit.platformer.direction.Direction;

/**
 * Entity
 */
public interface TankState {
    long shoot();

    float processMovementProgress(float deltaTime);

    Direction move(Direction direction);
}
