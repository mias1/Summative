package Tools;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import Launchers.Launcher;

public class Enemy {
	
	public Texture image;
	public float x;
	public float y;
	public float startX;
	public float startY;
	public boolean isOnScreen = false;
	
	public Enemy(Texture texture, float x, float y) {
		image = texture;
		this.x = x;
		this.y = y;
		startX = this.x;
		startY = this.y;
	}
	
	public void scroll(int actorX, float speed) {
		x -= speed;
	}
	
	//if((Gdx.input.getX() < 122 + SONIC_BUTTON_WIDTH && Gdx.input.getX() > 122) && 
	//		(Launcher.WINDOW_HEIGHT - Gdx.input.getY() < 160 + SONIC_BUTTON_HEIGHT && Launcher.WINDOW_HEIGHT - Gdx.input.getY() > 160)) {
	
	public boolean isAlive(int actorX, int actorY, int actorWidth, int actorHeight, int windowWidth, int windowHeight) {
		if((x < actorX + actorWidth && x > actorX) && (windowHeight - y < actorY + actorHeight && windowHeight - y > actorY)) {
			return false;
		}else {
			return true;
		}
		
	}
	
}
