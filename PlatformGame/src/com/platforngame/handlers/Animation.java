package com.platforngame.handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Animation {

	private TextureRegion[] frames;
	private float time;
	private float delay;
	private int currentFrame;
	private int timesPlayed;
	Body body;
	public boolean flip = false;
	private int only_one_time = 0;

	public Animation() {
	}

	public Animation(TextureRegion[] frames) {
		this(frames, 1 / 12f);
	}

	public Animation(TextureRegion[] frames, float delay) {
		setFrames(frames, delay);
	}

	public void setFrames(TextureRegion[] frames, float delay) {
		this.frames = frames;
		this.delay = delay;

		time = 0;
		currentFrame = 0;
		timesPlayed = 0;
	}

	public void update(float dt) {
		if (delay <= 0)
			return;
		time += dt;
		while (time >= delay) {
			step();
		}
	}

	public void step() {
		time -= delay;
		currentFrame++;
		
		if (body == null)
			if (currentFrame >= frames.length) {
				currentFrame = 0;
				timesPlayed++;
			}
		
		if (body != null){
			if (currentFrame >= frames.length) {
				currentFrame = 0;
				timesPlayed++;
			}
			if(body.getLinearVelocity().x == 0){
				setFrame(1);
			}
			
			if(body.getLinearVelocity().x < 0 && only_one_time == 0){
				flip = true;
				only_one_time = 1;
			} 
			
			if(body.getLinearVelocity().x > 0 && only_one_time == 1){
				flip = false;
				only_one_time = 0;
			}
		}
	}

	public TextureRegion getFrame() {
		return frames[currentFrame];
	}

	public void setFrame(int fr) {
		this.currentFrame = fr;
	}

	public int getTimesPlayed() {
		return timesPlayed;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}
