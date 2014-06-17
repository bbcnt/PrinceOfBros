/* ============================================================================
 * Filename   : GameOver.java
 * ============================================================================
 * Created on : 17 juin 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package game.states;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class GameOver extends BasicGameState{

	private Image gameOverImage;
	private TrueTypeFont font;
	private InputStream inputStream;
	
	@Override
   public void init(GameContainer arg0, StateBasedGame arg1)
         throws SlickException {
	   
		gameOverImage = new Image("/res/game_over/Game_Over.png");
	   
		try {
			
			inputStream = ResourceLoader.getResourceAsStream("./res/trajanproregular.ttf");
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(20f);
			font = new TrueTypeFont(awtFont, false);
			
		}catch(Exception e){}
   }

	@Override
   public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
         throws SlickException {
	   
		g.setBackground(new Color(255,255,255));
		g.drawImage(gameOverImage, 190, 40);
		font.drawString(230, 440, "Press enter <ENTER> to quit", new Color(0,0,0));
		
   }

	@Override
   public void update(GameContainer gc, StateBasedGame sbg, int delta)
         throws SlickException {
	   
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_ENTER))
			System.exit(0);
	   
   }

	@Override
   public int getID() {
	   // TODO Auto-generated method stub
	   return 3;
   }

}
