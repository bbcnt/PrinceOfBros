/* ============================================================================
 * Filename   : Jump.java
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

import engine.GameController;
import engine.models.player.Player;
import game.controlls.PlayerControl;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Jump extends UserAction {
	
	private Player player;
	
	public Jump(PlayerControl control, Player player) {
		super(control);
		this.player = player;
	}

	@Override
   public boolean isAllowed() {
		return GameController.getInstance().getWorld().getTile(
				(int)Math.floor(player.getX()), (int)Math.floor(player.getY()- 1))
				!= null;
   }

	@Override
   public void execute() {
	   getPlayerControl().actionJump();
   }

}
