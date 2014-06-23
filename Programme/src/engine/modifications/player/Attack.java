/* ============================================================================
 * Filename   : Attack.java
 * ============================================================================
 * Created on : 27 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine.modifications.player;

import engine.GameController;
import engine.interaction.Player;
import engine.interaction.tiles.Tile;
import game.controls.PlayerControl.Facing;

/**
 * Command for a player's attack.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Attack extends PlayerAction {
	private int shift; // Horizontal shift.
	
	public Attack(int delta, int cooldown, Player player, Facing facing) {
		super(delta, cooldown, player);
		
	   switch(facing) {
		case Left:
			shift = -1;
			break;
		case Right:
			shift = 1;
			break;
		default:
			shift = 0;
			break;
	   }
	}

	@Override
   public void apply() {
	   Tile t = (Tile) GameController.getInstance().getWorld().getTile(getPlayer().getX() + shift, getPlayer().getY());
	   if(t != null) {			   
	   	t.harm(50);
	   }
	}

	@Override
   public void cancel() {
		// The mushrooms can't bring a block back to life :(
   }
}
