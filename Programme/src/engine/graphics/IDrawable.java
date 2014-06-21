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
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public interface IDrawable {
	
	public float getX();
	public float getY();
	public float getWidth();
	public float getHeight();
	
	public void draw(Graphics g);
	
	public void update(int delta);

}
