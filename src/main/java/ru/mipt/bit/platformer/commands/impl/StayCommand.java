package ru.mipt.bit.platformer.commands.impl;

import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class StayCommand implements Command {
    private final Tank tank;

    public StayCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {
        //do nothing
    }
}
