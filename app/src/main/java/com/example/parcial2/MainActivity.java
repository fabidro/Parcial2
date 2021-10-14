package com.example.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sm;
    Sensor RotacionDelVector;
    TextView valX, valY, valZ;
    LinearLayout fondo;
    Vibrator vibrador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibrador = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        valX=(TextView) findViewById(R.id.textView6);
        valY=(TextView) findViewById(R.id.textView7);
        valZ=(TextView) findViewById(R.id.textView8);
        fondo= (LinearLayout) findViewById(R.id.Fondo2);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE); /*sensorManager se usa para inicializar los sensores */
        RotacionDelVector=sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sm.registerListener(this,RotacionDelVector,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        valX.setText("X ="+sensorEvent.values[0]);
        valY.setText("Y ="+sensorEvent.values[1]);
        valZ.setText("Z ="+sensorEvent.values[2]);

        if(sensorEvent.values[2]>0.2705){ //Mayor a 45

            fondo.setBackgroundColor(Color.BLACK);
            long[] pattern={0,100,1000};
            vibrador.vibrate(pattern,0);
        }
        if (sensorEvent.values[2]<-0.2705){//menor a -45
            long[] pattern={0,10,10};
            vibrador.vibrate(pattern,0);
            fondo.setBackgroundColor(Color.BLUE);
        }
        if(sensorEvent.values[2]<0.06142&&sensorEvent.values[2]>-0.06142){  //entre 10 y -10
            long[] pattern={0,10,400};
            vibrador.vibrate(pattern,0);
            fondo.setBackgroundColor(Color.RED);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}