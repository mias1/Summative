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
import Tools.MoveMechanics;

public class MainGameScreen extends MoveMechanics implements Screen {

	public static final float CHARACTER_ANIMATION_SPEED = 0.089f;
	public static final int CHARACTER_WIDTH = 55;
	public static final int CHARACTER_HEIGHT = 60;
	
	private static int score = 0;
	
	float stateTime;
	
	int xBackground1;
	int xStart;
	int xBackground2;
	int xLimit;
	int backgroundSpeed;
	int xFloor1;
	int xFloor2;
	int floorSpeed;
	
	SonicGame game;
	
	Texture skyBackground = new Texture("Summative-Workspace/Summative/assets/sky_background.png");
	Texture greenHillZoneFloor = new Texture("Summative-Workspace/Summative/assets/plainGreenHillZoneFloor.png");
	Animation[] run; 
	
	public MainGameScreen(SonicGame game, String characterSelected) {
		this.game = game;
		
		setMechanics(10, 48, 120, 15, 0.75f, "Ground", 41);
		
		xBackground1 = 0;
		xBackground2 = Launcher.WINDOW_WIDTH;
		xStart =  xBackground2;
		xLimit = Launcher.WINDOW_WIDTH * -1;
		backgroundSpeed = 3;
		
		xFloor1 = xBackground1;
		xFloor2 = xBackground2;
		floorSpeed = 5;
		
		run = new Animation[1];
		
		TextureRegion[][] rightSonicRunSpriteSheet = TextureRegion.split(new Texture("Summative-Workspace/Summative/Character Sprites/Sonic Sprites/SonicRunningRight.png"), 21, 25);
		TextureRegion[][] rightTailsRunSpriteSheet = TextureRegion.split(new Texture("Summative-Workspace/Summative/Character Sprites/Tails Sprites/TailsRunningRight.png"), 30, 26);
		TextureRegion[][] rightKnucklesRunSpriteSheet = TextureRegion.split(new Texture("Summative-Workspace/Summative/Character Sprites/Knuckles Sprites/KnucklesRunR.png"), 45, 43);
		
		if(characterSelected.equalsIgnoreCase("sonic")) {
			run[0] = new Animation(CHARACTER_ANIMATION_SPEED, rightSonicRunSpriteSheet[0]);
		}
		
		if(characterSelected.equalsIgnoreCase("tails")) {
			run[0] = new Animation(CHARACTER_ANIMATION_SPEED, rightTailsRunSpriteSheet[0]);
		}
		
		if(characterSelected.equalsIgnoreCase("knuckles")) {
			run[0] = new Animation(CHARACTER_ANIMATION_SPEED, rightKnucklesRunSpriteSheet[0]);
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
		
		verifyIfJumping();
		
		if(Gdx.input.isKeyPressed(Keys.UP) && jumpState.equalsIgnoreCase("Ground")) {
			initiateJump();
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			moveRight();
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			moveLeft();
		}
		
		stateTime += delta;
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
	
		game.batch.draw(skyBackground, xBackground1, 0, Launcher.WINDOW_WIDTH, Launcher.WINDOW_HEIGHT);
		game.batch.draw(skyBackground, xBackground2, 0, Launcher.WINDOW_WIDTH, Launcher.WINDOW_HEIGHT);
		
		game.batch.draw(greenHillZoneFloor, xFloor1, 0, Launcher.WINDOW_WIDTH, 50);
		game.batch.draw(greenHillZoneFloor, xFloor2, 0, Launcher.WINDOW_WIDTH, 50);
		
		if(xBackground1 <= xLimit) {
			xBackground1 = xStart;
		}
		if(xBackground2 <= xLimit) {
			xBackground2 = xStart;
		}
		if(xFloor1 <= xLimit) {
			xFloor1 = xStart;
		}
		if(xFloor2 <= xLimit) {
			xFloor2 = xStart;
		}
		
		xBackground1 -= backgroundSpeed;
		xBackground2 -= backgroundSpeed;
		xFloor1 -= floorSpeed;
		xFloor2 -= floorSpeed;
		
		game.batch.draw(run[0].getKeyFrame(stateTime, true), actorX, actorY, CHARACTER_WIDTH, CHARACTER_HEIGHT);
		
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
