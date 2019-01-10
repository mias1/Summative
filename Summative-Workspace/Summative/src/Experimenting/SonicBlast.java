package Experimenting;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;

import Launchers.Launcher;

public class SonicBlast extends Game {

	private Stage mainStage;
	private Stage uiStage;
	
	private AnimatedActor sonic;
	private BaseActor skyBackground;
	private boolean lose;
	
	private float timeElapsed;
	private Label timeLabel;
	
	final int viewWidth = Launcher.WINDOW_WIDTH;
	final int viewHeight = Launcher.WINDOW_HEIGHT;
	
	// Map Dimensions
	final int mapWidth = viewWidth;
	final int mapHeight = viewHeight;
	
	public void create() {
		mainStage = new Stage();
		uiStage = new Stage();
		timeElapsed = 0;
		
		skyBackground = new BaseActor();
		skyBackground.setTexture(new Texture("Summative-Workspace/Summative/assets/sky_background.png"));
		skyBackground.setPosition(0, 0);
		mainStage.addActor(skyBackground);
		
		sonic = new AnimatedActor();
		TextureRegion[] frames = new TextureRegion[8];
		for(int n = 1; n < 7; n++) {
			String fileName = "Summative-Workspace/Summative/Character Sprites/Sonic Sprites/sonic_run_0" + n + ".png";
			Texture texture = new Texture(fileName);
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			frames[n-1] = new TextureRegion(texture);
		}
		Array<TextureRegion> framesArray = new Array<TextureRegion>(frames);
		
		Animation animation = new Animation(0.1f, framesArray, Animation.PlayMode.LOOP_PINGPONG);
		
		sonic.setAnimation(animation);
		sonic.setOrigin(sonic.getWidth()/2, sonic.getHeight()/2);
		sonic.setPosition(10, 48);
		uiStage.addActor(sonic);
		
		BitmapFont font = new BitmapFont();
		String text = "Time: 0";
		LabelStyle style = new LabelStyle(font, Color.BLACK);
		timeLabel = new Label(text, style);
		timeLabel.setFontScale(2);
		timeLabel.setPosition(100, 100);
		uiStage.addActor(timeLabel);
		
		lose = false;
	}

	public void render() {
		sonic.velocityX = 0;
		sonic.velocityY = 0;
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			sonic.velocityX -= 100;
		}
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
        	sonic.velocityX += 100;
        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
        	sonic.velocityY += 100;
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
        	sonic.velocityY -= 100;
        }
        
        //update
        float delta = Gdx.graphics.getDeltaTime();
        
        mainStage.act(delta);
        uiStage.act(delta);
        
        sonic.setX(MathUtils.clamp(sonic.getX(), 0, mapWidth - sonic.getWidth()));
        sonic.setY(MathUtils.clamp(sonic.getY(), 0, mapHeight - sonic.getHeight()));
        
        Rectangle sonicRectangle = sonic.getBoundingRectangle();
        
        //if(!lose)     >For Enemy touch
        
        if(!lose) {
        	timeElapsed += delta;
        	timeLabel.setText("Time: " + (int)timeElapsed);
        }
        
        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // camera adjustment
        //Camera camera = mainStage.getCamera();
        
        //center camera on player
        //camera.position.set(sonic.getX() + sonic.getOriginX(), sonic.getY() + sonic.getOriginY(), 0);
        
        //bound camera to layout
        //camera.position.x = MathUtils.clamp(camera.position.x, viewWidth/2, mapWidth - viewWidth/2);
        //camera.position.y = MathUtils.clamp(camera.position.y, viewHeight/2, mapHeight - viewHeight/2);
        //camera.update();
        
        mainStage.draw();
        uiStage.draw();
	}
}
