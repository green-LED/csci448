package edu.mines.mwichman.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Matthew on 4/23/2015.
 *  Loads sprites from android/assets
 *  Called once, then disposed of afterwards
 */
public class AssetLoader {

    public static Texture shipTexture, enemyTexture;

    public static TextureRegion ship, enemy;

    public static void load() {

        shipTexture = new Texture(Gdx.files.internal("ship.png"));
        shipTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        ship = new TextureRegion(shipTexture, 8, 0, 48, 64);
        ship.flip(false, true);

        enemyTexture = new Texture(Gdx.files.internal("enemy.png"));
        enemyTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        enemy = new TextureRegion(enemyTexture, 8, 0, 48, 64);
        enemy.flip(false, false);

    }

    public static void dispose() {
        shipTexture.dispose();
        enemyTexture.dispose();
    }

}
