/* ============================================================================
 * Filename   : CoordsConverter.java
 * ============================================================================
 * Created on : 1 juin 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine;

/**
 * Convert one type of coordinate to another. We use two systems of coordinates for the game.
 * The first one (logic coordinate system (LCS)) is used to handle the logic part of the game, like
 * the position of the player and other entities.
 * The second one (graphical coordinate system (GCS)) is used to specify the coordinates of any displayed element.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class CoordsConverter {
	
	private Float tileSize;
	
	/**
	 * Register the tile's size.
	 * @param f The size
	 */
	public static void registerTileSize(float f) {
		getInstance().tileSize = f;
	}
	
	/**
	 * Convert a GCS point to LCS point.
	 * @param f The GCS point
	 * @return The corresponding LCS point.
	 */
	public float toLogic(float f) {
		if(getInstance().tileSize == null) {
			throw new RuntimeException("The tile's size is not registered yet");
		}
		return f / tileSize;
	}
	
	/**
	 * Convert a LCS point to GCS point.
	 * @param f The LCS point
	 * @return The corresponding GCS point.
	 */
	public float toGraphic(float f) {
		if(getInstance().tileSize == null) {
			throw new RuntimeException("The tile's size is not registered yet");
		}
		return tileSize * f; 
	}
	
	/*---Singleton part--------------------------------------------------------*/
	
	/**
	 * Returns the singleton.
	 * @return The instance.
	 */
	public static CoordsConverter getInstance() {
		return Instance.instance;
	}
	
	private CoordsConverter() { }
	
	/**
	 * This private class allows the singleton to be thread safe.
	 * @author Brito Carvalho Bruno
	 * @author Decorvet Grégoire
	 * @author Ngo Quang Dung
	 * @author Schweizer Thomas
	 *
	 */
	private static class Instance {
		private static final CoordsConverter instance = new CoordsConverter();
	}
}
