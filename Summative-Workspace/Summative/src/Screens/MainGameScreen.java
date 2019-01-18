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

import Actors.PhysicsActor;
import Game.SonicGame;
import Launchers.Launcher;
import Managers.EnemyManager;
import Tools.ScrollingBackground;

/**
 * Renders and Manages the Game Screen.
 * @author Jeremias
 */
public class MainGameScreen implements Screen {
	
	public Stage mainStage;
	public Stage uiStage;
	
	TextureRegion[] jumpFrames = new TextureRegion[8];
	Animation jumpAnimation;
	Animation moveAnimation;
	
	private float timeElapsed;
	private Label timeLabel;
	
	private boolean lose;
	
	float stateTime;
	
	boolean ascending = true;
	
	public ScrollingBackground bg;
	public ScrollingBackground floor;
	
	public PhysicsActor[] enemies = new PhysicsActor[4];
	
	SonicGame game;
	
	private PhysicsActor sonic;
	
	
	/**
	 * Initializes the SonicGame object and all visible components seen
	 * in the MainGameScreen.
	 * @param game
	 */
	public MainGameScreen(SonicGame game) {
		this.game = game;
		
		mainStage = new Stage();
		uiStage = new Stage();
		timeElapsed = 0;
		
		bg = new ScrollingBackground();
		bg.setX(0);
		bg.setY(0);
		bg.setTexture(new Texture("Summative-Workspace/Summative/assets/sky_background.png"));
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
		floor.setTexture(new Texture("Summative-Workspace/Summative/assets/plainGreenHillZoneFloor.png"));
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
		
        EnemyManager.initializeEnemies();
        
		BitmapFont font = new BitmapFont();
		String text = "Score: 0";
		LabelStyle style = new LabelStyle(font, Color.BLACK);
		timeLabel = new Label(text, style);
		timeLabel.setFontScale(2);
		timeLabel.setPosition(15, 365);
		mainStage.addActor(timeLabel);
		
		lose = false;
	}

	@Override
	public void render(float delta) {
		float deltaTime = Gdx.graphics.getDeltaTime();
        
		manageGame(deltaTime);
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

	@Override
	public void show() {
		
	}
	
	/**
	 * Manages crucial game __, such as collision, auto-scroll, score managing, enemy generating 
	 * and the main actor's physics managing. Also renders the screen.
	 * @param deltaTime
	 */
	public void manageGame(float deltaTime) {
		if(EnemyManager.isColliding(sonic)) {
			lose = true;
		}
		
		if(!lose) {
			sonic.managePhysics();
			bg.scrollLeft();
			floor.scrollLeft();
			EnemyManager.generateEnemies(uiStage);
			
			mainStage.act(deltaTime);
			uiStage.act(deltaTime);
			
			timeElapsed += 0.2;
        	timeLabel.setText("Score: " + (int)timeElapsed);
		}else {
			timeLabel.setPosition(15, 350);
        	timeLabel.setText("Score: " + (int)timeElapsed + "\nYou lose! Press ENTER to return to menu.");
		}
		
		returnToMenu();
		drawScreen();
	}
	
	/**
	 * Takes player back to the main menu if ENTER is clicked when
	 * player has loses.
	 */
	private void returnToMenu() {
		if(Gdx.input.isKeyJustPressed(Keys.ENTER) && lose) {
			lose = false;
			sonic.setX(sonic.getOriginX());
			sonic.setY(sonic.getOriginY());
			sonic.setAscending(true);
			sonic.setInAir(false);
			game.setScreen(new StartMenuScreen(game));
		}
	}
	
	/**
	 * Draws the Main and User interface.
	 */
	private void drawScreen() {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		mainStage.draw();
		uiStage.draw();
	}
}
