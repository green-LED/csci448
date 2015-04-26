package edu.mines.mwichman.MySchmup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;

import edu.mines.mwichman.Helpers.AssetLoader;
import edu.mines.mwichman.Screens.GameScreen;



public class MySchmup extends Game {
	
	@Override
	public void create () {
		Gdx.app.log("MySchmup", "created");
		AssetLoader.load();
        setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}
