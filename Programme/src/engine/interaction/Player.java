/* ============================================================================
 * Filename   : Player.java
 * ============================================================================
 * Created on : 3 juin 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine.interaction;

import engine.animations.IAnimatedObject;
import engine.animations.IAnimatedState;

/**
 * 
 * Represent the logic player in the game.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Player extends GameObject implements IAnimatedObject {
	
	private final float maxLife = 100;
	private final float minLife = 0;
	private float currentLife;
	
	private float speed = .007f;
	
	private IAnimatedState state;
	
	
	public Player(float posX, float posY) {
		super(posX,posY, 64,64);
		setCurrentLife(maxLife);
	}
	
	/**
	 * Returns the speed of the player.
	 * @return The speed
	 */
	public float getSpeed() {
		return speed;
	}
	
	/**
	 * Returns the maximum amount of life the player can have.
	 * @return The maximum amount of life
	 */
	public float getMaxLife() {
	   return maxLife;
   }
	
	/**
	 * Returns the current amount of life of the player.
	 * @return The current amount of life
	 */
	public float getCurrentLife() {
	   return currentLife;
   }

	/**
	 * Return the minimum amount of life the player can have.
	 * @return The minimum amount of life
	 */
	public float getMinLife() {
	   return minLife;
   }
	
	/**
	 * Moves the player to the left.	
	 * @param value The difference of position
	 */
	public void moveLeft(float value) {
		moveX(-value);
	}
	
	/**
	 * Moves the player to the right.	
	 * @param value The difference of position
	 */
	public void moveRight(float value) {
		moveX(value);
	}
	
	/**
	 * Moves the player up.	
	 * @param value The difference of position
	 */
	public void moveUp(float value) {
		moveY(value);
	}
	
	/**
	 * Moves the player down.	
	 * @param value The difference of position
	 */
	public void moveDown(float value) {
		moveY(-value);
	}
	
	/**
	 * Sets the current life of the player.
	 * @param currentLife The new amount of life
	 */
	public void setCurrentLife(float currentLife) {
	   this.currentLife = currentLife;
   }
	
	/** 
	 * Sets the speed of the player.
	 * @param speed The new speed of the player
	 */
	public void setSpeed(float speed) {
		if (speed > 0) {
			this.speed = speed;
		}
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
	
	@Override
   public void collision(GameObject o) {
		// Nothing to collision with.
   }

	@Override
   public void harm(float amount) {
	   setCurrentLife(getCurrentLife() - amount);
   }
}
