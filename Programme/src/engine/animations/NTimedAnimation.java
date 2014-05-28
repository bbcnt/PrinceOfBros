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

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;


/**
 * This class is a badass rewrite of the Slick's Animation class ;)
 * 
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class NTimedAnimation {
	
	private int ellapsedTime;
	private long idleTime;
	
	private boolean loop, pingPong, finished;
	
	private class TimedFrame {
		int start;
		int end;
		Image frame;
	}
	private TimedFrame[] animation;
	
	private int currentFrame;
	
	private enum Direction {
		Forward, Backward
	}
	private Direction currentDirection;
	
	private float speed;
	
	
	/*---Constructors and setup------------------------------------------------*/
	
	public NTimedAnimation(Image[] frames, int[] durations) {
		this(frames, durations, false, false);
	}
	
	public NTimedAnimation(Image[] frames, int[] durations, boolean loop) {
		this(frames, durations, loop, false);
	}
	
	public NTimedAnimation(Image[] frames, int[] durations, boolean loop, boolean pingPong) {
		 setupAnimation(frames, durations, loop, pingPong);
	}
	
	private void setupAnimation(Image[] frames, int[] durations, boolean loop, boolean pingPong) {
		
		if (frames == null || durations == null || frames.length != durations.length)
			throw new RuntimeException("A timed animation has to contain frames and durations");
		
		// Setup parameters
		this.loop = loop;
		this.pingPong = pingPong;
		finished = false;
		
		// Setup time, total time and time line
		ellapsedTime = 0;
		
		animation = new TimedFrame[frames.length];
		
		int currentTime = 0;
		for (int i = 0 ; i < animation.length ; i++) {
			animation[i] = new TimedFrame();
			animation[i].start = currentTime;
			animation[i].end = currentTime + durations[i] - 1;
			animation[i].frame = frames[i];
			
			currentTime += durations[i];
		}
		
		// Setup current frame
		currentFrame = 0;
		currentDirection = Direction.Forward;
		
		speed = 1.0f;
		
	}
	
	/*---Getters---------------------------------------------------------------*/
	// Warning : don't care about pingpong status, which double the animation time
	
	public boolean isFinished() {
		return finished;
		//return !loop && !pingPong && ellapsedTime >= getDuration();
	}
	
	public boolean isPingponging() {
		return pingPong;
	}
	
	public boolean isLooping() {
		return loop;
	}
	
	public int getDuration() {
		return animation[animation.length - 1].end;
	}
	
	public int getEllapsedTime() {
		return ellapsedTime;
	}
	
	public int getRemainingTime() {
		return getDuration() - getEllapsedTime();
	}
	
	public int getWidth() {
		return animation[currentFrame].frame.getWidth();
	}
	
	public int getHeight() {
		return animation[currentFrame].frame.getHeight();
	}
	
	public float getSpeed() {
		return speed;
	}
	
	/*---Modifiers-------------------------------------------------------------*/
	
	public void setLooping(boolean loop) {
		this.loop = loop;
	}
	
	public void setPingPong(boolean pingPong) {
		this.pingPong = pingPong;
	}
	
	public void setSpeed(float speed) {
		if (speed > 0) {
			this.speed = speed;
		}
	}
	
	public void restart() {
		restartAt(0);
	}
	
	public void restartAt(int time) {
		currentDirection = Direction.Forward;
		idleTime = 0;
		finished = false;
		setEllapsedTime(time);
	}
	
	public void setEllapsedTime(int time) {
		if (loop) {
			// Loop + ping pong animation => time depends of the number of loops
			if (pingPong) {
				int nbLoop = Math.abs(time / getDuration());
				
				if (time >= 0) {
					if (nbLoop % 2 == 0) {
						ellapsedTime = time % getDuration();
						currentDirection = Direction.Forward;
					}
					else {
						ellapsedTime = 2 * getDuration() - (time % getDuration());
						currentDirection = Direction.Backward;
					}
				}
				else {
					if (nbLoop % 2 == 0) {
						ellapsedTime = (-time) % getDuration();
						currentDirection = Direction.Backward;
					}
					else {
						while(time < 0) time += getDuration(); // Ensure correct modulus
						ellapsedTime = time;
						currentDirection = Direction.Forward;
					}
				}
				
			}
			// Looping animation => time in modulus getDuration()
			else {
				while(time < 0) time += getDuration(); // Ensure correct modulus
				ellapsedTime = time % getDuration();
				currentDirection = Direction.Forward;
			}
		}
		// Once animation => time in [0, getDuration()]
		else {
			ellapsedTime = time < 0 ? 0 : Math.min(time, getDuration()) ; 
			currentDirection = Direction.Forward;
		}
		
		updateCurrentFrame();
	}
	
	/**
	 * Update the value of currentFrame by comparing the ellapsedTime and the
	 * bounds of neighboring frames.
	 */
	private void updateCurrentFrame() {
		while (ellapsedTime > animation[currentFrame].end) currentFrame++;
		while (ellapsedTime < animation[currentFrame].start) currentFrame--;
	}
	
	/*---Graphics--------------------------------------------------------------*/
	
	public void update(long delta) {
		
		delta *= speed;
		
		// Parameter check
		// Warning : test backward before isFinished, otherwise backward is impossible
		if (delta < 0) {
			updateBackward(-delta);
			return;
		}
		if (delta == 0) return;
		
		// No other modification if animation is finished
		// Keep ellapsedTime up to date, for backward
		if (isFinished()) {
			idleTime += delta;
			return;
		}
		
		switch(currentDirection) {
		case Forward:
			ellapsedTime += delta;
			
			// End of animation reached
			if (ellapsedTime >= getDuration()) {
				
				// Pingpong => back to the start of the animation (inverse it)
				if (pingPong) {
					currentDirection = Direction.Backward;
					ellapsedTime = 2 * getDuration() - ellapsedTime;
				}
				// Loop => restart the animation
				else if (loop) {
					restartAt(ellapsedTime % getDuration());
				}
				// No given behavior, ends the animation
				else {
					idleTime = ellapsedTime - getDuration();
					ellapsedTime = getDuration();
					finished = true;
				}
			}
			
			break;
		case Backward:
			ellapsedTime -= delta;
			
			// Start of animation reached
			if (ellapsedTime <= 0) {
				
				// Infinite pingPong => restart animation
				if (pingPong && loop) {
					restartAt(ellapsedTime * -1);
				}
				// No given behavior, end of the animation
				else {
					idleTime = ellapsedTime * -1;
					ellapsedTime = 0;
					finished = true;
				}
			}
			
			break;
		}
		
		updateCurrentFrame();
	}
	
	private void updateBackward(long delta) {
		
		if (finished) {
			idleTime -= delta;
			
			// No change => return
			if (idleTime >= 0) return;
			
			// No more idle time => restart animation (backward)
			if (idleTime < 0) {
				delta = idleTime * -1;
				finished = false;
			}
		}
		
		switch (currentDirection) {
		case Forward:
			ellapsedTime -= delta;
			
			// Start of animation reached
			if (ellapsedTime <= 0) {
				if (loop) {
					// Infinite pingpong => inverse animation
					if (pingPong) {
						currentDirection = Direction.Backward;
						ellapsedTime *= -1;
					}
					// Loop, start reach => go to the end. ellapsedTime is negative.
					else {
						restartAt(getDuration() + ellapsedTime); 
					}
				}
				// Reached start of animation, no behavior => nothing
				else {
					ellapsedTime = 0;
				}
			}

			break;
		case Backward:
			ellapsedTime += delta;
			
			// End of animation reached
			if (ellapsedTime >= getDuration()) {
				currentDirection = Direction.Forward;
				
				ellapsedTime = 2 * getDuration() - ellapsedTime;
			}

			break;
		}

		updateCurrentFrame();
	}
	
	/**
	 * Draw the animation to the screen
	 */
	public void draw() {
		draw(0,0);
	}

	/**
	 * Draw the animation at a specific location
	 * 
	 * @param x The x position to draw the animation at
	 * @param y The y position to draw the animation at
	 */
	public void draw(float x,float y) {
		draw(x,y,getWidth(),getHeight());
	}

	/**
	 * Draw the animation at a specific location
	 * 
	 * @param x The x position to draw the animation at
	 * @param y The y position to draw the animation at
	 * @param filter The filter to apply
	 */
	public void draw(float x,float y, Color filter) {
		draw(x,y,getWidth(),getHeight(), filter);
	}
	
	/**
	 * Draw the animation
	 * 
	 * @param x The x position to draw the animation at
	 * @param y The y position to draw the animation at
	 * @param width The width to draw the animation at
	 * @param height The height to draw the animation at
	 */
	public void draw(float x,float y,float width,float height) {
		draw(x,y,width,height,Color.white);
	}
	
	/**
	 * Draw the animation
	 * 
	 * @param x The x position to draw the animation at
	 * @param y The y position to draw the animation at
	 * @param width The width to draw the animation at
	 * @param height The height to draw the animation at
	 * @param col The color filter to use
	 */
	public void draw(float x, float y, float width, float height, Color col) {
		
		// Center the animation
		x -= width / 2;
		y -= height / 2;
		
		animation[currentFrame].frame.draw(x, y, width, height, col);
	}

}
