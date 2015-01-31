package com.platformgame.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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

public class LevelScreen extends AbstractScreen {

	public LevelScreen(PlatformGame game) {
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
	float starsLevel11= game.getPreferencesManager().getLevelElevenStars();
	float starsLevel12 = game.getPreferencesManager().getLevelTwelveStars();
	

	public static int curLevel;
	public static int curStaticLevel;

	private Skin skin;
	private Table container;

	private Table table;
	Button buttonBack;

	private Image backGImage;
	
	Table starTable;

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

		Label label = new Label(Integer.toString(level), skin);
		label.setFontScale(1f);
		label.setAlignment(Align.center);

		Label label2 = new Label("", skin);
		label2.setFontScale(1f);
		label2.setAlignment(Align.center);

		Label labelLocked = new Label("", skin);
		label2.setFontScale(1f);
		label2.setAlignment(Align.center);

		Label starsLabel = new Label("stadfrs", skin, "big-font");

		starsLabel.setFontScale(0.3f);
		// starsLabel.setPosition(12, -5);
		starsLabel.setAlignment(Align.bottom, Align.center);

		TextureRegion crystal;
		Texture tex = new Texture(Gdx.files.internal("images/hud.png"));

		crystal = new TextureRegion(tex, 80, 0, 16, 16);
		Image img = new Image(crystal);
		img.setScale(0.5f);
		// Stack the image and the label at the top of our button

		switch (level) {
		case 1:
			Table table = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getLevelOneHighScore() + " / " + 30);
			table.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table.add(img).padBottom(-29).padLeft(4).bottom();
			// starsLabel.setPosition(100, 100);
			button.stack(new Image(skin.getDrawable("top")), label, table)
					.expand().fill();
			game.manager.load("images/ropes.png", Texture.class);
			break;
		case 2:
			Table table2 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getLevelTwoHighScore() + " / " + 30);
			table2.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table2.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsLevel1 == 0) {
				button.stack(new Image(skin.getDrawable("lockedlevel")),
						labelLocked, table2).expand().fill();

				// button.add(starsLabel);
			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table2)
						.expand().fill();
			}
			break;
		case 3:
			Table table3 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getLevelThreeHighScore() + " / " + 30);
			table3.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table3.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsLevel2 == 0) {
				button.stack(new Image(skin.getDrawable("lockedlevel")),
						labelLocked, table3).expand().fill();

				// button.add(starsLabel);
			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table3)
						.expand().fill();
			}
			break;
		case 4:
			Table table4 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getLevelFourHighScore() + " / " + 30);
			table4.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table4.add(img).padBottom(-29).padLeft(4).bottom();
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
			table5.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsLevel4 == 0) {
				button.stack(new Image(skin.getDrawable("lockedlevel")),
						labelLocked, table5).expand().fill();

				
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
			table6.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsLevel5 == 0) {
				button.stack(new Image(skin.getDrawable("lockedlevel")),
						labelLocked, table6).expand().fill();

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
			table7.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsLevel6 == 0) {
				button.stack(new Image(skin.getDrawable("lockedlevel")),
						labelLocked, table7).expand().fill();

			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table7)
						.expand().fill();
			}
			break;
		case 8:
			Table table8 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getLevelEightHighScore() + " / " + 35);
			table8.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table8.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsLevel7 == 0) {
				button.stack(new Image(skin.getDrawable("lockedlevel")),
						labelLocked, table8).expand().fill();

				
			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table8)
						.expand().fill();
			}
			break;
		case 9:
			Table table9 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getLevelNineHighScore() + " / " + 60);
			table9.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table9.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsLevel8 == 0) {
				button.stack(new Image(skin.getDrawable("lockedlevel")),
						labelLocked, table9).expand().fill();
			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table9)
						.expand().fill();
			}
			break;
		case 10:
			Table table10 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getLevelTenHighScore() + " / " + 30);
			table10.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table10.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsLevel9 == 0) {
				button.stack(new Image(skin.getDrawable("lockedlevel")),
						labelLocked, table10).expand().fill();

				// button.add(starsLabel);
			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table10)
						.expand().fill();
			}
			break;
		case 11:
			Table table11 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getLevelElevenHighScore() + " / " + 35);
			table11.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table11.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsLevel10 == 0) {
				button.stack(new Image(skin.getDrawable("lockedlevel")),
						labelLocked, table11).expand().fill();

				// button.add(starsLabel);
			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table11)
						.expand().fill();
			}
			break;
		case 12:
			Table table12 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getLevelTwelveHighScore() + " / " + 30);
			table12.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table12.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsLevel11 == 0) {
				button.stack(new Image(skin.getDrawable("lockedlevel")),
						labelLocked, table12).expand().fill();

				// button.add(starsLabel);
			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table12)
						.expand().fill();
			}
			break;

		}

		button.row();
		button.add(starTable).height(10);

		button.setName(Integer.toString(level));
		button.addListener(levelClickListener);

		int levelSel = Integer.parseInt(button.getName());

		switch (levelSel) {
		case 1:
			doStars(starsLevel1);
			break;
		case 2:
			doStars(starsLevel2);
			break;
		case 3:
			doStars(starsLevel3);
			break;
		case 4:
			doStars(starsLevel4);
			break;
		case 5:
			doStars(starsLevel5);
			break;
		case 6:
			doStars(starsLevel6);
			break;
		case 7:
			doStars(starsLevel7);
			break;
		case 8:
			doStars(starsLevel8);
			break;
		case 9:
			doStars(starsLevel9);
			break;
		case 10:
			doStars(starsLevel10);
			break;
		case 11:
			doStars(starsLevel11);
			break;
		case 12:
			doStars(starsLevel12);
			break;
		default:
			for (int star = 0; star < 3; star++) {
				if (-1 > star) {
					starTable.add(new Image(skin.getDrawable("star-filled")))
							.width(20).height(12);
				} else {
					starTable.add(new Image(skin.getDrawable("star-unfilled")))
							.width(20).height(12);
				}
			}
			break;
		}

		return button;
	}
	
	private void doStars(float stars){
		for (int star = 0; star < 3; star++) {
			if (stars > star) {
				starTable.add(new Image(skin.getDrawable("star-filled")))
						.width(20).height(15);
			} else {
				starTable.add(new Image(skin.getDrawable("star-unfilled")))
						.width(20).height(15);
			}
		}
	}

	/**
	 * 129 Handle the click - in real life, we'd go to the level 130
	 */
	public ClickListener levelClickListener = new ClickListener() {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			curLevel = Integer.parseInt(event.getListenerActor().getName());
			if(curLevel == 1 || 
					(curLevel == 2 && starsLevel1 != 0) ||
					(curLevel == 3 && starsLevel2 != 0) ||
					(curLevel == 4 && starsLevel3 != 0) ||
					(curLevel == 5 && starsLevel4 != 0) ||
					(curLevel == 6 && starsLevel5 != 0) ||
					(curLevel == 7 && starsLevel6 != 0) ||
					(curLevel == 8 && starsLevel7 != 0) ||
					(curLevel == 9 && starsLevel8 != 0) ||
					(curLevel == 10 && starsLevel9 != 0) ||
					(curLevel == 11 && starsLevel10 != 0) ||
					(curLevel == 12 && starsLevel11 != 0) )
			switch (curLevel) {
			case 1:
			case 2:
			case 3:
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

		// BackButton Handeling

		Drawable menuDrawable = new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/backgroundlevel.png"))));

		backGImage = new Image(menuDrawable, Scaling.stretch);
		backGImage.setFillParent(true);

		stage.addActor(backGImage);

		skin = new Skin(Gdx.files.internal("ui/JsonFiles/uiskin.json"));
		skin.add("top", new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/levelscreen.png")) ,153, 88, 150, 150)),
				Drawable.class);
		skin.add("lockedlevel", new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/levelscreen.png")) ,1, 88, 150, 150)),
				Drawable.class);

		skin.add("star-filled", new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/levelscreen.png")) ,1, 1, 90, 85)),
				Drawable.class);
		skin.add("star-unfilled", new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/levelscreen.png")), 93,1 , 90, 85)),
				Drawable.class);

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
		for (int l = 0; l < 2; l++) {
			Table levels = new Table().pad(0);
			levels.defaults().pad(0, 20, 0, 15);
			for (int y = 0; y < 2; y++) {
				levels.row();
				for (int x = 0; x < 3; x++) {
					levels.add(getLevelButton(c++)).fill().expand()
							.size(63, 88);
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


		game.getMusicManager().stop();

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
