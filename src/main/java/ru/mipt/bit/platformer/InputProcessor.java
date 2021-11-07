package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.ai.TankCommand;
import ru.mipt.bit.platformer.ai.commands.*;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gameobjects.Tank;

import static com.badlogic.gdx.Input.Keys.*;

public class InputProcessor {

    private final Tank tank;

    public InputProcessor(Tank tank) {
        this.tank = tank;
    }

    public TankCommand processInputs() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            return new TankMoveCommand(tank, Direction.UP);
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            return new TankMoveCommand(tank, Direction.LEFT);
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return new TankMoveCommand(tank, Direction.DOWN);
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return new TankMoveCommand(tank, Direction.RIGHT);
        }
        return new TankStayCommand(tank);
    }
}
