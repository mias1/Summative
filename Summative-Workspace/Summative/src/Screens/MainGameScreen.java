package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import Game.SpaceGame;

public class MainGameScreen implements Screen {
	
	public static final float SPEED = 120;
	
	Texture img;
	float x;
	float y;
	
	SpaceGame game;
	
	private Sound backgroundMusic;
	Sound buttonSE = Gdx.audio.newSound(Gdx.files.internal("sound_effects/Cancel2.ogg"));
	
	
	public MainGameScreen(SpaceGame game) {
		this.game = game;
		backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("music/MenuTheme.ogg"));
		backgroundMusic.loop(0.05f);
	}
	
	@Override
	public void show() {
		img = new Texture("assets/Sonic.jpg");
	}
	
	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			y += SPEED * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			y -= SPEED * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= SPEED * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += SPEED * Gdx.graphics.getDeltaTime();
		}
		
		game.batch.draw(img, x, y);
		
		game.batch.end();
	}
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		
	}

	@Override
	public void resume() {
		
	}

}