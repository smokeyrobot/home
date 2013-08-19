package com.smokeyrobot;

import com.bad.RandomFighterGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * @author kyle.t.doyle
 *
 */
public class DesktopGame {
    public static void main (String[] args) {
    	LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    	config.height = 480;
    	config.width = 800;
    	config.title = "Random Fighter";
    	config.useGL20 = true;
        new LwjglApplication(new RandomFighterGame(), config);
    }
}
