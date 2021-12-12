package ru.mipt.bit.platformer.generator;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.Test;
import ru.mipt.bit.platformer.InputProcessor;
import ru.mipt.bit.platformer.ai.internal.TanksCommandGeneratorImpl;
import ru.mipt.bit.platformer.event.EventType;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.Tree;
import ru.mipt.bit.platformer.generator.impl.FromFileLevelGenerator;
import ru.mipt.bit.platformer.graphics.ui.UISettings;
import ru.mipt.bit.platformer.physics.GameEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FromFileLevelGeneratorTest {

    @Test
    public void getGameEngine() {
        //given
        FromFileLevelGenerator fromFileLevelGenerator = new FromFileLevelGenerator("src/test/resources/test_level.txt");
        Level level = fromFileLevelGenerator.getLevel();
        //when
        GameEngine gameEngine = new GameEngine(level,
                new InputProcessor(level.getPlayerTank(), new UISettings(Collections.singletonList(EventType.CHANGE_UI_RENDER_MODE))),
                new TanksCommandGeneratorImpl(level.getTanks(), 2f));
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
        List<Tree> trees = fromFileLevelGenerator.getLevel().getTrees();
        //verify
        assertEquals(expectedCoordinates, trees.stream().map(Tree::getCoordinates).collect(Collectors.toList()));
    }

    @Test
    public void getTank() {
        //given
        FromFileLevelGenerator fromFileLevelGenerator = new FromFileLevelGenerator("src/test/resources/test_level.txt");
        GridPoint2 expectedCoordinate = new GridPoint2(1, 1);
        //when
        Tank tank = fromFileLevelGenerator.getLevel().getPlayerTank();
        //verify
        assertEquals(expectedCoordinate, tank.getTankCoordinates());
    }
}