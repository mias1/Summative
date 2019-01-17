package Tools;

import com.badlogic.gdx.graphics.Texture;

public class Rhino extends Enemy {
	
	@Override
	public void create() {
		Texture rhino = new Texture("Summative-Workspace/Summative/Character Sprites/enemy00_00.png");
		setOriginX(650);
		setOriginY(48);
		setTexture(rhino);
		setSpeed(3);
	}
	
	@Override
	public void move() {
		setX(getX() - getSpeed());
	}
	
}
