package Managers;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.sun.xml.internal.bind.v2.runtime.reflect.Accessor.GetterOnlyReflection;

import Actors.PhysicsActor;
import Actors.StaticActor;

/**
 * Manages, Generates and Moves the enemies.
 * @author Jeremias
 */
public class EnemyManager {
	
	private static PhysicsActor[] enemies = new PhysicsActor[4];
	
	private static boolean ascending = true;
	
	/**
	 * Initializes all crucial attributes of the enemies, such as
	 * Animation, Origin Points, Physics and more.
	 */
	public static void initializeEnemies() {
		
		TextureRegion[] rhinoFrames = new TextureRegion[1];
        rhinoFrames[0] = new TextureRegion(new Texture("Summative-Workspace/Summative/Character Sprites/Enemy Sprites/Rhino.png"));
        Array<TextureRegion> rhinoFramesArray = new Array<TextureRegion>(rhinoFrames);
        Animation rhinoAnimation = new Animation(0.1f, rhinoFramesArray, Animation.PlayMode.LOOP);
		
		enemies[0] = new PhysicsActor();
        enemies[0].setOriginX(651);
        enemies[0].setOriginY(48);
        enemies[0].setAnimation(rhinoAnimation);
        enemies[0].setSpeed(5);
        enemies[0].setWidth(55);
        enemies[0].setHeight(51);
        enemies[0].setPosition(651, 48);
        enemies[0].setGravity(0.75f);
        enemies[0].setJumpSpeed(15);
        enemies[0].setGroundLevel(48);
        
        
        TextureRegion[] waspFrames = new TextureRegion[1];
        waspFrames[0] = new TextureRegion(new Texture("Summative-Workspace/Summative/Character Sprites/Enemy Sprites/Wasp.png"));
        Array<TextureRegion> waspFramesArray = new Array<TextureRegion>(waspFrames);
        Animation waspAnimation = new Animation(0.1f, waspFramesArray, Animation.PlayMode.LOOP);
        
        enemies[1] = new PhysicsActor();
        enemies[1].setOriginX(651);
        enemies[1].setOriginY(97);
        enemies[1].setAnimation(waspAnimation);
        enemies[1].setSpeed(9);
        enemies[1].setWidth(58);
        enemies[1].setHeight(38);
        enemies[1].setPosition(651, 97);
        enemies[1].setGravity(0.75f);
        enemies[1].setJumpSpeed(15);
        enemies[1].setGroundLevel(97);
        
        
        TextureRegion[] beeFrames = new TextureRegion[1];
        beeFrames[0] = new TextureRegion(new Texture("Summative-Workspace/Summative/Character Sprites/Enemy Sprites/Bee.png"));
        Array<TextureRegion> beeFramesArray = new Array<TextureRegion>(beeFrames);
        Animation beeAnimation = new Animation(0.1f, beeFramesArray, Animation.PlayMode.LOOP);
        
        enemies[2] = new PhysicsActor();
        enemies[2].setOriginX(651);
        enemies[2].setOriginY(55);
        enemies[2].setAnimation(beeAnimation);
        enemies[2].setSpeed(1.5f);
        enemies[2].setWidth(42);
        enemies[2].setHeight(50);
        enemies[2].setPosition(651, 55);
        enemies[2].setGravity(0.23f);
        enemies[2].setJumpSpeed(8);
        enemies[2].setGroundLevel(55);
        
        enemies[3] = new PhysicsActor();
        enemies[3].setOriginX(651);
        enemies[3].setOriginY(48);
        enemies[3].setAnimation(rhinoAnimation);
        enemies[3].setSpeed(2);
        enemies[3].setWidth(29);
        enemies[3].setHeight(37);
        enemies[3].setPosition(651, 48);
        enemies[3].setGravity(0.75f);
        enemies[3].setJumpSpeed(15);
        enemies[3].setGroundLevel(48);
		
	}
	
