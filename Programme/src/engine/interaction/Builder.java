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
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Builder {
	
	public enum TileType {Breakable, Solid, Spike};
	
	/**
	 * For now, our builder can create only Tile.
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
	
	public static Builder getInstance() {
		return Instance.instance;
	}
	
	private Builder() { }
	
	private static class Instance {
		private static final Builder instance = new Builder();
	}

}
