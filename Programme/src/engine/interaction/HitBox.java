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
 * TODO
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
	
	/*----------------- SETTERS -----------------*/
	
	public void setX(float x) {	
		hitBox.x = x - shiftX;
	}
	
	public void setY(float y) {	
		hitBox.y = y - shiftY;
	}
	
	public void setWidth(float w) {
		float newShiftX = w / 2.0f;
		
		hitBox.x -= (newShiftX - shiftX);
		shiftX = newShiftX;
	}
	
	public void setHeight(float h) {
		float newShiftY = h / 2.0f;
		
		hitBox.y -= (newShiftY - shiftY);
		shiftY = newShiftY;
	}
	
	/*--------------------------------------------*/
	
	/*----------------- GETTERS ------------------*/
	
	public float getX() {
		return hitBox.x;
	}
	
	public float getY() {
		return hitBox.y;
	}
	
	public float getWidth() {
		return hitBox.width;
	}
	
	public float getHeight() {
		return hitBox.height;
	}
	
	/*--------------------------------------------*/

	public boolean intersectsWith(HitBox hitBox) {
		return true;
	}
}
