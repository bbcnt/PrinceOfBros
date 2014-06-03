package game.states;

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
import engine.Engine;
import engine.LogicWorld;
import engine.animations.TimedAnimation;
import engine.graphics.player.GPlayer;
import engine.interaction.GameObject;
import engine.models.player.Player;
import engine.modifications.graphics.UpdateDrawableObject;
import engine.modifications.player.MoveDown;
import game.controlls.Commands;
import game.controlls.PlayerControl;

public class Play extends BasicGameState {

	private TiledMap background;
	private int idLayerContent;

	private Player player;
	private GPlayer gPlayer;
	private PlayerControl playerControl;
	
	private LogicWorld world;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Engine.getInstance().init();

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
		

		// Init player
		player = new Player(0.5f, 4.5f);
		gPlayer = new GPlayer(player);
		playerControl = new PlayerControl(player);
		
		// Init map
		background = new TiledMap("./res/Map.tmx");
		CoordsConverter.registerTileSize(background.getTileHeight());
		world = new LogicWorld(background);
		
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

		background.render((int) CoordsConverter.getInstance().toGraphic(0),
		      (int) CoordsConverter.getInstance().toGraphic(0));

		// Draw a rectangle that highlight the current tile mario is on.
		g.drawRect(
		      CoordsConverter.getInstance().toGraphic(
		            (float) Math.floor(player.getPosX()) + 0.5f) - 32,
		      CoordsConverter.getInstance().toGraphic(
		            (float) Math.floor(10 - player.getPosY())),
		      background.getTileWidth(), background.getTileHeight());

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
			Engine.getInstance().addModification(new MoveDown(-delta, player));
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			Engine.getInstance().addModification(new MoveDown(delta, player));
		}

		// FIXME remove when list of drawable object manage in engine
		Engine.getInstance().addModification(
		      new UpdateDrawableObject(delta, gPlayer));

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
