package engine.modifications.player;

import engine.models.player.Player;

public class MoveUp extends PlayerAction {
	
	public MoveUp(int delta, Player player) {
		super(delta, 500, player);
	}

	@Override
   public void apply() {
		getPlayer().moveUp(getDelta() * getPlayer().getSpeed() * 10);
   }

	@Override
   public void cancel() {
		getPlayer().moveUp(-getDelta() * getPlayer().getSpeed() * 10);
   }
}
