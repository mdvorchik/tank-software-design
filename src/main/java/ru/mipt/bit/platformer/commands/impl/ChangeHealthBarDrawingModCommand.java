package ru.mipt.bit.platformer.commands.impl;

import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.graphics.ui.UISettings;

public class ChangeHealthBarDrawingModCommand implements Command {
        private final UISettings uiSettings;

        public ChangeHealthBarDrawingModCommand(UISettings uiSettings) {
            this.uiSettings = uiSettings;
        }

        @Override
        public void execute() {
            uiSettings.changeRenderHealthBarMod();
        }
}
