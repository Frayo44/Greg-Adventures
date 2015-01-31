package com.platformgame.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.platform.game.PlatformGame;
import com.platforngame.handlers.LoadingBar;

public class LoadingScreen extends AbstractScreen {

	public static boolean wasCalled = false;

	private Stage stage;
	int count = 0;
	private Image logo;
	private Image loadingFrame;
	private Image loadingBarHidden;
	private Image screenBg;
	private Image loadingBg;

	private float startX, endX;
	private float percent;

	private Actor loadingBar;

	public LoadingScreen(PlatformGame game) {
		super(game);
	}

	@Override
	public void show() {
		count = 0;
		wasCalled = true;

		// Tell the manager to load assets for the loading screen
		game.manager.load("data/loading.pack", TextureAtlas.class);
		// Wait until they are finished loading
		game.manager.finishLoading();

		// Initialize the stage where we will place everything
		stage = new Stage();

		// Get our textureatlas from the manager
		TextureAtlas atlas = game.manager.get("data/loading.pack",
				TextureAtlas.class);

		// Grab the regions from the atlas and create some images
		logo = new Image(atlas.findRegion("libgdx-logo"));
		loadingFrame = new Image(atlas.findRegion("loading-frame"));
		loadingBarHidden = new Image(atlas.findRegion("loading-bar-hidden"));
		screenBg = new Image(atlas.findRegion("screen-bg"));
		loadingBg = new Image(atlas.findRegion("loading-frame-bg"));

		// Add the loading bar animation
		Animation anim = new Animation(0.05f,
				atlas.findRegions("loading-bar-anim"));
		anim.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
		loadingBar = new LoadingBar(anim);

		// Or if you only need a static bar, you can do
		// loadingBar = new Image(atlas.findRegion("loading-bar1"));

		// Add all the actors to the stage
		stage.addActor(screenBg);
		stage.addActor(loadingBar);
		stage.addActor(loadingBg);
		stage.addActor(loadingBarHidden);
		stage.addActor(loadingFrame);
		stage.addActor(logo);
		
		System.out.println(LevelScreen.curLevel);
	
		if (LevelScreen.curLevel == 1) {
			game.manager.setLoader(TiledMap.class,new TmxMapLoader(new InternalFileHandleResolver()));
			game.manager.load("maps/level1.tmx", TiledMap.class);
			game.manager.load("images/background.png", Texture.class);
		}

		if (LevelScreen.curLevel == 2) {
			game.manager.setLoader(TiledMap.class,new TmxMapLoader(new InternalFileHandleResolver()));
			game.manager.load("maps/level2.tmx", TiledMap.class);
			game.manager.load("images/woodrock.png", Texture.class);
			game.manager.load("images/background.png", Texture.class);
			game.manager.load("images/tree.png", Texture.class);
			
		}

		if (LevelScreen.curLevel == 3) {
			game.manager.setLoader(TiledMap.class,new TmxMapLoader(new InternalFileHandleResolver()));
			game.manager.load("maps/level3.tmx",TiledMap.class);
			game.manager.load("images/bouncyball.png", Texture.class);
			game.manager.load("animations/dragonfly.png", Texture.class);
			game.manager.load("images/backgroundnight.png", Texture.class);
			game.manager.load("images/tree.png", Texture.class);
			game.manager.load("images/log.png", Texture.class);
		}
		
		if (LevelScreen.curLevel == 4) {
			game.manager.setLoader(TiledMap.class,new TmxMapLoader(new InternalFileHandleResolver()));
			game.manager.load("maps/level4.tmx", TiledMap.class);
			game.manager.load("images/woodrock.png", Texture.class);
			game.manager.load("images/background.png", Texture.class);
			
		}
		
		if (LevelScreen.curLevel == 5){
			game.manager.setLoader(TiledMap.class,new TmxMapLoader(new InternalFileHandleResolver()));
			game.manager.load("maps/level5.tmx", TiledMap.class);
			game.manager.load("images/background3.png", Texture.class);
		//	game.manager.load("images/firerope.png", Texture.class);
			game.manager.load("images/wheel.png", Texture.class);
			game.manager.load("images/car.png", Texture.class);

			game.manager.load("images/tree.png", Texture.class);
		}
		
		if (LevelScreen.curLevel == 6){
			game.manager.setLoader(TiledMap.class,new TmxMapLoader(new InternalFileHandleResolver()));
			game.manager.load("maps/level6.tmx", TiledMap.class);
			//game.manager.load("images/firerope.png", Texture.class);
			game.manager.load("images/wheel.png", Texture.class);
			game.manager.load("images/car.png", Texture.class);
			game.manager.load("images/bouncyball.png", Texture.class);
			game.manager.load("images/background.png", Texture.class);

			game.manager.load("images/log.png", Texture.class);
		}
		
		if (LevelScreen.curLevel == 7){
			game.manager.setLoader(TiledMap.class,new TmxMapLoader(new InternalFileHandleResolver()));
			game.manager.load("maps/level7.tmx", TiledMap.class);
			game.manager.load("images/background4.png", Texture.class);

			game.manager.load("images/tree.png", Texture.class);
			//game.manager.load("images/background.png", Texture.class);
		}
		
		if (LevelScreen.curLevel == 8){
			game.manager.setLoader(TiledMap.class,new TmxMapLoader(new InternalFileHandleResolver()));
			game.manager.load("maps/level8.tmx", TiledMap.class);
			game.manager.load("images/wheel.png", Texture.class);
			game.manager.load("images/car.png", Texture.class);
			//game.manager.load("images/firerope.png", Texture.class);
			game.manager.load("images/background.png", Texture.class);
		}
		
		if (LevelScreen.curLevel == 9){
			game.manager.setLoader(TiledMap.class,new TmxMapLoader(new InternalFileHandleResolver()));
			game.manager.load("maps/level9.tmx", TiledMap.class);
			game.manager.load("images/wheel.png", Texture.class);
			game.manager.load("images/car.png", Texture.class);
	//		game.manager.load("images/firerope.png", Texture.class);
			game.manager.load("images/background3.png", Texture.class);
		}
		
		if (LevelScreen.curLevel == 10){
			game.manager.setLoader(TiledMap.class,new TmxMapLoader(new InternalFileHandleResolver()));
			game.manager.load("maps/level10.tmx", TiledMap.class);
			game.manager.load("images/wheel.png", Texture.class);
			game.manager.load("images/car.png", Texture.class);
		//	game.manager.load("images/firerope.png", Texture.class);
			game.manager.load("images/bouncyball.png", Texture.class);
			game.manager.load("images/background.png", Texture.class);
		}
		
		if (LevelScreen.curLevel == 11){
			game.manager.setLoader(TiledMap.class,new TmxMapLoader(new InternalFileHandleResolver()));
			game.manager.load("maps/level11.tmx", TiledMap.class);
		//	game.manager.load("images/background3.png", Texture.class);
			//game.manager.load("images/firerope.png", Texture.class);
			game.manager.load("images/wheel.png", Texture.class);
			game.manager.load("images/car.png", Texture.class);
			game.manager.load("images/background4.png", Texture.class);
		}
		
		if (LevelScreen.curLevel == 12){
			game.manager.setLoader(TiledMap.class,new TmxMapLoader(new InternalFileHandleResolver()));
			game.manager.load("maps/level4.tmx", TiledMap.class);
			game.manager.load("images/background.png", Texture.class);
			game.manager.load("images/woodrock.png", Texture.class);
		}

	}

