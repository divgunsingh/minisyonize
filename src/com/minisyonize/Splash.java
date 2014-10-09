package com.minisyonize;

import com.Main.HelloWorld;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Splash extends Activity {
	
       ImageView i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		i=(ImageView) findViewById(R.id.imageView1);
		i.setBackgroundResource(R.anim.animation);
		AnimationDrawable anim=(AnimationDrawable) i.getBackground();
		anim.start();
		i.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i= new Intent(Splash.this,HelloWorld.class);
				startActivity(i);
				finish();
			}
		});
		
		
		
		
		
	}
}
