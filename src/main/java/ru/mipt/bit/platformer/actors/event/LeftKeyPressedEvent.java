package ru.mipt.bit.platformer.actors.event;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.actors.Playable;
import ru.mipt.bit.platformer.actors.Tank;
import ru.mipt.bit.platformer.actors.action.ActionFromPlayer;
import ru.mipt.bit.platformer.actors.action.MoveLeftAction;
import ru.mipt.bit.platformer.gameobjects.CollisionChecker;

import static com.badlogic.gdx.Input.Keys.A;
import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.math.MathUtils.isEqual;

public class LeftKeyPressedEvent implements EventFromPlayer {
    final Playable player;
    final CollisionChecker collisionChecker;
    final float playerMovementProgressLimit;

    public LeftKeyPressedEvent(Playable player, CollisionChecker collisionChecker, float playerMovementProgressLimit) {
        this.player = player;
        this.collisionChecker = collisionChecker;
        this.playerMovementProgressLimit = playerMovementProgressLimit;
    }

    @Override
    public ActionFromPlayer makeAction() {
        if (player instanceof Tank) {
            if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
                if (isEqual(((Tank) player).getPlayerMovementProgress(), playerMovementProgressLimit)) {
                    return new MoveLeftAction(player, collisionChecker);
                }
            }
        }
        return null;
    }
}
