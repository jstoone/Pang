package com.jakobsteinn.pang.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jakobsteinn.pang.Level;

/**
 * User: jstoone
 * Date: 9/11/13
 * Time: 10:03 PM
 */
public class Ball extends Entity {

	private Rectangle field;
	private Level level;

	public Ball(Level level, int width, int height) {
		// 32x32
		super(level, width, height);
		this.level = level;
		field = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// init properties
		setVelocity(new Vector2(300, 150));

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
				move(getX(), field.getY());
				reflect(false, true);
			}
			if(getY() > field.getHeight() - (getWidth() / 2)) {
				move(getX(), field.getHeight() - getHeight());
				reflect(false, true);
			}
		}

		// check for collision for N amout of players (in this case two)
		// i made this collision local to the ball, since it produces
		// less code, and no dublication
		// dublication would happen if we checked the collision
		// in the paddle itself (reversed the collision check)
		for(Paddle p : level.players) {
			if(getBounds().overlaps(p.getBounds())) {
				Gdx.app.log("Player", "overlaps");
				reflect(true, false);
				p.score++;
				if(getVelocityY() * p.getVelocityY() > 0) {
					setVelocity(getVelocity().mul(1.08f));
				} else {
					setVelocity(getVelocity().mul(0.9f));
				}

				// if paddle hits the ball with the opposite velocity
				// the ball will reflect at the velocity of the paddle
				if((p.getVelocityY() > 0 && getVelocityY() < 0) ||
						(p.getVelocityY() < 0 && getVelocityY() > 0)) {
					reflect(false, true);
				}
			}
		}
	}

	public void render() {
		level.shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
	}
}
