/* ============================================================================
 * Filename   : Hitbox.java
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

import java.awt.geom.Rectangle2D;

/**
 * Represent the hitbox of a GameObject.
 * (We couldn't use it because of the deadline of the project)
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class HitBox {
	
	private Rectangle2D.Float hitBox;
	
	private float shiftX;
	private float shiftY;

	public HitBox(float x, float y, float width, float height){
		shiftX = width / 2.0f;
		shiftY = height / 2.0f;
		
		hitBox = new Rectangle2D.Float(x - shiftX, y - shiftY, width, height);
		
	}
	
	/**
	 * Sets the horizontal position of the hitbox.
	 * @param x The position
	 */
	public void setX(float x) {	
		hitBox.x = x - shiftX;
	}
	
	/**
	 * Sets the vertical position of the hitbox.
	 * @param y The position
	 */
	public void setY(float y) {	
		hitBox.y = y - shiftY;
	}
	
	/**
	 * Sets the width of the hitbox.
	 * @param w The width
	 */
	public void setWidth(float w) {
		float newShiftX = w / 2.0f;
		
		hitBox.x -= (newShiftX - shiftX);
		shiftX = newShiftX;
	}
	
	/**
	 * Sets the height of the hitbox.
	 * @param h The height
	 */
	public void setHeight(float h) {
		float newShiftY = h / 2.0f;
		
		hitBox.y -= (newShiftY - shiftY);
		shiftY = newShiftY;
	}
	
	/**
	 * Returns the horizontal position of the hitbox.
	 * @return The position
	 */
	public float getX() {
		return hitBox.x;
	}
	
	/**
	 * Returns the vertical position of the hitbox.
	 * @return The position
	 */
	public float getY() {
		return hitBox.y;
	}
	
	/**
	 * Return the width of the hitbox.
	 * @return The width
	 */
	public float getWidth() {
		return hitBox.width;
	}
	
	/**
	 * Return the height of the hitbox
	 * @return The height
	 */
	public float getHeight() {
		return hitBox.height;
	}
}
