package ru.mipt.bit.platformer.ai.external.adapter;

import org.awesome.ai.state.immovable.Obstacle;
import ru.mipt.bit.platformer.gameobjects.Tree;

/**
 * Adapter
 */
public class ObstacleFromTreeCreator {
    public Obstacle createObstacle(Tree tree) {
        return new Obstacle(tree.getCoordinates().x, tree.getCoordinates().y);
    }
}
