package ru.mipt.bit.platformer.physics;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.InputProcessor;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class GameEngine {

    private final InputProcessor inputProcessor;
    private final Tank tank;

    public GameEngine(InputProcessor inputProcessor, Tank tank) {
        this.inputProcessor = inputProcessor;
        this.tank = tank;
    }

    public void doCalculations() {
        processInputs();
        processPlayerMovementProgress(getDeltaTime());
    }

    private void processInputs() {
        inputProcessor.processInputs();
    }

    private void processPlayerMovementProgress(float deltaTime) {
        tank.processMovementProgress(deltaTime);
    }

    private float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }
}
