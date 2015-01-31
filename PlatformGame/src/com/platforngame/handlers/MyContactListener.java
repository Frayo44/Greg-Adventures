package com.platforngame.handlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.platform.game.PlatformGame;
import com.platformgame.entities.Player;
import com.platformgame.screens.ModesScreen;
import com.platforngame.handlers.SoundManager.TyrianSound;

public class MyContactListener implements ContactListener {

	private int numFootContacts;
	private Array<Body> bodiesToRemove;
	private Array<Body> enemiesToRemove;
	private Array<Body> bodiesToRemoveHeart;
	private Array<Body> bodiesToRemoveBoom;
	private Array<Body> bodiesToRemoveShields;
	public boolean isJoysticAvailable = true;
	public boolean isOnDelay = true;
	public boolean isOnGround;
	private boolean isGoToEnd;
	Player player;
	public static int numLifes;
	public int numLifesCurr;

	public MyContactListener() {
		super();
		// this.player = player;
		bodiesToRemove = new Array<Body>();
		enemiesToRemove = new Array<Body>();
		bodiesToRemoveHeart = new Array<Body>();
		bodiesToRemoveBoom = new Array<Body>();
		bodiesToRemoveShields = new Array<Body>();
		isGoToEnd = false;
		isJoysticAvailable = true;
		isOnDelay = true;
		
	
			numLifes = 5;
			
	}

	// called when two fixtures come to collide
	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		if (fa == null || fb == null)
			return;

		if (fa.getUserData() != null && fa.getUserData().equals("foot")) {
			numFootContacts++;
			isOnGround = true;
			//isJoysticAvailable = true;

		}

		if (fb.getUserData() != null && fb.getUserData().equals("foot")) {
			numFootContacts++;
			isOnGround = true;
			//isJoysticAvailable = true;

		}

		if (fa.getUserData() != null && fb.getUserData() != null && fa.getUserData().equals("player")
				&& fb.getUserData().equals("head")) {
			fa.getBody().setLinearVelocity(0, 3);
		}

		if (fb.getUserData() != null && fa.getUserData() != null && fb.getUserData().equals("player")
				&& fa.getUserData().equals("head")) {
			fb.getBody().setLinearVelocity(0, 3);
		}

		if (fa.getUserData() != null && fa.getUserData() != null && fa.getUserData().equals("theend")) {
			System.out.println("TheEnd");
			isGoToEnd = true;

		}

		if (fb.getUserData() != null && fb.getUserData().equals("theend")) {
			System.out.println(numFootContacts);
			isGoToEnd = true;

		}

		if (fa.getUserData() != null && fa.getUserData().equals("crystal")
				&& fb.getUserData() != null && fb.getUserData().equals("foot")) {
			// remove crystal
			bodiesToRemove.add(fa.getBody());
			PlatformGame.getSoundManager().play(TyrianSound.CRYSTAL);
		}

		if (fb.getUserData() != null && fb.getUserData().equals("crystal") 
				&& fa.getUserData() != null && fa.getUserData().equals("foot")) {
			bodiesToRemove.add(fb.getBody());
			PlatformGame.getSoundManager().play(TyrianSound.CRYSTAL);

		}

		if (fa.getUserData() != null && fa.getUserData().equals("head")) {
			// remove crystal
			enemiesToRemove.add(fa.getBody());
		}

		if (fb.getUserData() != null && fb.getUserData().equals("head")) {
			enemiesToRemove.add(fb.getBody());
		}
		
		if (fa.getUserData() != null && fa.getUserData().equals("hearts")) {
			// remove crystal
			bodiesToRemoveHeart.add(fa.getBody());
			System.out.println("hearts");
		}

		if (fb.getUserData() != null && fb.getUserData().equals("hearts")) {
			bodiesToRemoveHeart.add(fb.getBody());
			System.out.println("hearts");
		}
		
		if (fa.getUserData() != null && fa.getUserData().equals("booms")
				&& fb.getUserData() != null && fb.getUserData().equals("foot")) {
			bodiesToRemoveBoom.add(fa.getBody());
			System.out.println("boom");
		}

		if (fb.getUserData() != null && fb.getUserData().equals("booms") 
				&& fa.getUserData() != null && fa.getUserData().equals("foot")) {
			bodiesToRemoveBoom.add(fb.getBody());
			System.out.println("boom");

		}
		
//		if (fa.getUserData() != null && fa.getUserData().equals("booms")) {
//			// remove crystal
//			
//		}
//
//		if (fb.getUserData() != null && fb.getUserData().equals("booms")) {
//			bodiesToRemoveBoom.add(fb.getBody());
//			System.out.println("boom");
//		}
		
		if (fa.getUserData() != null && fa.getUserData().equals("shield")) {
			// remove crystal
			bodiesToRemoveShields.add(fa.getBody());
			System.out.println("hearts");
		}

