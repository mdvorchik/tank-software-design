package ru.mipt.bit.platformer.physics;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.InputProcessor;
import ru.mipt.bit.platformer.ai.TankCommand;
import ru.mipt.bit.platformer.ai.TanksCommandGenerator;
import ru.mipt.bit.platformer.gameobjects.Tank;

import java.util.List;

public class GameEngine {

    private final InputProcessor inputProcessor;
    private final TanksCommandGenerator tanksCommandGenerator;
    private final Tank playerTank;
    private final List<Tank> tanks;

    public GameEngine(Tank playerTank, List<Tank> tanks, TanksCommandGenerator tanksCommandGenerator) {
        this.playerTank = playerTank;
        this.tanks = tanks;
        this.inputProcessor = new InputProcessor(playerTank);
        this.tanksCommandGenerator = tanksCommandGenerator;
    }

    public void doCalculations() {
        processInputs();
        processCommandsFromGenerator(getDeltaTime());
        processPlayerMovementProgress(getDeltaTime());
        processTanksMovementProgress(getDeltaTime());
    }

    private void processInputs() {
        inputProcessor.processInputs().execute();
    }

    private void processCommandsFromGenerator(float deltaTime) {
        for (TankCommand command : tanksCommandGenerator.generateCommands(deltaTime)) {
            command.execute();
        }
    }

    private void processPlayerMovementProgress(float deltaTime) {
        playerTank.processMovementProgress(deltaTime);
    }

    private void processTanksMovementProgress(float deltaTime) {
        for (Tank tank : tanks) {
            tank.processMovementProgress(deltaTime);
        }
    }

    private float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }
}
