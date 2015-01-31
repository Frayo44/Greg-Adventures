package com.platforngame.handlers;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.platforngame.handlers.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
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
import com.platformgame.entities.TheEnd;
import com.platformgame.screens.AbstractScreen;
import com.platformgame.screens.LevelScreen;
import com.platformgame.screens.ModesScreen;
import com.platforngame.handlers.MusicManager.TyrianMusic;

public class BaseLevel extends AbstractScreen {

	// Car Creating
	private Car car, car2;

	Skin skin, skin1, skin2, skin3, skin4, skin5, skin6, skin7, skin8, skin9;
	Table tableLabel;
	public static int level;
	MapLayer layer2;
	Table tablego;
	int moneAD;
	TextureRegion rope, rope2, rope3, ropeFire, woodpole, kirby, theend;
	
	public BaseLevel(PlatformGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	public enum GameState {
		READY, RUNNING, PAUSED, GAMEOVER, LEVEL_COMPLETED, BEFORE_RUNNING, BEFORE_PAUSED, BEFORE_LEVELCOMPLETED, BEFORE_GAMEOVER

	}

	public static int hashia = 0;
	protected Array<HealthBar> healthBar;

	public GameState state = GameState.READY;

	Body[] bdRopes;

	protected int totalCryStals;

	// Sone Labels Foe Level Completd States
	protected Label curScore;
	protected Label highScore;

	// Font for crystals
	GameFont gameFont;

	// Back Grounds
	private Background[] backgrounds;

	// Play Joystic booleans
	private boolean if_touch_left_side;
	private boolean if_touch_right_side;

	// The texture in the end
	TheEnd theEnd;

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

	// all the body int the world' for removing them
	private Array<Body> worldBodies = new Array<Body>();

	public static MyContactListener cl;

	private TiledMap tileMap;
	private float tileSize;
	private OrthogonalTiledMapRenderer tmr;

	protected Player player;
	private Array<Crystals> crystals;
	private Array<Rock> hearts;
	private Array<Rock> rocks;
	private Array<Rock> trees;
	private Array<Rock> logs;
	private BoundedCamera b2dCam;

	private Array<Rock> bdRopesPoints;
	Body ebody;

	protected Image nextLevelImage;
	protected Image gameOverImage;

	private Array<DragonEnemy> dragonEnemy;

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
	//	System.out.println("player x - " + player.getPosition().x
		//	+ "player y - " + player.getPosition().y);
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
			if ((cl.numLifes  >= 0) && (cl.numLifes < healthBar.size))
			for (int f = 0; f < cl.numLifes + 1; f++) {
				healthBar.get(f).lifeVisible = true;
			}
			if ((cl.numLifes  >= 0) && (cl.numLifes < healthBar.size)){
				cl.numLifes += 1;
				cl.numLifesCurr += 1;
			}
			
//			if ((cl.numLifes  >= 0) && (cl.numLifes < healthBar.size)) {
//				
//				healthBar.get(cl.numLifes).lifeVisible = true;
//				if ((cl.numLifesCurr >= 0) && (cl.numLifesCurr < healthBar.size)) {
//					healthBar.get(cl.numLifesCurr).lifeVisible = true;
//				}
//			}
		}
		bodiesHearts.clear();

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
		
		if (car != null)
			car.render(sb);
		if (car2 != null)
			car2.render(sb);

		
		
		for (int i = 0; i < hearts.size; i++) {
			hearts.get(i).render(sb);
		}
		
		if(trees != null)
		for (int i = 0; i < trees.size; i++) {
			trees.get(i).render(sb);
		}
		
		
		// draw tile map
		tmr.setView(cam);
		tmr.render();
		
		if(logs != null)
			for (int i = 0; i < logs.size; i++) {
				logs.get(i).render(sb);
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
			if ((level == 3 && i >= 11 && i <= 14)
					|| (level == 6 && i >= 0 && i <= 4) || 
				level == 10 && i >= 0 && i <= 3){

				if (rocks.get(i).getBody().getLinearVelocity().y >= 0) {
					if (rocks.get(i).getBody().getLinearVelocity().y != 2)
						rocks.get(i).getBody().setLinearVelocity(0, 2);
				} else {
					if (rocks.get(i).getBody().getLinearVelocity().y != -2)
						rocks.get(i).getBody().setLinearVelocity(0, -2);
				}

			}
			rocks.get(i).render(sb);
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
			moneAD = 0;
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
		moneAD = 0;
		// Clear the stage
		stage.clear();

		// home button
		// stage.addAction(sequence(Actions.fadeIn(0.3f)));

		// Pause Button
		// initPause();

		// All game over UI

		//initGameOverUI();
		stage.addActor(gameOverImage);
		stage.addActor(tableLabel);
		stage.addActor(tablego);

		stage.addAction(sequence(Actions.fadeIn(0.4f)));

		state = GameState.LEVEL_COMPLETED;
	}

