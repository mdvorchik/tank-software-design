package ru.mipt.bit.platformer.ai;

import java.util.List;

public interface TanksCommandGenerator {
    List<TankCommand> generateCommands(float deltaTime);
}
