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

public class BrazosActivity extends AppCompatActivity {

    SensorManager mySensorManager;
    TextView allsensor,contador;
    Button reiniciar;
    public MediaPlayer bien, excelente, titulo;
    Random random = new Random();
    ArrayList<Boolean> pila = new ArrayList<Boolean>();
    Boolean brazos = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brazos);
        brazos = true;
        pila.add(true);

        allsensor = (TextView) findViewById(R.id.allsensor3);
        contador = (TextView) findViewById(R.id.cont3);
        reiniciar = (Button) findViewById(R.id.reinicio3);
        // AUDIOS
        bien = MediaPlayer.create(BrazosActivity.this, R.raw.bien);
        excelente = MediaPlayer.create(BrazosActivity.this, R.raw.excelente);
        titulo = MediaPlayer.create(BrazosActivity.this, R.raw.tonificabrazos);
        titulo.start();
        //SENSOR
        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        Sensor tipoSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(tipoSensor != null){
            Toast.makeText(BrazosActivity.this, "Sensor de Acelerometro Disponible",Toast.LENGTH_LONG).show();
            mySensorManager.registerListener(
                    SensorListener,
                    tipoSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            Toast.makeText(BrazosActivity.this, "Sensor de Acelerometro NO Disponible",Toast.LENGTH_LONG).show();
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
            if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && brazos==true){
                float x = event.values[0];
                float y = event.values[1];
                //allsensor.setText("X: "+x+" \n\n "+"Y:"+y);

                if(x<1 && y>8 && pila.get(pila.size()-1)==false){
                    int rep = Integer.parseInt(contador.getText().toString()) + 1;
                    contador.setText(""+rep);
                    if(random.nextBoolean())
                        excelente.start();
                    else
                        bien.start();
                    pila.clear();
                    pila.add(true);
                }
                else if(x<3 && y<-6){
                    pila.add(false);
                }
            }

        }

    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        brazos = false;
        bien.reset();
        excelente.reset();
        titulo.reset();
        this.finish();
    }
}
