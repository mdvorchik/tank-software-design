package ru.mipt.bit.platformer.generator.impl;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.event.EventType;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.generator.Level;
import ru.mipt.bit.platformer.generator.LevelGenerator;
import ru.mipt.bit.platformer.generator.ObjectsByCoordinatesCreator;
import ru.mipt.bit.platformer.physics.CollisionChecker;

import java.io.FileReader;
import java.util.*;

/**
 * Use-case
 */
public class FromFileLevelGenerator implements LevelGenerator {
    private final String fileName;
    private Level level;

    public FromFileLevelGenerator(String fileName) {
        this.fileName = fileName;
    }

    private Set<GridPoint2> readCoordinatesOf(String fileName) {
        Set<GridPoint2> levelBorders = new HashSet<>();
        List<List<String>> signMultiArray = new ArrayList<>();
        fillSignMultiArrayFromFile(fileName, signMultiArray);
        int height = signMultiArray.size();
        int width = signMultiArray.get(0).size();
        for (int i = 0; i < height; i++) {
            levelBorders.add(new GridPoint2(-1, i));
        }
        for (int i = 0; i < height; i++) {
            levelBorders.add(new GridPoint2(width, i));
        }
        for (int j = 0; j < width; j++) {
            levelBorders.add(new GridPoint2(j, -1));
        }
        for (int j = 0; j < width; j++) {
            levelBorders.add(new GridPoint2(j, height));
        }
        return levelBorders;
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

    @Override
    public Level getLevel() {
        if (level == null) {
            List<GridPoint2> treeCoordinatesList = new ArrayList<>(readCoordinatesOf(fileName, "T"));
            List<GridPoint2> tankCoordinatesList = new ArrayList<>(readCoordinatesOf(fileName, "X"));
            List<GridPoint2> levelBordersList = new ArrayList<>(readCoordinatesOf(fileName));
            CollisionChecker collisionChecker = new CollisionChecker(new ArrayList<>());


            List<EventType> eventTypes = new ArrayList<>();
            eventTypes.add(EventType.ADD_BULLET);
            eventTypes.add(EventType.REMOVE_BULLET);
            eventTypes.add(EventType.REMOVE_TANK);
            level = new Level(eventTypes);
            ObjectsByCoordinatesCreator creator = new ObjectsByCoordinatesCreator(level, tankCoordinatesList,
                    treeCoordinatesList, levelBordersList, collisionChecker);
            level.addPlayerTank(creator.getPlayerTank());
            level.addAllTrees(creator.getTrees());
            level.addAllTanks(creator.getTanks());
        }
        return level;
    }
}
