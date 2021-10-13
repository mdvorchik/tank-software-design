package ru.mipt.bit.platformer.generator;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.InputProcessor;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.Tree;
import ru.mipt.bit.platformer.physics.CollisionChecker;
import ru.mipt.bit.platformer.physics.GameEngine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.mipt.bit.platformer.util.GdxGameUtils.incrementedY;

public class RandomLevelGenerator {
    private final GameEngine gameEngine;
    private final List<Tree> trees = new ArrayList<>();
    private final Tank tank;

    public RandomLevelGenerator(int widthOfMap, int heightOfMap, int treesCount) {
        List<GridPoint2> treeCoordinatesList = new ArrayList<>(generateRandomCoordinates(treesCount, widthOfMap, heightOfMap));
        List<GridPoint2> tankCoordinatesList = new ArrayList<>(generateRandomCoordinates(1, widthOfMap, heightOfMap));

        CollisionChecker collisionChecker = new CollisionChecker(new ArrayList<>());
        tank = new Tank(0.4f, collisionChecker, tankCoordinatesList.get(0), incrementedY((tankCoordinatesList.get(0))));
        collisionChecker.addCollidable(tank);
        InputProcessor inputProcessor = new InputProcessor(tank);

        for (GridPoint2 treeCoordinates : treeCoordinatesList) {
            trees.add(new Tree(treeCoordinates, 0f));
        }
        for (Tree tree : trees) {
            collisionChecker.addCollidable(tree);
        }
        gameEngine = new GameEngine(inputProcessor, tank);
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    private Set<GridPoint2> generateRandomCoordinates(int numberToGenerate, int maxX, int maxY) {
        Set<GridPoint2> randomGridPoint2Set = new HashSet<>();
        for (int i = 0; i < numberToGenerate; i++) {
            randomGridPoint2Set.add(new GridPoint2((int) (Math.random() * (maxX - 1)),
                    (int) (Math.random() * (maxY - 1))));
        }
        return randomGridPoint2Set;
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public Tank getTank() {
        return tank;
    }
}

