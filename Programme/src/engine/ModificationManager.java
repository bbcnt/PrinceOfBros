/* ============================================================================
 * Filename   : ModificationManager.java
 * ============================================================================
 * Created on : 13 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */

package engine;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import engine.modifications.IModification;
import engine.modifications.IModificationManager;
import engine.modifications.IModificationTransaction;

/**
 * Tool for managing modifications.
 * 
 * <p>
 * <b>Usage:</b>
 * <p>
 * Setup the manager by passing the capacities to the constructor :
 * <ul>
 * <li>Logical capacity : is the maximum number of stored transaction in the
 * buffer</li>
 * <li>Physial capacity : is the size of the buffer. Has to be greater or equals
 * to the logical capacity.</li>
 * </ul>
 * <p>
 * <p>
 * Choosing a physical capacity greater than the logical one allow the user to
 * resize the logical capacity in no time.
 * <p>
 * Modifying the logical capacity over the physical capacity or modifying the
 * physical capacity implies a resize of the buffer. This action cost more time.
 * <p>
 * When done, a call to {@link #begin()} provide a transaction. Add all the
 * wanted modification to this transaction, then {@link Transaction#commit()}.
 * <p>
 * To revert the last transaction, simply call {@link #pop()}, then
 * {@link Transaction#revert()} Calls to {@link #pop()} will each time remove
 * the last transaction from the buffer.
 * 
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 * 
 */
public class ModificationManager implements IModificationManager {
	
	/**
	 * The transaction used as returned value when a call to {@link #pop()} is
	 * done and the modification stack is empty. 
	 */
	public static final EmptyTransaction EMPTY_TRANSACTION = new EmptyTransaction();

	private int userLogicalCapacity;
	private int userPhysicalCapacity;

	private int realPhysicalCapacity;

	private Transaction[] transactions;

	private int cursorNext;
	private int transactionCounter;

	/*---Constructors and init-------------------------------------------------*/

	/**
	 * Create a manager
	 * 
	 * @param logicalCapacity
	 * @param physicalCapacity
	 */
	public ModificationManager(int logicalCapacity, int physicalCapacity) {
		if (logicalCapacity < 0 || physicalCapacity < 0) {
			throw new RuntimeException("Capacities have to be positives");
		}

		// Ensure enough physical capacity
		if (logicalCapacity > physicalCapacity) {
			physicalCapacity = logicalCapacity;
		}

		userLogicalCapacity = logicalCapacity;
		userPhysicalCapacity = physicalCapacity;

		transactionCounter = 0;

		setupPhysicalContainer();
	}

	/**
	 * Setup the real capacity.
	 * <p>
	 * Has to be 1 more than the user choice. When the buffer is full, a new call
	 * to "begin()" should not erase the oldest transaction. One more transaction
	 * allow the calls to "begin()" and keeps the state of the buffer.
	 */
	private void setupRealCapacity() {
		realPhysicalCapacity = userPhysicalCapacity + 1;
	}

	/**
	 * Setup the container in order to be able to store the elements
	 * corresponding to the physical capacity of the Modification manager.
	 * 
	 * Keep the maximum number of existing transactions.
	 */
	private void setupPhysicalContainer() {
		int oldCursor = cursorNext;

		setupRealCapacity();

		Transaction[] newTransactions = new Transaction[realPhysicalCapacity];

		// Retrieve the stored transactions, fill from the end.
		int movingElements = Math.min(transactionCounter, newTransactions.length);
		for (int i = 0; i < movingElements; i++) {
			oldCursor--;
			if (oldCursor < 0)
				oldCursor = transactions.length;
			newTransactions[newTransactions.length - i] = transactions[oldCursor];
		}

		// Init other values
		for (int i = 0; i < newTransactions.length; i++) {
			if (newTransactions[i] == null) {
				newTransactions[i] = new Transaction();
			}
		}

		// Position the cursor on the next free element which is the first element
		// of the array (filling the array from the end)
		cursorNext = 0;

		transactions = newTransactions;
	}

	/**
	 * Move the cursor to the next element.
	 */
	private void cursorNext() {
		cursorNext++;

		// End reached, restart
		if (cursorNext >= realPhysicalCapacity) {
			cursorNext = 0;
		}
	}

	/**
	 * Move the cursor to the previous element.
	 */
	private void cursorPrevious() {
		cursorNext--;

		// Start reached, go to the end
		if (cursorNext < 0) {
			cursorNext = realPhysicalCapacity - 1;
		}
	}

	/**
	 * Call to update the counter after a commit.
	 */
	private void updateCommit() {
		if (transactionCounter < userLogicalCapacity) {
			transactionCounter++;
		}
	}