		if (fb.getUserData() != null && fb.getUserData().equals("shield")) {
			bodiesToRemoveShields.add(fb.getBody());
			System.out.println("hearts");
		}

		if (fa.getUserData() != null && fa.getUserData() == "body"
				&& fb.getUserData() == "player") {
			fb.getBody().setLinearVelocity(-3, 5);
			PlatformGame.getSoundManager().play(TyrianSound.HIT);
			//BaseLevel.hashia = 0;
//			if(Player.count2 <= 5)
//			numLifes -= 1;
			isOnDelay = false;
			isJoysticAvailable = false;
			//System.out.print("F" + Player.count2);
			
			numLifesCurr = numLifes + 1;
			//System.out.println(numLifes);
		}

		if (fb.getUserData() != null && fb.getUserData() == "body"
				&& (fa.getUserData() == "player")) {
			fa.getBody().setLinearVelocity(-3, 5);

//			if(Player.count2 <= 5)
//			numLifes -= 1;
			PlatformGame.getSoundManager().play(TyrianSound.HIT);
			//BaseLevel.hashia = 0;
			isJoysticAvailable = false;
			isOnDelay = false;

	
			numLifesCurr = numLifes + 1;
			//System.out.println(numLifes);
			
		}
		
	
			if (fa.getUserData() != null && fa.getUserData() == "ropefire"
					&& fb.getUserData() == "player") {
				fb.getBody().setLinearVelocity(0, 10);
				PlatformGame.getSoundManager().play(TyrianSound.HIT);
				
				isOnDelay = false;

//				if(Player.count2 <= 5)
//				numLifes -= 1f;
				numLifesCurr = numLifes + 1;
				//numLifes += 1f;
				//System.out.println("touches2");
				
			}

			if (fb.getUserData() != null && fb.getUserData() == "ropefire"
					&& (fa.getUserData() == "player")) {
				fa.getBody().setLinearVelocity(0, 10);
				
				isOnDelay = false;

		//		if(Player.count2 <= 5)
		//		numLifes -= 1;
				numLifesCurr = numLifes + 1;
				PlatformGame.getSoundManager().play(TyrianSound.HIT);
				
			}
		
		
		if(BaseLevel.level == 3 || BaseLevel.level == 10){
			if (fa.getUserData() != null && fa.getUserData() == "jumpingrock"
					&& fb.getUserData() == "player") {
				fb.getBody().setLinearVelocity(-3, 5);
				isJoysticAvailable = false;
				
			}

			if (fb.getUserData() != null && fb.getUserData() == "jumpingrock"
					&& (fa.getUserData() == "player")) {
				fa.getBody().setLinearVelocity(-3, 5);
				isJoysticAvailable = false;
				
			}
		}
	}

	public Array<Body> getBodiesToRemove() {
		return bodiesToRemove;
	}
	
	public Array<Body> getBodiesToRemoveHearts() {
		return bodiesToRemoveHeart;
	}
	
	public Array<Body> getBodiesToRemoveBooms() {
		return bodiesToRemoveBoom;
	}

	public Array<Body> getEnemyToRemove() {
		return enemiesToRemove;
	}
	
	public Array<Body> getBodiesToRemoveSheilds() {
		return bodiesToRemoveShields;
	}

	// exaxt opposite
	@Override
	public void endContact(Contact contact) {

		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		if (fa == null || fb == null)
			return;

		if (fa.getUserData() != null && fa.getUserData() == "body"
				&& fb.getUserData() == "player") {
			
			isOnDelay = true;
		}

		if (fb.getUserData() != null && fb.getUserData() == "body"
				&& (fa.getUserData() == "player")) {
			
			isOnDelay = true;
		
		}
		
		if (fa.getUserData() != null && fa.getUserData() == "ropefire"
				&& fb.getUserData() == "player") {
			System.out.println("dDSSFDSVDSV");
			isOnDelay = true;
		}

		if (fb.getUserData() != null && fb.getUserData() == "ropefire"
				&& (fa.getUserData() == "player")) {
			System.out.println("dDSSFDSVDSV");
			isOnDelay = true;
		}
			
		if (fa.getUserData() != null && fa.getUserData().equals("foot")) {
			numFootContacts--;
		}

		if (fb.getUserData() != null && fb.getUserData().equals("foot")) {
			numFootContacts--;
		}
	}

	public boolean isPlayerOnDGround() {
		// return numFootContacts > 0 ;
		return isOnGround;
	}

	public boolean isGoToEnd() {
		// return numFootContacts > 0 ;
		return isGoToEnd;
	}

	public void setisGoToEnd(boolean bool) {
		// return numFootContacts > 0 ;
		this.isGoToEnd = bool;
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

}
