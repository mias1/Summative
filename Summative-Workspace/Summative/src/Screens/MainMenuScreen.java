package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import Game.SonicGame;
import Launchers.Launcher;

public class MainMenuScreen implements Screen {
	
	public static final float SPEED = 120;
	
	Texture sonic;
	Texture tails;
	Texture knuckles;
	Texture amy;
	float x;
	float y;
	
	SonicGame game;
	
	private Sound backgroundMusic;
	Sound buttonSE = Gdx.audio.newSound(Gdx.files.internal("Summative-Workspace/Summative/sound_effects/Cancel2.ogg"));
	
	
	public MainMenuScreen(SonicGame game) {
		this.game = game;
		backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("Summative-Workspace/Summative/music/MenuTheme.ogg"));
		backgroundMusic.loop(0.05f);
	}
	
	@Override
	public void show() {
		sonic = new Texture("Summative-Workspace/Summative/Character Sprites/Sonic Sprites/sonic_select.png");
		tails = new Texture("Summative-Workspace/Summative/Character Sprites/Tails Sprites/tails_select.png");
		knuckles = new Texture("Summative-Workspace/Summative/Character Sprites/Knuckles Sprites/knuckles_select.png");
		amy = new Texture("Summative-Workspace/Summative/Character Sprites/Amy Sprites/amy_select.png");
	}
	
	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(0, 0, 0.5f, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		
		//if((Gdx.input.getX() < 122 + sonic.getWidth() && Gdx.input.getX() > 122) && (Launcher.WINDOW_HEIGHT - Gdx.input.getY() < 160 + sonic.getHeight() && Launcher.WINDOW_HEIGHT - Gdx.input.getY() > 160)) {}
		
	    game.batch.draw(sonic, 122, 160);
	    game.batch.draw(tails, 222, 160);
		game.batch.draw(knuckles, 322, 160);
		game.batch.draw(amy, 422, 160); 
		
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