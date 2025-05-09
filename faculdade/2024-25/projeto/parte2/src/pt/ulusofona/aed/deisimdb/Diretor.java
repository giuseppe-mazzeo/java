package pt.ulusofona.aed.deisimdb;

public class Diretor {
    private final int directorId;
    private final String directorName;
    private final int movieId;



    public Diretor(int directorId, String directorName, int movieId) {
        this.directorId = directorId;
        this.directorName = directorName;
        this.movieId = movieId;
    }



    public int getDirectorId() {
        return directorId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public int getMovieId() {
        return movieId;
    }



    @Override
    public String toString() {
        return directorId + " | " + directorName + " | " + movieId;
    }
}
