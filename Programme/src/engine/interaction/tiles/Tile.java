/* ============================================================================
 * Filename   : Tile.java
 * ============================================================================
 * Created on : 3 juin 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine.interaction.tiles;

import engine.GameController;
import engine.interaction.GameObject;

/**
 * Represent a tile.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public abstract class Tile extends GameObject {
	
	private static int hitBoxSize = 64;
	private int id;

	/**
	 * @param t
	 */
   public Tile(int id, float x, float y) {
	   super(x,y, hitBoxSize,hitBoxSize);
	   this.id = id;
   }
   
	/**
	 * @param f The new value of the horizontal position.
	 */
	public void setX(float f) {
		float old = getX();
		super.setX(f);
		GameController.getInstance().moveTile(old, getY(), getX(), getY());
	}
	/**
	 * @param f The new value of the vertical position.
	 */
	public void setY(float f) {
		float old = getY();
		super.setY(f);
		GameController.getInstance().moveTile(getX(), old, getX(), getY());
	}

	@Override
   public void harm(float amount) {
   }
	
	public int getId() {
		return id;
	}
	
   public abstract boolean isObstacle();

   public abstract boolean isBreakable();

   public abstract boolean isMovable();
   
   public abstract boolean isHurting();
}
