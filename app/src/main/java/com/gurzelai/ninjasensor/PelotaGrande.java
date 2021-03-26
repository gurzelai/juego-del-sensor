package com.gurzelai.ninjasensor;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PelotaGrande extends Drawable {

    Paint pincel;
    int radioInt, radioExt;
    float ejeX, ejeY;
    int borde;
    int ancho, alto;

    public PelotaGrande(int ancho, int alto, int borde, Paint pincel) {
        ejeX = ancho / 2;
        ejeY = alto / 2;
        this.borde = borde;
        this.ancho = ancho;
        this.alto = alto;
        this.pincel = pincel;
        radioInt = 150;
        radioExt = 155;
    }


    @Override
    public void draw(@NonNull Canvas lienzo) {
        pincel.setColor(Color.WHITE);
        lienzo.drawCircle(ejeX, ejeY, radioExt, pincel);
        pincel.setColor(Color.BLACK);
        lienzo.drawCircle(ejeX, ejeY, radioInt, pincel);
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
