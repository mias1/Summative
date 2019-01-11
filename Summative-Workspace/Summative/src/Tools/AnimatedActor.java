package Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;

public class AnimatedActor extends StaticActor {
	
    public float elapsedTime;
    public Animation animation;
    
    private boolean inAir;
    private boolean ascending;
	
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

    public void draw(Batch batch, float parentAlpha) {
        actorTexture.setRegion(animation.getKeyFrame(elapsedTime));
        super.draw(batch, parentAlpha);
    }
    
    public void moveRight(float speed) {
    	setX(getX() + speed);
    }
    
    public void moveLeft(float speed) {
    	setX(getX() - speed);
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
    
    public void jump(float speed, boolean ascending) {
    	if(ascending) {
    		setY(getY() + speed);
    	}else {
    		setY(getY() - speed);
    	}
    }
}