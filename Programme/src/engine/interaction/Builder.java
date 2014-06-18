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
	
	private static Builder instance;
	
	public enum TileType {Breakable, Solid, Spike};
	
	private Builder(){}
	
	public static Builder getInstance() {
		if(instance == null)
			instance = new Builder();
		return instance;
	}
	
	/**
	 * For now, our builder can create only Tile.
	 * @return
	 */
	public GameObject createGameObject(float x, float y, TileType type) {
		switch (type) {
		case Breakable:
			return new BreakableTile(x,y);
			
		case Solid:
			return new SolidTile(x,y);
			
		case Spike:
			return new SpikeTile(x, y);

		default:
			throw new RuntimeException("Something terrible happened!");
		}
	}

}
