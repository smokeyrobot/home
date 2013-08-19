package com.bad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * @author kyle.t.doyle
 *
 */
public class SasquatchAssets {

	public static TextureAtlas sasquatchAtlas;
	public static TextureRegion stand;
	public static TextureRegion stand2;
	public static TextureRegion stand3;
	public static TextureRegion stand4;
	public static TextureRegion punch1;
	public static TextureRegion punch2;
	public static TextureRegion punch3;
	public static float SASQ_LOW_PUNCH = 0.16f;
	public static float SASQ_STAND = 0.33f;
	public static float SASQ_WALK = 0.16f;
	public static Array<TextureRegion> punchAnim;
	public static Array<TextureRegion> standAnim;
	public static Array<TextureRegion> walkingAnim;
	public static Texture background;
	public static Texture splash;
	
	public static void load(){
		Texture sasquatchSMap = new Texture(Gdx.files.internal("assets/Sasquatch_clear.png"));
		sasquatchAtlas = new TextureAtlas(Gdx.files.internal("assets/Sasquatch.atlas"));
		background = new Texture(Gdx.files.internal("assets/moon_background.png"));
		
		stand = sasquatchAtlas.findRegion("stand1");
		stand2 = sasquatchAtlas.findRegion("stand2");
		stand3 = sasquatchAtlas.findRegion("stand3");
		stand4 = sasquatchAtlas.findRegion("stand4");
		
		punch1 = sasquatchAtlas.findRegion("punch1");
		punch2 = sasquatchAtlas.findRegion("punch2");
		punch3 = sasquatchAtlas.findRegion("punch3");
		
		punchAnim = new Array<TextureRegion>();
		punchAnim.add(punch1);
		punchAnim.add(punch2);
		punchAnim.add(punch3);
		
		standAnim = new Array<TextureRegion>();
		standAnim.add(stand);
		standAnim.add(stand2);
		standAnim.add(stand3);
//		standAnim.add(stand4);
		
		walkingAnim = new Array<TextureRegion>();
		
		for(int i=0; i < 12; i++){
			walkingAnim.add(new TextureRegion(sasquatchSMap, 10+(i*136), 136, 136, 100));
		}
	}
	
	
	
	public static void dispose(){
		sasquatchAtlas.dispose();
		background.dispose();
//		splash.dispose();
	}
}