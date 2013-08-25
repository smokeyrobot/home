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
	public static float SASQ_TUMBLE = 0.33f;
	public static Array<TextureRegion> punchAnim;
	public static Array<TextureRegion> standAnim;
	public static Array<TextureRegion> walkingAnim;
	public static Array<TextureRegion> tumbleAnim;
	public static Texture background;
	public static Texture splash;
	
	public static void load(){
		Texture sasquatchSMap = new Texture(Gdx.files.internal("assets/Sasquatch_clear.png"));
		sasquatchAtlas = new TextureAtlas(Gdx.files.internal("assets/Sasquatch.atlas"));
		background = new Texture(Gdx.files.internal("assets/moon_background.png"));
		
		int textureOffset = 10;
		
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
		tumbleAnim = new Array<TextureRegion>();
		
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
			walkingAnim.add(new TextureRegion(sasquatchSMap, positionX, 136, 136, 100));
			int rightX = positionX+136+sizeOffset;
			Gdx.app.debug("position check - tumble","i: " + i + " Left X: " + positionX + " Right X: " + rightX);
		}
		
		textureOffset = 1655;
		for(int i=0; i < 4; i++){
			if(i > 1){
				sizeOffset += 10;
			} else if(i == 1){
				sizeOffset += 7;
			}
			positionX = textureOffset+(i*115) + sizeOffset;
			tumbleAnim.add(new TextureRegion(sasquatchSMap, positionX, 155, 115+sizeOffset, 90));
		}
		
	}
	
	
	
	public static void dispose(){
		sasquatchAtlas.dispose();
		background.dispose();
//		splash.dispose();
	}
}