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
 * Represent a generic tile.
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
	 * Construct a generic tile.
	 * @param id The id of the tile in the TiledMap.
	 * @param x The horizontal position.
	 * @param y The vertical position
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
	
	/**
	 * Returns the TiledMap's id equivalent of this tile.
	 * @return The id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Return whether the tile should be considered like an obstacle.
	 * @return True if the tile is an obstacle
	 */
   public abstract boolean isObstacle();

	/**
	 * Return whether the tile should be breakable.
	 * @return True if the tile is breakable
	 */
   public abstract boolean isBreakable();

	/**
	 * Return whether the tile should be moving.
	 * @return True if the tile can move
	 */
   public abstract boolean isMovable();
   
	/**
	 * Return whether the tile should hurt when going trough.
	 * @return True if the tile is hurts.
	 */
   public abstract boolean isHurting();
}
