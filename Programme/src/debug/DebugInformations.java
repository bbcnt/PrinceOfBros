/* ============================================================================
 * Filename   : DebugInformations.java
 * ============================================================================
 * Created on : 13 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */

package debug;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import engine.GameController;
import engine.graphics.IDrawable;
import engine.models.player.Player;

/**
 * Displayed values on the top of the game panel.
 * 
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class DebugInformations {
	
	public final static boolean SHOW_DEBUG_INFORMATIONS = true;
	
	public final static boolean SHOW_MOUSE_POS = true;
	public final static boolean SHOW_UPDATE_DELTA = true;
	public final static boolean SHOW_PLAYER_POS = true;
	public final static boolean SHOW_STACK_COMPLETION = true;
	public final static boolean SHOW_STACK_COMPLETION_PERCENT = true;
	
	public final static Color SHOW_DRAWABLES_COLOR = Color.green;
	public final static boolean SHOW_DRAWABLES_BOUNDS = true;
	public final static boolean SHOW_DRAWABLES_CENTER = true;
	
	private class GameStateDrawables {
		int stateId;
		List<IDrawable> objects = new LinkedList<>();
      public GameStateDrawables(int id) {
	      stateId = id;
      }
	}
	private List<GameStateDrawables> drawableObjects = new LinkedList<>();
	
	private int displayPosX, displayPosY;
	
	private int updateDelta;
	
	private int gameStateId;
	
	private Player player;
	
	private List<IDrawable> getObjectForState(int stateId) {
		for (GameStateDrawables state : drawableObjects) {
			if (state.stateId == stateId)
				return state.objects;
		}
		return null;
	}
	
   @SuppressWarnings("unused")
   private void drawDebugForDrawablesObjects(Graphics g) {
		
		if (!SHOW_DRAWABLES_BOUNDS && !SHOW_DRAWABLES_CENTER) return;
		
		List<IDrawable> list = getObjectForState(gameStateId);
		
		if (list == null) return;
		
		Color oldColor = g.getColor();
		
		g.setColor(SHOW_DRAWABLES_COLOR);
		
		for (IDrawable d : list) {
			
			if (SHOW_DRAWABLES_BOUNDS) {
				g.drawRect(d.getX() - d.getWidth() / 2, d.getY() - d.getHeight() / 2,
						d.getWidth(), d.getHeight());
			}
			
			if (SHOW_DRAWABLES_CENTER) {
				g.fillRect(d.getX() - 6, d.getY(), 13, 2);
				g.fillRect(d.getX(), d.getY() - 6, 2, 13);
			}
					
		}
		
		g.setColor(oldColor);
	}
	
	
	public void draw(Graphics g) {
		
		if (!SHOW_DEBUG_INFORMATIONS) return;
		
		String text = "";
		
		// Mouse position
		if (SHOW_MOUSE_POS)
			text += "Mouse X: " + Mouse.getX() + "\nMouse Y: " + Mouse.getY() 
					+ "\n";
		
		// Update delta
		if (SHOW_UPDATE_DELTA)
			text += "Update delta: " + updateDelta + "\n";
		
		// Player position
		if (SHOW_PLAYER_POS) {
			text += "Player pos: ";
			
			if (player == null) {
				text += " ?, ?";
			}
			else {
				text += String.format("%.4f, %4f", player.getPosX(),
				      player.getPosY());
			}
			text += "\n";
		}
		
		// Modification stack
		if (SHOW_STACK_COMPLETION) {
			int stackSize = GameController.getInstance().getStackSize();
			int stackCapacity = GameController.getInstance().getStackCapacityLogical();
			
			text += "Stack : " + stackSize + " / " + stackCapacity + "\n"; 
		}
		
		if (SHOW_STACK_COMPLETION_PERCENT) {
			int stackSize = GameController.getInstance().getStackSize();
			int stackCapacity = GameController.getInstance().getStackCapacityLogical();
			
			text += "Stack : "
					+ String.format("%.2f", 100.0*(double)stackSize /
							(double)(stackCapacity > 0 ? stackCapacity : 1))
					+ " %\n"; 
		}
		
		g.drawString(text, displayPosX, displayPosY);
		
		drawDebugForDrawablesObjects(g);
	}
	
	public void setPosition(int x, int y) {
		displayPosX = x;
		displayPosY = y;
	}
	
	public void updateUpdateDelta(int delta) {
		updateDelta = delta;
	}
	
	public void updateGameStateId(int stateId) {
		gameStateId = stateId;
	}
	
	public void registerPlayer(Player player) {
		this.player = player;
	}
	
	public void registerDrawableObject(IDrawable drawable, int gameStateId) {
		List<IDrawable> li = getObjectForState(gameStateId);
		
		if (li == null) {
			GameStateDrawables gmd = new GameStateDrawables(gameStateId);
			li = gmd.objects;
			drawableObjects.add(gmd);
		}
		
		li.add(drawable);
	}
	
	/*---Singleton part--------------------------------------------------------*/
	
	private static class Instance {
		static final DebugInformations instance = new DebugInformations();
	}
	
	private DebugInformations() {}
	
	public static DebugInformations getInstance() {
		return Instance.instance;
	}

}
