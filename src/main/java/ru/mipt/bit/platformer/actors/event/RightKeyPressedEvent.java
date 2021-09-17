package ru.mipt.bit.platformer.actors.event;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.actors.Tank;
import ru.mipt.bit.platformer.actors.action.ActionFromPlayer;
import ru.mipt.bit.platformer.actors.action.MoveRightAction;
import ru.mipt.bit.platformer.gameobjects.CollisionChecker;

import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.RIGHT;
import static com.badlogic.gdx.math.MathUtils.isEqual;

public class RightKeyPressedEvent implements EventFromPlayer {
    final Tank player;
    final CollisionChecker collisionChecker;
    final float playerMovementProgressLimit;

    public RightKeyPressedEvent(Tank player, CollisionChecker collisionChecker, float playerMovementProgressLimit) {
        this.player = player;
        this.collisionChecker = collisionChecker;
        this.playerMovementProgressLimit = playerMovementProgressLimit;
    }

    @Override
    public ActionFromPlayer makeAction() {
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            if (isEqual(player.getPlayerMovementProgress(), playerMovementProgressLimit)) {
                return new MoveRightAction(player, collisionChecker);
            }
        }
        return null;
    }
}
