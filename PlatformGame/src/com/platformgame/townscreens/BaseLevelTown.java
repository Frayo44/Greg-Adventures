package com.platformgame.townscreens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.platforngame.handlers.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJoint;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.platform.game.PlatformGame;
import com.platformgame.entities.Car;
import com.platformgame.entities.Crystals;
import com.platformgame.entities.DragonEnemy;
import com.platformgame.entities.GameFont;
import com.platformgame.entities.HealthBar;
import com.platformgame.entities.Player;
import com.platformgame.entities.Rock;
import com.platformgame.entities.Rock2;
import com.platformgame.entities.TheEnd;
import com.platformgame.screens.AbstractScreen;
import com.platforngame.handlers.B2DVars;
import com.platforngame.handlers.Background;
import com.platforngame.handlers.BodyEditorLoader;
import com.platforngame.handlers.BoundedCamera;
import com.platforngame.handlers.MusicManager.TyrianMusic;
import com.platforngame.handlers.MyContactListener;

public class BaseLevelTown extends AbstractScreen {

	// Car Creating
	private Car car, car2, car3;
	float angleAxe = 0;
	Skin skin, skin1, skin2, skin3, skin4, skin5, skin6, skin7, skin8, skin9;
	Table tableLabel;
	
	
	Body bottleModel;
	
	Vector2 bottleModelOrigin;
	Sprite bottleSprite;
	
	/** level varieble that handels the level that we are now **/
	public static int level;
	MapLayer layer2;
	
	Table tablego;

	/** The blue shield that protect pinky **/
	boolean to_start_counting_shield = false;
	int count_shield = 0;

