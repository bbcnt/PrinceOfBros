package game.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import debug.DebugInformations;
import engine.CoordsConverter;
import engine.GameController;
import engine.LogicWorld;
import engine.animations.TimedAnimation;
import engine.graphics.player.ATH;
import engine.graphics.player.GPlayer;
import engine.models.player.Player;
import engine.modifications.graphics.UpdateDrawableObject;
import engine.modifications.player.MoveDown;
import game.controlls.ActionExecutor;
import game.controlls.Commands;
import game.controlls.PlayerControl;
import game.controlls.actions.Attack;
import game.controlls.actions.Jump;
import game.controlls.actions.MoveLeft;
import game.controlls.actions.MoveRight;
import game.controlls.actions.UserMacro;

public class Play extends BasicGameState {

	private TiledMap background;

	private Player player;
	private GPlayer gPlayer;
	private PlayerControl playerControl;
	
	private ActionExecutor playerActionExecutor;
	
	private LogicWorld world;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		GameController.getInstance().init();

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

		int[] duration = {
		      100, 200
		};
		int[] durationAttack = {
		      75, 100, 100
		};

		// Animations setup
		GPlayer.AnimationState.IdleLeft.init(new TimedAnimation(heroIdleLeft,
		      duration, true), true);
		GPlayer.AnimationState.IdleRight.init(new TimedAnimation(heroIdleRight,
		      duration, true), true);
		GPlayer.AnimationState.MovingLeft.init(new TimedAnimation(heroLeft,
		      duration, true), true);
		GPlayer.AnimationState.MovingRight.init(new TimedAnimation(heroRight,
		      duration, true), true);
		GPlayer.AnimationState.AttackingLeft.init(new TimedAnimation(
		      heroAttackLeft, durationAttack), false);
		GPlayer.AnimationState.AttackingRight.init(new TimedAnimation(
		      heroAttackRight, durationAttack), false);
		
		
		// Init action executor
		playerActionExecutor = new ActionExecutor();

		// Init player
		player = new Player(2.5f, 4.5f);
		gPlayer = new GPlayer(player);
		playerControl = new PlayerControl(player);
		ATH.getInstance().register(player);
		
		// Init map
		background = new TiledMap("./res/Final_Map.tmx");
		world = new LogicWorld(background);
		
		// Registering
		CoordsConverter.registerTileSize(background.getTileHeight());
		GameController.getInstance().registerMap(world);
		GameController.getInstance().registerMovableObjects(player);
		
		// Debug information
		// Register every Drawable item
		DebugInformations.getInstance().registerDrawableObject(gPlayer, getID());
		DebugInformations.getInstance().updateGameStateId(getID());
		DebugInformations.getInstance().registerPlayer(player);

		System.out.println("Number of layers: " + background.getLayerCount());
		System.out.println("Tile size: " + background.getTileHeight());
		System.out.println(world);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
	      throws SlickException {
		
		float shiftx = 2;
		float shifty = 4.5f;
		background.render((int) CoordsConverter.getInstance().toGraphic(-player.getX() + shiftx),
		      (int) CoordsConverter.getInstance().toGraphic(player.getY() - shifty));

		// Draw a rectangle that highlight the current tile mario is on.
		g.setColor(Color.white);
		g.drawRect(CoordsConverter.getInstance().toGraphic((shiftx + (-player.getX() % 1))),
					  CoordsConverter.getInstance().toGraphic((shifty + (player.getY() % 1))),
					  background.getTileWidth(), background.getTileHeight());
		
		//g.setColor(Color.black);

		gPlayer.draw(g);
		ATH.getInstance().draw(g);

		// DEBUG
		DebugInformations.getInstance().draw(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
	      throws SlickException {
		Input input = gc.getInput();

		ATH.getInstance().update(delta);
		GameController.getInstance().beginUpdate();

		boolean goBackward = false;

		// Moves
		playerControl.beginUpdate(delta);

		if (Commands.MoveLeft.isTriggered(input, delta)) {
			//playerControl.moveLeft();
			playerActionExecutor.pushAction(new MoveLeft(playerControl, player));
		}
		if (Commands.MoveRight.isTriggered(input, delta)) {
			//playerControl.moveRight();
			playerActionExecutor.pushAction(new MoveRight(playerControl, player));
		}

		if (Commands.Jump.isTriggered(input, delta)) {
			//playerControl.actionJump();
			playerActionExecutor.pushAction(new Jump(playerControl, player));
		}

		if (Commands.Attack.isTriggered(input, delta)) {
			//playerControl.actionAttack();
			playerActionExecutor.pushAction(new Attack(playerControl));
		}

		if (Commands.BackInTime.isTriggered(input, delta)) {
			goBackward = true;
		}
		
		if (Commands.Macro.isTriggered(input, delta)) {
			// TODO
			playerActionExecutor.pushAction(new UserMacro(new Jump(playerControl, player)));
		}
		
		GameController.getInstance().gravityUpdate();

		playerActionExecutor.executeActions();
		playerControl.endUpdate();

		// DEBUG controls
		if (input.isKeyDown(Input.KEY_UP)) {
			GameController.getInstance().addModification(new MoveDown(-delta, player));
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			GameController.getInstance().addModification(new MoveDown(delta, player));
		}

		// FIXME remove when list of drawable object manage in engine
		GameController.getInstance().addModification(
		      new UpdateDrawableObject(delta, gPlayer));

		if (goBackward)
			GameController.getInstance().revertLastUpdate();
		else
			GameController.getInstance().commitUpdate();

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
