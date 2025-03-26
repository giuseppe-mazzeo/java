package pt.ulusofona.aed.deisimdb;

import java.util.List;

public class GeneroFilme {
    private int genreId;
    private int movieId;
    private String genreName;



    public GeneroFilme(int genreId, int movieId, String genreName) {
        this.genreId = genreId;
        this.movieId = movieId;
        this.genreName = genreName;
    }



    public int getGenreId() {
        return genreId;
    }

    public int getMovieId() {
        return movieId;
    }



    @Override
    public String toString() {
        return genreId + " | " + genreName;
    }
}
