
package engine.models.player;

import engine.animations.IAnimatedObject;
import engine.animations.IAnimatedState;
import engine.interaction.GameObject;

public class Player extends GameObject implements IAnimatedObject {
	
	private final float maxLife = 100;
	private final float minLife = 0;
	private float currentLife;
	
	private float speed = .007f;
	
	private IAnimatedState state;
	
	/*---Constructors----------------------------------------------------------*/
	
	public Player(float posX, float posY) {
		super(posX,posY, 64,64);
		setCurrentLife(maxLife);
	}
	
	/*---Getters---------------------------------------------------------------*/
	
	public float getSpeed() {
		return speed;
	}
	
	public float getMaxLife() {
	   return maxLife;
   }

	public float getCurrentLife() {
	   return currentLife;
   }

	public float getMinLife() {
	   return minLife;
   }
	
	/*---Modifiers-------------------------------------------------------------*/
	
	public void moveLeft(float value) {
		moveX(-value);
	}
	
	public void moveRight(float value) {
		moveX(value);
	}
	
	public void moveUp(float value) {
		moveY(value);
	}
	
	public void moveDown(float value) {
		moveY(-value);
	}
	
	public void setCurrentLife(float currentLife) {
	   this.currentLife = currentLife;
   }
	
	public void setSpeed(float speed) {
		if (speed > 0) {
			this.speed = speed;
		}
	}
	
	/*---Inherit from interfaces-----------------------------------------------*/

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
	
	@Override
   public void collision(GameObject o) {
		// Nothing for now
   }

	@Override
   public void harm(float amount) {
	   setCurrentLife(getCurrentLife() - amount);
   }
}
