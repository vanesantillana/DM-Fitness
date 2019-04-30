package com.vanessa.fitness;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class ejercicioActivity extends AppCompatActivity {
    SensorManager mySensorManager;
    TextView allsensor,contador;
    Button reiniciar;
    public MediaPlayer bien, perfecto, animo;
    Random random = new Random();
    ArrayList<Boolean> pila = new ArrayList<Boolean>();
    Boolean punios = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio);
        punios = true;

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
            if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && punios==true){
                float x = event.values[0];
                float y = event.values[1];
                //allsensor.setText("X: "+x+" \n\n "+"Y:"+y);
                if(x<-8 && y<1 && pila.get(pila.size()-1)==false){
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
                }
            }

        }

    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        punios = false;
        bien.reset();
        perfecto.reset();
        this.finish();
    }

}
