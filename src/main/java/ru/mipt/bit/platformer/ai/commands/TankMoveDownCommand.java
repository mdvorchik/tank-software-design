package ru.mipt.bit.platformer.ai.commands;

import ru.mipt.bit.platformer.ai.TankCommand;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class TankMoveDownCommand implements TankCommand {
    private final Tank tank;

    public TankMoveDownCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.moveDown();
    }
}
