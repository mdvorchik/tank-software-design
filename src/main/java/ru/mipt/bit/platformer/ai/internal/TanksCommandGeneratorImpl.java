package ru.mipt.bit.platformer.ai.internal;

import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.ai.TanksCommandGenerator;
import ru.mipt.bit.platformer.commands.impl.MoveCommand;
import ru.mipt.bit.platformer.commands.impl.ShootCommand;
import ru.mipt.bit.platformer.commands.impl.StayCommand;
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
    public List<Command> generateCommands(float deltaTime) {
        progress += deltaTime;
        List<Command> commands = new ArrayList<>();
        if (progress > interval) {
            progress = 0f;
            for (Tank tank : tanks) {
                int numberOfCommand = (int) (Math.random() * 7);
                switch (numberOfCommand) {
                    case 0:
                        commands.add(new MoveCommand(tank, Direction.UP));
                        break;
                    case 1:
                        commands.add(new MoveCommand(tank, Direction.RIGHT));
                        break;
                    case 2:
                        commands.add(new MoveCommand(tank, Direction.DOWN));
                        break;
                    case 3:
                        commands.add(new MoveCommand(tank, Direction.LEFT));
                        break;
                    case 4:
                    case 5:
                        commands.add(new ShootCommand(tank));
                        break;
                    default:
                        commands.add(new StayCommand(tank));
                }
            }
        }
        return commands;
    }
}
