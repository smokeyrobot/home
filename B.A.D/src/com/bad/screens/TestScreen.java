/**
 * 
 */
package com.bad.screens;

import com.bad.Meat;
import com.bad.RandomFighterGame;
import com.bad.fighters.Sasquatch;
import com.bad.fighters.Sasquatch.SasquatchMoves;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author kyle.t.doyle
 *
 */
public class TestScreen extends GameScreen {
	
	SpriteBatch batch;
	
	Texture background;
	Texture jumpTest;
	TextureRegion currentFrame;
	Animation stand;
	Animation punch;
	Animation walking;
	Animation walkingBackwards;
	Animation tumble;
	Sasquatch sasq;
	
	int sasquatchScale;
	int sasquatchPosX;
	int sasquatchPosY;
	int sasquatchVelocity;
	
	float stateTime;
	float punchTime;
	boolean actionAnimation;
	
	Meat testMeat;
	
	public TestScreen(Game game) {
		super(game);
		sasquatchPosX = 0;
		sasquatchPosY = 0;
		sasquatchVelocity = 300;
		testMeat = new Meat();
		sasq = new Sasquatch();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		//should not be here
		testMeat.update(Gdx.graphics.getDeltaTime());
		
		if(actionAnimation){
			//end punching
			if(stateTime >= punchTime){
				actionAnimation = false;
				currentFrame = punch.getKeyFrame(stateTime);
				Gdx.app.debug("Random"," Exit - Punch Frame: " + punch.getKeyFrameIndex(stateTime) + " StateTime: " + stateTime + "\nPunch Time: " + punchTime + "\n\n");
			}
			else{
				currentFrame = punch.getKeyFrame(stateTime);
				//check for collision here
				Gdx.app.debug("Random","IN punch - Punch Frame: " + punch.getKeyFrameIndex(stateTime) + " StateTime: " + stateTime + "\nPunch Time: " + punchTime + "\n\n");
			}
		} else {
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
				System.exit(0);
			} else if(Gdx.input.isKeyPressed(Keys.LEFT)){
				currentFrame = walking.getKeyFrame(stateTime, true);
				sasquatchPosX = (int) (sasquatchPosX-(sasquatchVelocity*Gdx.graphics.getDeltaTime()));
			} else if(Gdx.input.isKeyPressed(Keys.UP)){
				currentFrame = tumble.getKeyFrame(stateTime, true);
				sasquatchPosX = (int) (sasquatchPosX+(sasquatchVelocity*Gdx.graphics.getDeltaTime()));
				sasquatchPosY = (int) (sasquatchPosY+(sasquatchVelocity*Gdx.graphics.getDeltaTime()));
			} else if(Gdx.input.isKeyPressed(Keys.RIGHT)){
				//have to animate similar to punch
				currentFrame = walking.getKeyFrame(stateTime, true);
				sasquatchPosX = (int) (sasquatchPosX+(sasquatchVelocity*Gdx.graphics.getDeltaTime()));
			} else if(Gdx.input.isKeyPressed(Keys.SPACE)) {
				stateTime = 0.0f;
				currentFrame = punch.getKeyFrame(stateTime);
				punchTime = (Sasquatch.SASQ_LOW_PUNCH * Sasquatch.moveAnims.get(SasquatchMoves.LOW_PUNCH).size) + stateTime;
				Gdx.app.debug("Random","Key Press - Punch Frame: " + punch.getKeyFrameIndex(stateTime) + " StateTime: " + stateTime + "\nPunch Time: " + punchTime + "\n\n");
				actionAnimation = true;
			} else if (Gdx.input.isKeyPressed(Keys.ENTER)){

			} else {
				currentFrame = stand.getKeyFrame(stateTime, true);
			}
		}
		
		batch.begin();
		batch.draw(background, 0, 0, RandomFighterGame.width, RandomFighterGame.height);
		batch.draw(currentFrame, sasquatchPosX, sasquatchPosY, 0, 0, currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), this.sasquatchScale, this.sasquatchScale, 0);
		batch.draw(Meat.meatTexture, Meat.pos.x, Meat.pos.y, Meat.meatTexture.getHeight()/2, Meat.meatTexture.getWidth()/2);
		batch.end();
	}

	@Override
	public void show() {
		background = new Texture(Gdx.files.internal("assets/moon_background.png"));
		jumpTest = new Texture(Gdx.files.internal("assets/jump_test.png"));
		
		batch = new SpriteBatch();
//		batch.getProjectionMatrix().setToOrtho2D(0, 0, RandomFighterGame.width, RandomFighterGame.height);
		
		punch = new Animation(Sasquatch.SASQ_LOW_PUNCH, Sasquatch.moveAnims.get(SasquatchMoves.LOW_PUNCH));
		stand = new Animation(Sasquatch.SASQ_STAND, Sasquatch.moveAnims.get(SasquatchMoves.STAND), Animation.LOOP);
		walking = new Animation(Sasquatch.SASQ_WALK, Sasquatch.moveAnims.get(SasquatchMoves.WALKING), Animation.LOOP);
		walkingBackwards = new Animation(Sasquatch.SASQ_WALK, Sasquatch.moveAnims.get(SasquatchMoves.WALKING), Animation.LOOP_REVERSED);
		tumble = new Animation(Sasquatch.SASQ_TUMBLE, Sasquatch.moveAnims.get(SasquatchMoves.TUMBLE), Animation.LOOP);
		sasquatchScale = 2;
	}

	@Override
	public void dispose() {
		Gdx.app.debug("Random", "dispose test screen");
		batch.dispose();
		background.dispose();
	}

}
