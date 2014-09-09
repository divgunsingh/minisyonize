package com.Main;

import java.lang.reflect.Field;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;

import com.Bullet.Bullet;
import com.Bullet.BulletManager;
import com.Collision.CollisionManager;
import com.Enemy.Enemy;
import com.Enemy.EnemyManager;
import com.Messaging.Messager;
import com.Messaging.ScreentouchMessage;
import com.Player.Player;
import com.Provider.ScreenInfoProvider;
import com.Sprite.AnimatedSpriteBlueprint;
import com.Sprite.SimpleSpriteBlueprint;
import com.Sprite.SimpleSpriteToken;
import com.Sprite.SpriteBlueprintProvider;
import com.Sprite.SpriteManager;
import com.minisyonize.R;
import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Light;
import com.threed.jpct.Logger;
import com.threed.jpct.Object3D;
import com.threed.jpct.Primitives;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;
import com.threed.jpct.util.BitmapHelper;
import com.threed.jpct.util.MemoryHelper;

/**
 * A simple demo. This shows more how to use jPCT-AE than it shows how to write
 * a proper application for Android. It includes basic activity management to
 * handle pause and resume...
 * 
 * @author EgonOlsen
 * 
 */

public class HelloWorld extends Activity {
	EnemyManager enemyManager;
	BulletManager bulletManager;
   Messager messager;
	// Used to handle pause and resume...
	private static HelloWorld master = null;
	//Player calling
	Bullet bullet;
	Enemy enemy;
	CollisionManager collisionManager;
     Player player;
	private GLSurfaceView mGLView;
	private MyRenderer renderer = null;
	private FrameBuffer fb = null;
	private World world = null;
	private RGBColor back = new RGBColor(50, 50, 100);

	private float touchTurn = 0;
	private float touchTurnUp = 0;

	private float xpos = -1;
	private float ypos = -1;

	private Object3D cube = null;
	private int fps = 0;

	private Light sun = null;

	protected void onCreate(Bundle savedInstanceState) {

		//enemyManager = new EnemyManager();

		Logger.log("onCreate");

		if (master != null) {
			copy(master);
		}

		super.onCreate(savedInstanceState);
		mGLView = new GLSurfaceView(getApplication());

		mGLView.setEGLConfigChooser(new GLSurfaceView.EGLConfigChooser() {
			public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
				// Ensure that we get a 16bit framebuffer. Otherwise, we'll fall
				// back to Pixelflinger on some device (read: Samsung I7500)
				int[] attributes = new int[] { EGL10.EGL_DEPTH_SIZE, 16, EGL10.EGL_NONE };
				EGLConfig[] configs = new EGLConfig[1];
				int[] result = new int[1];
				egl.eglChooseConfig(display, attributes, configs, 1, result);
				return configs[0];
			}
		});

