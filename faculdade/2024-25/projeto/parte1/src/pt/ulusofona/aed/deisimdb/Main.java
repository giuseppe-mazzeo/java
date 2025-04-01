package pt.ulusofona.aed.deisimdb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Variáveis Globais (guarda todas as informações dos ficheiros que são lidos corretamente)
    //
    static List<Ator> listaAtores = new ArrayList<>();
    static List<Diretor> listaDiretores = new ArrayList<>();
    static List<GeneroFilme> listaGeneroFilmes = new ArrayList<>();
    static List<VotosFilme> listaVotosFilmes = new ArrayList<>();
    static List<Filme> listaFilmes = new ArrayList<>();
    static List<Genero> listaGeneros = new ArrayList<>();
    //
    static int[] linhaOK;
    static int[] linhaNOK;
    static int[] primeiraLinhaErrada;
    //
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Função que lê ficheiros dentro de uma pasta e guarda as informações.
    // Retornou false ⇾ Pasta não foi encontrado, pasta vazia ou não existe nenhum ficheiro necessário para este Projeto.
    // Retornou true ⇾ Pasta existe, logo, leu os ficheiros.
    //
    public static boolean parseFiles(File pasta) {
        // Pasta não existe
        if (!pasta.exists()) {
            return false;
        }

        linhaOK = new int[6];
        linhaNOK = new int[6];
        primeiraLinhaErrada = new int[] {-1, -1, -1, -1, -1, -1};
        String linha;
        String[] dataLinha;

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> actors
        File ficheiroActors = new File(pasta, "actors.csv");
        //
        try {
            listaAtores.clear();
            Scanner scanner = new Scanner(ficheiroActors);
            scanner.nextLine(); // Ignora a 1.ª linha (cabeçalho)

            Ator novoAtor;
            int actorId;
            String actorName;
            String actorGender;
            int movieId;

            while (scanner.hasNext()) {
                linha = scanner.nextLine();
                dataLinha = linha.split(",");

                if (dataLinha.length != 4) { // Tem mais ou menos informações do que deveria
                    acrescentarLinhaNOK(0);
                    continue;
                }

                actorId = Integer.parseInt(dataLinha[0].trim());
                actorName = dataLinha[1].trim();
                actorGender = dataLinha[2].trim();
                movieId = Integer.parseInt(dataLinha[3].trim());
                novoAtor = new Ator(actorId, actorName, actorGender, movieId);

                listaAtores.add(novoAtor);
                linhaOK[0]++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            // Ficheiro não existe
            return false;
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> directors
        File ficheiroDirectors = new File(pasta, "directors.csv");
        //
        try {
            listaDiretores.clear();
            Scanner scanner = new Scanner(ficheiroDirectors);
            scanner.nextLine(); // Ignora a 1.ª linha (cabeçalho)

            Diretor novoDiretor;
            int directorId;
            String directorName;
            int movieId;

            while (scanner.hasNext()) {
                linha = scanner.nextLine();
                dataLinha = linha.split(",");

                if (dataLinha.length != 3) { // Tem mais ou menos informações do que deveria
                    acrescentarLinhaNOK(1);
                    continue;
                }

                directorId = Integer.parseInt(dataLinha[0].trim());
                directorName = dataLinha[1].trim();
                movieId = Integer.parseInt(dataLinha[2].trim());
                novoDiretor = new Diretor(directorId, directorName, movieId);

                listaDiretores.add(novoDiretor);
                linhaOK[1]++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            // Ficheiro não existe
            return false;
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> genres
        File ficheiroGenero = new File(pasta, "genres.csv");
        //
        try {
            listaGeneros.clear();
            Scanner scanner = new Scanner(ficheiroGenero);
            scanner.nextLine(); // Ignora a 1.ª linha (cabeçalho)

            Genero novoGenero;
            int genreId;
            String genreName;

            while (scanner.hasNext()) {
                linha = scanner.nextLine();
                dataLinha = linha.split(",");

                if (dataLinha.length != 2) { // Tem mais ou menos informações do que deveria
                    acrescentarLinhaNOK(3);
                    continue;
                }

                genreId = Integer.parseInt(dataLinha[0].trim());
                genreName = dataLinha[1].trim();
                novoGenero = new Genero(genreId, genreName);

                listaGeneros.add(novoGenero);
                linhaOK[3]++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            // Ficheiro não existe
            return false;
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> genres_movies
        File ficheiroGenerosFilme = new File(pasta, "genres_movies.csv");
        //
        try {
            listaGeneroFilmes.clear();
            Scanner scanner = new Scanner(ficheiroGenerosFilme);
            scanner.nextLine(); // Ignora a 1.ª linha (cabeçalho)

            GeneroFilme novoGeneroFilme;
            int genreId;
            int movieId;
            String genreName = "";

            while (scanner.hasNext()) {
                linha = scanner.nextLine();
                dataLinha = linha.split(",");

                if (dataLinha.length != 2) { // Tem mais ou menos informações do que deveria
                    acrescentarLinhaNOK(2);
                    continue;
                }

                genreId = Integer.parseInt(dataLinha[0].trim());
                movieId = Integer.parseInt(dataLinha[1].trim());

                for (Genero genero : listaGeneros) {
                    if (genero.getGenreId() == genreId) {
                        genreName = genero.getGenreName();
                        break;
                    }
                }

                novoGeneroFilme = new GeneroFilme(genreId, movieId, genreName);

                listaGeneroFilmes.add(novoGeneroFilme);
                linhaOK[2]++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            // Ficheiro não existe
            return false;
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> movie_votes
        File ficheiroVotosFilmes = new File(pasta, "movie_votes.csv");
        //
        try {
            listaVotosFilmes.clear();
            Scanner scanner = new Scanner(ficheiroVotosFilmes);
            scanner.nextLine(); // Ignora a 1.ª linha (cabeçalho)

            VotosFilme novoVotosFilme;
            int movieId;
            float movieRating;
            int movieRatingCount;

            while (scanner.hasNext()) {
                linha = scanner.nextLine();
                dataLinha = linha.split(",");

                if (dataLinha.length != 3) { // Tem mais ou menos informações do que deveria
                    acrescentarLinhaNOK(4);
                    continue;
                }

                movieId = Integer.parseInt(dataLinha[0].trim());
                movieRating = Float.parseFloat(dataLinha[1].trim());
                movieRatingCount = Integer.parseInt(dataLinha[2].trim());
                novoVotosFilme = new VotosFilme(movieId, movieRating, movieRatingCount);

                listaVotosFilmes.add(novoVotosFilme);
                linhaOK[4]++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            // Ficheiro não existe
            return false;
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> movies
        File ficheiroFilmes = new File(pasta, "movies.csv");
        //
        try {
            listaFilmes.clear();
            Scanner scanner = new Scanner(ficheiroFilmes);
            scanner.nextLine(); // Ignora a 1.ª linha (cabeçalho)

            // TODO usar hashset futuramente
            // Usando uma variável que guarda todos os movieId consigo diminuir o tempo que o programa demora para "correr" com arquivos grandes
            ArrayList<Integer> movieIdUnicos = new ArrayList<>();

            Filme novoFilme;
            int movieId;
            String movieName;
            float movieDuration;
            long movieBudget;
            String movieReleaseDate;

            while (scanner.hasNext()) {
                linha = scanner.nextLine();
                dataLinha = linha.split(",");

                if (dataLinha.length != 5) { // Tem mais ou menos informações do que deveria
                    acrescentarLinhaNOK(5);
                    continue;
                }


                movieId = Integer.parseInt(dataLinha[0].trim());

                // Verifico se já existe um movieId na listaFilmes.
                // Se não houver ⇾ guardo as informções do filme em listaFilmes e também guardo o movieId do filme para verificar se exite "ids" duplicados.
                // Se houver ⇾ não adiciono à lista, mas mesmo assim a linha foi lida com sucesso.
                if (!movieIdUnicos.contains(movieId)) {
                    movieName = dataLinha[1].trim();
                    movieDuration = Float.parseFloat(dataLinha[2].trim());
                    movieBudget = Long.parseLong(dataLinha[3].trim());
                    movieReleaseDate = dataLinha[4].trim();
                    novoFilme = new Filme(movieId, movieName, movieDuration, movieBudget, movieReleaseDate);

                    // Quando o movieId < 1000, terá que mostrar quantos atores estiveram nesse filme.
                    if (movieId < 1000) {
                        for (Ator ator : listaAtores) {
                            if (ator.getMovieId() == movieId) {
                                novoFilme.accNumAtoresEnvolvidos();
                            }
                        }
                    }

                    listaFilmes.add(novoFilme);
                    movieIdUnicos.add(movieId);
                }

                linhaOK[5]++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            // Pasta não existe
            return false;
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        return true; // Ficheiros da pasta lidos com sucesso!
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Existe algum erro nesta linha:
    // - dados a mais ou a menos.
    static void acrescentarLinhaNOK(int posAtual) {
        if (linhaNOK[posAtual] == 0) {
            primeiraLinhaErrada[posAtual] = (linhaOK[posAtual] + 1);
        }
        linhaNOK[posAtual]++;
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Retorna um arraylist com algumas informações dos ficheiros na mesma ordem lido no ficheiro.
    // Por exemplo: tipoEntidade.ATOR → irá devolver um arraylist com vários objetos relacionado com o ator.
    public static ArrayList getObjects(TipoEntidade tipoEntidade) {
        switch (tipoEntidade) {
            case ATOR:
                return (ArrayList) listaAtores;

            case FILME:
                return (ArrayList) listaFilmes;

            case REALIZADOR:
                return (ArrayList) listaDiretores;

            case GENERO_CINEMATOGRAFICO:
                return (ArrayList) listaGeneros;

            case INPUT_INVALIDO:
                ArrayList<String> valores = new ArrayList<>();

                valores.add("movies.csv | " + linhaOK[5] + " | " + linhaNOK[5] + " | " + primeiraLinhaErrada[5]);
                valores.add("actors.csv | " + linhaOK[0] + " | " + linhaNOK[0] + " | " + primeiraLinhaErrada[0]);
                valores.add("directors.csv | " + linhaOK[1] + " | " + linhaNOK[1] + " | " + primeiraLinhaErrada[1]);
                valores.add("genres.csv | " + linhaOK[3] + " | " + linhaNOK[3] + " | " + primeiraLinhaErrada[3]);
                valores.add("genres_movies.csv | " + linhaOK[2] + " | " + linhaNOK[2] + " | " + primeiraLinhaErrada[2]);
                valores.add("movie_votes.csv | " + linhaOK[4] + " | " + linhaNOK[4] + " | " + primeiraLinhaErrada[4]);
                return valores;
        }

        return null;
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    public static void main(String[] args) {
        /*
        long start = System.currentTimeMillis();
        parseFiles(new File("test-files"));
        long end = System.currentTimeMillis();
        System.out.println("Tempo: " + (end - start) + " ms");

        System.out.println("filmes - " + listaFilmes.size());

        for (Filme filme : listaFilmes) {
            System.out.println(filme);
        }

        System.out.println("atores - " + listaAtores.size());
        System.out.println("diretores - " + listaDiretores.size());
        System.out.println("generos - " + listaGeneros.size());
        System.out.println("genero-filmes - " + listaGeneroFilmes.size());
        //for (GeneroFilme a : listaGeneroFilmes) {
          //  System.out.println(a);
        //}


        for (Object a : getObjects(TipoEntidade.GENERO_CINEMATOGRAFICO)) {
            System.out.println(a);
        }


        System.out.println("votos-filmes - " + listaVotosFilmes.size());

        System.out.println("\n");

        for (Object a : getObjects(TipoEntidade.INPUT_INVALIDO)) {
            System.out.println(a);
        }
         */



        parseFiles(new File("."));

        parseFiles(new File("test-files"));
    }
}
