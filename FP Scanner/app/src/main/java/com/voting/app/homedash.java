package com.voting.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class homedash extends AppCompatActivity implements View.OnClickListener {
        private TextView Quit;
        private Button Employee,Boss,Algo,Game,Food;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homedash);

        Quit = findViewById(R.id.quit);
        Quit.setOnClickListener(this);

        Employee = findViewById(R.id.empbutton);
        Employee.setOnClickListener(this);

        Boss = findViewById(R.id.bossbutton);
        Algo = findViewById(R.id.algobutton);
        Game = findViewById(R.id.gamebutton);
        Food = findViewById(R.id.foodbutton);
        Boss.setOnClickListener(this);
        Algo.setOnClickListener(this);
        Game.setOnClickListener(this);
        Food.setOnClickListener(this);

        votedialog();
        opendalog();


    }

    private void opendalog() {
        //Toast.makeText(homedash.this,"Welcome to VoteIT !",Toast.LENGTH_LONG).show();
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.opendialogue,null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        Button accept = view.findViewById(R.id.accept);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();

            }
        });
    }

    private void votedialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.votedialog,null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        Button accept = view.findViewById(R.id.accept);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.empbutton:
                tovote();
                //setfalseifvoted();
                break;
            case R.id.bossbutton:
                boss();
                break;
            case R.id.algobutton:
                algo();
                break;
            case R.id.gamebutton:
                game();
                break;
            case R.id.foodbutton:
                food();
                break;

            case R.id.quit:
                //android.os.Process.killProcess(android.os.Process.myPid());
                exit();
          }
    }

    private void food() {
        startActivity(new Intent(homedash.this,votemain5.class));
    }

    private void game() {
        startActivity(new Intent(homedash.this,votemain4.class));
    }

    private void algo() {
        startActivity(new Intent(homedash.this,votemain3.class));
    }

    private void boss() {
        startActivity(new Intent(homedash.this,votemain2.class));
    }

    private void exit() {
        finishAffinity();
        System.exit(0);



    }

    private void tovote() {
            startActivity(new Intent(homedash.this,votemain.class));
    }
}