package com.example.finalvideojuego.Entities;

public class Pokemon {
    public String nombrePokemon;
    public String tipoPokemon;
    public String imagenPokemon;
    public int laitud;
    public int longitud;

    public Pokemon(String nombrePokemon, String tipoPokemon, String imagenPokemon, int laitud, int longitud) {
        this.nombrePokemon = nombrePokemon;
        this.tipoPokemon = tipoPokemon;
        this.imagenPokemon = imagenPokemon;
        this.laitud = laitud;
        this.longitud = longitud;
    }
}
