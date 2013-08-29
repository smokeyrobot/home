/**
 * 
 */
package com.bad.fighters;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Kyle
 *
 */
public class Fighter {
	
	public static final int READY = 0;
	public static final int ACTION = 1;
	
	public int fighterState;
	
	private Rectangle hitBox;

	public Fighter(float x, float y, float width, float height){
		hitBox = new Rectangle(x, y, width, height);
	}
	
	public Fighter(Vector2 coords, float width, float height){
		hitBox = new Rectangle(coords.x, coords.y, width, height);
	}
	
	//Will need to remove to force hitbox usage
	public Fighter() {
		// TODO Auto-generated constructor stub
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}

}