	@Override
	public void resize(int width, int height) {
		// Set our screen to always be XXX x 480 in size
		// width = 480 * width / height;
		// height = 480;
		stage.getViewport().update(width, height, true);

		// Make the background fill the screen
		screenBg.setSize(width, height);

		// Place the logo in the middle of the screen and 100 px up
		logo.setX((width - logo.getWidth()) / 2);
		logo.setY((height - logo.getHeight()) / 2 + 100);

		// Place the loading frame in the middle of the screen
		loadingFrame.setX((stage.getWidth() - loadingFrame.getWidth()) / 2);
		loadingFrame.setY((stage.getHeight() - loadingFrame.getHeight()) / 2);

		// Place the loading bar at the same spot as the frame, adjusted a few
		// px
		loadingBar.setX(loadingFrame.getX() + 15);
		loadingBar.setY(loadingFrame.getY() + 5);

		// Place the image that will hide the bar on top of the bar, adjusted a
		// few px
		loadingBarHidden.setX(loadingBar.getX() + 35);
		loadingBarHidden.setY(loadingBar.getY() - 3);
		// The start position and how far to move the hidden loading bar
		startX = loadingBarHidden.getX();
		endX = 460;

		// The rest of the hidden bar
		loadingBg.setSize(450, 50);
		loadingBg.setX(loadingBarHidden.getX() + 30);
		loadingBg.setY(loadingBarHidden.getY() + 3);
	}

	@Override
	public void render(float delta) {
		// Clear the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (game.manager.update()) { // Load some, will return true if done
			loadingBarHidden.addAction(sequence(fadeOut(0.6f), run(new Runnable() {
				public void run() {
					switch (LevelScreen.curLevel) {
					case 1:
						if(count == 0){
							game.setScreen(game.getLevel1Screen());
							count = 1;
						}
						
						break;
					case 2:
						if(count == 0){
						game.setScreen(game.getLevel2Screen());
						count = 1;
						}
						break;
					case 3:
						if(count == 0){
						game.setScreen(game.getLevel3Screen());
						count = 1;
						}
						break;
					case 4:
						if(count == 0){
						game.setScreen(game.getLevel4Screen());
						count = 1;
						}
						break;
					case 5:
						if(count == 0){
						game.setScreen(game.getLevel5Screen());
						count = 1;
						}
						break;
					case 6:
						if(count == 0){
						game.setScreen(game.getLevel6Screen());
						count = 1;
						}
						break;
					case 7:
						if(count == 0){
						game.setScreen(game.getLevel7Screen());
						count = 1;
						}
						break;
					case 8:
						if(count == 0){
						game.setScreen(game.getLevel8Screen());
						count = 1;
						}
						break;
					case 9:
						if(count == 0){
						game.setScreen(game.getLevel9Screen());
						count = 1;
						}
						break;
					case 10:
						if(count == 0){
						game.setScreen(game.getLevel10Screen());
						count = 1;
						}
						break;
					case 11:
						if(count == 0){
						game.setScreen(game.getLevel11Screen());
						count = 1;
						}
						break;
					case 12:
						if(count == 0){
						game.setScreen(game.getLevel12Screen());
						count = 1;
						}
						break;
					default:
						break;
							}

				}
			})));			// loading
			
		}

		// Interpolate the percentage to make it more smooth
		percent = Interpolation.linear.apply(percent,
				game.manager.getProgress(), 0.1f);

		// Update positions (and size) to match the percentage
		loadingBarHidden.setX(startX + endX * percent);
		loadingBg.setX(loadingBarHidden.getX() + 30);
		loadingBg.setWidth(450 - 450 * percent);
		loadingBg.invalidate();

		// Show the loading screen
		stage.act();
		stage.draw();
	}

	@Override
	public void hide() {

		// Dispose the loading assets as we no longer need them
		game.manager.unload("data/loading.pack");
	}
}
