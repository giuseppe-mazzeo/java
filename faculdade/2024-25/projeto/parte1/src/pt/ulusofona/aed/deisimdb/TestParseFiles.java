package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestParseFiles {
    @Test
    public void nomeFicheiroNaoExiste() {
        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.parseFiles(new File("abacate"));
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void formatoFicheiroErrado() {
        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.parseFiles(new File("test-files/actors.abc"));
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void actors_leituraFicheiro() {
        boolean resultadoEsperado = true;
        boolean resultadoAtual = Main.parseFiles(new File("test-files/actors.csv"));
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void directors_leituraFicheiro() {
        boolean resultadoEsperado = true;
        boolean resultadoAtual = Main.parseFiles(new File("test-files/directors.csv"));
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void genres_leituraFicheiro() {
        boolean resultadoEsperado = true;
        boolean resultadoAtual = Main.parseFiles(new File("test-files/genres.csv"));
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void genresMovies_leituraFicheiro() {
        boolean resultadoEsperado = true;
        boolean resultadoAtual = Main.parseFiles(new File("test-files/genres_movies.csv"));
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void moviesVotes_leituraFicheiro() {
        boolean resultadoEsperado = true;
        boolean resultadoAtual = Main.parseFiles(new File("test-files/movie_votes.csv"));
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void movies_leituraFicheiro() {
        boolean resultadoEsperado = true;
        boolean resultadoAtual = Main.parseFiles(new File("test-files/movies.csv"));

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void actors_variasChamadasDeParseFiles() {
        /* Testar com esses dados o ficheiro actors.csv

        actorId,actorName,actorGender,movieId
        88212,Daniel Skowronek,F,22075
        1018947,Maximiliano Hernández,M,76122
        1149,Aleksander Bardini,M,76122
        */

        Main.parseFiles(new File("test-files/actors.csv"));
        Main.parseFiles(new File("test-files/actors.csv"));
        Main.parseFiles(new File("test-files/actors.csv"));

        List<Ator> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add(new Ator(88212, "Daniel Skowronek", "F",22075));
        resultadoEsperado.add(new Ator(1018947, "Maximiliano Hernández", "M", 76122));
        resultadoEsperado.add(new Ator(1149, "Aleksander Bardini", "M", 76122));

        List<Ator> resultadoAtual = Main.listaAtores;

        if (resultadoEsperado.size() != resultadoAtual.size()) {
            throw new IllegalArgumentException("Erro: Dados a mais ou a menos. Esperado: " + resultadoEsperado.size() + ". Atual: " + resultadoAtual.size());
        }

        for (int pos = 0; pos < resultadoEsperado.size(); pos++) {
            if (resultadoEsperado.get(pos).getActorId() != resultadoAtual.get(pos).getActorId() &&
                !resultadoEsperado.get(pos).getActorName().equals(resultadoAtual.get(pos).getActorName()) &&
                !resultadoEsperado.get(pos).getActorGender().equals(resultadoAtual.get(pos).getActorGender()) &&
                resultadoEsperado.get(pos).getMovieId() != resultadoAtual.get(pos).getMovieId()
            ) {
                throw new IllegalArgumentException("Erro: Dados errados");
            }
        }
    }


    @Test
    public void directors_variasChamadasDeParseFiles() {
        /* Testar com esses dados o ficheiro directors.csv

        directorId,directorName,movieId
        1335642,Robert Gordon,319067
        105866,Marco Bellocchio,30239
        19639,André Hunebelle,1871
        */

        Main.parseFiles(new File("test-files/directors.csv"));
        Main.parseFiles(new File("test-files/directors.csv"));
        Main.parseFiles(new File("test-files/directors.csv"));

        List<Diretor> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add(new Diretor(1335642, "Robert Gordon",319067));
        resultadoEsperado.add(new Diretor(105866, "Marco Bellocchio",  30239));
        resultadoEsperado.add(new Diretor(19639, "André Hunebelle", 1871));

        List<Diretor> resultadoAtual = Main.listaDiretores;

        if (resultadoEsperado.size() != resultadoAtual.size()) {
            throw new IllegalArgumentException("Erro: Dados a mais ou a menos. Esperado: " + resultadoEsperado.size() + ". Atual: " + resultadoAtual.size());
        }

        for (int pos = 0; pos < resultadoEsperado.size(); pos++) {
            if (resultadoEsperado.get(pos).getDirectorId() != resultadoAtual.get(pos).getDirectorId() &&
                !resultadoEsperado.get(pos).getDirectorName().equals(resultadoAtual.get(pos).getDirectorName()) &&
                resultadoEsperado.get(pos).getMovieId() != resultadoAtual.get(pos).getMovieId()
            ) {
                throw new IllegalArgumentException("Erro: Dados errados");
            }
        }
    }


    @Test
    public void genres_variasChamadasDeParseFiles() {
        /* Testar com esses dados o ficheiro genres.csv

        genreId,genreName
        3817, Action
        5388, Adventure
        7869, Animation
        4823, Comedy
        7156, Crime
        3836, Documentary
        4986, Drama
        9814, Family
        5148, Fantasy
        4629, Foreign
        8528, History
        8586, Horror
        7553, Music
        351, Mystery
        2635, Romance
        3263, Science Fiction
        2867, TV Movie
        4881, Thriller
        6025, War
        5912, Western
        */

        Main.parseFiles(new File("test-files/genres.csv"));
        Main.parseFiles(new File("test-files/genres.csv"));
        Main.parseFiles(new File("test-files/genres.csv"));

        List<Genero> resultadoEsperado = new ArrayList<>();

        File ficheiro = new File("test-files/genres.csv");
        Scanner scanner;
        try {
            scanner = new Scanner(ficheiro);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String linha = scanner.nextLine();
        for (int linhaAtual = 0; linhaAtual < 20; linhaAtual++) {
            linha = scanner.nextLine();
            String[] dataLinha = linha.split(",");

            resultadoEsperado.add( new Genero(Integer.parseInt(dataLinha[0].trim()), dataLinha[1].trim()));
        }

        List<Genero> resultadoAtual = Main.listaGeneros;

        if (resultadoEsperado.size() != resultadoAtual.size()) {
            throw new IllegalArgumentException("Erro: Dados a mais ou a menos. Esperado: " + resultadoEsperado.size() + ". Atual: " + resultadoAtual.size());
        }

        for (int pos = 0; pos < resultadoEsperado.size(); pos++) {
            if (resultadoEsperado.get(0) == resultadoAtual.get(0) &&
                !resultadoEsperado.get(1).equals(resultadoAtual.get(1))
            ) {
                throw new IllegalArgumentException("Erro: Dados errados");
            }
        }
    }


    @Test
    public void genresMovies_variasChamadasDeParseFiles() {
        /* Testar com esses dados o ficheiro genresMovies.csv

        genreId,movieId
        7869, 149870
        8528, 84084
        4823, 12166
        */

        Main.parseFiles(new File("test-files/genres_movies.csv"));
        Main.parseFiles(new File("test-files/genres_movies.csv"));
        Main.parseFiles(new File("test-files/genres_movies.csv"));

        List<GeneroFilme> resultadoEsperado = new ArrayList<>();
        /*
        resultadoEsperado.add(new GeneroFilme(7869, 149870));
        resultadoEsperado.add(new GeneroFilme(8528, 84084));
        resultadoEsperado.add(new GeneroFilme(4823, 12166));

         */

        List<GeneroFilme> resultadoAtual = Main.listaGeneroFilmes;

        if (resultadoEsperado.size() != resultadoAtual.size()) {
            throw new IllegalArgumentException("Erro: Dados a mais ou a menos. Esperado: " + resultadoEsperado.size() + ". Atual: " + resultadoAtual.size());
        }

        for (int pos = 0; pos < resultadoEsperado.size(); pos++) {
            if (resultadoEsperado.get(pos).getGenreId() != resultadoAtual.get(pos).getGenreId() &&
                resultadoEsperado.get(pos).getMovieId() != resultadoAtual.get(pos).getMovieId()
            ) {
                throw new IllegalArgumentException("Erro: Dados errados");
            }
        }
    }


    @Test
    public void movieVotes_variasChamadasDeParseFiles() {
        /* Testar com esses dados o ficheiro movie_votes.csv

        movieId,movieRating,movieRatingCount
        334681,7.0,5
        172890,5.9,5
        55761,3.0,3
        147264,7.0,2
        */

        Main.parseFiles(new File("test-files/movie_votes.csv"));
        Main.parseFiles(new File("test-files/movie_votes.csv"));
        Main.parseFiles(new File("test-files/movie_votes.csv"));

        List<VotosFilme> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add(new VotosFilme(334681, Float.parseFloat("7.0"), 5));
        resultadoEsperado.add(new VotosFilme(172890, Float.parseFloat("5.9"), 5));
        resultadoEsperado.add(new VotosFilme(55761, Float.parseFloat("3.0"), 3));
        resultadoEsperado.add(new VotosFilme(147264, Float.parseFloat("7.0"), 2));

        List<VotosFilme> resultadoAtual = Main.listaVotosFilmes;

        if (resultadoEsperado.size() != resultadoAtual.size()) {
            throw new IllegalArgumentException("Erro: Dados a mais ou a menos. Esperado: " + resultadoEsperado.size() + ". Atual: " + resultadoAtual.size());
        }

        for (int pos = 0; pos < resultadoEsperado.size(); pos++) {
            if (resultadoEsperado.get(pos).getMovieId() != resultadoAtual.get(pos).getMovieId() &&
                resultadoEsperado.get(pos).getMovieRating() != resultadoAtual.get(pos).getMovieRating() &&
                resultadoEsperado.get(pos).getMovieRatingCount() != resultadoAtual.get(pos).getMovieRatingCount()
            ) {
                throw new IllegalArgumentException("Erro: Dados errados");
            }
        }
    }


    @Test
    public void movies_variasChamadasDeParseFiles() {
        /* Testar com esses dados o ficheiro movies.csv

        movieId,movieName,movieDuration,movieBudget,movieReleaseDate
        56429,Up from the Depths, 85.0,0 ,01-06-1979
        95853,Bunny Drop, 114.0,0 ,20-08-2011
        90532,Shala, 141.0,0 ,20-01-2011
        13460,Doomsday, 108.0,30000000 ,14-03-2008
        */

        Main.parseFiles(new File("test-files/movies.csv"));
        Main.parseFiles(new File("test-files/movies.csv"));
        Main.parseFiles(new File("test-files/movies.csv"));

        List<Filme> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add(new Filme(56429, "Up from the Depths", Float.parseFloat("85.0"), 0,  "01-06-1979"));
        resultadoEsperado.add(new Filme(95853, "Bunny Drop", Float.parseFloat("114.0"), 0,  "20-08-2011"));
        resultadoEsperado.add(new Filme(90532, "Shala", Float.parseFloat("114.0"), 0,  "20-01-2011"));
        resultadoEsperado.add(new Filme(13460, "Doomsday", Float.parseFloat("108.0"), 30000000,  "14-03-2008"));

        List<Filme> resultadoAtual = Main.listaFilmes;

        if (resultadoEsperado.size() != resultadoAtual.size()) {
            throw new IllegalArgumentException("Erro: Dados a mais ou a menos. Esperado: " + resultadoEsperado.size() + ". Atual: " + resultadoAtual.size());
        }

        for (int pos = 0; pos < resultadoEsperado.size(); pos++) {
            if (resultadoEsperado.get(pos).getMovieId() != resultadoAtual.get(pos).getMovieId() &&
                !resultadoEsperado.get(pos).getMovieName().equals(resultadoAtual.get(pos).getMovieName()) &&
                resultadoEsperado.get(pos).getMovieDuration() != resultadoAtual.get(pos).getMovieDuration() &&
                resultadoEsperado.get(pos).getMovieBudget() != resultadoAtual.get(pos).getMovieBudget() &&
                !resultadoEsperado.get(pos).getMovieReleaseDate().equals(resultadoAtual.get(pos).getMovieReleaseDate())
            ) {
                throw new IllegalArgumentException("Erro: Dados errados");
            }
        }
    }
}
