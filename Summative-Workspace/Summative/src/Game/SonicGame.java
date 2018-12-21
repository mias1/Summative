package Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.MainGameScreen;
import Screens.MainMenuScreen;
import Screens.StartMenuScreen;

public class SonicGame extends Game {
	
	public SpriteBatch batch;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new MainGameScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

}
