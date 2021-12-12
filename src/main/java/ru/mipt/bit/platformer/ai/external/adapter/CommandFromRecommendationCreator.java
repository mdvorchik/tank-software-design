package ru.mipt.bit.platformer.ai.external.adapter;

import org.awesome.ai.Action;
import org.awesome.ai.Recommendation;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.commands.impl.MoveCommand;
import ru.mipt.bit.platformer.commands.impl.ShootCommand;
import ru.mipt.bit.platformer.commands.impl.StayCommand;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class CommandFromRecommendationCreator {
    public Command createCommand(Recommendation recommendation) {
        Tank tank = (Tank) recommendation.getActor().getSource();
        if (recommendation.getAction() == Action.MoveNorth) {
            return new MoveCommand(tank, Direction.UP);
        } else if (recommendation.getAction() == Action.MoveEast) {
            return new MoveCommand(tank, Direction.RIGHT);
        } else if (recommendation.getAction() == Action.MoveSouth) {
            return new MoveCommand(tank, Direction.DOWN);
        } else if (recommendation.getAction() == Action.MoveWest) {
            return new MoveCommand(tank, Direction.LEFT);
        } else if (recommendation.getAction() == Action.Shoot) {
            return new ShootCommand(tank);
        } else {
            return new StayCommand(tank);
        }
    }
}
