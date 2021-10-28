package ru.mipt.bit.platformer.ai.external;

import org.awesome.ai.AI;
import org.awesome.ai.Recommendation;
import org.awesome.ai.state.GameState;
import ru.mipt.bit.platformer.ai.TankCommand;
import ru.mipt.bit.platformer.ai.TanksCommandGenerator;
import ru.mipt.bit.platformer.ai.external.adapter.CommandFromRecommendationCreator;
import ru.mipt.bit.platformer.ai.external.adapter.GameStateFromLevelCreator;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.Tree;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class TankExternalCommandGenerator implements TanksCommandGenerator {
    private final AI ai;
    private final GameState gameState;
    private final Queue<TankCommand> commands = new ArrayDeque<>();
    private final CommandFromRecommendationCreator commandCreator = new CommandFromRecommendationCreator();

    TankExternalCommandGenerator(AI ai, List<Tree> trees, List<Tank> bots, Tank player, int levelWidth, int levelHeight) {
        this.ai = ai;
        GameStateFromLevelCreator gameStateCreator = new GameStateFromLevelCreator();
        this.gameState = gameStateCreator.createGameState(trees, bots, player, levelWidth, levelHeight);
    }

    @Override
    public TankCommand generateCommand() {
        if (commands.isEmpty()) {
            generateCommands();
        }
        return commands.poll();
    }

    private void generateCommands() {
        List<Recommendation> recommendations = ai.recommend(gameState);
        for (Recommendation recommendation : recommendations) {
            commands.add(commandCreator.createCommand(recommendation));
        }
    }
}
