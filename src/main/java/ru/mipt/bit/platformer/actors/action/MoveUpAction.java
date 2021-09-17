package ru.mipt.bit.platformer.actors.action;

import ru.mipt.bit.platformer.actors.Tank;
import ru.mipt.bit.platformer.gameobjects.CollisionChecker;

import static ru.mipt.bit.platformer.util.GdxGameUtils.incrementedY;

public class MoveUpAction implements ActionFromPlayer {
    private final Tank player;
    private final CollisionChecker collisionChecker;

    public MoveUpAction(Tank player, CollisionChecker collisionChecker) {
        this.player = player;
        this.collisionChecker = collisionChecker;
    }

    @Override
    public void doAction() {
        // check potential player destination for collision with obstacles
        if (collisionChecker.checkNotCollision(incrementedY(player.getPlayerCoordinates()))) {
            player.setPlayerDestinationCoordinates(incrementedY(player.getPlayerCoordinates()));
            player.setPlayerMovementProgress(0f);
        }
        player.setPlayerRotation(90f);
    }
}
