package ru.mipt.bit.platformer.util.render.action;

import ru.mipt.bit.platformer.actors.Playable;
import ru.mipt.bit.platformer.actors.Tank;
import ru.mipt.bit.platformer.util.render.RenderImpl;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class CalculateInterpolatedCoordinatesAction implements RenderAction {

    private final Playable player;

    public CalculateInterpolatedCoordinatesAction(Playable player) {
        this.player = player;
    }

    @Override
    public void doAction(Object render) {
        // calculate interpolated player screen coordinates
        if (render instanceof RenderImpl) {
            if (player instanceof Tank) {
                ((RenderImpl) render).getTileMovement().moveRectangleBetweenTileCenters(((Tank) player).getPlayerRectangle(),
                        ((Tank) player).getPlayerCoordinates(), ((Tank) player).getPlayerDestinationCoordinates(),
                        ((Tank) player).getPlayerMovementProgress());
                ((Tank) player).setPlayerMovementProgress(continueProgress(((Tank) player).getPlayerMovementProgress(),
                        ((RenderImpl) render).getDeltaTime() , ((Tank) player).getMOVEMENT_SPEED()));
                if (isEqual(((Tank) player).getPlayerMovementProgress(), 1f)) {
                    // record that the player has reached his/her destination
                    ((Tank) player).setPlayerCoordinates(((Tank) player).getPlayerDestinationCoordinates());
                }
            }
        }
    }
}
