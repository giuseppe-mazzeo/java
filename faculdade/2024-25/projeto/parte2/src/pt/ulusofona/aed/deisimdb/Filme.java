package pt.ulusofona.aed.deisimdb;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Filme {
    private final int movieId;
    private final String movieName;
    private final float movieDuration;
    private final long movieBudget;
    private final String movieReleaseDate;
    // - - -
    private float movieVote;
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
    private HashMap<Integer, String> idNomesAtores; // key - actorId ; value - actorName
    private HashMap<Integer, String> idNomesDiretores; // key - directorId ; value - directorName
    private final ArrayList<String> generosAssociados;



    public Filme(int movieId, String movieName, float movieDuration, long movieBudget, String movieReleaseDate) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDuration = movieDuration;
        this.movieBudget = movieBudget;
        this.movieReleaseDate = movieReleaseDate;

        this.idNomesAtores = new HashMap<>();
        this.idNomesDiretores = new HashMap<>();
        this.generosAssociados = new ArrayList<>();

        numAtoresMasculino = 0;
        numAtoresFeminino = 0;

        movieVote = 0;

        String[] separaData = this.movieReleaseDate.split("-");
        day = separaData[0];
        month = separaData[1];
        year = separaData[2];
    }



    public void verificarIdMaior1000() {
        String generos, diretores;
        ArrayList<String> nomesDiretores = new ArrayList<>();

        if (movieId < 1000) {
            Collections.sort(generosAssociados);
            generos = String.join(",", generosAssociados);

            nomesDiretores.addAll(idNomesDiretores.values());
            Collections.sort(nomesDiretores);
            diretores = String.join(",", nomesDiretores);
        } else {
            generos = generosAssociados.size() + "";
            diretores = idNomesDiretores.size() + "";
        }

        toString = movieId + " | " + movieName + " | " + year + "-" + month + "-" + day + " | " + generos + " | " + diretores + " | " + numAtoresMasculino + " | " + numAtoresFeminino;
    }

    public void setAtoresAssociados(HashSet<Ator> atores) {
        int numMasculino = 0;
        int numFeminino = 0;
        HashMap<Integer, String> idNomesAtores = new HashMap<>();

        for (Ator ator : atores) {
            idNomesAtores.put(ator.getActorId(), ator.getActorFullName());

            if (ator.getActorGender().equals("Masculino")) {
                numMasculino++;
            } else {
                numFeminino++;
            }
        }

        this.numAtoresMasculino = numMasculino;
        this.numAtoresFeminino = numFeminino;
        this.idNomesAtores = idNomesAtores;
    }

    public void setDiretoresAssociados(HashSet<Diretor> diretores) {
        HashMap<Integer, String> idNomesDiretores = new HashMap<>();

        for (Diretor diretor : diretores) {
            idNomesDiretores.put(diretor.getDirectorId(), diretor.getDirectorName());
        }

        this.idNomesDiretores = idNomesDiretores;
    }

    public void setGenerosAssociados(HashSet<String> generos) {
        this.generosAssociados.addAll(generos);
    }

    public void setMovieVote(Float movieVote) {
        this.movieVote = movieVote;
    }

    public void setNumAtoresMasculinoEFeminino(int numAtoresMasculino, int numAtoresFeminino) {
        this.numAtoresMasculino = numAtoresMasculino;
        this.numAtoresFeminino = numAtoresFeminino;
    }

    public void adicionarNovoAtor(int actorId, String actorName, String genderActor) {
        idNomesAtores.put(actorId, actorName);

        if (genderActor.equals("M")) {
            numAtoresMasculino++;
        } else {
            numAtoresFeminino++;
        }

        verificarIdMaior1000();
    }

    public void adicionarNovoDiretor(int directorId, String directorName) {
        idNomesDiretores.put(directorId, directorName);

        verificarIdMaior1000();
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

    public float getMovieVote() {
        return movieVote;
    }

    public HashSet<Integer> getAllActorsId() {
        return new HashSet<>(idNomesAtores.keySet());
    }

    public HashSet<String> getAllActorsName() {
        return new HashSet<>(idNomesAtores.values());
    }

    public int getQuantAllActors() {
        return idNomesAtores.size();
    }

    public int getNumMaleActors() {
        return numAtoresMasculino;
    }

    public int getNumFemaleActors() {
        return numAtoresFeminino;
    }

    public HashSet<String> getAllDirectorsName() {
        return new HashSet<>(idNomesDiretores.values());
    }

    @Override
    public String toString() {
        return toString;
    }
}
