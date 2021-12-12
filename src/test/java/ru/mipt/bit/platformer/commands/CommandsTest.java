package ru.mipt.bit.platformer.commands;

import org.junit.Test;
import org.mockito.Mockito;
import ru.mipt.bit.platformer.commands.impl.MoveCommand;
import ru.mipt.bit.platformer.commands.impl.ShootCommand;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class CommandsTest {

    @Test
    public void executeTankMoveUpCommand() {
        //given
        Tank mockTank = Mockito.mock(Tank.class);
        MoveCommand command = new MoveCommand(mockTank, Direction.UP);
        //when
        command.execute();
        //verify
        Mockito.verify(mockTank, Mockito.times(1))
                .move(Direction.UP);
    }

    @Test
    public void executeTankMoveRightCommand() {
        //given
        Tank mockTank = Mockito.mock(Tank.class);
        MoveCommand command = new MoveCommand(mockTank, Direction.RIGHT);
        //when
        command.execute();
        //verify
        Mockito.verify(mockTank, Mockito.times(1))
                .move(Direction.RIGHT);
    }

    @Test
    public void executeTankMoveDownCommand() {
        //given
        Tank mockTank = Mockito.mock(Tank.class);
        MoveCommand command = new MoveCommand(mockTank, Direction.DOWN);
        //when
        command.execute();
        //verify
        Mockito.verify(mockTank, Mockito.times(1))
                .move(Direction.DOWN);
    }

    @Test
    public void executeTankMoveLeftCommand() {
        //given
        Tank mockTank = Mockito.mock(Tank.class);
        MoveCommand command = new MoveCommand(mockTank, Direction.LEFT);
        //when
        command.execute();
        //verify
        Mockito.verify(mockTank, Mockito.times(1))
                .move(Direction.LEFT);
    }

    @Test
    public void executeTankShootCommand() {
        //given
        Tank mockTank = Mockito.mock(Tank.class);
        ShootCommand command = new ShootCommand(mockTank);
        //when
        command.execute();
        //verify
        Mockito.verify(mockTank, Mockito.times(1))
                .shoot();
    }
}