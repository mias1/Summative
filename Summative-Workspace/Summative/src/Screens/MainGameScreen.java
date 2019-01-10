package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;

import Experimenting.AnimatedActor;
import Game.SonicGame;
import Launchers.Launcher;
import Tools.Background;
import Tools.CharacterMoveMechanics;
import Tools.Enemy;
import Tools.StaticActor;

public class MainGameScreen extends CharacterMoveMechanics implements Screen {

	public Stage mainStage;
	
	public static final float CHARACTER_ANIMATION_SPEED = 0.089f;
	public static final int CHARACTER_WIDTH = 55;
	public static final int CHARACTER_HEIGHT = 60;
	
	private static int score = 0;
	
	float stateTime;
	
	//public Background background, background2;
	public StaticActor bg1, bg2;
	//public Background floor, floor2;
	public StaticActor floor1, floor2;
	
	SonicGame game;
	
	Texture skyBackground = new Texture("Summative-Workspace/Summative/assets/sky_background.png");
	Texture greenHillZoneFloor = new Texture("Summative-Workspace/Summative/assets/plainGreenHillZoneFloor.png");
	Texture enemyT = new Texture("Summative-Workspace/Summative/assets/badlogic.jpg");
	Animation[] run; 
	
	public MainGameScreen(SonicGame game, String characterSelected) {
		this.game = game;
		
		mainStage = new Stage();
		
		bg1 = new StaticActor();
		bg1.setTexture(skyBackground);
		bg1.setPosition(0, 0);
		bg1.setOrigin(0, 0);
		bg1.setWidth(Launcher.WINDOW_WIDTH);
		bg1.setHeight(Launcher.WINDOW_HEIGHT);
		mainStage.addActor(bg1);
		
		bg2 = new StaticActor();
		bg2.setTexture(skyBackground);
		bg2.setPosition(600, 0);
		bg2.setOrigin(600, 0);
		bg2.setWidth(Launcher.WINDOW_WIDTH);
		bg2.setHeight(Launcher.WINDOW_HEIGHT);
		mainStage.addActor(bg2);
		
		floor1 = new StaticActor();
		floor1.setTexture(greenHillZoneFloor);
		floor1.setPosition(0, 0);
		floor1.setOrigin(0, 0);
		floor1.setWidth(Launcher.WINDOW_WIDTH);
		floor1.setHeight(50);
		mainStage.addActor(floor1);
		
		floor2 = new StaticActor();
		floor2.setTexture(greenHillZoneFloor);
		floor2.setPosition(600, 0);
		floor2.setOrigin(600, 0);
		floor2.setWidth(Launcher.WINDOW_WIDTH);
		floor2.setHeight(50);
		mainStage.addActor(floor2);
		
		setMechanics(10, 48, 120, 15, 0.75f, "Ground", 48);
		
		run = new Animation[8];
		
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
		score++; //System.out.println(score);
		
		float deltaTime = Gdx.graphics.getDeltaTime();
		
		/*verifyIfJumping();
		
		if(Gdx.input.isKeyPressed(Keys.UP) && jumpState.equalsIgnoreCase("Ground")) {
			initiateJump();
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			moveRight();
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			moveLeft();
		}*/
		
		//stateTime += delta;
		
		if(bg1.getX() <= (-1) * Launcher.WINDOW_WIDTH) {
			bg1.setPosition(Launcher.WINDOW_WIDTH, 0);
		}
		if(bg2.getX() <= (-1) * Launcher.WINDOW_WIDTH) {
			//bg2.setPosition(Launcher.WINDOW_WIDTH, 0);
			bg2.setPosition(bg1.getX() + 600, 0);
		}
		if(floor1.getX() <= (-1) * Launcher.WINDOW_WIDTH) {
			floor1.setPosition(Launcher.WINDOW_WIDTH, 0);
		}
		if(floor2.getX() <= (-1) * Launcher.WINDOW_WIDTH) {
			floor2.setPosition(Launcher.WINDOW_WIDTH, 0);
		}
		
		bg1.speedX = -120;
		bg2.speedX = -120;
		floor1.speedX = -120;
		floor2.speedX = -120;
        
        mainStage.act(deltaTime);
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		/*game.batch.draw(background.image, background.x, 0, Launcher.WINDOW_WIDTH, Launcher.WINDOW_HEIGHT);
		game.batch.draw(background2.image, background2.x, 0, Launcher.WINDOW_WIDTH, Launcher.WINDOW_HEIGHT);
		background.leftScrollUpdate(Launcher.WINDOW_WIDTH * -1, 2);
		background2.leftScrollUpdate(Launcher.WINDOW_WIDTH * -1, 2);
		
		game.batch.draw(floor.image, floor.x, 0, Launcher.WINDOW_WIDTH, 50);
		game.batch.draw(floor2.image, floor2.x, 0, Launcher.WINDOW_WIDTH, 50);
		floor.leftScrollUpdate(Launcher.WINDOW_WIDTH * -1, 5);
		floor2.leftScrollUpdate(Launcher.WINDOW_WIDTH * -1, 5);
		
		game.batch.draw(run[0].getKeyFrame(stateTime, true), actorX, actorY, CHARACTER_WIDTH, CHARACTER_HEIGHT);
		
		if(score % 500 == 0 || enemy.isOnScreen) {
			enemy.isOnScreen = true;
			game.batch.draw(enemy.image, enemy.x, enemy.y);
			enemy.scroll(actorX, 3);
			if(!enemy.isAlive(actorX, actorY, CHARACTER_WIDTH, CHARACTER_HEIGHT, Launcher.WINDOW_WIDTH, Launcher.WINDOW_HEIGHT) ||
					enemy.x > - 100) {
				enemy.isOnScreen = false;
				enemy.x = enemy.startX;
			}
		}*/
		mainStage.draw();
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
