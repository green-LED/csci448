package edu.mines.mwichman.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import edu.mines.mwichman.GameObjects.Bullet;
import edu.mines.mwichman.GameObjects.BulletType;
import edu.mines.mwichman.GameWorld.GameWorld;

/**
 * Created by Matthew on 4/23/2015.
 * Keeps an ArrayList of every enemy object, updating and rendering them appropriately.
 *  Handles generation of bullets when the ship (or an enemy ship) calls for it
 */
public class BulletHandler {

    ArrayList<Bullet> bullets;

    GameWorld world;

    public BulletHandler(GameWorld gw) {
        bullets = new ArrayList<Bullet>();
        world = gw;
    }

    public void update(float delta) {
        ArrayList<Bullet> toRemove = new ArrayList<Bullet>();
        for(Bullet bullet : bullets) {
            bullet.update(delta);
            if(outOfBounds(bullet.getPos())) {toRemove.add(bullet);}
        }
        bullets.removeAll(toRemove);
        // Gdx.app.log("BulletHandler", bullets.size() + " lasers");
    }

    public void render(ShapeRenderer renderer) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for(Bullet bullet : bullets) {
            bullet.render(renderer);
        }
        renderer.end();
    }

    private boolean outOfBounds(Vector2 v) {
        return (v.x < -20) || (v.x > 156) ||
                (v.y < -20) || (v.y > world.getGameHeight() + 20);
    }

    public void fireLaser(float x, float y) {
        bullets.add(new Bullet(x, y, 0, BulletType.LASER));
    }

    public void fireSpread(float x, float y) {
        float ang = (float) Math.PI/9;
        bullets.add(new Bullet(x, y, (float) -Math.PI/2, BulletType.SPREAD));
        bullets.add(new Bullet(x, y, (float) -Math.PI/2 - ang, BulletType.SPREAD));
        bullets.add(new Bullet(x, y, (float) -Math.PI/2 + ang, BulletType.SPREAD));
    }

    public ArrayList<Bullet> getBullets() {return bullets;}

}
