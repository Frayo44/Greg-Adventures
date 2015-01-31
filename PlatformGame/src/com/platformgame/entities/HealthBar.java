package com.platformgame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.platform.game.PlatformGame;

public class HealthBar {

	Texture fullLife, emptyLife;
	public Texture actor;
	public boolean lifeVisible;
	float x, y;
	float scl;
	// public TextureRegion region;

	public HealthBar(float x, float y) {
		this.x = x;
		this.y = y;
		lifeVisible = true;
		fullLife = PlatformGame.manager.get("images/fullLife.png");
				//new Texture(Gdx.files.internal("images/fullLife.png"));
		emptyLife = PlatformGame.manager.get("images/emptyLife.png");
		//new Texture(Gdx.files.internal("images/emptyLife.png"));
		
		scl = 0.4f;
		// emptyLife = new Sprite(new
		// Texture(Gdx.files.internal("img/Player4.png")));

		// region = new TextureRegion(fullLife, fullLife.getWidth(),
		// fullLife.getHeight());

		// actor = new Texture(region);
		// stage.addActor(actor);
		// setPosition(600, 400);
		// setPosition(700, 400);
		// System.out.println("fff");
	}

	public void update() {
		
	}

	public void draw(SpriteBatch sb) {
		sb.begin();
		if (!lifeVisible) {
			sb.draw(emptyLife, x, y, emptyLife.getWidth() * scl, emptyLife.getHeight() * scl);
		} else {
			sb.draw(fullLife, x, y, emptyLife.getWidth() * scl, emptyLife.getHeight() * scl);
		}
		
		sb.end();
	}

}
