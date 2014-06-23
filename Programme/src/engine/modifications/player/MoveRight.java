/* ============================================================================
 * Filename   : MoveRight.java
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
 * Command for a player's right move.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class MoveRight extends PlayerAction {
	
	public MoveRight(int delta, Player player) {
		super(delta, 0, player);
	}

	@Override
   public void apply() {
		getPlayer().moveRight(getDelta() * getPlayer().getSpeed());
   }

	@Override
   public void cancel() {
		getPlayer().moveRight(-getDelta() * getPlayer().getSpeed());
   }
}
