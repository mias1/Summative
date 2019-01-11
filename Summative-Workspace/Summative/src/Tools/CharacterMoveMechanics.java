package Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * @author User
 *
 */
public class CharacterMoveMechanics {

	public int actorX;
	public int actorY;
	public float speed;
	private float initialJumpSpeed;
	public float jumpSpeed;
	public float gravity;
	public String jumpState;
	public int groundLevel;
	
	/**
	 * Defines all the physics components.
	 * @param actorX
	 * @param actorY
	 * @param speed
	 * @param jumpSpeed
	 * @param gravity
	 * @param jumpState
	 * @param groundLevel
	 */
	public void setMechanics(int actorX, int actorY, float speed, float jumpSpeed, float gravity, String jumpState, int groundLevel) {
		this.actorX = actorX;
		this.actorY = actorY;
		this.speed = speed;
		this.jumpSpeed = jumpSpeed;
		initialJumpSpeed = jumpSpeed;
		this.gravity = gravity;
		this.jumpState = jumpState;
		this.groundLevel = groundLevel;
	}
	
	//---------- Run Mechanics ----------//
	
	/**
	 * Alters the X-Component of an actor, moving it to the right.
	 */
	public void moveRight(Actor actor) {
		if(actor.getX() <= 100) {
			if(actorIsInAir()) {
				actor.setX(actor.getX() + (speed - 60) * Gdx.graphics.getDeltaTime());
			}else {
				actor.setX(actor.getX() + speed * Gdx.graphics.getDeltaTime());
			}
		}
	}
	
	/**
	 * Alters the X-Component of an actor, moving it to the left.
	 */
	public void moveLeft(Actor actor) {
		if(actor.getX() >= 0) {
			if(actorIsInAir()) {
				actor.setX(actor.getX() - (speed - 60) * Gdx.graphics.getDeltaTime());
			}else {
				actor.setX(actor.getX() - speed * Gdx.graphics.getDeltaTime());
			}
		}
	}
	
	//-----------------------------------//
	
	//-------- Jumping Mechanics --------//
	
	/**
	 * Verifies if the actor is in the air, if it is, the process of ascending or descending continues.
	 */
	public void verifyIfJumping(){
		if(actorIsInAir()) {
			verifyStateOfJump();
		}
	}
	
	/*public void jump() {
		if(!actorIsInAir()) {
			initiateJump();
		}else {
			continueJump();
		}
	}*/
	
	/**
	 * Starts the jump process of an actor.
	 */
	public void initiateJump(Actor actor) {
		jumpState = "Ascending";
		actor.setY(actor.getY() + jumpSpeed);
		jumpSpeed -= gravity;
	}
	
	/**
	 * Checks if the actor is ascending or descending then continues either of the processes.
	 */
	private void verifyStateOfJump() {
		if(jumpState.equalsIgnoreCase("Ascending")) {
			ascend();
		} else if(jumpState.equalsIgnoreCase("Descending")) {
			descend();
		}
	}
	
	/**
	 * Makes the actor ascend.
	 */
	private void ascend(Actor actor) {
		actor.setY(actor.getY() + jumpSpeed);
		jumpSpeed -= gravity;
		
		if(jumpSpeed <= 0) {
			jumpState = "Descending";
			jumpSpeed = 0;
		}
	}
	
	/**
	 * Makes the actor descend.
	 */
	private void descend(Actor actor) {
		actor.setY(actor.getY() - jumpSpeed);
		jumpSpeed += gravity;
		
		if(jumpSpeed >= initialJumpSpeed + 0.5) { // The 0.5 is to make sure the character touches the ground before it can jump again
			jumpState = "Ground";
			actor.setY(groundLevel);
			jumpSpeed = initialJumpSpeed;
		}
	}
	
	//-----------------------------------//
	
	//--------- Slide Mechanics ---------//
	
	public void slide() {
		
	}
	
	public void groundPound() {
		
	}
	
	//-----------------------------------//
	
	//------------ Verifiers ------------//
	
	/**
	 * Verifies if the actor is in the air.
	 * @return true if the actor is in the air or false if it isn't.
	 */
	private boolean actorIsInAir() {
		if(jumpState.equalsIgnoreCase("Ground")) {
			return false;
		}else {
			return true;
		}
	}
	
	//-----------------------------------//
	
}
