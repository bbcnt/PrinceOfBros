package game.states;

import game.controlls.Commands;
import game.controlls.PlayerControl;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import debug.DebugInformations;
import engine.Engine;
import engine.animations.IAnimatedState;
import engine.animations.NTimedAnimation;
import engine.graphics.player.GPlayer;
import engine.models.player.Player;
import engine.modifications.graphics.UpdateDrawableObject;
import engine.modifications.player.MoveDown;

public class Play extends BasicGameState {
	
	private TiledMap background;
	private int idLayerContent;
	
	private Player player;
	private GPlayer gPlayer;
	private PlayerControl playerControl;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Engine.getInstance().init();
		
		background = new TiledMap("./res/Map.tmx");
		
		Image[] heroLeft = {
		      new Image("res/player/move_00_left_0.png"),
		      new Image("res/player/move_00_left_1.png")
		};
		Image[] heroRight = {
		      new Image("res/player/move_00_right_0.png"),
		      new Image("res/player/move_00_right_1.png")
		};
		Image[] heroIdleLeft = {
				new Image("res/player/idle_00_left_0.png"),
		      new Image("res/player/idle_00_left_0.png")
		};
		Image[] heroIdleRight = {
				new Image("res/player/idle_00_right_0.png"),
		      new Image("res/player/idle_00_right_0.png")
		};
		Image[] heroAttackLeft = {
				new Image("res/player/attack_saber_00_left_0.png"),
		      new Image("res/player/attack_saber_00_left_1.png"),
		      new Image("res/player/attack_saber_00_left_2.png")
		};
		Image[] heroAttackRight = {
				new Image("res/player/attack_saber_00_right_0.png"),
		      new Image("res/player/attack_saber_00_right_1.png"),
		      new Image("res/player/attack_saber_00_right_2.png")
		};

		int[] duration = {100, 200};
		int[] durationAttack = {75, 100, 100};
		
		// Init player
		player = new Player(0.5f, 4.5f);
		gPlayer = new GPlayer(player);
		playerControl = new PlayerControl(player);
		
//		GPlayer.LegsState.IdleLeft.init(new TimedAnimation(new Animation(heroIdleLeft, duration, false)));
//		GPlayer.LegsState.IdleRight.init(new TimedAnimation(new Animation(heroIdleRight, duration, false)));
//		GPlayer.LegsState.MovingLeft.init(new TimedAnimation(new Animation(heroLeft, duration, false)));
//		GPlayer.LegsState.MovingRight.init(new TimedAnimation(new Animation(heroRight, duration, false)));
//		
//		GPlayer.BodyState.IdleLeft.init(new TimedAnimation(new Animation(heroIdleLeft, duration, false)));
//		GPlayer.BodyState.IdleRight.init(new TimedAnimation(new Animation(heroIdleRight, duration, false)));
//		GPlayer.BodyState.AttackingLeft.init(new TimedAnimation(new Animation(heroAttackLeft, durationAttack, false)));
//		GPlayer.BodyState.AttackingRight.init(new TimedAnimation(new Animation(heroAttackRight, durationAttack, false)));
		
		GPlayer.AnimationState.IdleLeft.init(new NTimedAnimation(heroIdleLeft, duration, true), true);
		GPlayer.AnimationState.IdleRight.init(new NTimedAnimation(heroIdleRight, duration, true), true);
		GPlayer.AnimationState.MovingLeft.init(new NTimedAnimation(heroLeft, duration, true), true);
		GPlayer.AnimationState.MovingRight.init(new NTimedAnimation(heroRight, duration, true), true);
		GPlayer.AnimationState.AttackingLeft.init(new NTimedAnimation(heroAttackLeft, durationAttack, true, false), false);
		GPlayer.AnimationState.AttackingRight.init(new NTimedAnimation(heroAttackRight, durationAttack, false, true), false);
		
//		IAnimatedState[] playerStates = new IAnimatedState[6];
//		playerStates[0] = GPlayer.AnimationState.IdleLeft;
//		playerStates[1] = GPlayer.AnimationState.IdleRight;
//		playerStates[2] = GPlayer.AnimationState.MovingLeft;
//		playerStates[3] = GPlayer.AnimationState.MovingRight;
//		playerStates[4] = GPlayer.AnimationState.AttackingLeft;
//		playerStates[5] = GPlayer.AnimationState.AttackingRight;
//		
//		player.initAnimationStates(playerStates);
		
		DebugInformations.getInstance().updateGameStateId(getID());
		DebugInformations.getInstance().registerPlayer(player);
		
		// Register every Drawable item
		DebugInformations.getInstance().registerDrawableObject(gPlayer, getID());
		
		idLayerContent = background.getLayerIndex("Content");
		
		System.out.println("Map size: " + background.getHeight() + "x" + background.getWidth());
		System.out.println("Number of layers: " + background.getLayerCount());
		System.out.println("Tile size: " + background.getTileHeight());
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
	      throws SlickException {
		
		// Using float position.
		
		background.render(0, 0);
		// Temporaire.
		g.drawRect((float)Math.floor(player.getPosX()) * background.getTileHeight(), (float)Math.floor(10 - player.getPosY()) * background.getTileHeight(), background.getTileWidth(), background.getTileHeight());
		gPlayer.draw(g);
		
		// DEBUG
		DebugInformations.getInstance().draw(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
	      throws SlickException {
		Input input = gc.getInput();
		
		Engine.getInstance().beginUpdate();
		
		boolean goBackward = false;
		
		// Moves
		playerControl.beginUpdate(delta);
		
		if (Commands.MoveLeft.isTriggered(input.isKeyDown(Input.KEY_LEFT))) {
			playerControl.moveLeft();
		}
		if (Commands.MoveRight.isTriggered(input.isKeyDown(Input.KEY_RIGHT))) {
			playerControl.moveRight();
		}
		
		if (Commands.Jump.isTriggered(input.isKeyDown(Input.KEY_SPACE))) {
			playerControl.actionJump();
		}
		
		if (Commands.Attack.isTriggered(input.isKeyDown(Input.KEY_A))) {
			playerControl.actionAttack();
		}
		
		if (Commands.BackInTime.isTriggered(input.isKeyDown(Input.KEY_Q))) {
			goBackward = true;
		}
		
		playerControl.endUpdate();
		
		// DEBUG controls
		if (input.isKeyDown(Input.KEY_UP)) {
			Engine.getInstance().addModification(new MoveDown(-delta,player));
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			Engine.getInstance().addModification(new MoveDown(delta,player));
		}
		
		// FIXME remove when list of drawable object manage in engine
		Engine.getInstance().addModification(new UpdateDrawableObject(delta, gPlayer));
		
		if (goBackward) 
			Engine.getInstance().revertLastUpdate();
		else
			Engine.getInstance().commitUpdate();

		// Escape
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			sbg.enterState(Game.STATE_MENU);
		}
		
		// DEBUG
		DebugInformations.getInstance().updateGameStateId(getID());
		DebugInformations.getInstance().updateUpdateDelta(delta);
	}

	@Override
   public int getID() {
		return Game.STAT_PLAY;
   }
}
