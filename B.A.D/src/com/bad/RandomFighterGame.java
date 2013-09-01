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

	public final static int HEIGHT = 480;
	public final static int WIDTH = 800;
	public final static int LEFT = -1;
	public final static int RIGHT = 1;
	public final static int UP = 1;
	public final static int DOWN = -1;
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
