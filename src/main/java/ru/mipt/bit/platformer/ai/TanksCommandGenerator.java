package ru.mipt.bit.platformer.ai;

import ru.mipt.bit.platformer.commands.Command;

import java.util.List;

public interface TanksCommandGenerator {
    List<Command> generateCommands(float deltaTime);
}
