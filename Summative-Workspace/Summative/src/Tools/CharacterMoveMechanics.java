package Tools;

import com.badlogic.gdx.Gdx;

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
	public void moveRight() {
		if(actorX <= 100) {
			if(actorIsInAir()) {
				actorX += (speed - 60) * Gdx.graphics.getDeltaTime();
			}else {
				actorX += speed * Gdx.graphics.getDeltaTime();
			}
		}
	}
	
	/**
	 * Alters the X-Component of an actor, moving it to the left.
	 */
	public void moveLeft() {
		if(actorX >= 0) {
			if(actorIsInAir()) {
				actorX -= (speed - 60) * Gdx.graphics.getDeltaTime();
			}else {
				actorX -= speed * Gdx.graphics.getDeltaTime();
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
	public void initiateJump() {
		jumpState = "Ascending";
		actorY += jumpSpeed;
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
	private void ascend() {
		actorY += jumpSpeed;
		jumpSpeed -= gravity;
		
		if(jumpSpeed <= 0) {
			jumpState = "Descending";
			jumpSpeed = 0;
		}
	}
	
	/**
	 * Makes the actor descend.
	 */
	private void descend() {
		actorY -= jumpSpeed;
		jumpSpeed += gravity;
		
		if(jumpSpeed >= initialJumpSpeed) {
			jumpState = "Ground";
			actorY = groundLevel;
			jumpSpeed = initialJumpSpeed;
		}
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
