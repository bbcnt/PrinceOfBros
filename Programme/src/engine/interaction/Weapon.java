/* ============================================================================
 * Filename   : Weapon.java
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
public class Weapon extends GameObject {

	/**
	 * @param t
	 */
	public Weapon(TypeObject t) {
		super(TypeObject.Weapon);
	}

	@Override
	public void intersectWith(GameObject o) {
		if (this.isIntersect(o)) {
			if (o.getType() == TypeObject.Enemy) {
				// Tuer l'ennemi
			}
		}

	}

}
