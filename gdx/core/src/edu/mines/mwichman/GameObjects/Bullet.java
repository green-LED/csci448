package edu.mines.mwichman.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

// Has the collision detection function
// To be converted into an abstract class with bullet types as subclasses

public class Bullet {

    private Vector2 pos;
    private float ang;
    private BulletType type;

    private static float laserWidth = 4, laserHeight = 16;
    private static float laserVel = -250;

    private static float spreadRad = 3;
    private static float spreadVel = 150;

    public Bullet(float x, float y, float a, BulletType t) {
        pos = new Vector2(x,y);
        ang = a;
        type = t;
    }

    public void update(float delta) {
        if (type == BulletType.LASER) {
            pos.y += delta * laserVel;
        }
        if (type == BulletType.SPREAD) {
            pos.x += delta * Math.cos(ang) * spreadVel;
            pos.y += delta * Math.sin(ang) * spreadVel;
        }
    }

    public void render(ShapeRenderer renderer) {
        // renderer.begin() and .end() in BulletHandler
        if (type == BulletType.LASER) {
            renderer.setColor(150 / 255.0f, 200 / 255.0f, 255 / 255.0f, 1);
            renderer.rect(pos.x - laserWidth / 2,
                    pos.y - laserHeight / 2,
                    laserWidth, laserHeight);
        }
        if (type == BulletType.SPREAD) {
            renderer.setColor(255 / 255.0f, 255 / 255.0f, 50 / 255.0f, 1);
            renderer.circle(pos.x, pos.y, spreadRad);
        }
    }

    // Only collides with ships that have circular hitboxes
    public boolean collides(Vector2 p, float r) {
        if (type == BulletType.LASER) {
            // first check: are any of the laser's corners within the ship?
            if ( (distance(p.x, p.y, pos.x, pos.y) < r) ||
                    (distance(p.x, p.y, pos.x+laserWidth, pos.y) < r) ||
                    (distance(p.x, p.y, pos.x, pos.y+laserHeight) < r) ||
                    (distance(p.x, p.y, pos.x+laserWidth, pos.y+laserHeight) < r)) {
                return true;
            }
            // second check: do any of the laser's edges cross/contain the ship?
            if ( (p.x > pos.x) && (p.x < pos.x + laserWidth) &&
                    (p.y - r < pos.y + laserHeight) &&
                    (p.y + r > pos.y) ) {
                return true;
            }
            if ( (p.y > pos.y) && (p.y < pos.y + laserHeight) &&
                    (p.x - r < pos.x + laserWidth) &&
                    (p.x + r > pos.x) ) {
                return true;
            }

        }
        if (type == BulletType.SPREAD) {
            if ( distance(p.x, p.y, pos.x, pos.y) < r+spreadRad ) {
                return true;
            }
        }
        return false;
    }

    private float distance(float ax, float ay, float bx, float by) {
        float x = ax - bx;
        float y = ay - by;
        return (float) Math.sqrt(x*x + y*y);
    }

    public Vector2 getPos() {return pos;}
}
