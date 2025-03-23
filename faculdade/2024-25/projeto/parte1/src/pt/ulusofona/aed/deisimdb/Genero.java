package pt.ulusofona.aed.deisimdb;

public class Genero {
    private int genreId;
    private String genreName;



    public Genero(int genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }



    public int getGenreId() {
        return genreId;
    }

    public String getGenreName() {
        return genreName;
    }
}
