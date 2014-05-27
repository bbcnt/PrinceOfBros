/* ============================================================================
 * Filename   : IModificationManager.java
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
 * Tool for managing modifications.
 * 
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 * 
 */
public interface IModificationManager {

	/**
	 * Create and return a new transaction.
	 * @return The new transaction.
	 */
	public IModificationTransaction begin();

	/**
	 * Return the last transaction.
	 * @return The last transaction.
	 */
	public IModificationTransaction pop();

}
