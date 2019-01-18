package Tools;

import Actors.StaticActor;

public class ScrollingBackground extends StaticActor{
	
	private float speed;
	private float resetX;
	private float resetY;
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getSpeed() {
		return speed;
	}
	
	public void setResetPointX(float resetX) {
		this.resetX = resetX;
	}
	public float getResetPointX() {
		return this.resetX;
	}
	
	public void setResetPointY(float resetY) {
		this.resetY = resetY;
	}
	public float getResetPointY() {
		return resetY;
	}
	
	
	public void scrollLeft() {
		if(getX() <= resetX) {
			setX(getOriginX());
		}else {
			setX(getX()-getSpeed());
		}
	}
	
	public void scrollRight() {
		if(getX() >= resetX) {
			setX(getOriginX());
		}else {
			setX(getX()+getSpeed());
		}
	}
	
	public void scrollUp() {
		if(getY() >= resetY) {
			setY(getOriginY());
		}else {
			setY(getY()+getSpeed());
		}
	}
	
	public void scrollDown() {
		if(getY() <= resetY) {
			setY(getOriginY());
		}else {
			setY(getY()-getSpeed());
		}
	}
}
