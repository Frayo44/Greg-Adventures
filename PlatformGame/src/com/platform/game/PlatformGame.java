package com.platform.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.platformgame.icescreens.IceLevelsScreen;
import com.platformgame.icescreens.LevelIce1;
import com.platformgame.icescreens.LevelIce2;
import com.platformgame.icescreens.LoadingIceScreen;
import com.platformgame.screens.HighscoresScreen;
import com.platformgame.screens.Level1;
import com.platformgame.screens.Level10;
import com.platformgame.screens.Level11;
import com.platformgame.screens.Level12;
import com.platformgame.screens.Level2;
import com.platformgame.screens.Level3;
import com.platformgame.screens.Level4;
import com.platformgame.screens.Level5;
import com.platformgame.screens.Level6;
import com.platformgame.screens.Level7;
import com.platformgame.screens.Level8;
import com.platformgame.screens.Level9;
import com.platformgame.screens.LevelScreen;
import com.platformgame.screens.LoadingMenuScreen;
import com.platformgame.screens.LoadingScreen;
import com.platformgame.screens.MenuScreen;
import com.platformgame.screens.ModesScreen;
import com.platformgame.screens.OptionsScreen;
import com.platformgame.screens.ScrollPane1;
import com.platformgame.screens.WorldLevelScreen;
import com.platformgame.townscreens.LevelTown1;
import com.platformgame.townscreens.LevelTown10;
import com.platformgame.townscreens.LevelTown11;
import com.platformgame.townscreens.LevelTown12;
import com.platformgame.townscreens.LevelTown2;
import com.platformgame.townscreens.LevelTown3;
import com.platformgame.townscreens.LevelTown4;
import com.platformgame.townscreens.LevelTown5;
import com.platformgame.townscreens.LevelTown6;
import com.platformgame.townscreens.LevelTown7;
import com.platformgame.townscreens.LevelTown8;
import com.platformgame.townscreens.LevelTown9;
import com.platformgame.townscreens.LoadingTownScreen;
import com.platformgame.townscreens.TownLevelsScreen;
import com.platforngame.handlers.ActioResolver;
import com.platforngame.handlers.ActionResolver;
import com.platforngame.handlers.ActionResolver2;
import com.platforngame.handlers.BoundedCamera;
import com.platforngame.handlers.IActivityRequestHandler;
import com.platforngame.handlers.MusicManager;
import com.platforngame.handlers.PreferencesManager;
import com.platforngame.handlers.SoundManager;

public class PlatformGame extends Game {
	
	
	
	//from now
	public ActionResolver actionResolver;
	public ActionResolver2 actionResolver2;
	private ActioResolver actioResolver;
	boolean firstTimeCreate = true;
	
	private IActivityRequestHandler myRequestHandler;
//
//    public GameLevel(IActivityRequestHandler handler) {
//    	this.myRequestHandler = handler;
//    }
	
	public PlatformGame(IActivityRequestHandler handler, ActionResolver actionResolver, ActionResolver2  actionResolver2, ActioResolver actioResolver) {
		this.myRequestHandler = handler;
		this.actionResolver = actionResolver;
		this.actionResolver2 = actionResolver2;
		this.actioResolver = actioResolver;
	}
	
	public ActioResolver getActioResolver(){
		return actioResolver;
	}
	

	public static final String TITLE = "Block Bunny";
	public static final int V_WIDTH = 350; // 320
	public static final int V_HEIGHT = 240; //240
	public static final int SCALE = 2;

	public static final float STEP = 1 / 60f;
	private float accum;

	private SpriteBatch sb;
	private BoundedCamera cam;
	private OrthographicCamera hudCam;
	// -- end --
	
	 //Services
	 private MusicManager musicManager;
	 private static SoundManager soundManager;
	 private PreferencesManager preferencesManager;
	 
	public static final String LOG = PlatformGame.class.getSimpleName();
	
	 public static AssetManager manager = new AssetManager();
	 

	@Override
	public void create() {

		Gdx.app.log(PlatformGame.LOG, "Showing Game");
		
		// create the preferences manager
        preferencesManager = new PreferencesManager();
        
        // create the music manager
        musicManager = new MusicManager();
        musicManager.setVolume( preferencesManager.getVolume() );
        musicManager.setEnabled( preferencesManager.isMusicEnabled() );

        // create the sound manager
        soundManager = new SoundManager();
        soundManager.setVolume( preferencesManager.getVolume() );
        soundManager.setEnabled( preferencesManager.isSoundEnabled() );

		sb = new SpriteBatch();
		cam = new BoundedCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		// show the splash screen when the game is resized for the first time;
		// this approach avoids calling the screen's resize method repeatedly
		if (getScreen() == null) {
			setScreen(new LoadingMenuScreen(this));

		}
	}
	
	// Services' getters

    public PreferencesManager getPreferencesManager()
    {
        return preferencesManager;
    }

    public MusicManager getMusicManager()
    {
        return musicManager;
    }

