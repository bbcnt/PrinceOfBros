/* ============================================================================
 * Filename   : UserAction.java
 * ============================================================================
 * Created on : 17 juin 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package game.controls.actions;

import game.controls.PlayerControl;

/**
 * Abstract class for a user action.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public abstract class UserAction implements IUserAction {
	
	private PlayerControl controls;
	
	public UserAction(PlayerControl controls) {
		this.controls = controls;
	}
	
	/**
	 * Gets the controls of the player.
	 * @return The controls
	 */
	public PlayerControl getPlayerControl() {
		return controls;
	}

}
