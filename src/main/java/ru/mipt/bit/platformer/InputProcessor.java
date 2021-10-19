package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.ai.TankCommand;
import ru.mipt.bit.platformer.ai.commands.*;
import ru.mipt.bit.platformer.gameobjects.Tank;

import static com.badlogic.gdx.Input.Keys.*;

public class InputProcessor {

    private final Tank tank;

    public InputProcessor(Tank tank) {
        this.tank = tank;
    }

    public TankCommand processInputs() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            return new TankMoveUpCommand(tank);
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            return new TankMoveLeftCommand(tank);
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return new TankMoveDownCommand(tank);
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return new TankMoveRightCommand(tank);
        }
        return new TankStayCommand(tank);
    }
}
