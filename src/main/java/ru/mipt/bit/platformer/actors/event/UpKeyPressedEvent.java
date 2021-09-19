package ru.mipt.bit.platformer.actors.event;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.actors.Tank;
import ru.mipt.bit.platformer.actors.action.ActionFromPlayer;
import ru.mipt.bit.platformer.actors.action.MoveUpAction;
import ru.mipt.bit.platformer.gameobjects.CollisionChecker;

import static com.badlogic.gdx.Input.Keys.UP;
import static com.badlogic.gdx.Input.Keys.W;

public class UpKeyPressedEvent implements EventFromPlayer {
    final Tank player;
    final CollisionChecker collisionChecker;
    final float playerMovementProgressLimit;

    public UpKeyPressedEvent(Tank player, CollisionChecker collisionChecker, float playerMovementProgressLimit) {
        this.player = player;
        this.collisionChecker = collisionChecker;
        this.playerMovementProgressLimit = playerMovementProgressLimit;
    }

    @Override
    public ActionFromPlayer makeAction() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            if (player.checkPlayerCanMove(playerMovementProgressLimit)) {
                return new MoveUpAction(player, collisionChecker);
            }
        }
        return null;
    }
}
