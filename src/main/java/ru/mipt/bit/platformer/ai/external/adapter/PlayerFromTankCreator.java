package ru.mipt.bit.platformer.ai.external.adapter;

import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.gameobjects.Tank;

/**
 * Adapter
 */
public class PlayerFromTankCreator {
    public Player createPlayer(Tank player, OrientationFromCoordinatesCreator orientationCreator) {
        return new Player.PlayerBuilder()
                .source(player)
                .x(player.getTankCoordinates().x)
                .y(player.getTankCoordinates().y)
                .destX(player.getTankDestinationCoordinates().x)
                .destY(player.getTankDestinationCoordinates().y)
                .orientation(orientationCreator.createOrientation(player.getTankCoordinates(), player.getTankDestinationCoordinates()))
                .build();
    }
}
