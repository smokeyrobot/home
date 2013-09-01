package com.bad.fighters;

import java.util.HashMap;
import java.util.Vector;

import com.bad.FighterAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * @author Kyle
 *
 */
public class Sasquatch extends Fighter {
	
	public static TextureAtlas sasquatchAtlas;
	
	//Look to refactor these into one value and derive the frame movement speeds from there
	public static float SASQ_STAND = 0.33f;
	public static float SASQ_WALK = 0.16f;
	public static float SASQ_TUMBLE = 0.33f;
	
	public static Array<TextureRegion> tempTextures;
	
	public static Texture background;
	public static Texture splash;
	public static Texture sasquatchSMap;
	
	public static HashMap<SasquatchMoves,FighterAnimation> moveAnims;
	
	boolean actionAnimation;
	
	TextureRegion currentFrame;

	public static int sasquatchScale = 1;
	static int sasqVelocity;
	
	float stateTime;
	static float punchTime;
	
	public enum SasquatchMoves
	{
		STAND,
		LOW_PUNCH,
		WALKING,
		TUMBLE,
		WALKING_BACKWARDS
	};
	
	public Sasquatch(){
		super();
		load();
		setPosition(new Vector2(0,0));
	}
	
	public Sasquatch(Vector2 coords, float width, float height) {
		super(coords, width, height, sasqVelocity);
		load();
		setPosition(new Vector2(0,0));
	}
	
	public void processInput(float delta){
		if(actionAnimation){
			//end punching
			if(stateTime >= punchTime){
				actionAnimation = false;
				currentFrame = moveAnims.get(SasquatchMoves.LOW_PUNCH).getAnimation().getKeyFrame(stateTime);
				Gdx.app.debug("Random"," Exit - Punch Frame: " + moveAnims.get(SasquatchMoves.LOW_PUNCH).getAnimation().getKeyFrameIndex(stateTime) + " StateTime: " + stateTime + "\nPunch Time: " + punchTime + "\n\n");
			}
			else{
				currentFrame = moveAnims.get(SasquatchMoves.LOW_PUNCH).getAnimation().getKeyFrame(stateTime);
				//check for collision here
				Gdx.app.debug("Random","IN punch - Punch Frame: " + moveAnims.get(SasquatchMoves.LOW_PUNCH).getAnimation().getKeyFrameIndex(stateTime) + " StateTime: " + stateTime + "\nPunch Time: " + punchTime + "\n\n");
			}
		} else {
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
				System.exit(0);
			} else if(Gdx.input.isKeyPressed(Keys.LEFT)){
				currentFrame = moveAnims.get(SasquatchMoves.WALKING).getAnimation().getKeyFrame(stateTime, true);
				position.x = (int) (position.x-(velocity*delta));
			} else if(Gdx.input.isKeyPressed(Keys.UP)){
				currentFrame = moveAnims.get(SasquatchMoves.TUMBLE).getAnimation().getKeyFrame(stateTime, true);
				position.x = (int) (position.x+(velocity*delta));
				position.y = (int) (position.y+(velocity*delta));
			} else if(Gdx.input.isKeyPressed(Keys.RIGHT)){
				//have to animate similar to punch
				currentFrame = moveAnims.get(SasquatchMoves.WALKING).getAnimation().getKeyFrame(stateTime, true);
				position.x = (int) (position.x+(velocity*delta));
			} else if(Gdx.input.isKeyPressed(Keys.SPACE)) {
				stateTime = 0.0f;
				currentFrame = moveAnims.get(SasquatchMoves.LOW_PUNCH).getAnimation().getKeyFrame(stateTime);
				punchTime = (Sasquatch.moveAnims.get(SasquatchMoves.LOW_PUNCH).getAnimation().frameDuration * Sasquatch.moveAnims.get(SasquatchMoves.LOW_PUNCH).getKeyFrames().size) + stateTime;
				Gdx.app.debug("Random","Key Press - Punch Frame: " + moveAnims.get(SasquatchMoves.LOW_PUNCH).getAnimation().getKeyFrameIndex(stateTime) + " StateTime: " + stateTime + "\nPunch Time: " + punchTime + "\n\n");
				actionAnimation = true;
			} else if (Gdx.input.isKeyPressed(Keys.ENTER)){

			} else {
				currentFrame = moveAnims.get(SasquatchMoves.STAND).getAnimation().getKeyFrame(stateTime, true);
			}
		}
		
	}
	
	//Use this method in the Renderer class to draw
	public void draw(float delta){
		
	}
	
	public void update(float delta){
		stateTime += delta;
	}

	public static void load(){
		moveAnims = new HashMap<SasquatchMoves,FighterAnimation>();
		sasquatchSMap = new Texture(Gdx.files.internal("assets/Sasquatch_clear.png"));
		sasquatchAtlas = new TextureAtlas(Gdx.files.internal("assets/Sasquatch.atlas"));
		background = new Texture(Gdx.files.internal("assets/moon_background.png"));
		
		punchTime = 0.0f;
		int textureOffset = 10;
		
		moveAnims.put(SasquatchMoves.LOW_PUNCH, 
				new FighterAnimation(
						0.16f,
						sasquatchAtlas.findRegion("punch1"),
						sasquatchAtlas.findRegion("punch2"),
						sasquatchAtlas.findRegion("punch3")
				));
		
		moveAnims.put(SasquatchMoves.STAND, 
				new FighterAnimation(
						SASQ_STAND,
						sasquatchAtlas.findRegion("stand1"),
						sasquatchAtlas.findRegion("stand2"),
						sasquatchAtlas.findRegion("stand3"),
						sasquatchAtlas.findRegion("stand4")
				));
		
		tempTextures = new Array<TextureRegion>();
		int positionX;
		int sizeOffset = 0;
		for(int i=0; i < 12; i++){ 	
			textureOffset = 10;
			if(i == 5){
				textureOffset += 12;
			} else if (i == 3 || i == 7){
				textureOffset += 5;
			}
			positionX =  textureOffset+(i*136);
			tempTextures.add(new TextureRegion(sasquatchSMap, positionX, 136, 136, 100));
			int rightX = positionX+136+sizeOffset;
			Gdx.app.debug("position check - tumble","i: " + i + " Left X: " + positionX + " Right X: " + rightX);
		}
		
		moveAnims.put(SasquatchMoves.WALKING,
				new FighterAnimation(SASQ_WALK,tempTextures));
		moveAnims.put(SasquatchMoves.WALKING_BACKWARDS,
				new FighterAnimation(SASQ_WALK, Animation.REVERSED, tempTextures));
		
		tempTextures = new Array<TextureRegion>();
		textureOffset = 1655;
		for(int i=0; i < 4; i++){
			if(i > 1){
				sizeOffset += 10;
			} else if(i == 1){
				sizeOffset += 7;
			}
			positionX = textureOffset+(i*115) + sizeOffset;
			tempTextures.add(new TextureRegion(sasquatchSMap, positionX, 155, 115+sizeOffset, 90));
		}
		
		moveAnims.put(SasquatchMoves.TUMBLE,
				new FighterAnimation(SASQ_TUMBLE,tempTextures));
	}
	
	
	
	public static void dispose(){
		sasquatchAtlas.dispose();
		background.dispose();
//		splash.dispose();
	}

	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}

}
