package engine.models.player;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.awt.*;

import engine.animations.IAnimatedObject;
import engine.animations.IAnimatedState;
import engine.animations.IAnimationType;

public class Player implements IAnimatedObject {
	
	private static IAnimatedState[][] animatedStates;
	
	private float posX;
	private float posY;
	
	private float speed = .1f;
	
	private IAnimatedState[] states;
	
	/*---Constructors----------------------------------------------------------*/
	
	public Player(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	/*---Getters---------------------------------------------------------------*/
	
	public float getPosX() {
		return posX;
	}
	
	public float getPosY() {
		return posY;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	/*---Modifiers-------------------------------------------------------------*/
	
	public void moveLeft(float value) {
		posX += value;
	}
	
	public void moveRight(float value) {
		posX -= value;
	}
	
	public void moveUp(float value) {
		posY += value;
	}
	
	public void moveDown(float value) {
		posY -= value;
	}
	
	/*---Inherit from interfaces-----------------------------------------------*/
	
	public void initIdleStates(IAnimatedState[] states) {
		// TODO
	}

	@Override
   public void initAnimationStates(IAnimatedState[] states) {
		
		// No use to init global multiple times for the same type of object
		if (animatedStates == null) {
			List<List<IAnimatedState>> types = new LinkedList<>();
			
			int typeId;
			for (IAnimatedState s : states) {
				typeId = s.getType().getTypeId();
				
				while (types.size() <= typeId) {
					types.add(new LinkedList<IAnimatedState>());
				}
				
				types.get(typeId).add(s);
			}
			
			animatedStates = new IAnimatedState[types.size()][0];
			
			List<IAnimatedState> current;
			for (int i = 0 ; i < animatedStates.length ; i++) {
				current = types.get(i);
				
				// Sort by ID ascendent order
				Collections.sort(current, new Comparator<IAnimatedState>() {
					@Override
	            public int compare(IAnimatedState o1, IAnimatedState o2) {
		            return o1.getId() - o2.getId();
	            }
				});
				
				animatedStates[i] = current.toArray(new IAnimatedState[current.size()]);
			}
		}
		
		// Now init local values to default state
		this.states = new IAnimatedState[animatedStates.length];
		
		for (int i = 0 ; i < this.states.length ; i++) {
			this.states[i] = animatedStates[i][0];
		}
   }

	@Override
   public boolean setAnimation(IAnimatedState state) {
		
		if (state == null) return false;
		
		IAnimatedState current = getState(state.getType());
		if (current == null) return false;
		
		states[current.getType().getTypeId()] = state;
		return true;
   }
	
	@Override
	public IAnimatedState getState(IAnimationType type) {
		if (type.getTypeId() < 0 || type.getTypeId() >= states.length) return null;
		
		return states[type.getTypeId()];
	}
}