    public static SoundManager getSoundManager()
    {
        return soundManager;
    }
    
    public LevelScreen getLevelScreen()
    {
    	
        return new LevelScreen(this );
    }
    
    public Level6 getLevel6Screen()
    {
    	
        return new Level6(this );
    }
    
    public WorldLevelScreen getLevelWorldLevelScreen()
    {
    	
        return new WorldLevelScreen(this );
    }
    
    public LevelIce1 getLevelIce1()
    {
    	
        return new LevelIce1(this );
    }
    
    public LevelIce2 getLevelIce2()
    {
    	
        return new LevelIce2(this );
    }
    
    public IceLevelsScreen getIceLevelsScreen()
    {
    	
        return new IceLevelsScreen(this );
    }
    
    public LoadingIceScreen getLoadingIceScreen()
    {
    	
        return new LoadingIceScreen(this );
    }
    
    public ModesScreen getModesScreen()
    {
    	
        return new ModesScreen(this );
    }
    
    public LevelTown10 getLevelTown10Screen()
    {
    	
        return new LevelTown10(this );
    }
    
    public HighscoresScreen getHighscoresScreen()
    {
    	
        return new HighscoresScreen(this );
    }
    
    public LevelTown11 getLevelTown11Screen()
    {
    	
        return new LevelTown11(this );
    }
    
    public LevelTown2 getLevelTown2Screen()
    {
    	
        return new LevelTown2(this );
    }
    
    public LevelTown12 getLevelTown12Screen()
    {
    	
        return new LevelTown12(this );
    }
    
    
    public LevelTown9 getLevelTown9Screen()
    {
    	
        return new LevelTown9(this );
    }
    
    public LevelTown8 getLevelTown8Screen()
    {
    	
        return new LevelTown8(this );
    }
    
    public LevelTown6 getLevelTown6Screen()
    {
    	
        return new LevelTown6(this );
    }
    
    public LevelTown7 getLevelTown7Screen()
    {
    	
        return new LevelTown7(this );
    }
    
    public LevelTown3 getLevelTown3Screen()
    {
    	
        return new LevelTown3(this );
    }
    
    public LevelTown5 getLevelTown5Screen()
    {
    	
        return new LevelTown5(this );
    }
    
    public LevelTown4 getLevelTown4Screen()
    {
    	
        return new LevelTown4(this );
    }
    
    
    public TownLevelsScreen getLevelTownLevelScreen()
    {
    	
        return new TownLevelsScreen(this );
    }
    
    public Level12 getLevel12Screen()
    {
    	
        return new Level12(this );
    }
    
    public Level7 getLevel7Screen()
    {
    	
        return new Level7(this );
    }
    
    public Level10 getLevel10Screen()
    {
    	
        return new Level10(this );
    }
    
    public Level11 getLevel11Screen()
    {
    	
        return new Level11(this );
    }
    
   
    
    public Level9 getLevel9Screen()
    {
    	
        return new Level9(this );
    }
    
    public OptionsScreen getOptionsScreen()
    {
    	
        return new OptionsScreen(this );
    }
    
    public Level1 getLevel1Screen()
    {
    	
    	 return new Level1(this);
    }
    
    public LevelTown1 getLevelTown1Screen()
    {
    	
    	 return new LevelTown1(this);
    }
    
    public MenuScreen getMenuScreen()
    {
    	
    	 return new MenuScreen(this);
    }
    
    public Level2 getLevel2Screen()
    {
    	
    	 return new Level2(this);
    }
    
    public Level3 getLevel3Screen()
    {
    	
    	 return new Level3(this);
    }
    
    public Level8 getLevel8Screen()
    {
    	
    	 return new Level8(this);
    }
    
    public Level4 getLevel4Screen()
    {
    	 return new Level4(this);
    }
    
    public Level5 getLevel5Screen()
    {
    	
    	 return new Level5(this);
    }
    
    public LoadingScreen getLoadingScreen()
    {
    	
    	 return new LoadingScreen(this);
    }
    
    public LoadingTownScreen getLoadingTownScreen()
    {
    	
    	 return new LoadingTownScreen(this);
    }

	@Override
	public void dispose() {
		Gdx.app.log(PlatformGame.LOG, "Disposing Game");
		super.dispose();
		
		 manager.clear();

	}

	@Override
	public void render() {
		super.render();
		accum += Gdx.graphics.getDeltaTime();
		while (accum >= STEP) {
			accum -= STEP;

		}

		sb.setProjectionMatrix(hudCam.combined);
	}

	public SpriteBatch getSpriteBatch() {
		return sb;
	}

	public BoundedCamera getCamera() {
		return cam;
	}

	public OrthographicCamera getHUDCamera() {
		return hudCam;
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
	}

	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
		Gdx.app.log(PlatformGame.LOG, "Setting screen: "
				+ screen.getClass().getSimpleName());
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
		Gdx.app.log(PlatformGame.LOG, "Resuming game");
	}
}
