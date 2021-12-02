package ru.mipt.bit.platformer.ai.external.adapter;

import org.awesome.ai.state.movable.Bot;
import ru.mipt.bit.platformer.gameobjects.Tank;

/**
 * Adapter
 */
public class BotFromTankCreator {
    public Bot createBot(Tank bot, OrientationFromCoordinatesCreator orientationCreator) {
        return new Bot.BotBuilder()
                .source(bot)
                .x(bot.getTankCoordinates().x)
                .y(bot.getTankCoordinates().y)
                .destX(bot.getTankDestinationCoordinates().x)
                .destY(bot.getTankDestinationCoordinates().y)
                .orientation(orientationCreator.createOrientation(bot.getTankCoordinates(), bot.getTankDestinationCoordinates()))
                .build();

    }
}
