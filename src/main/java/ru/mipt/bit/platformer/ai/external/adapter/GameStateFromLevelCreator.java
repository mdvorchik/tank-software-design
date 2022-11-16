package ru.mipt.bit.platformer.ai.external.adapter;

import org.awesome.ai.state.GameState;
import org.awesome.ai.state.immovable.Obstacle;
import org.awesome.ai.state.movable.Bot;
import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter
 */
public class GameStateFromLevelCreator {
    private final BotFromTankCreator botCreator = new BotFromTankCreator();
    private final ObstacleFromTreeCreator obstacleCreator = new ObstacleFromTreeCreator();
    private final PlayerFromTankCreator playerCreator = new PlayerFromTankCreator();
    private final OrientationFromCoordinatesCreator orientationCreator = new OrientationFromCoordinatesCreator();

    public GameState createGameState(List<Tree> trees, List<Tank> bots, Tank player, int levelWidth, int levelHeight) {
        GameState.GameStateBuilder gameStateBuilder = new GameState.GameStateBuilder();
        return gameStateBuilder
                .obstacles(getObstacles(trees))
                .bots(getBots(bots))
                .player(getPlayer(player))
                .levelWidth(levelWidth)
                .levelHeight(levelHeight)
                .build();
    }

    private Player getPlayer(Tank player) {
        return playerCreator.createPlayer(player, orientationCreator);
    }

    private List<Bot> getBots(List<Tank> bots) {
        List<Bot> obstacles = new ArrayList<>();
        for (Tank bot : bots) {
            obstacles.add(botCreator.createBot(bot, orientationCreator));
        }
        return obstacles;
    }

    private List<Obstacle> getObstacles(List<Tree> trees) {
        List<Obstacle> obstacles = new ArrayList<>();
        for (Tree tree : trees) {
            obstacles.add(obstacleCreator.createObstacle(tree));
        }
        return obstacles;
    }
}
