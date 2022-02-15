package com.example.atividade_pratica_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    private SensorManager sensorManager;
    private Sensor light;
    private Sensor magnetic;
    ConstraintLayout fundinho;

    private TextView M, L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaViews();

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        magnetic = sensorManager.getDefaultSensor((Sensor.TYPE_MAGNETIC_FIELD));

        sensorManager.registerListener(MainActivity.this, light, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(MainActivity.this, magnetic, SensorManager.SENSOR_DELAY_NORMAL);

    }
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if(sensor.getType() == Sensor.TYPE_LIGHT)
        {
            L.setText("Luminosidade: " + event.values[0]);
            float valor_cor = event.values[0]/4;
            if (valor_cor > 225){
                valor_cor = 225;
            }
            int cor = (int) valor_cor;
            fundinho.setBackgroundColor(Color.argb(cor, cor, cor, cor));
        }
        if(sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
        {
            M.setText("Campo Magnético: " + event.values[0]);
        }
    }
    public void inicializaViews(){
        M = (TextView) findViewById(R.id.textMagnetic);
        L = (TextView) findViewById(R.id.textLuminosidade);
        fundinho = (ConstraintLayout) findViewById((R.id.constraint_lay));
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}