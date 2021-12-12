package ru.mipt.bit.platformer.commands.impl;

import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class MoveCommand implements Command {
    private final Tank tank;
    private final Direction direction;

    public MoveCommand(Tank tank, Direction direction) {
        this.tank = tank;
        this.direction = direction;
    }

    @Override
    public void execute() {
        tank.move(direction);
    }
}
