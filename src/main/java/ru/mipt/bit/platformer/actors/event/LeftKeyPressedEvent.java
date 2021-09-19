package ru.mipt.bit.platformer.actors.event;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.actors.Tank;
import ru.mipt.bit.platformer.actors.action.ActionFromPlayer;
import ru.mipt.bit.platformer.actors.action.MoveLeftAction;
import ru.mipt.bit.platformer.gameobjects.CollisionChecker;

import static com.badlogic.gdx.Input.Keys.A;
import static com.badlogic.gdx.Input.Keys.LEFT;

public class LeftKeyPressedEvent implements EventFromPlayer {
    final Tank player;
    final CollisionChecker collisionChecker;
    final float playerMovementProgressLimit;

    public LeftKeyPressedEvent(Tank player, CollisionChecker collisionChecker, float playerMovementProgressLimit) {
        this.player = player;
        this.collisionChecker = collisionChecker;
        this.playerMovementProgressLimit = playerMovementProgressLimit;
    }

    @Override
    public ActionFromPlayer makeAction() {
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            if (player.checkPlayerCanMove(playerMovementProgressLimit)) {
                return new MoveLeftAction(player, collisionChecker);
            }
        }
        return null;
    }
}
