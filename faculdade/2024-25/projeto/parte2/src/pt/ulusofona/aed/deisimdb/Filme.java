package pt.ulusofona.aed.deisimdb;

import java.util.HashMap;
import java.util.HashSet;

public class Filme {
    private final int movieId;
    private final String movieName;
    private final float movieDuration;
    private final long movieBudget;
    private final String movieReleaseDate;
    // - - -
    private final String day;
    private final String month;
    private final String year;
    // - - -
    private String toString;
    // - - -
    private int numAtoresMasculino;
    private int numAtoresFeminino;
    // - - -
    private HashMap<Integer, String> idNomesAtoresAssociados;
    private final HashSet<String> generosAssociados;
    private final HashSet<String> diretoresAssociados;



    public Filme(int movieId, String movieName, float movieDuration, long movieBudget, String movieReleaseDate) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDuration = movieDuration;
        this.movieBudget = movieBudget;
        this.movieReleaseDate = movieReleaseDate;

        this.idNomesAtoresAssociados = new HashMap<>();
        this.generosAssociados = new HashSet<>();
        this.diretoresAssociados = new HashSet<>();

        numAtoresMasculino = 0;
        numAtoresFeminino = 0;

        String[] separaData = this.movieReleaseDate.split("-");
        day = separaData[0];
        month = separaData[1];
        year = separaData[2];
    }



    public void accGenerosAssociados(String genero) {
        generosAssociados.add(genero);
    }

    public void accDiretoresAssociados(String diretor) {
        diretoresAssociados.add(diretor);
    }

    public void verificarIdMaior1000() {
        String generos, diretores;

        if (movieId < 1000) {
            generos = String.join(",", generosAssociados);
            diretores = String.join(",", diretoresAssociados);
        } else {
            generos = generosAssociados.size() + "";
            diretores = diretoresAssociados.size() + "";
        }

        toString = movieId + " | " + movieName + " | " + year + "-" + month + "-" + day + " | " + generos + " | " + diretores + " | " + numAtoresMasculino + " | " + numAtoresFeminino;
    }


    public void setIdNomesAtoresAssociados(HashMap<Integer, String> allActorId) {
        this.idNomesAtoresAssociados = allActorId;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public float getMovieDuration() {
        return movieDuration;
    }

    public long getMovieBudget() {
        return movieBudget;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public String getMovieReleaseOnlyDay() {
        return day;
    }

    public String getMovieReleaseOnlyMonth() {
        return month;
    }

    public String getMovieReleaseOnlyYear() {
        return year;
    }

    public HashSet<Integer> getAllActorsId() {
        return new HashSet<>(idNomesAtoresAssociados.keySet());
    }

    public HashSet<String> getAllActorsName() {
        return new HashSet<>(idNomesAtoresAssociados.values());
    }

    public int getQuantAllActors() {
        return idNomesAtoresAssociados.size();
    }

    public HashSet<String> getAllDirectorsName() {
        return diretoresAssociados;
    }

    @Override
    public String toString() {
        return toString;
    }
}
