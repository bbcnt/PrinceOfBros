import game.Game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import debug.DebugInformations;


public class Launcher {
	public static void main(String[] args) {
      AppGameContainer container;
      
		try {
			DebugInformations.getInstance().setPosition(Game.SCREEN_WIDTH - 250, 0);
			
			container = new AppGameContainer(new Game());
			container.setDisplayMode(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT, false);
			container.setTargetFrameRate(60);
			container.start();
		}
		catch (SlickException e) {
			e.printStackTrace();
		}
   }
}
