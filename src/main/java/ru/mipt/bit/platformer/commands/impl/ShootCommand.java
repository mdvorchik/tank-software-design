package ru.mipt.bit.platformer.commands.impl;

import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class ShootCommand implements Command {
    private final Tank tank;

    public ShootCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.shoot();
    }
}
