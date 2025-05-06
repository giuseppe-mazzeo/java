package pt.ulusofona.aed.deisimdb.data_classes;

import java.util.ArrayList;
import java.util.Collections;

public class Filme {
    private final int movieId;
    private final String movieName;
    private final float movieDuration;
    private final long movieBudget;
    private final String movieReleaseDate;
    private String toString;
    private int numAtoresMasculino = 0;
    private int numAtoresFeminino = 0;
    private final ArrayList<String> generosAssociados;



    public Filme(int movieId, String movieName, float movieDuration, long movieBudget, String movieReleaseDate) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDuration = movieDuration;
        this.movieBudget = movieBudget;
        this.movieReleaseDate = movieReleaseDate;
        this.generosAssociados = new ArrayList<>();
    }



    public void accNumAtoresEnvolvidos(String actorGender) {
        if (actorGender.equals("Masculino")) {
            numAtoresMasculino++;
        } else {
            numAtoresFeminino++;
        }
    }

    public void accGenerosAssociados(String genero) {
        generosAssociados.add(genero);
    }

    public void verificarIdMaior1000() {
        String[] movieReleaseDate = this.movieReleaseDate.split("-"); // Serve para inverter, ou seja, antes estava dia-mes-ano, porém, agora vou retornar como ano-mes-dia
        String generos;

        if (movieId < 1000) {
            generos = String.join(", ", generosAssociados);
        } else {
            generos = generosAssociados.size() + "";
        }

        toString = movieId + " | " + movieName + " | " + movieReleaseDate[2] + "-" + movieReleaseDate[1] + "-" + movieReleaseDate[0] + " | " + generos + " | " + "[[]]" + " | " + numAtoresMasculino + " | " + numAtoresFeminino;
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



    @Override
    public String toString() {
        return toString;
    }
}
