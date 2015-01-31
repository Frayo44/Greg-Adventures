package com.platformgame.utils;

public interface ActionResolver {
	public void bootstrap();

	public void showScoreloop();

	public void submitScore(int mode, int score);

	public void refreshScores();
}