/* ============================================================================
 * Filename   : Charge.java
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
 * Macro command for a player's charge attack.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Charge extends PlayerAction {
	
	private float oldSpeed;
	
	public Charge(int delta, int cooldown, Player player) {
		super(delta, cooldown, player);
	}

	@Override
   public void apply() {
		oldSpeed = getPlayer().getSpeed();
		getPlayer().setSpeed(oldSpeed * 3.0f);
   }

	@Override
   public void cancel() {
		getPlayer().setSpeed(oldSpeed);
   }
}
