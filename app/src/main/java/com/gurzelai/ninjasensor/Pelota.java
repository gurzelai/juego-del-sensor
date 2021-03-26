package com.gurzelai.ninjasensor;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.SensorEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Pelota extends Drawable {

    Paint pincel;
    int radio = 60; //circuferencia de la pelota
    float ejeX, ejeY, ejeZ; //posicion cada momento
    int borde;
    int ancho, alto;

    public Pelota(int ancho, int alto, int borde, Paint pincel) {
        ejeX = ancho / 2;
        ejeY = alto / 2;
        ejeZ = radio; //posicion cada momento
        this.borde = borde;
        this.ancho = ancho;
        this.alto = alto;
        this.pincel = pincel;
    }

    @Override
    public void draw(@NonNull Canvas lienzo) {
        pincel.setColor(Color.RED);
        lienzo.drawCircle(ejeX, ejeY, radio, pincel);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @SuppressLint("WrongConstant")
    @Override
    public int getOpacity() {
        return 1;
    }

    public int getRadio() {
        return radio;
    }

    public float getEjeX() {
        return ejeX;
    }

    public float getEjeY() {
        return ejeY;
    }

    public void cambioSensor(SensorEvent cambio) {
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
    }
}
