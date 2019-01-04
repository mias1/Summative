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
	
	private static final int SONIC_BUTTON_HEIGHT = 74;
	private static final int SONIC_BUTTON_WIDTH = 58;
	private static final int TAILS_BUTTON_HEIGHT = 68;
	private static final int TAILS_BUTTON_WIDTH = 58;
	private static final int KNUCKLES_BUTTON_HEIGHT = 79;
	private static final int KNUCKLES_BUTTON_WIDTH = 55;
	private static final int AMY_BUTTON_HEIGHT = 72;
	private static final int AMY_BUTTON_WIDTH = 49;
	
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
		
		if((Gdx.input.getX() < 122 + SONIC_BUTTON_WIDTH && Gdx.input.getX() > 122) && 
		(Launcher.WINDOW_HEIGHT - Gdx.input.getY() < 160 + SONIC_BUTTON_HEIGHT && Launcher.WINDOW_HEIGHT - Gdx.input.getY() > 160)) {
			game.batch.draw(sonic, 122, 160, SONIC_BUTTON_WIDTH * 1.2f, SONIC_BUTTON_HEIGHT * 1.2f);
		}else {
			game.batch.draw(sonic, 122, 160);
		}
				
		if((Gdx.input.getX() < 222 + TAILS_BUTTON_WIDTH && Gdx.input.getX() > 222) && 
		(Launcher.WINDOW_HEIGHT - Gdx.input.getY() < 160 + TAILS_BUTTON_HEIGHT && Launcher.WINDOW_HEIGHT - Gdx.input.getY() > 160)) {
			game.batch.draw(tails, 222, 160, TAILS_BUTTON_WIDTH * 1.2f, TAILS_BUTTON_HEIGHT * 1.2f);
		}else {
			game.batch.draw(tails, 222, 160);
		}
				
		if((Gdx.input.getX() < 322 + KNUCKLES_BUTTON_WIDTH && Gdx.input.getX() > 322) && 
		(Launcher.WINDOW_HEIGHT - Gdx.input.getY() < 160 + KNUCKLES_BUTTON_HEIGHT && Launcher.WINDOW_HEIGHT - Gdx.input.getY() > 160)) {
			game.batch.draw(knuckles, 322, 160, KNUCKLES_BUTTON_WIDTH * 1.2f, KNUCKLES_BUTTON_HEIGHT * 1.2f);
		}else {
			game.batch.draw(knuckles, 322, 160);
		}
			
		if((Gdx.input.getX() < 422 + AMY_BUTTON_WIDTH && Gdx.input.getX() > 422) && 
		(Launcher.WINDOW_HEIGHT - Gdx.input.getY() < 160 + AMY_BUTTON_HEIGHT && Launcher.WINDOW_HEIGHT - Gdx.input.getY() > 160)) {
			game.batch.draw(amy, 422, 160, AMY_BUTTON_WIDTH * 1.2f, AMY_BUTTON_HEIGHT * 1.2f);
		}else {
			game.batch.draw(amy, 422, 160);
		}
		
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