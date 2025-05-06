package pt.ulusofona.aed.deisimdb.data_classes;

public class GeneroFilme {
    private final int genreId;
    private final int movieId;
    private final String genreName;



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

    public String getGenreName() {
        return genreName;
    }



    @Override
    public String toString() {
        return genreId + " | " + genreName;
    }
}
