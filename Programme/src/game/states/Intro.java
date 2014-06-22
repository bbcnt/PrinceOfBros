/* ============================================================================
 * Filename   : Intro.java
 * ============================================================================
 * Created on : 10 juin 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package game.states;

import java.awt.Font;

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
public class Intro extends BasicGameState{

	private Image logo;
	private Image background;
	private TrueTypeFont font;
	private Color textColor;
	private int timeCounter;
	private String textToShow;
	
	private final String pressEnter = "Press <ENTER>";
	
	@Override
   public void init(GameContainer arg0, StateBasedGame arg1)
         throws SlickException {
		
		logo = new Image("/res/intro/Logo_700.png");
		background = new Image("/res/intro/intro_background.png");
		timeCounter = 0;
		textToShow = pressEnter;
		
		try {
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("./res/trajanproregular.ttf"));
			awtFont = awtFont.deriveFont(20f);
			font = new TrueTypeFont(awtFont, false);
		}catch(Exception e){}
		
		textColor = new Color(0,0,0);
   }

	@Override
   public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
         throws SlickException {
	   
		g.drawImage(background, 0, 0);
		g.drawImage(logo, 50, 120);
		font.drawString(310, 370, textToShow, textColor);
	   
   }

	@Override
   public void update(GameContainer gc, StateBasedGame sbg, int delta)
         throws SlickException {
		Input input = gc.getInput();
		
		timeCounter += delta;
		
		if(timeCounter > 1000){ 
			
			if(textToShow.isEmpty())
				textToShow = pressEnter;
			else
				textToShow = "";
			timeCounter = 0;
		}
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			sbg.enterState(Game.STATE_MENU);
		}
   }

	@Override
   public int getID() {
	   return Game.STATE_INTRO;
   }

}
