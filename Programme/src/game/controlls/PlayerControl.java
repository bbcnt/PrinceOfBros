/* ============================================================================
 * Filename   : PlayerControl.java
 * ============================================================================
 * Created on : 19 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package game.controlls;

import engine.GameController;
import engine.LogicWorld;
import engine.animations.IAnimatedState;
import engine.graphics.player.GPlayer;
import engine.interaction.tiles.Tile;
import engine.models.player.Player;
import engine.modifications.IModification;
import engine.modifications.animations.AnimationChange;
import engine.modifications.player.Attack;
import engine.modifications.player.Jump;
import engine.modifications.player.MoveLeft;
import engine.modifications.player.MoveRight;
import engine.modifications.player.PlayerAction;

/**
 * TODO
 * Class used to manage the control of the player object. Check if action in use, 
 * in cooldown, ...
 * Manage action buffer (only one)
 * 
 * 
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class PlayerControl {
	
	private Player player;
	private int delta;
	
	private boolean movingRight, movingLeft;
	
	private IModification movementHor;
	private IModification movementVer;
	
	private enum Facing {
		Left, Right, Unknown
	}
	
	private enum ActionTypes {
		Idle, Attacking, Jumping;
	}
	
	private Facing facing = Facing.Unknown;
	private Facing oldFacing = Facing.Unknown;
	
	// TODO
	private int cooldownCounter;
	private PlayerAction currentAction = null;
	private ActionTypes currentActionType = ActionTypes.Idle;
	private boolean currentActionMultiExecute = false;
	private boolean currentActionAnimationSet = false; // Don't set animation twice or more...
	
	private PlayerAction nextAction = null;
	private ActionTypes nextActionType = ActionTypes.Idle;
	private boolean nextActionMultiExecute = false;
	
	private class ActionUpdate implements IModification {
		private int delta;
		
		int oldCooldownCounter;
		PlayerAction oldAction = null;
		ActionTypes oldActionType;
		boolean oldActionMultiExecute;
		boolean oldActionAnimationSet;
		
		ActionUpdate(int delta) {
			this.delta = delta;
		}

		@Override
      public void apply() {
			cooldownCounter += delta;
			
			if (cooldownCounter >= currentAction.getCooldown()) {
				
				// Copy values for cancel()
				oldCooldownCounter = cooldownCounter;
				oldAction = currentAction;
				oldActionType = currentActionType;
				oldActionMultiExecute = currentActionMultiExecute;
				oldActionAnimationSet = currentActionAnimationSet;
				
				// Swap to next animation
				cooldownCounter = 0;
				currentAction = nextAction;
				currentActionType = nextActionType;
				currentActionMultiExecute = nextActionMultiExecute;
				currentActionAnimationSet = false;
				nextAction = null;
				nextActionType = ActionTypes.Idle;
			}
      }

		@Override
      public void cancel() {
			cooldownCounter -= delta;
			
			// Restore previous state if stored
			if (oldAction != null) {
				// Next values
				nextAction = currentAction;
				nextActionType = currentActionType;
				nextActionMultiExecute = currentActionMultiExecute;
				
				// Then current
				cooldownCounter = oldCooldownCounter;
				currentAction = oldAction;
				currentActionType = oldActionType;
				currentActionMultiExecute = oldActionMultiExecute;
				currentActionAnimationSet = oldActionAnimationSet;
			}
      }
		
	}
	
	/*---Constructors----------------------------------------------------------*/
	
	public PlayerControl(Player player) {
		this.player = player;
	}
	
	private void update() {
		movingLeft = false;
		movingRight = false;
		
		movementHor = null;
		movementVer = null;
		
		oldFacing = facing;
		facing = Facing.Unknown;
		
		if (currentAction != null)
			GameController.getInstance().addModification(new ActionUpdate(delta));
	}
	
	public void beginUpdate(int delta) {
		this.delta = delta;
	}
	
	public void endUpdate() {
		
		// Assure correct facing if not set
		if (facing == Facing.Unknown)
			facing = oldFacing;

		// CHECK ICI POUR LES COLLISIONS.
		LogicWorld world = GameController.getInstance().getWorld();
		
		// Add horizontal movement
		if (movingRight && !movingLeft) {
			Tile tile = (Tile)world.getTile(player.getX() + 1, player.getY());
			if(tile == null || !tile.isObstacle() || player.getX() % 1 < 0.5) {
				movementHor = new MoveRight(delta, player);
			}
		}
			
		if (movingLeft && !movingRight) {
			Tile tile = (Tile)world.getTile(player.getX() - 1, player.getY());
			if(tile == null || !tile.isObstacle() || player.getX() % 1 > 0.5) {
				movementHor = new MoveLeft(delta, player);
			}
		}
		
		// Add action
		if (currentAction != null && (cooldownCounter == 0 || currentActionMultiExecute)) {
			GameController.getInstance().addModification(currentAction);
		}
		
		GameController.getInstance().addModification(movementHor);
		GameController.getInstance().addModification(movementVer);
		
		// Choose animation
		IAnimatedState oldState = player.getState();
		IAnimatedState newState = null;
		
		if (oldState.isStoppable() || oldState.getAnimation().isFinished()) {
			
			// Action first
			if (currentAction != null && !currentActionAnimationSet) {
				switch (currentActionType) {
				case Attacking:
					if (facing == Facing.Left && oldState != GPlayer.AnimationState.AttackingLeft)
						newState = GPlayer.AnimationState.AttackingLeft;
					else if (facing == Facing.Right && oldState != GPlayer.AnimationState.AttackingRight)
						newState = GPlayer.AnimationState.AttackingRight;
					break;
				default:
					break;
				}
				
				currentActionAnimationSet = true;
			}
			
			// Moving if no action
			if (newState == null) {
				if (movingLeft && !movingRight) {
					if (oldState != GPlayer.AnimationState.MovingLeft)
						newState = GPlayer.AnimationState.MovingLeft;
				}
				else if (movingRight && !movingLeft) {
					if (oldState != GPlayer.AnimationState.MovingRight)
						newState = GPlayer.AnimationState.MovingRight;
				}
				else {
					if (facing == Facing.Left && oldState != GPlayer.AnimationState.IdleLeft)
						newState = GPlayer.AnimationState.IdleLeft;
					else if (facing == Facing.Right && oldState != GPlayer.AnimationState.IdleRight)
						newState = GPlayer.AnimationState.IdleRight;
				}
			}
			
			// Then add the change if there is one.
			if (newState != null)
				GameController.getInstance().addModification(new AnimationChange(player, newState));
			
		}
		
		update();
	}
	
	public void moveRight() {
		movingRight = true;
		
		if (facing == Facing.Unknown)
			facing = Facing.Right;
	}
	
	public void moveLeft() {
		movingLeft = true;
		
		if (facing == Facing.Unknown)
			facing = Facing.Left;
	}
	
	public void actionJump() {
		pushAction(new Jump(delta, 200, player), ActionTypes.Jumping, true);
	}
	
	public void actionAttack() {
		pushAction(new Attack(delta, 1000, player), ActionTypes.Attacking, false);
	}
	
	
	private void pushAction(PlayerAction action, ActionTypes type, boolean multiExecute) {
		
		if (currentAction == null) {
			currentAction = action;
			currentActionType = type;
			currentActionMultiExecute = multiExecute;
		}
		else {
			nextAction = action;
			nextActionType = type;
			nextActionMultiExecute = multiExecute;
		}
	}

}
