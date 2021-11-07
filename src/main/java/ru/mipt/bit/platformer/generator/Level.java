package ru.mipt.bit.platformer.generator;

import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.Tree;

import java.util.List;

public class Level {
    private final Tank playerTank;
    private final List<Tree> trees;
    private final List<Tank> tanks;

    public Level(Tank playerTank, List<Tree> trees, List<Tank> tanks) {
        this.playerTank = playerTank;
        this.trees = trees;
        this.tanks = tanks;
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public Tank getPlayerTank() {
        return playerTank;
    }
}
