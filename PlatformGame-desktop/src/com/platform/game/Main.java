package com.platform.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.platforngame.handlers.IActivityRequestHandler;

public class Main implements IActivityRequestHandler {
	
	private static Main application;
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "PlatformGame";
		// MOTOROLA RAZR
		cfg.width = 960;
		cfg.height = 540;

		// NEXUS 5
		// cfg.width = 1920;
		// cfg.height = 1080;

		new LwjglApplication(new PlatformGame(application, new ActionResolverDesktop() , new ActionResolverDesktop2(), new ActioResolverDesktop()), cfg);
	}
	
	@Override
	public void showAds(boolean show) {
		// TODO Auto-generated method stub

	}
}
