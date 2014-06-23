/* ============================================================================
 * Filename   : Launcher.java
 * ============================================================================
 * Created on : 13 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */

import game.settings.Config;
import game.states.Game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import debug.DebugInformations;

/**
 * Launcher of the game.
 * 
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Launcher {
	public static void main(String[] args) {
      AppGameContainer container;
      
		try {
			DebugInformations.getInstance().setPosition(
					Config.getInstance().getResolutionWidth() - 250, 0);
			
			container = new AppGameContainer(new Game());
			container.setDisplayMode(Config.getInstance().getResolutionWidth(),
									       Config.getInstance().getResolutionHeight(),
									       false);
			container.setTargetFrameRate(60);
			container.setShowFPS(true);
			container.start();
		}
		catch (SlickException e) {
			e.printStackTrace();
		}
   }
}
