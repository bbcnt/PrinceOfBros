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

import java.awt.Rectangle;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public abstract class GameObject {

	private float x;
	private float y;
	private float width;
	private float height;
	
	public TypeObject type;
	
	public GameObject(TypeObject t) {
		type = t;
	}
	
	
	/**
	 * @return the type
	 */
	public TypeObject getType() {
		return type;
	}


	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(float width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}
	
	public boolean isIntersect(GameObject o) {
		Rectangle thisRectangle = new Rectangle((int)this.x, (int)this.y, (int)this.width, (int)this.height);
		Rectangle oRect = new Rectangle((int)o.getX(), (int)o.getY(), (int)o.getWidth(), (int)o.getHeight());
		
		return thisRectangle.intersects(oRect);
	}
	
	public abstract void intersectWith(GameObject o);
	
}
