/* ============================================================================
 * Filename   : PlayerAction.java
 * ============================================================================
 * Created on : 13 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */

package game.logic.actions;

import game.logic.modstack.IModification;

/**
 * Represent an action made by the player.
 * 
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public abstract class PlayerAction implements IModification {
	
	private int delta;
	
	/**
	 * Create the action, for the specified delta. This value is given by the
	 * Slick2d library.
	 * @param delta the value given by Slick2d
	 */
	public PlayerAction(int delta) {
		this.delta = delta;
	}
	
	/**
	 * Return the delta when the action was called.
	 * @return The delta when the action was called. 
	 */
	public int getDelta() {
		return delta;
	}

}
