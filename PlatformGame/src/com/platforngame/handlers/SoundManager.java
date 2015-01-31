package com.platforngame.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.platformgame.utils.LRUCache2;
import com.platformgame.utils.LRUCache2.CacheEntryRemovedListener;
import com.platforngame.handlers.SoundManager.TyrianSound;

public class SoundManager
implements
    CacheEntryRemovedListener<TyrianSound,Sound>,
    Disposable
{
/**
 * The available sound files.
 */
public enum TyrianSound
{
    HIT( "sound/hit.wav" ),
    CRYSTAL( "sound/crystal.wav" );
    
    private final String fileName;

    private TyrianSound(
        String fileName )
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
}

/**
 * The volume to be set on the sound.
 */
private float volume = 0.5f;

/**
 * Whether the sound is enabled.
 */
private boolean enabled = true;

/**
 * The sound cache.
 */
private final LRUCache2<TyrianSound,Sound> soundCache;

/**
 * Creates the sound manager.
 */
public SoundManager()
{ 
    soundCache = new LRUCache2<SoundManager.TyrianSound,Sound>( 10 );
    soundCache.setEntryRemovedListener( this );
}

/**
 * 
 * Plays the specified sound.
 */
public void play(
    TyrianSound sound )
{
    // check if the sound is enabled
    if( ! enabled ) return;

    // try and get the sound from the cache
    Sound soundToPlay = soundCache.get( sound );
    if( soundToPlay == null ) {
        FileHandle soundFile = Gdx.files.internal( sound.getFileName() );
        soundToPlay = Gdx.audio.newSound( soundFile );
        soundCache.add( sound, soundToPlay );
    }

    
    soundToPlay.play( volume );
}

/**
 * Sets the sound volume which must be inside the range [0,1].
 */
public void setVolume(
    float volume )
{
   
    // check and set the new volume
    if( volume < 0 || volume > 1f ) {
        throw new IllegalArgumentException( "The volume must be inside the range: [0,1]" );
    }
    this.volume = volume;
}

/**
 * Enables or disabled the sound.
 */
public void setEnabled(
    boolean enabled )
{
    this.enabled = enabled;
}

// EntryRemovedListener implementation

@Override
public void notifyEntryRemoved(
    TyrianSound key,
    Sound value )
{
   // Gdx.app.log( GameLevel.LOG, "Disposing sound: " + key.name() );
    value.dispose();
}

/**
 * Disposes the sound manager.
 */
public void dispose()
{
    //Gdx.app.log( GameLevel.LOG, "Disposing sound manager" );
    for( Sound sound : soundCache.retrieveAll() ) {
        sound.stop();
        sound.dispose();
    }
}
}


