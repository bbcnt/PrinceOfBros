/* ============================================================================
 * Filename   : AttackRight.java
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
import engine.interaction.tiles.Tile;
import engine.models.player.Player;
import game.controlls.PlayerControl.Facing;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Attack extends PlayerAction {
	
	private Facing facing;
	
	public Attack(int delta, int cooldown, Player player, Facing facing) {
		super(delta, cooldown, player);
		this.facing = facing;
	}

	@Override
   public void apply() {
		int shift;
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
		
		   Tile t = (Tile) GameController.getInstance().getWorld().getTile(getPlayer().getX() + shift, getPlayer().getY());
		   if(t != null) {
		   	t.harm(50);
		   }
		
   }

	@Override
   public void cancel() {
	   // TODO Auto-generated method stub
	   
   }

}
