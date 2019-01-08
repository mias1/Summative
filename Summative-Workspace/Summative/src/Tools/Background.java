package Tools;

import com.badlogic.gdx.graphics.Texture;

public class Background {
	
	public Texture image;
	public int x;
	private int start;
	
	public Background(Texture image, int x) {
		this.image = image;
		this.x = x;
		start = x;
	}
	
	public void leftScrollUpdate(int limit, float speed) {
		
		if(x <= limit + 5) { // The +5 closes a gap that was appearing in the background.
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
