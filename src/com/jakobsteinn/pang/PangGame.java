package com.jakobsteinn.pang;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jakobsteinn.pang.entities.Ball;
import com.jakobsteinn.pang.entities.Paddle;

/**
 * User: jstoone
 * Date: 9/10/13
 * Time: 7:38 PM
 */
public class PangGame implements ApplicationListener {

	public static final int WIDTH = 680;
	public static final int HEIGHT = WIDTH / 16 * 9;

	private Level level;

	private ShapeRenderer shapeRenderer;
	private SpriteBatch batch;

	@Override
	public void create() {
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();

		level = new Level(shapeRenderer, batch);
	}

	@Override
	public void render() {
		float deltaTime = Gdx.graphics.getRawDeltaTime();
		level.render(deltaTime);
		update(deltaTime);
		draw();
	}

	private void update(float deltaTime) {

	}

	private void draw() {

	}

	@Override
	public void resize(int width, int height) {
		level.resize(width, height);
	}

	@Override
	public void dispose() {
		// here is the list of disposable items:
		// http://code.google.com/p/libgdx/wiki/MemoryManagment
		shapeRenderer.dispose();
		batch.dispose();

		level.dispose();
	}

	@Override
	public void pause() {
		level.pause();
	}
	@Override
	public void resume() {
		level.resume();
	}

	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.width = WIDTH;
		cfg.height = HEIGHT;
		cfg.title = "PANG: A Pong Game";
		cfg.useGL20 = true;
		new LwjglApplication(new PangGame(), cfg);
	}
}
