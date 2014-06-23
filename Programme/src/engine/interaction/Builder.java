/* ============================================================================
 * Filename   : Builder.java
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

import engine.interaction.tiles.BreakableTile;
import engine.interaction.tiles.SolidTile;
import engine.interaction.tiles.SpikeTile;

/**
 * Create Tiles objects from a TiledMap.
 * Theses tiled will be used for the logic of the game.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Builder {
	
	public enum TileType {Breakable, Solid, Spike};
	
	/**
	 * Creates objects from TiledMap'ids.
	 * @return
	 */
	public GameObject createGameObject(int id, float x, float y, TileType type) {
		switch (type) {
		case Breakable:
			return new BreakableTile(id, x,y);
			
		case Solid:
			return new SolidTile(id, x,y);
			
		case Spike:
			return new SpikeTile(id, x, y);

		default:
			throw new RuntimeException("Something terrible happened!");
		}
	}
	
   /*---Singleton part--------------------------------------------------------*/
	
	/**
	 * Returns the singleton.
	 * @return The instance.
	 */
	public static Builder getInstance() {
		return Instance.instance;
	}
	
	private Builder() { }
	
	/**
	 * This private class allows the singleton to be thread safe.
	 * @author Brito Carvalho Bruno
	 * @author Decorvet Grégoire
	 * @author Ngo Quang Dung
	 * @author Schweizer Thomas
	 *
	 */
	private static class Instance {
		private static final Builder instance = new Builder();
	}
}
