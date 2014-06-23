/* ============================================================================
 * Filename   : DoubleSpeed.java
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

import engine.interaction.Player;
import game.controls.PlayerControl;

/**
 * Charge user action.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Charge extends UserAction {
	
   public Charge(PlayerControl controls, Player player) {
	   super(controls);
   }

	@Override
   public boolean isAllowed() {
	   return true;
   }

	@Override
   public void execute() {
		getPlayerControl().actionCharge();
   }

}
