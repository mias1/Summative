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
	
	public static final float CHARACTER_ANIMATION_SPEED = 0.047f;
	public static final int CHARACTER_WIDTH = 89;
	public static final int CHARACTER_HEIGHT = 88;
	
	float x;
	float characterY;
	float stateTime;
	
	int xBackground1;
	int xStart;
	int xBackground2;
	int xLimit;
	int speed;
	
	SonicGame game;
	
	Texture skyBackground = new Texture("Summative-Workspace/Summative/assets/sky_background.png");
	Texture greenHillZoneFloor = new Texture("Summative-Workspace/Summative/assets/plainGreenHillZoneFloor.png");
	Animation[] run; 
	
	String jumpState = "Ground";
	float jumpSpeed = 50 * Gdx.graphics.getDeltaTime();
	float gravity = 10 * Gdx.graphics.getDeltaTime();
	int ground;
	
	public MainGameScreen(SonicGame game, String characterSelected) {
		this.game = game;
		ground = 30;
		characterY = 30;
		x = 10;
		
		xBackground1 = 0;
		xBackground2 = Launcher.WINDOW_WIDTH;
		xStart =  xBackground2;
		xLimit = Launcher.WINDOW_WIDTH * -1;
		speed = 5;
		
		run = new Animation[8];
		
		TextureRegion[][] rightSonicRunSpriteSheet = TextureRegion.split(new Texture("Summative-Workspace/Summative/Character Sprites/Sonic Sprites/sonicRunR.png"), 59, 58);
		
		if(characterSelected.equalsIgnoreCase("sonic")) {
			run[0] = new Animation(CHARACTER_ANIMATION_SPEED, rightSonicRunSpriteSheet[0]);
		}
		
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
		if(jumpState.equalsIgnoreCase("Descending")) {
			characterY -= jumpSpeed;
			jumpSpeed += gravity;
			
			if(characterY >= ground) {
				jumpState = "Ground";
				characterY = ground;
				jumpSpeed = 50 * Gdx.graphics.getDeltaTime();
			}
		} else if(jumpState.equalsIgnoreCase("Ascending")) {
			characterY += jumpSpeed;
			jumpSpeed -= gravity;
			
			if(jumpSpeed <= 0) {
				jumpState = "Descending";
				jumpSpeed = 0;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP) && jumpState.equalsIgnoreCase("Ground")) {
			jumpState = "Ascending";
			characterY += jumpSpeed;
			jumpSpeed -= gravity;
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if(x <= 100) {
				x += SPEED * Gdx.graphics.getDeltaTime();
			}
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			if(x >= 0) {
				x -= SPEED * Gdx.graphics.getDeltaTime();
			}
		}
		
		stateTime += delta;
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
	
		game.batch.draw(skyBackground, xBackground1, 0, Launcher.WINDOW_WIDTH, Launcher.WINDOW_HEIGHT);
		game.batch.draw(skyBackground, xBackground2, 0, Launcher.WINDOW_WIDTH, Launcher.WINDOW_HEIGHT);
		
		game.batch.draw(greenHillZoneFloor, xBackground1, ground - 35, Launcher.WINDOW_WIDTH, 50);
		game.batch.draw(greenHillZoneFloor, xBackground2, ground - 35, Launcher.WINDOW_WIDTH, 50);
		
		if(xBackground1 <= xLimit) {
			xBackground1 = xStart;
		}
		if(xBackground2 <= xLimit) {
			xBackground2 = xStart;
		}
		
		xBackground1 -= speed;
		xBackground2 -= speed;
		
		game.batch.draw(run[0].getKeyFrame(stateTime, true), x, characterY, CHARACTER_WIDTH, CHARACTER_HEIGHT);
		
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
