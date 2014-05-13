package game.logic;

import game.Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

public class Player {
	
	private Animation currentAnimation, movingUp, movingDown, movingLeft, movingRight;
	private float playerPosX = 0;
	private float playerPosY = 0;
	private float shiftX = playerPosX + Game.SCREEN_WIDTH / 2;
	private float shiftY = playerPosY + Game.SCREEN_HEIGHT / 2;
	
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
		return playerPosX;
	}
	
	public float getPosY() {
		return playerPosY;
	}
	
	public void draw(Graphics g) {
		currentAnimation.draw(shiftX, shiftY);
	}
	
	
	/*---Modifiers-------------------------------------------------------------*/
	
	public void moveLeft(float value) {
		currentAnimation = movingLeft;
		playerPosX += value;
	}
	
	public void moveRight(float value) {
		currentAnimation = movingRight;
		playerPosX -= value;
	}
	
	public void moveUp(float value) {
		currentAnimation = movingUp;
		playerPosY += value;
	} 
	public void moveDown(float value) {
		currentAnimation = movingDown;
		playerPosY -= value;
	} 
	
}
