package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.Input.Keys.*;

public class InputProcessor {

    private final Player player;
    private final Tree tree;

    public InputProcessor(Player player, Tree tree) {
        this.player = player;
        this.tree = tree;
    }

    public void processInputs() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            if (player.canMoveInThisTick()) {
                player.moveUp(tree.getCoordinates());
            }
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            if (player.canMoveInThisTick()) {
                player.moveLeft(tree.getCoordinates());
            }
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            if (player.canMoveInThisTick()) {
                player.moveDown(tree.getCoordinates());
            }
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            if (player.canMoveInThisTick()) {
                player.moveRight(tree.getCoordinates());
            }
        }
    }
}
