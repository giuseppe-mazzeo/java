package pt.ulusofona.aed.deisimdb;

public class Filme {
    private int movieId;
    private String movieName;
    private float movieDuration;
    private long movieBudget;
    private String movieReleaseDate;
    private int numAtoresEnvolvidos = 0;



    public Filme(int movieId, String movieName, float movieDuration, long movieBudget, String movieReleaseDate) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDuration = movieDuration;
        this.movieBudget = movieBudget;
        this.movieReleaseDate = movieReleaseDate;
    }



    public void accNumAtoresEnvolvidos() {
        this.numAtoresEnvolvidos++;
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
        String[] movieReleaseDate = this.movieReleaseDate.split("-"); // Serve para inverter, ou seja, antes estava dia-mes-ano, porém, agora vou retornar como ano-mes-dia

        if (movieId < 1000) {
            return movieId + " | " + movieName + " | " + movieReleaseDate[2] + "-" + movieReleaseDate[1] + "-" + movieReleaseDate[0] + " | " + numAtoresEnvolvidos;
        }

        return movieId + " | " + movieName + " | " + movieReleaseDate[2] + "-" + movieReleaseDate[1] + "-" + movieReleaseDate[0];
    }
}
