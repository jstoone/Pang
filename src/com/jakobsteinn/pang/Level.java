package com.jakobsteinn.pang;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.jakobsteinn.pang.entities.Ball;
import com.jakobsteinn.pang.entities.Paddle;

import java.util.ArrayList;
import java.util.Random;

/**
 * User: jstoone
 * Date: 9/17/13
 * Time: 10:19 AM
 */

public class Level implements Screen {

	public Ball ball;
	public ArrayList<Paddle> players = new ArrayList<Paddle>();
	public Random random;
	public Rectangle field;
	public ShapeRenderer shapeRenderer;
	public SpriteBatch batch;

	private BitmapFont bitmapFont;
	private int score1 = 0;
	private int score2 = 0;


	public Level(ShapeRenderer shapeRenderer, SpriteBatch batch) {
		// renderer from PangGame
		this.shapeRenderer = shapeRenderer;
		// spriteBatch from PangGame
		this.batch = batch;
		// the games field
		field = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		// new random generator
		random = new Random();

		bitmapFont = new BitmapFont();

		ball = new Ball(this, 16, 16);
		ball.reset();
		// adding players
		players.add(new Paddle(this, 16, 96, 1));
		players.add(new Paddle(this, 16, 96, 2));
	}

	@Override
	public void render(float deltaTime) {
		update(deltaTime);
		draw();
	}

	private void update(float deltaTime) {
		ball.update(deltaTime);
		// loop over ArrayList of players/paddles
		// and update them.
		for(int i = 0; i < players.size(); i++){
			Paddle player = players.get(i);
			player.update(deltaTime);
			if(player.getPlayerNumber() == 1) {
				score1 = player.score;
			} else if(player.getPlayerNumber() == 2) {
				score2 = player.score;
			}
		}
	}

	private void draw() {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
			bitmapFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			bitmapFont.draw(batch, score1 + "", 100, 100);
			bitmapFont.draw(batch, score2 + "", field.getWidth() - 100, field.getHeight()- 100);
		batch.end();

		// this is so ugly, my eyes hurt
		// but since I'm thinking that I'm making it all
		// rectangular, this could work..... hm.
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		ball.render();
		for(int i = 0; i < players.size(); i++){
			Paddle player = players.get(i);
			player.render();
		}
		shapeRenderer.end();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		ball.renderDebug();
		for(int i = 0; i < players.size(); i++) {
			Paddle player = players.get(i);
			player.renderDebug();
		}
		shapeRenderer.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		bitmapFont.dispose();
	}
}
