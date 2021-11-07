package ru.mipt.bit.platformer.ai.internal;

import ru.mipt.bit.platformer.ai.TankCommand;
import ru.mipt.bit.platformer.ai.TanksCommandGenerator;
import ru.mipt.bit.platformer.ai.commands.*;
import ru.mipt.bit.platformer.direction.Direction;
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
                return new TankMoveCommand(tanks.get(numberOfTank), Direction.UP);
            case 1:
                return new TankMoveCommand(tanks.get(numberOfTank), Direction.RIGHT);
            case 2:
                return new TankMoveCommand(tanks.get(numberOfTank), Direction.DOWN);
            case 3:
                return new TankMoveCommand(tanks.get(numberOfTank), Direction.LEFT);
            default:
                return new TankStayCommand(tanks.get(numberOfTank));
        }
    }
}
