package engine.modifications.player;

import engine.models.player.Player;

public class MoveDown extends PlayerAction {
	
	private Player player;
	
	public MoveDown(int delta, Player player) {
		super(delta, 0);
		this.player = player;
	}

	@Override
   public void apply() {
	   player.moveDown(getDelta() * player.getSpeed());
   }

	@Override
   public void cancel() {
		player.moveDown(-getDelta() * player.getSpeed());
   }
}
