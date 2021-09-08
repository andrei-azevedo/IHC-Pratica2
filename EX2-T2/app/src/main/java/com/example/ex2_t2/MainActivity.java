package com.example.ex2_t2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor temperature;
    TextView temperatureValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperatureValue = (TextView)findViewById(R.id.temperatureTextView);
        sensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(temperature != null)
        {
            sensorManager.registerListener(MainActivity.this, temperature,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }else
        {
            temperatureValue.setText("Temperature sensor not supported");
        }

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if(sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE)
        {
            temperatureValue.setText("Temperature: " + event.values[0] + " celsius degrees");
        }
    }
}