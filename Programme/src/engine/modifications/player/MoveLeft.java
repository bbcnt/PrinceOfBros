package engine.modifications.player;

import engine.models.player.Player;

public class MoveLeft extends PlayerAction {
	
	private Player player;
	
	public MoveLeft(int delta, Player player) {
		super(delta, 0);
		this.player = player;
	}

	@Override
   public void apply() {
	   player.moveLeft(getDelta() * player.getSpeed());
   }

	@Override
   public void cancel() {
		player.moveLeft(-getDelta() * player.getSpeed());
   }
}
