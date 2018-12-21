package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import Game.SonicGame;
import Launchers.Launcher;

public class MainGameScreen implements Screen {

	public static final float SPEED = 120;
	
	public static final float CHARACTER_ANIMATION_SPEED = 0.05f;
	public static final int CHARACTER_WIDTH = 89;
	public static final int CHARACTER_HEIGHT = 88;
	
	float x;
	float y;
	float stateTime;
	
	SonicGame game;
	
	Texture skyBackground = new Texture("Summative-Workspace/Summative/assets/sky_background.png");
	Animation[] run; 
	
	public MainGameScreen(SonicGame game) {
		this.game = game;
		y = 15;
		x = 10;
		
		run = new Animation[8];
		
		TextureRegion[][] rightSonicRunSpriteSheet = TextureRegion.split(new Texture("Summative-Workspace/Summative/Character Sprites/Sonic Sprites/sonicRunR.png"), 59, 58);
		
		run[0] = new Animation(CHARACTER_ANIMATION_SPEED, rightSonicRunSpriteSheet[0]);
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
	public void render(float delta) {
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += SPEED * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= SPEED * Gdx.graphics.getDeltaTime();
		}
		
		stateTime += delta;
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
	
		game.batch.draw(skyBackground, 0, 0, 800, 400);
		
		game.batch.draw(run[0].getKeyFrame(stateTime, true), x, y, CHARACTER_WIDTH, CHARACTER_HEIGHT);
			
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
