/* ============================================================================
 * Filename   : MoveRight.java
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
public class MoveRight extends UserAction {

	private Player player;
	
   public MoveRight(PlayerControl controls, Player player) {
	   super(controls);
	   this.player = player;
   }
   
   public Player getPlayer() {
   	return player;
   }

	@Override
   public boolean isAllowed() {
		return player.getX() < GameController.getInstance().getWorld().getWidth() - 15
				&& GameController.getInstance().getWorld().getTile(
				(int)Math.floor(player.getX() + 1), (int)Math.floor(player.getY()))
				== null;
   }

	@Override
   public void execute() {
	   getPlayerControl().moveRight();
   }

}
