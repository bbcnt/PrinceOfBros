/* ============================================================================
 * Filename   : Engine.java
 * ============================================================================
 * Created on : 19 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine;

import engine.graphics.IDrawable;
import engine.modifications.IModification;
import engine.modifications.IModificationTransaction;
import engine.modifications.graphics.UpdateDrawableObject;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Engine {
	
	private ModificationManager modStack;
	
	private IModificationTransaction currentTransaction;
	
	/*---Getters---------------------------------------------------------------*/
	
	public void beginUpdate() {
		currentTransaction = modStack.begin();
	}
	
	public void commitUpdate() {
		currentTransaction.commit();
	}
	
	public void revertLastUpdate() {
		modStack.pop().revert();
	}
	
	/**
	 * Convenient method for a call to {@link #addModification(IModification, boolean)}
	 * with boolean value set to true.
	 * @param modif - the modification to add to the transaction.
	 */
	public void addModification(IModification modif) {
		this.addModification(modif, true);
	}
	
	/**
	 * Enqueue the given modification to the current transaction.
	 * @param modif - the modification to add to the transaction.
	 * @param addToBackStack - if the modification has to be added to the back 
	 * stack. If not added to the back stack, the modification will never be 
	 * reverted.
	 */
	public void addModification(IModification modif, boolean addToBackStack) {
		if (modif == null) return;
		
		currentTransaction.add(modif, addToBackStack);
	}
	
	public int getStackCapacityLogical() {
		return modStack.getCapacityLogical();
	}
	
	public int getStackCapacityPhysical() {
		return modStack.getCapacityPhysical();
	}
	
	public int getStackSize() {
		return modStack.getStackSize();
	}
	
	/*---Modifiers-------------------------------------------------------------*/
	
	public void init() {
		// Nothing for now
	}
	
	public void setStackCapacityLogical(int capacity) {
		modStack.setCapacityLogical(capacity);
	}
	
	public void setStackCapacityPhysical(int capacity) {
		modStack.setCapacityPhysical(capacity);
	}
	
	/*---Singleton part--------------------------------------------------------*/
	
	public static Engine getInstance() {
		return Instance.instance;
	}
	
	private Engine() {
		modStack = new ModificationManager(300, 500);
		currentTransaction = ModificationManager.EMPTY_TRANSACTION;
	}
	
	private static class Instance {
		private static final Engine instance = new Engine();
	}

}
