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
import com.platformgame.icescreens.BaseLevelIce;
import com.platformgame.townscreens.BaseLevelTown;
import com.platforngame.handlers.B2DVars;
import com.platforngame.handlers.BaseLevel;
import com.platforngame.handlers.MyContactListener;

public class Player  {
	
	private int numCrystals;
	private int totalCrystals;
	protected Body body;
	TextureRegion tex;
	int count3 = 0;
	Sprite spr, sheildspr;
	public boolean enable_sheild, start_counting;
	int count;
	public static int count2 = 0;
	int onlyonetime = 0;
	
	public Player(Body body) {
		this.body = body;
		count = 0;
		count2 = 0;
		start_counting =  false;
		Texture ta = PlatformGame.manager.get("images/ropes.png");
		 tex = new TextureRegion(ta, 774, 362, 100, 100);
		 
		// tex = PlatformGame.manager.get("images/kirby.png");
				 //new Texture(Gdx.files.internal("images/kirby.png")) ;
		 spr = new Sprite(tex);
		 enable_sheild = false;
		 sheildspr = new Sprite(new Texture(Gdx.files.internal("images/bubbleshield.png")));
		 spr.setScale(0.3f);
		 sheildspr.setScale(0.25f);
	}

	public Body getBody() { return body; }
	public void collectCrystals(){
		numCrystals++;
	}
	
	public void render(SpriteBatch sb){
		sb.begin();
		if((BaseLevel.cl != null && BaseLevel.cl.isOnDelay == false) || (BaseLevelTown.cl != null && BaseLevelTown.cl.isOnDelay  == false
				|| (BaseLevelIce.cl != null && BaseLevelIce.cl.isOnDelay  == false))){//.getEnemyToRemove().size != 0
			start_counting = true;
		}
		count3 += 1;
		if(count3 >= 500){
			count3 = 0;
			start_counting = false;
			spr.setAlpha(1);
			count = 0;
			count2 = 0;
			if(BaseLevel.cl != null)
				BaseLevel.cl.isOnDelay = true;
			else if(BaseLevelTown.cl != null)
				BaseLevelTown.cl.isOnDelay = true;
			else if(BaseLevelIce.cl != null)
				BaseLevelIce.cl.isOnDelay = true;
		}
		if(start_counting == true && count3 <= 5000){
			count += 1;
			count2 += 1;
			if(onlyonetime == 0){
				MyContactListener.numLifes -= 1;	
				onlyonetime = 1;
			}
			
			if(count <= 20){
				spr.setAlpha(0.4f);
			} else if(count <= 40 && count >= 21){
				spr.setAlpha(1f);
				if(count >= 37)
				count = 0;
			}
			
			if(count2 >= 90){
				onlyonetime = 0;
				start_counting = false;
				spr.setAlpha(1);
				count = 0;
				count2 = 0;
				if(BaseLevel.cl != null){
					BaseLevel.cl.isOnDelay = true;
					System.out.println("isOnDelay");
				}
				
				else if(BaseLevelTown.cl != null){
					BaseLevelTown.cl.isOnDelay = true;
					//System.out.println("isOnDelay");
				}
				
				else if(BaseLevelIce.cl != null){
					BaseLevelIce.cl.isOnDelay = true;
					//System.out.println("isOnDelay");
				}
			}
		} 
		
		//tex.getTexture().
		
		spr.setPosition((body.getPosition().x * B2DVars.PPM - spr.getWidth() / 2), (body.getPosition().y * B2DVars.PPM - spr.getHeight() / 2));
		spr.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
		spr.draw(sb);
		
		if(enable_sheild){
			
			sheildspr.setPosition((body.getPosition().x * B2DVars.PPM - sheildspr.getWidth() / 2), (body.getPosition().y * B2DVars.PPM - sheildspr.getHeight() / 2));
			sheildspr.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
			sheildspr.draw(sb);
		}
		
//		sb.draw(tex, (body.getPosition().x * B2DVars.PPM - tex.getWidth() / 2), (int) (body.getPosition().y * B2DVars.PPM - tex.getHeight() / 2)
//				,(body.getPosition().x * B2DVars.PPM - tex.getWidth() / 2), (int) (body.getPosition().y * B2DVars.PPM - tex.getHeight() / 2),
//				tex.getWidth(),tex.getHeight(),0,0,getBody().getAngle(),60,60,60, 60, false,false);
		sb.end();
		
	}
	
	public Vector2 getPosition() { return body.getPosition(); }
	
	public int getNumCrystals() {
		return numCrystals;
	}

	public void setNumCrystals(int numCrystals) {
		this.numCrystals = numCrystals;
	}

	public int getTotalCrystals() {
		return totalCrystals;
	}

	public void setTotalCrystals(int totalCrystals) {
		this.totalCrystals = totalCrystals;
	}
	
	
}
