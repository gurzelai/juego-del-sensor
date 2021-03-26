package com.gurzelai.ninjasensor;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;

public class PelotaGrande extends Drawable {

    Paint pincel;
    int radioInt, radioExt;
    float ejeX, ejeY;
    int borde;
    int ancho, alto;

    int diferenciaX = 1, diferenciaY = 1;
    int diferenciaRadio = 1;

    public PelotaGrande(int ancho, int alto, int borde, Paint pincel) {
        ejeX = ancho / 2;
        ejeY = alto / 2;
        this.borde = borde;
        this.ancho = ancho;
        this.alto = alto;
        this.pincel = pincel;
        radioExt = 220;
        radioInt = radioExt - 6;
    }

    public int getRadioInt() {
        return radioInt;
    }


    public float getEjeX() {
        return ejeX;
    }



    public float getEjeY() {
        return ejeY;
    }



    @Override
    public void draw(@NonNull Canvas lienzo) {
        paredes();
        controlRadio();
        pincel.setColor(Color.WHITE);
        lienzo.drawCircle(ejeX = ejeX + diferenciaX, ejeY = ejeY + diferenciaY, radioExt = radioExt + diferenciaRadio, pincel);
        pincel.setColor(Color.BLACK);
        lienzo.drawCircle(ejeX = ejeX + diferenciaX, ejeY = ejeY + diferenciaY, radioInt = radioInt + diferenciaRadio, pincel);

    }

    private void controlRadio() {
        if (radioExt == 260 || radioExt == 180) {
            diferenciaRadio = diferenciaRadio * -1;
        }
    }

    private void paredes() {
        if (ejeX < (radioExt + borde)) {
            ejeX = (radioExt + borde);
            diferenciaX = -1 * diferenciaX;
        } else if (ejeX > (ancho - radioExt - borde)) {
            ejeX = ancho - radioExt - borde;
            diferenciaX = -1 * diferenciaX;
        }
        if (ejeY < (radioExt + borde)) {
            ejeY = (radioExt + borde);
            diferenciaY = -1 * diferenciaY;
        } else if (ejeY > (alto - radioExt - borde)) { //poner -150 si ponemos el navigation bar
            ejeY = (alto - radioExt - borde);  //poner -150 si ponemos el navigation bar
            diferenciaY = -1 * diferenciaY;
        }
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
}
