package ru.mipt.bit.platformer.generator;

import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.Tree;
import ru.mipt.bit.platformer.physics.GameEngine;

import java.util.List;

public interface LevelGenerator {
    GameEngine getGameEngine();

    List<Tree> getTrees();

    List<Tank> getTanks();

    Tank getPlayerTank();
}
