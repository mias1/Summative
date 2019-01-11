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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;

import Game.SonicGame;
import Launchers.Launcher;
import Tools.AnimatedActor;
import Tools.CharacterMoveMechanics;
import Tools.StaticActor;

public class MainGameScreen extends CharacterMoveMechanics implements Screen {

	//public static final float CHARACTER_ANIMATION_SPEED = 0.089f;
	//public static final int CHARACTER_WIDTH = 55;
	//public static final int CHARACTER_HEIGHT = 60;
	
	public Stage mainStage;
	public Stage uiStage;
	
	TextureRegion[] jumpFrames = new TextureRegion[8];
	Animation jumpAnimation;
	
	private float timeElapsed;
	private Label timeLabel;
	
	private boolean lose;
	
	float stateTime;
	
	public StaticActor bg1, bg2;
	public StaticActor floor1, floor2;
	public StaticActor enemy0;
	
	SonicGame game;
	
	final float JUMP_SPEED = 15;
	float jumpSpeed = JUMP_SPEED;
	float gravity = 0.75f;
	
	private Texture skyBackground = new Texture("Summative-Workspace/Summative/assets/sky_background.png");
	private Texture greenHillZoneFloor = new Texture("Summative-Workspace/Summative/assets/plainGreenHillZoneFloor.png");
	private Texture enemy00 = new Texture("Summative-Workspace/Summative/Character Sprites/enemy00_00.png");
	private AnimatedActor sonic;
	
	public MainGameScreen(SonicGame game, String characterSelected) {
		this.game = game;
		
		mainStage = new Stage();
		uiStage = new Stage();
		timeElapsed = 0;
		
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
		
		enemy0 = new StaticActor();
		enemy0.setTexture(enemy00);
		enemy0.setOrigin(300, 48);
		
        sonic = new AnimatedActor();
        TextureRegion[] movingFrames = new TextureRegion[8];
        for (int n = 0; n < 8; n++)
        {   
            String fileName = "Summative-Workspace/Summative/Character Sprites/Sonic Sprites/sonic_run_0" + (n+1) + ".png";
            Texture texture = new Texture(Gdx.files.internal(fileName));
            texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
            movingFrames[n] = new TextureRegion(texture);
        }
        Array<TextureRegion> framesArray = new Array<TextureRegion>(movingFrames);
        
        
        for (int n = 0; n < 4; n++)
        {   
            String fileName = "Summative-Workspace/Summative/Character Sprites/Sonic Sprites/sonic_jump0" + n + ".png";
            Texture texture = new Texture(Gdx.files.internal(fileName));
            texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
            jumpFrames[n] = new TextureRegion(texture);
        }
        Array<TextureRegion> jumpFramesArray = new Array<TextureRegion>(jumpFrames);
        
        
        Animation moveAnimation = new Animation(0.1f, framesArray, Animation.PlayMode.LOOP);
        
        jumpAnimation = new Animation(0.1f, jumpFramesArray, Animation.PlayMode.LOOP);
        
        sonic.setAnimation(moveAnimation);
        sonic.setOrigin(10, 48);
        sonic.setPosition(10, 48);
        sonic.setWidth(55);
        sonic.setHeight(60);
        uiStage.addActor(sonic);
		
		BitmapFont font = new BitmapFont();
		String text = "Time: 0";
		LabelStyle style = new LabelStyle(font, Color.BLACK);
		timeLabel = new Label(text, style);
		timeLabel.setFontScale(2);
		timeLabel.setPosition(15, 365);
		uiStage.addActor(timeLabel);
		
		lose = false;
		
		setMechanics(10, 48, 120, 15, 0.75f, "Ground", 48);
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
		//while(!lose) {
		float deltaTime = Gdx.graphics.getDeltaTime();
		
		if(sonic.getInAir()  && !lose) {
			//sonic.animation = jumpAnimation;
			if(sonic.getAscending()) {
				jumpSpeed -= gravity;
				if(jumpSpeed <= 0) {
					sonic.setAscending(false);
				}
			}else {
				if(sonic.getY() <= 30) {
					sonic.setY(30);
					sonic.setAscending(true);
					sonic.setInAir(false);
					jumpSpeed = 15;
				}else {
					jumpSpeed += gravity;
				}
			}
			sonic.jump(jumpSpeed, sonic.getAscending());
		}
		
		if(sonic.getRectangleBoundary().overlaps(enemy0.getRectangleBoundary())) {
			lose = true;
		}else {
			enemy0.setPosition(enemy0.getOriginX(), enemy0.getOriginY());
			uiStage.addActor(enemy0);
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP) && !sonic.getInAir()) {
			sonic.jump(jumpSpeed, sonic.getAscending());
			sonic.setAscending(true);
			sonic.setInAir(true);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT) && !lose) {
			sonic.moveRight(120 * Gdx.graphics.getDeltaTime());
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT) && !lose) {
			sonic.moveLeft(120 * Gdx.graphics.getDeltaTime());
		}
		
		//stateTime += delta;
		
		if(bg1.getX() <= (-1) * Launcher.WINDOW_WIDTH) {
			bg1.setPosition(600, 0);
		}
		if(bg2.getX() <= (-1) * Launcher.WINDOW_WIDTH) {
			//bg2.setPosition(Launcher.WINDOW_WIDTH, 0);
			bg2.setPosition(600, 0);
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
        
		if(!lose) {
        mainStage.act(deltaTime);
		uiStage.act(deltaTime);
		}
		
		if(Gdx.input.isKeyPressed(Keys.ENTER)) {
			lose = false;
			sonic.setX(sonic.getOriginX());
			sonic.setY(sonic.getOriginY());
			sonic.setAscending(true);
			sonic.setInAir(false);
		}
        
        if(!lose) {
        	timeElapsed += 0.2;
        	timeLabel.setText("Score: " + (int)timeElapsed);
        }else {
        	timeElapsed = 0;
        	//timeLabel.setText("Score: 0");
        	timeLabel.setText("You Lose! Press ENTER to start over.");
        }
        
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
		uiStage.draw();
		//}
		
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
