package com.platforngame.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferencesManager
{
    // constants
    private static final String LEVEL_ONE_STARS = "levelone.stars";
    private static final String LEVEL_ONE_HIGHSCORE = "levelone.highscore";
    private static final String LEVEL_TWO_STARS = "leveltwo.stars";
    private static final String LEVEL_TWO_HIGHSCORE = "leveltwo.highscore";
    private static final String LEVEL_THREE_STARS = "levelthree.stars";
    private static final String LEVEL_THREE_HIGHSCORE = "levelthree.highscore";
    private static final String LEVEL_FOUR_STARS = "leveltfour.stars";
    private static final String LEVEL_FOUR_HIGHSCORE = "levelfour.highscore";
    private static final String LEVEL_FIVE_STARS = "levelfive.stars";
    private static final String LEVEL_FIVE_HIGHSCORE = "levelfive.highscore";
    private static final String LEVEL_SIX_STARS = "levelsix.stars";
    private static final String LEVEL_SIX_HIGHSCORE = "levelsix.highscore";
    private static final String LEVEL_SEVEN_STARS = "levelseven.stars";
    private static final String LEVEL_SEVEN_HIGHSCORE = "levelseven.highscore";
    private static final String LEVEL_EIGHT_STARS = "leveleight.stars";
    private static final String LEVEL_EIGHT_HIGHSCORE = "leveleight.highscore";
    private static final String LEVEL_NINE_STARS = "levelnine.stars";
    private static final String LEVEL_NINE_HIGHSCORE = "levelnine.highscore";
    private static final String LEVEL_TEN_STARS = "levelten.stars";
    private static final String LEVEL_TEN_HIGHSCORE = "levelten.highscore";
    private static final String LEVEL_ELEVEN_STARS = "leveleleven.stars";
    private static final String LEVEL_ELEVEN_HIGHSCORE = "leveleleven.highscore";
    private static final String LEVEL_TWELVE_STARS = "leveltwelve.stars";
    private static final String LEVEL_TWELVE_HIGHSCORE = "leveltwelve.highscore";
    
    
    
    private static final String WORLD_ONE_STARS = "WORLDone.stars";
    private static final String WORLD_ONE_HIGHSCORE = "WORLDone.highscore";
    private static final String WORLD_TWO_STARS = "WORLDtwo.stars";
    private static final String WORLD_TWO_HIGHSCORE = "WORLDtwo.highscore";
    private static final String WORLD_THREE_STARS = "WORLDthree.stars";
    private static final String WORLD_THREE_HIGHSCORE = "WORLDthree.highscore";
    private static final String WORLD_FOUR_STARS = "WORLDtfour.stars";
    private static final String WORLD_FOUR_HIGHSCORE = "WORLDfour.highscore";
    private static final String WORLD_FIVE_STARS = "WORLDfive.stars";
    private static final String WORLD_FIVE_HIGHSCORE = "WORLDfive.highscore";
    private static final String WORLD_SIX_STARS = "WORLDsix.stars";
    private static final String WORLD_SIX_HIGHSCORE = "WORLDsix.highscore";
    private static final String WORLD_SEVEN_STARS = "WORLDseven.stars";
    private static final String WORLD_SEVEN_HIGHSCORE = "WORLDseven.highscore";
    private static final String WORLD_EIGHT_STARS = "WORLDeight.stars";
    private static final String WORLD_EIGHT_HIGHSCORE = "WORLDeight.highscore";
    private static final String WORLD_NINE_STARS = "WORLDnine.stars";
    private static final String WORLD_NINE_HIGHSCORE = "WORLDnine.highscore";
    private static final String WORLD_TEN_STARS = "WORLDten.stars";
    private static final String WORLD_TEN_HIGHSCORE = "WORLDten.highscore";
    private static final String WORLD_ELEVEN_STARS = "WORLDeleven.stars";
    private static final String WORLD_ELEVEN_HIGHSCORE = "WORLDeleven.highscore";
    private static final String WORLD_TWELVE_STARS = "WORLDtwelve.stars";
    private static final String WORLD_TWELVE_HIGHSCORE = "WORLDtwelve.highscore";
    
    
    private static final String ICE_ONE_STARS = "ICEone.stars";
    private static final String ICE_ONE_HIGHSCORE = "ICEone.highscore";
    private static final String ICE_TWO_STARS = "ICEtwo.stars";
    private static final String ICE_TWO_HIGHSCORE = "ICEtwo.highscore";
    private static final String ICE_THREE_STARS = "ICEthree.stars";
    private static final String ICE_THREE_HIGHSCORE = "ICEthree.highscore";
    private static final String ICE_FOUR_STARS = "ICEtfour.stars";
    private static final String ICE_FOUR_HIGHSCORE = "ICEfour.highscore";
    private static final String ICE_FIVE_STARS = "ICEfive.stars";
    private static final String ICE_FIVE_HIGHSCORE = "ICEfive.highscore";
    private static final String ICE_SIX_STARS = "ICEsix.stars";
    private static final String ICE_SIX_HIGHSCORE = "ICEsix.highscore";
    private static final String ICE_SEVEN_STARS = "ICEseven.stars";
    private static final String ICE_SEVEN_HIGHSCORE = "ICEseven.highscore";
    private static final String ICE_EIGHT_STARS = "ICEeight.stars";
    private static final String ICE_EIGHT_HIGHSCORE = "ICEeight.highscore";
    private static final String ICE_NINE_STARS = "ICEnine.stars";
    private static final String ICE_NINE_HIGHSCORE = "ICEnine.highscore";
    private static final String ICE_TEN_STARS = "ICEten.stars";
    private static final String ICE_TEN_HIGHSCORE = "ICEten.highscore";
    private static final String ICE_ELEVEN_STARS = "ICEeleven.stars";
    private static final String ICE_ELEVEN_HIGHSCORE = "ICEeleven.highscore";
    private static final String ICE_TWELVE_STARS = "ICEtwelve.stars";
    private static final String ICE_TWELVE_HIGHSCORE = "ICEtwelve.highscore";
    
    
    
    private static final String EVER_SCORE = "summary.score";
 //   private static final String LEVEL_SIX_HIGHSCORE = "levelsix.highscore";
    
    // constants
    private static final String PREF_VOLUME = "volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    private static final String PREFS_NAME = "MY.NAME";
    
    
    private static final String TOTAL_RECORDS_HIGHSCORE = "highscores.records";
    
    private Preferences preferences;

    public PreferencesManager()
    {
    }

    protected Preferences getPrefs()
    {
    	if(preferences==null){
            preferences = Gdx.app.getPreferences(PREFS_NAME);
         }
      return preferences;
    }
    
    public int getTotalRecords()
    {
        return getPrefs().getInteger( TOTAL_RECORDS_HIGHSCORE, 0 );
    }

    public void setTotalRecords( int numStars )
    {
        getPrefs().putInteger( TOTAL_RECORDS_HIGHSCORE, numStars );
        getPrefs().flush();
    }

    
    public void setSummaryOfScore( int highscore )
    {
        getPrefs().putInteger( EVER_SCORE, highscore );
        getPrefs().flush();
    }
    
    public int getSummaryOfScore()
    {
        return getPrefs().getInteger( EVER_SCORE, 0 );
    }
    
    public void setLevelTwelveHighScore( int highscore )
    {
        getPrefs().putInteger( LEVEL_TWELVE_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getLevelTwelveHighScore()
    {
        return getPrefs().getInteger( LEVEL_TWELVE_HIGHSCORE, 0 );
    }
    
    public int getLevelTwelveStars()
    {
        return getPrefs().getInteger( LEVEL_TWELVE_STARS, 0 );
    }

    public void setLevelTwelveStars( int numStars )
    {
        getPrefs().putInteger( LEVEL_TWELVE_STARS, numStars );
        getPrefs().flush();
    }
    
    public void setLevelElevenHighScore( int highscore )
    {
        getPrefs().putInteger( LEVEL_ELEVEN_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getLevelElevenHighScore()
    {
        return getPrefs().getInteger( LEVEL_ELEVEN_HIGHSCORE, 0 );
    }
    
    public int getLevelElevenStars()
    {
        return getPrefs().getInteger( LEVEL_ELEVEN_STARS, 0 );
    }

    public void setLevelElevenStars( int numStars )
    {
        getPrefs().putInteger( LEVEL_ELEVEN_STARS, numStars );
        getPrefs().flush();
    }
    
    public void setLevelTenHighScore( int highscore )
    {
        getPrefs().putInteger( LEVEL_TEN_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getLevelTenHighScore()
    {
        return getPrefs().getInteger( LEVEL_TEN_HIGHSCORE, 0 );
    }
    
    public int getLevelTenStars()
    {
        return getPrefs().getInteger( LEVEL_TEN_STARS, 0 );
    }

    public void setLevelTenStars( int numStars )
    {
        getPrefs().putInteger( LEVEL_TEN_STARS, numStars );
        getPrefs().flush();
    }
    
    public void setLevelNineHighScore( int highscore )
    {
        getPrefs().putInteger( LEVEL_NINE_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getLevelNineHighScore()
    {
        return getPrefs().getInteger( LEVEL_NINE_HIGHSCORE, 0 );
    }
    
    public int getLevelNineStars()
    {
        return getPrefs().getInteger( LEVEL_NINE_STARS, 0 );
    }

    public void setLevelNineStars( int numStars )
    {
        getPrefs().putInteger( LEVEL_NINE_STARS, numStars );
        getPrefs().flush();
    }
   
    
    public void setLevelEightHighScore( int highscore )
    {
        getPrefs().putInteger( LEVEL_EIGHT_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getLevelEightHighScore()
    {
        return getPrefs().getInteger( LEVEL_EIGHT_HIGHSCORE, 0 );
    }
    
    public int getLevelEightStars()
    {
        return getPrefs().getInteger( LEVEL_EIGHT_STARS, 0 );
    }

    public void setLevelEightStars( int numStars )
    {
        getPrefs().putInteger( LEVEL_EIGHT_STARS, numStars );
        getPrefs().flush();
    }
   
    
    public void setLevelSevenHighScore( int highscore )
    {
        getPrefs().putInteger( LEVEL_SEVEN_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getLevelSevenHighScore()
    {
        return getPrefs().getInteger( LEVEL_SEVEN_HIGHSCORE, 0 );
    }
    
    public int getLevelSevenStars()
    {
        return getPrefs().getInteger( LEVEL_SEVEN_STARS, 0 );
    }

    public void setLevelSevenStars( int numStars )
    {
        getPrefs().putInteger( LEVEL_SEVEN_STARS, numStars );
        getPrefs().flush();
    }
   
    public void setLevelSixHighScore( int highscore )
    {
        getPrefs().putInteger( LEVEL_SIX_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getLevelSixHighScore()
    {
        return getPrefs().getInteger( LEVEL_SIX_HIGHSCORE, 0 );
    }
    
    public int getLevelSixStars()
    {
        return getPrefs().getInteger( LEVEL_SIX_STARS, 0 );
    }

    public void setLevelSixStars( int numStars )
    {
        getPrefs().putInteger( LEVEL_SIX_STARS, numStars );
        getPrefs().flush();
    }
    
    
    
    
    public int getLevelOneStars()
    {
        return getPrefs().getInteger( LEVEL_ONE_STARS, 0 );
    }

    public void setLevelOneStars( int numStars )
    {
        getPrefs().putInteger( LEVEL_ONE_STARS, numStars );
        getPrefs().flush();
    }
    
    public void setLevelFourHighScore( int highscore )
    {
        getPrefs().putInteger( LEVEL_FOUR_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getLevelFourHighScore()
    {
        return getPrefs().getInteger( LEVEL_FOUR_HIGHSCORE, 0 );
    }
    
    public int getLevelFourStars()
    {
        return getPrefs().getInteger( LEVEL_FOUR_STARS, 0 );
    }

    public void setLevelFourStars( int numStars )
    {
        getPrefs().putInteger( LEVEL_FOUR_STARS, numStars );
        getPrefs().flush();
    }
    
    public int getLevelFiveHighScore()
    {
        return getPrefs().getInteger( LEVEL_FIVE_HIGHSCORE, 0 );
    }
    
    public int getLevelFiveStars()
    {
        return getPrefs().getInteger( LEVEL_FIVE_STARS, 0 );
    }

    public void setLevelFiveStars( int numStars )
    {
        getPrefs().putInteger( LEVEL_FIVE_STARS, numStars );
        getPrefs().flush();
    }
    
    public void setLevelFiveHighScore( int highscore )
    {
        getPrefs().putInteger( LEVEL_FIVE_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getLevelThreeStars()
    {
        return getPrefs().getInteger( LEVEL_THREE_STARS, 0 );
    }

    public void setLevelThreeStars( int numStars )
    {
        getPrefs().putInteger( LEVEL_THREE_STARS, numStars );
        getPrefs().flush();
    }
    
    public int getLevelThreeHighScore()
    {
        return getPrefs().getInteger( LEVEL_THREE_HIGHSCORE, 0 );
    }
    
    public void setLevelThreeHighScore( int highscore )
    {
        getPrefs().putInteger( LEVEL_THREE_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    
    public int getLevelOneHighScore()
    {
        return getPrefs().getInteger( LEVEL_ONE_HIGHSCORE, 0 );
    }
    
    public int getLevelTwoHighScore()
    {
        return getPrefs().getInteger( LEVEL_TWO_HIGHSCORE, 0 );
    }

    public void setLevelOneHighScore( int highscore )
    {
        getPrefs().putInteger( LEVEL_ONE_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public void setLevelTwoHighScore( int highscore )
    {
        getPrefs().putInteger( LEVEL_TWO_HIGHSCORE, highscore );
        getPrefs().flush();
    }


    public int getLevelTwoStars()
    {
        return getPrefs().getInteger( LEVEL_TWO_STARS, 0 );
    }
    
    public void setLevelTwoStars( int numStars )
    {
        getPrefs().putInteger( LEVEL_TWO_STARS, numStars );
        getPrefs().flush();
    }
    //FORM GERE
    
    public void setWORLDTwelveHighScore( int highscore )
    {
        getPrefs().putInteger( WORLD_TWELVE_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getWORLDTwelveHighScore()
    {
        return getPrefs().getInteger( WORLD_TWELVE_HIGHSCORE, 0 );
    }
    
    public int getWORLDTwelveStars()
    {
        return getPrefs().getInteger( WORLD_TWELVE_STARS, 0 );
    }

    public void setWORLDTwelveStars( int numStars )
    {
        getPrefs().putInteger( WORLD_TWELVE_STARS, numStars );
        getPrefs().flush();
    }
    
    public void setWORLDElevenHighScore( int highscore )
    {
        getPrefs().putInteger( WORLD_ELEVEN_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getWORLDElevenHighScore()
    {
        return getPrefs().getInteger( WORLD_ELEVEN_HIGHSCORE, 0 );
    }
    
    public int getWORLDElevenStars()
    {
        return getPrefs().getInteger( WORLD_ELEVEN_STARS, 0 );
    }

    public void setWORLDElevenStars( int numStars )
    {
        getPrefs().putInteger( WORLD_ELEVEN_STARS, numStars );
        getPrefs().flush();
    }
    
    public void setWORLDTenHighScore( int highscore )
    {
        getPrefs().putInteger( WORLD_TEN_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getWORLDTenHighScore()
    {
        return getPrefs().getInteger( WORLD_TEN_HIGHSCORE, 0 );
    }
    
    public int getWORLDTenStars()
    {
        return getPrefs().getInteger( WORLD_TEN_STARS, 0 );
    }

    public void setWORLDTenStars( int numStars )
    {
        getPrefs().putInteger( WORLD_TEN_STARS, numStars );
        getPrefs().flush();
    }
    
    public void setWORLDNineHighScore( int highscore )
    {
        getPrefs().putInteger( WORLD_NINE_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getWORLDNineHighScore()
    {
        return getPrefs().getInteger( WORLD_NINE_HIGHSCORE, 0 );
    }
    
    public int getWORLDNineStars()
    {
        return getPrefs().getInteger( WORLD_NINE_STARS, 0 );
    }

    public void setWORLDNineStars( int numStars )
    {
        getPrefs().putInteger( WORLD_NINE_STARS, numStars );
        getPrefs().flush();
    }
   
    
    public void setWORLDEightHighScore( int highscore )
    {
        getPrefs().putInteger( WORLD_EIGHT_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getWORLDEightHighScore()
    {
        return getPrefs().getInteger( WORLD_EIGHT_HIGHSCORE, 0 );
    }
    
    public int getWORLDEightStars()
    {
        return getPrefs().getInteger( WORLD_EIGHT_STARS, 0 );
    }

    public void setWORLDEightStars( int numStars )
    {
        getPrefs().putInteger( WORLD_EIGHT_STARS, numStars );
        getPrefs().flush();
    }
   
    
    public void setWORLDSevenHighScore( int highscore )
    {
        getPrefs().putInteger( WORLD_SEVEN_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getWORLDSevenHighScore()
    {
        return getPrefs().getInteger( WORLD_SEVEN_HIGHSCORE, 0 );
    }
    
    public int getWORLDSevenStars()
    {
        return getPrefs().getInteger( WORLD_SEVEN_STARS, 0 );
    }

    public void setWORLDSevenStars( int numStars )
    {
        getPrefs().putInteger( WORLD_SEVEN_STARS, numStars );
        getPrefs().flush();
    }
   
    public void setWORLDSixHighScore( int highscore )
    {
        getPrefs().putInteger( WORLD_SIX_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getWORLDSixHighScore()
    {
        return getPrefs().getInteger( WORLD_SIX_HIGHSCORE, 0 );
    }
    
    public int getWORLDSixStars()
    {
        return getPrefs().getInteger( WORLD_SIX_STARS, 0 );
    }

    public void setWORLDSixStars( int numStars )
    {
        getPrefs().putInteger( WORLD_SIX_STARS, numStars );
        getPrefs().flush();
    }
    
    
    
    
    public int getWORLDOneStars()
    {
        return getPrefs().getInteger( WORLD_ONE_STARS, 0 );
    }

    public void setWORLDOneStars( int numStars )
    {
        getPrefs().putInteger( WORLD_ONE_STARS, numStars );
        getPrefs().flush();
    }
    
    public void setWORLDFourHighScore( int highscore )
    {
        getPrefs().putInteger( WORLD_FOUR_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getWORLDFourHighScore()
    {
        return getPrefs().getInteger( WORLD_FOUR_HIGHSCORE, 0 );
    }
    
    public int getWORLDFourStars()
    {
        return getPrefs().getInteger( WORLD_FOUR_STARS, 0 );
    }

    public void setWORLDFourStars( int numStars )
    {
        getPrefs().putInteger( WORLD_FOUR_STARS, numStars );
        getPrefs().flush();
    }
    
    public int getWORLDFiveHighScore()
    {
        return getPrefs().getInteger( WORLD_FIVE_HIGHSCORE, 0 );
    }
    
    public int getWORLDFiveStars()
    {
        return getPrefs().getInteger( WORLD_FIVE_STARS, 0 );
    }

    public void setWORLDFiveStars( int numStars )
    {
        getPrefs().putInteger( WORLD_FIVE_STARS, numStars );
        getPrefs().flush();
    }
    
    public void setWORLDFiveHighScore( int highscore )
    {
        getPrefs().putInteger( WORLD_FIVE_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getWORLDThreeStars()
    {
        return getPrefs().getInteger( WORLD_THREE_STARS, 0 );
    }

    public void setWORLDThreeStars( int numStars )
    {
        getPrefs().putInteger( WORLD_THREE_STARS, numStars );
        getPrefs().flush();
    }
    
    public int getWORLDThreeHighScore()
    {
        return getPrefs().getInteger( WORLD_THREE_HIGHSCORE, 0 );
    }
    
    public void setWORLDThreeHighScore( int highscore )
    {
        getPrefs().putInteger( WORLD_THREE_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    
    public int getWORLDOneHighScore()
    {
        return getPrefs().getInteger( WORLD_ONE_HIGHSCORE, 0 );
    }
    
    public int getWORLDTwoHighScore()
    {
        return getPrefs().getInteger( WORLD_TWO_HIGHSCORE, 0 );
    }

    public void setWORLDOneHighScore( int highscore )
    {
        getPrefs().putInteger( WORLD_ONE_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public void setWORLDTwoHighScore( int highscore )
    {
        getPrefs().putInteger( WORLD_TWO_HIGHSCORE, highscore );
        getPrefs().flush();
    }


    public int getWORLDTwoStars()
    {
        return getPrefs().getInteger( WORLD_TWO_STARS, 0 );
    }
    
    public void setWORLDTwoStars( int numStars )
    {
        getPrefs().putInteger( WORLD_TWO_STARS, numStars );
        getPrefs().flush();
    }
    //END
    
    
    //FORM HERE // ICE
    
//FORM GERE
    
    public void setICETwelveHighScore( int highscore )
    {
        getPrefs().putInteger( ICE_TWELVE_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getICETwelveHighScore()
    {
        return getPrefs().getInteger( ICE_TWELVE_HIGHSCORE, 0 );
    }
    
    public int getICETwelveStars()
    {
        return getPrefs().getInteger( ICE_TWELVE_STARS, 0 );
    }

    public void setICETwelveStars( int numStars )
    {
        getPrefs().putInteger( ICE_TWELVE_STARS, numStars );
        getPrefs().flush();
    }
    
    public void setICEElevenHighScore( int highscore )
    {
        getPrefs().putInteger( ICE_ELEVEN_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getICEElevenHighScore()
    {
        return getPrefs().getInteger( ICE_ELEVEN_HIGHSCORE, 0 );
    }
    
    public int getICEElevenStars()
    {
        return getPrefs().getInteger( ICE_ELEVEN_STARS, 0 );
    }

    public void setICEElevenStars( int numStars )
    {
        getPrefs().putInteger( ICE_ELEVEN_STARS, numStars );
        getPrefs().flush();
    }
    
    public void setICETenHighScore( int highscore )
    {
        getPrefs().putInteger( ICE_TEN_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getICETenHighScore()
    {
        return getPrefs().getInteger( ICE_TEN_HIGHSCORE, 0 );
    }
    
    public int getICETenStars()
    {
        return getPrefs().getInteger( ICE_TEN_STARS, 0 );
    }

    public void setICETenStars( int numStars )
    {
        getPrefs().putInteger( ICE_TEN_STARS, numStars );
        getPrefs().flush();
    }
    
    public void setICENineHighScore( int highscore )
    {
        getPrefs().putInteger( ICE_NINE_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getICENineHighScore()
    {
        return getPrefs().getInteger( ICE_NINE_HIGHSCORE, 0 );
    }
    
    public int getICENineStars()
    {
        return getPrefs().getInteger( ICE_NINE_STARS, 0 );
    }

    public void setICENineStars( int numStars )
    {
        getPrefs().putInteger( ICE_NINE_STARS, numStars );
        getPrefs().flush();
    }
   
    
    public void setICEEightHighScore( int highscore )
    {
        getPrefs().putInteger( ICE_EIGHT_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getICEEightHighScore()
    {
        return getPrefs().getInteger( ICE_EIGHT_HIGHSCORE, 0 );
    }
    
    public int getICEEightStars()
    {
        return getPrefs().getInteger( ICE_EIGHT_STARS, 0 );
    }

    public void setICEEightStars( int numStars )
    {
        getPrefs().putInteger( ICE_EIGHT_STARS, numStars );
        getPrefs().flush();
    }
   
    
    public void setICESevenHighScore( int highscore )
    {
        getPrefs().putInteger( ICE_SEVEN_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getICESevenHighScore()
    {
        return getPrefs().getInteger( ICE_SEVEN_HIGHSCORE, 0 );
    }
    
    public int getICESevenStars()
    {
        return getPrefs().getInteger( ICE_SEVEN_STARS, 0 );
    }

    public void setICESevenStars( int numStars )
    {
        getPrefs().putInteger( ICE_SEVEN_STARS, numStars );
        getPrefs().flush();
    }
   
    public void setICESixHighScore( int highscore )
    {
        getPrefs().putInteger( ICE_SIX_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getICESixHighScore()
    {
        return getPrefs().getInteger( ICE_SIX_HIGHSCORE, 0 );
    }
    
    public int getICESixStars()
    {
        return getPrefs().getInteger( ICE_SIX_STARS, 0 );
    }

    public void setICESixStars( int numStars )
    {
        getPrefs().putInteger( ICE_SIX_STARS, numStars );
        getPrefs().flush();
    }
    
    
    
    
    public int getICEOneStars()
    {
        return getPrefs().getInteger( ICE_ONE_STARS, 0 );
    }

    public void setICEOneStars( int numStars )
    {
        getPrefs().putInteger( ICE_ONE_STARS, numStars );
        getPrefs().flush();
    }
    
    public void setICEFourHighScore( int highscore )
    {
        getPrefs().putInteger( ICE_FOUR_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getICEFourHighScore()
    {
        return getPrefs().getInteger( ICE_FOUR_HIGHSCORE, 0 );
    }
    
    public int getICEFourStars()
    {
        return getPrefs().getInteger( ICE_FOUR_STARS, 0 );
    }

    public void setICEFourStars( int numStars )
    {
        getPrefs().putInteger( ICE_FOUR_STARS, numStars );
        getPrefs().flush();
    }
    
    public int getICEFiveHighScore()
    {
        return getPrefs().getInteger( ICE_FIVE_HIGHSCORE, 0 );
    }
    
    public int getICEFiveStars()
    {
        return getPrefs().getInteger( ICE_FIVE_STARS, 0 );
    }

    public void setICEFiveStars( int numStars )
    {
        getPrefs().putInteger( ICE_FIVE_STARS, numStars );
        getPrefs().flush();
    }
    
    public void setICEFiveHighScore( int highscore )
    {
        getPrefs().putInteger( ICE_FIVE_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public int getICEThreeStars()
    {
        return getPrefs().getInteger( ICE_THREE_STARS, 0 );
    }

    public void setICEThreeStars( int numStars )
    {
        getPrefs().putInteger( ICE_THREE_STARS, numStars );
        getPrefs().flush();
    }
    
    public int getICEThreeHighScore()
    {
        return getPrefs().getInteger( ICE_THREE_HIGHSCORE, 0 );
    }
    
    public void setICEThreeHighScore( int highscore )
    {
        getPrefs().putInteger( ICE_THREE_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    
    public int getICEOneHighScore()
    {
        return getPrefs().getInteger( ICE_ONE_HIGHSCORE, 0 );
    }
    
    public int getICETwoHighScore()
    {
        return getPrefs().getInteger( ICE_TWO_HIGHSCORE, 0 );
    }

    public void setICEOneHighScore( int highscore )
    {
        getPrefs().putInteger( ICE_ONE_HIGHSCORE, highscore );
        getPrefs().flush();
    }
    
    public void setICETwoHighScore( int highscore )
    {
        getPrefs().putInteger( ICE_TWO_HIGHSCORE, highscore );
        getPrefs().flush();
    }


    public int getICETwoStars()
    {
        return getPrefs().getInteger( ICE_TWO_STARS, 0 );
    }
    
    public void setICETwoStars( int numStars )
    {
        getPrefs().putInteger( ICE_TWO_STARS, numStars );
        getPrefs().flush();
    }
    
    //END ICE

 
    public boolean isSoundEnabled()
    {
        return getPrefs().getBoolean( PREF_SOUND_ENABLED, true );
    }

    public void setSoundEnabled(
        boolean soundEffectsEnabled )
    {
        getPrefs().putBoolean( PREF_SOUND_ENABLED, soundEffectsEnabled );
        getPrefs().flush();
    }

    public boolean isMusicEnabled()
    {
        return getPrefs().getBoolean( PREF_MUSIC_ENABLED, true );
    }

    public void setMusicEnabled(
        boolean musicEnabled )
    {
        getPrefs().putBoolean( PREF_MUSIC_ENABLED, musicEnabled );
        getPrefs().flush();
    }

    public float getVolume()
    {
        return getPrefs().getFloat( PREF_VOLUME, 0.5f );
    }

    public void setVolume(
        float volume )
    {
        getPrefs().putFloat( PREF_VOLUME, volume );
        getPrefs().flush();
    }
}