		renderer = new MyRenderer();
		mGLView.setRenderer(renderer);
		setContentView(mGLView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mGLView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGLView.onResume();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	private void copy(Object src) {
		try {
			Logger.log("Copying data from master Activity!");
			Field[] fs = src.getClass().getDeclaredFields();
			for (Field f : fs) {
				f.setAccessible(true);
				f.set(this, f.get(src));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean onTouchEvent(MotionEvent me) {

		
		if (me.getAction() == MotionEvent.ACTION_DOWN) {
			xpos = me.getX();
			ypos = me.getY();
			
			Messager.GetInstance().Publish(new ScreentouchMessage(xpos,ypos));
			//player.Fire(new SimpleVector(xpos,ypos,0));
			return true;
		}

		if (me.getAction() == MotionEvent.ACTION_UP) {
			xpos = -1;
			ypos = -1;
			touchTurn = 0;
			touchTurnUp = 0;
			return true;
		}

		if (me.getAction() == MotionEvent.ACTION_MOVE) {
			float xd = me.getX() - xpos;
			float yd = me.getY() - ypos;

			xpos = me.getX();
			ypos = me.getY();

			touchTurn = xd / -100f;
			touchTurnUp = yd / -100f;
			return true;
		}

		try {
			Thread.sleep(15);
		} catch (Exception e) {
			// No need for this...
		}

		return super.onTouchEvent(me);
	}

	protected boolean isFullscreenOpaque() {
		return true;
	}

	class MyRenderer implements GLSurfaceView.Renderer {

		private long prviousTickTime= System.currentTimeMillis();
		private long time = System.currentTimeMillis();
		private long deltaTickTime = System.currentTimeMillis() - prviousTickTime ;

		public MyRenderer() {
		}

		public void onSurfaceChanged(GL10 gl, int w, int h) {
			
			if (fb != null) {
				fb.dispose();
			}
			fb = new FrameBuffer(gl, w, h);
            
			if (master == null) {

				world = new World();
				world.setAmbientLight(20, 20, 20);

				sun = new Light(world);
				sun.setIntensity(250, 250, 250);
ScreenInfoProvider.GetInstance().Initialize(w, h);
				// Create a texture out of the icon...:-)
				Texture texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(getResources().getDrawable(R.raw.enemy2)), 64, 64));
				TextureManager.getInstance().addTexture("texture", texture);
				// to create a texture in the manager 
				TextureManager.getInstance().addTexture("enemy", new Texture(getResources().openRawResource(R.raw.enemy), true));
				TextureManager.getInstance().addTexture("player", new Texture(getResources().openRawResource(R.raw.playerall), true));
				TextureManager.getInstance().addTexture("bullet", new Texture(getResources().openRawResource(R.raw.bullet), true));
				// to create a simple sprite blueprint in the sprite blueprint provider 
				//Animated Sprite
				SpriteBlueprintProvider.GetInstance().AddAnimatedSpriteBlueprint("playerlabel", new AnimatedSpriteBlueprint("player", new SimpleVector(0,1000,0), 10f, new int[]{4} , 2f, 16, 16));
				//Simple Sprite
				SpriteBlueprintProvider.GetInstance().AddSimpleSpriteBlueprint("bullet_blueprint", new SimpleSpriteBlueprint("bullet", new SimpleVector(0,0,0) , 2.5f));
				SpriteBlueprintProvider.GetInstance().AddSimpleSpriteBlueprint("enemy_blueprint", new SimpleSpriteBlueprint("enemy", new SimpleVector(0,0,0) , 5f));
				//sSpriteBlueprintProvider.GetInstance().AddSimpleSpriteBlueprint("sprite_blueprint_label", new SimpleSpriteBlueprint("texture_label", new SimpleVector(0,0,0) , 5f));
				// to instantiate a sprite from the sprite manager 
			//	SpriteManager.GetInstance().AddAnimatedSprite("playerlabel", 0);
				//SpriteManager.GetInstance().AddSimpleSprite("playerlabel", 0);
				//SimpleSpriteToken tokenPlayer=SpriteManager.GetInstance().AddSimpleSprite("playerlabel", 0);
                
				cube = Primitives.getCube(10);
				cube.calcTextureWrapSpherical();
				cube.setTexture("texture");
				cube.strip();
				cube.build();

				world.addObject(cube);

				Camera cam = world.getCamera();
				cam.moveCamera(Camera.CAMERA_MOVEOUT, 50);
				cam.lookAt(cube.getTransformedCenter());

				SimpleVector sv = new SimpleVector();
				sv.set(cube.getTransformedCenter());
				sv.y -= 100;
				sv.z -= 100;
				sun.setPosition(sv);
				MemoryHelper.compact();
				//calling constructor of player class
				player=new Player();
			    enemyManager= new EnemyManager(player.position,w,h);
				
			   
				//bullet=new Bullet();
		// enemy=new Enemy();
				if (master == null) {
					Logger.log("Saving master Activity!");
					master = HelloWorld.this;
				}
			}
		}

		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		}
        
		
		
		public void update(){
        	
	deltaTickTime=System.currentTimeMillis() - prviousTickTime;
	prviousTickTime = System.currentTimeMillis();
			enemyManager.Update(1.0f / 60.0f);
			player.update(1.0f / 60.0f);
			collisionManager.GetInstance().Update(1.0f / 60.0f);
        	
        	
        }
		public void onDrawFrame(GL10 gl) {
			if (touchTurn != 0) {
				cube.rotateY(touchTurn);
				touchTurn = 0;
			}

			if (touchTurnUp != 0) {
				cube.rotateX(touchTurnUp);
				touchTurnUp = 0;
			}

			fb.clear(back);
			
			//world.renderScene(fb);
			//world.draw(fb);
			//draw our object 
			//SpriteManager.update();
			update();
			SpriteManager.GetInstance().Update(1f/10.0f);
			SpriteManager.GetInstance().Draw(fb);
			fb.display();

			if (System.currentTimeMillis() - time >= 1000) {
				Logger.log(fps + "fps");
				fps = 0;
				time = System.currentTimeMillis();
			}
			fps++;
		}
	}
}