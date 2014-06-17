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
import engine.interaction.tiles.Tile;
import engine.models.player.Player;
import engine.modifications.Gravity;
import engine.modifications.IModification;
import engine.modifications.IModificationTransaction;

/**
 * Handles the game's logic.
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
	public LogicWorld getWorld() {
		return map;
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
	
	public void beginUpdate() {
		currentTransaction = modStack.begin();
	}
	
	public void commitUpdate() {
		currentTransaction.commit();
	}
	
	public void revertLastUpdate() {
		modStack.pop().revert();
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
	
	public void registerMovableObjects(Player p) {
		movableGameObjects.add(p);
		for(GameObject t : map) {
			if(t != null && ((Tile)t).isMovable()) {
				
				movableGameObjects.add(t);
				System.out.println("x:" + t.getX() + " y: " + t.getY());
				break; // TODO Suppr
			}
				
		}
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
	
	/**
	 * Gravity check on movable objects.
	 */
	public void gravityUpdate() {
		final float force = -0.05f;
		int x,y;

		for(final GameObject g : movableGameObjects) {
			x = (int)Math.floor(g.getX());
			y = (int)Math.floor(g.getY() - 1);

			if(x < map.getWidth() && y < map.getHeight() && x >= 0 && y >= 0) {
				Tile tile = (Tile) map.getTile((int)Math.floor(g.getX()), (int)Math.floor(g.getY() - 1));
				if(!(tile != null && tile.isObstacle())){
					addModification(new Gravity(force, g));
				} 
			}
			
			// Place mario in the center of the tile.
			if(g.getY() % 1 > 0.5) {
				addModification(new Gravity(force, g));
			}
		}
	}
	
	public void moveTile(int oldX, int oldY, int newX, int newY) {
		map.moveTile(oldX, oldY, newX, newY);
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
