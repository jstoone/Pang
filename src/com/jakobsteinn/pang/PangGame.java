package com.jakobsteinn.pang;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.math.Rectangle;
import entities.Ball;
import entities.Paddle;

/**
 * User: jstoone
 * Date: 9/10/13
 * Time: 7:38 PM
 */
public class PangGame implements ApplicationListener {

	private static final int WIDTH = 1280;
	private static final int HEIGHT = WIDTH / 16 * 9;

	private Rectangle field = new Rectangle();
	private Ball ball = new Ball(32, 32);
	private Paddle paddle1 = new Paddle(32, 96);
	private Paddle paddle2 = new Paddle(32, 96);


	public void create() {
		field.set(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	private void update(float deltaTime) {

	}

	private void draw(float deltaTime) {

	}

	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		update(deltaTime);
		draw(deltaTime);
	}

	public void resize(int width, int height) {

	}

	public void dispose() {
	}

	// not used as these are for mobile, and this game is only desktop
	public void pause() {}
	public void resume() {}

	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.width = WIDTH;
		cfg.height = HEIGHT;
		cfg.title = "PANG: A Pong Game";
		new LwjglApplication(new PangGame(), cfg);
	}
}
