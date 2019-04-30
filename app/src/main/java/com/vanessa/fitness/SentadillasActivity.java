package com.vanessa.fitness;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class SentadillasActivity extends AppCompatActivity {
    SensorManager mySensorManager2;
    TextView allsensor2,contador2;
    Button reiniciar2;
    public MediaPlayer bien, perfecto, animo;
    Random random = new Random();
    ArrayList<Boolean> pila = new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentadillas);

        allsensor2 = (TextView) findViewById(R.id.allsensor2);
        contador2 = (TextView) findViewById(R.id.cont2);
        reiniciar2 = (Button) findViewById(R.id.reinicio2);
        // AUDIOS

        bien = MediaPlayer.create(SentadillasActivity.this, R.raw.bien);
        perfecto = MediaPlayer.create(SentadillasActivity.this, R.raw.perfecto);
        animo = MediaPlayer.create(SentadillasActivity.this, R.raw.animo);
        //SENSOR
        mySensorManager2 = (SensorManager)getSystemService(SENSOR_SERVICE);

        Sensor tipoSensor2 = mySensorManager2.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if(tipoSensor2 != null){
            Toast.makeText(SentadillasActivity.this, "Sensor de Orientación Disponible",Toast.LENGTH_LONG).show();
            mySensorManager2.registerListener(
                    SensorListener2,
                    tipoSensor2,
                    SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            Toast.makeText(SentadillasActivity.this, "Sensor de Orientación NO Disponible",Toast.LENGTH_LONG).show();
        }

        reiniciar2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                contador2.setText("0");
            }
        });
    }

    private final SensorEventListener SensorListener2 = new SensorEventListener(){
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                allsensor2.setText("X: "+x+" \n\n "+"Y: "+y+" \n\n "+"Z:"+z);
                /*if(x<-8 && y<1 && pila.get(pila.size()-1)==false){
                    int rep = Integer.parseInt(contador.getText().toString()) + 1;
                    contador.setText(""+rep);
                    if(random.nextBoolean())
                        bien.start();
                    else
                        perfecto.start();
                    pila.clear();
                    pila.add(true);
                }
                else if(x<1 && y>9){
                    pila.add(false);
                }*/
            }

        }

    };
}