	public BaseLevelTown(PlatformGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	/** Game State, manage all the states that in the game **/
	public enum GameState {
		READY, RUNNING, PAUSED, GAMEOVER, LEVEL_COMPLETED, BEFORE_RUNNING, BEFORE_PAUSED, BEFORE_LEVELCOMPLETED, BEFORE_GAMEOVER

	}

	public static int hashia = 0;
	
	/** Health Bar that in the left upper side in the game **/
	protected Array<HealthBar> healthBar;

	public GameState state = GameState.READY;

	/** an array the handle all kinds of rope in the game**/
	Body[] bdRopes;

	/** All the crystals that the player have recieved in the current level **/
	protected int totalCryStals;

	// Sone Labels Foe Level Completd States
	protected Label curScore;
	protected Label highScore;

	// Font for crystals
	GameFont gameFont;

	TextureRegion rope, rope2, rope3, ropeFire, woodpole, kirby, theend;

	// Back Grounds
	private Background[] backgrounds;

	// Play Joystic booleans
	private boolean if_touch_left_side;
	private boolean if_touch_right_side;

	// The texture in the end
	TheEnd theEnd;
	int moneAD;
	// all atlasses for the game
	private TextureAtlas atlasleft, atlasright, atlasjump, atlaspause,
			atlashouse, atlasresume, atlasRestart, atlasNextLevel;
	// private Table table;
	Button buttonRight, buttonLeft, buttonJump, buttonPause, buttonHome,
			buttonResume, buttonRestart;

	int tileMapWidth;
	int tileMapHeight;

	private boolean debug = false;
	float posY = 0;
	private World world;
	private Box2DDebugRenderer b2dr;
	float degrees = 0;
	// all the body int the world' for removing them
	private Array<Body> worldBodies = new Array<Body>();

	public static MyContactListener cl;

	private TiledMap tileMap;
	private float tileSize;
	private OrthogonalTiledMapRenderer tmr;

	/** Main Player **/
	protected Player player;
	private Array<Crystals> crystals;
	private Array<Rock> hearts;
	private Array<Rock> shields;
	private Array<Rock> rocks;
	private BoundedCamera b2dCam;
	private Array<Rock> garbages;
	private Array<Rock> bdRopesPoints;
	private Array<Rock> movingPlatform;
	private Array<Rock> rollingSpikes;
	private Array<Rock2> axes;
	Body ebody;

	protected Image nextLevelImage;
	protected Image gameOverImage;

	private Array<DragonEnemy> dragonEnemy;

	/** Function that handels all the joystic bar movement **/
	public void handleInput() {
		if (if_touch_left_side) {
			if (cl.isJoysticAvailable) {
				if (player.getBody().getLinearVelocity().x >= -2) {
					player.getBody().setLinearVelocity(-2,
							player.getBody().getLinearVelocity().y);
				}

				else if (player.getBody().getLinearVelocity().x <= -2) {
					player.getBody()
							.setLinearVelocity(
									-(Math.abs(player.getBody()
											.getLinearVelocity().x)),
									player.getBody().getLinearVelocity().y);
				}
			}
		}

		if (if_touch_right_side) {
			if (cl.isJoysticAvailable) {
				if (level != 8) {

					if (player.getBody().getLinearVelocity().x <= 2) {
						player.getBody().setLinearVelocity(2,
								player.getBody().getLinearVelocity().y);
					}

					else {
						player.getBody()
								.setLinearVelocity(
										Math.abs(player.getBody()
												.getLinearVelocity().x),
										player.getBody().getLinearVelocity().y);
					}
				} else {
					if (player.getBody().getLinearVelocity().x <= 2.5f) {
						player.getBody().setLinearVelocity(2.5f,
								player.getBody().getLinearVelocity().y);
					}

					else {
						player.getBody()
								.setLinearVelocity(
										Math.abs(player.getBody()
												.getLinearVelocity().x),
										player.getBody().getLinearVelocity().y);
					}
				}
			}
		}

	}

	public void update(float dt) {
		moneAD =0;
		
		if (player.enable_sheild) {

			// Filter filter = new Filter();
			// filter.categoryBits = B2DVars.BIT_PLAYER;
			// filter.maskBits = B2DVars.BIT_RED | B2DVars.BIT_PLAYER;
			player.getBody().getFixtureList().get(0).setUserData("ssad");
		} else {
			player.getBody().getFixtureList().get(0).setUserData("player");
		}

		//System.out.println("player x - " + player.getPosition().x
		//		+ "player y - " + player.getPosition().y);
		handleInput();

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			player.getBody().setLinearVelocity(4, 1);
		}

		world.step(dt, 6, 2);
		// velocity Handeling
		if (player.getBody().getLinearVelocity().y >= 4) {
			player.getBody().setLinearVelocity(
					player.getBody().getLinearVelocity().x, 4);
		}

		// remove crystals
		Array<Body> bodies = cl.getBodiesToRemove();
		for (int i = 0; i < bodies.size; i++) {
			Body b = bodies.get(i);
			crystals.removeValue((Crystals) b.getUserData(), true);
			world.destroyBody(b);
			player.collectCrystals();

		}
		bodies.clear();

		Array<Body> bodiesHearts = cl.getBodiesToRemoveHearts();
		for (int i = 0; i < bodiesHearts.size; i++) {
			Body b = bodiesHearts.get(i);
			hearts.removeValue((Rock) b.getUserData(), true);
			world.destroyBody(b);
			if ((cl.numLifes >= 0) && (cl.numLifes < healthBar.size))
				for (int f = 0; f < cl.numLifes + 1; f++) {
					healthBar.get(f).lifeVisible = true;
				}
			if ((cl.numLifes >= 0) && (cl.numLifes < healthBar.size)) {
				cl.numLifes += 1;
				cl.numLifesCurr += 1;
			}

			// if ((cl.numLifes >= 0) && (cl.numLifes < healthBar.size)) {
			//
			// healthBar.get(cl.numLifes).lifeVisible = true;
			// if ((cl.numLifesCurr >= 0) && (cl.numLifesCurr < healthBar.size))
			// {
			// healthBar.get(cl.numLifesCurr).lifeVisible = true;
			// }
			// }
		}
		bodiesHearts.clear();

		Array<Body> bodiesSheilds = cl.getBodiesToRemoveSheilds();
		for (int i = 0; i < bodiesSheilds.size; i++) {
			Body b = bodiesSheilds.get(i);
			shields.removeValue((Rock) b.getUserData(), true);
			world.destroyBody(b);
			to_start_counting_shield = true;
			count_shield = 0;
			player.enable_sheild = true;
		}
		bodiesSheilds.clear();

		if (to_start_counting_shield) {
			count_shield += 1;
			if (count_shield >= 500) {
				to_start_counting_shield = false;
				player.enable_sheild = false;
			}
		}
		Array<Body> enemies = cl.getEnemyToRemove();
		for (int i = 0; i < enemies.size; i++) {
			Body b = enemies.get(i);

			// crystals.removeValue((Crystals) b.getUserData(), true);
			// world.destroyBody(b);
			// player.collectCrystals();
			Filter filter = new Filter();
			filter.maskBits = B2DVars.BIT_BLUE;
			if ((i >= 0) && (i < b.getFixtureList().size))
				b.getFixtureList().get(i).setFilterData(filter);
			b.getLinearVelocity().set(0, b.getLinearVelocity().y);
			if (b.getLinearVelocity().y <= -5) {

				dragonEnemy.removeValue((DragonEnemy) b.getUserData(), true);
				world.destroyBody(b);
				enemies.clear();
			}
		}

		// player.update(dt);
		if (cl.numLifes <= 0) {
			stage.addAction(sequence(fadeOut(0.1f), run(new Runnable() {
				public void run() {
					state = GameState.BEFORE_GAMEOVER;
				}
			})));

		}

		for (int i = 0; i < crystals.size; i++) {
			crystals.get(i).update(dt);
		}

		if (dragonEnemy != null)
			for (int i = 0; i < dragonEnemy.size; i++) {
				dragonEnemy.get(i).update(dt);
			}

		if (cl.isGoToEnd()) {
			state = GameState.BEFORE_LEVELCOMPLETED;
		}

		if ((cl.numLifes >= 0) && (cl.numLifes < healthBar.size)) {
			healthBar.get(cl.numLifes).lifeVisible = false;
			if ((cl.numLifesCurr >= 0) && (cl.numLifesCurr < healthBar.size)) {
				healthBar.get(cl.numLifesCurr).lifeVisible = false;
			}
		}

		if (tileMap.getLayers().get("enemies") != null)
			for (int i = 0; i < dragonEnemy.size; i++) {
				if (dragonEnemy.get(i).getBody().getLinearVelocity().x != 1
						|| dragonEnemy.get(i).getBody().getLinearVelocity().x != -1
						&& dragonEnemy.get(i).getBody().getLinearVelocity().y == 0)
					if (dragonEnemy.get(i).getBody().getLinearVelocity().x >= 0) {
						// dragonEnemy.get(i).getBody().setAngularDamping(100000);
						dragonEnemy
								.get(i)
								.getBody()
								.setLinearVelocity(
										1,
										dragonEnemy.get(i).getBody()
												.getLinearVelocity().y);
					}

				if (dragonEnemy.get(i).getBody().getLinearVelocity().x < 0) {
					// dragonEnemy.get(i).getBody().setAngularDamping(100000);
					dragonEnemy
							.get(i)
							.getBody()
							.setLinearVelocity(
									-1,
									dragonEnemy.get(i).getBody()
											.getLinearVelocity().y);
				}

			}

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render(float dt) {

		// clear screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		for (int i = 0; i < backgrounds.length; i++) {
			backgrounds[i].render(sb);
			backgrounds[i].update(dt);
		}

		hashia += 1;

		stage.act(dt);

		switch (state) {

		case RUNNING:
			update(dt);
			break;
		case PAUSED:
			gamePaused();
			break;
		case LEVEL_COMPLETED:
			level_completed();
			break;
		case BEFORE_PAUSED:
			before_paused();
			break;
		case BEFORE_RUNNING:
			before_running();
			break;
		case BEFORE_LEVELCOMPLETED:
			before_levelcompleted();
			break;
		case BEFORE_GAMEOVER:
			before_gameover();// game over :P
			break;
		case GAMEOVER:
			game_over();
			break;
		}

		cam.setPosition(player.getPosition().x * PPM, player.getPosition().y
				* PPM - 10); // here to minus if you want from the center
		cam.update();
		// cam.update();
		sb.setProjectionMatrix(cam.combined);
		if (layer2 != null) {
			if (bdRopesPoints != null)
				for (int i = 0; i < bdRopesPoints.size; i++) {
					bdRopesPoints.get(i).render(sb);
				}

		}
		
		//float time = 0.01f; // one second
		degrees += 0.07f;
	//	float b2Angle = -1 * Math.(self.rotation);
		

		if (car != null)
			car.render(sb);
		if (car2 != null)
			car2.render(sb);
		if (car3 != null)
			car3.render(sb);

		for (int i = 0; i < hearts.size; i++) {
			hearts.get(i).render(sb);
		}

		for (int i = 0; i < shields.size; i++) {
			shields.get(i).render(sb);
		}

		// draw tile map
		tmr.setView(cam);
		tmr.render();


		if(movingPlatform != null){
			for (int i = 0; i < movingPlatform.size; i++) {
				movingPlatform.get(i).render(sb);
			}
		}
		
		if(rollingSpikes != null){
			for (int i = 0; i < rollingSpikes.size; i++) {
				rollingSpikes.get(i).render(sb);
				// body->GetInertia() * (desiredAngularVelocity - body->getAngularVelocity() ) / time;
				Vector2 position = rollingSpikes.get(i).getBody().getPosition();
				 rollingSpikes.get(i).getBody().setTransform(position, degrees);//.applyTorque(degrees, true);
				
			}
		}
		
		
		
		if(axes != null){
			for (int i = 0; i < axes.size; i++) {
				axes.get(i).render(sb);
				Vector2 position = axes.get(i).getBody().getPosition();
				
				if(angleAxe <= 3 && angleAxe >= 2)
					angleAxe += 0.02;
				else {
					angleAxe -= 0.02;
				}
				axes.get(i).getBody().setTransform(position, angleAxe);
			}
		}
		
		for (int i = 0; i < crystals.size; i++) {
			crystals.get(i).render(sb);
		}

		player.render(sb);
		if (theEnd != null) {
			theEnd.renderr(sb);
		}

		if (dragonEnemy != null)
			for (int i = 0; i < dragonEnemy.size; i++) {
				dragonEnemy.get(i).render(sb);
			}
		for (int i = 0; i < rocks.size; i++) {
			if (level == 1)
				if (Math.abs((double) rocks.get(i).getBody()
						.getLinearVelocity().x) <= 0.8f) {
					rocks.get(i)
							.getBody()
							.setLinearVelocity(
									(rocks.get(i).getBody().getLinearVelocity().x) * 1.1f,
									rocks.get(i).getBody().getLinearVelocity().y);
				}

			if (level == 2) {
				if (i >= 0 && i <= 0) {
					if (rocks.get(i).getBody().getLinearVelocity().x >= 0) {
						if (rocks.get(i).getBody().getLinearVelocity().x != 1.5f)
							rocks.get(i)
									.getBody()
									.setLinearVelocity(
											1.5f,
											rocks.get(i).getBody()
													.getLinearVelocity().y);
					} else {
						if (rocks.get(i).getBody().getLinearVelocity().x != -1.5f)
							rocks.get(i)
									.getBody()
									.setLinearVelocity(
											-1.5f,
											rocks.get(i).getBody()
													.getLinearVelocity().y);
					}
				} else {
					if (Math.abs((double) rocks.get(i).getBody()
							.getLinearVelocity().x) <= 0.8f) {
						rocks.get(i)
								.getBody()
								.setLinearVelocity(
										(rocks.get(i).getBody()
												.getLinearVelocity().x) * 1.1f,
										rocks.get(i).getBody()
												.getLinearVelocity().y);
					}
				}

			}

			if (level == 3) {
				if (i >= 0 && i <= 3) {
					if (rocks.get(i).getBody().getLinearVelocity().x >= 0) {
						if (rocks.get(i).getBody().getLinearVelocity().x != 1.5f)
							rocks.get(i)
									.getBody()
									.setLinearVelocity(
											1.5f,
											rocks.get(i).getBody()
													.getLinearVelocity().y);
					} else {
						if (rocks.get(i).getBody().getLinearVelocity().x != -1.5f)
							rocks.get(i)
									.getBody()
									.setLinearVelocity(
											-1.5f,
											rocks.get(i).getBody()
													.getLinearVelocity().y);
					}
				}
			}

			if (level == 4 || level == 7) {
				if (Math.abs((double) rocks.get(i).getBody()
						.getLinearVelocity().x) <= 0.8f) {
					rocks.get(i)
							.getBody()
							.setLinearVelocity(
									(rocks.get(i).getBody().getLinearVelocity().x) * 1.1f,
									rocks.get(i).getBody().getLinearVelocity().y);
				}
			}

			if (level == 5 && i >= 0 && i >= 5) {
				// Rollinf stone over and over again
				if (rocks.get(i).getBody().getLinearVelocity().x >= 0) {
					if (rocks.get(i).getBody().getLinearVelocity().x != 1.5f)
						rocks.get(i)
								.getBody()
								.setLinearVelocity(
										1.5f,
										rocks.get(i).getBody()
												.getLinearVelocity().y);
				} else {
					if (rocks.get(i).getBody().getLinearVelocity().x != -1.5f)
						rocks.get(i)
								.getBody()
								.setLinearVelocity(
										-1.5f,
										rocks.get(i).getBody()
												.getLinearVelocity().y);
				}
			}

			if (level == 6) {
				if (Math.abs((double) rocks.get(i).getBody()
						.getLinearVelocity().x) <= 0.8f) {
					rocks.get(i)
							.getBody()
							.setLinearVelocity(
									(rocks.get(i).getBody().getLinearVelocity().x) * 1.1f,
									rocks.get(i).getBody().getLinearVelocity().y);
				}
			}
			
			if (level == 8 && i >= 0 && i <= 4 || (level == 11 && i <= 1)) {
				if (Math.abs((double) rocks.get(i).getBody()
						.getLinearVelocity().x) <= 0.8f) {
					rocks.get(i)
							.getBody()
							.setLinearVelocity(
									(rocks.get(i).getBody().getLinearVelocity().x) * 1.1f,
									rocks.get(i).getBody().getLinearVelocity().y);
				}
			} else if(level == 11 && i > 1){
				if (rocks.get(i).getBody().getLinearVelocity().x >= 0) {
					if (rocks.get(i).getBody().getLinearVelocity().x != 1.5f)
						rocks.get(i)
								.getBody()
								.setLinearVelocity(
										1.5f,
										rocks.get(i).getBody()
												.getLinearVelocity().y);
				} else {
					if (rocks.get(i).getBody().getLinearVelocity().x != -1.5f)
						rocks.get(i)
								.getBody()
								.setLinearVelocity(
										-1.5f,
										rocks.get(i).getBody()
												.getLinearVelocity().y);
				}
			}
			
				else if(level == 8 && i >= 5) {
				if (rocks.get(i).getBody().getLinearVelocity().x >= 0) {
					if (rocks.get(i).getBody().getLinearVelocity().x != 1.5f)
						rocks.get(i)
								.getBody()
								.setLinearVelocity(
										1.5f,
										rocks.get(i).getBody()
												.getLinearVelocity().y);
				} else {
					if (rocks.get(i).getBody().getLinearVelocity().x != -1.5f)
						rocks.get(i)
								.getBody()
								.setLinearVelocity(
										-1.5f,
										rocks.get(i).getBody()
												.getLinearVelocity().y);
				}
			} else if(level == 12 && i <= 3){
				if (rocks.get(i).getBody().getLinearVelocity().x >= 0) {
					if (rocks.get(i).getBody().getLinearVelocity().x != 1.5f)
						rocks.get(i)
								.getBody()
								.setLinearVelocity(
										1.5f,
										rocks.get(i).getBody()
												.getLinearVelocity().y);
				} else {
					if (rocks.get(i).getBody().getLinearVelocity().x != -1.5f)
						rocks.get(i)
								.getBody()
								.setLinearVelocity(
										-1.5f,
										rocks.get(i).getBody()
												.getLinearVelocity().y);
				}
			}
			
			
			rocks.get(i).render(sb);

		}

		for (int i = 0; i < garbages.size; i++) {
			garbages.get(i).render(sb);
		}

		// Plyer Fall Down
		if (player.getPosition().y <= 0) {
			stage.addAction(sequence(fadeOut(0.1f), run(new Runnable() {
				public void run() {
					player.getBody().setLinearVelocity(0, 100);
					cl.numLifes = 0;
				}
			})));
		}

		// draw box2d world
		if (debug) {
			b2dCam.setPosition(player.getPosition().x, player.getPosition().y
					- 10 / PPM); // PlatformGame.V_HEIGHT / 4 / PPM // minus
									// here!@
			b2dCam.update();
			b2dr.render(world, b2dCam.combined);
		}

		sb.setProjectionMatrix(hudCam.combined);
		gameFont.setText("" + player.getNumCrystals(),
				"" + player.getTotalCrystals());
		gameFont.render(sb);

		for (int i = 0; i < healthBar.size; i++) {
			healthBar.get(i).draw(sb);
		}

		stage.draw();
	}

