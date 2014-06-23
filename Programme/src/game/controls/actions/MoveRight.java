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
package game.controls.actions;

import engine.GameController;
import engine.interaction.Player;
import game.controls.PlayerControl;

/**
 * Right move user action.
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
   
   /**
    * Returns the player.
    * @return The player
    */
   public Player getPlayer() {
   	return player;
   }

	@Override
   public boolean isAllowed() {
		return player.getX() < GameController.getInstance().getWorld().getWidth() - 10;
   }

	@Override
   public void execute() {
	   getPlayerControl().moveRight();
   }

}
