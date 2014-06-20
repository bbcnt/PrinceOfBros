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
import engine.interaction.GameObject;
import engine.interaction.tiles.Tile;
import engine.models.player.Player;
import game.controlls.PlayerControl.Facing;
import game.states.Game;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Attack extends PlayerAction {
	private int tileId;
	private float x;
	private float y;
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
//		   System.out.println("Attacking tile: "+ getPlayer().getX() + shift + "," + getPlayer().getY());
//		   System.out.println("Tile : " + (t==null?"false":"true"));
		   if(t != null) {
			   // Saves the location of the tile and his id.
			   tileId = t.getId();
			   x = t.getX();
			   y = t.getY();
			   
		   	t.harm(50);
		   }
	}

	@Override
   public void cancel() {
//	   GameObject g = GameController.getInstance().getWorld().createTile(tileId, x , y);
//	   GameController.getInstance().addMovableObject(g);
   }

}
