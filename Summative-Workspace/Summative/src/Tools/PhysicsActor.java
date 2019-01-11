package Tools;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;

public class PhysicsActor extends AnimatedActor
{
    private Vector2 speed;
    private Vector2 acceleration;

    private float maxSpeed;

    private float deceleration;

    // should image rotate to match velocity?
    //private boolean autoAngle;

    public PhysicsActor()
    {
    	speed = new Vector2();
        acceleration = new Vector2();
        maxSpeed = 9999;
        deceleration = 0;
        //autoAngle = false;
    }

    // velocity methods

    public void setVelocityXY(float vx, float vy) {  
    	speed.set(vx,vy);  
    }

    public void addVelocityXY(float vx, float vy) {  
    	speed.add(vx,vy); 
    }
    
    // set velocity from angle and speed
    public void setVelocityAS(float angleDeg, float velocity) {
    	speed.x = velocity * MathUtils.cosDeg(angleDeg);
    	speed.y = velocity * MathUtils.sinDeg(angleDeg);
    }

    public float getSpeed() {  
    	return speed.len(); 
    }

    public void setSpeed(float s) { 
    	speed.setLength(s);  
    }

    public void setMaxSpeed(float ms) {
    	maxSpeed = ms; 
    }
    
    public float getMotionAngle() {
    	return MathUtils.atan2(speed.y, speed.x) * MathUtils.radiansToDegrees; 
    }

    /*public void setAutoAngle(boolean b) {
    	autoAngle = b; 
    }*/

    public void setAccelerationXY(float ax, float ay) { 
    	acceleration.set(ax,ay); 
    }

    public void addAccelerationXY(float ax, float ay) {
    	acceleration.add(ax,ay);  
    }

    // set acceleration from angle and speed
    public void setAccelerationAS(float angleDeg, float speed) {
        acceleration.x = speed * MathUtils.cosDeg(angleDeg);
        acceleration.y = speed * MathUtils.sinDeg(angleDeg);
    }

    // add acceleration from angle and speed
    public void addAccelerationAS(float angleDeg, float speed){
        acceleration.add(speed * MathUtils.cosDeg(angleDeg), speed * MathUtils.sinDeg(angleDeg));
    }
    
    public void accelerateForward(float speed){ 
    	setAccelerationAS(getRotation(), speed); 
    }

    public void setDeceleration(float decelerationRate) { 
    	deceleration = decelerationRate; 
    }

    public void act(float deltaTime) {
        super.act(deltaTime);

        speed.add(acceleration.x * deltaTime, acceleration.y * deltaTime);
        
        if (acceleration.len() < 0.01) {
            float decelerateAmount = deceleration * deltaTime;
            if(getSpeed() < decelerateAmount) {
            	setSpeed(0);
            } else {
                setSpeed( getSpeed() - decelerateAmount );
            }
        }
        
        // cap at max speed
        if ( getSpeed() > maxSpeed ) {
            setSpeed(maxSpeed);
        } 

        // apply velocity
        moveBy( speed.x * deltaTime, speed.y * deltaTime );

        // rotate image when moving
       /* if (autoAngle && getSpeed() > 0.1 ) {
            setRotation( getMotionAngle() );
        }*/
    }
    
    /*public void copy(PhysicsActor original) {
        super.copy(original);
        this.speed     = new Vector2(original.speed);
        this.acceleration = new Vector2(original.acceleration);
        this.maxSpeed     = original.maxSpeed;
        this.deceleration = original.deceleration;
        //this.autoAngle    = original.autoAngle;
    }*/

    /*public PhysicsActor clone() {
        PhysicsActor newbie = new PhysicsActor();
        newbie.copy( this );
        return newbie;
    }*/
}