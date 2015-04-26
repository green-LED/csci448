package edu.mines.mwichman.GameWorld;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

import edu.mines.mwichman.GameObjects.Bullet;
import edu.mines.mwichman.GameObjects.Enemy;
import edu.mines.mwichman.GameObjects.Ship;
import edu.mines.mwichman.Helpers.BulletHandler;
import edu.mines.mwichman.Helpers.EnemyHandler;


/**
 * Created by Matthew on 4/21/2015.
 * Contains and manages Bullet and Enemy Handlers as well as the player's ship.
 *  Also does collision detection and handling.
 */
public class GameWorld {

    private Ship ship;
    private BulletHandler bulletHandler;
    private EnemyHandler enemyHandler;

    private float runTime;
    private float gameHeight;

    public GameWorld(float height) {
        gameHeight = height;
        ship = new Ship(68, gameHeight - 40, this);
        bulletHandler = new BulletHandler(this);
        enemyHandler = new EnemyHandler(this);

    }

    public void update(float delta) {
        //Gdx.app.log("GameWorld", "update");
        runTime += delta;

        ship.update(delta);
        bulletHandler.update(delta);
        enemyHandler.update(delta);

        collisions();
    }

    // check for collisions between relevent objects and deal with them
    private void collisions() {
        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        ArrayList<Enemy> enemiesToRemove = new ArrayList<Enemy>();
        for(Bullet bullet: bulletHandler.getBullets()) {
            for (Enemy enemy: enemyHandler.getEnemies()) {
                if ( bullet.collides(enemy.getPos(), enemy.getRad()) ) {
                    explode(enemy);

                    bulletsToRemove.add(bullet);
                    enemiesToRemove.add(enemy);
                }

            }
        }
        bulletHandler.getBullets().removeAll(bulletsToRemove);
        enemyHandler.getEnemies().removeAll(enemiesToRemove);
    }

    // TBI: creates a temporary "explosion"
    private void explode(Enemy e) {

    }

    public Ship getShip() {return ship;}
    public BulletHandler getBulletHandler() {return bulletHandler;}
    public EnemyHandler getEnemyHandler() {return enemyHandler;}

    public float getRunTime() {return runTime;}
    public float getGameHeight() {return gameHeight;}
}
