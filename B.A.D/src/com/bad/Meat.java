/**
 * 
 */
package com.bad;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Kyle Doyle
 *
 */
public class Meat {
	public static Texture meatTexture;
	public static Vector2 pos;
	public static float velocity;
	public static Rectangle hitBox;
	
	public Meat(){
		super();
		velocity = 2.0f;
		meatTexture = new Texture("assets/Wedding-Bears.png");
		pos = new Vector2(RandomFighterGame.width - meatTexture.getWidth(), RandomFighterGame.height - meatTexture.getHeight());
		hitBox = new Rectangle(pos.x, pos.y, meatTexture.getWidth() + pos.x, meatTexture.getHeight() + pos.y); 
	}
	
	public void update(float delta){
		pos.sub(velocity * delta, velocity * delta);
	}
	
	public boolean checkForCollisions(Rectangle collidingBox){
		if(collidingBox.overlaps(hitBox)){
			return true;
		} else {
			return false;
		}
	}
}
