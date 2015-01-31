package com.platformgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.platform.game.PlatformGame;
import com.platforngame.handlers.BoundedCamera;

public class AbstractScreen implements Screen {
	// the fixed viewport dimensions (ratio: 1.6)
//	public static final int GAME_VIEWPORT_WIDTH = 320,
//			GAME_VIEWPORT_HEIGHT = 240;
//	public static final int MENU_VIEWPORT_WIDTH = 240,
//			MENU_VIEWPORT_HEIGHT = 480;

	// Prefences that handle in which player the user will play
	/*
	 * 0 - Normal Player
	 * 1 - 500 Handred Player
	 */
	
	protected final PlatformGame game;
	protected Stage stage;

	private BitmapFont font;
	private SpriteBatch batch;
	public static Skin skin2;
	private TextureAtlas atlas;
	private Table table;
	
	
	protected SpriteBatch sb;
	protected BoundedCamera cam;
	protected OrthographicCamera hudCam;

	public AbstractScreen(PlatformGame game) {
		this.game = game;
//		int width = (isGameScreen() ? GAME_VIEWPORT_WIDTH : MENU_VIEWPORT_WIDTH);
//		int height = (isGameScreen() ? GAME_VIEWPORT_HEIGHT
//				: MENU_VIEWPORT_HEIGHT);
		this.stage = new Stage(new StretchViewport(320, 240));
	}

	protected String getName() {
		return getClass().getSimpleName();
	}

	protected boolean isGameScreen() {
		return false;
	}

	// Lazily loaded collaborators

	public BitmapFont getFont() {
		if (font == null) {
			font = new BitmapFont();
		}
		return font;
	}

	public SpriteBatch getBatch() {
		if (batch == null) {
			batch = new SpriteBatch();
		}
		return batch;
	}

	// public TextureAtlas getAtlas()
	// {
	//
	// }

	public static Skin getSkin() {
		
		if(skin2 == null){
			FileHandle skinFile = Gdx.files
					.internal("ui/JsonFiles/uiskin.json");
			skin2 = new Skin(skinFile);
		}
			
		
		return skin2;
	}

	protected Table getTable() {
		
			table = new Table(getSkin());
			table.setFillParent(true);
			
			stage.addActor(table);
		
		return table;
	}

	// Screen implementation

	@Override
	public void show() {
		Gdx.app.log(PlatformGame.LOG, "Showing screen: " + getName());

		// set the stage as the input processor
		Gdx.input.setInputProcessor(stage);
		
		
		sb = game.getSpriteBatch();
		cam = game.getCamera();
		
		hudCam = game.getHUDCamera();
		
		//System.out.println("I am here ");
		
		
		/**
		 *  check in base levels!!!!!!!!!!!!!!!!!!!!!!!1
		 *  check in base levels!!!!!!!!!!!!!!!!!!!!!!!1
		 *  check in base levels!!!!!!!!!!!!!!!!!!!!!!!1
		 *  check in base levels!!!!!!!!!!!!!!!!!!!!!!!1
		 *  check in base levels!!!!!!!!!!!!!!!!!!!!!!!1
		 *  check in base levels!!!!!!!!!!!!!!!!!!!!!!!1
		 *  
		 **/
		int totalRecordsHighscores = (int) ( game.getPreferencesManager().getLevelOneHighScore() + game.getPreferencesManager().getLevelTwoHighScore() + game.getPreferencesManager().getLevelThreeHighScore() + game.getPreferencesManager().getLevelFourHighScore() + game.getPreferencesManager().getLevelFiveHighScore() + game.getPreferencesManager().getLevelSixHighScore() + game.getPreferencesManager().getLevelSevenHighScore() + 
				game.getPreferencesManager().getLevelEightHighScore() + game.getPreferencesManager().getLevelNineHighScore() + game.getPreferencesManager().getLevelTenHighScore() + game.getPreferencesManager().getLevelElevenHighScore() + game.getPreferencesManager().getLevelTwelveHighScore() +
				game.getPreferencesManager().getWORLDOneHighScore() + game.getPreferencesManager().getWORLDTwoHighScore() + game.getPreferencesManager().getWORLDThreeHighScore() + game.getPreferencesManager().getWORLDFourHighScore() + game.getPreferencesManager().getWORLDFiveHighScore() + game.getPreferencesManager().getWORLDSixHighScore() + game.getPreferencesManager().getWORLDSevenHighScore() + game.getPreferencesManager().getWORLDEightHighScore() + game.getPreferencesManager().getWORLDNineHighScore() +
				game.getPreferencesManager().getWORLDTenHighScore() + game.getPreferencesManager().getWORLDElevenHighScore() + game.getPreferencesManager().getWORLDTwelveHighScore()
				/**ice**/ +
				game.getPreferencesManager().getICEOneHighScore() + game.getPreferencesManager().getICETwoHighScore() + game.getPreferencesManager().getICEThreeHighScore() + game.getPreferencesManager().getICEFourHighScore() + game.getPreferencesManager().getICEFiveHighScore() + game.getPreferencesManager().getICESixHighScore() + game.getPreferencesManager().getICESevenHighScore() + game.getPreferencesManager().getICEEightHighScore() + game.getPreferencesManager().getICENineHighScore() +
				game.getPreferencesManager().getICETenHighScore() + game.getPreferencesManager().getICEElevenHighScore() + game.getPreferencesManager().getICETwelveHighScore()
				);
		if (game.actionResolver2.getSignedInGPGS2()) {
		
			 game.actionResolver2.submitScoreGPGS2(totalRecordsHighscores);
		}
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(PlatformGame.LOG, "Resizing screen: " + getName() + " to: "
				+ width + " x " + height);
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render(float delta) {
		// (1) process the game logic

		// update the actors
		//stage.act(delta);

		// (2) draw the result

		// clear the screen with the given RGB color (black)
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// draw the actors
		//stage.draw();

		// draw the table debug lines
		//Table.drawDebug(stage);
	}

	@Override
	public void hide() {
		Gdx.app.log(PlatformGame.LOG, "Hiding screen: " + getName());

		// dispose the screen when leaving the screen;
		// note that the dipose() method is not called automatically by the
		// framework, so we must figure out when it's appropriate to call it
		dispose();
	}

	@Override
	public void pause() {
		Gdx.app.log(PlatformGame.LOG, "Pausing screen: " + getName());
	}

	@Override
	public void resume() {
		Gdx.app.log(PlatformGame.LOG, "Resuming screen: " + getName());
	}

	@Override
	public void dispose() {
		Gdx.app.log(PlatformGame.LOG, "Disposing screen: " + getName());

		// the following call disposes the screen's stage, but on my computer it
		// crashes the game so I commented it out; more info can be found at:
		// http://www.badlogicgames.com/forum/viewtopic.php?f=11&t=3624
		stage.dispose();

		// as the collaborators are lazily loaded, they may be null
		if (font != null)
			font.dispose();
		if (batch != null)
			batch.dispose();
		if (skin2 != null)
			skin2.dispose();
		skin2 = null;
		if (atlas != null)
			atlas.dispose();
	}
}
