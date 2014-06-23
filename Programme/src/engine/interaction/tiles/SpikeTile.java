/* ============================================================================
 * Filename   : SpikeTile.java
 * ============================================================================
 * Created on : 18 juin 2014
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
 * Represent a spike. It hurts mario.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class SpikeTile extends Tile {
	
	float lifePenality = 1.0f;

	/**
	 * Construct a spiked tile.
	 * @param id The id of the tile in the TiledMap.
	 * @param x The horizontal position.
	 * @param y The vertical position
	 */
   public SpikeTile(int id, float x, float y) {
	   super(id, x, y);
   }
	
	@Override
	public String toString() {
		return "Spiked Tile";
	}

	@Override
   public void collision(GameObject o) {
	   o.harm(lifePenality);
   }

	@Override
   public boolean isObstacle() {
	   return false;
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
	   return true;
   }
}
