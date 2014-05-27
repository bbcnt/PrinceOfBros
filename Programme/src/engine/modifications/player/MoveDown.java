package engine.modifications.player;

import engine.models.player.Player;

public class MoveDown extends PlayerAction {
	
	public MoveDown(int delta, Player player) {
		super(delta, 0, player);
	}

	@Override
   public void apply() {
	   getPlayer().moveDown(getDelta() * getPlayer().getSpeed());
   }

	@Override
   public void cancel() {
		getPlayer().moveDown(-getDelta() * getPlayer().getSpeed());
   }
}
