/* ============================================================================
 * Filename   : Mario.java
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
public class Mario extends GameObject {

	/**
	 * @param o
	 */
	public Mario(TypeObject t) {
		super(TypeObject.Mario);
	}

	@Override
	public void intersectWith(GameObject o) {
		if (this.isIntersect(o)) {
			if (o.getType() == TypeObject.Enemy) {
				// Mourir si touché par l'ennemi
			}

			if (o.getType() == TypeObject.Obstacle) {
				// Traverser l'obstacle
			}
		}

	}

}
