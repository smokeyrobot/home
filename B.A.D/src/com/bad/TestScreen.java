/**
 * 
 */
package com.bad;

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
	TextureRegion currentFrame;
	Animation stand;
	Animation punch;
	Animation walking;
	
	int sasquatchScale;
	
	float stateTime;
	float punchTime;
	boolean actionAnimation;
	
	public TestScreen(Game game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		
		if(actionAnimation){
			//end punching
			if(stateTime >= punchTime){
				actionAnimation = false;
				currentFrame = punch.getKeyFrame(stateTime);
				Gdx.app.debug("Random"," Exit - Punch Frame: " + punch.getKeyFrameIndex(stateTime) + " StateTime: " + stateTime + "\nPunch Time: " + punchTime + "\n\n");
			}
			else{
				currentFrame = punch.getKeyFrame(stateTime);
				Gdx.app.debug("Random","IN punch - Punch Frame: " + punch.getKeyFrameIndex(stateTime) + " StateTime: " + stateTime + "\nPunch Time: " + punchTime + "\n\n");
			}
		} else {
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
				System.exit(0);
			} else if(Gdx.input.isKeyPressed(Keys.RIGHT)){
				currentFrame = walking.getKeyFrame(stateTime, true);
			}
			else if(Gdx.input.isKeyPressed(Keys.SPACE)) {
				stateTime = 0.0f;
				currentFrame = punch.getKeyFrame(stateTime);
				punchTime = (SasquatchAssets.SASQ_LOW_PUNCH * SasquatchAssets.punchAnim.size) + stateTime;
				Gdx.app.debug("Random","Key Press - Punch Frame: " + punch.getKeyFrameIndex(stateTime) + " StateTime: " + stateTime + "\nPunch Time: " + punchTime + "\n\n");
				actionAnimation = true;
			}
			else {
				currentFrame = stand.getKeyFrame(stateTime, true);
			}
		}
		
		batch.begin();
		batch.draw(background, 0, 0, RandomFighterGame.width, RandomFighterGame.height);
		batch.end();
	}

	@Override
	public void show() {
		background = new Texture(Gdx.files.internal("assets/moon_background.png"));
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, RandomFighterGame.width, RandomFighterGame.height);
		
		punch = new Animation(SasquatchAssets.SASQ_LOW_PUNCH, SasquatchAssets.punchAnim);
		stand = new Animation(SasquatchAssets.SASQ_STAND, SasquatchAssets.standAnim, Animation.LOOP);
		walking = new Animation(SasquatchAssets.SASQ_WALK, SasquatchAssets.walkingAnim, Animation.LOOP);
		sasquatchScale = 2;
	}

	@Override
	public void dispose() {
		Gdx.app.debug("Random", "dispose test screen");
		batch.dispose();
		background.dispose();
	}

}
