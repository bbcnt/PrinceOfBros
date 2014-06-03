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
	
	private static CoordsConverter instance;
	private Float tileSize;
	
	private CoordsConverter() {
		
	}
	
	public static void registerTileSize(float f) {
		if(instance == null)
			getInstance();
		instance.tileSize = f;
	}
	
	public static CoordsConverter getInstance() {
		if(instance == null)
			instance = new CoordsConverter();
		return instance;
	}
	
	public float toLogic(float f) {
		if(instance.tileSize == null) {
			throw new RuntimeException("The tile's size is not registered yet");
		}
		return f / tileSize;
	}
	
	public float toGraphic(float f) {
		if(instance.tileSize == null) {
			throw new RuntimeException("The tile's size is not registered yet");
		}
		return tileSize * f; 
	}
}
