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

package engine.modifications.player;

import engine.models.player.Player;
import engine.modifications.IModification;
import engine.modifications.SynchronizedModification;


/**
 * Represent an action made by the player.
 * 
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public abstract class PlayerAction extends SynchronizedModification implements IModification {
	
	private int cooldown;
	private Player player;
	
	/**
	 * Create the action, for the specified delta. This value is given by the
	 * Slick2d library.
	 * @param delta - the value given by Slick2d
	 * @param cooldown - the cooldown in millis for the action. 
	 */
	public PlayerAction(int delta, int cooldown, Player player) {
		super(delta);
		this.cooldown = cooldown;
		this.player = player;
	}
	
	/**
	 * Return the cooldown in millis for the action.
	 * @return The cooldown in millis.
	 */
	public int getCooldown() {
		return cooldown;
	}
	
	/**
	 * Return if the action has a cooldown or not.
	 * @return True if the action has a cooldown, false otherwise.
	 */
	public boolean hasCooldown() {
		return cooldown > 0;
	}
	
	/**
	 * Return the player object concerned by this action.
	 * @return The player concerned by this action.
	 */
	public Player getPlayer() {
		return player;
	}

}
