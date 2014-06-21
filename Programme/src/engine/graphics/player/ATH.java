/* ============================================================================
 * Filename   : ATH.java
 * ============================================================================
 * Created on : 3 juin 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine.graphics.player;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.ResourceLoader;

import engine.GameController;
import engine.graphics.IDrawable;
import engine.models.player.Player;
import game.settings.Config;
import game.states.Game;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class ATH implements IDrawable{

	private float x;
	private float y;
	private float height;
	private float width;
	private Player player;
	private TrueTypeFont font;
	
	private Rectangle lifeBar;
	private Rectangle mushroomBar;
	private GradientFill lifeFill;
	private GradientFill mushroomFill;
	
	private void init() {
		
		try {
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT,
			                               ResourceLoader.getResourceAsStream("./res/trajanproregular.ttf"));
			awtFont = awtFont.deriveFont(20f);
			font = new TrueTypeFont(awtFont, false);
		}
		catch(Exception e){}
		
		lifeBar = new Rectangle(x, y, width * (player.getCurrentLife() / player.getMaxLife()), height);
		mushroomBar = new Rectangle(x, y + 25, width * GameController.getInstance().getStackSize() / GameController.getInstance().getStackCapacityLogical(), height);
		
      lifeFill = new GradientFill(x, 0, new Color(255, 60, 0),
            x + width, 20, new Color(255, 180, 0));
      
		mushroomFill = new GradientFill(x, 0, new Color(0, 60, 255),
            x + width, 20, new Color(0, 180, 255));
	}
	
	
	
	@Override
   public float getX() {
	   return x;
   }

	@Override
   public float getY() {
	   return y;
   }

	@Override
   public float getWidth() {
	   return width;
   }

	@Override
   public float getHeight() {
	   return height;
   }

	@Override
   public void draw(Graphics g) {
	   
		//HP Bar
		font.drawString(x - 40, y, "HP", new Color(255, 0, 0));
		lifeBar = new Rectangle(x, y, width * (player.getCurrentLife() / player.getMaxLife()), height);
      lifeFill = new GradientFill(x, 0, new Color(255, 60, 0),
                                           x + width, 20, new Color(255, 180, 0));
      //MP Bar
      font.drawString(x - 40, y + 25, "MP", new Color(0,0, 255));
      mushroomBar = new Rectangle(x, y + 25, width * GameController.getInstance().getStackSize() / GameController.getInstance().getStackCapacityLogical(), height);
      mushroomFill = new GradientFill(x, 0, new Color(0, 60, 255),
                                           x + width, 20, new Color(0, 180, 255));
      
      //The previous color
      Color color = g.getColor();
      
      g.setColor(Color.darkGray);
      g.fillRect(x, y, width, height);
      g.fillRect(x, y + 25, width, height);
      g.fill(lifeBar, lifeFill); 
      g.fill(mushroomBar, mushroomFill); 
      g.setColor(color);
	   
   }

	@Override
   public void update(int delta) {
		// Nothing to update
   }
	
	public void register(Player player) {
		this.player = player;
		init();
	}
	
/*---Singleton part--------------------------------------------------------*/
	
	public static ATH getInstance() {
		return Instance.instance;
	}
	
	private ATH() {
		x 		 = 80;
		y 		 = 20;
		width  = Config.getInstance().getResolutionWidth()  / 5;
		height = Config.getInstance().getResolutionHeight() / 22;
	}
	
	private static class Instance {
		private static final ATH instance = new ATH();
	}

}
