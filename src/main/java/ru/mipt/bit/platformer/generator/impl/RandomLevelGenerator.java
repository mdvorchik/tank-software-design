package ru.mipt.bit.platformer.generator.impl;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.event.EventType;
import ru.mipt.bit.platformer.generator.Level;
import ru.mipt.bit.platformer.generator.LevelGenerator;
import ru.mipt.bit.platformer.generator.ObjectsByCoordinatesCreator;
import ru.mipt.bit.platformer.physics.CollisionChecker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RandomLevelGenerator implements LevelGenerator {
    private final int widthOfMap;
    private final int heightOfMap;
    private final int treesCount;
    private final int tankCount;
    private Level level;

    public RandomLevelGenerator(int widthOfMap, int heightOfMap, int treesCount, int tankCount) {
        this.widthOfMap = widthOfMap;
        this.heightOfMap = heightOfMap;
        this.treesCount = treesCount;
        this.tankCount = tankCount;
    }

    private Set<GridPoint2> createBorderCoordinates(int width, int height) {
        Set<GridPoint2> levelBorders = new HashSet<>();
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

    private Set<GridPoint2> generateRandomCoordinates(int numberToGenerate, int maxX, int maxY) {
        Set<GridPoint2> randomGridPoint2Set = new HashSet<>();
        for (int i = 0; i < numberToGenerate; i++) {
            randomGridPoint2Set.add(new GridPoint2((int) (Math.random() * (maxX - 1)),
                    (int) (Math.random() * (maxY - 1))));
        }
        return randomGridPoint2Set;
    }

    @Override
    public Level getLevel() {
        if (level == null) {
            List<EventType> eventTypes = new ArrayList<>();
            eventTypes.add(EventType.ADD_BULLET);
            eventTypes.add(EventType.REMOVE_BULLET);
            eventTypes.add(EventType.ADD_TANK);
            eventTypes.add(EventType.REMOVE_TANK);
            eventTypes.add(EventType.ADD_PLAYER_TANK);
            eventTypes.add(EventType.REMOVE_PLAYER_TANK);
            eventTypes.add(EventType.ADD_TREE);
            eventTypes.add(EventType.REMOVE_TREE);
            level = new Level(eventTypes);
        }
        return level;
    }

    @Override
    public void fillLevel(Level level) {
        List<GridPoint2> treeCoordinatesList = new ArrayList<>(generateRandomCoordinates(treesCount, widthOfMap, heightOfMap));
        List<GridPoint2> tankCoordinatesList = new ArrayList<>(generateRandomCoordinates(tankCount, widthOfMap, heightOfMap));
        List<GridPoint2> levelBorders = new ArrayList<>(createBorderCoordinates(widthOfMap, heightOfMap));
        CollisionChecker collisionChecker = new CollisionChecker(new ArrayList<>());

        ObjectsByCoordinatesCreator creator = new ObjectsByCoordinatesCreator(level, tankCoordinatesList,
                treeCoordinatesList, levelBorders, collisionChecker);

        level.registerPlayerTankCreation(creator.getPlayerTank());
        creator.getTrees().forEach(level::registerTreeCreation);
        creator.getTanks().forEach(level::registerTankCreation);
    }
}

