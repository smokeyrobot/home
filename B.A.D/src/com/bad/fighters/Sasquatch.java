/**
 * 
 */
package com.bad.fighters;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * @author Kyle
 *
 */
public class Sasquatch extends Fighter {
	
	public Sasquatch(){
		super();
		load();
	}
	
	public Sasquatch(float x, float y, float width, float height) {
		super(x, y, width, height);
		load();
		// TODO Auto-generated constructor stub
	}
	
	public Sasquatch(Vector2 coords, float width, float height) {
		super(coords, width, height);
		load();
		// TODO Auto-generated constructor stub
	}

	public enum SasquatchMoves
	{
		STAND,
		LOW_PUNCH,
		WALKING,
		TUMBLE
	};
	
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
	public static Array<TextureRegion> tempTextures;
	public static Texture background;
	public static Texture splash;
	public static Texture sasquatchSMap;
	public static HashMap<SasquatchMoves,Array<TextureRegion>> moveAnims;
	
	public static void load(){
		moveAnims = new HashMap<SasquatchMoves,Array<TextureRegion>>();
		sasquatchSMap = new Texture(Gdx.files.internal("assets/Sasquatch_clear.png"));
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
		
		tempTextures = new Array<TextureRegion>();
		tempTextures.add(punch1);
		tempTextures.add(punch2);
		tempTextures.add(punch3);
		
		moveAnims.put(SasquatchMoves.LOW_PUNCH, tempTextures);
		
		tempTextures = new Array<TextureRegion>();
		tempTextures.add(stand);
		tempTextures.add(stand2);
		tempTextures.add(stand3);
		tempTextures.add(stand4);
		
		moveAnims.put(SasquatchMoves.STAND, tempTextures);
		
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
		
		moveAnims.put(SasquatchMoves.WALKING, tempTextures);
		
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
		
		moveAnims.put(SasquatchMoves.TUMBLE, tempTextures);
		
	}
	
	
	
	public static void dispose(){
		sasquatchAtlas.dispose();
		background.dispose();
//		splash.dispose();
	}

}
