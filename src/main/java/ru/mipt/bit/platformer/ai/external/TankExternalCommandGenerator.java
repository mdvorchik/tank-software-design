package ru.mipt.bit.platformer.ai.external;

import org.awesome.ai.AI;
import org.awesome.ai.Recommendation;
import org.awesome.ai.state.GameState;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.ai.TanksCommandGenerator;
import ru.mipt.bit.platformer.ai.external.adapter.CommandFromRecommendationCreator;
import ru.mipt.bit.platformer.ai.external.adapter.GameStateFromLevelCreator;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.Tree;

import java.util.ArrayList;
import java.util.List;

public class TankExternalCommandGenerator implements TanksCommandGenerator {
    private final AI ai;
    private final GameState gameState;
    private final CommandFromRecommendationCreator commandCreator = new CommandFromRecommendationCreator();
    private final float interval;
    private float progress;

    TankExternalCommandGenerator(AI ai, List<Tree> trees, List<Tank> bots, Tank player, int levelWidth, int levelHeight, float interval) {
        this.ai = ai;
        GameStateFromLevelCreator gameStateCreator = new GameStateFromLevelCreator();
        this.gameState = gameStateCreator.createGameState(trees, bots, player, levelWidth, levelHeight);
        this.interval = interval;
    }

    @Override
    public List<Command> generateCommands(float deltaTime) {
        progress += deltaTime;
        List<Command> commands = new ArrayList<>();
        if (progress > interval) {
            progress = 0f;
            List<Recommendation> recommendations = ai.recommend(gameState);
            for (Recommendation recommendation : recommendations) {
                commands.add(commandCreator.createCommand(recommendation));
            }
        }
        return commands;
    }
}
