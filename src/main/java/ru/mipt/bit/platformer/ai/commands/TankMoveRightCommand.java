package ru.mipt.bit.platformer.ai.commands;

import ru.mipt.bit.platformer.ai.TankCommand;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class TankMoveRightCommand implements TankCommand {
    private final Tank tank;

    public TankMoveRightCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.moveRight();
    }
}
