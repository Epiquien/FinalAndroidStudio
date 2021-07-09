package com.example.finalvideojuego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityDetalleEntrenador extends AppCompatActivity {

    Button siguienteCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_entrenador);

        siguienteCrear=(Button)findViewById(R.id.btnCrearPokemons);

        siguienteCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguienteCrear = new Intent(ActivityDetalleEntrenador.this, ActivityCrearPokemon.class);
                startActivity(siguienteCrear);
            }
        });
    }
}