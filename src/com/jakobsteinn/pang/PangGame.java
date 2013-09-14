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
import entities.Ball;
import entities.Paddle;

/**
 * User: jstoone
 * Date: 9/10/13
 * Time: 7:38 PM
 */
public class PangGame implements ApplicationListener {

	public static final int WIDTH = 680;
	public static final int HEIGHT = WIDTH / 16 * 9;

	private Rectangle field = new Rectangle();
	private Ball ball;
	private Paddle paddle1;
	private Paddle paddle2;
	private ShapeRenderer shapeRenderer;
	private BitmapFont bitmapFont;
	private SpriteBatch batch;

	public void create() {
		ball = new Ball(16, 16);
		paddle1 = new Paddle(16, 96, 1);
		paddle2 = new Paddle(16, 96, 2);
		paddle1.setPosition(new Vector2(20,20));
		paddle2.setPosition(new Vector2(WIDTH-20 - 16, HEIGHT - 20 - 96));

		ball.setVelocity(new Vector2(300, 150));
		field.set(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		shapeRenderer = new ShapeRenderer();
		bitmapFont = new BitmapFont();
		batch = new SpriteBatch();
		ball.reset();
	}

	private void update(float deltaTime) {

		ball.update(deltaTime);
		paddle1.update(deltaTime);
		paddle2.update(deltaTime);

		if ( ball.getBounds().overlaps(paddle1.getBounds()) && ball.getVelocityX() < 0 ) {
			ball.reflect(true, false);
			paddle1.points++;
			if ( ball.getVelocityY() * paddle1.getVelocityY() > 0 ) {
				ball.setVelocity(ball.getVelocity().mul(1.1f));
			} else {
				ball.setVelocity(ball.getVelocity().mul(0.9f));
			}

			// if paddle hits the ball with the opposite velocity
			// the ball will reflect at the velocity of the paddle
			// (it's a little buggy tho...)
			if((paddle2.getVelocityY() > 0 && ball.getVelocityY() < 0) ||
			   (paddle2.getVelocityY() < 0 && ball.getVelocityY() > 0)) {
				ball.reflect(false, true);
			}
		}

		if ( ball.getBounds().overlaps(paddle2.getBounds()) && ball.getVelocityX() > 0 ) {
			ball.reflect(true, false);
			paddle2.points++;
			if ( ball.getVelocityY() * paddle1.getVelocityY() > 0 ) {
				ball.setVelocity(ball.getVelocity().mul(1.01f));
			} else {
				ball.setVelocity(ball.getVelocity().mul(0.99f));
			}

			// if paddle hits the ball with the opposite velocity
			// the ball will reflect at the velocity of the paddle
			// (it's a little buggy tho...)
			if((paddle2.getVelocityY() > 0 && ball.getVelocityY() < 0) ||
			   (paddle2.getVelocityY() < 0 && ball.getVelocityY() > 0)) {
				ball.reflect(false, true);
			}
		}
	}

	private void draw(float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bitmapFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		bitmapFont.draw(batch, paddle1.points + "", 100, 100);
		bitmapFont.draw(batch, paddle2.points + "", WIDTH - 100, HEIGHT - 100);
		batch.end();

		// this is so ugly, my eyes hurt
		// but since I'm thinking that I'm making it all
		// rectangular, this could work..... hm.
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		drawBall(deltaTime);
		drawPaddle1(deltaTime);
		drawPaddle2(deltaTime);
		shapeRenderer.end();

	}

	private void drawBall(float deltaTime) {
		shapeRenderer.rect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
	}

	private void drawPaddle1(float deltaTime) {
		shapeRenderer.rect(paddle1.getX(), paddle1.getY(), paddle1.getWidth(), paddle1.getHeight());
	}

	private void drawPaddle2(float deltaTime) {
		shapeRenderer.rect(paddle2.getX(), paddle2.getY(), paddle2.getWidth(), paddle2.getHeight());
	}

	public void render() {
		float deltaTime = Gdx.graphics.getRawDeltaTime();
		update(deltaTime);
		draw(deltaTime);
	}

	public void resize(int width, int height) {

	}

	public void dispose() {
		// here is the list of disposable items:
		// http://code.google.com/p/libgdx/wiki/MemoryManagment
		bitmapFont.dispose();
		batch.dispose();
	}

	// not used as these are for mobile, and this game is only desktop
	// does it inherit
	public void pause() {}
	public void resume() {}

	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.width = WIDTH;
		cfg.height = HEIGHT;
		cfg.title = "PANG: A Pong Game";
		cfg.useGL20 = true;
		new LwjglApplication(new PangGame(), cfg);
	}
}
