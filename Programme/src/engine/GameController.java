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

import java.util.LinkedList;

import engine.interaction.GameObject;
import engine.modifications.IModification;
import engine.modifications.IModificationTransaction;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class GameController {
	
	private ModificationManager modStack;
	
	private IModificationTransaction currentTransaction;
	
	// Tile map of the world.
	private LogicWorld map;
	
	// List of moving gameobjects
	private LinkedList<GameObject> movableGameObjects = new LinkedList<>();
	
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
	
	public void registerMap(LogicWorld map) {
		this.map = map;
	}
	
	/*---Singleton part--------------------------------------------------------*/
	
	public static GameController getInstance() {
		return Instance.instance;
	}
	
	private GameController() {
		modStack = new ModificationManager(300, 500);
		currentTransaction = ModificationManager.EMPTY_TRANSACTION;
	}
	
	private static class Instance {
		private static final GameController instance = new GameController();
	}

}
