/* ============================================================================
 * Filename   : Attack.java
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
 * Attack user action.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Attack extends UserAction {

	public Attack(PlayerControl control) {
		super(control);
	}

	@Override
   public boolean isAllowed() {
	   return true;
   }

	@Override
   public void execute() {
	   getPlayerControl().actionAttack();
   }
}
