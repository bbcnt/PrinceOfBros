/* ============================================================================
 * Filename   : IModification.java
 * ============================================================================
 * Created on : 13 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */

package engine.modifications;

/**
 * Defines a reversible modification.
 * 
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public interface IModification {
	
	/**
	 * Apply the modification. 
	 */
	public void apply();
	
	/**
	 * Restore the state before the modification.
	 */
	public void cancel();
}
