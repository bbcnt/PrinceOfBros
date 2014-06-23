/* ============================================================================
 * Filename   : MoveLeft.java
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

import engine.interaction.Player;

/**
 * Command for a player's left move.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class MoveLeft extends PlayerAction {
	
	public MoveLeft(int delta, Player player) {
		super(delta, 0, player);
	}

	@Override
   public void apply() {
	   getPlayer().moveLeft(getDelta() * getPlayer().getSpeed());
   }

	@Override
   public void cancel() {
		getPlayer().moveLeft(-getDelta() * getPlayer().getSpeed());
   }
}
