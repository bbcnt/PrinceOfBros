/* ============================================================================
 * Filename   : TimedAnimation.java
 * ============================================================================
 * Created on : 24 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine.animations;

import org.newdawn.slick.Animation;


/**
 * TODO - need rewrite Slick's animation !!!!!! TODO extends it and override 
 * 
 * Class used to manage a Slick animation. Hide some functionalities, and
 * add some other. The goal is to keep only elements that are essential to the
 * application.
 * 
 * Lots of duplicated items...but due to the fact that Slick'animation is not
 * enough complete for our application...
 * 
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class TimedAnimationOLD {
	
	private Animation animation;
	
	private int width, height;
	
	private int ellapsedTime, totalTime;
	private boolean loop, pingPong;
	
	public TimedAnimationOLD(Animation animation) {
		this(animation, true, false);
	}
	
	public TimedAnimationOLD(Animation animation, boolean loop, boolean pingPong) {
		if (animation == null)
			throw new RuntimeException("A timed animation has to contain an animation");
		
		 setupAnimation(animation, loop, pingPong);
	}
	
	private void setupAnimation(Animation anim, boolean loop, boolean pingPong) {
		animation = anim;
		animation.setAutoUpdate(false);
		animation.restart();
		
		ellapsedTime = 0;
		totalTime = 0;
		for (int i : animation.getDurations()) {
			totalTime += i;
		}
		
		for (int i = 0 ; i < anim.getFrameCount() ; i++) {
			height = Math.max(anim.getImage(i).getHeight(), height);
			width = Math.max(anim.getImage(i).getWidth(), width);
		}
		
		this.loop = loop;
		this.pingPong = pingPong;
		animation.setLooping(loop);
		animation.setPingPong(pingPong);
	}
	
	/*---Getters---------------------------------------------------------------*/
	
	public boolean isFinished() {
		return !loop && !pingPong && ellapsedTime >= totalTime;
	}
	
	public int getDuration() {
		return totalTime;
	}
	
	public int getEllapsedTime() {
		return ellapsedTime;
	}
	
	public int getRemainingTime() {
		int remaining = totalTime - ellapsedTime;
		return remaining < 0 ? 0 : remaining;
	}
	
	/*---Modifiers-------------------------------------------------------------*/
	
	public void setLooping(boolean loop) {
		this.loop = loop;
		animation.setLooping(loop);
	}
	
	public void setPingPong(boolean pingPong) {
		this.pingPong = pingPong;
		animation.setPingPong(pingPong);
	}
	
	public void restart() {
		ellapsedTime = 0;
		animation.restart();
	}
	
	public void setEllapsedTime(int time) {
		if (time < 0 || time > totalTime) return;
		
		ellapsedTime = time;
		
		int[] durations = animation.getDurations();
		
		int frame = -1;
		while (time >= 0) {
			frame++;
			time -= durations[frame];
		}
		
		// Beware, may have a frame bad duration. Have to rewrite all Slick's animation
		// to correct it => don't care for now
		animation.setCurrentFrame(frame);
	}
	
	
	/*---Graphics--------------------------------------------------------------*/
	
	public void update(int delta) {
		if (isFinished() || delta < 0) return;
		
		if (!loop && !pingPong) {
			ellapsedTime += delta;
			
			if (ellapsedTime > totalTime)
				ellapsedTime = totalTime;
		}
		else if (loop) {
			ellapsedTime = (ellapsedTime + delta) % totalTime;
		}
		else if (pingPong) {
			ellapsedTime += delta;
			
			if (ellapsedTime > totalTime)
				ellapsedTime = 2 * totalTime - ellapsedTime;
		}
		
		animation.update(delta);
	}
	
	// TODO - check if it works and improve this shit...
	public void updateBackward(int delta) {
		
		if (delta < 0 || (!loop && !pingPong && ellapsedTime == 0 )) return;
		
		
		if (!loop && !pingPong) {
			ellapsedTime -= delta;
			
			if (ellapsedTime < 0) {
				ellapsedTime = 0;
			}
		}
		else if (loop) {
			ellapsedTime -= delta;
			
			if (ellapsedTime < 0) {
				ellapsedTime += totalTime;
			}
		}
		else if (pingPong) {
			ellapsedTime -= delta;
			
			if (ellapsedTime < 0) {
				ellapsedTime *= -1;
			}
		}
		
		setEllapsedTime(ellapsedTime);
	}
	
	public void draw(float x, float y) {
		animation.draw(x, y, width, height);
	}

}
