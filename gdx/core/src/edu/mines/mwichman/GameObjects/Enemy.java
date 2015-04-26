package edu.mines.mwichman.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;

import edu.mines.mwichman.GameWorld.GameWorld;
import edu.mines.mwichman.Helpers.AssetLoader;

/**
 * Created by Matthew on 4/25/2015.
 */

//Will have subclasses for varieties of enemies

public class Enemy {
    // circular hitbox
    private Vector2 pos;
    private float vel;
    private float rad;

    public Enemy(float x, float y, float v) {
        pos = new Vector2(x,y);
        vel = v;

        rad = 6;
    }

    public void update(float delta) {
        // travels straight down
        pos.add(0, vel * delta);

    }

    public void render(SpriteBatch batcher) {
        // batcher.begin() and .end() in EnemyHandler
        batcher.draw(AssetLoader.enemy, pos.x - 6, pos.y - 7, 12, 16);
    }

    public Vector2 getPos() {return pos;}
    public float getRad() {return rad;}
}
