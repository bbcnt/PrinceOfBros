
package engine.models.player;

import engine.animations.IAnimatedObject;
import engine.animations.IAnimatedState;

public class Player implements IAnimatedObject {
	
	private float posX;
	private float posY;
	private final float maxLife = 100;
	private final float minLife = 0;
	private float currentLife;
	
	private float speed = .003f;
	
	private IAnimatedState state;
	
	/*---Constructors----------------------------------------------------------*/
	
	public Player(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
		setCurrentLife(100);
	}
	
	/*---Getters---------------------------------------------------------------*/
	
	public float getPosX() {
		return posX;
	}
	
	public float getPosY() {
		return posY;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	/*---Modifiers-------------------------------------------------------------*/
	
	public void moveLeft(float value) {
		posX -= value;
	}
	
	public void moveRight(float value) {
		posX += value;
	}
	
	public void moveUp(float value) {
		posY += value;
	}
	
	public void moveDown(float value) {
		posY -= value;
	}
	
	/*---Inherit from interfaces-----------------------------------------------*/
	
	public void initIdleStates(IAnimatedState[] states) {
		// TODO
	}

	@Override
   public boolean setAnimation(IAnimatedState state) {
		
		if (state == null) return false;
		
		this.state = state;
		
		return true;
   }
	
	@Override
	public IAnimatedState getState() {
		return state;
	}

	public float getMaxLife() {
	   return maxLife;
   }

	public float getCurrentLife() {
	   return currentLife;
   }

	public void setCurrentLife(float currentLife) {
	   this.currentLife = currentLife;
   }

	public float getMinLife() {
	   return minLife;
   }
}
