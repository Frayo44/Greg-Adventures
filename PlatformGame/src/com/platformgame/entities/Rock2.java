package com.platformgame.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.platforngame.handlers.B2DVars;

public class Rock2 extends Rock {

	public Rock2(Body body, TextureRegion ntex, boolean useTR) {
		super(body, ntex, useTR);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		if(useTR){
			spr.setPosition(
					(body.getPosition().x * B2DVars.PPM - tex.getRegionWidth() / 2),
					(body.getPosition().y * B2DVars.PPM - tex.getRegionHeight() / 2));
			spr.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
			spr.draw(sb);
		} else {
			spr.setPosition(
					(body.getPosition().x * B2DVars.PPM - tex2.getWidth() / 2 + 1),
					(body.getPosition().y * B2DVars.PPM - tex2.getHeight() / 2 + 27));
			
			spr.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
			spr.draw(sb);
		}
		
		// sb.draw(tex, (body.getPosition().x * B2DVars.PPM - tex.getWidth() /
		// 2), (int) (body.getPosition().y * B2DVars.PPM - tex.getHeight() / 2)
		// ,(body.getPosition().x * B2DVars.PPM - tex.getWidth() / 2), (int)
		// (body.getPosition().y * B2DVars.PPM - tex.getHeight() / 2),
		// tex.getWidth(),tex.getHeight(),0,0,getBody().getAngle(),60,60,60, 60,
		// false,false);
		sb.end();

	}
}
