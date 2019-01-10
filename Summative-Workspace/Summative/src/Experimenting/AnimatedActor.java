package Experimenting;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;

public class AnimatedActor extends BaseActor {
	 public float elapsedTime;
	 public Animation animation;

	 public AnimatedActor() {
		 super();
	     elapsedTime = 0;
	 }

	 public void setAnimation(Animation a) { 
		 Texture t = a.getKeyFrame(0).getTexture();
	     setTexture(t);
	     animation = a;
	 }

	 public void act(float delta) {
	     super.act(delta);
	     elapsedTime += delta;
	 if (velocityX != 0 || velocityY != 0)
		 setRotation( MathUtils.atan2( velocityY, velocityX ) * MathUtils.radiansToDegrees );
	 }

	 public void draw(Batch batch, float parentAlpha) {
	 	region.setRegion(animation.getKeyFrame(elapsedTime));
	 	super.draw(batch, parentAlpha);
	 }
}
