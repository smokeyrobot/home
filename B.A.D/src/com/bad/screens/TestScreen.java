/**
 * 
 */
package com.bad.screens;

import com.bad.Meat;
import com.bad.RandomFighterGame;
import com.bad.fighters.Sasquatch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * @author kyle.t.doyle
 *
 */
public class TestScreen extends GameScreen {
	
	SpriteBatch batch;
	
	Texture background;
	Texture jumpTest;
	Sasquatch sasq;
	
	Meat testMeat;
	
	public TestScreen(Game game) {
		super(game);
		testMeat = new Meat();
		sasq = new Sasquatch(new Vector2(0,0), 133, 100); //the width and height should be set dynamically
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//should not be here
		testMeat.update(Gdx.graphics.getDeltaTime());
		sasq.processInput(delta);
		
		batch.begin();
		batch.draw(background, 0, 0, RandomFighterGame.WIDTH, RandomFighterGame.HEIGHT);
		batch.draw(sasq.getCurrentFrame(), sasq.getPosition().x, sasq.getPosition().y, 0, 0, sasq.getCurrentFrame().getRegionWidth(), sasq.getCurrentFrame().getRegionHeight(), Sasquatch.sasquatchScale, Sasquatch.sasquatchScale, 0);
		batch.draw(Meat.meatTexture, Meat.pos.x, Meat.pos.y, Meat.meatTexture.getHeight()/2, Meat.meatTexture.getWidth()/2);
		batch.end();
	}

	@Override
	public void show() {
		background = new Texture(Gdx.files.internal("assets/moon_background.png"));
		jumpTest = new Texture(Gdx.files.internal("assets/jump_test.png"));
		
		batch = new SpriteBatch();
	}

	@Override
	public void dispose() {
		Gdx.app.debug("Random", "dispose test screen");
		batch.dispose();
		background.dispose();
	}

}
