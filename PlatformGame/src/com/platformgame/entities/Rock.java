package com.platformgame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.platform.game.PlatformGame;
import com.platforngame.handlers.B2DVars;

public class Rock {

	protected Body body;
	TextureRegion tex;
	Texture tex2;
	public Sprite spr;
	boolean useTR;
	public Rock(Body body, TextureRegion ntex, boolean useTR) {
		this.body = body;
		this.useTR = useTR;
		if(useTR){
			if (ntex == null){
				//	tex = PlatformGame.manager.get("images/rock.png");
					Texture tex1 = PlatformGame.manager.get("images/ropes.png");
					tex = new TextureRegion(tex1, 130, 11, 79, 80); //FIX
				}
					
				else
					this.tex = ntex;
		} else {
			tex2 = PlatformGame.manager.get("images/rock.png");
		}
		
		// new Texture(Gdx.files.internal("images/rock.png")) ;
		if(tex != null)
		spr = new Sprite(tex);
		if(tex2 != null)
			spr = new Sprite(tex2);
	}

	public Texture getTex2() {
		return tex2;
	}

	public void setTex2(Texture tex2) {
		this.tex2 = tex2;
	}

	public TextureRegion getTex() {
		return tex;
	}

	public void setTex(TextureRegion tex) {
		this.tex = tex;
	}

	public Body getBody() {
		return body;
	}

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
					(body.getPosition().x * B2DVars.PPM - tex2.getWidth() / 2),
					(body.getPosition().y * B2DVars.PPM - tex2.getHeight() / 2));// + 25));
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

	public Vector2 getPosition() {
		return body.getPosition();
	}

}
