package pt.ulusofona.aed.deisimdb.classes_ficheiro;

public class Filme {
    int movieId;
    String movieName;
    float movieDuration;
    long movieBudget;
    String movieReleaseDate;



    public Filme(int movieId, String movieName, float movieDuration, long movieBudget, String movieReleaseDate) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDuration = movieDuration;
        this.movieBudget = movieBudget;
        this.movieReleaseDate = movieReleaseDate;
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
        String[] movieReleaseDate = this.movieReleaseDate.split("-");

        return movieId + " | " + movieName + " | " + movieReleaseDate[2] + "-" + movieReleaseDate[1] + "-" + movieReleaseDate[0];
    }
}