	/**
	 * Generates a certain type of enemy (based on probability) on to the screen and makes it move, then removes it once
	 * it is no longer on the screen, adding another enemy right after.
	 * @param stage
	 */
	public static void generateEnemies(Stage stage) {
		if(enemyOnScreen(-60, 650) == -1) {
			Random r = new Random();
			
			int index = r.nextInt(100) + 1;
			
			if(index <= 50) {
				enemies[0].setX(650);
				stage.addActor(enemies[0]);
			}
			if(index > 50 && index <= 85) {
				enemies[1].setX(650);
				stage.addActor(enemies[1]);
			}
			if(index > 85 && index <= 100) {
				enemies[2].setX(650);
				enemies[2].setY(enemies[2].getOriginY());
				enemies[2].setJumpSpeed(8);
				ascending = true;
				stage.addActor(enemies[2]);
			}
			/*if(index > 90 && index <= 100) {
				enemies[3].setX(650);
				stage.addActor(enemies[3]);
			}*/
		}else if(enemyOnScreen(-60, 650) == 0) {
			moveRhino();
			
			if(enemies[0].getX() < -60) {
				enemies[0].setX(651);
				enemies[0].addAction(Actions.removeActor());
			}
		}else if(enemyOnScreen(-60, 650) == 1) {
			moveWasp();
			
			if(enemies[1].getX() < -60) {
				enemies[1].setX(651);
				enemies[1].addAction(Actions.removeActor());
			}
		}else if(enemyOnScreen(-60, 650) == 2) {
			moveBee();
			
			if(enemies[2].getX() < -60) {
				enemies[2].setX(651);
				enemies[2].setY(55);
				enemies[2].setJumpSpeed(8);
				ascending = true;
				enemies[2].addAction(Actions.removeActor());
			}
		}/*else if(enemyOnScreen(-60, 650) == 3) {
			moveThree();
			
			if(enemies[3].getX() < -60) {
				enemies[3].setX(651);
				enemies[3].addAction(Actions.removeActor());
			}
		}*/
		
	}
	
	/**
	 * Checks which type of enemy is on the screen.
	 * @param leftLimit
	 * @param rightLimit
	 * @return 
	 */
	private static int enemyOnScreen(int leftLimit, int rightLimit) {
		for(int x = 0; x < 4; ++x) {
			if(enemies[x].getX() >= leftLimit && enemies[x].getX() <= rightLimit) {
				return x; 
			}else {
				continue;
			}
		}
		return -1;
	}

	/**
	 * Moves the Rhino enemy.
	 */
	private static void moveRhino() {
		enemies[0].setX(enemies[0].getX() - enemies[0].getSpeed());
	}
	
	/**
	 * Moves the Wasp enemy.
	 */
	private static void moveWasp() {
		enemies[1].setX(enemies[1].getX() - enemies[1].getSpeed());
	}
	
	/**
	 * Moves the Bee enemy
	 */
	private static void moveBee() {
		if(!ascending) {
			enemies[2].setX(enemies[2].getX() - enemies[2].getSpeed());
			enemies[2].setY(enemies[2].getY() - enemies[2].getJumpSpeed());
			enemies[2].setJumpSpeed(enemies[2].getJumpSpeed() - enemies[2].getGravity());
			if(enemies[2].getJumpSpeed() <= 0) {
				enemies[2].setJumpSpeed(8);
				ascending = true;
			}
		}else {
			enemies[2].setX(enemies[2].getX() - enemies[2].getSpeed());
			enemies[2].setY(enemies[2].getY() + enemies[2].getJumpSpeed());
			enemies[2].setJumpSpeed(enemies[2].getJumpSpeed() - enemies[2].getGravity());
			if(enemies[2].getJumpSpeed() <= 0) {
				enemies[2].setJumpSpeed(8);
				ascending = false;
			}
		}
	}
	
	/*private static void moveThree() {
		if(ascending) {
			enemies[3].setX(enemies[3].getX() - enemies[3].getSpeed());
			enemies[3].setY(enemies[3].getY() + enemies[3].getJumpSpeed());
			enemies[3].setJumpSpeed(enemies[3].getJumpSpeed() - enemies[3].getGravity());
			if(enemies[3].getJumpSpeed() <= 0) {
				ascending = false;
			}
		}else {
			enemies[3].setX(enemies[3].getX() - enemies[3].getSpeed());
			enemies[3].setY(enemies[3].getY() - enemies[3].getJumpSpeed());
			enemies[3].setJumpSpeed(enemies[3].getJumpSpeed() + enemies[3].getGravity());
			if(enemies[3].getJumpSpeed() >= 15) {
				ascending = true;
			}
		}
	}*/
	
	/**
	 * Checks if the main actor is colliding with an enemy.
	 * @param mainActor
	 * @return
	 */
	public static boolean isColliding(StaticActor mainActor) {
		for(int x = 0; x < 4; ++x) {
			if(enemies[x].getRectangleBoundary().overlaps(mainActor.getRectangleBoundary())) {
				return true; 
			}else {
				continue;
			}
		}
		return false;
	}
}
