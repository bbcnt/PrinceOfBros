/* ============================================================================
 * Filename   : ICollisionWith.java
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
 * Defines the action todo when a game objet collision with a certain object.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 */
public interface ICollisionWith {
	public void collision(GameObject o);
}
