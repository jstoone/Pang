package com.jakobsteinn.pang.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jakobsteinn.pang.Level;
import com.jakobsteinn.pang.PangGame;

/**
 * User: jstoone
 * Date: 9/10/13
 * Time: 8:03 PM
 */
public class Entity {

	private Vector2 position = new Vector2();
	private Vector2 velocity = new Vector2();
	private Rectangle bounds = new Rectangle();
	private Level level;

	protected Entity(Level level, int width, int height) {
		bounds.setWidth(width);
		bounds.setHeight(height);
		this.level = level;

		// could make separate init() method, but
		// this game is not going to have to
		// async init anything.
	}



	public void update(float deltaTime) {
		getBounds();
	}
	public void renderDebug() {
		level.shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	public void render() {

	}

	// BOUNDS
	public Rectangle getBounds() {
		bounds.setX(position.x);
		bounds.setY(position.y);
		return bounds;
	}

	public float getWidth() {
		return bounds.getWidth();
	}

	public float getHeight() {
		return bounds.getHeight();
	}

	public float getTop() {
		return bounds.getY() + bounds.getHeight();
	}
	public float getBottom() {
		return bounds.getY();
	}
	public float getLeft() {
		return bounds.getX() + bounds.getWidth();
	}
	public float getRight() {
		return bounds.getX();
	}

	// POSITION
	public float getX(){
		return position.x;
	}

	public float getY(){
		return position.y;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	// VELOCITY
	public float getVelocityX() {
		return velocity.x;
	}

	public float getVelocityY() {
		return velocity.y;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public void translate(float x, float y) {
		position.add(x, y);
	}

	public void integrate(float deltaTime) {
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
	}

	public void move(float x, float y) {
		position.set(x, y);
	}
}
