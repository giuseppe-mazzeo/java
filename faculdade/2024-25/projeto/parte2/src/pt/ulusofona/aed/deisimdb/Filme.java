package pt.ulusofona.aed.deisimdb;

import java.util.HashSet;

public class Filme {
    private final int movieId;
    private final String movieName;
    private final float movieDuration;
    private final long movieBudget;
    private final String movieReleaseDate;
    private String toString;
    private int numAtoresMasculino = 0;
    private int numAtoresFeminino = 0;
    private final HashSet<String> generosAssociados;
    private final HashSet<String> diretoresAssociados;



    public Filme(int movieId, String movieName, float movieDuration, long movieBudget, String movieReleaseDate) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDuration = movieDuration;
        this.movieBudget = movieBudget;
        this.movieReleaseDate = movieReleaseDate;
        this.generosAssociados = new HashSet<>();
        this.diretoresAssociados = new HashSet<>();
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

    public void accDiretoresAssociados(String diretor) {
        diretoresAssociados.add(diretor);
    }

    public void verificarIdMaior1000() {
        String[] movieReleaseDate = this.movieReleaseDate.split("-"); // Serve para inverter, ou seja, antes estava dia-mes-ano, porém, agora vou retornar como ano-mes-dia
        String generos, diretores;

        if (movieId < 1000) {
            generos = String.join(",", generosAssociados);
            diretores = String.join(",", diretoresAssociados);
        } else {
            generos = generosAssociados.size() + "";
            diretores = diretoresAssociados.size() + "";
        }

        toString = movieId + " | " + movieName + " | " + movieReleaseDate[2] + "-" + movieReleaseDate[1] + "-" + movieReleaseDate[0] + " | " + generos + " | " + diretores + " | " + numAtoresMasculino + " | " + numAtoresFeminino;
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

    public String getMovieReleaseOnlyYear() {
        return movieReleaseDate.substring(6, 10);
    }

    public int getMovieReleaseOnlyYearInt() {
        return Integer.parseInt(movieReleaseDate.substring(6,10));
    }

    // O bloco if serve para devolver, por exemplo, o mês no formato 9 e não 09
    public String getMovieReleaseMonth() {
        String string = movieReleaseDate.substring(3,5);

        if (string.charAt(0) == '0') {
            string = string.charAt(1)+"";
        }

        return string;
    }

    // substring(3,10) fica no formato: mes-ano (por exemplo, 12-3456)
    public String getMovieReleaseOnlyMonthAndYear() {
        return movieReleaseDate.substring(3,10);
    }

    public int getAllActors() {
        return (numAtoresMasculino + numAtoresFeminino);
    }



    @Override
    public String toString() {
        return toString;
    }
}
