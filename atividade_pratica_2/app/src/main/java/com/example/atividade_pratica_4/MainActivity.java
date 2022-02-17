package com.example.atividade_pratica_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        Button getGPSBtn = (Button) findViewById(R.id.getGPSBtn);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        getGPSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker g = new GPSTracker(getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double longi = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LAT: " + lat +"\n"+ "LONG: " + longi, Toast.LENGTH_LONG).show();
                }
            }
        });

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