package com.platformgame.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.platforngame.handlers.Animation;
import com.platforngame.handlers.B2DVars;

public class B2DSprite {

	protected Body body;
	protected Animation animation;
	protected float width;
	protected float height;
	float scl;
	
	public B2DSprite(Body body){
		this.body = body;
		animation  = new Animation();
		
	}
	
	public void setAnimation(TextureRegion[] reg, float delay, float scale){
		animation.setFrames(reg, delay);
		this.scl = scale;
		width = reg[0].getRegionWidth() * scale;
		height = reg[0].getRegionHeight() * scale;
	}
	
	public void update(float dt){
		animation.update(dt);
	}
	
	public void render(SpriteBatch sb){
		sb.begin();
		if(animation.flip){
			sb.draw(animation.getFrame(), (body.getPosition().x * B2DVars.PPM + width / 2), (int) (body.getPosition().y * B2DVars.PPM - height / 2),
					-animation.getFrame().getRegionWidth() * scl,animation.getFrame().getRegionHeight() * scl);
		} else {
			sb.draw(animation.getFrame(), (body.getPosition().x * B2DVars.PPM - width / 2), (int) (body.getPosition().y * B2DVars.PPM - height / 2),
					animation.getFrame().getRegionWidth() * scl,animation.getFrame().getRegionHeight() * scl);
		}
		
		
		sb.end();
	}
	
	public Body getBody() { return body; }
	public Vector2 getPosition() { return body.getPosition(); }
	public float getWidth() { return width; }
	public float getHeight() { return height; }
}
