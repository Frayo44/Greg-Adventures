package com.platformgame.townscreens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.repeat;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.platform.game.PlatformGame;
import com.platformgame.screens.AbstractScreen;
import com.platforngame.handlers.PagedScrollPane;

public class TownLevelsScreen extends AbstractScreen {

	public TownLevelsScreen(PlatformGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

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
	float starsWORLD11= game.getPreferencesManager().getWORLDElevenStars();
	float starsWORLD12 = game.getPreferencesManager().getWORLDTwelveStars();
	

	public static int curlevel;
	public static int curStaticWORLD;

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

	public Button getWORLDButton(int WORLD) {
		Button button = new Button(skin);
		ButtonStyle style = button.getStyle();
		style.up = style.down = null;

		starTable = new Table();
		starTable.defaults().pad(2);

		Label label = new Label(Integer.toString(WORLD), skin);
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
		
		//for coming soon levels..
		if(WORLD > 12){
			WORLD = 10102;
		}
		switch (WORLD) {
		case 1:
			Table table = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getWORLDOneHighScore() + " / " + 30);
			table.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table.add(img).padBottom(-29).padLeft(4).bottom();
			// starsLabel.setPosition(100, 100);
			button.stack(new Image(skin.getDrawable("top")), label, table)
					.expand().fill();
			break;
		case 2:
			Table table2 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getWORLDTwoHighScore() + " / " + 30);
			table2.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table2.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsWORLD1 == 0) {
				button.stack(new Image(skin.getDrawable("lockedWORLD")),
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
					.getWORLDThreeHighScore() + " / " + 30);
			table3.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table3.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsWORLD2 == 0) {
				button.stack(new Image(skin.getDrawable("lockedWORLD")),
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
					.getWORLDFourHighScore() + " / " + 30);
			table4.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table4.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsWORLD3 == 0) {
				button.stack(new Image(skin.getDrawable("lockedWORLD")),
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
					.getWORLDFiveHighScore() + " / " + 30);
			table5.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table5.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsWORLD4 == 0) {
				button.stack(new Image(skin.getDrawable("lockedWORLD")),
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
					.getWORLDSixHighScore() + " / " + 30);
			table6.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table6.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsWORLD5 == 0) {
				button.stack(new Image(skin.getDrawable("lockedWORLD")),
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
					.getWORLDSevenHighScore() + " / " + 30);
			table7.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table7.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsWORLD6 == 0) {
				button.stack(new Image(skin.getDrawable("lockedWORLD")),
						labelLocked, table7).expand().fill();

				// button.add(starsLabel);
			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table7)
						.expand().fill();
			}
			break;
		case 8:
			Table table8 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getWORLDEightHighScore() + " / " + 30);
			table8.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table8.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsWORLD7 == 0) {
				button.stack(new Image(skin.getDrawable("lockedWORLD")),
						labelLocked, table8).expand().fill();

				// button.add(starsLabel);
			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table8)
						.expand().fill();
			}
			break;
		case 9:
			Table table9 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getWORLDNineHighScore() + " / " + 30);
			table9.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table9.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsWORLD8 == 0) {
				button.stack(new Image(skin.getDrawable("lockedWORLD")),
						labelLocked, table9).expand().fill();

				// button.add(starsLabel);
			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table9)
						.expand().fill();
			}
			break;
		case 10:
			Table table10 = new Table();
			starsLabel.setText(game.getPreferencesManager()
					.getWORLDTenHighScore() + " / " + 30);
			table10.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table10.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsWORLD9 == 0) {
				button.stack(new Image(skin.getDrawable("lockedWORLD")),
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
					.getWORLDElevenHighScore() + " / " + 30);
			table11.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table11.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsWORLD10 == 0) {
				button.stack(new Image(skin.getDrawable("lockedWORLD")),
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
					.getWORLDTwelveHighScore() + " / " + 40);
			table12.add(starsLabel).padBottom(-30).padLeft(6).bottom();
			table12.add(img).padBottom(-29).padLeft(4).bottom();
			if (starsWORLD11 == 0) {
				button.stack(new Image(skin.getDrawable("lockedWORLD")),
						labelLocked, table12).expand().fill();

				// button.add(starsLabel);
			} else {
				button.stack(new Image(skin.getDrawable("top")), label, table12)
						.expand().fill();
			}
			break;
		default:
			button.stack(new Image(skin.getDrawable("soon-WORLD")), label2)
					.expand().fill();
			break;
		}

		button.row();
		button.add(starTable).height(10);

		button.setName(Integer.toString(WORLD));
		button.addListener(WORLDClickListener);

		int WORLDSel = Integer.parseInt(button.getName());

		switch (WORLDSel) {
		case 1:
			doStars(starsWORLD1);
			break;
		case 2:
			doStars(starsWORLD2);
			break;
		case 3:
			doStars(starsWORLD3);
			break;
		case 4:
			doStars(starsWORLD4);
			break;
		case 5:
			doStars(starsWORLD5);
			break;
		case 6:
			doStars(starsWORLD6);
			break;
		case 7:
			doStars(starsWORLD7);
			break;
		case 8:
			doStars(starsWORLD8);
			break;
		case 9:
			doStars(starsWORLD9);
			break;
		case 10:
			doStars(starsWORLD10);
			break;
		case 11:
			doStars(starsWORLD11);
			break;
		case 12:
			doStars(starsWORLD12);
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
	 * 129 Handle the click - in real life, we'd go to the WORLD 130
	 */
	public ClickListener WORLDClickListener = new ClickListener() {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			curlevel = Integer.parseInt(event.getListenerActor().getName());
			if(curlevel == 1 || 
					(curlevel == 2 && starsWORLD1 != 0) ||
					(curlevel == 3 && starsWORLD2 != 0) ||
					(curlevel == 4 && starsWORLD3 != 0) ||
					(curlevel == 5 && starsWORLD4 != 0) ||
					(curlevel == 6 && starsWORLD5 != 0) ||
					(curlevel == 7 && starsWORLD6 != 0) ||
					(curlevel == 8 && starsWORLD7 != 0) ||
					(curlevel == 9 && starsWORLD8 != 0) ||
					(curlevel == 10 && starsWORLD9 != 0) ||
					(curlevel == 11 && starsWORLD10 != 0) ||
					(curlevel == 12 && starsWORLD11 != 0) )// ||
					//(curWORLD == 3 && starsWORLD5 != 0));//(curWORLD == 3 && starsWORLD6 != 0))
			switch (curlevel) {
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
						game.setScreen(game.getLoadingTownScreen());
					}
				})));

				break;
			default:
				curStaticWORLD = -1;
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
		skin.add("lockedWORLD", new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/levelscreen.png")) ,1, 88, 150, 150)),
				Drawable.class);

		skin.add("star-filled", new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/levelscreen.png")) ,1, 1, 90, 85)),
				Drawable.class);
		skin.add("star-unfilled", new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/levelscreen.png")), 93,1 , 90, 85)),
				Drawable.class);
		skin.add("soon-WORLD", new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("images/levelscreen.png")), 305 ,88 , 150, 150)),
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
			Table WORLDs = new Table().pad(0);
			WORLDs.defaults().pad(0, 20, 0, 15);
			for (int y = 0; y < 2; y++) {
				WORLDs.row();
				for (int x = 0; x < 3; x++) {
					WORLDs.add(getWORLDButton(c++)).fill().expand()
							.size(63, 88);
				}
			}
			scroll.addPage(WORLDs);
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

