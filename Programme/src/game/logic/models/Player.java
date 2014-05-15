package game.logic.models;

import game.Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

public class Player {
	
	// Logic
	
	private float posX;
	private float posY;
	
	// Graphic
	
	private Animation currentAnimation, movingUp, movingDown, movingLeft, movingRight;
	private float shiftX = posX + Game.SCREEN_WIDTH / 2;
	private float shiftY = posY + Game.SCREEN_HEIGHT / 2;
	
	public Player(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public Player(Animation movingUp, Animation movingDown,
	      		  Animation movingLeft, Animation movingRight) {
		this.movingUp = movingUp;
		this.movingDown = movingDown;
		this.movingLeft = movingLeft;
		this.movingRight = movingRight;
		
		currentAnimation = movingRight;
	}
	
	
	public Animation getAnimation() {
		return currentAnimation;
	}
	
	public float getPosX() {
		return posX;
	}
	
	public float getPosY() {
		return posY;
	}
	
	public void draw(Graphics g) {
		currentAnimation.draw(shiftX, shiftY);
	}
	
	
	/*---Modifiers-------------------------------------------------------------*/
	
	public void moveLeft(float value) {
		currentAnimation = movingLeft;
		posX += value;
	}
	
	public void moveRight(float value) {
		currentAnimation = movingRight;
		posX -= value;
	}
	
	public void moveUp(float value) {
		currentAnimation = movingUp;
		posY += value;
	} 
	public void moveDown(float value) {
		currentAnimation = movingDown;
		posY -= value;
	} 
	
}
