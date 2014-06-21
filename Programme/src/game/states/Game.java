package game.states;

import game.settings.Config;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
   
   public static final int STATE_MENU = 0;
   public static final int STAT_PLAY = 1;
   public static final int STATE_INTRO = 2;
   public static final int STATE_GAMEOVER = 3;
   
   public Game() {
      super(Config.getInstance().getGameName());
      this.addState(new Menu());
      this.addState(new Play());
      this.addState(new Intro());
      this.addState(new GameOver());
   }
   
   public void initStatesList(GameContainer gc) throws SlickException{
      this.enterState(STATE_INTRO);
   }
   
   

}