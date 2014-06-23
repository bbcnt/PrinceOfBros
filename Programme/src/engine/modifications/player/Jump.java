/* ============================================================================
 * Filename   : Jump.java
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
 * Command for a player's jump.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Jump extends PlayerAction {
	
	public Jump(int delta, int cooldown, Player player) {
		super(delta, cooldown, player);
	}

	@Override
   public void apply() {
		getPlayer().moveUp(getDelta() * getPlayer().getSpeed() * 3.0f);
   }

	@Override
   public void cancel() {
		getPlayer().moveUp(-getDelta() * getPlayer().getSpeed() * 3.0f);
   }
}
