package engine.modifications.player;

import engine.models.player.Player;

public class MoveUp extends PlayerAction {
	
	private Player player;
	
	public MoveUp(int delta, Player player) {
		super(delta, 500);
		this.player = player;
	}

	@Override
   public void apply() {
	   player.moveUp(getDelta() * player.getSpeed() * 10);
   }

	@Override
   public void cancel() {
		player.moveUp(-getDelta() * player.getSpeed() * 10);
   }
}
