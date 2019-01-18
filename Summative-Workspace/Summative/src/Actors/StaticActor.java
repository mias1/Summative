package Actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * A Base Actor that has no animation or physics.
 * @author Jeremias (Based off of the BaseActor class in the Ch5 CheesePleaseChapter5 project provided on D2L)
 */
public class StaticActor extends Actor {

	public TextureRegion actorTexture;
	public Rectangle rectangleBoundary;
	public float speedX;
	public float speedY;
	
	/**
	 * Initializes a StaticActor.
	 */
	public StaticActor() {
		super();
		actorTexture = new TextureRegion();
		rectangleBoundary = new Rectangle();
		speedX = 0;
		speedY = 0;
	}
	
	public void setTexture(Texture texture) {
		int width = texture.getWidth();
		int height = texture.getHeight();
		setWidth(width);
		setHeight(height);
		actorTexture.setRegion(texture);
	}
	
	public Rectangle getRectangleBoundary() {
		rectangleBoundary.set(getX(), getY(), getWidth(), getHeight());
		return rectangleBoundary;
	}
	    
	/** 
	 * Draws a StaticActor
	 */
	public void draw(Batch batch, float parentAlpha) {
	     Color color = getColor();
	     batch.setColor(color.r, color.g, color.b, color.a);
	     if (isVisible()) {
	    	 batch.draw(actorTexture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	     }
	 }
}
