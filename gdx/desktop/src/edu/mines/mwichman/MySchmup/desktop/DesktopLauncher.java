package edu.mines.mwichman.MySchmup.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.mines.mwichman.MySchmup.MySchmup;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "MySchmup";
        config.width = 272;
        config.height = 408;
		new LwjglApplication(new MySchmup(), config);
	}
}
