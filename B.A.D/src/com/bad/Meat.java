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
	public static float scale;
	public static int directionX;
	public static int directionY;
	
	public Meat(){
		super();
		velocity = 50.0f;
		scale = 0.5f;
		directionX = RandomFighterGame.LEFT;
		directionY = RandomFighterGame.DOWN;
		meatTexture = new Texture("assets/Wedding-Bears.png");
		pos = new Vector2(RandomFighterGame.WIDTH - meatTexture.getWidth(), RandomFighterGame.HEIGHT - meatTexture.getHeight());
		hitBox = new Rectangle(pos.x, pos.y, meatTexture.getWidth() + pos.x, meatTexture.getHeight() + pos.y); 
	}
	
	public void update(float delta){
		if(pos.x +  (velocity * delta * directionX) < 0 && directionX == RandomFighterGame.LEFT){
			directionX = RandomFighterGame.RIGHT;
		} else if(pos.x + (velocity * delta * directionX) + meatTexture.getWidth() > RandomFighterGame.WIDTH 
					&& directionX == RandomFighterGame.RIGHT){
			directionX = RandomFighterGame.LEFT;
		}
		
		if(pos.y +  (velocity * delta * directionY) < 0 && directionY == RandomFighterGame.DOWN){
			directionY = RandomFighterGame.UP;
		} else if(pos.y +  (velocity * delta * directionY) > RandomFighterGame.HEIGHT 
					&& directionY == RandomFighterGame.UP){
			directionY = RandomFighterGame.DOWN;
		}
		
		pos.add(velocity * delta * directionX, velocity * delta * directionY);
	}
	
	public boolean checkForCollisions(Rectangle collidingBox){
		if(collidingBox.overlaps(hitBox)){
			return true;
		} else {
			return false;
		}
	}
}
