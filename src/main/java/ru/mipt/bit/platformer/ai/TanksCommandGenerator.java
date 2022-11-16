package ru.mipt.bit.platformer.ai;

import java.util.List;

/**
 * Port
 */
public interface TanksCommandGenerator {
    List<TankCommand> generateCommands(float deltaTime);
}
