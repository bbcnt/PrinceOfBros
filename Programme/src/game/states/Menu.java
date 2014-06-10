package game.states;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import debug.DebugInformations;

public class Menu extends BasicGameState {

	private Image keyA;
	private Image keyS;
	private Image keyD;
	private Image keyArrows;
	private Image story;
	private Image background;
	private Image play;
	private Image quit;
	private Image yoshi;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		keyA = new Image("/res/menu/A_Key.png");
		keyS = new Image("/res/menu/S_Key.png");
		keyD = new Image("/res/menu/D_Key.png");
		keyArrows = new Image("/res/menu/Arrow_Keys.png");
		background = new Image("/res/menu/Menu_Background.jpg");
		story = new Image("/res/menu/Story.png");
		play = new Image("/res/menu/Play.png");
		quit = new Image("/res/menu/Quit.png");
		yoshi = new Image("/res/menu/Pause_Yoshi.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
	      throws SlickException {
		
		g.drawImage(background, 0, 0);
		g.setColor(new Color(51,42,39));
		
		g.drawImage(play, 120, 50);
		g.drawImage(quit, 270, 50);
		
		g.drawImage(yoshi, 518, 0);
		
		g.drawImage(story, 40, 180);
		
		g.drawString("CONTROLS", 570, 150);
		
		g.drawString("Move", 594, 200);
		
		g.drawString("Attack", 535, 370);
		g.drawImage(keyA, 540, 400);
		
		g.drawString("Jump", 595, 460);
		g.drawImage(keyS, 590, 400);
		
		g.drawString("Mushrooms", 620, 370);
		g.drawImage(keyD, 640, 400);
		
		g.drawImage(keyArrows, 540, 240);
		//g.drawString(", x, y)
		
		// DEBUG
		//DebugInformations.getInstance().updateGameStateId(getID());
		//DebugInformations.getInstance().draw(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
	      throws SlickException {
		
		Input input = gc.getInput();
		
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		
		// play button
		if ((mouseX > 120 && mouseX < 220) && (mouseY > Game.SCREEN_HEIGHT - 135 && mouseY < Game.SCREEN_HEIGHT - 100)) {
			if (Mouse.isButtonDown(0)) {
				sbg.enterState(Game.STAT_PLAY);
			}
		}
		// exit game
		if ((mouseX > 260 && mouseX < 380) && (mouseY > Game.SCREEN_HEIGHT - 135 && mouseY < Game.SCREEN_HEIGHT - 100)) {
			if (Mouse.isButtonDown(0)) {
				System.exit(0);
			}
		}
		
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
		
		if (input.isKeyDown(Input.KEY_1)) {
			sbg.enterState(Game.STAT_PLAY);
		}
	}

	@Override
	public int getID() {
		return Game.STATE_MENU;
	}
}
