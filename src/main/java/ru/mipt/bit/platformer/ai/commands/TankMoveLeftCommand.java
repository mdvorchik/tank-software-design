package ru.mipt.bit.platformer.ai.commands;

import ru.mipt.bit.platformer.ai.TankCommand;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class TankMoveLeftCommand implements TankCommand {
    private final Tank tank;

    public TankMoveLeftCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.moveLeft();
    }
}
