package ru.mipt.bit.platformer.generator;

import org.junit.Test;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.Tree;
import ru.mipt.bit.platformer.physics.GameEngine;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RandomLevelGeneratorTest {

    @Test
    public void getGameEngine() {
        //given
        RandomLevelGenerator randomLevelGenerator = new RandomLevelGenerator(8, 10, 4);
        //when
        GameEngine gameEngine = randomLevelGenerator.getGameEngine();
        //verify
        assertNotNull(gameEngine);
    }

    @Test
    public void getTrees() {
        //given
        RandomLevelGenerator randomLevelGenerator = new RandomLevelGenerator(8, 10, 4);
        //when
        List<Tree> trees = randomLevelGenerator.getTrees();
        //verify
        assertEquals(4, trees.size());
    }

    @Test
    public void getTank() {
        //given
        RandomLevelGenerator randomLevelGenerator = new RandomLevelGenerator(8, 10, 4);
        //when
        Tank tank = randomLevelGenerator.getTank();
        //verify
        assertNotNull(tank);
    }
}