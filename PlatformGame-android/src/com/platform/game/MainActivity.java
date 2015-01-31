package com.platform.game;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;
import com.platforngame.handlers.ActioResolver;
import com.platforngame.handlers.ActionResolver;
import com.platforngame.handlers.ActionResolver2;
import com.platforngame.handlers.IActivityRequestHandler;


public class MainActivity extends AndroidApplication implements IActivityRequestHandler, GameHelperListener, ActionResolver, ActionResolver2, ActioResolver {
	/*
//	 * Handle the LeaderBoard
//	 */
	GameHelper gameHelper;
	
	
	/** instrial ads **/
	private static final String AD_UNIT_ID_INTERSTITIAL = "ca-app-pub-9470442411108307/6545887877";
	
	private static InterstitialAd interstitialAd;
	/*
	 * For ads, 
	 * Handels the ads in the side down corner
	 */
	public MainActivity(){
		
	}
	
	
	protected AdView adView;

    private final int SHOW_ADS = 1;
    private final int HIDE_ADS = 0;

    protected  Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case SHOW_ADS:
                {
                    adView.setVisibility(View.VISIBLE);
                    break;
                }
                case HIDE_ADS:
                {
                    adView.setVisibility(View.GONE);
                    break;
                }
            }
        }
    };
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.print("Constructor");
        gameHelper = new GameHelper(this, GameHelper.CLIENT_ALL);
       
        //for leaderboards
//        
//        mHelper = new GameHelper(this, GameHelper.CLIENT_ALL);
//        
//        GameHelperListener listener = new GameHelper.GameHelperListener() {
//            @Override
//            public void onSignInSucceeded() {
//                // handle sign-in succeess
//            }
//            @Override
//            public void onSignInFailed() {
//                // handle sign-in failure (e.g. show Sign In button)
//            }
//
//        };
//        mHelper.setup(listener);
        
        
 
        //Regulat configuration
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        
        // Create the layout
        RelativeLayout layout = new RelativeLayout(this);

        // Do the stuff that initialize() would do for you
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        // Create the libgdx View
        //View gameView = initializeForView(new GameLevel(this), cfg);
        View gameView = initializeForView(new PlatformGame(this, this, this, this), cfg);
        // Create and setup the AdMob view
        AdView adView = new AdView(this);
        adView.setAdUnitId("ca-app-pub-9470442411108307/3273937878");
        adView.setAdSize(AdSize.BANNER);
        
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(AD_UNIT_ID_INTERSTITIAL);
        interstitialAd.setAdListener(new AdListener() {
          @Override
          public void onAdLoaded() {
          //  Toast.makeText(getApplicationContext(), "Finished Loading Interstitial", Toast.LENGTH_SHORT).show();
          }
          @Override
          public void onAdClosed() {
           // Toast.makeText(getApplicationContext(), "Closed Interstitial", Toast.LENGTH_SHORT).show();
          }
        });
    
        // Add the libgdx view
        layout.addView(gameView);

        // Add the AdMob view
        RelativeLayout.LayoutParams adParams = 
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adParams.addRule(RelativeLayout.ALIGN_RIGHT);
      
        layout.addView(adView, adParams);
        
        System.out.print("Set Content View");
        setContentView(layout);
        
        gameHelper.setup(this);
        //not!@# :P
       // gameHelper.setup(this);
        
    }

    
    @Override
    public void showOrLoadInterstital() {
      try {
        runOnUiThread(new Runnable() {
          public void run() {
            if (interstitialAd.isLoaded()) {
              interstitialAd.show();
             // Toast.makeText(getApplicationContext(), "Showing Interstitial", Toast.LENGTH_SHORT).show();
           }
            else {
              AdRequest interstitialRequest = new AdRequest.Builder().build();
              interstitialAd.loadAd(interstitialRequest);
             // Toast.makeText(getApplicationContext(), "Loading Interstitial", Toast.LENGTH_SHORT).show();
           }
          }
        });
      } catch (Exception e) {
      }
    }
    
	@Override
	public void showAds(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_ADS : SHOW_ADS);
		
	}

	//for leaderboards

	
	@Override
	public void onStart(){
		super.onStart();
		gameHelper.onStart(this);
	}

	@Override
	public void onStop(){
		super.onStop();
		gameHelper.onStop();
	}

	@Override
	public void onActivityResult(int request, int response, Intent data) {
		super.onActivityResult(request, response, data);
		gameHelper.onActivityResult(request, response, data);
	}
	
	@Override
	public boolean getSignedInGPGS() {
		return gameHelper.isSignedIn();
	}

	@Override
	public void loginGPGS() {
		try {
			runOnUiThread(new Runnable(){
				public void run() {
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		} catch (final Exception ex) {
		}
	}

	@Override
	public void submitScoreGPGS(int score) {
		Games.Leaderboards.submitScore(gameHelper.getApiClient(), "CgkIrZWZm6ILEAIQBg", score);
		//gameHelper.getGamesClient().submitScore("CgkI6574wJUXEAIQBw", score);
	}
	
	@Override
	public void unlockAchievementGPGS(String achievementId) {
		Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);
	}
	
	@Override
	public void getLeaderboardGPGS() {
		//startActivityForResult(gameHelper.getGamesClient().getLeaderboardIntent("CgkI6574wJUXEAIQBw"), 100);
		startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), "CgkIrZWZm6ILEAIQBg"), 100);
	}

	@Override
	public void getAchievementsGPGS() {
		startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), 101);
		//startActivityForResult(gameHelper.getGamesClient().getAchievementsIntent(), 101);
	//	startActivityForResult(gameHelper.getGamesClient().getAchievementsIntent(), 101);
	}
	
	@Override
	public void onSignInFailed() {
	}

	@Override
	public void onSignInSucceeded() {
	}

	@Override
	public boolean getSignedInGPGS2() {
		return gameHelper.isSignedIn();
	}

	@Override
	public void loginGPGS2() {
		try {
			runOnUiThread(new Runnable(){
				public void run() {
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		} catch (final Exception ex) {
		}
	}

	@Override
	public void submitScoreGPGS2(int score) {
		Games.Leaderboards.submitScore(gameHelper.getApiClient(), "CgkIrZWZm6ILEAIQCQ", score);
		//gameHelper.getGamesClient().submitScore("CgkI6574wJUXEAIQBw", score);
	}
	
	@Override
	public void unlockAchievementGPGS2(String achievementId) {
		Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);
	}
	
	@Override
	public void getLeaderboardGPGS2() {
		//startActivityForResult(gameHelper.getGamesClient().getLeaderboardIntent("CgkI6574wJUXEAIQBw"), 100);
		startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), "CgkIrZWZm6ILEAIQCQ"), 100);
	}

	@Override
	public void getAchievementsGPGS2() {
		startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), 101);
		//startActivityForResult(gameHelper.getGamesClient().getAchievementsIntent(), 101);
	//	startActivityForResult(gameHelper.getGamesClient().getAchievementsIntent(), 101);
	}
	
	
}


