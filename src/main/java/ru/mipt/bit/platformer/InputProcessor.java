package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.gameobjects.Tank;

import static com.badlogic.gdx.Input.Keys.*;

public class InputProcessor {

    private final Tank tank;

    public InputProcessor(Tank tank) {
        this.tank = tank;
    }

    public void processInputs() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            if (tank.canMoveInThisTick()) {
                tank.moveUp();
            }
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            if (tank.canMoveInThisTick()) {
                tank.moveLeft();
            }
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            if (tank.canMoveInThisTick()) {
                tank.moveDown();
            }
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            if (tank.canMoveInThisTick()) {
                tank.moveRight();
            }
        }
    }
}
