package com.bp.pruebagiroscopio;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int sensorType = Sensor.TYPE_ACCELEROMETER;

    private TextView xTextView;
    private TextView yTextView;
    private TextView zTextView;

    private float xAxis = 0f;
    private float yAxis = 0f;
    private float zAxis = 0f;

    /* Instancia de sensorEventListener, que permite recibir las actualizaciones del sensor. */
    private final SensorEventListener gyroscopeEventListener = new SensorEventListener() {

        //Permite advertir las actualizaciones del sensor.
        @Override
        public void onSensorChanged(SensorEvent event) {
            //Se comprueba el tipo de sensor.
            if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                //Recupera los valores de x,y,z.
                xAxis = event.values[0];
                yAxis = event.values[1];
                zAxis = event.values[2];
                //Actualiza los textViews.
                xTextView.setText("Eje x = " + Float.toString(xAxis));
                yTextView.setText("Eje y = " + Float.toString(yAxis));
                zTextView.setText("Eje z = " + Float.toString(zAxis));
            }
        }

        //Permite advertir los cambios en la precisión del sensor.
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xTextView = (TextView) findViewById(R.id.x_axis);
        yTextView = (TextView) findViewById(R.id.y_axis);
        zTextView = (TextView) findViewById(R.id.z_axis);

        //Instancia del sensorManager.
        final SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //Asocia el sensor al sensorManager registrando el listener.
        sensorManager.registerListener(gyroscopeEventListener //Listener a registrar.
                , sensorManager.getDefaultSensor(sensorType) //Tipo de sensor.
                , SensorManager.SENSOR_DELAY_NORMAL); //Intervalo de actualización.
    }
}
