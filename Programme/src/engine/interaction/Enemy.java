/* ============================================================================
 * Filename   : Enemy.java
 * ============================================================================
 * Created on : 27 mai 2014
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
public class Enemy extends GameObject {

	


	/**
	 * @param o
	 * @param t
	 */
   public Enemy(TypeObject t) {
	   super(TypeObject.Enemy);
   }

	@Override
   public void intersectWith(GameObject o) {
		
	}
	
}
