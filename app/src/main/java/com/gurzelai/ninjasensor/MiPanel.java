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


public class MiPanel extends View implements SensorEventListener {
    int radio = 60; //circuferencia de la pelota
    Paint pincel = new Paint();
    int alto, ancho;
    int borde = 30; // esto se usara para definir el borde de la pantalla

    Pelota pelota;
    PelotaGrande pgrande;

    public MiPanel(Context interfaz) {
        super(interfaz);
        SensorManager smAdministrador = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor snsRotacion = smAdministrador.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        smAdministrador.registerListener(this, snsRotacion, SensorManager.SENSOR_DELAY_FASTEST);
        Display pantalla = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        ancho = pantalla.getWidth();
        alto = pantalla.getHeight();
        View root = this.getRootView();
        root.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.black));

        pelota = new Pelota(ancho, alto, borde, pincel);
        pgrande = new PelotaGrande(ancho, alto, borde, pincel);
    }

    @Override
    public void onSensorChanged(SensorEvent cambio) {

        pelota.cambioSensor(cambio);
        invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDraw(Canvas lienzo) {
        pgrande.draw(lienzo);
        pelota.draw(lienzo);
    }
}
