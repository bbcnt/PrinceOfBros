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
import engine.animations.TimedAnimation;
import engine.graphics.player.GPlayer;
import engine.models.player.Player;
import engine.modifications.graphics.UpdateDrawableObject;

public class Play extends BasicGameState {
	
	private TiledMap background;
	
	private Player player;
	private GPlayer gPlayer;
	private PlayerControl playerControl;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Engine.getInstance().init();
		
		background = new TiledMap("./res/Map.tmx");
		
//		background = new Image("res/Hydrangeas.jpg");
		
		Image[] heroLeft = {
		      new Image("res/little_mario_move0_l.png"),
		      new Image("res/little_mario_move1_l.png")
		};
		Image[] heroRight = {
		      new Image("res/little_mario_move0_r.png"),
		      new Image("res/little_mario_move1_r.png")
		};
//		Image[] heroIdle = {
//				new Image("res/hero_up.png"),
//		      new Image("res/hero_down.png")
//		};

		int[] duration = {1000, 1000};
		
		// Init player
		player = new Player(0, 0);
		gPlayer = new GPlayer(player);
		playerControl = new PlayerControl(player);
//		
//		GPlayer.LegsState.Idle.init(new TimedAnimation(new Animation(heroLeft, duration, false)));
		GPlayer.LegsState.MovingLeft.init(new TimedAnimation(new Animation(heroLeft, duration, false)));
		GPlayer.LegsState.MovingRight.init(new TimedAnimation(new Animation(heroRight, duration, false)));
		
		IAnimatedState[] playerStates = new IAnimatedState[2];
//		playerStates[0] = GPlayer.LegsState.Idle;
		playerStates[0] = GPlayer.LegsState.MovingLeft;
		playerStates[1] = GPlayer.LegsState.MovingRight;
		
		player.initAnimationStates(playerStates);
		
		DebugInformations.getInstance().registerPlayer(player);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
	      throws SlickException {
		
		background.render((int)player.getPosX(),(int) player.getPosY());
		
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
		
		if (Commands.BackInTime.isTriggered(input.isKeyDown(Input.KEY_Q))) {
			goBackward = true;
		}
		
		playerControl.endUpdate();
		
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
		DebugInformations.getInstance().updateUpdateDelta(delta);
	}

	@Override
   public int getID() {
		return Game.STAT_PLAY;
   }
}
