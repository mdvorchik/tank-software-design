package ru.mipt.bit.platformer.generator;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.Tree;
import ru.mipt.bit.platformer.physics.CollisionChecker;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.incrementedY;

public class ObjectsByCoordinatesCreator {
    private final List<GridPoint2> tankCoordinatesList;
    private final List<GridPoint2> treeCoordinatesList;
    private final CollisionChecker collisionChecker;

    private final List<Tank> tanks = new ArrayList<>();
    private final List<Tree> trees = new ArrayList<>();
    private final Tank playerTank;

    public ObjectsByCoordinatesCreator(List<GridPoint2> tankCoordinatesList,
                                       List<GridPoint2> treeCoordinatesList,
                                       CollisionChecker collisionChecker) {
        this.tankCoordinatesList = tankCoordinatesList;
        this.treeCoordinatesList = treeCoordinatesList;
        this.collisionChecker = collisionChecker;
        createTanksWithCollision();
        createTreesWithCollision();
        playerTank = tanks.remove(tanks.size() - 1);
    }

    private void createTanksWithCollision() {
        for (GridPoint2 tankCoordinates : tankCoordinatesList) {
            tanks.add(new Tank(0.4f, collisionChecker, tankCoordinates, incrementedY(tankCoordinates)));
        }
        for (Tank tank : tanks) {
            collisionChecker.addCollidable(tank);
        }
    }

    private void createTreesWithCollision() {
        for (GridPoint2 treeCoordinates : treeCoordinatesList) {
            trees.add(new Tree(treeCoordinates, 0f));
        }
        for (Tree tree : trees) {
            collisionChecker.addCollidable(tree);
        }
    }

    public Tank getPlayerTank() {
        return playerTank;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public List<Tree> getTrees() {
        return trees;
    }
}
