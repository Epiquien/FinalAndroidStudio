package com.example.finalvideojuego;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalvideojuego.Entities.Pokemon;
import com.example.finalvideojuego.Services.RegistrandoEntrenadorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityCrearPokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pokemon);

        Log.i("ERROR ACAA", "hj");

        Button btnSubmit = findViewById(R.id.btnRegistrarPokemon);
        EditText nombrePokemon =findViewById(R.id.etNombrePokemon);
        EditText tipoPokemon = findViewById(R.id.etTipoPokemon);
        EditText imagenPokemon = findViewById(R.id.etImagenPokemon);
        EditText latitud = findViewById(R.id.etLatitud);
        EditText longitud = findViewById(R.id.etLongitud);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegistrandoEntrenadorService service =retrofit.create(RegistrandoEntrenadorService.class);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etNombrePokemon = nombrePokemon.getText().toString();
                String etTipoPokemon = tipoPokemon.getText().toString();
                String etImagenPokemon = imagenPokemon.getText().toString();
                int etLatitud = latitud.getInputType();
                int etLongitud = longitud.getInputType();
                Pokemon pokemon = new Pokemon(etNombrePokemon,etTipoPokemon,etImagenPokemon,etLatitud,etLongitud);
                Call<Void> pokemonCall =service.CreatePokemon(pokemon);

                pokemonCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.i("ERROR ACAA", String.valueOf(response.code()));
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });


    }
}