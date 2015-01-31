package com.platformgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.platform.game.PlatformGame;
import com.platforngame.handlers.MusicManager.TyrianMusic;

public class HighscoresScreen extends AbstractScreen {

	Skin skin, skin3;
	PlatformGame game;
	Texture tex;
	TextureAtlas ta3;
	TextureAtlas ta;

	public HighscoresScreen(PlatformGame game) {
		super(game);
		this.game = game;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float dt) {
		super.render(dt);
		//Gdx.gl20.glClearColor(1,1,1,1);
		//Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(dt);

		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		super.show();
		
		
		 InputProcessor backProcessor = new InputAdapter() {
			 @Override
			 public boolean keyDown(int keycode) {
			
			 if ((keycode == Keys.ESCAPE) || (keycode == Keys.BACK))
				 game.setScreen(game.getMenuScreen());
			 return false;
			 }
			 };

			 InputMultiplexer multiplexer = new InputMultiplexer(stage,
			 backProcessor);
			 Gdx.input.setInputProcessor(multiplexer);
			 Gdx.input.setCatchBackKey(true);
		
		 game.getMusicManager().play( TyrianMusic.MENU );

		 tex = new Texture(Gdx.files.internal("images/highscoresbg.png"));
				//game.manager.get("images/menubg.png");
		Drawable gameoverDrawable = new TextureRegionDrawable(
				new TextureRegion(tex));

		Image gameOverImage = new Image(gameoverDrawable, Scaling.stretch);
		gameOverImage.setFillParent(true);
		stage.addActor(gameOverImage);

		 ta = new TextureAtlas(Gdx.files.internal("ui/thighscores.pack"));
				//game.manager.get("ui/buttonplay.pack");

		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), ta);

		Button buttonNextLevel = new Button(skin);
		buttonNextLevel.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

						if (game.actionResolver.getSignedInGPGS()) game.actionResolver.getLeaderboardGPGS();
						else game.actionResolver.loginGPGS();
			}
		});

//		TextureAtlas ta2 = game.manager.get("ui/optionsbutton.pack");
//
//		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), ta2);
//
//		Button buttonOptions = new Button(skin);
//		buttonOptions.addListener(new InputListener() {
//			public boolean touchDown(InputEvent event, float x, float y,
//					int pointer, int button) {
//
//				return true;
//			}
//
//			public void touchUp(InputEvent event, float x, float y,
//					int pointer, int button) {
//
//				stage.addAction(sequence(fadeOut(0.3f), run(new Runnable() {
//					public void run() {
//						game.setScreen(game.getOptionsScreen());
//					}
//				})));
//				// System.out.print("peaa");
//			}
//		});
		
		ta3 = new TextureAtlas(Gdx.files.internal("ui/rhighscores.pack"));
				//game.manager.get("ui/exitbutton.pack");

		skin3 = new Skin(Gdx.files.internal("ui/menuSkin.json"), ta3);

		Button buttonHighScore = new Button(skin3);
		buttonHighScore.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
//						//Gdx.app.exit();
				if (game.actionResolver2.getSignedInGPGS2()) game.actionResolver2.getLeaderboardGPGS2();
				else game.actionResolver2.loginGPGS2();
						
			}
		});


		Table table = new Table();
		table.add(buttonNextLevel);// .left();
		table.getCell(buttonNextLevel).padLeft(35).size(150, 70);
		stage.addActor(table);
		table.align(Align.center);
		table.bottom().padBottom(10).padLeft(0);
		//table.add(buttonNextLevel);// .left();
		
		table.row();
		table.add(buttonHighScore);
		table.getCell(buttonHighScore).padLeft(40).size(150, 70);
		stage.addActor(table);
		table.align(Align.center);
		table.bottom().padBottom(50).padLeft(450);
//		
//		table.row();
//		table.add(buttonOptions);
//		table.getCell(buttonOptions).padLeft(40).size(100, 50);
//		stage.addActor(table);
		
		table.align(Align.center);
		table.bottom().padBottom(30).padLeft(280);
		

	}
	
	@Override
	public void dispose() {
		super.dispose();
		tex.dispose();
		ta3.dispose();
		ta.dispose();
		skin.dispose();
	}

}