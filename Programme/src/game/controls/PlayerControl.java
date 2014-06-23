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
package game.controls;

import engine.GameController;
import engine.LogicWorld;
import engine.animations.IAnimatedState;
import engine.graphics.player.GPlayer;
import engine.interaction.Player;
import engine.interaction.tiles.Tile;
import engine.modifications.IModification;
import engine.modifications.animations.AnimationChange;
import engine.modifications.player.Attack;
import engine.modifications.player.Charge;
import engine.modifications.player.Jump;
import engine.modifications.player.MoveLeft;
import engine.modifications.player.MoveRight;
import engine.modifications.player.PlayerAction;

/**
 * Class used to manage the control of the player object. Check if action in use, 
 * in cooldown, ...
 * Manage action buffer (only one)
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
	
	public enum Facing {
		Left, Right, Unknown
	}
	
	private enum ActionTypes {
		Idle, Attacking, Jumping, Charging;
	}
	
	private Facing facing = Facing.Unknown;
	private Facing oldFacing = Facing.Unknown;
	
	private int cooldownCounter;
	private PlayerAction currentAction = null;
	private ActionTypes currentActionType = ActionTypes.Idle;
	private boolean currentActionMultiExecute = false;
	private boolean currentActionAnimationSet = false; // Don't set animation twice or more...
	
	private PlayerAction nextAction = null;
	private ActionTypes nextActionType = ActionTypes.Idle;
	private boolean nextActionMultiExecute = false;
	
	/**
	 * Transition state between 2 actions.
	 * @author Brito Carvalho Bruno
	 * @author Decorvet Grégoire
	 * @author Ngo Quang Dung
	 * @author Schweizer Thomas
	 *
	 */
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
	
	public PlayerControl(Player player) {
		this.player = player;
	}
	
	/**
	 * Update when a new control is given.
	 */
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
	
	/**
	 * Begins the update.
	 * @param delta Time between 2 frames.
	 */
	public void beginUpdate(int delta) {
		this.delta = delta;
		
		// Trick to manage charging speed.
		// Due to the fact there is no timed modification.
		player.setSpeed(0.007f);
	}
	
	/**
	 * Ends the update.
	 */
	public void endUpdate() {
		
		// Assure correct facing if not set
		if (facing == Facing.Unknown)
			facing = oldFacing;

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
	
	/**
	 * Indicate the player is moving right for the animation.
	 */
	public void moveRight() {
		movingRight = true;
		
		if (facing == Facing.Unknown)
			facing = Facing.Right;
	}
	
	/**
	 * Indicate the player is moving left for the animation.
	 */
	public void moveLeft() {
		movingLeft = true;
		
		if (facing == Facing.Unknown)
			facing = Facing.Left;
	}
	
	/**
	 * Adds the jumping action to the stack.
	 */
	public void actionJump() {
		pushAction(new Jump(delta, 300, player), ActionTypes.Jumping, true);
	}
	
	/**
	 * Adds the attack action to the stack.
	 */
	public void actionAttack() {
		pushAction(new Attack(delta, 1000, player, oldFacing), ActionTypes.Attacking, false);
	}
	
	/**
	 * Adds the charge action to the stack.
	 */
	public void actionCharge() {
		pushAction(new Charge(delta, 200, player), ActionTypes.Charging, true);
	}
	
	/**
	 * Add the next action to the stack.
	 * @param action The next action
	 * @param type The type of the action
	 * @param multiExecute Whether the action can be executed multiple times (no cooldown)
	 */
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