	/**
	 * Call to update the counter after a revert.
	 */
	private void updateRevert() {
		if (transactionCounter > 0) {
			transactionCounter--;
		}
	}

	/*---Getters---------------------------------------------------------------*/

	/**
	 * Return the logical capacity, which is the maximum stored transaction
	 * perceived by the user.
	 * 
	 * @return The logical capacity.
	 */
	public int getCapacityLogical() {
		return userLogicalCapacity;
	}

	/**
	 * Return the physical capacity, which is the number of transaction the
	 * buffer is able to store at a time.
	 * 
	 * @return The physical capacity.
	 */
	public int getCapacityPhysical() {
		return userPhysicalCapacity;
	}

	/**
	 * Return the number of stored transaction, available for a call to
	 * {@link #pop()}.
	 * 
	 * @return The number of stored transaction.
	 */
	public int getStackSize() {
		return transactionCounter;
	}

	/*---Modifiers-------------------------------------------------------------*/

	/**
	 * Set the logical capacity. If the value is greater than the physical
	 * capacity the buffer will be rebuilt, keeping the stored transaction.
	 * <p>
	 * A negative value will result in no change.
	 * 
	 * @param capacity - the new capacity.
	 */
	public void setCapacityLogical(int capacity) {
		if (capacity < 0)
			return;

		userLogicalCapacity = capacity;

		// Update physical only if need more space
		if (userLogicalCapacity > userPhysicalCapacity) {
			userPhysicalCapacity = userLogicalCapacity;
			setupPhysicalContainer();
		}
		else {
			setupRealCapacity();
		}
	}

	/**
	 * Set the physical capacity corresponding to the real memory used by the
	 * buffer. If the value is smaller than the logical capacity, the logical
	 * value will be set to the given value.
	 * <p>
	 * A negative value will result in no change.
	 * 
	 * @param capacity - the new capacity.
	 */
	public void setCapacityPhysical(int capacity) {
		if (capacity < 0)
			return;

		userPhysicalCapacity = capacity;

		if (userLogicalCapacity > userPhysicalCapacity) {
			userLogicalCapacity = userPhysicalCapacity;
		}

		setupPhysicalContainer();
	}

	@Override
	public IModificationTransaction begin() {
		transactions[cursorNext].clear();
		return transactions[cursorNext];
	}

	@Override
	public IModificationTransaction pop() {
		if (transactionCounter > 0) {
			cursorPrevious();
			return transactions[cursorNext];
		}
		else {
			return EMPTY_TRANSACTION;
		}
	}
	
	/**
	 * A fake transaction that does nothing.
	 * 
	 * @author Brito Carvalho Bruno
	 * @author Decorvet Grégoire
	 * @author Ngo Quang Dung
	 * @author Schweizer Thomas
	 *
	 */
	public static class EmptyTransaction implements IModificationTransaction {

		@Override
      public IModificationTransaction add(IModification modif, boolean addToBackStack) {
	      // Do nothing
	      return this;
      }

		@Override
      public void commit() {
	      // Do nothing
      }

		@Override
      public void revert() {
			// Do nothing
      }
		
	}

	/**
	 * A transaction is a list of operation modifying data. Adding an operation
	 * to the transaction enqueue the operation. A call to {@link #commit()} will
	 * apply every transaction in the same order as they were added.
	 * 
	 * @author Brito Carvalho Bruno
	 * @author Decorvet Grégoire
	 * @author Ngo Quang Dung
	 * @author Schweizer Thomas
	 * 
	 */
	public class Transaction implements IModificationTransaction {

		private List<IModification> mods = new LinkedList<>();
		private List<IModification> noBackStackMods = new LinkedList<>();
		private boolean commited = false;

		/**
		 * Clear the transaction, reseting the state to empty transaction.
		 */
		void clear() {
			mods.clear();
			noBackStackMods.clear();
			commited = false;
		}

		@Override
		public IModificationTransaction add(IModification modif, boolean addToBackStack) {
			
			if (addToBackStack)
				mods.add(modif);
			else
				noBackStackMods.add(modif);
			
			return this;
		}

		@Override
		public void commit() {
			if (!commited) {
				ModificationManager.this.cursorNext();

				for (IModification m : mods) {
					m.apply();
				}
				
				for (IModification m : noBackStackMods) {
					m.apply();
				}
				
				// No more useful => clear
				noBackStackMods.clear();

				commited = true;
				ModificationManager.this.updateCommit();
			}
		}

		@Override
		public void revert() {
			// Warning : has to execute from last element
			if (commited) {
				ListIterator<IModification> it = mods.listIterator(mods.size());
				IModification modif;
				while (it.hasPrevious()) {
					modif = it.previous();
					modif.cancel();
				}
				clear();
				ModificationManager.this.updateRevert();
			}
		}

	}
}
