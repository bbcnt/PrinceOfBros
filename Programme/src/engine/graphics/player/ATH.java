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
import java.io.InputStream;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.ResourceLoader;

import engine.GameController;
import engine.graphics.IDrawable;
import engine.models.player.Player;
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
	private static ATH instance = null;
	
	private Rectangle lifeBar;
	private Rectangle mushroomBar;
	private InputStream inputStream;
	private GradientFill lifeFill;
	private GradientFill mushroomFill;
	
	private void init() {
		
		try {
			
			inputStream = ResourceLoader.getResourceAsStream("./res/trajanproregular.ttf");
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
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
	
	private ATH() {
		x 		 = 80;
		y 		 = 20;
		width  = Game.SCREEN_WIDTH  / 5;
		height = Game.SCREEN_HEIGHT / 22;
	}
	private ATH(float x, float y, float height, float width) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
   public float getX() {
	   // TODO Auto-generated method stub
	   return x;
   }

	@Override
   public float getY() {
	   // TODO Auto-generated method stub
	   return y;
   }

	@Override
   public float getWidth() {
	   // TODO Auto-generated method stub
	   return width;
   }

	@Override
   public float getHeight() {
	   // TODO Auto-generated method stub
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
		
   }
	
	public void register(Player player) {
		this.player = player;
		init();
	}
	
	public static ATH getInstance() {
		if(instance == null)
			instance = new ATH();
		return instance;
	}

}
