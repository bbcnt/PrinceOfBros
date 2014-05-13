package game;

import game.logic.Player;
import game.logic.Transaction;
import game.logic.actions.MoveDown;
import game.logic.actions.MoveLeft;
import game.logic.actions.MoveRight;
import game.logic.actions.MoveUp;
import game.logic.modstack.IModificationTransaction;
import game.logic.modstack.ModificationStack;

import java.util.LinkedList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import debug.DebugInformations;

public class Play extends BasicGameState {
	
//	private ActionStack actionStack = new ActionStack(250);
	
	private ModificationStack modStack;
	
	private Image background;
	
	private Player player;
	
//	Animation player, movingUp, movingDown, movingLeft, movingRight;
//	int[] duration = {200, 200};
//	float playerPosX = 0;
//	float playerPosY = 0;
//	float shiftX = playerPosX + Game.SCREEN_WIDTH / 2;
//	float shiftY = playerPosY + Game.SCREEN_HEIGHT / 2;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		modStack = new ModificationStack(3, 100);
		DebugInformations.getInstance().registerModificationStack(modStack);
		
		background = new Image("res/Hydrangeas.jpg");
		
		Image[] heroUp = {
		      new Image("res/hero_up.png"),
		      new Image("res/hero_up.png")
		};
		Image[] heroDown = {
		      new Image("res/hero_down.png"),
		      new Image("res/hero_down.png")
		};
		Image[] heroLeft = {
		      new Image("res/hero_left.png"),
		      new Image("res/hero_left.png")
		};
		Image[] heroRight = {
		      new Image("res/hero_right.png"),
		      new Image("res/hero_right.png")
		};

		int[] duration = {200, 200};
		player = new Player(
						new Animation(heroUp, duration, false),
						new Animation(heroDown, duration, false),
						new Animation(heroLeft, duration, false),
						new Animation(heroRight, duration, false)
					);
		DebugInformations.getInstance().registerPlayer(player);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
	      throws SlickException {
		
		background.draw(player.getPosX(), player.getPosY());
		
		player.draw(g);
		
		// DEBUG
		DebugInformations.getInstance().draw(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
	      throws SlickException {
		Input input = gc.getInput();
		
		Transaction transaction = new Transaction();
		
		transaction.begin();
		
		// Moves
		if (input.isKeyDown(Input.KEY_UP)) {
			transaction.add(new MoveUp(delta, player));
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			transaction.add(new MoveDown(delta, player));
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			transaction.add(new MoveLeft(delta, player));
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			transaction.add(new MoveRight(delta, player));
		}
		
		if (input.isKeyDown(Input.KEY_Q)) {
			IModificationTransaction trans = modStack.pop();
			
			if (trans != null) {
				trans.revert();
			}
		}
		else {
			modStack.push(transaction.commit());
		}
		
//		MultiActions actions = new MultiActions();
//
//		// Moves
//		if (input.isKeyDown(Input.KEY_UP)) {
//			actions.add(new MoveUpAction(delta));
//		}
//		if (input.isKeyDown(Input.KEY_DOWN)) {
//			actions.add(new MoveDownAction(delta));
//		}
//		if (input.isKeyDown(Input.KEY_LEFT)) {
//			actions.add(new MoveLeftAction(delta));
//		}
//		if (input.isKeyDown(Input.KEY_RIGHT)) {
//			actions.add(new MoveRightAction(delta));
//		}
//		
//		// Push and execute actions or revert
//		if (input.isKeyDown(Input.KEY_Q))
//			actionStack.pop();
//		else
//			actionStack.push(actions);

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
	
	
//	public class ActionStack {
//		private int size;
//		
//		private LinkedList<MultiActions> list = new LinkedList<>();
//		
//		public ActionStack(int size) {
//			this.size = size;
//		}
//		
//		public void push(MultiActions actions) {
//			actions.execute();
//			
//			list.addLast(actions);
//			if (list.size() > size) {
//				list.removeFirst();
//			}
//			
//			// DEBUG
//			DebugInformations.getInstance().updateModificationStack(list.size(), size);
//		}
//		
//		public void pop() {
//			if (list.size() > 0) {
//				list.removeLast().unexecute();
//				// DEBUG
//				DebugInformations.getInstance().updateModificationStack(list.size(), size);
//			}
//		}
//	}
//	
//	
//	
//	public abstract class Action {
//		public abstract void execute();
//		public abstract void unexecute();
//	}
//	
//	public class MultiActions extends Action {
//		private LinkedList<Action> list = new LinkedList<>();
//		
//		public void add(Action action) {
//			list.add(action);
//		}
//
//		@Override
//      public void execute() {
//	      for (Action a : list) {
//	      	a.execute();
//	      }
//      }
//
//		@Override
//      public void unexecute() {
//	      for (Action a : list) {
//	      	a.unexecute();
//	      }
//      }
//		
//	}
//	
//	public class MoveUpAction extends Action {
//		private int delta;
//		public MoveUpAction(int delta) {
//			this.delta = delta;
//		}
//		@Override
//      public void execute() {
//			player = movingUp;
//			playerPosY += delta * .1f;
//      }
//		@Override
//      public void unexecute() {
//			player = movingUp;
//			playerPosY -= delta * .1f;
//      }
//	}
//	
//	public class MoveDownAction extends Action {
//		private int delta;
//		public MoveDownAction(int delta) {
//			this.delta = delta;
//		}
//		@Override
//      public void execute() {
//			player = movingDown;
//			playerPosY -= delta * .1f;
//      }
//		@Override
//      public void unexecute() {
//			player = movingDown;
//			playerPosY += delta * .1f;
//      }
//	}
//	
//	public class MoveLeftAction extends Action {
//		private int delta;
//		public MoveLeftAction(int delta) {
//			this.delta = delta;
//		}
//		@Override
//      public void execute() {
//			player = movingLeft;
//			playerPosX += delta * .1f;
//      }
//		@Override
//      public void unexecute() {
//			player = movingLeft;
//			playerPosX -= delta * .1f;
//      }
//	}
//	
//	public class MoveRightAction extends Action {
//		private int delta;
//		public MoveRightAction(int delta) {
//			this.delta = delta;
//		}
//		@Override
//      public void execute() {
//			player = movingRight;
//			playerPosX -= delta * .1f;
//      }
//		@Override
//      public void unexecute() {
//			player = movingRight;
//			playerPosX += delta * .1f;
//      }
//	}
}
