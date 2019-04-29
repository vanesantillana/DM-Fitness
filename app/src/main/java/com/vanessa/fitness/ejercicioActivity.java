package com.vanessa.fitness;

import android.graphics.Color;
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

import java.util.Random;

public class ejercicioActivity extends AppCompatActivity {
    SensorManager mySensorManager;
    TextView allsensor,contador;
    Button reiniciar;
    public MediaPlayer bien, perfecto, animo;
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio);

        allsensor = (TextView) findViewById(R.id.allsensor);
        contador = (TextView) findViewById(R.id.cont);
        reiniciar = (Button) findViewById(R.id.reinicio);
        // AUDIOS
        bien = MediaPlayer.create(ejercicioActivity.this, R.raw.bien);
        perfecto = MediaPlayer.create(ejercicioActivity.this, R.raw.perfecto);
        animo = MediaPlayer.create(ejercicioActivity.this, R.raw.animo);
        //SENSOR
        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        Sensor tipoSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(tipoSensor != null){
            Toast.makeText(ejercicioActivity.this, "Sensor de ACCELEROMETER Disponible",Toast.LENGTH_LONG).show();
            mySensorManager.registerListener(
                SensorListener,
                tipoSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            Toast.makeText(ejercicioActivity.this, "Sensor de ACCELEROMETER NO Disponible",Toast.LENGTH_LONG).show();
        }

        reiniciar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                contador.setText("0");
            }
        });

    }

    private final SensorEventListener SensorListener = new SensorEventListener(){
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                float x = event.values[0];
                float y = event.values[1];
                allsensor.setText("X: "+x+" \n\n "+"Y:"+y);
                if(x<-9 && y<1){
                    int rep = Integer.parseInt(contador.getText().toString()) + 1;
                    contador.setText(""+rep);
                    int audio = random.nextInt()%3 +1;
                    if(audio == 0)
                        bien.start();
                    else if(audio == 1)
                        animo.start();
                    else if(audio == 2)
                        perfecto.start();
                    //esperar(3);
                }
            }

        }

    };
    public void esperar (int segundos) {
        try {
            Thread.sleep (segundos*1000);
        } catch (Exception e) {
        }
    }
}
