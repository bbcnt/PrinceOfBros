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
		// J'ai modifié ça. Thomas
	   player.moveUp(1);
   }

	@Override
   public void cancel() {
		// J'ai modifié ça. Thomas
		player.moveUp(-1);
   }
}
