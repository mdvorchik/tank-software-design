package ru.mipt.bit.platformer.ai;

import ru.mipt.bit.platformer.ai.commands.*;
import ru.mipt.bit.platformer.gameobjects.Tank;

import java.util.List;

public class TanksCommandGenerator {

    private final List<Tank> tanks;

    public TanksCommandGenerator(List<Tank> tanks) {
        this.tanks = tanks;
    }

    public TankCommand generateCommand() {
        int numberOfCommand = (int) (Math.random() * 7);
        int numberOfTank = ((int) (Math.random() * 100)) % tanks.size();
        switch (numberOfCommand) {
            case 0:
                return new TankMoveUpCommand(tanks.get(numberOfTank));
            case 1:
                return new TankMoveRightCommand(tanks.get(numberOfTank));
            case 2:
                return new TankMoveDownCommand(tanks.get(numberOfTank));
            case 3:
                return new TankMoveLeftCommand(tanks.get(numberOfTank));
            default:
                return new TankStayCommand(tanks.get(numberOfTank));
        }
    }
}
