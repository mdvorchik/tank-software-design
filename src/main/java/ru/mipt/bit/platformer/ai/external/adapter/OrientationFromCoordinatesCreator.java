package ru.mipt.bit.platformer.ai.external.adapter;

import com.badlogic.gdx.math.GridPoint2;
import org.awesome.ai.state.movable.Orientation;

/**
 * Adapter
 */
public class OrientationFromCoordinatesCreator {
    public Orientation createOrientation(GridPoint2 src, GridPoint2 dest) {
        if (dest.x - src.x > 0) {
            return Orientation.E;
        } else if (dest.x - src.x < 0) {
            return Orientation.W;
        } else if (dest.y - src.y > 0) {
            return Orientation.N;
        } else {
            return Orientation.S;
        }
    }
}
