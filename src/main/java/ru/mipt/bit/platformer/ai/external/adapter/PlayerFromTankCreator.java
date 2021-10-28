package ru.mipt.bit.platformer.ai.external.adapter;

import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class PlayerFromTankCreator {
    public Player createPlayer(Tank player, OrientationFromCoordinatesCreator orientationCreator) {
        return new Player.PlayerBuilder()
                .source(player)
                .x(player.getPlayerCoordinates().x)
                .y(player.getPlayerCoordinates().y)
                .destX(player.getPlayerDestinationCoordinates().x)
                .destY(player.getPlayerDestinationCoordinates().y)
                .orientation(orientationCreator.createOrientation(player.getPlayerCoordinates(), player.getPlayerDestinationCoordinates()))
                .build();
    }
}
