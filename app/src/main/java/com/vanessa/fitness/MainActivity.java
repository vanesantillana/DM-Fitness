package com.vanessa.fitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout menu1,menu2,menu3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu1 = (LinearLayout )findViewById(R.id.menu1);
        menu2 = (LinearLayout )findViewById(R.id.menu2);
        menu3 = (LinearLayout )findViewById(R.id.menu3);

        menu1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ejercicioActivity.class);
                startActivity(intent);
            }
        });

        menu2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SentadillasActivity.class);
                startActivity(intent);
            }
        });
        menu3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BrazosActivity.class);
                startActivity(intent);
            }
        });
    }
}
