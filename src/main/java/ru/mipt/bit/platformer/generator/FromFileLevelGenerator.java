package ru.mipt.bit.platformer.generator;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.InputProcessor;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.Tree;
import ru.mipt.bit.platformer.physics.CollisionChecker;
import ru.mipt.bit.platformer.physics.GameEngine;

import java.io.FileReader;
import java.util.*;

import static ru.mipt.bit.platformer.util.GdxGameUtils.incrementedY;

public class FromFileLevelGenerator implements LevelGenerator {
    private final GameEngine gameEngine;
    private final List<Tree> trees = new ArrayList<>();
    private final Tank tank;

    public FromFileLevelGenerator(String fileName) {
        List<GridPoint2> treeCoordinatesList = new ArrayList<>(readCoordinatesOf(fileName, "T"));
        List<GridPoint2> tankCoordinatesList = new ArrayList<>(readCoordinatesOf(fileName, "X"));

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

    private Set<GridPoint2> readCoordinatesOf(String fileName, String sign) {
        List<List<String>> signMultiArray = new ArrayList<>();
        fillSignMultiArrayFromFile(fileName, signMultiArray);
        Collections.reverse(signMultiArray);
        Set<GridPoint2> gridPoint2Set = new HashSet<>();
        for (int i = 0; i < signMultiArray.size(); i++) {
            for (int j = 0; j < signMultiArray.get(i).size(); j++) {
                if (signMultiArray.get(i).get(j).equals(sign)) {
                    gridPoint2Set.add(new GridPoint2(j, i));
                }
            }
        }
        return gridPoint2Set;
    }

    private void fillSignMultiArrayFromFile(String fileName, List<List<String>> signMultiArray) {
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(new FileReader(fileName));
        } catch (Throwable ex) {
            System.out.println(ex.getMessage());
        }
        if (fileIn != null) {
            while (fileIn.hasNextLine()) {
                signMultiArray.add(Arrays.asList(fileIn.next().split("")));
            }
        }
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public Tank getTank() {
        return tank;
    }
}
