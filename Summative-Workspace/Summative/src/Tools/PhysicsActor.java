package Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PhysicsActor extends AnimatedActor {
	
	private int speed;
	private float gravity;
	private float jumpSpeed;
	private int groundLevel;
	private String jumpState = "Ground";
	private float initialJumpSpeed;
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getSpeed() {
		return speed;
	}
	
	public void setGravity(float gravity) {
		this.gravity = gravity;
	}
	public float getGravity() {
		return gravity;
	}
	
	public void setJumpSpeed(float jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
		initialJumpSpeed = jumpSpeed;
	}
	public float getJumpSpeed() {
		return jumpSpeed;
	}
	
	public void setGroundLevel(int groundLevel) {
		this.groundLevel = groundLevel;
	}
	public int getGroundLevel() {
		return groundLevel;
	}
	
	private void manageJump() {
		if(jumpState.equalsIgnoreCase("Descending")) {
			if(getY() <= groundLevel) {
				jumpState = "Ground";
				setY(groundLevel);
				jumpSpeed = initialJumpSpeed;
			}else {
				setY(getY() - jumpSpeed);
				jumpSpeed += gravity;
			}
		}
		
		if(jumpState.equalsIgnoreCase("Ascending")) {
			setY(getY() + jumpSpeed);
			jumpSpeed -= gravity;
			
			if(jumpSpeed <= 0) {
				jumpState = "Descending";
				jumpSpeed = 0;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP) && jumpState.equalsIgnoreCase("Ground")) {
			jumpState = "Ascending";
			setY(getY() + jumpSpeed);
			jumpSpeed -= gravity;
		}
	}
	
	private void manageWalk() {
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if(!jumpState.equalsIgnoreCase("Ground")) {
				setX(getX() + (speed - (speed / 3)) * Gdx.graphics.getDeltaTime());
			}else {
				setX(getX() + speed * Gdx.graphics.getDeltaTime());
			}
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			if(!jumpState.equalsIgnoreCase("Ground")) {
				setX(getX() - (speed - (speed / 2)) * Gdx.graphics.getDeltaTime());
			}else {
				setX(getX() - speed * Gdx.graphics.getDeltaTime());
			}
		}
	}
	
	public void managePhysics() {
		manageWalk();
		manageJump();
	}
	
	public boolean isColliding(StaticActor actor, boolean colliding) {
		if(getRectangleBoundary().overlaps(actor.getRectangleBoundary())) {
			colliding = true;
		}else {
			colliding = false;
		}
		return colliding;
	}
}