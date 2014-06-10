/* ============================================================================
 * Filename   : SolidTile.java
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


/**
 * Represents a solid block
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class SolidTile extends Tile {

	/**
	 * @param x
	 * @param y
	 */
   public SolidTile(float x, float y) {
	   super(x, y);
   }

	@Override
   public boolean isObstacle() {
	   return true;
   }

	@Override
   public boolean isBreakable() {
	   return false;
   }

	@Override
   public boolean isMovable() {
	   return false;
   }
}
