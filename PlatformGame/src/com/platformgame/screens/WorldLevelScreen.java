package com.platformgame.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.repeat;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.platform.game.PlatformGame;
import com.platforngame.handlers.PagedScrollPane;

public class WorldLevelScreen extends AbstractScreen {

	public WorldLevelScreen(PlatformGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	float starsLevel1 = game.getPreferencesManager().getLevelOneStars();
	float starsLevel2 = game.getPreferencesManager().getLevelTwoStars();
	float starsLevel3 = game.getPreferencesManager().getLevelThreeStars();
	float starsLevel4 = game.getPreferencesManager().getLevelFourStars();
	float starsLevel5 = game.getPreferencesManager().getLevelFiveStars();
	float starsLevel6 = game.getPreferencesManager().getLevelSixStars();
	float starsLevel7 = game.getPreferencesManager().getLevelSevenStars();
	float starsLevel8 = game.getPreferencesManager().getLevelEightStars();
	float starsLevel9 = game.getPreferencesManager().getLevelNineStars();
	float starsLevel10 = game.getPreferencesManager().getLevelTenStars();
	float starsLevel11 = game.getPreferencesManager().getLevelElevenStars();
	float starsLevel12 = game.getPreferencesManager().getLevelTwelveStars();
	
	float starsWORLD1 = game.getPreferencesManager().getWORLDOneStars();
	float starsWORLD2 = game.getPreferencesManager().getWORLDTwoStars();
	float starsWORLD3 = game.getPreferencesManager().getWORLDThreeStars();
	float starsWORLD4 = game.getPreferencesManager().getWORLDFourStars();
	float starsWORLD5 = game.getPreferencesManager().getWORLDFiveStars();
	float starsWORLD6 = game.getPreferencesManager().getWORLDSixStars();
	float starsWORLD7 = game.getPreferencesManager().getWORLDSevenStars();
	float starsWORLD8 = game.getPreferencesManager().getWORLDEightStars();
	float starsWORLD9 = game.getPreferencesManager().getWORLDNineStars();
	float starsWORLD10 = game.getPreferencesManager().getWORLDTenStars();
	float starsWORLD11 = game.getPreferencesManager().getWORLDElevenStars();
	float starsWORLD12 = game.getPreferencesManager().getWORLDTwelveStars();
	
	float starsICE1 = game.getPreferencesManager().getICEOneStars();
	float starsICE2 = game.getPreferencesManager().getICETwoStars();
	float starsICE3 = game.getPreferencesManager().getICEThreeStars();
	float starsICE4 = game.getPreferencesManager().getICEFourStars();
	float starsICE5 = game.getPreferencesManager().getICEFiveStars();
	float starsICE6 = game.getPreferencesManager().getICESixStars();
	float starsICE7 = game.getPreferencesManager().getICESevenStars();
	float starsICE8 = game.getPreferencesManager().getICEEightStars();
	float starsICE9 = game.getPreferencesManager().getICENineStars();
	float starsICE10 = game.getPreferencesManager().getICETenStars();
	float starsICE11 = game.getPreferencesManager().getICEElevenStars();
	float starsICE12 = game.getPreferencesManager().getICETwelveStars();
	
	public static int totalRecordsHighscores;

	int b = 0;
	public static int curLevel;
	public static int curStaticLevel;

	private Skin skin;
	private Table container;

	private Image backGImage;

	Table starTable;

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);

	}

	public boolean needsGL20() {
		return false;
	}

	public Button getLevelButton(int level) {
		Button button = new Button(skin);
		ButtonStyle style = button.getStyle();
		style.up = style.down = null;

		starTable = new Table();
		starTable.defaults().pad(2);
		// System.out.println("here");

		Label label = new Label("", skin);
		label.setFontScale(1f);
		label.setAlignment(Align.center);

		Label label2 = new Label("", skin);
		label2.setFontScale(1f);
		label2.setAlignment(Align.center);

		Label labelLocked = new Label("", skin);
		label2.setFontScale(1f);
		label2.setAlignment(Align.center);

		Label starsLabel = new Label("stadfrs", skin, "medium-font");

		starsLabel.setFontScale(0.7f);
		// starsLabel.setPosition(12, -5);
		starsLabel.setAlignment(Align.bottom, Align.center);

		//TextureRegion crystal;
	//	Texture tex = new Texture(Gdx.files.internal("images/hud.png"));

		//crystal = new TextureRegion(tex, 80, 0, 16, 16);
		//Image img = new Image(crystal);
	//	img.setScale(0.5f);
		// Stack the image and the label at the top of our button

		switch (level) {
		case 1:
			Table table = new Table();
			int completed;
			if (starsLevel1 == 0) completed = 0;
			else completed = 1;
			if (starsLevel2 > 0) completed = 2;
			if (starsLevel3 > 0) completed = 3;
			if (starsLevel4 > 0) completed = 4;
			if (starsLevel5 > 0) completed = 5;
			if (starsLevel6 > 0) completed = 6;
			if (starsLevel7 > 0) completed = 7;
			if (starsLevel8 > 0) completed = 8;
			if (starsLevel9 > 0) completed = 9;
			if (starsLevel10 > 0) completed = 10;
			if (starsLevel11 > 0) completed = 11;
			if (starsLevel12 > 0) completed = 12;

			starsLabel.setText("Complete: " +completed + " / " + 12);
			table.add(starsLabel).padBottom(-100).padLeft(6).bottom();
		//	table.add(img).padBottom(-29).padLeft(4).bottom();

			button.stack(new Image(skin.getDrawable("top")), label, table)
					.expand().fill();
			break;
		case 2:
			int completed2;
			if (starsWORLD1 == 0) completed2 = 0;
			else completed2 = 1;
			if (starsWORLD2 > 0) completed2 = 2;
			if (starsWORLD3 > 0) completed2 = 3;
			if (starsWORLD4 > 0) completed2 = 4;
			if (starsWORLD5 > 0) completed2 = 5;
			if (starsWORLD6 > 0) completed2 = 6;
			if (starsWORLD7 > 0) completed2 = 7;
			if (starsWORLD8 > 0) completed2 = 8;
			if (starsWORLD9 > 0) completed2 = 9;
			if (starsWORLD10 > 0) completed2 = 10;
			if (starsWORLD11 > 0) completed2 = 11;
			if (starsWORLD12 > 0) completed2 = 12;
			Table table2 = new Table();
			starsLabel.setText("Complete: " +completed2 + " / " + 12);
			table2.add(starsLabel).padBottom(-100).padLeft(6).bottom();
		//	table2.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsLevel6 != 0) {
				button.stack(new Image(skin.getDrawable("townworld")),
						labelLocked, table2).expand().fill();

				// button.add(starsLabel);
			} else {
				button.stack(new Image(skin.getDrawable("townworldlocked")), label, table2)
						.expand().fill();
			}
			break;
		case 3:
			int completed3;
			if (starsICE1 == 0) completed3 = 0;
			else completed3 = 1;
			if (starsICE2 > 0) completed3 = 2;
			if (starsICE3 > 0) completed3 = 3;
			if (starsICE4 > 0) completed3 = 4;
			if (starsICE5 > 0) completed3 = 5;
			if (starsICE6 > 0) completed3 = 6;
			if (starsICE7 > 0) completed3 = 7;
			if (starsICE8 > 0) completed3  = 8;
			if (starsICE9 > 0) completed3 = 9;
			if (starsICE10 > 0) completed3 = 10;
			if (starsICE11 > 0) completed3 = 11;
			if (starsICE12 > 0) completed3 = 12;
			Table table3 = new Table();
			starsLabel.setText("Complete: " +completed3 + " / " + 12);
			table3.add(starsLabel).padBottom(-100).padLeft(6).bottom();
		//	table2.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsWORLD6 != 0) {
				button.stack(new Image(skin.getDrawable("iceworld")),
						labelLocked, table3).expand().fill();

				// button.add(starsLabel);
			} else {
				button.stack(new Image(skin.getDrawable("iceworldlocked")), label, table3)
						.expand().fill();
			}
			break;
		case 4:
			Table table4 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getLevelFourHighScore() + " / " + 30);
			table4.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			//table4.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsLevel3 == 0) {
				button.stack(new Image(skin.getDrawable("lockedlevel")),
						labelLocked, table4).expand().fill();

				// button.add(starsLabel);
			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table4)
						.expand().fill();
			}
			break;
		case 5:
			Table table5 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getLevelFiveHighScore() + " / " + 35);
			table5.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			//table5.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsLevel4 == 0) {
				button.stack(new Image(skin.getDrawable("lockedlevel")),
						labelLocked, table5).expand().fill();

				// button.add(starsLabel);
			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table5)
						.expand().fill();
			}
			break;
		case 6:
			Table table6 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getLevelSixHighScore() + " / " + 35);
			table6.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			//table6.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsLevel5 == 0) {
				button.stack(new Image(skin.getDrawable("lockedlevel")),
						labelLocked, table6).expand().fill();

				// button.add(starsLabel);
			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table6)
						.expand().fill();
			}
			break;
		case 7:
			Table table7 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getLevelSevenHighScore() + " / " + 40);
			table7.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			//table7.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsLevel6 == 0) {
				button.stack(new Image(skin.getDrawable("lockedlevel")),
						labelLocked, table7).expand().fill();

				// button.add(starsLabel);
			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table7)
						.expand().fill();
			}
			break;
		
		default:
			button.stack(new Image(skin.getDrawable("soon-level")), label2)
					.expand().fill();
			break;
		}

		button.row();
		button.add(starTable).height(10);

		button.setName(Integer.toString(level));
		button.addListener(levelClickListener);

		int levelSel = Integer.parseInt(button.getName());

		return button;
	}

	/**
	 * 129 Handle the click - in real life, we'd go to the level 130
	 */
	public ClickListener levelClickListener = new ClickListener() {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			curLevel = Integer.parseInt(event.getListenerActor().getName());
			if (curLevel == 1 ||
			// (curLevel == 2 && starsLevel1 != 0) ||
			// (curLevel == 3 && starsLevel2 != 0) ||
			// (curLevel == 4 && starsLevel3 != 0) ||
			// (curLevel == 5 && starsLevel4 != 0) ||
			// (curLevel == 6 && starsLevel5 != 0) ||
			// (curLevel == 7 && starsLevel6 != 0) ||
			// (curLevel == 8 && starsLevel7 != 0) ||
			// (curLevel == 9 && starsLevel8 != 0) ||
			// (curLevel == 10 && starsLevel9 != 0) ||
			// (curLevel == 11 && starsLevel10 != 0) ||
					(curLevel == 2 && starsLevel6 != 0) || (curLevel == 3 && starsWORLD6 != 0) )// ||
				// //(curLevel == 3 && starsLevel5 != 0));//(curLevel == 3 &&
				// starsLevel6 != 0))
				switch (curLevel) {
				case 1:
					stage.addAction(sequence(fadeOut(0.8f), run(new Runnable() {
						public void run() {
							game.setScreen(game.getLevelScreen());
						}
					})));
					break;
				case 2:
					stage.addAction(sequence(fadeOut(0.8f), run(new Runnable() {
						public void run() {
							game.setScreen(game.getLevelTownLevelScreen());
						}
					})));
					break;
				case 3:
					stage.addAction(sequence(fadeOut(0.8f), run(new Runnable() {
						public void run() {
							game.setScreen(game.getIceLevelsScreen());
						}
					})));
					break;
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
				case 11:
				case 12:
					stage.addAction(sequence(fadeOut(0.8f), run(new Runnable() {
						public void run() {
							game.setScreen(game.getLoadingScreen());
						}
					})));

					break;
				default:
					curStaticLevel = -1;
					// System.out.println("soon...");
					break;
				}
		}
		// })));
		// }
	};

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		// Table.drawDebug(stage);
	}

	@Override
	public void show() {

		
		/** for records highscores */
//		totalRecordsHighscores = (int) ( game.getPreferencesManager().getLevelOneHighScore() + game.getPreferencesManager().getLevelTwoHighScore() + game.getPreferencesManager().getLevelThreeHighScore() + game.getPreferencesManager().getLevelFourHighScore() + game.getPreferencesManager().getLevelFiveHighScore() + game.getPreferencesManager().getLevelSixHighScore() + game.getPreferencesManager().getLevelSevenHighScore() + 
//				game.getPreferencesManager().getLevelEightHighScore() + game.getPreferencesManager().getLevelNineHighScore() + game.getPreferencesManager().getLevelTenHighScore() + game.getPreferencesManager().getLevelElevenHighScore() + game.getPreferencesManager().getLevelTwelveHighScore() +
//				game.getPreferencesManager().getWORLDOneHighScore() + game.getPreferencesManager().getWORLDTwoHighScore() + game.getPreferencesManager().getWORLDThreeHighScore() + game.getPreferencesManager().getWORLDFourHighScore() + game.getPreferencesManager().getWORLDFiveHighScore() + game.getPreferencesManager().getWORLDSixHighScore() + game.getPreferencesManager().getWORLDSevenHighScore() + game.getPreferencesManager().getWORLDEightHighScore() + game.getPreferencesManager().getWORLDNineHighScore() +
//				game.getPreferencesManager().getWORLDTenHighScore() + game.getPreferencesManager().getWORLDElevenHighScore());// + starsWORLD12 );
//		System.out.println("records total " + totalRecordsHighscores);
		
		// BackButton Handeling

		Drawable menuDrawable = new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/backgroundworld2.png"))));

		backGImage = new Image(menuDrawable, Scaling.stretch);
		backGImage.setFillParent(true);

		stage.addActor(backGImage);

		skin = new Skin(Gdx.files.internal("ui/JsonFiles/uiskin.json"));
		skin.add("top", new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/forestworld.png")))),
				Drawable.class);
		skin.add("townworld", new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/townworld.png")))),
				Drawable.class);
		skin.add("townworldlocked", new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/worldtownlocked.png")))), Drawable.class);
		skin.add("iceworld", new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/iceworld.png")))),
				Drawable.class);
		skin.add("iceworldlocked", new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/worldicelocked.png")))), Drawable.class);
		
		skin.add("soon", new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/castleworld.png")))), Drawable.class);

		// skin.add("star-filled", skin.newDrawable("white", Color.YELLOW),
		// Drawable.class);
		// skin.add("star-unfilled", skin.newDrawable("white", Color.GRAY),
		// Drawable.class);
		Gdx.input.setInputProcessor(stage);

		container = new Table();
		stage.addActor(container);
		container.setFillParent(true);

		PagedScrollPane scroll = new PagedScrollPane();

		scroll.setFlingTime(0.4f);
		scroll.setPageSpacing(80);
		int c = 1;
		for (int l = 0; l < 3; l++) {
			b++;
			Table levels = new Table().pad(0);
			if (b == 1)
				levels.defaults().pad(0, 80, 0, 15);
			else
				levels.defaults().pad(0, 0, 0, 15);
			for (int y = 0; y < 1; y++) {
				levels.row();
				for (int x = 0; x < 1; x++) {
					levels.add(getLevelButton(c++)).fill().expand()
							.size(150, 188);
				}
			}
			scroll.addPage(levels);
		}
		container.add(scroll).expand().fill();

		// backGImage.addAction(parallel(moveTo(250, 250, 2, bounceOut),
		// color(Color.RED, 6), delay(0.5f), rotateTo(180, 5, swing)));
		backGImage
				.addAction(repeat(
						1,
						(sequence(scaleTo(2, 2, 0.8f), scaleTo(1, 1, 0.8f),
								delay(1.9f)))));

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

	}

	@Override
	public void hide() {
		dispose();

	}

	@Override
	public void dispose() {
		super.dispose();
		// stage.dispose();
		skin.dispose();

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
}
