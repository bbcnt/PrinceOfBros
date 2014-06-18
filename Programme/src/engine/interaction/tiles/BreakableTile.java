/* ============================================================================
 * Filename   : BreakableTile.java
 * ============================================================================
 * Created on : 8 juin 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine.interaction.tiles;

import engine.interaction.GameObject;


/**
 * Represent a breakable tile.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class BreakableTile extends Tile {

	/**
	 * @param x
	 * @param y
	 */
   public BreakableTile(float x, float y) {
	   super(x, y);
   }

	@Override
   public boolean isObstacle() {
	   return true;
   }

	@Override
   public boolean isBreakable() {
	   return true;
   }

	@Override
   public boolean isMovable() {
	   return true;
   }
	
	@Override
   public boolean isHurting() {
	   return false;
   }
	
	@Override
	public String toString() {
		return "Breakable Tile";
	}

	@Override
   public void collision(GameObject o) {
	   
   }

	@Override
   public void harm(float amount) {	   
   }
}
