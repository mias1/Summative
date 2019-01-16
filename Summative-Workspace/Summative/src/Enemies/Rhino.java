package Enemies;

import com.badlogic.gdx.graphics.Texture;

import Tools.PhysicsActor;

public class Rhino extends PhysicsActor {
	
	private Texture rhino = new Texture("Summative-Workspace/Summative/Character Sprites/enemy00_00.png");
	
	public Rhino() {
		setTexture(rhino);
		setOrigin(650, 48);
		setSpeed(3);
	}
	
	public void move() {
		if(getX() <= -630) {
			setVisible(false);
		}else {
			setX(getX() - getSpeed());
		}
	}
	
}
