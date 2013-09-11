package entities;

import com.badlogic.gdx.math.Vector2;

/**
 * User: jstoone
 * Date: 9/11/13
 * Time: 10:03 PM
 */
public class Ball extends Entity {

	public Ball(int width, int height) {
		// 32x32
		super(width, height);
	}

	public void reflect(boolean x, boolean y) {
		Vector2 vel = getVelocity();
		if(x){
			vel.x *= -1;
		}
		if(y){
			vel.y *= -1;
		}
		setVelocity(vel);
	}
}
