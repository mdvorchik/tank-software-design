package ru.mipt.bit.platformer.actors.event;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.actors.Playable;
import ru.mipt.bit.platformer.actors.Tank;
import ru.mipt.bit.platformer.actors.action.ActionFromPlayer;
import ru.mipt.bit.platformer.actors.action.MoveDownAction;
import ru.mipt.bit.platformer.gameobjects.CollisionChecker;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.math.MathUtils.isEqual;

public class DownKeyPressedEvent implements EventFromPlayer {
    final Playable player;
    final CollisionChecker collisionChecker;
    final float playerMovementProgressLimit;

    public DownKeyPressedEvent(Playable player, CollisionChecker collisionChecker, float playerMovementProgressLimit) {
        this.player = player;
        this.collisionChecker = collisionChecker;
        this.playerMovementProgressLimit = playerMovementProgressLimit;
    }

    @Override
    public ActionFromPlayer makeAction() {
        if (player instanceof Tank) {
            if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
                if (isEqual(((Tank) player).getPlayerMovementProgress(), playerMovementProgressLimit)) {
                    return new MoveDownAction(player, collisionChecker);
                }
            }
        }
        return null;
    }
}
