package com.platformgame.townscreens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.platform.game.PlatformGame;

public class LevelTown11 extends  BaseLevelTown {

	public LevelTown11(PlatformGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void show() {
	
		if (game.manager.isLoaded("maps/leveltown11.tmx")) {
			TiledMap map = game.manager.get("maps/leveltown11.tmx");
			loadMap(map);
		} else {
			game.manager.setLoader(TiledMap.class, new TmxMapLoader(
					new InternalFileHandleResolver()));
			game.manager.load("maps/levelTown9.tmx", TiledMap.class);
			if (game.manager.isLoaded("maps/levelTown9.tmx")) {
				TiledMap map = game.manager.get("maps/levelTown9.tmx");
				loadMap(map);
			}

		}

		level = 11;
		super.show();

	}

	@Override
	protected void initLevelCompletedUI() {

		int currenCrystals = player.getNumCrystals();
		int besthighscore = game.getPreferencesManager().getWORLDElevenHighScore();
		int totalCryss = player.getTotalCrystals();
		int curStars = game.getPreferencesManager().getWORLDElevenStars();

		if (currenCrystals >= besthighscore) {
			game.getPreferencesManager().setWORLDElevenHighScore(currenCrystals);
			besthighscore = game.getPreferencesManager().getWORLDElevenHighScore();
		}

		if (currenCrystals <= totalCryss / 2) {
			Drawable pauseDrawable = new TextureRegionDrawable(
					new TextureRegion(new Texture(
							Gdx.files.internal("images/lconestars.png"))));

			nextLevelImage = new Image(pauseDrawable, Scaling.stretch);
			nextLevelImage.setFillParent(true);
			stage.addActor(nextLevelImage);

			if (curStars < 1) {
				game.getPreferencesManager().setWORLDElevenStars(1);
			}
		} else if (currenCrystals > totalCryss / 2
				&& currenCrystals < totalCryss) {
			Drawable pauseDrawable = new TextureRegionDrawable(
					new TextureRegion(new Texture(
							Gdx.files.internal("images/lctwostars.png"))));

			nextLevelImage = new Image(pauseDrawable, Scaling.stretch);
			nextLevelImage.setFillParent(true);

			stage.addActor(nextLevelImage);
			if (curStars < 2) {
				game.getPreferencesManager().setWORLDElevenStars(2);
			}
		} else {
			Drawable pauseDrawable = new TextureRegionDrawable(
					new TextureRegion(new Texture(
							Gdx.files.internal("images/lcthreestars.png"))));

			nextLevelImage = new Image(pauseDrawable, Scaling.stretch);
			nextLevelImage.setFillParent(true);

			stage.addActor(nextLevelImage);

			if (curStars <= 3) {
				game.getPreferencesManager().setWORLDElevenStars(3);
			}
		}

		curScore = new Label("Score: " + player.getNumCrystals() + "    ",
				getSkin(), "medium-font");
		int currentScore = player.getNumCrystals();
		int beforeTotalAnount = game.getPreferencesManager()
				.getSummaryOfScore();
		game.getPreferencesManager().setSummaryOfScore(
				currentScore + beforeTotalAnount);
		int total_amount = beforeTotalAnount + currentScore;
		highScore = new Label("HIGHSCORE: " + besthighscore + "     Total: " + total_amount , getSkin(),
				"medium-font");
		curScore.setFontScale(0.6f);
		highScore.setFontScale(0.6f);
		// retrieve the default table actor

		Table tableLabel = super.getTable();
		if (curScore != null)
			tableLabel.removeActor(curScore);
		tableLabel.defaults().padTop(70);
		tableLabel.padRight(100);
		tableLabel.add(curScore);
		tableLabel.center();
		stage.addActor(tableLabel);

		// Table tableLabel2 = super.getTable();
		if (highScore != null)
			tableLabel.removeActor(highScore);
		tableLabel.padTop(10);
		tableLabel.padRight(1);
		tableLabel.add(highScore);
		tableLabel.left().center();
		stage.addActor(tableLabel);

		// Home Button For Game Menu
		TextureAtlas atlashouse = game.manager.get("ui/homebutton.pack");
		// new TextureAtlas("ui/homebutton.pack");

		Skin skin7 = new Skin(Gdx.files.internal("ui/menuSkin.json"),
				atlashouse);

		Table table = new Table(skin7);

		Button buttonHome = new Button(skin7);
		buttonHome.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				stage.addAction(sequence(fadeOut(0.3f), run(new Runnable() {
					public void run() {
						game.setScreen(game.getMenuScreen());
					}
				})));
				// System.out.print("peaa");
			}
		});

		// Restart Button For Game Menu
		TextureAtlas atlasRestart = game.manager.get("ui/restartbutton.pack");
		// new TextureAtlas("ui/restartbutton.pack");

		Skin skin8 = new Skin(Gdx.files.internal("ui/menuSkin.json"),
				atlasRestart);

		// table = new Table(skin);
		Button buttonRestart = new Button(skin8);
		buttonRestart.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				stage.addAction(sequence(fadeOut(0.3f), run(new Runnable() {
					public void run() {
						initVars();
						state = GameState.BEFORE_RUNNING;
					}
				})));
				// System.out.print("peaa");
			}
		});

		TextureAtlas atlasNextLevel = game.manager
				.get("ui/nextlevelbutton.pack");
		// new TextureAtlas("ui/nextlevelbutton.pack");

		Skin skin9 = new Skin(Gdx.files.internal("ui/menuSkin.json"),
				atlasNextLevel);

		// table = new Table(skin);

		Button buttonNextLevel = new Button(skin9);
		buttonNextLevel.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				stage.addAction(sequence(fadeOut(0.3f), run(new Runnable() {
					public void run() {
						TownLevelsScreen.curlevel = 4;
						game.setScreen(game.getLevelWorldLevelScreen());
					//	game.setScreen(game.getLoadingScreen());
					}
				})));
			}
		});

		table.add(buttonHome);// .left();
		table.getCell(buttonHome).padLeft(40).size(40, 40);
		// stage.addActor(table);

		table.add(buttonRestart);// .left();
		table.getCell(buttonRestart).padLeft(40).size(40, 40);
		stage.addActor(table);
		table.bottom().padBottom(50).padLeft(275);

		table.add(buttonNextLevel);// .left();
		table.getCell(buttonNextLevel).padLeft(40).size(40, 40);
		stage.addActor(table);
		table.align(Align.center);
		table.bottom().padBottom(30).padLeft(255);

		if (game.actionResolver.getSignedInGPGS()) {
			int total_upper = (int) (total_amount + currenCrystals * 0.8f);
			game.getPreferencesManager().setSummaryOfScore(total_upper);
			 game.actionResolver.submitScoreGPGS(total_upper);
		}
			//game.actionResolver.submitScoreGPGS(total_amount);
		
		// super.initLevelCompletedUI();

	}

	@Override
	public void dispose() {
		super.dispose();
	
		game.manager.unload("maps/leveltown11.tmx");
		game.manager.unload("images/backgroundbuilding.png");
		game.manager.unload("images/garbage.png");
		game.manager.unload("images/spikesrock.png");
		//game.manager.unload("animations/pig.png");
		game.manager.unload("animations/cat.png");
		game.manager.unload("images/spikesplatform.png");
		game.manager.unload("images/swingwood.png");
	}

}