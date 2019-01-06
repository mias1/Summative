package Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Launchers.Launcher;

//EXPERIMENTING

public class HorizontalScrollingBackgroundExample {
	
	/*
	public static final int NORMAL_SPEED = 80;
	public static final int ACCELERATION = 50;
	public static final int GOAL_REACH_ACCELERATION = 200;
	
	Texture background;
	float yBackground1, yBackground2;
	int speed;
	int goalSpeed;
	float imageScale;
	boolean speedFixed;
	
	public HorizontalScrollingBackgroundExample(Texture image) {
		background = image;
		
		speed = 0;
		goalSpeed = NORMAL_SPEED;
		
		yBackground1 = 0;
		yBackground2 = background.getWidth();
		
		imageScale = Launcher.WINDOW_HEIGHT / image.getHeight();
		speedFixed = true;
	}
	
	public void updateAndRender (float deltaTime, SpriteBatch batch) {
		//Speed adjustment to reach goal
		if (speed < goalSpeed) {
			speed += GOAL_REACH_ACCELERATION * deltaTime;
			
			if (speed > goalSpeed) {
				speed = goalSpeed;
			}
		} else if (speed > goalSpeed) {
			speed -= GOAL_REACH_ACCELERATION * deltaTime;
			
			if (speed < goalSpeed) {
				speed = goalSpeed;
			}
		}
		
		if (!speedFixed) {
			speed += ACCELERATION * deltaTime;
		}
		
		yBackground1 -= speed * deltaTime;
		yBackground2 -= speed * deltaTime;
		
		//if image reached the bottom of screen and is not visible, put it back on top
		if (yBackground1 + background.getWidth() * imageScale <= 0)
			yBackground1 = yBackground2 + background.getWidth() * imageScale;
		
		if (yBackground2 + background.getWidth() * imageScale <= 0)
			yBackground2 = yBackground1 + background.getWidth() * imageScale;
		
		//Render
		batch.draw(background, 0, yBackground1, Launcher.WINDOW_HEIGHT, background.getWidth() * imageScale);
		batch.draw(background, 0, yBackground2, Launcher.WINDOW_HEIGHT, background.getWidth() * imageScale);
	}
	
	public void setSpeed (int goalSpeed) {
		this.goalSpeed = goalSpeed;
	}
	
	public void setSpeedFixed (boolean speedFixed) {
		this.speedFixed = speedFixed;
	}
		
    Texture background;
	int width;
	int height;
	int startPoint = 0 - width;
	int endPoint = width;
	
	public HorizontalScrollingBackground(Texture background) {
		this.background = background;
	}
	*/
}
