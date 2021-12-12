package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.commands.impl.ChangeHealthBarDrawingModCommand;
import ru.mipt.bit.platformer.commands.impl.MoveCommand;
import ru.mipt.bit.platformer.commands.impl.ShootCommand;
import ru.mipt.bit.platformer.commands.impl.StayCommand;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.graphics.ui.UISettings;

import static com.badlogic.gdx.Input.Keys.*;

public class InputProcessor {

    private final Tank tank;
    private final UISettings uiSettings;

    public InputProcessor(Tank tank, UISettings uiSettings) {
        this.tank = tank;
        this.uiSettings = uiSettings;
    }

    public Command processInputs() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            return new MoveCommand(tank, Direction.UP);
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            return new MoveCommand(tank, Direction.LEFT);
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return new MoveCommand(tank, Direction.DOWN);
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return new MoveCommand(tank, Direction.RIGHT);
        }
        if (Gdx.input.isKeyPressed(SPACE)) {
            return new ShootCommand(tank);
        }
        if (Gdx.input.isKeyJustPressed(L)) {
            return new ChangeHealthBarDrawingModCommand(uiSettings);
        }
        return new StayCommand(tank);
    }
}
