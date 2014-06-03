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

	/**
	 * @param t
	 */
   public Tile() {
	   super(TypeObject.Tile);
	   // TODO Auto-generated constructor stub
   }

	@Override
	public void intersectWith(GameObject o) {
		// TODO Auto-generated method stub

	}
	
	public boolean isObstacle() {
		return true;
	}

}
