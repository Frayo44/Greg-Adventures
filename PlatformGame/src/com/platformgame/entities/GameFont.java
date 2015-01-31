package com.platformgame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.platform.game.PlatformGame;

public class GameFont {

	String numCrystals;
	String totalCrystals;
	
	private TextureRegion[] font;
	private TextureRegion crystal;
	
	public GameFont(){
		
		Texture tex = PlatformGame.manager.get("images/hud.png");
				//new Texture(Gdx.files.internal("images/hud.png"));
		
		crystal = new TextureRegion(tex, 80, 0, 16, 16);
		
		font = new TextureRegion[11];
		for(int i = 0; i < 6; i++) {
			font[i] = new TextureRegion(tex, 32 + i * 9, 16, 9, 9);
		}
		for(int i = 0; i < 5; i++) {
			font[i + 6] = new TextureRegion(tex, 32 + i * 9, 25, 9, 9);
		}
	}
	
	public void setText(String numCrystals, String totalCrystls){
		this.numCrystals = numCrystals;
		this.totalCrystals = totalCrystls;
	}
	
	public void render(SpriteBatch sb){
		sb.begin();
		
		sb.draw(crystal, 160, 208);
		
		drawString(sb, numCrystals + " / " + totalCrystals, 192, 211);
		sb.end();
	}
	
	private void drawString(SpriteBatch sb, String s, float x, float y) {
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c == '/') c = 10;
			else if(c >= '0' && c <= '9') c -= '0';
			else continue;
			sb.draw(font[c], x + i * 9, y);
		}
	}
}
