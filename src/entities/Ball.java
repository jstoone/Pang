package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * User: jstoone
 * Date: 9/11/13
 * Time: 10:03 PM
 */
public class Ball extends Entity {

	private Rectangle field;

	public Ball(int width, int height) {
		// 32x32
		super(width, height);
		field = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

	public void update(float deltaTime) {
		integrate(deltaTime);
		if(!field.contains(getBounds())){
			if(getX() < field.getX()){
				reflect(true, false);
				reset();
			}
			if(getX() > field.getWidth() - (getWidth() / 2)) {
				reflect(true, false);
				reset();
			}
			if(getY() < field.getY()) {
				reflect(false, true);
			}
			if(getY() > field.getHeight() - (getWidth() / 2)) {
				reflect(false, true);
			}
		}
	}
}
