package com.bad;

import com.bad.fighters.Sasquatch;
import com.bad.screens.IntroSplashScreen;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * @author kyle.t.doyle
 *
 */
public class RandomFighterGame extends Game {

	public final static int height = 480;
	public final static int width = 800;
	public IntroSplashScreen introScreen;
	
	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		introScreen = new IntroSplashScreen(this);
		this.setScreen(introScreen);
	}

	@Override
	public void dispose() {
		Sasquatch.dispose();
		introScreen.dispose();
	}

}
