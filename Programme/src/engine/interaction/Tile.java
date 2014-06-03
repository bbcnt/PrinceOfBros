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
package engine.interaction;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Tile extends GameObject {
	
	private static int hitBoxSize = 64;

	/**
	 * @param t
	 */
   public Tile(float x, float y) {
	   super(x,y, hitBoxSize,hitBoxSize);
   }
	
	public boolean isObstacle() {
		return true;
	}

}
