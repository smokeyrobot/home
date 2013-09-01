package com.bad;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * @author Kyle
 *
 */
public class FighterAnimation {
	
	Array<TextureRegion> keyFrames;
	Animation anim;
	float frameDuration;

	/**
	 * Default constructor when no play mode is passed
	 * @param frameDuration
	 * @param keyFrames
	 */
	public FighterAnimation(float frameDuration, TextureRegion... keyFrames) {
		this.keyFrames = new Array<TextureRegion>();
		this.frameDuration = frameDuration;
		for(TextureRegion keyFrame : keyFrames){
			this.keyFrames.add(keyFrame);
		}
		anim = new Animation(frameDuration, this.keyFrames, Animation.NORMAL);
	}
	
	/**
	 * All animation parameters are passed
	 * @param frameDuration
	 * @param playMode
	 * @param keyFrames
	 */
	public FighterAnimation(float frameDuration, int playMode, TextureRegion... keyFrames) {
		this.keyFrames = new Array<TextureRegion>();
		this.frameDuration = frameDuration;
		for(TextureRegion keyFrame : keyFrames){
			this.keyFrames.add(keyFrame);
		}
		anim = new Animation(frameDuration, this.keyFrames, playMode);
	}
	
	/**
	 * Constructor to pass Array<TextureRegion>
	 * @param frameDuration
	 * @param tempTextures
	 */
	public FighterAnimation(float frameDuration, int playMode, Array<TextureRegion> keyFrames) {
		this.keyFrames = new Array<TextureRegion>();
		this.frameDuration = frameDuration;
		for(TextureRegion keyFrame : keyFrames){
			this.keyFrames.add(keyFrame);
		}
		anim = new Animation(frameDuration, this.keyFrames, playMode);
	}
	
	/**
	 * Constructor to pass Array<TextureRegion>
	 * @param frameDuration
	 * @param tempTextures
	 */
	public FighterAnimation(float frameDuration, Array<TextureRegion> keyFrames) {
		this.keyFrames = new Array<TextureRegion>();
		this.frameDuration = frameDuration;
		for(TextureRegion keyFrame : keyFrames){
			this.keyFrames.add(keyFrame);
		}
		anim = new Animation(frameDuration, this.keyFrames, Animation.NORMAL);
	}

	/**
	 * Method to add frames on to the animation (this may not be necessary)
	 * @param keyFrames
	 */
	public void addFrames(TextureRegion... keyFrames){
		
		if(this.keyFrames == null){
			this.keyFrames = new Array<TextureRegion>();
		}
		
		for(TextureRegion keyFrame : keyFrames){
			this.keyFrames.add(keyFrame);
		}
		anim = new Animation(frameDuration, this.keyFrames, anim.getPlayMode());
	}
	
	public Array<TextureRegion> getKeyFrames() {
		return keyFrames;
	}
	
	public void changeFrameDuration(float frameDuration){
		anim = new Animation(frameDuration, getKeyFrames(), anim.getPlayMode());
	}
	
	public int getPlayMode(){
		return this.anim.getPlayMode();
	}
	
	public void setPlayMode(int playMode){
		anim = new Animation(frameDuration, getKeyFrames(), playMode);
	}
	
	public Animation getAnimation(){
		return this.anim;
	}
}