	private void before_paused() {

		// Clear the stage
		stage.clear();

		// home button
		stage.addAction(sequence(Actions.fadeIn(0.3f)));

		// Pause Button
		initPause();

		// All in Menu buttons
		initInMenuUI();

		state = GameState.PAUSED;
	}

	private void before_levelcompleted() {

		//game.getActioResolver().showOrLoadInterstital();
		// Clear the stage
		stage.clear();

		// home button
		// stage.addAction(sequence(Actions.fadeIn(0.3f)));

		// Pause Button
		initPause();

		// All in Menu buttons
		initLevelCompletedUI();

		state = GameState.LEVEL_COMPLETED;
	}

	private void before_gameover() {
	//	game.getActioResolver().showOrLoadInterstital();
		// Clear the stage
		stage.clear();

		// home button
		// stage.addAction(sequence(Actions.fadeIn(0.3f)));

		// Pause Button
		// initPause();

		// All game over UI

		// initGameOverUI();
		stage.addActor(gameOverImage);
		stage.addActor(tableLabel);
		stage.addActor(tablego);

		stage.addAction(sequence(Actions.fadeIn(0.4f)));

		state = GameState.LEVEL_COMPLETED;
	}

	private void game_over(){
		moneAD += 1;
		if(moneAD >= 20 && moneAD <= 40){
			game.getActioResolver().showOrLoadInterstital();
		}
		
	}
	
	private void level_completed() {
		moneAD += 1;
		if(moneAD >= 20 && moneAD <= 40){
			game.getActioResolver().showOrLoadInterstital();
		}
	}

	private void before_running() {
		stage.clear();

		stage.addAction(sequence(Actions.fadeIn(0.3f)));

		initJoysticUI();
		initPause();

		state = GameState.RUNNING;

	}

	private void gamePaused() {

	}


	// Done
	@Override
	public void show() {
		super.show();

		//System.out.println("showing boolean");
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
		
		
		Texture ta = game.manager.get("images/ropes.png");
		// rope = new TextureRegion(ta, 1, 1, 127, 90); //
		// rope2 = new TextureRegion(ta, 575, 56, 52, 406);//
		rope3 = new TextureRegion(ta, 629, 56, 52, 406);
		// theend = new TextureRegion(ta, 1, 93, 572, 369);
		// ropeFire = new TextureRegion(ta, 683, 56, 127, 90);
		woodpole = new TextureRegion(ta, 683, 236, 89, 226);

		// Multi For back Pressesd
		InputProcessor backProcessor = new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {

				if ((keycode == Keys.ESCAPE) || (keycode == Keys.BACK)) {
					if (state == GameState.RUNNING)
						state = GameState.BEFORE_PAUSED;
					if (state == GameState.PAUSED)
						state = GameState.BEFORE_RUNNING;
				}

				return false;
			}
		};

		InputMultiplexer multiplexer = new InputMultiplexer(stage,
				backProcessor);
		Gdx.input.setInputProcessor(multiplexer);
		Gdx.input.setCatchBackKey(true);
		Texture bgs = null;
		if (game.manager.isLoaded("images/backgroundbuilding.png")) {
			bgs = game.manager.get("images/backgroundbuilding.png");
		} else {

			game.manager.load("images/backgroundbuilding.png", Texture.class);
			if (game.manager.update())
				bgs = game.manager.get("images/backgroundbuilding.png");
		}

		// new Texture(
		// Gdx.files.internal("images/backgroundbuilding.png"));
		// game.manager.get("images/backgroundbuilding.png");
		if (level == 3) {
			// bgs = game.manager.get("images/backgroundnight.png");
		}

		if (level == 5 || level == 9) {
			// bgs = game.manager.get("images/background3.png");
		}

		if (level == 7 || level == 11) {

		}

		if (bgs == null) {
			bgs = new Texture(
					Gdx.files.internal("images/backgroundbuilding.png"));
		}
		TextureRegion sky = new TextureRegion(bgs, 0, 0, 2220, 440);

		backgrounds = new Background[1];
		backgrounds[0] = new Background(sky, cam, 0.2f);

		// set up box2d stuff
		world = new World(new Vector2(0, -9.81f), true);
		cl = new MyContactListener();
		world.setContactListener(cl);
		b2dr = new Box2DDebugRenderer();

		game.getMusicManager().play(TyrianMusic.LEVEL);

		createAll();

		// set up box2d cam
		b2dCam = new BoundedCamera();
		b2dCam.setToOrtho(false, PlatformGame.V_WIDTH / PPM,
				PlatformGame.V_HEIGHT / PPM);
		b2dCam.setBounds(0, (tileMapWidth * tileSize) / PPM, 0,
				(tileMapHeight * tileSize) / PPM + 9000);

		cam.setBounds(0, tileMapWidth * tileSize, 0, tileMapHeight * tileSize);

		initJoysticUI();

		initHealthBar();

		hashia = 400;

		initGameOverUI();

