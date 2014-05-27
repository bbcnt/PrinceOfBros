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

import engine.models.player.Player;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Attack extends PlayerAction {
	
	public Attack(int delta, int cooldown, Player player) {
		super(delta, cooldown, player);
	}

	@Override
   public void apply() {
	   // TODO Auto-generated method stub
	   
   }

	@Override
   public void cancel() {
	   // TODO Auto-generated method stub
	   
   }

}
