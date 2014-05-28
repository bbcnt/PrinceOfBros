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

import engine.Engine;
import engine.animations.IAnimatedState;
import engine.graphics.player.GPlayer;
import engine.models.player.Player;
import engine.modifications.IModification;
import engine.modifications.animations.AnimationChange;
import engine.modifications.graphics.UpdateDrawableObject;
import engine.modifications.player.Attack;
import engine.modifications.player.MoveDown;
import engine.modifications.player.MoveLeft;
import engine.modifications.player.MoveRight;
import engine.modifications.player.MoveUp;
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
		Left, Right
	}
	
	private enum ActionTypes {
		Idle, Attacking, Jumping;
	}
	
	private Facing facing;
	
	// TODO
	private int cooldownCounter;
	
	private PlayerAction currentAction = null;
	private ActionTypes currentActionType = ActionTypes.Idle;
	
	private PlayerAction nextAction = null;
	private ActionTypes nextActionType = ActionTypes.Idle;
	
	public PlayerControl(Player player) {
		this.player = player;
	}
	
	private void reset() {
		movingLeft = false;
		movingRight = false;
		
		movementHor = null;
		movementVer = null;
		
		// Action management. Check cooldown
		if (currentAction != null) {
			cooldownCounter += delta;
			
			if (cooldownCounter >= currentAction.getCooldown()) {
				cooldownCounter = 0;
				currentAction = nextAction;
				currentActionType = nextActionType;
				nextAction = null;
				nextActionType = ActionTypes.Idle;
			}
		}
	}
	
	public void beginUpdate(int delta) {
		this.delta = delta;
	}
	
	public void endUpdate() {
		
		// Add horizontal movement
		if (movementHor == null) {
			if (movingRight) {
				movementHor = new MoveRight(delta, player);
			}
			else if (movingLeft) {
				movementHor = new MoveLeft(delta, player);
			}
			
			// Manage movement animation left or right
			IAnimatedState oldState = player.getState();
			
			if (oldState.isStoppable() || oldState.getAnimation().isFinished()) {
				
			}
		}
		
		// Add action
		if (currentAction != null && cooldownCounter == 0) {
			Engine.getInstance().addModification(currentAction);
		}
		
		Engine.getInstance().addModification(movementHor);
		Engine.getInstance().addModification(movementVer);
		
		// Choose animation
		IAnimatedState oldState = player.getState();
		IAnimatedState newState = null;
		
		if (oldState.isStoppable() || oldState.getAnimation().isFinished()) {
			
			// Action first
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
			
			// Moving if no action
			if (newState == null) {
				if (movingLeft) {
					if (oldState != GPlayer.AnimationState.MovingLeft)
						newState = GPlayer.AnimationState.MovingLeft;
				}
				else if (movingRight) {
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
				Engine.getInstance().addModification(new AnimationChange(player, newState));
			
		}
		
		reset();
	}
	
	public void moveRight() {
		// Ignore if already assigned to left
		if (movingLeft) return;
		
		movingRight = true;
		facing = Facing.Right;
	}
	
	public void moveLeft() {
		// Ignore if already assigned
		if (movingRight) return;
		
		movingLeft = true;
		facing = Facing.Left;
	}
	
	public void actionJump() {
		pushAction(new MoveUp(delta, player), ActionTypes.Jumping);
	}
	
	public void actionAttack() {
		pushAction(new Attack(delta, 1000, player), ActionTypes.Attacking);
	}
	
	
	private void pushAction(PlayerAction action, ActionTypes type) {
		if (currentAction == null) {
			currentAction = action;
			currentActionType = type;
		}
		else {
			nextAction = action;
			nextActionType = type;
		}
	}

}
