package edu.mines.mwichman.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import edu.mines.mwichman.GameObjects.Ship;
import edu.mines.mwichman.Helpers.BulletHandler;
import edu.mines.mwichman.Helpers.EnemyHandler;

/**
 * Created by Matthew on 4/21/2015.
 *
 */
public class GameRenderer {

    private OrthographicCamera cam;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;

    private float gameHeight;

    // references
    private GameWorld world;
    private Ship ship;
    private BulletHandler bulletHandler;
    private EnemyHandler enemyHandler;

    public GameRenderer(GameWorld w, float height) {
        world = w;
        initGameObjects();

        gameHeight = height;
        cam = new OrthographicCamera();
        cam.setToOrtho(true,136,gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    private void initGameObjects() {
        ship = world.getShip();
        bulletHandler = world.getBulletHandler();
        enemyHandler = world.getEnemyHandler();
    }

    public void render() {
        //Gdx.app.log("GameRenderer", "render");

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(5 / 255.0f, 5 / 255.0f, 5 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, gameHeight);
        shapeRenderer.end();

        bulletHandler.render(shapeRenderer);

        ship.render(batcher);
        enemyHandler.render(batcher);
    }

}
