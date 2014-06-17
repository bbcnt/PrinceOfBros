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
package game.controlls.actions;

import game.controlls.PlayerControl;

/**
 * TODO
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
	
	public PlayerControl getPlayerControl() {
		return controls;
	}

}
