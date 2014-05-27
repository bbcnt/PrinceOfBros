package game.states;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import debug.DebugInformations;

public class Menu extends BasicGameState {

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
	      throws SlickException {
		g.drawString("Main menu bidon", 100, 50);
		
		g.drawRoundRect(100, 100, 100, 35, 7);
		g.drawString("Play", 115, 105);
		
		g.drawRoundRect(100, 150, 100, 35, 7);
		g.drawString("Quit", 115, 155);
		
		// DEBUG
		DebugInformations.getInstance().draw(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
	      throws SlickException {
		
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		
		// play button
		if ((mouseX > 100 && mouseX < 200) && (mouseY > Game.SCREEN_HEIGHT - 135 && mouseY < Game.SCREEN_HEIGHT - 100)) {
			if (Mouse.isButtonDown(0)) {
				sbg.enterState(Game.STAT_PLAY);
			}
		}
		// exit game
		if ((mouseX > 100 && mouseX < 200) && (mouseY > Game.SCREEN_HEIGHT - 185 && mouseY < Game.SCREEN_HEIGHT - 150)) {
			if (Mouse.isButtonDown(0)) {
				System.exit(0);
			}
		}
	}

	@Override
	public int getID() {
		return Game.STATE_MENU;
	}
}
