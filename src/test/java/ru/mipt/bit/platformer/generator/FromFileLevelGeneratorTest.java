package ru.mipt.bit.platformer.generator;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.Test;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.Tree;
import ru.mipt.bit.platformer.physics.GameEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FromFileLevelGeneratorTest {

    @Test
    public void getGameEngine() {
        //given
        FromFileLevelGenerator fromFileLevelGenerator = new FromFileLevelGenerator("src/test/resources/test_level.txt");
        //when
        GameEngine gameEngine = fromFileLevelGenerator.getGameEngine();
        //verify
        assertNotNull(gameEngine);
    }

    @Test
    public void getTrees() {
        //given
        FromFileLevelGenerator fromFileLevelGenerator = new FromFileLevelGenerator("src/test/resources/test_level.txt");
        List<GridPoint2> expectedCoordinates = new ArrayList<>();
        expectedCoordinates.add(new GridPoint2(1, 2));
        expectedCoordinates.add(new GridPoint2(2, 0));
        expectedCoordinates.add(new GridPoint2(0, 0));
        //when
        List<Tree> trees = fromFileLevelGenerator.getTrees();
        //verify
        assertEquals(expectedCoordinates, trees.stream().map(Tree::getCoordinates).collect(Collectors.toList()));
    }

    @Test
    public void getTank() {
        //given
        FromFileLevelGenerator fromFileLevelGenerator = new FromFileLevelGenerator("src/test/resources/test_level.txt");
        GridPoint2 expectedCoordinate = new GridPoint2(1, 1);
        //when
        Tank tank = fromFileLevelGenerator.getTank();
        //verify
        assertEquals(expectedCoordinate, tank.getPlayerCoordinates());
    }
}