package edu.mines.mwichman.Screens;

/**
 *
 * Created by Matthew on 4/21/2015.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import edu.mines.mwichman.GameWorld.GameRenderer;
import edu.mines.mwichman.GameWorld.GameWorld;
import edu.mines.mwichman.Helpers.InputHandler;

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;

    public GameScreen() {
        Gdx.app.log("GameScreen", "Attached");

        // Game size: the game is full screen, but everything in the game
        //  assumes a 136-unit-long x-axis and a variable y-axis. All
        //  calculations are scaled with the actual dimensions.
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        world = new GameWorld(gameHeight);
        renderer = new GameRenderer(world, gameHeight);

        Gdx.input.setInputProcessor(new InputHandler(world.getShip()));
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }

}