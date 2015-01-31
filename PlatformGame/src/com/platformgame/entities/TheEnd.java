package com.platformgame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.platform.game.PlatformGame;
import com.platforngame.handlers.B2DVars;

public class TheEnd {
	
	//Texture tex;
	Sprite spr;
	Body body;
	TextureRegion theend;
	public TheEnd(Body body){
		this.body = body;
		//this.tex = text;
		Texture ta = PlatformGame.manager.get("images/ropes.png");
		 theend = new TextureRegion(ta, 1, 93, 572, 369);
//		tex = PlatformGame.manager.get("images/theend.png");
//				//new Texture(Gdx.files.internal("images/theend.png"));
		spr = new Sprite(theend);
		spr.setScale(0.2f, 0.3f);
		//spr.setTexture(tex);
	}
	
	public void renderr(SpriteBatch sb){
		
		spr.setPosition((body.getPosition().x * B2DVars.PPM - theend.getRegionWidth() / 2), (body.getPosition().y * B2DVars.PPM - theend.getRegionHeight() / 2));
		spr.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
		
		sb.begin();
	spr.draw(sb);
//		sb.draw(tex,(body.getPosition().x * B2DVars.PPM - tex.getWidth() / 2), (body.getPosition().y * B2DVars.PPM - tex.getHeight() / 2), 
//				3013 / B2DVars.PPM ,2144 / B2DVars.PPM );
		sb.end();
	}

}
