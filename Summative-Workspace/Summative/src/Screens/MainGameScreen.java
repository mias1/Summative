package Screens;

import java.util.ArrayList;

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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;

import Game.SonicGame;
import Launchers.Launcher;
import Tools.PhysicsActor;
import Tools.Rhino;
import Tools.ScrollingBackground;
import Tools.StaticActor;

public class MainGameScreen implements Screen {

	//public static final float CHARACTER_ANIMATION_SPEED = 0.089f;
	//public static final int CHARACTER_WIDTH = 55;
	//public static final int CHARACTER_HEIGHT = 60;
	
	public Stage mainStage;
	public Stage uiStage;
	
	TextureRegion[] jumpFrames = new TextureRegion[8];
	Animation jumpAnimation;
	Animation moveAnimation;
	
	private float timeElapsed;
	private Label timeLabel;
	
	private boolean lose;
	
	float stateTime;
	
	public ScrollingBackground bg;
	public ScrollingBackground floor;
	
	public PhysicsActor[] enemies = new PhysicsActor[1];
	
	SonicGame game;
	
	private Texture skyBackground = new Texture("Summative-Workspace/Summative/assets/sky_background.png");
	private Texture greenHillZoneFloor = new Texture("Summative-Workspace/Summative/assets/plainGreenHillZoneFloor.png");
	private Texture rhinoTexture = new Texture("Summative-Workspace/Summative/Character Sprites/enemy00_00.png");
	private PhysicsActor sonic;
	
	public MainGameScreen(SonicGame game, String characterSelected) {
		this.game = game;
		
		mainStage = new Stage();
		uiStage = new Stage();
		timeElapsed = 0;
		
		bg = new ScrollingBackground();
		bg.setX(0);
		bg.setY(0);
		bg.setTexture(skyBackground);
		bg.setOriginX(0);
		bg.setOriginY(0);
		bg.setSpeed(1);
		bg.setResetPointX(-1200);
		bg.setWidth(1800);
		bg.setHeight(Launcher.WINDOW_HEIGHT);
		mainStage.addActor(bg);
		
		floor = new ScrollingBackground();
		floor.setX(0);
		floor.setY(0);
		floor.setTexture(greenHillZoneFloor);
		floor.setOriginX(0);
		floor.setOriginY(0);
		floor.setSpeed(3);
		floor.setResetPointX(-1200);
		floor.setWidth(1800);
		floor.setHeight(50);
		mainStage.addActor(floor);
		
        sonic = new PhysicsActor();
        TextureRegion[] movingFrames = new TextureRegion[8];
        for (int n = 0; n < 8; n++)
        {   
            String fileName = "Summative-Workspace/Summative/Character Sprites/Sonic Sprites/sonic_run_0" + (n+1) + ".png";
            Texture texture = new Texture(Gdx.files.internal(fileName));
            texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
            movingFrames[n] = new TextureRegion(texture);
        }
        Array<TextureRegion> framesArray = new Array<TextureRegion>(movingFrames);
        
        moveAnimation = new Animation(0.1f, framesArray, Animation.PlayMode.LOOP);
        
        sonic.setAnimation(moveAnimation);
        sonic.setOrigin(10, 48);
        sonic.setPosition(10, 48);
        sonic.setWidth(55);
        sonic.setHeight(60);   
        sonic.setSpeed(120);
        sonic.setGravity(0.75f);
        sonic.setJumpSpeed(15);
        sonic.setGroundLevel(48);
        uiStage.addActor(sonic);
		
        TextureRegion[] rhinoFrames = new TextureRegion[1];
        rhinoFrames[0] = new TextureRegion(rhinoTexture);
        
        Array<TextureRegion> rhinoFramesArray = new Array<TextureRegion>(rhinoFrames);
        
        Animation rhinoAnimation = new Animation(0.1f, rhinoFramesArray, Animation.PlayMode.LOOP);
        
        enemies[0] = new PhysicsActor();
        enemies[0].setOriginX(650);
        enemies[0].setOriginY(48);
        enemies[0].setAnimation(rhinoAnimation);
        enemies[0].setSpeed(3);
        enemies[0].setWidth(55);
        enemies[0].setHeight(55);
        enemies[0].setPosition(650, 48);
        enemies[0].setGravity(0.75f);
        enemies[0].setJumpSpeed(15);
        enemies[0].setGroundLevel(48);
        uiStage.addActor(enemies[0]);
        
		BitmapFont font = new BitmapFont();
		String text = "Time: 0";
		LabelStyle style = new LabelStyle(font, Color.BLACK);
		timeLabel = new Label(text, style);
		timeLabel.setFontScale(2);
		timeLabel.setPosition(15, 365);
		mainStage.addActor(timeLabel);
		
		lose = false;
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
		float deltaTime = Gdx.graphics.getDeltaTime();
		
		if(!(enemies[0].getX() <= -60)) {
			uiStage.addActor(enemies[0]);
			System.out.println(1);
		}else {
			enemies[0].addAction(Actions.removeActor());
		}
		enemies[0].setX(enemies[0].getX() - enemies[0].getSpeed());
		
		if(!lose) {
			sonic.managePhysics();
			bg.scrollLeft();
			floor.scrollLeft();
		}
        
		if(!lose) {
			mainStage.act(deltaTime);
			uiStage.act(deltaTime);
		}
		
		if(Gdx.input.isKeyPressed(Keys.ENTER) && lose) {
			lose = false;
			sonic.setX(sonic.getOriginX());
			sonic.setY(sonic.getOriginY());
			sonic.setAscending(true);
			sonic.setInAir(false);
			//enemy0.setPosition(enemy0.getOriginX(), enemy0.getOriginY());
		}
        
        if(!lose) {
        	timeElapsed += 0.2;
        	timeLabel.setText("Score: " + (int)timeElapsed);
        }else {
        	timeElapsed = 0;
        	timeLabel.setText("You Lose! Press ENTER to start over.");
        }
        
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		mainStage.draw();
		uiStage.draw();
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
