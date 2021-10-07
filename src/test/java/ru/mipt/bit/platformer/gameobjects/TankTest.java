package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.Test;
import ru.mipt.bit.platformer.physics.CollisionChecker;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TankTest {

    @Test
    public void tankCanRotateAndMoveUpWhenNoObstacleOnTop() {
        //given
        CollisionChecker collisionChecker = new CollisionChecker(new ArrayList<>());
        Tank tank = new Tank(0.4f, collisionChecker, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionChecker.addCollidable(tank);
        //when
        tank.moveUp();
        //verify
        assertEquals(new GridPoint2(1, 1), tank.getPlayerDestinationCoordinates());
        assertEquals(90f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankCanRotateButCanNotMoveUpWhenObstacleOnTop() {
        //given
        CollisionChecker collisionChecker = new CollisionChecker(new ArrayList<>());
        Tree tree = new Tree(new GridPoint2(1, 1), 0f);
        Tank tank = new Tank(0.4f, collisionChecker, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionChecker.addCollidable(tree);
        collisionChecker.addCollidable(tank);
        //when
        tank.moveUp();
        //verify
        assertEquals(new GridPoint2(1, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(90f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankCanRotateAndMoveRightWhenNoObstacleOnRight() {
        //given
        CollisionChecker collisionChecker = new CollisionChecker(new ArrayList<>());
        Tank tank = new Tank(0.4f, collisionChecker, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionChecker.addCollidable(tank);
        //when
        tank.moveRight();
        //verify
        assertEquals(new GridPoint2(2, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(0f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankCanRotateButCanNotMoveRightWhenObstacleOnRight() {
        //given
        CollisionChecker collisionChecker = new CollisionChecker(new ArrayList<>());
        Tree tree = new Tree(new GridPoint2(2, 0), 0f);
        Tank tank = new Tank(0.4f, collisionChecker, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionChecker.addCollidable(tree);
        collisionChecker.addCollidable(tank);
        //when
        tank.moveRight();
        //verify
        assertEquals(new GridPoint2(1, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(0f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankCanRotateAndMoveDownWhenNoObstacleBelow() {
        //given
        CollisionChecker collisionChecker = new CollisionChecker(new ArrayList<>());
        Tank tank = new Tank(0.4f, collisionChecker, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionChecker.addCollidable(tank);
        //when
        tank.moveDown();
        //verify
        assertEquals(new GridPoint2(1, -1), tank.getPlayerDestinationCoordinates());
        assertEquals(-90f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankCanRotateButCanNotMoveDownWhenObstacleBelow() {
        //given
        CollisionChecker collisionChecker = new CollisionChecker(new ArrayList<>());
        Tree tree = new Tree(new GridPoint2(1, -1), 0f);
        Tank tank = new Tank(0.4f, collisionChecker, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionChecker.addCollidable(tree);
        collisionChecker.addCollidable(tank);
        //when
        tank.moveDown();
        //verify
        assertEquals(new GridPoint2(1, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(-90f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankCanRotateAndMoveLeftWhenNoObstacleOnLeft() {
        //given
        CollisionChecker collisionChecker = new CollisionChecker(new ArrayList<>());
        Tank tank = new Tank(0.4f, collisionChecker, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionChecker.addCollidable(tank);
        //when
        tank.moveLeft();
        //verify
        assertEquals(new GridPoint2(0, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(-180f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankCanRotateButCanNotMoveLeftWhenObstacleOnLeft() {
        //given
        CollisionChecker collisionChecker = new CollisionChecker(new ArrayList<>());
        Tree tree = new Tree(new GridPoint2(0, 0), 0f);
        Tank tank = new Tank(0.4f, collisionChecker, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionChecker.addCollidable(tree);
        collisionChecker.addCollidable(tank);
        //when
        tank.moveLeft();
        //verify
        assertEquals(new GridPoint2(1, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(-180f, tank.getPlayerRotation(), 0.1f);
    }
}