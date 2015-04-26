package edu.mines.mwichman.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import edu.mines.mwichman.GameWorld.GameWorld;
import edu.mines.mwichman.Helpers.AssetLoader;

/**
 * Created by Matthew on 4/22/2015.
 * Handles the player's ship, as well as when to generate new player bullets
 */
public class Ship {
    private Vector2 pos;
    private float vel;
    private float dest;

    // the hitbox will be circular
    private int radius;

    private float heat;
    private float laserCD, spreadCD;
    private BulletType current;

    private GameWorld world;

    public Ship(float x, float y, GameWorld gw) {
        pos = new Vector2(x,y);
        vel = 100;

        radius = 6;

        // if dest <0, don't move
        dest = -1;

        laserCD = 0.3f;
        spreadCD = 0.8f;
        current = BulletType.LASER;

        world = gw;
    }

    public void update(float delta) {
        //Gdx.app.log("Ship", "at "+pos.x+" going to "+dest);
        // if dest is set and far enough, move towards it at set vel
        if ( (dest >= 0) & (Math.abs(dest - pos.x) > 2) ) {
            if (dest > pos.x) {pos.add(vel*delta, 0);}
            else {pos.sub(vel*delta, 0);}
        }

        heat -= delta;
        //Gdx.app.log("Ship", "heat="+heat);
        while (heat <= 0) {
            if (current == BulletType.LASER) {
                world.getBulletHandler().fireLaser(pos.x, pos.y);
                heat += laserCD;
            }
            if (current == BulletType.SPREAD) {
                world.getBulletHandler().fireSpread(pos.x, pos.y);
                heat += spreadCD;
            }
        }
    }

    public void render(SpriteBatch batcher) {
        batcher.begin();
        batcher.enableBlending();
        // The sprite size is fixed
        batcher.draw(AssetLoader.ship, pos.x - 6, pos.y - 9, 12, 16);
        batcher.end();
    }

    // if touched below ship, fire spread. Otherwise, laser.
    public void move(float x, float y) {
        //Gdx.app.log("Ship", "move to "+x);
        dest = x;
        if (y > world.getGameHeight() - 40) {
            current = BulletType.SPREAD;
        } else {
            current = BulletType.LASER;
        }
    }

    public void stop() {
        //Gdx.app.log("Ship", "stop");
        dest = -1;
    }

    public Vector2 getPos() {return pos;}
    public int getRadius() {return radius;}

}
