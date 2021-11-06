package ru.mipt.bit.platformer.ai.commands;

import org.junit.Test;
import org.mockito.Mockito;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class TankCommandsTest {

    @Test
    public void executeTankMoveUpCommand() {
        //given
        Tank mockTank = Mockito.mock(Tank.class);
        TankMoveUpCommand command = new TankMoveUpCommand(mockTank);
        //when
        command.execute();
        //verify
        Mockito.verify(mockTank, Mockito.times(1))
                .moveUp();
    }

    @Test
    public void executeTankMoveRightCommand() {
        //given
        Tank mockTank = Mockito.mock(Tank.class);
        TankMoveRightCommand command = new TankMoveRightCommand(mockTank);
        //when
        command.execute();
        //verify
        Mockito.verify(mockTank, Mockito.times(1))
                .moveRight();
    }

    @Test
    public void executeTankMoveDownCommand() {
        //given
        Tank mockTank = Mockito.mock(Tank.class);
        TankMoveDownCommand command = new TankMoveDownCommand(mockTank);
        //when
        command.execute();
        //verify
        Mockito.verify(mockTank, Mockito.times(1))
                .moveDown();
    }

    @Test
    public void executeTankMoveLeftCommand() {
        //given
        Tank mockTank = Mockito.mock(Tank.class);
        TankMoveLeftCommand command = new TankMoveLeftCommand(mockTank);
        //when
        command.execute();
        //verify
        Mockito.verify(mockTank, Mockito.times(1))
                .moveLeft();
    }
}