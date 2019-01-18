package Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.StartMenuScreen;

/**
 * The Game class that creates the main SpriteBatch.
 * @author Jeremias
 */
public class SonicGame extends Game {
	
	public SpriteBatch batch;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new StartMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

}
