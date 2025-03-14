package pt.ulusofona.aed.deisimdb.classes_ficheiro;

public class VotosFilme {
    private int movieId;
    private float movieRating;
    private int movieRatingCount;



    public VotosFilme(int movieId, float movieRating, int movieRatingCount) {
        this.movieId = movieId;
        this.movieRating = movieRating;
        this.movieRatingCount = movieRatingCount;
    }



    public int getMovieId() {
        return movieId;
    }

    public float getMovieRating() {
        return movieRating;
    }

    public int getMovieRatingCount() {
        return movieRatingCount;
    }



    @Override
    public String toString() {
        return movieId + " | " + movieRating + " | " + movieRatingCount;
    }
}
