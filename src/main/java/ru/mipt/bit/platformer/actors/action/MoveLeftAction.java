package ru.mipt.bit.platformer.actors.action;

import ru.mipt.bit.platformer.actors.Playable;
import ru.mipt.bit.platformer.actors.Tank;
import ru.mipt.bit.platformer.gameobjects.CollisionChecker;

import static ru.mipt.bit.platformer.util.GdxGameUtils.decrementedX;

public class MoveLeftAction implements ActionFromPlayer {
    private final Playable player;
    private final CollisionChecker collisionChecker;

    public MoveLeftAction(Playable player, CollisionChecker collisionChecker) {
        this.player = player;
        this.collisionChecker = collisionChecker;
    }

    @Override
    public void doAction() {
        // check potential player destination for collision with obstacles
        if (player instanceof Tank) {
            if (collisionChecker.checkNotCollision(decrementedX(((Tank) player).getPlayerCoordinates()))) {
                ((Tank) player).setPlayerDestinationCoordinates(decrementedX(((Tank) player).getPlayerCoordinates()));
                ((Tank) player).setPlayerMovementProgress(0f);
            }
            ((Tank) player).setPlayerRotation(-180f);
        }
    }
}
