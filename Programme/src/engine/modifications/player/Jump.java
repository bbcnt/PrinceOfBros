package engine.modifications.player;

import engine.models.player.Player;

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
