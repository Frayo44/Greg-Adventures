package com.platformgame.entities;

import aurelienribon.bodyeditor.BodyEditorLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;
import com.platform.game.PlatformGame;

public class Car {

	private Body chasis, leftWheel, rightWheel, chasisRight, chasisLeft;
	private WheelJoint leftAxis, rightAxis;
	
	public Rock rock, rock2, bodyRock;
	//Vector2 bottleModelOrigin;
	// Body carModel;
	 
	//Sprite bottleSprite;
	
	public Car(World world, FixtureDef chasisFixtureDef,FixtureDef wheelFixtureDef
			,float x, float y , float width, float height){
		
		
		
		//create the body def
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x,y);
		

		Texture wheels = PlatformGame.manager.get("images/wheel.png");
		Texture chasisTex = PlatformGame.manager.get("images/car.png");
		
		//shasis
		PolygonShape chasisShape = new PolygonShape();
		chasisShape.setAsBox(width / 9, height / 4);

		RevoluteJoint joints = new RevoluteJoint(world, 1);
		
		RevoluteJointDef jointdef = new RevoluteJointDef();
		jointdef.localAnchorA.set(- width / 2 * 1.45f + 1.1f, -height / 2f);
		
		
		//shasis
		chasisFixtureDef.shape = chasisShape;
	
		chasisRight = world.createBody(bodyDef);
		chasisRight.createFixture(chasisFixtureDef);
		chasisRight.setFixedRotation(true);
		
		RevoluteJoint joints2 = new RevoluteJoint(world, 1);
		
		RevoluteJointDef jointdef2 = new RevoluteJointDef();
		jointdef2.localAnchorA.set(- width / 2 * 1.45f + 0.28f, -height / 3);
		
		
		//shasis
		chasisFixtureDef.shape = chasisShape;
	
		chasisLeft = world.createBody(bodyDef);
		chasisLeft.createFixture(chasisFixtureDef);
		chasisLeft.setFixedRotation(true);
		
		
		chasisShape.setAsBox(width / 2, height /6);
		chasisFixtureDef.shape = chasisShape;
		chasis = world.createBody(bodyDef);
		chasis.createFixture(chasisFixtureDef);
		
		bodyRock = new Rock(chasis, null, false);
		bodyRock.spr.setTexture(chasisTex);
		bodyRock.spr.setScale(1.3f, 1.2f);
	//	bodyRock.spr.setPosition(bodyRock.getPosition().x, bodyRock.getPosition().x + 100);
		//chasis.setFixedRotation(true);
		
		
		jointdef.bodyA = chasisRight;
		jointdef.bodyB = chasis;
		joints = (RevoluteJoint) world.createJoint(jointdef);
		
		jointdef2.bodyA = chasisLeft;
		jointdef2.bodyB = chasis;
		joints2 = (RevoluteJoint) world.createJoint(jointdef2);
		
		//left wheel
		CircleShape wheelShape = new CircleShape();
		wheelShape.setRadius(height / 3.5f);
		
		wheelFixtureDef.shape  = wheelShape;
		
		leftWheel = world.createBody(bodyDef);
		leftWheel.createFixture(wheelFixtureDef);
		rock2 = new Rock(leftWheel, null, false);
		rock2.spr.setTexture(wheels);
		rock2.spr.setScale(0.4f);
		
		//right wheel
		rightWheel = world.createBody(bodyDef);
		rightWheel.createFixture(wheelFixtureDef);
		rock = new Rock(rightWheel,  null, false);
		rock.spr.setTexture(wheels);
		rock.spr.setScale(0.4f);
		//leftaxis
		WheelJointDef axisDef = new WheelJointDef(); 
		axisDef.bodyA = chasis;
		axisDef.bodyB = leftWheel;
		axisDef.localAnchorA.set(- width / 2 * .75f + wheelShape.getRadius(), -height / 2);
		axisDef.localAxisA.set(Vector2.Y);
		axisDef.frequencyHz = 12f;
		
		leftAxis = (WheelJoint) world.createJoint(axisDef);
		
		//right axis
		//axisDef.bodyA = rightWheel;
		axisDef.bodyB = rightWheel;
		axisDef.localAnchorA.x *= -1;
		
		rightAxis = (WheelJoint) world.createJoint(axisDef);
				
	}
	
	public void render(SpriteBatch sb){
		
		
		bodyRock.render(sb);
		rock.render(sb);
		rock2.render(sb);
	}

	
}
