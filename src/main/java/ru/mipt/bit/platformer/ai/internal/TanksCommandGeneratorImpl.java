package ru.mipt.bit.platformer.ai.internal;

import ru.mipt.bit.platformer.ai.TankCommand;
import ru.mipt.bit.platformer.ai.TanksCommandGenerator;
import ru.mipt.bit.platformer.ai.commands.*;
import ru.mipt.bit.platformer.gameobjects.Tank;

import java.util.List;

public class TanksCommandGeneratorImpl implements TanksCommandGenerator {

    private final List<Tank> tanks;

    public TanksCommandGeneratorImpl(List<Tank> tanks) {
        this.tanks = tanks;
    }

    @Override
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
