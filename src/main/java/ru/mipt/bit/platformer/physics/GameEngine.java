package ru.mipt.bit.platformer.physics;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.InputProcessor;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.ai.TanksCommandGenerator;
import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.generator.Level;

public class GameEngine {

    private final InputProcessor inputProcessor;
    private final Level level;
    private final TanksCommandGenerator tanksCommandGenerator;

    public GameEngine(Level level, InputProcessor inputProcessor, TanksCommandGenerator tanksCommandGenerator) {
        this.level = level;
        this.inputProcessor = inputProcessor;
        this.tanksCommandGenerator = tanksCommandGenerator;
    }

    public void doCalculations() {
        processInputs();
        processCommandsFromGenerator(getDeltaTime());
        processPlayerMovementProgress(getDeltaTime());
        processTanksMovementProgress(getDeltaTime());
        processBulletsMovementProgress(getDeltaTime());
    }

    private void processInputs() {
        inputProcessor.processInputs().execute();
    }

    private void processCommandsFromGenerator(float deltaTime) {
        for (Command command : tanksCommandGenerator.generateCommands(deltaTime)) {
            command.execute();
        }
    }

    private void processPlayerMovementProgress(float deltaTime) {
        level.getPlayerTank().processMovementProgress(deltaTime);
    }

    private void processTanksMovementProgress(float deltaTime) {
        for (Tank tank : level.getTanks()) {
            tank.processMovementProgress(deltaTime);
        }
    }

    private void processBulletsMovementProgress(float deltaTime) {
        for (Bullet bullet : level.getBullets()) {
            bullet.processMovementProgress(deltaTime);
        }
    }

    private float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }
}
