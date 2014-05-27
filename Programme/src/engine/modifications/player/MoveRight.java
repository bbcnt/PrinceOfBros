package engine.modifications.player;

import engine.models.player.Player;

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
