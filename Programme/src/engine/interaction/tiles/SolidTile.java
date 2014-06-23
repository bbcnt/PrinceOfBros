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

import engine.interaction.GameObject;


/**
 * Represents a solid block.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class SolidTile extends Tile {

	/**
	 * Construct a solid tile.
	 * @param id The id of the tile in the TiledMap.
	 * @param x The horizontal position.
	 * @param y The vertical position
	 */
   public SolidTile(int id, float x, float y) {
	   super(id, x, y);
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
	
	@Override
   public boolean isHurting() {
	   return false;
   }
	
	@Override
	public String toString() {
		return "Solid Tile";
	}

	@Override
   public void collision(GameObject o) {}
}
