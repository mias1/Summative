package Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Launchers.Launcher;

public class HorizontalScrollingBackgroundPiece {

	public Texture bg;
	public float startingX, endingX;
	
	public int speed;
	public float x1, x2;
	
	public HorizontalScrollingBackgroundPiece(Texture background, float firstBackgroundStartingPoint, float secondBackgroundStartingPoint, int scrollSpeed) {
		bg = background;
		startingX = secondBackgroundStartingPoint;
		endingX = firstBackgroundStartingPoint;
		speed = scrollSpeed;
		x1 = startingX;
		x2 = endingX;
	}
	
	public void update() {
		if(x1 <= endingX) {
			x1 = endingX;
		}
		if(x2 <= endingX) {
			x2 = startingX;
		}
		
		x1 -= speed;
		x2 -= speed;
	}
	
	//EXPERIMENTING
	
	/*Texture background;
	float xBackground1, xBackground2;
	int speed;
	float imageScale;
	boolean speedIsFixed;
	
	public HorizontalScrollingBackground(Texture image, float x1, float x2, int speed) {
		background = image;
		xBackground1 = x1;
		xBackground2 = x2;
		speed = 0;
		
		xBackground1 = 0;
		xBackground2 = background.getWidth();
		
		imageScale = Launcher.WINDOW_HEIGHT / image.getHeight();
		speedIsFixed = true;
	}

	public void updateAndRender (float deltaTime) {
		if(speedIsFixed) {
			
			if(xBackground1==600) {
				xBackground1=-600;
			}
			
			if(xBackground2==600) {
				xBackground2=-600;
			}
			
			xBackground1 += speed * deltaTime;
			xBackground2+=5;
		}
		
	}
	*/
}
