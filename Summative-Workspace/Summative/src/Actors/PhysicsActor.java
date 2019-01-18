package Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 * Based off of the PhysicsActor class in the Ch5 CheesePleaseChapter5 project provided on D2L
 */
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
	
	
	/**
	 * Fully performs the jump action.
	 */
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
	
	
	/**
	 * Makes the actor walk.
	 */
	private void manageWalk() {
		if(getX() < 0) {
			setX(1);
		}
		if(getX() > 250) {
			setX(249);
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT) && (getX() >= 0 && getX() <= 250)) {
			if(!jumpState.equalsIgnoreCase("Ground")) {
				setX(getX() + (speed) * Gdx.graphics.getDeltaTime());
			}else {
				setX(getX() + speed * Gdx.graphics.getDeltaTime());
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT) && (getX() >= 0 && getX() <= 250)) {
			if(!jumpState.equalsIgnoreCase("Ground")) {
				setX(getX() - (speed) * Gdx.graphics.getDeltaTime());
			}else {
				setX(getX() - speed * Gdx.graphics.getDeltaTime());
			}
		}
	}
	
	/**
	 * Manages both the jump and walk actions.
	 */
	public void managePhysics() {
		manageWalk();
		manageJump();
	}
}