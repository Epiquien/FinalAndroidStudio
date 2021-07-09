package com.example.finalvideojuego.Services;

import com.example.finalvideojuego.Entities.Entrenador;
import com.example.finalvideojuego.Entities.Pokemon;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegistrandoEntrenadorService {
        @POST("entrenador/22599")
                Call<Void> CreateEntrenador(@Body Entrenador entrenador);

        @POST("/pokemons/22599/crear")
                Call<Void> CreatePokemon(@Body Pokemon pokemon);
}
