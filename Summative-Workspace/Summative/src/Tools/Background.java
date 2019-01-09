package Tools;

import com.badlogic.gdx.graphics.Texture;

import Game.SonicGame;

public class Background {
	
	public Texture image;
	public float x;
	public float y;
	private float start;
	
	public Background(Texture image, float x, float y) {
		this.image = image;
		this.x = x;
		this.y = y;
		start = x;
	}
	
	public void drawBackground(SonicGame game) {
		game.batch.draw(image, x, y);
	}
	
	/*public void drawLeftScrollingBackground(SonicGame game, float x, float y, int limit, float speed) {
	game.batch.draw(image, x, y);
	
	if(x <= limit) { // The +speed closes a gap that was appearing in the background.
		x = limit * (-1);
	}
	x -= speed;
	}*/
	
	public void leftScrollUpdate(int limit, float speed) {
		
		if(x <= limit + speed) { // The +speed closes a gap that was appearing in the background.
			x = limit * (-1);
		}else {
			x -= speed;
		}
		
	}
	
	public void rightScrollUpdate(int limit, float speed) {
		
		if(x <= limit) {
			x = start;
		}else {
			x -= speed;
		}
		
	}
	
}
