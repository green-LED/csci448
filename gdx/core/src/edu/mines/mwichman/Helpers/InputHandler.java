package edu.mines.mwichman.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import edu.mines.mwichman.GameObjects.Ship;

/**
 *
 * Created by Matthew on 4/22/2015.
 */
public class InputHandler implements InputProcessor{

    private Ship ship;
    private float scale;

    public InputHandler(Ship s) {
        ship = s;
        scale = 136.0f/Gdx.graphics.getWidth();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        ship.move(scale * screenX, scale * screenY);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        ship.stop();

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        ship.move(scale * screenX, scale * screenY);

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
     public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
}
