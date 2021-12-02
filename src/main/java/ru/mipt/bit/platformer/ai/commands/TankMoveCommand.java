package ru.mipt.bit.platformer.ai.commands;

import ru.mipt.bit.platformer.ai.TankCommand;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gameobjects.Tank;

/**
 * Use-case
 */
public class TankMoveCommand implements TankCommand {
    private final Tank tank;
    private final Direction direction;

    public TankMoveCommand(Tank tank, Direction direction) {
        this.tank = tank;
        this.direction = direction;
    }

    @Override
    public void execute() {
        tank.move(direction);
    }
}
