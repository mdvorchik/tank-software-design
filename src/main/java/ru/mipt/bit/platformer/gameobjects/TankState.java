package ru.mipt.bit.platformer.gameobjects;

import ru.mipt.bit.platformer.direction.Direction;

public interface TankState {
    long shoot();

    float processMovementProgress(float deltaTime);

    Direction move(Direction direction);
}
