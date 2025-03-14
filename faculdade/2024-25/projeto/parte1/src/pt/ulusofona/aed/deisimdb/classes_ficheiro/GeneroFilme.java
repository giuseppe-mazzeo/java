package pt.ulusofona.aed.deisimdb.classes_ficheiro;

import pt.ulusofona.aed.deisimdb.Main;

public class GeneroFilme {
    private int genreId;
    private int movieId;



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
        String[][] todosGeneros = Main.todosGeneros;
        String genero = "";

        for (String[] tipoGereno : todosGeneros) {
            if (Integer.parseInt(tipoGereno[0]) == genreId) {
                genero = tipoGereno[1];
                break;
            }
        }

        return movieId + " | " + genero;
    }
}
