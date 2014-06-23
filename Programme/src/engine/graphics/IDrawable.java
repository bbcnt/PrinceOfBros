/* ============================================================================
 * Filename   : Drawable.java
 * ============================================================================
 * Created on : 16 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine.graphics;

import org.newdawn.slick.Graphics;

/**
 * Represent a drawable element in the game.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public interface IDrawable {
	
	/**
	 * Return the horizontal position of the element.
	 * @return The position
	 */
	public float getX();
	
	/**
	 * Return the vertical position of the element.
	 * @return The position
	 */
	public float getY();
	
	/**
	 * Return the width of the element.
	 * @return The width
	 */
	public float getWidth();
	
	/**
	 * Return the height of the element.
	 * @return The height
	 */
	public float getHeight();
	
	/**
	 * Draw method for the element.
	 * @param g Graphical context
	 */
	public void draw(Graphics g);
	
	/**
	 * Update method for the element.
	 * @param delta Time between 2 frames
	 */
	public void update(int delta);

}
