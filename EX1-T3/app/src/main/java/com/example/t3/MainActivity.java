package com.example.t3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView textX;
    private TextView textY;
    private TextView textZ;

    private boolean sensorInitialized = false;
    double gravityValue;
    double threshold = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textX = findViewById(R.id.textViewX);
        textY = findViewById(R.id.textViewY);
        textZ = findViewById(R.id.textViewZ);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            double deltaX, deltaY, deltaZ;

            if(!sensorInitialized) {
                gravityValue = event.values[1];
                sensorInitialized = true;

            }
            else {
                deltaX = event.values[0];
                deltaY = Math.abs(event.values[1] - gravityValue);
                deltaZ = event.values[2];

                textX.setText(String.valueOf(deltaX));
                textY.setText(String.valueOf(deltaY));
                textZ.setText(String.valueOf(deltaZ));

                if (deltaX > threshold || deltaY > threshold || deltaZ > threshold && sensorInitialized) {
                    Intent intent = new Intent(this, MainActivity2.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    sensorInitialized = false;
                }
            }
        }
    }
}