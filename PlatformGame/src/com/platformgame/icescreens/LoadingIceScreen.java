package com.platformgame.icescreens;

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
import com.platformgame.screens.AbstractScreen;
import com.platformgame.screens.LevelScreen;
import com.platforngame.handlers.LoadingBar;

public class LoadingIceScreen extends AbstractScreen {

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

	public LoadingIceScreen(PlatformGame game) {
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

		if (IceLevelsScreen.curlevel == 1) {
			game.manager.setLoader(TiledMap.class, new TmxMapLoader(
					new InternalFileHandleResolver()));
			game.manager.load("maps/levelice1.tmx", TiledMap.class);
			game.manager.load("images/woodplatform.png", Texture.class);
			game.manager.load("images/backgroundice.png", Texture.class);
			game.manager.load("images/slashesrock.png", Texture.class);
			game.manager.load("images/bolltree.png", Texture.class);
			game.manager.load("animations/penguin.png", Texture.class);
			game.manager.load("images/spikesplatformvert.png", Texture.class);
			game.manager.load("images/spikesplatform.png", Texture.class);
			// game.manager.load("images/ropes.png", Texture.class);

		}

		if (IceLevelsScreen.curlevel == 2) {
			game.manager.setLoader(TiledMap.class, new TmxMapLoader(
					new InternalFileHandleResolver()));
			game.manager.load("maps/levelice2.tmx", TiledMap.class);
			game.manager.load("images/woodplatform.png", Texture.class);
			game.manager.load("images/backgroundice.png", Texture.class);
			game.manager.load("images/slashesrock.png", Texture.class);
			game.manager.load("images/bolltree.png", Texture.class);
			game.manager.load("animations/penguin.png", Texture.class);
			game.manager.load("images/spikesplatformvert.png", Texture.class);
			game.manager.load("images/spikesplatform.png", Texture.class);
			game.manager.load("images/swingwoodhorz.png", Texture.class);
		}

		if (IceLevelsScreen.curlevel == 3) {
			game.manager.setLoader(TiledMap.class, new TmxMapLoader(
					new InternalFileHandleResolver()));
			game.manager.load("maps/levelTown3.tmx", TiledMap.class);// game.manager.unload("images/backgroundbuilding.png");
			game.manager.load("images/wheel.png", Texture.class);
			game.manager.load("images/car.png", Texture.class);
			game.manager.load("images/slashesrock.png", Texture.class);
			game.manager.load("animations/rockman.png", Texture.class);
			game.manager.load("animations/man.png", Texture.class);
			game.manager.load("images/backgroundbuilding.png", Texture.class);
			game.manager.load("images/woodplatform.png", Texture.class);
		}

		if (IceLevelsScreen.curlevel == 4) {
			game.manager.setLoader(TiledMap.class, new TmxMapLoader(
					new InternalFileHandleResolver()));
			game.manager.load("maps/levelTown4.tmx", TiledMap.class);// game.manager.unload("images/backgroundbuilding.png");
			game.manager.load("images/wheel.png", Texture.class);
			game.manager.load("images/car.png", Texture.class);
			game.manager.load("animations/man.png", Texture.class);
			game.manager.load("images/backgroundbuilding.png", Texture.class);
			game.manager.load("animations/cat.png", Texture.class);
			game.manager.load("animations/pig.png", Texture.class);
			game.manager.load("images/shield.png", Texture.class);
			game.manager.load("images/spikesplatform.png", Texture.class);
			game.manager.load("images/woodplatform.png", Texture.class);
			game.manager.load("images/spikesrock.png", Texture.class);

		}

		if (IceLevelsScreen.curlevel == 5) {
			game.manager.setLoader(TiledMap.class, new TmxMapLoader(
					new InternalFileHandleResolver()));
			game.manager.load("maps/leveltown5.tmx", TiledMap.class);// game.manager.unload("images/backgroundbuilding.png");

			game.manager.load("images/garbage.png", Texture.class);
			game.manager.load("animations/man.png", Texture.class);
			game.manager.load("images/backgroundbuilding.png", Texture.class);
			game.manager.load("animations/cat.png", Texture.class);
			game.manager.load("animations/pig.png", Texture.class);
			game.manager.load("images/slashesrock.png", Texture.class);
			//game.manager.load("images/spikesrock.png", Texture.class);
		}

		if (IceLevelsScreen.curlevel == 6) {
			game.manager.setLoader(TiledMap.class, new TmxMapLoader(
					new InternalFileHandleResolver()));
			game.manager.load("maps/leveltown6.tmx", TiledMap.class);
			game.manager.load("animations/man.png", Texture.class);
			game.manager.load("images/backgroundbuilding.png", Texture.class);
			game.manager.load("animations/cat.png", Texture.class);
			game.manager.load("animations/pig.png", Texture.class);
			game.manager.load("images/wheel.png", Texture.class);
			game.manager.load("images/car.png", Texture.class);
			game.manager.load("images/spikesrock.png", Texture.class);

			game.manager.load("images/shield.png", Texture.class);
		}

		if (IceLevelsScreen.curlevel == 7) {
			game.manager.setLoader(TiledMap.class, new TmxMapLoader(
					new InternalFileHandleResolver()));
			game.manager.load("maps/leveltown7.tmx", TiledMap.class);
			game.manager.load("animations/man.png", Texture.class);
			game.manager.load("images/backgroundbuilding.png", Texture.class);
			game.manager.load("animations/cat.png", Texture.class);
			game.manager.load("animations/pig.png", Texture.class);
			game.manager.load("images/wheel.png", Texture.class);
			game.manager.load("images/car.png", Texture.class);
			game.manager.load("images/spikesrock.png", Texture.class);
			game.manager.load("images/garbage.png", Texture.class);

			game.manager.load("images/shield.png", Texture.class);
		}
		
		if (IceLevelsScreen.curlevel == 8) {
			game.manager.setLoader(TiledMap.class, new TmxMapLoader(
					new InternalFileHandleResolver()));
			game.manager.load("maps/leveltown8.tmx", TiledMap.class);
			game.manager.load("animations/man.png", Texture.class);
			game.manager.load("images/backgroundbuilding.png", Texture.class);
			game.manager.load("animations/cat.png", Texture.class);
			game.manager.load("animations/pig.png", Texture.class);
			
			game.manager.load("images/spikesrock.png", Texture.class);
			game.manager.load("images/garbage.png", Texture.class);
			game.manager.load("images/slashesrock.png", Texture.class);
			game.manager.load("images/shield.png", Texture.class);
		}
		
		if (IceLevelsScreen.curlevel == 9) {
			game.manager.setLoader(TiledMap.class, new TmxMapLoader(
					new InternalFileHandleResolver()));
			game.manager.load("maps/leveltown9.tmx", TiledMap.class);
			game.manager.load("images/backgroundbuilding.png", Texture.class);
			game.manager.load("animations/cat.png", Texture.class);
			game.manager.load("animations/pig.png", Texture.class);
			game.manager.load("images/wheel.png", Texture.class);
			game.manager.load("images/car.png", Texture.class);
		//	game.manager.load("images/spikesrock.png", Texture.class);
			game.manager.load("images/garbage.png", Texture.class);
		//	game.manager.load("images/slashesrock.png", Texture.class);
			game.manager.load("images/woodplatform.png", Texture.class);
			
			game.manager.load("images/swingwood.png", Texture.class);
		}
		
		if (IceLevelsScreen.curlevel == 10) {
			game.manager.setLoader(TiledMap.class, new TmxMapLoader(
					new InternalFileHandleResolver()));
			game.manager.load("maps/leveltown10.tmx", TiledMap.class);
		//	game.manager.load("animations/man.png", Texture.class);
			game.manager.load("images/backgroundbuilding.png", Texture.class);
			game.manager.load("animations/pig.png", Texture.class);
			game.manager.load("animations/cat.png", Texture.class);
			game.manager.load("images/spikesplatform.png", Texture.class);
			game.manager.load("images/woodplatform.png", Texture.class);
			game.manager.load("images/swingwood.png", Texture.class);
		}
		
		if (IceLevelsScreen.curlevel == 11) {
			game.manager.setLoader(TiledMap.class, new TmxMapLoader(
					new InternalFileHandleResolver()));
			game.manager.load("maps/leveltown11.tmx", TiledMap.class);
			//game.manager.load("animations/man.png", Texture.class);
			game.manager.load("images/backgroundbuilding.png", Texture.class);
			//game.manager.load("animations/pig.png", Texture.class);
			game.manager.load("animations/cat.png", Texture.class);
			game.manager.load("images/spikesplatform.png", Texture.class);
			game.manager.load("images/woodplatform.png", Texture.class);
			game.manager.load("images/swingwood.png", Texture.class);
			game.manager.load("images/garbage.png", Texture.class);
			game.manager.load("images/spikesrock.png", Texture.class);
		}
		
		if (IceLevelsScreen.curlevel == 12) {
			game.manager.setLoader(TiledMap.class, new TmxMapLoader(
					new InternalFileHandleResolver()));
			game.manager.load("maps/leveltown12.tmx", TiledMap.class);
			//game.manager.load("animations/man.png", Texture.class);
			game.manager.load("images/backgroundbuilding.png", Texture.class);
			game.manager.load("animations/pig.png", Texture.class);
			game.manager.load("animations/cat.png", Texture.class);
			game.manager.load("images/spikesplatform.png", Texture.class);
			game.manager.load("images/woodplatform.png", Texture.class);
			game.manager.load("images/swingwood.png", Texture.class);
			game.manager.load("images/slashesrock.png", Texture.class);
			//game.manager.load("images/garbage.png", Texture.class);
			//game.manager.load("images/spikesrock.png", Texture.class);
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
		System.out.println("dsg" + IceLevelsScreen.curlevel);
		if (game.manager.update()) { // Load some, will return true if done
			loadingBarHidden.addAction(sequence(fadeOut(0.6f),
					run(new Runnable() {
						public void run() {
							switch (IceLevelsScreen.curlevel) {
							case 1:
								if (count == 0) {
									game.setScreen(game.getLevelIce1());
									count = 1;
								}
								break;
							case 2:
								if (count == 0) {
									game.setScreen(game.getLevelIce2());
									count = 1;
								}
								break;
							case 3:
								if (count == 0) {
									game.setScreen(game.getLevelTown3Screen());
									count = 1;
								}

								break;
							case 4:
								if (count == 0) {
									game.setScreen(game.getLevelTown4Screen());
									count = 1;
								}
								break;
							case 5:
								if (count == 0) {
									game.setScreen(game.getLevelTown5Screen());
									count = 1;
								}
								break;
							case 6:
								if (count == 0) {
									game.setScreen(game.getLevelTown6Screen());
									count = 1;
								}
								break;
							case 7:
								if (count == 0) {
									game.setScreen(game.getLevelTown7Screen());
									count = 1;
								}							
								break;
							case 8:
								if (count == 0) {
									game.setScreen(game.getLevelTown8Screen());
									count = 1;
								}	
								break;
							case 9:
								if (count == 0) {
									game.setScreen(game.getLevelTown9Screen());
									count = 1;
								}	
								break;
							case 10:
								if (count == 0) {
									game.setScreen(game.getLevelTown10Screen());
									count = 1;
								}	
								break;
							case 11:
								if (count == 0) {
									game.setScreen(game.getLevelTown11Screen());
									count = 1;
								}	
								break;
							case 12:
								if (count == 0) {
									game.setScreen(game.getLevelTown12Screen());
									count = 1;
								}	
								break;
							default:

								break;
							}

						}
					}))); // loading

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
