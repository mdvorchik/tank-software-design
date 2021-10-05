package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.Tree;

import static com.badlogic.gdx.Input.Keys.*;

public class InputProcessor {

    private final Tank tank;
    private final Tree tree;

    public InputProcessor(Tank tank, Tree tree) {
        this.tank = tank;
        this.tree = tree;
    }

    public void processInputs() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            if (tank.canMoveInThisTick()) {
                tank.moveUp(tree.getCoordinates());
            }
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            if (tank.canMoveInThisTick()) {
                tank.moveLeft(tree.getCoordinates());
            }
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            if (tank.canMoveInThisTick()) {
                tank.moveDown(tree.getCoordinates());
            }
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            if (tank.canMoveInThisTick()) {
                tank.moveRight(tree.getCoordinates());
            }
        }
    }
}
