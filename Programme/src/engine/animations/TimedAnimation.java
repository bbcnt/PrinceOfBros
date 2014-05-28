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
 * Represent a succession of frames to manage as an animation. Use this to hold
 * sprites and durations for an animation.
 * 
 * This class was created to replace the Slick's Animation class who was not
 * sufficient for our application.
 * 
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class TimedAnimation {
	
	private int currentTime;
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
	
	/**
	 * Same as {@link #TimedAnimation(Image[], int[], boolean, boolean)} with
	 * loop parameter set to false and pingpong set to false.
	 * 
	 * @param frames - the frames to use for the animation.
	 * @param durations - the durations in millis.
	 */
	public TimedAnimation(Image[] frames, int[] durations) {
		this(frames, durations, false, false);
	}
	
	/**
	 * Same as {@link #TimedAnimation(Image[], int[], boolean, boolean)} with
	 * pingpong parameter set to false.
	 * 
	 * @param frames - the frames to use for the animation.
	 * @param durations - the durations in millis.
	 * @param loop - if the animation restart after ending.
	 */
	public TimedAnimation(Image[] frames, int[] durations, boolean loop) {
		this(frames, durations, loop, false);
	}
	
	
	/**
	 * Construct an animation from the given frames. Each frame is displayed
	 * during the time in millis given by the value at the same index in the
	 * duration tab. The animation while run indefinitely if loop is set to true,
	 * and / or will run backward to initial state if pingpong is set to true. If
	 * pingPong is set to true, the animation will read frames forward then
	 * backward only once, excepts if the loop parameter is true.
	 * 
	 * <p>
	 * You have to give exactly one duration per frame.
	 * 
	 * @param frames - the frames to use for the animation.
	 * @param durations - the durations in millis.
	 * @param loop - if the animation restart after ending.
	 * @param pingPong - if the animation is going backward after reaching the
	 * last frame.
	 */
	public TimedAnimation(Image[] frames, int[] durations, boolean loop, boolean pingPong) {
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
		currentTime = 0;
		
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
	
	/**
	 * Return if the animation is finished or not.
	 * @return True if the animation is finished, false otherwise.
	 */
	public boolean isFinished() {
		return finished;
	}
	
	/**
	 * Return the ping-ponging state of the animation.
	 * @return True if the animation is ping-ponging, false otherwise.
	 */
	public boolean isPingPonging() {
		return pingPong;
	}
	
	/**
	 * Return the looping state of the animation.
	 * @return True if the animation is looping, false otherwise.
	 */
	public boolean isLooping() {
		return loop;
	}
	
	/**
	 * Return the duration of one pass of the animation.
	 * <p> A ping-ponging animation will have twice this value as total duration.
	 * @return The duraation of one pass.
	 */
	public int getDuration() {
		return animation[animation.length - 1].end;
	}
	
	/**
	 * Return the full duration of the animation. Same as {@link #getDuration()}
	 * excepts for ping-ponging animation who will return the time needed to
	 * run forward and backward.
	 * @return The full duration in millis of the animation.
	 */
	public int getFullDuration() {
		return getDuration() * (pingPong ? 2 : 1);
	}
	
	/**
	 * Return the current time of the animation. The value is between 0 and
	 * {@link #getDuration()}.
	 * <p>
	 * A ping-ponging animation will have a value from {@link #getDuration()} to
	 * 0 when running backward to the initial frame.
	 * @return The current time of the animation.
	 */
	public int getCurrentTime() {
		return currentTime;
	}
	
	/**
	 * Return the remaining time in millis to reach the last animation's frame.
	 * <p>
	 * A ping-ponging animation will have a value corresponding to the current
	 * running of the animation (forward or backward), not relating to the real
	 * remaining time. 
	 * @return The remaining time in millis.
	 */
	public int getRemainingTime() {
		return getDuration() - getCurrentTime();
	}
	
	/**
	 * Return the full remaining time of the animation. Same as {@link #getRemainingTime()}
	 * excepts for ping-ponging animation who will return the remaining time to
	 * go back to the initial state.
	 * @return The remaining time in millis.
	 */
	public int getFullRemainingTime() {
		if (pingPong && currentDirection == Direction.Forward) {
			return getRemainingTime() + getDuration();
		}
		else {
			return getRemainingTime();
		}
	}
	
	/**
	 * Return the current frame's width.
	 * @return The width of the current frame.
	 */
	public int getWidth() {
		return animation[currentFrame].frame.getWidth();
	}
	
	/**
	 * Return the current frame's height.
	 * @return The height of the current frame.
	 */
	public int getHeight() {
		return animation[currentFrame].frame.getHeight();
	}
	
	/**
	 * Return the speed of the animation.
	 * @return The speed of the animation.
	 */
	public float getSpeed() {
		return speed;
	}
	
	/*---Modifiers-------------------------------------------------------------*/
	
	/**
	 * Set the looping state of the animation. True will cause the animation
	 * running indefinitely.
	 * @param loop - enable or not the looping state.
	 */
	public void setLooping(boolean loop) {
		this.loop = loop;
	}
	
	/**
	 * Set the ping-pong state of the animation. True will cause the animation
	 * to execute in reverse order when reaching the last frame.
	 * @param pingPong - enable or not the ping-ponging state.
	 */
	public void setPingPong(boolean pingPong) {
		this.pingPong = pingPong;
	}
	
	/**
	 * Set the speed of the animation. Normal speed is 1.0, greater value will
	 * run it faster, smaller value will run it slower. Does nothing if the value
	 * is negative.
	 * @param speed - the animation's speed.
	 */
	public void setSpeed(float speed) {
		if (speed > 0) {
			this.speed = speed;
		}
	}
	
	/**
	 * Restart the animation.
	 */
	public void restart() {
		restartAt(0);
	}
	
	/**
	 * Restart the animation at the specified time.
	 * @param time - the wanted time in millis.
	 */
	public void restartAt(int time) {
		currentDirection = Direction.Forward;
		idleTime = 0;
		finished = false;
		setCurrentTime(time);
	}
	
	/**
	 * Force the animation current frame to the given time.
	 * @param time - the wanted time in millis.
	 */
	public void setCurrentTime(int time) {
		if (loop) {
			// Loop + ping pong animation => time depends of the number of loops
			if (pingPong) {
				int nbLoop = Math.abs(time / getDuration());
				
				if (time >= 0) {
					if (nbLoop % 2 == 0) {
						currentTime = time % getDuration();
						currentDirection = Direction.Forward;
					}
					else {
						currentTime = 2 * getDuration() - (time % getDuration());
						currentDirection = Direction.Backward;
					}
				}
				else {
					if (nbLoop % 2 == 0) {
						currentTime = (-time) % getDuration();
						currentDirection = Direction.Backward;
					}
					else {
						while(time < 0) time += getDuration(); // Ensure correct modulus
						currentTime = time;
						currentDirection = Direction.Forward;
					}
				}
				
			}
			// Looping animation => time in modulus getDuration()
			else {
				while(time < 0) time += getDuration(); // Ensure correct modulus
				currentTime = time % getDuration();
				currentDirection = Direction.Forward;
			}
		}
		// Once animation => time in [0, getDuration()]
		else {
			currentTime = time < 0 ? 0 : Math.min(time, getDuration()) ; 
			currentDirection = Direction.Forward;
		}
		
		updateCurrentFrame();
	}
	
	/**
	 * Update the value of currentFrame by comparing the ellapsedTime and the
	 * bounds of neighboring frames. 
	 * <p>
	 * <b>Warning:</b> This function suppose that the currentTime is in
	 * [0, getDuration()], otherwise it may raise exceptions.
	 */
	private void updateCurrentFrame() {
		while (currentTime > animation[currentFrame].end) currentFrame++;
		while (currentTime < animation[currentFrame].start) currentFrame--;
	}
	
	/**
	 * Update the animation state.
	 * @param delta - the time in millis since the last update.
	 */
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
			currentTime += delta;
			
			// End of animation reached
			if (currentTime >= getDuration()) {
				
				// Pingpong => back to the start of the animation (inverse it)
				if (pingPong) {
					currentDirection = Direction.Backward;
					currentTime = 2 * getDuration() - currentTime;
				}
				// Loop => restart the animation
				else if (loop) {
					restartAt(currentTime % getDuration());
				}
				// No given behavior, ends the animation
				else {
					idleTime = currentTime - getDuration();
					currentTime = getDuration();
					finished = true;
				}
			}
			
			break;
		case Backward:
			currentTime -= delta;
			
			// Start of animation reached
			if (currentTime <= 0) {
				
				// Infinite pingPong => restart animation
				if (pingPong && loop) {
					restartAt(currentTime * -1);
				}
				// No given behavior, end of the animation
				else {
					idleTime = currentTime * -1;
					currentTime = 0;
					finished = true;
				}
			}
			
			break;
		}
		
		updateCurrentFrame();
	}
	
	/**
	 * Update animation in backward direction.
	 * @param delta - the millis since the last update.
	 */
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
			currentTime -= delta;
			
			// Start of animation reached
			if (currentTime <= 0) {
				if (loop) {
					// Infinite pingpong => inverse animation
					if (pingPong) {
						currentDirection = Direction.Backward;
						currentTime *= -1;
					}
					// Loop, start reach => go to the end. ellapsedTime is negative.
					else {
						restartAt(getDuration() + currentTime); 
					}
				}
				// Reached start of animation, no behavior => nothing
				else {
					currentTime = 0;
				}
			}

			break;
		case Backward:
			currentTime += delta;
			
			// End of animation reached
			if (currentTime >= getDuration()) {
				currentDirection = Direction.Forward;
				
				currentTime = 2 * getDuration() - currentTime;
			}

			break;
		}

		updateCurrentFrame();
	}
	
	/*---Graphics--------------------------------------------------------------*/
	
	/**
	 * Draw the animation, centered at the (0,0) position.
	 */
	public void draw() {
		draw(0,0);
	}

	/**
	 * Draw the animation, centered at the given position.
	 * 
	 * @param x - the x position of the animation's center.
	 * @param y - the y position of the animation's center.
	 */
	public void draw(float x,float y) {
		draw(x,y,getWidth(),getHeight());
	}
	
	/**
	 * Draw the animation, centered at the given position.
	 * 
	 * @param x - the x position of the animation's center.
	 * @param y - the y position of the animation's center.
	 * @param col - the color filter to use.
	 */
	public void draw(float x,float y, Color filter) {
		draw(x,y,getWidth(),getHeight(), filter);
	}
	
	/**
	 * Draw the animation, centered at the given position.
	 * 
	 * @param x - the x position of the animation's center.
	 * @param y - the y position of the animation's center.
	 * @param width - the width of the animation.
	 * @param height - the height of the animation.
	 */
	public void draw(float x,float y,float width,float height) {
		draw(x,y,width,height,Color.white);
	}
	
	/**
	 * Draw the animation, centered at the given position.
	 * 
	 * @param x - the x position of the animation's center.
	 * @param y - the y position of the animation's center.
	 * @param width - the width of the animation.
	 * @param height - the height of the animation.
	 * @param col - the color filter to use.
	 */
	public void draw(float x, float y, float width, float height, Color col) {
		
		// Center the animation
		x -= width / 2;
		y -= height / 2;
		
		animation[currentFrame].frame.draw(x, y, width, height, col);
	}

}
