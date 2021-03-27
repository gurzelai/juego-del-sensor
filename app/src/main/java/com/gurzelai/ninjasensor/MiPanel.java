package com.gurzelai.ninjasensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;


public class MiPanel extends View implements SensorEventListener {

    final int VOLUMEN = 0;

    Paint pincel = new Paint();
    ToneGenerator tone;

    Pelota pelota;
    PelotaGrande pgrande;

    int alto, ancho;
    int borde = 30; // esto se usara para definir el borde de la pantalla
    int bordeBajo;
    int contadorCiclos = 0;

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
        tone = new ToneGenerator(AudioManager.STREAM_ALARM, VOLUMEN);

        bordeBajo = alto - (alto - 75 - 75 - 75);

        pelota = new Pelota(ancho, alto, borde, pincel, bordeBajo);
        pgrande = new PelotaGrande(ancho, alto, borde, pincel, bordeBajo);
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
        choqueEntrePelotas();
        dibujarProgressBar(lienzo);
    }

    private void dibujarProgressBar(Canvas lienzo) {
        pincel.setColor(Color.BLUE);
        lienzo.drawRect(40, alto - 75,ancho -40, alto - 75 - 75, pincel);
    }

    private void choqueEntrePelotas() {

        if (contadorCiclos > 10) {
            if (pelota.getEjeX() + pelota.getRadio() >= pgrande.getEjeX() + pgrande.getRadioInt()) {
                tone.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
                contadorCiclos = 0;
            }
            if (pelota.getEjeX() - pelota.getRadio() <= pgrande.getEjeX() - pgrande.getRadioInt()) {
                tone.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
                contadorCiclos = 0;
            }
            if (pelota.getEjeY() + pelota.getRadio() >= pgrande.getEjeY() + pgrande.getRadioInt()) {
                tone.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
                contadorCiclos = 0;
            }
            if (pelota.getEjeY() - pelota.getRadio() <= pgrande.getEjeY() - pgrande.getRadioInt()) {
                tone.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
                contadorCiclos = 0;
            }
        }
        contadorCiclos++;

    }
}
