package com.jakobsteinn.pang.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.jakobsteinn.pang.Level;

/**
 * User: jstoone
 * Date: 9/11/13
 * Time: 10:10 PM
 */
public class Paddle extends Entity {

	private Level level;

	private int playerNumber;
	public int score = 0;


	public Paddle(Level level, int width, int height, int playerNumber) {
		// 32x96
		super(level, width, height);
		this.level = level;
		this.playerNumber = playerNumber;
		setVelocity(new Vector2(0, 300));
		if(playerNumber == 1) {
			setPosition(new Vector2(20,35));
		} else if(playerNumber == 2) {
			setPosition(new Vector2(level.field.getWidth()-20 - 16, level.field.getHeight()- 35 - 96));
		}
	}

	public void update(float deltaTime) {
		super.update(deltaTime);
		if ( playerNumber == 1 ) {
			if ( Gdx.input.isKeyPressed(Input.Keys.A) ) {
				setVelocity(new Vector2(0,300));
				integrate(deltaTime);
			}
			if ( Gdx.input.isKeyPressed(Input.Keys.Z)) {
				setVelocity(new Vector2(0,-300));
				integrate(deltaTime);
			}

			if(hasCollidedBall()) {
				level.ball.reflect(true, false);
				level.ball.move(getRight(), level.ball.getY());
				level.ball.getVelocity().setAngle(reflectionAngle());

				if(level.ball.getVelocityX() >= level.ball.MAX_SPEED) {
					level.ball.setVelocity(new Vector2(level.ball.MAX_SPEED, level.ball.getVelocityY()));
				} else {
					level.ball.getVelocity().mul(1.01f);
				}
			}
		} else if ( playerNumber == 2 ) {
			if ( Gdx.input.isKeyPressed(Input.Keys.K) ) {
				setVelocity(new Vector2(0,300));
				integrate(deltaTime);
			}
			if ( Gdx.input.isKeyPressed(Input.Keys.M)) {
				setVelocity(new Vector2(0,-300));
				integrate(deltaTime);
			}
			if(hasCollidedBall()) {
				level.ball.reflect(true, false);
				level.ball.move(getLeft() - level.ball.getWidth(), level.ball.getY());
				level.ball.getVelocity().setAngle(180f - reflectionAngle());

				if(level.ball.getVelocityX() <= -level.ball.MAX_SPEED) {
					level.ball.setVelocity(new Vector2(-level.ball.MAX_SPEED, level.ball.getVelocityY()));
				} else {
					level.ball.getVelocity().mul(1.01f);
				}
			}
		}
	}

	public void render() {
		level.shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
	}

	private boolean hasCollidedBall() {
		boolean p1 = getBounds().contains(level.ball.getLeft(), level.ball.getY());
		boolean p2 = getBounds().contains(level.ball.getRight(), level.ball.getY());

		if((p1 && playerNumber == 1) || (p2 && playerNumber == 2)) {
			return true;
		} else {
			return false;
		}
	}

	private float reflectionAngle() {
		float paddleCenter = getY() + (getHeight() / 2);
		float ballCenter = level.ball.getY() + (level.ball.getHeight() / 2);
		float dif = ballCenter - paddleCenter;
		float position = dif / getHeight();

		return level.ball.REFLECTION_ANGLE_MUL * position;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int player) {
		this.playerNumber = player;
	}

}
