package com.platform.game;

import com.platforngame.handlers.ActionResolver2;

public class ActionResolverDesktop2 implements ActionResolver2 {	
	boolean signedInStateGPGS = false;

	@Override
	public boolean getSignedInGPGS2() {
		return signedInStateGPGS;
	}

	@Override
	public void loginGPGS2() {
		System.out.println("loginGPGS");
		signedInStateGPGS = true;
	}

	@Override
	public void submitScoreGPGS2(int score) {
		System.out.println("submitScoreGPGS " + score);
	}

	@Override
	public void unlockAchievementGPGS2(String achievementId) {
		System.out.println("unlockAchievement " + achievementId);
	}

	@Override
	public void getLeaderboardGPGS2() {
		System.out.println("getLeaderboardGPGS");
	}

	@Override
	public void getAchievementsGPGS2() {
		System.out.println("getAchievementsGPGS");
	}
}