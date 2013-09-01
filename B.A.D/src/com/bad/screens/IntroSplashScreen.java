/**
 * 
 */
package com.bad.screens;

import com.bad.RandomFighterGame;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author kyle.t.doyle
 *
 */
public class IntroSplashScreen extends GameScreen {

	SpriteBatch batch;
	Texture splash;
	float time = 0.0f;
	
	public IntroSplashScreen(Game game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(splash, 0, 0, 800, 480);
		batch.end();
		
		time += delta;
		if(time > 1) {
			if(Gdx.input.isKeyPressed(Keys.ANY_KEY)){
				game.setScreen(new TestScreen(game));
			}
		}
	}

	@Override
	public void show() {
		splash = new Texture(Gdx.files.internal("assets/splash_screen.png"));
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, RandomFighterGame.WIDTH, RandomFighterGame.HEIGHT);
	}

	@Override
	public void dispose() {
		Gdx.app.debug("Random", "dispose intro splash");
		batch.dispose();
		splash.dispose();
	}

}
