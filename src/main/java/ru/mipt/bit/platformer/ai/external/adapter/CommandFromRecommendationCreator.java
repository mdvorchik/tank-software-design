package ru.mipt.bit.platformer.ai.external.adapter;

import org.awesome.ai.Action;
import org.awesome.ai.Recommendation;
import ru.mipt.bit.platformer.ai.TankCommand;
import ru.mipt.bit.platformer.ai.commands.*;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class CommandFromRecommendationCreator {
    public TankCommand createCommand(Recommendation recommendation) {
        Tank tank = (Tank) recommendation.getActor().getSource();
        if (recommendation.getAction() == Action.MoveNorth) {
            return new TankMoveUpCommand(tank);
        } else if (recommendation.getAction() == Action.MoveEast) {
            return new TankMoveRightCommand(tank);
        }else if (recommendation.getAction() == Action.MoveSouth) {
            return new TankMoveDownCommand(tank);
        }else if (recommendation.getAction() == Action.MoveWest) {
            return new TankMoveLeftCommand(tank);
        } else {
            return new TankStayCommand(tank);
        }
    }
}
