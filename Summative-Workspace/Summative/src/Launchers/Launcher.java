package Launchers;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import Game.SonicGame;

/**
 * Launches the Application.
 * @author Jeremias
 */
public class Launcher
{
	
	public static final int WINDOW_WIDTH = 600;
	public static final int WINDOW_HEIGHT = 400;
	
	/**
	 * Launches the Application.
	 */
    public static void main (String[] args) {
    	LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    	config.title = "Sonic Blast";
    	config.foregroundFPS = 60;
    	config.width = WINDOW_WIDTH;
    	config.height = WINDOW_HEIGHT;
    	config.resizable = false;
    	new LwjglApplication(new SonicGame(), config);
    }
}