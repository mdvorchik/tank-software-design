package ru.mipt.bit.platformer.actors.action;

import ru.mipt.bit.platformer.actors.Playable;
import ru.mipt.bit.platformer.actors.Tank;
import ru.mipt.bit.platformer.gameobjects.CollisionChecker;

import static ru.mipt.bit.platformer.util.GdxGameUtils.incrementedX;

public class MoveRightAction implements ActionFromPlayer {
    private final Playable player;
    private final CollisionChecker collisionChecker;

    public MoveRightAction(Playable player, CollisionChecker collisionChecker) {
        this.player = player;
        this.collisionChecker = collisionChecker;
    }

    @Override
    public void doAction() {
        // check potential player destination for collision with obstacles
        if (player instanceof Tank) {
            if (collisionChecker.checkNotCollision(incrementedX(((Tank) player).getPlayerCoordinates()))) {
                ((Tank) player).setPlayerDestinationCoordinates(incrementedX(((Tank) player).getPlayerCoordinates()));
                ((Tank) player).setPlayerMovementProgress(0f);
            }
            ((Tank) player).setPlayerRotation(0f);
        }
    }
}
