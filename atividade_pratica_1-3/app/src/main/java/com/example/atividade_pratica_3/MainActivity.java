package com.example.atividade_pratica_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;


import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private float anteriorX = 0;
    private float anteriorY = 0;
    private float anteriorZ = 0;

    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;

    private TextView X, Y, Z;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaViews();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void inicializaViews(){
        X = (TextView) findViewById(R.id.textView_x);
        Y = (TextView) findViewById(R.id.textView_y);
        Z = (TextView) findViewById(R.id.textView_z);
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        deltaX = Math.abs(anteriorX - sensorEvent.values[0]);
        deltaY = Math.abs(anteriorY - sensorEvent.values[1]);
        deltaZ = Math.abs(anteriorZ - sensorEvent.values[2]);

        X.setText("X: " + Float.toString(deltaX));
        Y.setText("Y: " + Float.toString(deltaY));
        Z.setText("Z: " + Float.toString(deltaZ));

        anteriorX = sensorEvent.values[0];
        anteriorY = sensorEvent.values[1];
        anteriorZ = sensorEvent.values[2];

        if (deltaX > 10.0 || deltaY > 10.0 || deltaZ > 10.0){
            sensorManager.unregisterListener(this);
            Intent i = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(i);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

}