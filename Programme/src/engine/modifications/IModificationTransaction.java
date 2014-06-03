/* ============================================================================
 * Filename   : IModificationTransaction.java
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
 * A transaction is a list of operation modifying data. Adding an operation to
 * the transaction enqueue the operation.
 * <p>
 * A call to {@link #commit()} will apply every transaction in the same order as
 * they were added.
 * 
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 * 
 */
public interface IModificationTransaction {
	
	/**
	 * Enqueue the given modification to the transaction.
	 * @param modif - the modification to add to the transaction.
	 * @param addToBackStack - if the modification has to be added to the back
	 * stack. If not added to the back stack, the modification will never be 
	 * reverted.
	 * @return The transaction with the newly added modification.
	 */
	public IModificationTransaction add(IModification modif, boolean addToBackStack);

	/**
	 * Apply every modification of this transaction. Apply them in the same order
	 * as they were added.
	 */
	public void commit();

	/**
	 * Revert every modification of this transaction. Revert them in the reverse
	 * order as they were added.
	 */
	public void revert();

}
