package Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * An Actor that has an animation but no physics.
 * @author Jeremias (Based off of the AnimatedActor class in the Ch5 CheesePleaseChapter5 project provided on D2L)
 */
public class AnimatedActor extends StaticActor {
	
    public float elapsedTime;
    public Animation animation;
    
    private boolean inAir;
    private boolean ascending;
	
    /**
     * Initializes an AnimatedActor.
     */
    public AnimatedActor() {
        super();
        elapsedTime = 0;
        inAir = false;
        ascending = true;
    }

    public void setAnimation(Animation a) { 
        Texture texture = a.getKeyFrame(0).getTexture();
        setTexture(texture);
        animation = a;
    }

    public void act(float deltaTime) {
        super.act(deltaTime);
        elapsedTime += deltaTime;
    }

    /**
     * @see Actors.StaticActor#draw(com.badlogic.gdx.graphics.g2d.Batch, float)
     */
    public void draw(Batch batch, float parentAlpha) {
        actorTexture.setRegion(animation.getKeyFrame(elapsedTime));
        super.draw(batch, parentAlpha);
    }
    
    public boolean getInAir() {
    	return inAir;
    }
    
    public void setInAir(boolean inAir) {
    	this.inAir = inAir;
    }
    
    public boolean getAscending() {
    	return ascending;
    }
    
    public void setAscending(boolean ascending) {
    	this.ascending = ascending;
    }
}