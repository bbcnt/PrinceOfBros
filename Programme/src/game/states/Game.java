package game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
   
   public static final String gamename = "Prince of Bros";
   
   public static final int SCREEN_WIDTH = 800;
   public static final int SCREEN_HEIGHT = 500;
   
   public static final int STATE_MENU = 0;
   public static final int STAT_PLAY = 1;
   
   public Game() {
      super(gamename);
      this.addState(new Menu());
      this.addState(new Play());
   }
   
   public void initStatesList(GameContainer gc) throws SlickException{
      this.enterState(STATE_MENU);
   }
   
   

}