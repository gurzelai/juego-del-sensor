package com.gurzelai.ninjasensor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MiPanel dibujo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pantallaCompleta();
        dibujo = new MiPanel(this);
        setContentView(dibujo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void pantallaCompleta() {
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void dialogoFDJ() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("GAME OVER");
        builder.setPositiveButton("Volver a jugar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dibujo = new MiPanel(MainActivity.this);
                setContentView(dibujo);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }
}