package Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Launchers.Launcher;

public class HorizontalScrollingBackground {

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
