package ru.mipt.bit.platformer.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.gameobjects.GDXDrawable;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class Tank implements GDXDrawable {

    private final TextureRegion playerGraphics;
    private final Rectangle playerRectangle;
    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    private GridPoint2 playerCoordinates = new GridPoint2(1, 0);
    // which tile the player want to go next
    private GridPoint2 playerDestinationCoordinates = new GridPoint2(1, 1);

    private float playerMovementProgress = 1f;
    private float playerRotation;

    public Tank(Texture blueTankTexture) {
        this.playerGraphics = new TextureRegion(blueTankTexture);
        this.playerRectangle = createBoundingRectangle(playerGraphics);
    }

    public float getPlayerMovementProgress() {
        return playerMovementProgress;
    }

    public void setPlayerMovementProgress(float playerMovementProgress) {
        this.playerMovementProgress = playerMovementProgress;
    }

    public float getPlayerRotation() {
        return playerRotation;
    }

    public GridPoint2 getPlayerCoordinates() {
        return playerCoordinates;
    }

    public void setPlayerCoordinates(GridPoint2 playerCoordinates) {
        this.playerCoordinates = playerCoordinates;
    }

    public GridPoint2 getPlayerDestinationCoordinates() {
        return playerDestinationCoordinates;
    }

    public void setPlayerDestinationCoordinates(GridPoint2 playerDestinationCoordinates) {
        this.playerDestinationCoordinates = playerDestinationCoordinates;
    }

    public void setPlayerRotation(float playerRotation) {
        this.playerRotation = playerRotation;
    }

    public Rectangle getPlayerRectangle() {
        return playerRectangle;
    }

    public float getMOVEMENT_SPEED() {
        return 0.4f;
    }

    @Override
    public TextureRegion getGraphics() {
        return playerGraphics;
    }

    @Override
    public Rectangle getRectangle() {
        return getPlayerRectangle();
    }

    @Override
    public float getRotation() {
        return getPlayerRotation();
    }
}