	private void game_over(){
		moneAD += 1;
		if(moneAD >= 20 && moneAD <= 30){
			game.getActioResolver().showOrLoadInterstital();
		}
		
	}
	
	private void level_completed() {
		moneAD += 1;
		if(moneAD >= 20 && moneAD <= 30){
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
//		initDialog();
		
		
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
				//new Texture(Gdx.files.internal("images/ropes.png"));
		//TextureRegion texReg = ta.findRegion("kirby");
		rope = new TextureRegion(ta, 1, 1, 127, 90); //
		rope2 = new TextureRegion(ta, 575, 56, 52, 406);//
		rope3 = new TextureRegion(ta, 629, 56, 52, 406);
		//theend = new TextureRegion(ta, 1, 93, 572, 369);
		ropeFire =  new TextureRegion(ta, 683, 56, 127, 90);
		woodpole = new TextureRegion(ta,683, 236, 89, 226);
		
//		Texture texP = new Texture(Gdx.files.internal("images/ropes.png"));
//		TextureRegion tgRope = new TextureRegion(texP, 1, 1, 127, 90);
		
		//Multi For back Pressesd
		InputProcessor backProcessor = new InputAdapter() {
			 @Override
			 public boolean keyDown(int keycode) {
			
			 if ((keycode == Keys.ESCAPE) || (keycode == Keys.BACK)){
				 if(state == GameState.RUNNING)
					 state = GameState.BEFORE_PAUSED;
				 if(state == GameState.PAUSED)
					 state = GameState.BEFORE_RUNNING;
			 }
				 
			 return false;
			 }
			 };

			 InputMultiplexer multiplexer = new InputMultiplexer(stage,
			 backProcessor);
			 Gdx.input.setInputProcessor(multiplexer);
			 Gdx.input.setCatchBackKey(true);
		
		
		Texture bgs;// = game.manager.get("images/background.png");
		if (level == 3) {
			bgs = game.manager.get("images/backgroundnight.png");
		}

		else if (level == 5 || level == 9) {
			if(!game.manager.isLoaded("images/background3.png")){
				game.manager.load("images/background3.png", Texture.class);
			}
			bgs = game.manager.get("images/background3.png");
		}

		else if (level == 7 || level == 11) {
			bgs = game.manager.get("images/background4.png");
		} else {
			bgs = game.manager.get("images/background.png");
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
		if (level == 5 || level == 11) {
			car = new Car(world, fdef, wheelFixtureDef, player.getPosition().x,
					player.getPosition().y - 1, 1F, .5f);
			car2 = new Car(world, fdef, wheelFixtureDef, 32, 13, 1F, .5f);
		}
		if (level == 6) {
			car = new Car(world, fdef, wheelFixtureDef,
					player.getPosition().x + 0.1f, player.getPosition().y - 1,
					1F, .5f);
			car2 = new Car(world, fdef, wheelFixtureDef, 35.5f, 4, 1F, .5f);
		}
		if (level == 8) {
			car = new Car(world, fdef, wheelFixtureDef,
					player.getPosition().x - 0.6f, player.getPosition().y - 1,
					1F, .5f);
			// car2 = new Car(world,fdef,wheelFixtureDef, 35.5f,4,1F,.5f);
		}
		if (level == 9) {
			 car = new Car(world, fdef, wheelFixtureDef,
			 44.7f, 30.5f,
			 1F, .5f);
		}
		
		if (level == 10) {
			 car = new Car(world, fdef, wheelFixtureDef,
			 41.3f, 26.5f,
			 1F, .5f);
		}
	}

	
	public void initDialog() {
	
		new CustomDialog("Town World Is Open")
				// this is the dialog title
				.text("Want to go to the next world?")
				// text appearing in the dialog
				.button("Keep playing", new InputListener() { // button
							// to
							// exit
							// app
							public boolean touchDown(InputEvent event, float x,
									float y, int pointer, int button) {
								
								// state = GameState.RUNNING;
								return false;
							}
						})
				.button("Continue", new InputListener() { // button
							// to
							// exit
							// app
							public boolean touchDown(InputEvent event, float x,
									float y, int pointer, int button) {
								stage.addAction(sequence(fadeOut(0.5f), run(new Runnable() {
									public void run() {
										game.setScreen(game.getLevelTownLevelScreen());
									}
								})));
								
								return false;
							}
						})// button that simply closes the dialog
				.show(stage); // actually show the dialog
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

		// create the main car
		if (level == 5 || level == 11) {
			createCar();
		}

		if (level == 6) {
			createCar();
		}

		if (level == 8 || level == 9 || level==10) {
			createCar();
		}
		
		if (tileMap.getLayers().get("trees") != null)
			createTrees();

		if (tileMap.getLayers().get("enemies") != null)
			createEnemies();
		
		if (tileMap.getLayers().get("logs") != null)
			createLogs();
		
		// create font
		gameFont = new GameFont();
		player.setTotalCrystals(crystals.size);

		layer2 = tileMap.getLayers().get("ropes");
		if (layer2 != null) {
			bdRopesPoints = null;
			switch (level) {
			case 1:
			case 2:
				break;
			case 3:
				createRope(16, 0);
				createRope(18, 2);
				break;
			case 4:
				createRope(10, 0);
				createRope(18, 2);
				createRope(18, 4);
				// createRope(34, 6);
				createRope(15, 8);
				createRope(15, 10);
				createRope(19, 12);
				createRope(8, 14);
				createRope(8, 16);
				createRope(10, 18);
				createRope(8, 20);
				createRope(10, 22);
				createRope(10, 24);
				break;
			case 5:
//				createRope2(20, 0);
//				createRope2(7, 0);
//				createRope2(8, 2);
//				createRope2(6, 4);
//				createRope2(7, 6);
//				createRope2(7, 8);
				//createRope2(10, 2);
				//createRope2(11, 4);
				//createRope2(10, 6);
				//createRope2(10, 8);
				//createRope2(10, 10);
				//createRope2(10, 12);
				//createRope2(10, 14);
				//createRope2(10, 16);
				//createRope2(12, 18);
				//createRope2(10, 20);
				//createRope2(7, 22);
			//	createRope(8, 24);
			//	createRope(8, 26);
			//	createRope(6, 28);
				//createRope(10, 30);
				break;
			case 6:
				createRope(7, 0);
				createRope(8, 2);
				createRope(8, 4);
				break;
			case 7:
				createRopeRock(5, 0, 0);
				createRopeRock(7, 2, 1);
				createRopeRock(7, 4, 2);
				break;
			case 8:
				createRope(9, 0);
				createRope(9, 2);
				createRope(9, 4);
				createRope(11, 6);
				break;
			case 9:
				createRope(8, 0);
				createRope(8, 2);
				createRopeRock(6, 4, 34);
				createRope(7, 6);
				createRope(7, 8);
				createRope(5, 10);
				createRope(5, 12);
				createRopeRock(6, 14, 35);
				createRopeRock(6, 16, 36);
				createRopeRock(6, 18, 37);
				createRopeRock(6, 20, 38);
				createRopeRock(6, 22, 39);
				createRope(5, 26);
				// System.out.println(rocks.size - 1);
				break;
			case 10:
				createRope(10, 0);
				createRope(10, 2);
				createRopeRock(4, 4, 34);
				createRopeRock(4, 6, 35);
				createRopeRock(4, 8, 36);
				createRopeRock(4, 10, 37);
				createRopeRock(4, 12, 38);
				createRopeRock(4, 14,39);
				createRopeRock(4, 16, 40);
				createRopeRock(4, 18, 41);
				createRopeRock(4, 20, 42);
				break;
			case 11:
				createRope(13, 0);
				createRope(12, 2);
				//createRope(20, 4);
				//createRope(9, 6);
				createRope(10, 8);
				createRope(10, 10);
				createRope(10, 12);
				createRope(10, 14);
				createRope(10, 16);
				createRope(12, 18);
				createRope(10, 20);
				createRope(14, 22);
				createRopeRock(4, 24, rocks.size - 2);
				createRopeRock(4, 26, rocks.size - 3);
				createRopeRock(4, 28, rocks.size - 1);
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
		//hhd
		//stage.addActor(gameOverImage);
		int besthighscore = 0;
		switch (level) {
		case 1:
			besthighscore = game.getPreferencesManager().getLevelOneHighScore();
			break;
		case 2:
			besthighscore = game.getPreferencesManager().getLevelTwoHighScore();
			break;
		case 3:
			besthighscore = game.getPreferencesManager()
					.getLevelThreeHighScore();
			break;
		case 4:
			besthighscore = game.getPreferencesManager()
					.getLevelFourHighScore();
			break;
		case 5:
			besthighscore = game.getPreferencesManager()
					.getLevelFiveHighScore();
			break;
		case 6:
			besthighscore = game.getPreferencesManager().getLevelSixHighScore();
			break;
		case 7:
			besthighscore = game.getPreferencesManager()
					.getLevelSevenHighScore();
			break;
		case 8:
			besthighscore = game.getPreferencesManager()
					.getLevelEightHighScore();
			break;
		case 9:
			besthighscore = game.getPreferencesManager()
					.getLevelNineHighScore();
		case 10:
			besthighscore = game.getPreferencesManager()
					.getLevelTenHighScore();
			break;
		case 11:
			besthighscore = game.getPreferencesManager()
					.getLevelElevenHighScore();
			break;
		case 12:
			besthighscore = game.getPreferencesManager()
					.getLevelTwelveHighScore();
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
//				getSkin(),
//				"medium-font");
		curScore.setFontScale(0.6f);
		highScore.setFontScale(0.6f);
		// retrieve the default table actor

		 tableLabel = new Table();//super.getTable();
		 tableLabel.setFillParent(true);
		if (curScore != null)
			tableLabel.removeActor(curScore);
		tableLabel.defaults().padBottom(10);
		tableLabel.padRight(100);
		tableLabel.add(curScore);
		tableLabel.center();
	//	stage.addActor(tableLabel);
		tableLabel.row();
		// Table tableLabel2 = super.getTable();
		if (highScore != null)
			tableLabel.removeActor(highScore);
		tableLabel.padTop(10);
		tableLabel.padRight(1);
		tableLabel.add(highScore);
		tableLabel.left().center();
		//stage.addActor(tableLabel);
		tableLabel.row();
		tableLabel.defaults().padBottom(10);
		tableLabel.padLeft(30);
		tableLabel.add(lifes);
		tableLabel.center();
		//hhd
		//stage.addActor(tableLabel);
		
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
//hh
		//stage.addActor(table);

	//	stage.addAction(sequence(Actions.fadeIn(0.4f)));// /, run(new Runnable()
				//hh										// {
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
						state = GameState.BEFORE_PAUSED;
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
		PolygonShape shape = new PolygonShape();
		CircleShape cs = new CircleShape();
		cs.setRadius(14.5f / PPM);
		// create player
		if (level == 3 || level == 4) {
			bdef.position.set(60 / PPM, 700 / PPM);
		} else if (level == 5 || level == 11) {
			bdef.position.set(60 / PPM, 1000 / PPM);
		} else if (level == 6 || level == 7) {
			bdef.position.set(60 / PPM, 3000 / PPM);
		} else if (level == 8) {
			bdef.position.set(150 / PPM, 3100 / PPM);
		} else if (level == 9) {
			bdef.position.set(150 / PPM, 3200 / PPM);
		} else if (level == 10) {
			bdef.position.set(150 / PPM, 3100 / PPM);
		}

		else {
			bdef.position.set(60 / PPM, 180 / PPM);
		}

		bdef.type = BodyType.DynamicBody;
		// bdef.linearVelocity.set(1f, 0f);
		Body body = world.createBody(bdef);
		shape.setAsBox(13 / PPM, 13 / PPM);
	//	shape.setAsBox(100 / PPM, 100 / PPM);
		fdef.shape = cs;
		fdef.density = 7;
		fdef.friction = 0.2f;
		fdef.restitution = 0.001f;
//		fdef.density = 7;
//		fdef.friction = 0.1f;
//		fdef.restitution = 0.001f;
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
				| B2DVars.BIT_CAR | B2DVars.BIT_THRONES;
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
		rocks = new Array<Rock>();
		if(tileMap == null){
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
					| B2DVars.BIT_CAR | B2DVars.BIT_CRYSTAL | B2DVars.BIT_BALLS
					| B2DVars.BIT_ROPE;

			if (level == 2 && rocks.size >= 11) {
				fdef.density = 9.1f;
				Body body = world.createBody(bdef);
		
				body.createFixture(fdef).setUserData("rock");
				body.setLinearVelocity(0, 0f);
				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/woodrock.png");
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 3 && rocks.size >= 11 && rocks.size <= 14) {
				fdef.density = 15.1f;
				fdef.restitution = 0.8f;
				Body body = world.createBody(bdef);
				body.setLinearVelocity(0, 100);
				body.createFixture(fdef).setUserData("jumpingrock");
				body.setLinearVelocity(0, 0f);

				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/bouncyball.png");
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 4) {
				fdef.density = 9.1f;
				Body body = world.createBody(bdef);
				body.createFixture(fdef).setUserData("rock");
				body.setLinearVelocity(0, 0f);
				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/woodrock.png");
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 6 && rocks.size >= 0 && rocks.size <= 4) {
				fdef.density = 15.1f;
				fdef.restitution = 0.8f;
				Body body = world.createBody(bdef);
				body.setLinearVelocity(0, 100);
				body.createFixture(fdef).setUserData("jumpingrock");
				body.setLinearVelocity(0, 0f);

				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/bouncyball.png");
				c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 7 && rocks.size >= 0 && rocks.size <= 4) {
				cshape.setRadius(35 / PPM);
				fdef.density = 9.1f;
				fdef.restitution = 0.8f;
				Body body = world.createBody(bdef);
				body.setLinearVelocity(0, 100);
				body.createFixture(fdef).setUserData("rock");
				body.setLinearVelocity(0, 0f);

				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.9f);
				// Texture texture = game.manager.get("images/bouncyball.png");
				// c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 9 && rocks.size >= 34 && rocks.size <= 39) {
				cshape.setRadius(45 / PPM);
				fdef.density = 9.1f;
				fdef.restitution = 0.8f;
				Body body = world.createBody(bdef);
				body.setLinearVelocity(0, 100);
				body.createFixture(fdef).setUserData("rock");
				body.setLinearVelocity(0, 0f);

				Rock c = new Rock(body, null, false);
				c.spr.setScale(1.2f);
				// Texture texture = game.manager.get("images/bouncyball.png");
				// c.spr.setTexture(texture);
				rocks.add(c);
				body.setUserData(c);
			} else if (level == 10 && rocks.size >= 0 && rocks.size <= 3) {
				fdef.density = 15.1f;
				fdef.restitution = 0.8f;
				Body body = world.createBody(bdef);
				body.setLinearVelocity(0, 100);
				body.createFixture(fdef).setUserData("jumpingrock");
				body.setLinearVelocity(0, 0f);

				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/bouncyball.png");
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

	
	private void createTrees() {
		trees = new Array<Rock>();
		
		MapLayer layer = tileMap.getLayers().get("trees");

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		for (MapObject mo : layer.getObjects()) {

			MapProperties props = mo.getProperties();

			bdef.type = BodyType.DynamicBody;

			float x = props.get("x", float.class) / PPM;
			float y = props.get("y", float.class) / PPM;

			bdef.position.set(x, y);

			PolygonShape cshape = new PolygonShape();
			cshape.setAsBox(15 /PPM, 25 / PPM);

			fdef.shape = cshape;
			fdef.isSensor = false;
			fdef.density = 3704.1f;
			fdef.friction = 0.1f;
			fdef.restitution = 0.1f;
			fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_RED
					| B2DVars.BIT_CAR | B2DVars.BIT_CRYSTAL | B2DVars.BIT_ENEMY | B2DVars.BIT_BALLS ;
			fdef.restitution = 0;
			fdef.friction = 0.5f;
				fdef.density = 12.1f;
				Body body = world.createBody(bdef);
				body.createFixture(fdef).setUserData("grabklkjage");
				body.setLinearVelocity(0, 0f);

				Rock c = new Rock(body, null, false);
				c.spr.setScale(0.7f);
				Texture texture = game.manager.get("images/tree.png");
				// new Texture(
				// Gdx.files.internal("images/spikesrock.png"));
				// game.manager.get("images/woodrock.png");
				c.spr.setTexture(texture);
				trees.add(c);
				body.setUserData(c);
				
			cshape.dispose();
		}
	}


	private void createLogs() {
		logs = new Array<Rock>();
		
		MapLayer layer = tileMap.getLayers().get("logs");

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		for (MapObject mo : layer.getObjects()) {

			MapProperties props = mo.getProperties();

			bdef.type = BodyType.DynamicBody;

			float x = props.get("x", float.class) / PPM;
			float y = props.get("y", float.class) / PPM;

			bdef.position.set(x, y);

			CircleShape cshape = new CircleShape();
			cshape.setRadius(15 / PPM);

			fdef.shape = cshape;
			fdef.isSensor = false;
			fdef.density = 12.1f;
			fdef.friction = 0.7f;
			fdef.restitution = 0.0f;
			fdef.filter.categoryBits = B2DVars.BIT_BALLS;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_RED
					| B2DVars.BIT_CAR | B2DVars.BIT_CRYSTAL | B2DVars.BIT_ROPE | B2DVars.BIT_BALLS;

			fdef.density = 20.1f;
			Body body = world.createBody(bdef);
			body.createFixture(fdef).setUserData("rock");
			body.setLinearVelocity(0, 0f);

			Rock c = new Rock(body, null, false);
			c.spr.setScale(0.4f);
			Texture teture = game.manager.get("images/log.png");
			// new Texture(
			// Gdx.files.internal("images/spikesrock.png"));
			// game.manager.get("images/woodrock.png");
			c.spr.setTexture(teture);
			logs.add(c);
			body.setUserData(c);

			cshape.dispose();
		}
	}
	
	private void createEnemies() {

		dragonEnemy = new Array<DragonEnemy>();

		MapLayer layer = tileMap.getLayers().get("enemies");

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		for (MapObject mo : layer.getObjects()) {

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
					| B2DVars.BIT_CRYSTAL | B2DVars.BIT_BALLS
					| B2DVars.BIT_ROPE | B2DVars.BIT_CAR;

			ebody = world.createBody(bdef);
			ebody.createFixture(fdef).setUserData("enemy");
			// ebody.setFixedRotation(true);
			ebody.setLinearVelocity(10, 0);
			// ebody.setAngularDamping(100000);
			ebody.setFixedRotation(true);
			DragonEnemy c;
			if (level == 3) {
				int random = (int) (Math.random() * 3 + 1);
				if (random == 1) {
					Texture texture = game.manager
							.get("animations/dragonfly.png");
					Texture tex = texture;
					c = new DragonEnemy(ebody, tex);
					dragonEnemy.add(c);
				} else {
					Texture tex = PlatformGame.manager
							.get("animations/dragon.png");
					c = new DragonEnemy(ebody, tex);
					dragonEnemy.add(c);
				}
			} else {
				Texture tex = PlatformGame.manager.get("animations/dragon.png");
				c = new DragonEnemy(ebody, tex);
				dragonEnemy.add(c);
			}

			/*
			 * create the head sensor,
			 * When the player jump on this the enemy fell off
			 */
			pshape.setAsBox(29 / PPM,7 / PPM, new Vector2(0, 29 / PPM), 0);

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

		//Texture tex = game.manager.get("images/woodpole.png");
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

			c = new Rock(ebody, woodpole, true);
			//c.spr.setTexture(tex);
			c.spr.setScale(0.5f, 0.25f);
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

	// 0 - 1
	private Body[] createRope(int lengh, int index) {

		if (bdRopesPoints == null)
			createPointsRope();

		Body[] segments = new Body[lengh];
		RevoluteJoint[] joints = new RevoluteJoint[lengh - 1];
		RopeJoint[] ropeJoints = new RopeJoint[lengh + 1];
		TextureRegion texg;
		Rock c;
		
		int random = (int) (Math.random() * 2 + 1);
//		if (level != 5 && level != 11 && level != 6 && level != 8
//				&& (level != 9 || index <= 4)) {
//			if (random == 1) {
//				//texg = rope;
//						//game.manager.get("images/rope.png");
//			} else {
//				texg = rope2;// game.manager.get("images/rope2.png");
//			}
//		} else {
//			texg = ropeFire;// game.manager.get("images/firerope.png");
//		}

		BodyDef bd = new BodyDef();

		bd.type = BodyType.DynamicBody;
		bd.position.set(bdRopesPoints.get(index + 1).getBody().getPosition());

		float width = 0.15f, height = 0.55f;
		PolygonShape ps = new PolygonShape();

		FixtureDef fdeff = new FixtureDef();
		fdeff.shape = ps;
		fdeff.density = 30;
		fdeff.friction = 1;
		fdeff.restitution = 0;
		fdeff.filter.categoryBits = B2DVars.BIT_ROPE;
		fdeff.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_BALLS
				| B2DVars.BIT_ENEMY;

		ps.setAsBox(width / 2, height / 2);

		for (int i = 0; i < segments.length; i++) {
			segments[i] = world.createBody(bd);
			if (level != 5 && level != 6 && level != 8
					&& (level != 9 || index <= 4) && level != 11)
				segments[i].createFixture(fdeff).setUserData("rope");
			else {
				if (i % 2 == 1) {
					segments[i].createFixture(fdeff).setUserData("ropefire");
				} else {
					segments[i].createFixture(fdeff).setUserData("rope");
				}
			}
			if (level != 5 && level != 11 && level != 6 && level != 8
					&& (level != 9 || index <= 4)) {
				if (random == 1) {
					//texg = rope;
					c = new Rock(segments[i], rope, true);
					if (random == 2)
						c.spr.setScale(0.3f, 0.15f);
					else
						c.spr.setScale(0.2f, 0.55f);
							//game.manager.get("images/rope.png");
				} else {
					c = new Rock(segments[i], rope2, true);
					if (random == 2)
						c.spr.setScale(0.3f, 0.15f);
					else
						c.spr.setScale(0.2f, 0.55f);
					//texg = rope2;// game.manager.get("images/rope2.png");
				}
			} else {
				c = new Rock(segments[i], ropeFire, true);
				c.spr.setScale(0.3f, 0.55f);
				//texg = ropeFire;// game.manager.get("images/firerope.png");
			}
			
			
			c.spr.setRotation(190);
			//c.setTex(rope2);

			bdRopesPoints.add(c);

		}

		RevoluteJointDef jointdef = new RevoluteJointDef();
		jointdef.localAnchorA.y = -height / 3;
		jointdef.localAnchorB.y = height / 3;
		for (int i = 0; i < joints.length; i++) {
			if (i == 0) {
				jointdef.bodyA = bdRopesPoints.get(index + 1).getBody();
				jointdef.bodyB = segments[0];
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

		for (int i = 0; i < segments.length - 1; i++) {

//			if (i == 0) {
//				ropeDef.bodyA = bdRopesPoints.get(index + 1).getBody();
//				ropeDef.bodyB = segments[0];
//			} else if (i < joints.length - 1) {
//				ropeDef.bodyA = segments[i];
//				ropeDef.bodyB = segments[i + 1];
//			} else if (i == joints.length - 1) {
//				ropeDef.bodyA = segments[i];
//				ropeDef.bodyB = bdRopesPoints.get(index).getBody();
//			}

			 ropeDef.bodyA = segments[i];
			 ropeDef.bodyB = segments[i + 1];

			ropeJoints[i] = (RopeJoint) world.createJoint(ropeDef);
			
			if(i == 0){
				ropeDef.bodyA = bdRopesPoints.get(index + 1).getBody();
				ropeDef.bodyB = segments[0];
				ropeJoints[i] = (RopeJoint) world.createJoint(ropeDef);
			}
			if(i == joints.length - 1){
				ropeDef.bodyA = segments[i];
				ropeDef.bodyB = bdRopesPoints.get(index).getBody();
				ropeJoints[i] = (RopeJoint) world.createJoint(ropeDef);
			}
		}

		return segments;
	}

	private Body[] createRope2(int lengh, int index) {

		if (bdRopesPoints == null)
			createPointsRope();

		Body[] segments = new Body[lengh];
		RevoluteJoint[] joints = new RevoluteJoint[lengh - 1];
		RevoluteJoint jointToRope;// = new RevoluteJoint(world, addr)
		RevoluteJoint jointToRope2;
		RopeJoint[] ropeJoints = new RopeJoint[lengh - 1];
		RopeJoint[] ropeJoints2 = new RopeJoint[lengh - 1];
	//	TextureRegion texg;
		Rock c;
		int random = (int) (Math.random() * 2 + 1);
//		Body[] segments = new Body[length];
//		RevoluteJoint[] joints = new RevoluteJoint[length - 1];
//		RopeJoint[] ropeJoints = new RopeJoint[length - 1];

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;

		float width = .2f, height = 0.8f;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.restitution = 0.0f;
		fdef.friction = 0.5f;
		fdef.density = 20;
		fdef.filter.categoryBits = B2DVars.BIT_ROPE;
		fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_CAR | B2DVars.BIT_BALLS;
		for(int i = 0; i < segments.length; i++) {
			segments[i] = world.createBody(bodyDef);
			segments[i].createFixture(fdef);
			
			if (level != 5 && level != 6 && level != 8
					&& (level != 9 || index <= 4) && level != 11)
				segments[i].createFixture(fdef).setUserData("rope");
			else {
				if (i % 2 == 1) {
					segments[i].createFixture(fdef).setUserData("ropefire");
				} else {
					segments[i].createFixture(fdef).setUserData("rope");
				}
			}
			if (level != 5 && level != 11 && level != 6 && level != 8
					&& (level != 9 || index <= 4)) {
				if (random == 1) {
					//texg = rope;
					c = new Rock(segments[i], rope, true);
					if (random == 2)
						c.spr.setScale(0.3f, 0.15f);
					else
						c.spr.setScale(0.2f, 0.55f);
							//game.manager.get("images/rope.png");
				} else {
					c = new Rock(segments[i], rope2, true);
					if (random == 2)
						c.spr.setScale(0.3f, 0.15f);
					else
						c.spr.setScale(0.2f, 0.55f);
					//texg = rope2;// game.manager.get("images/rope2.png");
				}
			} else {
				c = new Rock(segments[i], ropeFire, true);
				c.spr.setScale(-0.4f, 0.95f);
				//texg = ropeFire;// game.manager.get("images/firerope.png");
			}
			
			
			c.spr.setRotation(190);
			//c.setTex(rope2);

			bdRopesPoints.add(c);
		}

		shape.dispose();

		RevoluteJointDef jointDef = new RevoluteJointDef();
		jointDef.localAnchorA.y = -height / 2;
		jointDef.localAnchorB.y = height / 2;

		for(int i = 0; i < joints.length; i++) {
			jointDef.bodyA = segments[i];
			jointDef.bodyB = segments[i + 1];
			joints[i] = (RevoluteJoint) world.createJoint(jointDef);
		}

		RopeJointDef ropeJointDef = new RopeJointDef();
		ropeJointDef.localAnchorA.set(0, -height / 2);
		ropeJointDef.localAnchorB.set(0, height / 2);
		ropeJointDef.maxLength = height;

		for(int i = 0; i < ropeJoints.length; i++) {
			ropeJointDef.bodyA = segments[i];
			ropeJointDef.bodyB = segments[i + 1];
			ropeJoints[i] = (RopeJoint) world.createJoint(ropeJointDef);
		}
		
		for(int i = 0; i < ropeJoints2.length / 2; i++) {
			ropeJointDef.bodyA = segments[i];
			ropeJointDef.bodyB = segments[i + 1];
			ropeJoints2[i] = (RopeJoint) world.createJoint(ropeJointDef);
		}
		
		RevoluteJointDef jointDef2 = new RevoluteJointDef();
		jointDef2.localAnchorA.y = -height / 2;
		jointDef2.localAnchorB.y = height / 2;
		
		jointDef2.bodyA = segments[0];
		jointDef2.bodyB = bdRopesPoints.get(index).getBody();
		jointToRope = (RevoluteJoint) world.createJoint(jointDef2);
		
		
		
		
		jointDef2.bodyA = segments[segments.length - 1];
		jointDef2.bodyB = bdRopesPoints.get(index + 1).getBody();
		jointToRope2 = (RevoluteJoint) world.createJoint(jointDef2);
		return segments;
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
					//tex = game.manager.get("images/rope3.png");
				} else {
					c = new Rock(segments[i], rope3, true);
					//tex = game.manager.get("images/rope3.png");
				}
			} else {
				c = new Rock(segments[i], rope3, true);
				//tex = game.manager.get("images/firerope.png");
			}
			 
			if (random == 1)
				c.spr.setScale(0.33f, 0.13f);
			else
				c.spr.setScale(0.33f, 0.13f);
			c.spr.setRotation(190);
		//	c.spr.setTexture(tex);

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
			Rock c = new Rock(body, null, false);// FIX
			c.spr.setTexture(heartTex);
			c.spr.setScale(0.31f);
			hearts.add(c);

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
