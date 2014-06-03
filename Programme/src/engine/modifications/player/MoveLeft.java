package engine.modifications.player;

import engine.models.player.Player;

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
