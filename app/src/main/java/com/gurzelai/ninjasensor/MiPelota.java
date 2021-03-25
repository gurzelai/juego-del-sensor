package com.gurzelai.ninjasensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;


public class MiPelota extends View implements SensorEventListener {

    Paint pincel = new Paint();
    int alto, ancho;
    int radio = 60; //circuferencia de la pelota
    int borde = 30; // esto se usara para definir el borde de la pantalla
    float ejeX = radio + borde, ejeY = radio + borde, ejeZ = radio; //posicion cada momento

    public MiPelota(Context interfaz) {
        super(interfaz);
        SensorManager smAdministrador = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor snsRotacion = smAdministrador.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        smAdministrador.registerListener(this, snsRotacion, SensorManager.SENSOR_DELAY_FASTEST);
        Display pantalla = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        ancho = pantalla.getWidth();
        alto = pantalla.getHeight();
        View root = this.getRootView();
        root.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.black));
    }

    @Override
    public void onSensorChanged(SensorEvent cambio) {

        ejeX -= cambio.values[0];
        if (ejeX < (radio + borde)) {
            ejeX = (radio + borde);
        } else if (ejeX > (ancho - radio - borde)) {
            ejeX = ancho - radio - borde;
        }
        ejeY += cambio.values[1];
        if (ejeY < (radio + borde)) {
            ejeY = (radio + borde);
        } else if (ejeY > (alto - radio - borde)) { //poner -150 si ponemos el navigation bar
            ejeY = (alto - radio - borde);  //poner -150 si ponemos el navigation bar
        }
        ejeZ = cambio.values[2];
        invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDraw(Canvas lienzo) {
        pincel.setColor(Color.WHITE);
        lienzo.drawCircle(ancho / 2, alto / 2, radio * 4, pincel);
        pincel.setColor(Color.BLACK);
        lienzo.drawCircle(ancho / 2, alto / 2, (radio * 4) - 3, pincel);
        pincel.setColor(Color.RED);
        lienzo.drawCircle(ejeX, ejeY, radio, pincel);

    }
}
