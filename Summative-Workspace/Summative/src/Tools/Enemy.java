package Tools;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import Launchers.Launcher;

public class Enemy {
	
	public Texture image;
	public float x;
	public float y;
	
	public Enemy(Texture texture, float x, float y) {
		image = texture;
		this.x = x;
		this.y = y;
	}
	
	public void scroll(int actorX, float speed) {
		x -= speed;
	}
	
	public boolean isOnScreen(int actorX) {
		if(x < actorX - 100) {
			return false;
		}else {
			return true;
		}
	}
	
	//if((Gdx.input.getX() < 122 + SONIC_BUTTON_WIDTH && Gdx.input.getX() > 122) && 
	//		(Launcher.WINDOW_HEIGHT - Gdx.input.getY() < 160 + SONIC_BUTTON_HEIGHT && Launcher.WINDOW_HEIGHT - Gdx.input.getY() > 160)) {
	
	public boolean isAlive(int actorX, int actorY, int actorWidth, int actorHeight) {
		if((x < actorX + actorWidth && x > actorX) && (    y < actorY + actorHeight &&    y > actorY)) {
			return false;
		}else {
			return true;
		}
		
	}
	
}
