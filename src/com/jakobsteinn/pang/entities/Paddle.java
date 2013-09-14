package com.jakobsteinn.pang.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

/**
 * User: jstoone
 * Date: 9/11/13
 * Time: 10:10 PM
 */
public class Paddle extends Entity {

	private int player;
	public int points = 0;


	public Paddle(int width, int height, int player) {
		// 32x96
		super(width, height);
		this.player = player;
		setVelocity(new Vector2(0,300));
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
}
