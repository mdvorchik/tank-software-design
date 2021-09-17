package ru.mipt.bit.platformer.actors.event;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.actors.Playable;
import ru.mipt.bit.platformer.actors.Tank;
import ru.mipt.bit.platformer.actors.action.ActionFromPlayer;
import ru.mipt.bit.platformer.actors.action.MoveUpAction;
import ru.mipt.bit.platformer.gameobjects.CollisionChecker;

import static com.badlogic.gdx.Input.Keys.UP;
import static com.badlogic.gdx.Input.Keys.W;
import static com.badlogic.gdx.math.MathUtils.isEqual;

public class UpKeyPressedEvent implements EventFromPlayer {
    final Playable player;
    final CollisionChecker collisionChecker;
    final float playerMovementProgressLimit;

    public UpKeyPressedEvent(Playable player, CollisionChecker collisionChecker, float playerMovementProgressLimit) {
        this.player = player;
        this.collisionChecker = collisionChecker;
        this.playerMovementProgressLimit = playerMovementProgressLimit;
    }

    @Override
    public ActionFromPlayer makeAction() {
        if (player instanceof Tank) {
            if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
                if (isEqual(((Tank) player).getPlayerMovementProgress(), playerMovementProgressLimit)) {
                    return new MoveUpAction(player, collisionChecker);
                }
            }
        }
        return null;
    }
}
