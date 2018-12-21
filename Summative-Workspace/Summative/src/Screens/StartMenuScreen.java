package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import Game.SonicGame;
import Launchers.Launcher;

public class StartMenuScreen implements Screen {

	private static final int PLAY_BUTTON_WIDTH = 250;
	private static final int PLAY_BUTTON_HEIGHT = 125;
	private static final int PLAY_BUTTON_Y = 10;
	
	SonicGame game;
	
	private Sound backgroundMusic;
	Sound buttonSE = Gdx.audio.newSound(Gdx.files.internal("Summative-Workspace/Summative/sound_effects/Decision1.ogg"));
	
	Texture playButtonActive;
	Texture playButtonInactive;
	Texture skyBackground;
	Texture title;
	
	public StartMenuScreen(SonicGame game) {
		this.game = game;
		playButtonActive = new Texture("Summative-Workspace/Summative/assets/play_button_active.png");
		playButtonInactive = new Texture("Summative-Workspace/Summative/assets/play_button_inactive.png");
		skyBackground = new Texture("Summative-Workspace/Summative/assets/sky_background.png");
		title = new Texture("Summative-Workspace/Summative/assets/Title.png");
		backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("Summative-Workspace/Summative/music/MainMenuTheme.ogg"));
		backgroundMusic.loop(0.05f);
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
	public void render(float arg0) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		
		game.batch.draw(skyBackground, 0, 0, Launcher.WINDOW_WIDTH, Launcher.WINDOW_HEIGHT);
		game.batch.draw(title, Launcher.WINDOW_WIDTH / 2 - 400 / 2, Launcher.WINDOW_HEIGHT - 150, 400, 150);
		
		int x = Launcher.WINDOW_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
		
		if((Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x) && (Launcher.WINDOW_HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && Launcher.WINDOW_HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y)) {
			game.batch.draw(playButtonActive, x, PLAY_BUTTON_Y,  PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);	
			if(Gdx.input.isTouched()) {
				buttonSE.play(0.1f);
				backgroundMusic.stop();
				this.dispose();
				game.setScreen(new MainMenuScreen(game));
			}
		}else {
			game.batch.draw(playButtonInactive, x, PLAY_BUTTON_Y,  PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);	
		}
		
		game.batch.end();
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void show() {
		
	}

}
