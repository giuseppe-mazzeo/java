package pt.ulusofona.aed.deisimdb.classes_importantes;

public class Filme {
    private final int movieId;
    private final String movieName;
    private final float movieDuration;
    private final long movieBudget;
    private final String movieReleaseDate;
    private int numAtoresEnvolvidos = 0;
    private String toString;



    public Filme(int movieId, String movieName, float movieDuration, long movieBudget, String movieReleaseDate) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDuration = movieDuration;
        this.movieBudget = movieBudget;
        this.movieReleaseDate = movieReleaseDate;
        verificarMovieId();
    }



    public void accNumAtoresEnvolvidos() {
        this.numAtoresEnvolvidos++;
    }

    public void verificarMovieId() {
        String[] movieReleaseDate = this.movieReleaseDate.split("-"); // Serve para inverter, ou seja, antes estava dia-mes-ano, porém, agora vou retornar como ano-mes-dia

        if (movieId < 1000) {
            toString = movieId + " | " + movieName + " | " + movieReleaseDate[2] + "-" + movieReleaseDate[1] + "-" + movieReleaseDate[0] + " | " + numAtoresEnvolvidos;
            return;
        }

        toString = movieId + " | " + movieName + " | " + movieReleaseDate[2] + "-" + movieReleaseDate[1] + "-" + movieReleaseDate[0];
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
