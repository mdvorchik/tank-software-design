package ru.mipt.bit.platformer.ai.external.adapter;

import org.awesome.ai.Action;
import org.awesome.ai.Recommendation;
import ru.mipt.bit.platformer.ai.TankCommand;
import ru.mipt.bit.platformer.ai.commands.TankMoveCommand;
import ru.mipt.bit.platformer.ai.commands.TankShootCommand;
import ru.mipt.bit.platformer.ai.commands.TankStayCommand;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gameobjects.Tank;

/**
 * Adapter
 */
public class CommandFromRecommendationCreator {
    public TankCommand createCommand(Recommendation recommendation) {
        Tank tank = (Tank) recommendation.getActor().getSource();
        if (recommendation.getAction() == Action.MoveNorth) {
            return new TankMoveCommand(tank, Direction.UP);
        } else if (recommendation.getAction() == Action.MoveEast) {
            return new TankMoveCommand(tank, Direction.RIGHT);
        } else if (recommendation.getAction() == Action.MoveSouth) {
            return new TankMoveCommand(tank, Direction.DOWN);
        } else if (recommendation.getAction() == Action.MoveWest) {
            return new TankMoveCommand(tank, Direction.LEFT);
        } else if (recommendation.getAction() == Action.Shoot) {
            return new TankShootCommand(tank);
        } else {
            return new TankStayCommand(tank);
        }
    }
}
