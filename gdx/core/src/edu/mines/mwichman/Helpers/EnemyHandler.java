package edu.mines.mwichman.Helpers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

import edu.mines.mwichman.GameObjects.Enemy;
import edu.mines.mwichman.GameWorld.GameWorld;

/**
 * Created by Matthew on 4/25/2015.
 * Keeps an ArrayList of every enemy object, updating and rendering them appropriately.
 *  Responsible for the when, where, and how of the generation of new enemies
 */
public class EnemyHandler {

    private ArrayList<Enemy> enemies;
    private float timer;
    private float spawnTime;

    Random rand;

    GameWorld world;

    public EnemyHandler(GameWorld gw) {
        enemies = new ArrayList<Enemy>();
        world = gw;

        timer = 2;
        spawnTime = 6;

        rand = new Random();
    }

    public void update(float delta) {
        // as the game goes on, enemies spawn and move more quickly
        float scale = (float) Math.sqrt(world.getRunTime()/4+1);

        timer -= delta;
        if (timer <= 0) {
            addEnemy(scale);
            timer = (3 + rand.nextFloat()*2)/scale;
        }

        ArrayList<Enemy> toRemove = new ArrayList<Enemy>();
        for(Enemy enemy : enemies) {
            enemy.update(delta);
            if(outOfBounds(enemy.getPos())) {toRemove.add(enemy);}
        }
        enemies.removeAll(toRemove);
    }

    public void render(SpriteBatch batcher) {

        batcher.begin();
        batcher.enableBlending();
        for (Enemy enemy: enemies) {
            enemy.render(batcher);
        }
        batcher.end();

    }


    public void addEnemy(float s) {
        enemies.add(
                new Enemy(
                        136*rand.nextFloat(), -15,
                        (10+20*rand.nextFloat())*s
                )
        );
    }

    private boolean outOfBounds(Vector2 v) {
        return (v.x < -20) || (v.x > 156) ||
                (v.y < -20) || (v.y > world.getGameHeight() + 20);
    }

    public ArrayList<Enemy> getEnemies() {return enemies;}

}
