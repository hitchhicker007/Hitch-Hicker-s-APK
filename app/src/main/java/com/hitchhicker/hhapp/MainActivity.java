package com.hitchhicker.hhapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.hitchhicker.hhapp.R;

public class MainActivity extends AppCompatActivity {

    private CardView blogs,code,services,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blogs = (CardView)findViewById(R.id.card_blogs);
        code = (CardView)findViewById(R.id.card_code);
        services = (CardView)findViewById(R.id.card_services);
        contact = (CardView)findViewById(R.id.card_contact);


        blogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,BlogActivity.class));
            }
        });

        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CodeActivity.class));
            }
        });

        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ServicesActivity.class));
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ContactActivity.class));
            }
        });


    }

    private Boolean exit=false;

    @Override
    public void onBackPressed() {
        if (exit){
            MainActivity.this.finish();
        }else{
            Toast.makeText(this,"Press Back again to Exit.",Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit=false;
                }
            },2*1000);
        }
    }
}
