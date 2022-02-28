package com.voting.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int SPLASHSCR = 5000;

    Animation anim_top, anim_btm;
    ImageView image;
    TextView slogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Splash screen animation =
        anim_top = AnimationUtils.loadAnimation(this,R.anim.top_logo_anim);
        anim_btm = AnimationUtils.loadAnimation(this,R.anim.btm_anim);

        //assigning the images and slogan to animation
        image = findViewById(R.id.imageView);
        slogan = findViewById(R.id.textView);

        image.setAnimation(anim_top);
        slogan.setAnimation(anim_btm);

        Toast.makeText(MainActivity.this,"Developed by Yash Kandekar",Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,loginmain.class);
                startActivity(intent);
                finish();
            }
        },SPLASHSCR);

    }
}