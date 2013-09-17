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

	private int player;
	public int score = 0;


	public Paddle(Level level, int width, int height, int player) {
		// 32x96
		super(level, width, height);
		this.level = level;
		this.player = player;
		setVelocity(new Vector2(0, 300));
		if(player == 1) {
			setPosition(new Vector2(20,35));
		} else if(player == 2) {
			setPosition(new Vector2(level.field.getWidth()-20 - 16, level.field.getHeight()- 35 - 96));
		}
	}

	public void update(float deltaTime) {

		if ( player == 1 ) {
			if ( Gdx.input.isKeyPressed(Input.Keys.A) ) {
				setVelocity(new Vector2(0,300));
				integrate(deltaTime);
			}
			if ( Gdx.input.isKeyPressed(Input.Keys.Z)) {
				setVelocity(new Vector2(0,-300));
				integrate(deltaTime);
			}
		} else if ( player == 2 ) {
			if ( Gdx.input.isKeyPressed(Input.Keys.K) ) {
				setVelocity(new Vector2(0,300));
				integrate(deltaTime);
			}
			if ( Gdx.input.isKeyPressed(Input.Keys.M)) {
				setVelocity(new Vector2(0,-300));
				integrate(deltaTime);
			}
		}
	}

	public void render() {
		level.shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
	}

	public int getPlayerNumber() {
		return player;
	}

	public void setPlayerNumber(int player) {
		this.player = player;
	}

}