		state = GameState.RUNNING;
	}

	private void initHealthBar() {
		healthBar = new Array<HealthBar>();
		for (int i = 0; i < 5; i++) {
			HealthBar hc = new HealthBar((i + 1 / PPM) * 27, 210);
			healthBar.add(hc);
		}

	}

	private void createCar() {
		// car
		FixtureDef fdef = new FixtureDef(), wheelFixtureDef = new FixtureDef();
		fdef.density = 5;
		fdef.friction = .4f;
		fdef.restitution = .3f;
		fdef.filter.categoryBits = B2DVars.BIT_CAR;
		fdef.filter.maskBits = B2DVars.BIT_RED | B2DVars.BIT_BALLS
				| B2DVars.BIT_ROPE | B2DVars.BIT_CAR | B2DVars.BIT_PLAYER
				| B2DVars.BIT_ENEMY;

		wheelFixtureDef.density = fdef.density - .5f;
		wheelFixtureDef.friction = 1;
		wheelFixtureDef.restitution = .4f;
		wheelFixtureDef.filter.categoryBits = B2DVars.BIT_PLAYER;
		wheelFixtureDef.filter.maskBits = B2DVars.BIT_RED | B2DVars.BIT_BALLS
				| B2DVars.BIT_ROPE | B2DVars.BIT_CAR | B2DVars.BIT_PLAYER;

		if (level == 1) {
			car = new Car(world, fdef, wheelFixtureDef, 32.5f, 10, 1F, .5f);
			// car2 = new Car(world, fdef, wheelFixtureDef, 32, 13, 1F, .5f);
		}
		if (level == 2) {
			car = new Car(world, fdef, wheelFixtureDef,
					player.getPosition().x + 0.1f, player.getPosition().y - 1,
					1F, .5f);
			car2 = new Car(world, fdef, wheelFixtureDef, 35.5f, 4, 1F, .5f);
		}
		if (level == 3) {
			car = new Car(world, fdef, wheelFixtureDef, 25, 7, 1F, .5f);
			// car2 = new Car(world,fdef,wheelFixtureDef, 35.5f,4,1F,.5f);
		}
		if (level == 6) {
			car = new Car(world, fdef, wheelFixtureDef, 7.5f, 9.5f, 1F, .5f);
			car2 = new Car(world, fdef, wheelFixtureDef, 37.0f, 8.0f, 1F, .5f);
		}
		if (level == 7) {
			car = new Car(world, fdef, wheelFixtureDef, 35.5f, 2f, 1F, .5f);
			car2 = new Car(world, fdef, wheelFixtureDef, 39.0f, 3.0f, 1F, .5f);
			car3 = new Car(world, fdef, wheelFixtureDef, 51.0f, 3.0f, 1F, .5f);

		}

		if (level == 9) {
			car = new Car(world, fdef, wheelFixtureDef, 35.5f, 8.5f, 1F, .5f);
		}
		

		car.bodyRock.spr.setTexture(new Texture(Gdx.files
				.internal("images/cartown.png")));
	}

	private void createAll() {

		// create player
		createPlayer();

		// create tiles
		createTiles();

		createBalls();

		// create Crystals
		createCrystals();

		// create Hearts
		createHearts();

		// create the end
		createTheEnd();

		// create sheilds
		createSheilds();

		// create grabages
		createGrabges();

		// create the main car
		if (level == 1 || level == 3 || level == 6 || level == 7 || level == 9) {
			createCar();
		}

		if (tileMap.getLayers().get("enemies") != null)
			createEnemies();
		// create font
		gameFont = new GameFont();
		player.setTotalCrystals(crystals.size);

		layer2 = tileMap.getLayers().get("ropes");
		if (layer2 != null) {
			bdRopesPoints = null;
			switch (level) {
			case 1:
				createRopeRock(5, 0, 0);
				createRopeRock(5, 2, 1);
				createRopeRock(5, 4, 2);
				break;
			case 2:
				createRopeRock(5, 0, 1);
				createRopeRock(5, 2, 2);
				createRopeRock(5, 4, 3);
				createRopeRock(5, 6, 4);
				createRopeRock(5, 8, 5);
				createRopeRock(5, 10, 6);
				createAxes(12);
				createAxes(13);
				break;
			case 3:
				createRollingPlatfomrs(0);
				createAxes(1);
				createAxes(2);
				break;
			case 4:
				createRopeRock(5, 0, 0);
				createRopeRock(5, 2, 1);
				createRollingPlatfomrs(4);
				break;
			case 5:
				createRopeRock(5, 0, 0);
				createRopeRock(5, 2, 1);
				createRopeRock(5, 4, 2);
				createRopeRock(5, 6, 3);
				createRopeRock(5, 8, 4);
				break;
			case 6:
				createRopeRock(5, 0, 0);
				createRopeRock(5, 2, 1);
				createRopeRock(5, 4, 2);
				createRopeRock(5, 6, 3);
				break;
			case 7:
				createRopeRock(4, 0, 0);
				createRopeRock(4, 2, 1);
				createRopeRock(5, 4, rocks.size - 1);
				break;
			case 8:
				createRopeRock(4, 0, 0);
				createRopeRock(4, 2, 1);
				createRopeRock(5, 4, 2);
				createRopeRock(5, 6, 3);
				createRopeRock(5, 8, 4);
				break;
			case 9:
				createRopeRock(4, 0, rocks.size - 1);
				createMovingPlatforms(2);
				createMovingPlatforms(3);
				break;
			case 10:
				createAxes(14);
				createMovingPlatforms(0);
				createMovingPlatforms(1);
				createMovingPlatforms(2);
				createMovingPlatforms(3);
				createMovingPlatforms(4);
				createMovingPlatforms(5);
				createRollingPlatfomrs(6);
				createRollingPlatfomrs(7);
				createMovingPlatforms(8);
				createMovingPlatforms(9);
				createMovingPlatforms(10);			
				createMovingPlatforms(11);
				createMovingPlatforms(12);
				createMovingPlatforms(13);
				break;
			case 11:
				createRollingPlatfomrs(0);
				createRollingPlatfomrs(1);
				createRollingPlatfomrs(2);
				createRollingPlatfomrs(3);
				createRopeRock(5, 4, 0);
				createRollingPlatfomrs(6);
				createRopeRock(5, 7, 1);
				createMovingPlatforms(9);
				createMovingPlatforms(10);
				createMovingPlatforms(11);
				createMovingPlatforms(12);
				createMovingPlatforms(13);
				createMovingPlatforms(14);
				createMovingPlatforms(15);
				createMovingPlatforms(16);
				createMovingPlatforms(17);
				break;
			case 12:
				createRollingPlatfomrs(0);
				createRollingPlatfomrs(1);
				createMovingPlatforms(2);
				createMovingPlatforms(3);
				createAxes(4);
				createMovingPlatforms(5);
				createMovingPlatforms(6);
				createMovingPlatforms(7);
				createMovingPlatforms(8);
				createMovingPlatforms(9);
				createAxes(10);
				createRollingPlatfomrs(11);
				createAxes(12);
				break;

			}

			// createRope2(28);

		}

	}

	protected void initVars() {
		cl = new MyContactListener();
		world.setContactListener(cl);
		crystals.clear();
		hearts.clear();
		rocks.clear();
		if(movingPlatform != null){
			movingPlatform.clear();
			movingPlatform = null;
		}
		if(rollingSpikes != null){
			rollingSpikes.clear();
			rollingSpikes = null;	
		}
		if(axes != null){
			axes.clear();
			axes = null;	
		}
		garbages.clear();
		hashia = 400;
		world.getBodies(worldBodies);
		for (int i = 0; i < worldBodies.size; i++) {
			Body b = worldBodies.get(i);

			// crystals.removeValue((Crystals) b.getUserData(), true);
			// rocks.removeValue((Rock) b.getUserData(), true);
			world.destroyBody(b);
			player.collectCrystals();

		}
		worldBodies.clear();
		cl.setisGoToEnd(false);
		createAll();

		for (int i = 0; i < healthBar.size; i++) {
			healthBar.get(i).lifeVisible = true;
		}

		cl.numLifes = 5;
		cl.numLifesCurr = 5;
	}

	private void initGameOverUI() {

		Texture tex = game.manager.get("images/gameover.png");
		Drawable gameoverDrawable = new TextureRegionDrawable(
				new TextureRegion(tex));

		gameOverImage = new Image(gameoverDrawable, Scaling.stretch);
		gameOverImage.setFillParent(true);
		// hhd
		// stage.addActor(gameOverImage);
		int besthighscore = 0;
		switch (level) {
		case 1:
			besthighscore = game.getPreferencesManager().getWORLDOneHighScore();
			break;
		case 2:
			besthighscore = game.getPreferencesManager().getWORLDTwoHighScore();
			break;
		case 3:
			besthighscore = game.getPreferencesManager()
					.getWORLDThreeHighScore();
			break;
		case 4:
			besthighscore = game.getPreferencesManager()
					.getWORLDFourHighScore();
			break;
		case 5:
			besthighscore = game.getPreferencesManager()
					.getWORLDFiveHighScore();
			break;
		case 6:
			besthighscore = game.getPreferencesManager().getWORLDSixHighScore();
			break;
		case 7:
			besthighscore = game.getPreferencesManager()
					.getWORLDSevenHighScore();
			break;
		case 8:
			besthighscore = game.getPreferencesManager()
					.getWORLDEightHighScore();
			break;
		case 9:
			besthighscore = game.getPreferencesManager()
					.getWORLDNineHighScore();
		case 10:
			besthighscore = game.getPreferencesManager().getWORLDTenHighScore();
			break;
		case 11:
			besthighscore = game.getPreferencesManager()
					.getWORLDElevenHighScore();
			break;
		case 12:
			besthighscore = game.getPreferencesManager()
					.getWORLDTwelveHighScore();
			break;
		}
		BitmapFont font = new BitmapFont(Gdx.files.internal("bigfont.fnt"));
		LabelStyle ls = new LabelStyle(font, Color.GREEN);
		curScore = new Label("Score: " + player.getNumCrystals() + "", ls);
		// getSkin(), "big-font");
		Label lifes = new Label("Lives: " + 0 + "", ls);
		// getSkin(), "big-font");
		lifes.setFontScale(0.6f);

		highScore = new Label("Highscore: " + besthighscore, ls);
		// getSkin(),
		// "medium-font");
		curScore.setFontScale(0.6f);
		highScore.setFontScale(0.6f);
		// retrieve the default table actor

		tableLabel = new Table();// super.getTable();
		tableLabel.setFillParent(true);
		if (curScore != null)
			tableLabel.removeActor(curScore);
		tableLabel.defaults().padBottom(10);
		tableLabel.padRight(100);
		tableLabel.add(curScore);
		tableLabel.center();
		// stage.addActor(tableLabel);
		tableLabel.row();
		// Table tableLabel2 = super.getTable();
		if (highScore != null)
			tableLabel.removeActor(highScore);
		tableLabel.padTop(10);
		tableLabel.padRight(1);
		tableLabel.add(highScore);
		tableLabel.left().center();
		// stage.addActor(tableLabel);
		tableLabel.row();
		tableLabel.defaults().padBottom(10);
		tableLabel.padLeft(30);
		tableLabel.add(lifes);
		tableLabel.center();
		// hhd
		// stage.addActor(tableLabel);

		tableLabel.row();

		tablego = new Table(skin4);

		// Home Button For Game Menu
		if (atlashouse == null) {
			atlashouse = game.manager.get("ui/homebutton.pack");
		}

		if (skin4 == null)
			skin4 = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlashouse);

		buttonHome = new Button(skin4);
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

			}
		});

		// Restart Button For Game Menu
		if (atlasRestart == null)
			atlasRestart = game.manager.get("ui/restartbutton.pack");

		if (skin5 == null)
			skin5 = new Skin(Gdx.files.internal("ui/menuSkin.json"),
					atlasRestart);

		// table = new Table(skin);
		buttonRestart = new Button(skin5);
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

			}
		});

		tablego.add(buttonHome);// .left();
		tablego.getCell(buttonHome).size(40, 40);
		// stage.addActor(table);
		tablego.align(Align.left);
		tablego.bottom().padBottom(30).padLeft(150);

		tablego.add(buttonRestart).padLeft(30);// .left();
		tablego.getCell(buttonRestart).size(40, 40);

		tablego.align(Align.left);
		tablego.bottom().padBottom(30).padLeft(115);
		// hh
		// stage.addActor(table);

		// stage.addAction(sequence(Actions.fadeIn(0.4f)));// /, run(new
		// Runnable()
		// hh // {
		// public void run() {
		// state = GameState.BEFORE_GAMEOVER;
		// }
		// })));
	}

	private void initJoysticUI() {

		stage.addAction(sequence(Actions.fadeOut(0.001f), Actions.fadeIn(0.5f)));

		// Atlasses for joystic
		atlasleft = game.manager.get("ui/buttonleft2.pack");
		atlasright = game.manager.get("ui/buttonright.pack");
		atlasjump = game.manager.get("ui/jumpbutton.pack");

		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlasleft);

		Table table = new Table(skin);
		// table2 = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		// table2.setBounds(0, 0, Gdx.graphics.getWidth(),
		// Gdx.graphics.getHeight());

		buttonLeft = new Button(skin);
		buttonLeft.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				player.getBody().setLinearVelocity(-2f,
						player.getBody().getLinearVelocity().y);
				if_touch_left_side = true;
				if_touch_right_side = false;
				if (hashia >= 130)
					cl.isJoysticAvailable = true;
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if_touch_left_side = false;
				player.getBody().setLinearVelocity(0f,
						player.getBody().getLinearVelocity().y);

			}
		});

		// buttonExit.setX(3400);
		// buttonLeft.pad(10);

		table.add(buttonLeft).left();
		table.getCell(buttonLeft)
				.size(buttonLeft.getWidth() * 0.5f,
						buttonLeft.getHeight() * 0.5f).padLeft(10);
		stage.addActor(table);
		table.align(Align.left);
		table.bottom();
		// table.setPosition(table.getX() + 20, table.getY() + 40);

		skin1 = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlasright);

		// table = new Table(skin);

		buttonRight = new Button(skin1);
		buttonRight.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				player.getBody().setLinearVelocity(2f,
						player.getBody().getLinearVelocity().y);
				if_touch_right_side = true;
				if_touch_left_side = false;
				if (hashia >= 130)
					cl.isJoysticAvailable = true;

				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				player.getBody().setLinearVelocity(0f,
						player.getBody().getLinearVelocity().y);
				if_touch_right_side = false;
				if (hashia >= 130)
					cl.isJoysticAvailable = true;
			}
		});

		table.add(buttonRight).left();
		table.getCell(buttonRight)
				.size(buttonRight.getWidth() * 0.5f,
						buttonRight.getHeight() * 0.5f).uniform();
		stage.addActor(table);
		table.align(Align.left);
		table.bottom();

		skin2 = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlasjump);

		// table = new Table(skin);

		buttonJump = new Button(skin2);
		buttonJump.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (cl.isPlayerOnDGround()) {
					player.getBody().applyForceToCenter(0,/* 450 */270, true);
					cl.isOnGround = false;
				}

				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (hashia >= 130)
					cl.isJoysticAvailable = true;
			}
		});

		table.add(buttonJump).right();
		table.getCell(buttonJump)
				.size(buttonJump.getWidth() * 0.43f,
						buttonJump.getWidth() * 0.43f).padLeft(120)
				.padBottom(buttonJump.getHeight() * 0.33f);
		stage.addActor(table);
		table.align(Align.left);
		table.bottom();

		initPause();
	}

	private void initPause() {

		atlaspause = game.manager.get("ui/pausebutton.pack");

		skin3 = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlaspause);

		Table table = new Table(skin3);

		buttonPause = new Button(skin3);
		buttonPause.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				stage.addAction(sequence(fadeOut(0.1f), run(new Runnable() {
					public void run() {
						if (state == GameState.RUNNING)
							state = GameState.BEFORE_PAUSED;
						else {
							state = GameState.BEFORE_RUNNING;
						}
					}
				})));

			}
		});

		table.add(buttonPause).left();
		table.getCell(buttonPause)
				.size(buttonPause.getWidth() * 0.5f,
						buttonPause.getHeight() * 0.5f).uniform().size(25, 25);
		stage.addActor(table);
		table.align(Align.left);
		table.bottom().padBottom(210).padLeft(290);

	}

	private void initInMenuUI() {

		// Home Button For Game Menu
		if (atlashouse == null) {
			atlashouse = game.manager.get("ui/homebutton.pack");
		}

		if (skin4 == null)
			skin4 = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlashouse);

		Table table = new Table(skin4);

		buttonHome = new Button(skin4);
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

			}
		});

		// Restart Button For Game Menu
		if (atlasRestart == null)
			atlasRestart = game.manager.get("ui/restartbutton.pack");

		if (skin5 == null)
			skin5 = new Skin(Gdx.files.internal("ui/menuSkin.json"),
					atlasRestart);

		// table = new Table(skin);
		buttonRestart = new Button(skin5);
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

			}
		});

		atlasresume = game.manager.get("ui/resumebutton.pack");

		skin6 = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlasresume);

		// table = new Table(skin);

		buttonResume = new Button(skin6);
		buttonResume.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				stage.addAction(sequence(fadeOut(0.3f), run(new Runnable() {
					public void run() {
						state = GameState.BEFORE_RUNNING;
					}
				})));

			}
		});

		buttonResume.padBottom(20);

		buttonResume.padBottom(20);

		table.add(buttonResume);// .left();
		table.getCell(buttonResume).size(40, 40);
		stage.addActor(table);
		table.align(Align.left);
		table.bottom().padBottom(50).padLeft(275);

		table.row();

		table.add(buttonRestart);// .left();
		table.getCell(buttonRestart).size(40, 40);
		stage.addActor(table);
		table.align(Align.left);
		table.bottom().padBottom(50).padLeft(275);

		table.row();

		table.add(buttonHome);// .left();
		table.getCell(buttonHome).size(40, 40);
		// stage.addActor(table);
		table.align(Align.left);
		table.bottom().padBottom(10).padLeft(280);

	}

	protected void initLevelCompletedUI() {

	}

	private void createPlayer() {

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
	//	PolygonShape shape = new PolygonShape();
		CircleShape cs = new CircleShape();
		cs.setRadius(14.5f / PPM);
		// create player
		if (level == 3 || level == 4) {
			bdef.position.set(60 / PPM, 700 / PPM);
		} else if (level == 5 || level == 11) {
			bdef.position.set(60 / PPM, 700 / PPM);
		} 
		else {
			bdef.position.set(90 / PPM, 700 / PPM);
		}

		bdef.type = BodyType.DynamicBody;
		// bdef.linearVelocity.set(1f, 0f);
		Body body = world.createBody(bdef);
		//shape.setAsBox(13 / PPM, 13 / PPM);
		// shape.setAsBox(100 / PPM, 100 / PPM);
		fdef.shape = cs;
		fdef.density = 7;
		fdef.friction = 0.2f;
		fdef.restitution = 0.001f;
		// fdef.density = 7;
		// fdef.friction = 0.1f;
		// fdef.restitution = 0.001f;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_RED | B2DVars.BIT_BALLS
				| B2DVars.BIT_ENEMY | B2DVars.BIT_ROPE | B2DVars.BIT_CAR
				| B2DVars.BIT_PLAYER | B2DVars.BIT_THRONES;
		body.createFixture(fdef).setUserData("player");

		// create foot sensor
		cs.setRadius(16 / PPM);
		fdef.shape = cs;
		// fdef.friction = 0.1f;
		// fdef.density = 1.1f;
		fdef.restitution = 0;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_RED | B2DVars.BIT_BALLS
				| B2DVars.BIT_CRYSTAL | B2DVars.BIT_ENEMY | B2DVars.BIT_ROPE
				| B2DVars.BIT_CAR | B2DVars.BIT_THRONES | B2DVars.BIT_PLAYER;
		fdef.isSensor = true;
		body.setLinearVelocity(0, 0);
		body.createFixture(fdef).setUserData("foot");

		// create player
		player = new Player(body);

		body.setUserData(player);
	}

	protected void loadMap(TiledMap tileMap) {

		// if (tileMap == null)
		// tileMap = new TmxMapLoader().load("maps/level1.tmx");
		// else //{
		this.tileMap = tileMap;
		// }

	}

	private void createTiles() {

		// load tile map
		if (tileMap == null) {
			game.setScreen(game.getLoadingScreen());
			return;
		}
		MapProperties prop = tileMap.getProperties();
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		tileSize = prop.get("tilewidth", Integer.class);
		tileMapWidth = prop.get("width", Integer.class);
		tileMapHeight = prop.get("height", Integer.class);

		TiledMapTileLayer layer;
		layer = (TiledMapTileLayer) tileMap.getLayers().get("red");
		createLayer(layer, B2DVars.BIT_RED);

		if ((TiledMapTileLayer) tileMap.getLayers().get("slope") != null) {
			layer = (TiledMapTileLayer) tileMap.getLayers().get("slope");
			createSlopeLayer(layer, B2DVars.BIT_RED);
		}

		if ((TiledMapTileLayer) tileMap.getLayers().get("slopedown") != null) {
			layer = (TiledMapTileLayer) tileMap.getLayers().get("slopedown");
			createSlopeDownLayer(layer, B2DVars.BIT_RED);
		}

		if ((TiledMapTileLayer) tileMap.getLayers().get("thorns") != null) {
			layer = (TiledMapTileLayer) tileMap.getLayers().get("thorns");
			createThronesLayer(layer, B2DVars.BIT_THRONES);
		}

		//
		// layer = (TiledMapTileLayer) tileMap.getLayers().get("blue");
		// createLayer(layer, B2DVars.BIT_BLUE);

	}

	private void createLayer(TiledMapTileLayer layer, short bits) {

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		// go through all the cells in the layer
		for (int row = 0; row < layer.getHeight(); row++) {
			for (int col = 0; col < layer.getWidth(); col++) {

				// get cell
				Cell cell = layer.getCell(col, row);

				// check if cell exists
				if (cell == null)
					continue;
				if (cell.getTile() == null)
					continue;

				// create a body + fixture from cell
				bdef.type = BodyType.StaticBody;
				bdef.position.set((col + 0.5f) * tileSize / PPM, (row + 0.5f)
						* tileSize / PPM);

				ChainShape cs = new ChainShape();
				Vector2[] v = new Vector2[5];
				v[3] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[0] = new Vector2(-tileSize / 2 / PPM, tileSize / 2 / PPM);
				v[1] = new Vector2(tileSize / 2 / PPM, tileSize / 2 / PPM);
				v[2] = new Vector2(tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[4] = new Vector2(-tileSize / 2 / PPM, tileSize / 2 / PPM);
				cs.createChain(v);
				fdef.friction = 1f;
				fdef.shape = cs;
				fdef.restitution = 0.0f;
				fdef.density = 1f;
				fdef.filter.categoryBits = bits;
				fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_BALLS
						| B2DVars.BIT_ROPE | B2DVars.BIT_CAR;
				fdef.isSensor = false;
				world.createBody(bdef).createFixture(fdef);
			}

		}
	}

	private void createThronesLayer(TiledMapTileLayer layer, short bits) {

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		// go through all the cells in the layer
		for (int row = 0; row < layer.getHeight(); row++) {
			for (int col = 0; col < layer.getWidth(); col++) {

				// get cell
				Cell cell = layer.getCell(col, row);

				// check if cell exists
				if (cell == null)
					continue;
				if (cell.getTile() == null)
					continue;

				// create a body + fixture from cell
				bdef.type = BodyType.StaticBody;
				bdef.position.set((col + 0.5f) * tileSize / PPM, (row + 0.5f)
						* tileSize / PPM);

				ChainShape cs = new ChainShape();
				Vector2[] v = new Vector2[5];
				v[3] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[0] = new Vector2(-tileSize / 2 / PPM, tileSize / 2 / PPM);
				v[1] = new Vector2(tileSize / 2 / PPM, tileSize / 2 / PPM);
				v[2] = new Vector2(tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[4] = new Vector2(-tileSize / 2 / PPM, tileSize / 2 / PPM);
				cs.createChain(v);
				fdef.friction = 1f;
				fdef.shape = cs;
				fdef.restitution = 0.0f;
				fdef.density = 1f;
				fdef.filter.categoryBits = bits;
				fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_BALLS
						| B2DVars.BIT_ROPE | B2DVars.BIT_CAR
						| B2DVars.BIT_THRONES;
				fdef.isSensor = false;
				world.createBody(bdef).createFixture(fdef)
						.setUserData("ropefire");

			}

		}
	}

	private void createSlopeLayer(TiledMapTileLayer layer, short bits) {

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		// go through all the cells in the layer
		for (int row = 0; row < layer.getHeight(); row++) {
			for (int col = 0; col < layer.getWidth(); col++) {

				// get cell
				Cell cell = layer.getCell(col, row);

				// check if cell exists
				if (cell == null)
					continue;
				if (cell.getTile() == null)
					continue;

				// create a body + fixture from cell
				bdef.type = BodyType.StaticBody;
				bdef.position.set((col + 0.5f) * tileSize / PPM, (row + 0.5f)
						* tileSize / PPM);

				ChainShape cs = new ChainShape();
				Vector2[] v = new Vector2[4];
				v[3] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[0] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[1] = new Vector2(tileSize / 2 / PPM, tileSize / 2 / PPM);
				v[2] = new Vector2(tileSize / 2 / PPM, -tileSize / 2 / PPM);

				cs.createChain(v);
				fdef.friction = 1f;
				fdef.shape = cs;
				fdef.restitution = 0.0f;
				fdef.density = 156f;
				fdef.filter.categoryBits = bits;
				fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_BALLS;
				fdef.isSensor = false;
				world.createBody(bdef).createFixture(fdef);

			}
		}
	}

	private void createSlopeDownLayer(TiledMapTileLayer layer, short bits) {

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		// go through all the cells in the layer
		for (int row = 0; row < layer.getHeight(); row++) {
			for (int col = 0; col < layer.getWidth(); col++) {

				// get cell
				Cell cell = layer.getCell(col, row);

				// check if cell exists
				if (cell == null)
					continue;
				if (cell.getTile() == null)
					continue;

				// create a body + fixture from cell
				bdef.type = BodyType.StaticBody;
				bdef.position.set((col + 0.5f) * tileSize / PPM, (row + 0.5f)
						* tileSize / PPM);

				ChainShape cs = new ChainShape();
				Vector2[] v = new Vector2[4];
				v[3] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[0] = new Vector2(+tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[1] = new Vector2(-tileSize / 2 / PPM, tileSize / 2 / PPM);
				v[2] = new Vector2(tileSize / 2 / PPM, -tileSize / 2 / PPM);

				cs.createChain(v);
				fdef.friction = 1f;
				fdef.shape = cs;
				fdef.restitution = 0.0f;
				fdef.density = 1f;
				fdef.filter.categoryBits = bits;
				fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_BALLS;
				fdef.isSensor = false;
				world.createBody(bdef).createFixture(fdef);

			}
		}
	}

	private void createTheEnd() {
		MapLayer layer = tileMap.getLayers().get("theend");

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		for (MapObject mo : layer.getObjects()) {

			MapProperties props = mo.getProperties();

			bdef.type = BodyType.DynamicBody;

			float x = props.get("x", float.class) / PPM;
			float y = props.get("y", float.class) / PPM;
			bdef.type = BodyType.StaticBody;
			bdef.position.set(x, y);

			PolygonShape pss = new PolygonShape();
			pss.setAsBox(44 / PPM, 74 / PPM);

			fdef.shape = pss;
			fdef.isSensor = false;

			fdef.filter.categoryBits = B2DVars.BIT_BALLS;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_RED
					| B2DVars.BIT_CAR;

			Body body = world.createBody(bdef);

			body.createFixture(fdef).setUserData("theend");
			body.setLinearVelocity(0, 0f);
			theEnd = new TheEnd(body);

			body.setUserData(theEnd);

			pss.dispose();
		}
	}

	private void createBalls() {
		/*
		 * All the rocks int the game, spikes rock, slashes rock and regular
		 * rock and etc.
		 */
		rocks = new Array<Rock>();
		if (tileMap == null) {
			game.setScreen(game.getLoadingScreen());
			return;
		}
		MapLayer layer = tileMap.getLayers().get("balls");

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		for (MapObject mo : layer.getObjects()) {

			MapProperties props = mo.getProperties();

			bdef.type = BodyType.DynamicBody;

			float x = props.get("x", float.class) / PPM;
			float y = props.get("y", float.class) / PPM;

			bdef.position.set(x, y);

			CircleShape cshape = new CircleShape();
			cshape.setRadius(25 / PPM);

			fdef.shape = cshape;
			fdef.isSensor = false;
			fdef.density = 12.1f;
			fdef.friction = 0.1f;
			fdef.restitution = 0.1f;
			fdef.filter.categoryBits = B2DVars.BIT_BALLS;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_RED
					| B2DVars.BIT_CAR | B2DVars.BIT_CRYSTAL | B2DVars.BIT_ROPE;

			if (level == 1 && rocks.size >= 1) {
				fdef.density = 15.1f;
				Body body = world.createBody(bdef);
				body.createFixture(fdef).setUserData("ropefire");
				body.setLinearVelocity(0, 0f);
				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/spikesrock.png");
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 2 && rocks.size >= 0 && rocks.size <= 0) {
				fdef.density = 15.1f;
				fdef.restitution = 0.7f;
				Body body = world.createBody(bdef);
				body.setLinearVelocity(0, 100);
				body.createFixture(fdef).setUserData("ropefire");
				body.setLinearVelocity(0, 0f);

				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/slashesrock.png");
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 2 && rocks.size >= 1) {
				fdef.density = 15.1f;
				Body body = world.createBody(bdef);
				body.createFixture(fdef).setUserData("ropefire");
				body.setLinearVelocity(0, 0f);
				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/spikesrock.png");
				
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 3 && rocks.size >= 0 && rocks.size <= 3) {
				fdef.density = 15.1f;
				fdef.restitution = 0.7f;
				Body body = world.createBody(bdef);
				body.setLinearVelocity(0, 100);
				body.createFixture(fdef).setUserData("ropefire");
				body.setLinearVelocity(0, 0f);

				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/slashesrock.png");
				
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 4 && rocks.size <= 1) {
				fdef.density = 15.1f;
				Body body = world.createBody(bdef);
				body.createFixture(fdef).setUserData("ropefire");
				body.setLinearVelocity(0, 0f);
				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/spikesrock.png");
				
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 5 && rocks.size >= 0 && rocks.size >= 5) {
				fdef.density = 15.1f;
				fdef.restitution = 0.7f;
				Body body = world.createBody(bdef);
				body.setLinearVelocity(0, 100);
				body.createFixture(fdef).setUserData("ropefire");
				body.setLinearVelocity(0, 0f);

				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/slashesrock.png");
				
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 6 && rocks.size >= 0) {
				fdef.density = 15.1f;
				Body body = world.createBody(bdef);
				body.createFixture(fdef).setUserData("ropefire");
				body.setLinearVelocity(0, 0f);
				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/spikesrock.png");
				
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 7 && rocks.size > 6) {
				fdef.density = 15.1f;
				Body body = world.createBody(bdef);
				body.createFixture(fdef).setUserData("ropefire");
				body.setLinearVelocity(0, 0f);
				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/spikesrock.png");
			
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 8 && rocks.size >= 2 && rocks.size <= 4) {
				fdef.density = 15.1f;
				Body body = world.createBody(bdef);
				body.createFixture(fdef).setUserData("ropefire");
				body.setLinearVelocity(0, 0f);
				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/spikesrock.png");
				
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 8 && rocks.size >= 5) {
				fdef.density = 15.1f;
				fdef.restitution = 0.7f;
				Body body = world.createBody(bdef);
				body.setLinearVelocity(0, 100);
				body.createFixture(fdef).setUserData("ropefire");
				body.setLinearVelocity(0, 0f);

				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/slashesrock.png");
				
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 9 && rocks.size >= 5) {
				fdef.density = 15.1f;
				fdef.restitution = 0.7f;
				Body body = world.createBody(bdef);
				body.setLinearVelocity(0, 100);
				body.createFixture(fdef).setUserData("ropefire");
				body.setLinearVelocity(0, 0f);

				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/spikesrock.png");
				
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 11) {
				fdef.density = 15.1f;
				fdef.restitution = 0.7f;
				Body body = world.createBody(bdef);
				body.setLinearVelocity(0, 100);
				body.createFixture(fdef).setUserData("ropefire");
				body.setLinearVelocity(0, 0f);

				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/spikesrock.png");
				
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 12 && rocks.size <= 3) {
				fdef.density = 15.1f;
				fdef.restitution = 0.7f;
				Body body = world.createBody(bdef);
				body.setLinearVelocity(0, 100);
				body.createFixture(fdef).setUserData("ropefire");
				body.setLinearVelocity(0, 0f);

				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/slashesrock.png");
				
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			}
			else {
				fdef.density = 12.1f;
				Body body = world.createBody(bdef);
				body.createFixture(fdef).setUserData("rock");
				body.setLinearVelocity(0, 0f);

				Rock c = new Rock(body, null, true);
				c.spr.setScale(0.7f);
				rocks.add(c);
				body.setUserData(c);
			}

			cshape.dispose();
		}
	}

	private void createGrabges() {
		garbages = new Array<Rock>();

		MapLayer layer = tileMap.getLayers().get("garbage");

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		for (MapObject mo : layer.getObjects()) {

			MapProperties props = mo.getProperties();

			bdef.type = BodyType.DynamicBody;

			float x = props.get("x", float.class) / PPM;
			float y = props.get("y", float.class) / PPM;

			bdef.position.set(x, y);

			PolygonShape cshape = new PolygonShape();
			cshape.setAsBox(15 / PPM, 20 / PPM);

			fdef.shape = cshape;
			fdef.isSensor = false;
			fdef.density = 70.1f;
			fdef.friction = 0.1f;
			fdef.restitution = 0.1f;
			fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_RED
					| B2DVars.BIT_CAR | B2DVars.BIT_CRYSTAL | B2DVars.BIT_ENEMY
					| B2DVars.BIT_BALLS;
			fdef.restitution = 0;
			fdef.friction = 0.5f;
			fdef.density = 12.1f;
			Body body = world.createBody(bdef);
			body.createFixture(fdef).setUserData("grabage");
			body.setLinearVelocity(0, 0f);

			Rock c = new Rock(body, null, false);
			c.spr.setScale(0.7f);
			Texture texture = game.manager.get("images/garbage.png");
		
			c.spr.setTexture(texture);
			garbages.add(c);
			body.setUserData(c);

			cshape.dispose();
		}
	}

	private void createEnemies() {

		dragonEnemy = new Array<DragonEnemy>();

		MapLayer layer = tileMap.getLayers().get("enemies");

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		int i = 0;
		for (MapObject mo : layer.getObjects()) {
			i++;
			MapProperties props = mo.getProperties();

			bdef.type = BodyType.DynamicBody;

			float x = props.get("x", float.class) / PPM;
			float y = props.get("y", float.class) / PPM;

			bdef.position.set(x, y);

			PolygonShape pshape = new PolygonShape();
			pshape.setAsBox(25 / PPM, 29 / PPM);

			fdef.shape = pshape;
			fdef.isSensor = false;
			fdef.density = 10;
			fdef.restitution = 0;
			fdef.friction = 0.0f;
			fdef.filter.categoryBits = B2DVars.BIT_BALLS;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_RED
					| B2DVars.BIT_CRYSTAL
					| B2DVars.BIT_ROPE | B2DVars.BIT_CAR;

			ebody = world.createBody(bdef);
			ebody.createFixture(fdef).setUserData("enemy");
			// ebody.setFixedRotation(true);
			ebody.setLinearVelocity(10, 0);
			// ebody.setAngularDamping(100000);
			ebody.setFixedRotation(true);
			DragonEnemy c;
			if (level == 3) {
				if (i <= 3) {
					Texture tex = game.manager.get("animations/man.png");
					
					c = new DragonEnemy(ebody, tex);
				} else {
					Texture tex = game.manager.get("animations/rockman.png");
					
					c = new DragonEnemy(ebody, tex);

				}
				dragonEnemy.add(c);

			} else if (level == 4) {
				if (i <= 2) {
					Texture tex = game.manager.get("animations/man.png");
					c = new DragonEnemy(ebody, tex);
				} else if (i >= 2 && i < 4) {
					Texture tex = PlatformGame.manager
							.get("animations/pig.png");
					c = new DragonEnemy(ebody, tex);
				} else {
					Texture tex = PlatformGame.manager
							.get("animations/cat.png");
					c = new DragonEnemy(ebody, tex);
				}

				dragonEnemy.add(c);
			} else if (level == 6) {
				if (i <= 2) {
					Texture tex = game.manager.get("animations/man.png");
					c = new DragonEnemy(ebody, tex);
				} else if (i >= 2 && i < 4) {
					Texture tex = PlatformGame.manager
							.get("animations/pig.png");
					c = new DragonEnemy(ebody, tex);
				} else {
					Texture tex = PlatformGame.manager
							.get("animations/cat.png");
					c = new DragonEnemy(ebody, tex);
				}

				dragonEnemy.add(c);
			}

			else if (level == 7) {
				if (i <= 1) {
					Texture tex = game.manager.get("animations/man.png");
					c = new DragonEnemy(ebody, tex);
				} else if (i >= 2 && i < 3) {
					Texture tex = PlatformGame.manager
							.get("animations/pig.png");
					c = new DragonEnemy(ebody, tex);
				} else {
					Texture tex = PlatformGame.manager
							.get("animations/cat.png");
					c = new DragonEnemy(ebody, tex);
				}

				dragonEnemy.add(c);
			} else if (level == 8) {
				if (i <= 1) {
					Texture tex = game.manager.get("animations/pig.png");
					c = new DragonEnemy(ebody, tex);
				} else if (i >= 2 && i < 3) {
					Texture tex = PlatformGame.manager
							.get("animations/pig.png");
					c = new DragonEnemy(ebody, tex);
				} else {
					Texture tex = PlatformGame.manager
							.get("animations/cat.png");
					c = new DragonEnemy(ebody, tex);
				}

				dragonEnemy.add(c);
			}
			
			else {
				if (i <= 2) {
					Texture tex = PlatformGame.manager
							.get("animations/cat.png");
					c = new DragonEnemy(ebody, tex);
				} else {
					Texture tex = PlatformGame.manager
							.get("animations/pig.png");
					c = new DragonEnemy(ebody, tex);

				}
				dragonEnemy.add(c);

			}

			/*
			 * create the head sensor, When the player jump on this the enemy
			 * fell off
			 */
			pshape.setAsBox(29 / PPM, 7 / PPM, new Vector2(0, 29 / PPM), 0);

			bdef.position.set(bdef.position.x, bdef.position.y + 20 / PPM);
			fdef.shape = pshape;
			// fdef.friction = 0.1f;
			// fdef.density = 1.1f;
			fdef.restitution = 0;
			fdef.filter.categoryBits = B2DVars.BIT_ENEMY;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_RED;
			fdef.isSensor = true;
			ebody.createFixture(fdef).setUserData("head");

			// create foot sensor
			pshape.setAsBox(28 / PPM, 27 / PPM, new Vector2(0, -1 / PPM), 0);

			bdef.position.set(bdef.position.x, bdef.position.y + 10 / PPM);
			fdef.shape = pshape;
			// fdef.friction = 0.1f;
			// fdef.density = 1.1f;
			fdef.restitution = 0;
			fdef.filter.categoryBits = B2DVars.BIT_ENEMY;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_RED;
			fdef.isSensor = true;
			ebody.createFixture(fdef).setUserData("body");

			ebody.setUserData(c);

			pshape.dispose();
		}

	}

	@Override
	public void dispose() {
		super.dispose();
		// game.manager.unload("");
	}

	private void createPointsRope() {

		// Texture tex = game.manager.get("images/woodpole.png");

		bdRopesPoints = new Array<Rock>();

		MapLayer layer = tileMap.getLayers().get("ropes");

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		for (MapObject mo : layer.getObjects()) {

			MapProperties props = mo.getProperties();

			bdef.type = BodyType.StaticBody;

			float x = props.get("x", float.class) / PPM;
			float y = props.get("y", float.class) / PPM;

			bdef.position.set(x, y);

			PolygonShape pshape = new PolygonShape();
			pshape.setAsBox(2 / PPM, 2 / PPM);

			fdef.shape = pshape;
			fdef.isSensor = false;
			fdef.density = 10;
			fdef.restitution = 0;
			fdef.friction = 0.0f;
			fdef.filter.categoryBits = B2DVars.BIT_BLUE;

			ebody = world.createBody(bdef);
			ebody.createFixture(fdef).setUserData("enemy");
			// ebody.setFixedRotation(true);
			ebody.setLinearVelocity(10, 0);
			// ebody.setAngularDamping(100000);
			ebody.setFixedRotation(true);
			Rock c;
			if(level != 10 && level != 9 && level != 11 && level != 12){
				c = new Rock(ebody, woodpole, true);
				c.spr.setScale(0.3f);
			}
				
			else{
				c = new Rock(ebody, null, false);
				Texture text = PlatformGame.manager
						.get("images/swingwood.png");
				c.spr.setTexture(text);
				c.spr.setScale(0.25f, 1.99f);
				if(bdRopesPoints.size == 6 && level != 12){
					c.spr.setScale(0.01f);
				}
			}
			// c.spr.setTexture(tex);
			
			// c.spr.setTexture(tex);
			bdRopesPoints.add(c);

			// create foot sensor
			pshape.setAsBox(2 / PPM, 3 / PPM, new Vector2(0, 2 / PPM), 0);

			bdef.position.set(bdef.position.x, bdef.position.y + 10 / PPM);
			fdef.shape = pshape;
			// fdef.friction = 0.1f;
			// fdef.density = 1.1f;
			fdef.restitution = 0;
			fdef.filter.categoryBits = B2DVars.BIT_ROPE;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER;
			fdef.isSensor = true;
			ebody.createFixture(fdef).setUserData("hdfbead");

			// create foot sensor
			pshape.setAsBox(2 / PPM, 2 / PPM, new Vector2(0, -1 / PPM), 0);

			bdef.position.set(bdef.position.x, bdef.position.y + 10 / PPM);
			fdef.shape = pshape;
			// fdef.friction = 0.1f;
			// fdef.density = 1.1f;
			fdef.restitution = 0;
			fdef.filter.categoryBits = B2DVars.BIT_ENEMY;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_RED;
			fdef.isSensor = true;
			ebody.createFixture(fdef).setUserData("bfgody");

			// ebody.setUserData(c);

			pshape.dispose();
		}

	}

	private Body[] createRopeRock(int lengh, int index, int rockIndex) {

		if (bdRopesPoints == null)
			createPointsRope();

		Body[] segments = new Body[lengh];
		RevoluteJoint[] joints = new RevoluteJoint[lengh - 1];
		RopeJoint[] ropeJoints = new RopeJoint[lengh + 1];
		
		int random = (int) (Math.random() * 2 + 1);

		BodyDef bd = new BodyDef();

		bd.type = BodyType.DynamicBody;
		bd.position.set(bdRopesPoints.get(index + 1).getBody().getPosition());

		float width = 0.15f, height = 0.55f;
		PolygonShape ps = new PolygonShape();

		FixtureDef fdeff = new FixtureDef();
		fdeff.shape = ps;
		fdeff.density = 20;
		fdeff.friction = 1;
		fdeff.restitution = 0;
		fdeff.filter.categoryBits = B2DVars.BIT_BLUE;
		fdeff.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_BALLS
				| B2DVars.BIT_ENEMY;

		ps.setAsBox(width / 2, height / 2);

		for (int i = 0; i < segments.length; i++) {
			segments[i] = world.createBody(bd);
			if (level != 5 && level != 6)
				segments[i].createFixture(fdeff).setUserData("rope");
			else {
				if (i % 2 == 1) {
					segments[i].createFixture(fdeff).setUserData("ropefire");
				} else {
					segments[i].createFixture(fdeff).setUserData("rofdpe");
				}
			}
			Rock c;
			if (level != 5 && level != 6) {
				if (random == 1) {
					c = new Rock(segments[i], rope3, true);
					// tex = game.manager.get("images/rope3.png");
				} else {
					c = new Rock(segments[i], rope3, true);
					// tex = game.manager.get("images/rope3.png");
				}
			} else {
				c = new Rock(segments[i], rope3, true);
				// tex = game.manager.get("images/firerope.png");
			}

			if (random == 1)
				c.spr.setScale(0.33f, 0.13f);
			else
				c.spr.setScale(0.33f, 0.13f);
			c.spr.setRotation(190);
			// c.spr.setTexture(tex);

			bdRopesPoints.add(c);

		}

		RevoluteJointDef jointdef = new RevoluteJointDef();
		jointdef.localAnchorA.y = -height / 3;
		jointdef.localAnchorB.y = height / 3;
		for (int i = 0; i < joints.length; i++) {

			if (i == 0) {
				jointdef.bodyA = rocks.get(rockIndex).getBody();
				jointdef.bodyB = segments[0];
				// jointdef.bodyA = bdRopesPoints.get(index + 1).getBody();
				// jointdef.bodyB = segments[0];
			} else if (i < joints.length - 1) {
				jointdef.bodyA = segments[i - 1];
				jointdef.bodyB = segments[i];
			} else if (i == joints.length - 1) {

				jointdef.bodyA = segments[i];
				jointdef.bodyB = bdRopesPoints.get(index).getBody();
			}

			// jointdef.bodyA = segments[i + 1];
			// jointdef.bodyB = segments[i];

			joints[i] = (RevoluteJoint) world.createJoint(jointdef);
		}

		RopeJointDef ropeDef = new RopeJointDef();
		ropeDef.localAnchorA.set(0, -height / 3);
		ropeDef.localAnchorB.set(0, height / 3);
		ropeDef.maxLength = height - 0.2f;

		for (int i = 0; i < ropeJoints.length; i++) {

			if (i == 0) {
				ropeDef.bodyA = rocks.get(rockIndex).getBody();
				ropeDef.bodyB = segments[0];
			} else if (i < joints.length - 1) {
				ropeDef.bodyA = segments[i];
				ropeDef.bodyB = segments[i + 1];
			} else if (i == joints.length - 1) {
				ropeDef.bodyA = segments[i - 1];
				ropeDef.bodyB = bdRopesPoints.get(index).getBody();
			}

			// ropeDef.bodyA = segments[i];
			// ropeDef.bodyB = segments[i + 1];

			ropeJoints[i] = (RopeJoint) world.createJoint(ropeDef);
		}

		return segments;
	}

	/** all the moving platforms in the game, wood platforms **/
	private void createMovingPlatforms(int index){//), int rockIndex) {


		if (bdRopesPoints == null)
			createPointsRope();
		
		if(movingPlatform == null)
			movingPlatform = new Array<Rock>();

		

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		RevoluteJointDef jointdef = new RevoluteJointDef();
		jointdef.localAnchorA.y = -2.5f / 3;
		jointdef.localAnchorB.y = 1 / 3;
		
			
			bdef.type = BodyType.DynamicBody;

			float x = bdRopesPoints.get(index).getBody().getPosition().x;
			float y = bdRopesPoints.get(index).getBody().getPosition().y;

			bdef.position.set(x, y);

			PolygonShape cshape = new PolygonShape();
			cshape.setAsBox(0.533f,0.1f);

			fdef.shape = cshape;
			fdef.density = 50;
			fdef.friction = 1;
			fdef.restitution = 0;
			//fdef.isSensor = true;
			fdef.filter.categoryBits = 11111;
			int random1 = (int) (Math.random() * 2 + 0);
			float random = (float) (Math.random() * 1 + 0);
			if(random1 < 1){
				random *= -1;
			} else {
			
			}
			System.out.println(random);
			bdef.angle = bdef.angle + random;
			Body body = world.createBody(bdef);
			
			body.createFixture(fdef).setUserData("rock");
			body.setLinearVelocity(0, 0f);
			Rock c = new Rock(body, null, false);
			Texture tex = game.manager.get("images/woodplatform.png");
			c.spr.setTexture(tex);
			c.spr.setScale(1.5f, 0.4f);
			movingPlatform.add(c);

			jointdef.bodyA = bdRopesPoints.get(index).getBody();
			jointdef.bodyB = body;//[0];
			//System.out.println("joint size" + axes.size);
			RevoluteJoint joint = (RevoluteJoint) world.createJoint(jointdef);
			body.setUserData(c);
		
	}

	private void createRollingPlatfomrs(int index){


		if (bdRopesPoints == null)
			createPointsRope();
		
		if(rollingSpikes == null)
			rollingSpikes = new Array<Rock>();

		

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		RevoluteJointDef jointdef = new RevoluteJointDef();
		jointdef.localAnchorA.y = -0.5f / 3;
		jointdef.localAnchorB.y = 1 / 3;
		
			
			bdef.type = BodyType.DynamicBody;

			float x = bdRopesPoints.get(index).getBody().getPosition().x;
			float y = bdRopesPoints.get(index).getBody().getPosition().y;

			bdef.position.set(x, y);

			PolygonShape cshape = new PolygonShape();
			cshape.setAsBox(0.9f,0.133f);

			fdef.shape = cshape;
			fdef.density = 50;
			fdef.friction = 1;
			fdef.restitution = 0;
			//fdef.isSensor = true;
			fdef.filter.categoryBits = 11111;
			
			float random = (float) (Math.random() * 2 + 0);
			System.out.println(random);
			bdef.angle = bdef.angle + random;
			Body body = world.createBody(bdef);
			
			body.createFixture(fdef).setUserData("ropefire");
			body.setLinearVelocity(0, 0f);
			Rock c = new Rock(body, null, false);
			c.spr.setTexture(new Texture(Gdx.files.internal("images/spikesplatform.png")));
			c.spr.setScale(2.5f, 0.5f);
			rollingSpikes.add(c);

			jointdef.bodyA = bdRopesPoints.get(index).getBody();
			jointdef.bodyB = body;//[0];
			//System.out.println("joint size" + axes.size);
			RevoluteJoint joint = (RevoluteJoint) world.createJoint(jointdef);
			body.setUserData(c);
		
	}
	
	private void createAxes(int index){


		///bottleSprite = new Sprite(new Texture(Gdx.files.internal("images/axe.png"))); 
		
		if (bdRopesPoints == null)
			createPointsRope();
		
		if(axes == null)
			axes = new Array<Rock2>();
		
		float x = bdRopesPoints.get(index).getBody().getPosition().x;
		float y = bdRopesPoints.get(index).getBody().getPosition().y;

		
		    // 0. Create a loader for the file saved from the editor.
		    BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("physics/axe1.json"));
		 
		    // 1. Create a BodyDef, as usual.
		    BodyDef bd = new BodyDef();
		    bd.position.set(x, y);
		    bd.type = BodyType.DynamicBody;
		 
		    // 2. Create a FixtureDef, as usual.
		    FixtureDef fd = new FixtureDef();
		    fd.density = 50;
		    fd.friction = 0.5f;
		    fd.restitution = 0.0f;
		    fd.filter.categoryBits = B2DVars.BIT_BALLS;
		    // 3. Create a Body, as usual.
		    bd.angle = bd.angle + 9;
		   bottleModel = world.createBody(bd);
		 
		    // 4. Create the body fixture automatically by using the loader.
		    loader.attachFixture(bottleModel, "Name", fd, 1.01f,0.8f,1);
		    for(int i =0 ;i < bottleModel.getFixtureList().size; i++)
		    bottleModel.getFixtureList().get(i).setUserData("ropefire");		

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		RevoluteJointDef jointdef = new RevoluteJointDef();
		jointdef.localAnchorA.y = -0.5f / 3;
		jointdef.localAnchorB.y = 1 / 3;
		
			
			bdef.type = BodyType.DynamicBody;

			
			bdef.position.set(x, y);

			PolygonShape cshape = new PolygonShape();
			cshape.setAsBox(0.9f,0.133f);

			fdef.shape = cshape;
			fdef.density = 50;
			fdef.friction = 1;
			fdef.restitution = 0;
			//fdef.isSensor = true;
			fdef.filter.categoryBits = B2DVars.BIT_BALLS;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER;
			float random = (float) (Math.random() * 2 + 0);
		//	System.out.println(random);
			bdef.angle = bdef.angle + 909;
			//Body body = world.createBody(bdef);
			
			//body.createFixture(fdef).setUserData("ropefire");
			//body.setLinearVelocity(0, 0f);
			Rock2 c = new Rock2(bottleModel, null, false);
			c.spr.setTexture(new Texture(Gdx.files.internal("images/axe.png")));
			c.spr.setOrigin(c.spr.getOriginX() - 1f, c.spr.getOriginY() - 34
					);//.setPosition(c.spr.getX(), c.spr.getY() + 10);
			c.spr.setScale(1.0f, 1.5f);
			axes.add(c);

			jointdef.bodyA = bdRopesPoints.get(index).getBody();
			jointdef.bodyB = bottleModel;//[0];
			jointdef.localAnchorB.y *= 10;
			jointdef.localAnchorA.y *= +2.8f;
			//System.out.println("joint size" + axes.size);
			RevoluteJoint joint = (RevoluteJoint) world.createJoint(jointdef);
		bottleModel.setUserData(c);
		
			
			
	}
	
	public void createCrystals() {
		crystals = new Array<Crystals>();

		MapLayer layer = tileMap.getLayers().get("crystals");

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		for (MapObject mo : layer.getObjects()) {

			MapProperties props = mo.getProperties();

			bdef.type = BodyType.StaticBody;

			float x = props.get("x", float.class) / PPM;
			float y = props.get("y", float.class) / PPM;

			bdef.position.set(x, y);

			CircleShape cshape = new CircleShape();
			cshape.setRadius(8 / PPM);

			fdef.shape = cshape;
			fdef.isSensor = true;
			fdef.filter.categoryBits = B2DVars.BIT_CRYSTAL;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER;

			Body body = world.createBody(bdef);
			body.createFixture(fdef).setUserData("crystal");
			body.setLinearVelocity(0, 0f);
			Crystals c = new Crystals(body);
			crystals.add(c);

			body.setUserData(c);
		}
	}

	public void createHearts() {
		hearts = new Array<Rock>();

		MapLayer layer = tileMap.getLayers().get("hearts");

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		for (MapObject mo : layer.getObjects()) {

			MapProperties props = mo.getProperties();

			bdef.type = BodyType.StaticBody;

			float x = props.get("x", float.class) / PPM;
			float y = props.get("y", float.class) / PPM;

			bdef.position.set(x, y);

			CircleShape cshape = new CircleShape();
			cshape.setRadius(8 / PPM);

			fdef.shape = cshape;
			fdef.isSensor = true;
			fdef.filter.categoryBits = B2DVars.BIT_CRYSTAL;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER;

			Body body = world.createBody(bdef);
			body.createFixture(fdef).setUserData("hearts");
			body.setLinearVelocity(0, 0f);
			Texture heartTex = game.manager.get("images/heart.png");
			// Texture shieldTex = new
			// Texture(Gdx.files.internal("images/bubbleshield.png"));
			// game.manager.get("images/heart.png");
			Rock c = new Rock(body, null, false); // FIX
			c.spr.setTexture(heartTex);
			c.spr.setScale(0.31f);
			hearts.add(c);

			body.setUserData(c);
		}
	}

	public void createSheilds() {
		shields = new Array<Rock>();

		MapLayer layer = tileMap.getLayers().get("shields");

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		for (MapObject mo : layer.getObjects()) {

			MapProperties props = mo.getProperties();

			bdef.type = BodyType.StaticBody;

			float x = props.get("x", float.class) / PPM;
			float y = props.get("y", float.class) / PPM;

			bdef.position.set(x, y);

			CircleShape cshape = new CircleShape();
			cshape.setRadius(8 / PPM);

			fdef.shape = cshape;
			fdef.isSensor = true;
			fdef.filter.categoryBits = B2DVars.BIT_CRYSTAL;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER;

			Body body = world.createBody(bdef);
			body.createFixture(fdef).setUserData("shield");
			body.setLinearVelocity(0, 0f);
			// Texture heartTex = game.manager.get("images/heart.png");
			Texture shieldTex = game.manager.get("images/shield.png");
			// new Texture(Gdx.files.internal("images/shield.png"));
			// game.manager.get("images/heart.png");
			Rock c = new Rock(body, null, false); // FIX
			c.spr.setTexture(shieldTex);
			c.spr.setScale(0.4f);
			shields.add(c);

			body.setUserData(c);
		}
	}

	@Override
	public void pause() {
		super.pause();
		if (state == GameState.RUNNING)
			state = GameState.BEFORE_PAUSED;

	}
}
