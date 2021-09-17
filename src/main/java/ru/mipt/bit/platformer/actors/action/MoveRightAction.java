package ru.mipt.bit.platformer.actors.action;

import ru.mipt.bit.platformer.actors.Tank;
import ru.mipt.bit.platformer.gameobjects.CollisionChecker;

import static ru.mipt.bit.platformer.util.GdxGameUtils.incrementedX;

public class MoveRightAction implements ActionFromPlayer {
    private final Tank player;
    private final CollisionChecker collisionChecker;

    public MoveRightAction(Tank player, CollisionChecker collisionChecker) {
        this.player = player;
        this.collisionChecker = collisionChecker;
    }

    @Override
    public void doAction() {
        // check potential player destination for collision with obstacles
        if (collisionChecker.checkNotCollision(incrementedX(player.getPlayerCoordinates()))) {
            player.setPlayerDestinationCoordinates(incrementedX(player.getPlayerCoordinates()));
            player.setPlayerMovementProgress(0f);
        }
        player.setPlayerRotation(0f);
    }
}
