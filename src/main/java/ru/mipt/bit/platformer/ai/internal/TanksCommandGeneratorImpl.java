package ru.mipt.bit.platformer.ai.internal;

import ru.mipt.bit.platformer.ai.TankCommand;
import ru.mipt.bit.platformer.ai.TanksCommandGenerator;
import ru.mipt.bit.platformer.ai.commands.TankMoveCommand;
import ru.mipt.bit.platformer.ai.commands.TankStayCommand;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gameobjects.Tank;

import java.util.ArrayList;
import java.util.List;

public class TanksCommandGeneratorImpl implements TanksCommandGenerator {

    private final List<Tank> tanks;
    private final float interval;
    private float progress = 0f;

    public TanksCommandGeneratorImpl(List<Tank> tanks, float interval) {
        this.tanks = tanks;
        this.interval = interval;
    }

    @Override
    public List<TankCommand> generateCommands(float deltaTime) {
        progress += deltaTime;
        List<TankCommand> tankCommands = new ArrayList<>();
        if (progress > interval) {
            progress = 0f;
            for (Tank tank : tanks) {
                int numberOfCommand = (int) (Math.random() * 7);
                switch (numberOfCommand) {
                    case 0:
                        tankCommands.add(new TankMoveCommand(tank, Direction.UP));
                        break;
                    case 1:
                        tankCommands.add(new TankMoveCommand(tank, Direction.RIGHT));
                        break;
                    case 2:
                        tankCommands.add(new TankMoveCommand(tank, Direction.DOWN));
                        break;
                    case 3:
                        tankCommands.add(new TankMoveCommand(tank, Direction.LEFT));
                        break;
                    default:
                        tankCommands.add(new TankStayCommand(tank));
                }
            }
        }
        return tankCommands;
    }
}
