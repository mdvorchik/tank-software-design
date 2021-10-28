package ru.mipt.bit.platformer.ai.external.adapter;

import org.awesome.ai.state.movable.Bot;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class BotFromTankCreator {
    public Bot createBot(Tank bot, OrientationFromCoordinatesCreator orientationCreator) {
        return new Bot.BotBuilder()
                .source(bot)
                .x(bot.getPlayerCoordinates().x)
                .y(bot.getPlayerCoordinates().y)
                .destX(bot.getPlayerDestinationCoordinates().x)
                .destY(bot.getPlayerDestinationCoordinates().y)
                .orientation(orientationCreator.createOrientation(bot.getPlayerCoordinates(), bot.getPlayerDestinationCoordinates()))
                .build();

    }
}
