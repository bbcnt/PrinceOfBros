/* ============================================================================
 * Filename   : GameObject.java
 * ============================================================================
 * Created on : 27 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine.interaction;


/**
 * Represent a logic entity in the game. 
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public abstract class GameObject implements ICollisionWith {

	// Centered logical coordinates.
	private float x;
	private float y;
	
	private HitBox hitbox;
	
	public GameObject(float x, float y, int hitBoxWidth, int hitBoxHeight) {
		this.x = x;
		this.y = y;
		hitbox = new HitBox(x, y, hitBoxWidth, hitBoxHeight);
	}
	
	/**
	 * @return Return the value of the horizontal position.
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param f The new value of the horizontal position.
	 */
	public void setX(float f) {
		this.x = f;
		hitbox.setX(f);
	}

	/**
	 * @return Return the value of the vertical position.
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param f The new value of the vertical position.
	 */
	public void setY(float f) {
		this.y = f;
		hitbox.setY(f);
	}
	
	/**
	 * Offsets the horizontal position of the object.
	 * @param f The value of the offset.
	 */
	public void moveX(float f) {
		setX(getX() + f);
	}
	
	/**
	 * Offsets the vertical position of the object.
	 * @param f The value of the offset.
	 */
	public void moveY(float f) {
		setY(getY() + f);
	}
	
	/**
	 * Hurt the gameobject.
	 * @param amount The amout of life changed.
	 */
	public abstract void harm(float amount);
}
