package com.jakobsteinn.pang.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jakobsteinn.pang.Level;
import com.jakobsteinn.pang.PangGame;

/**
 * User: jstoone
 * Date: 9/11/13
 * Time: 10:03 PM
 */
public class Ball extends Entity {

	public final float MAX_SPEED = 650f;
	public final float REFLECTION_ANGLE_MUL = 110.0f;
	private Rectangle field;
	private Level level;

	public Ball(Level level, int width, int height) {
		super(level, width, height);
		this.level = level;
		field = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// init properties
		setVelocity(new Vector2(300, 150));
		reset();
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
		super.update(deltaTime);
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
				move(getX(), field.getY());
				reflect(false, true);
			}
			if(getY() > field.getHeight() - (getWidth() / 2)) {
				move(getX(), field.getHeight() - getHeight());
				reflect(false, true);
			}
		}
	}

	public void render() {
		level.shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
	}

	public void reset() {
		setPosition(new Vector2(field.getWidth() / 2, field.getHeight() / 2));
		float randomAng = MathUtils.random(-360, 360);
		System.out.println(randomAng);
		getVelocity().setAngle((float) randomAng);
	}
}
