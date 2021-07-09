package com.example.finalvideojuego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityRegistroEntrenador extends AppCompatActivity {

    Button siguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_entrenador);

        siguiente= (Button)findViewById(R.id.btnRegistrarEntrenador);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente= new Intent(ActivityRegistroEntrenador.this, ActivityRegistrandoEntrenador.class);
                startActivity(siguiente);
            }
        });

    }
}