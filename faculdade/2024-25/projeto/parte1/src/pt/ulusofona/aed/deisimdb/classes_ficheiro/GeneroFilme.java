package pt.ulusofona.aed.deisimdb.classes_ficheiro;

import pt.ulusofona.aed.deisimdb.Main;

import java.util.List;

public class GeneroFilme {
    private int genreId;
    private int movieId;
    private String genreName;



    public GeneroFilme(int genreId, int movieId) {
        this.genreId = genreId;
        this.movieId = movieId;
    }



    public int getGenreId() {
        return genreId;
    }

    public int getMovieId() {
        return movieId;
    }



    @Override
    public String toString() {
        List<Genero> todosGeneros = Main.listaGeneros;

        for (Genero genero : todosGeneros) {
            if (genero.getGenreId() == genreId) {
                genreName = genero.getGenreName();
                break;
            }
        }

        return movieId + " | " + genreName;
    }
}
