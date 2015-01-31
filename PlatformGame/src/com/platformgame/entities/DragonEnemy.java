package com.platformgame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class DragonEnemy extends B2DSprite {
	
	int col = 5;

	public DragonEnemy(Body body, Texture tex) {
		super(body);
		

		Texture text = tex;
				//PlatformGame.manager.get("animations/dragon.png");
				//new Texture("animations/dragon.png");
		TextureRegion[] reg = TextureRegion.split(text, text.getWidth() / col,text.getHeight())[0];
		
		setAnimation(reg, 1/ 6f, 0.3f);
		animation.setBody(body);
	}
	

}