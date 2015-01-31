package com.platformgame.screens;



import java.util.Locale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.platform.game.PlatformGame;

public class OptionsScreen extends AbstractScreen {
	private Label volumeValue;

	public OptionsScreen(PlatformGame game) {
		super(game);
	}

	@Override
	public void show() {
		super.show();
		
		InputProcessor backProcessor = new InputAdapter() {
			 @Override
			 public boolean keyDown(int keycode) {
			
			 if ((keycode == Keys.ESCAPE) || (keycode == Keys.BACK)){
				 game.setScreen(game.getMenuScreen());
			 }
				 
			 return false;
			 }
			 };

			 InputMultiplexer multiplexer = new InputMultiplexer(stage,
			 backProcessor);
			 Gdx.input.setInputProcessor(multiplexer);
			 Gdx.input.setCatchBackKey(true);

		Label label4 = new Label("", getSkin(), "big-font");
		// retrieve the default table actor
		Table table = super.getTable();
		table.defaults().spaceBottom(10);
		table.columnDefaults(0).padLeft(50).padBottom(0);
		table.add(label4).colspan(3);

		// create the labels widgets
		final CheckBox soundEffectsCheckbox = new CheckBox("", getSkin());
		soundEffectsCheckbox.setSize(20, 20);
		soundEffectsCheckbox.setChecked(game.getPreferencesManager()
				.isSoundEnabled());
		soundEffectsCheckbox.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				boolean enabled = soundEffectsCheckbox.isChecked();
				game.getPreferencesManager().setSoundEnabled(enabled);
				game.getSoundManager().setEnabled(enabled);
			//	game.getSoundManager().play(TyrianSound.BOUNCE);
			}
		});
		table.row();

		Label label = new Label("                        ", getSkin(), "big-font");
		Label label2 = new Label("                ", getSkin(), "big-font");
		Label label3 = new Label("                 ", getSkin(), "big-font");
		label.setFontScale(0.7f);
		label2.setFontScale(0.7f);
		label3.setFontScale(0.7f);
		//label.setFontScale(0.9f);
		table.add(label);// "Sound Effects" );
		table.add(soundEffectsCheckbox).colspan(2).left();

		final CheckBox musicCheckbox = new CheckBox("", getSkin());
		musicCheckbox.setChecked(game.getPreferencesManager().isMusicEnabled());
		musicCheckbox.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				boolean enabled = musicCheckbox.isChecked();
				game.getPreferencesManager().setMusicEnabled(enabled);
				game.getMusicManager().setEnabled(enabled);
				//game.getSoundManager().play(TyrianSound.BOUNCE);

				// if the music is now enabled, start playing the menu music
				//if (enabled)
					//game.getMusicManager().play(TyrianMusic.MENU);
			}
		});
		table.row();
		table.add(label2);
		table.add(musicCheckbox).colspan(2).left();

		// range is [0.0,1.0]; step is 0.1f
		Slider volumeSlider = new Slider(0f, 1f, 0.1f, false, getSkin());
	
		volumeSlider.setValue(game.getPreferencesManager().getVolume());
		volumeSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				float value = ((Slider) actor).getValue();
				game.getPreferencesManager().setVolume(value);
				game.getMusicManager().setVolume(value);
				game.getSoundManager().setVolume(value);
				updateVolumeLabel();
			}
		});

		// create the volume label
		volumeValue = new Label("", getSkin(), "medium-font");
		updateVolumeLabel();

		// add the volume row
		table.row();
		table.add(label3);
		table.add(volumeSlider);
		table.add(volumeValue).width(80);
		table.padBottom(-35);
		// register the back button
		TextButton backButton = new TextButton("Back", getSkin(),
				"big-text");

		backButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				//game.getSoundManager().play(TyrianSound.BOUNCE);
				game.setScreen(new MenuScreen(game));
			}
		});

		table.row();
		//table.add(backButton).size(50, 40).colspan(3);

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		getBatch().begin();
		getBatch().draw(new Texture(Gdx.files.internal("images/options.png")), 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		getBatch().end();
		stage.draw();
	}
	/**
	 * Updates the volume label next to the slider.
	 */
	private void updateVolumeLabel() {
		float volume = (game.getPreferencesManager().getVolume() * 100);
		volumeValue.setText(String.format(Locale.US, "%1.0f%%", volume));
	}
}
