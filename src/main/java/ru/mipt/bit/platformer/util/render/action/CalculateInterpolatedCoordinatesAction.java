package ru.mipt.bit.platformer.util.render.action;

import ru.mipt.bit.platformer.actors.Tank;
import ru.mipt.bit.platformer.util.render.Render;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class CalculateInterpolatedCoordinatesAction implements RenderAction {

    private final Tank player;

    public CalculateInterpolatedCoordinatesAction(Tank player) {
        this.player = player;
    }

    @Override
    public void doAction(Render render) {
        // calculate interpolated player screen coordinates
        render.getTileMovement().moveRectangleBetweenTileCenters(player.getPlayerRectangle(),
                player.getPlayerCoordinates(), player.getPlayerDestinationCoordinates(),
                player.getPlayerMovementProgress());
        player.setPlayerMovementProgress(continueProgress(player.getPlayerMovementProgress(),
                render.getDeltaTime(), player.getMOVEMENT_SPEED()));
        if (isEqual(player.getPlayerMovementProgress(), 1f)) {
            // record that the player has reached his/her destination
            player.setPlayerCoordinates(player.getPlayerDestinationCoordinates());
        }
    }
}
