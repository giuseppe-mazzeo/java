package pt.ulusofona.aed.deisimdb;

import pt.ulusofona.aed.deisimdb.classes_ficheiro.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Variáveis Globais (guarda todas as informações dos ficheiros que são lidas corretamente)
    //
    static List<Ator> listaAtores = new ArrayList<>();
    static List<Diretor> listaDiretores = new ArrayList<>();
    static List<GeneroFilme> listaGeneroFilmes = new ArrayList<>();
    static List<VotosFilme> listaVotosFilmes = new ArrayList<>();
    static List<Filme> listaFilmes = new ArrayList<>();
    public static List<Genero> listaGeneros = new ArrayList<>();
    //
    static int[] linhaOK = new int[6];
    static int[] linhaNOK = new int[6];
    static int[] primeiraLinhaErrada = new int[] {-1, -1, -1, -1, -1, -1};
    //
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Função que lê ficheiros e guarda as informações.
    // Retornou false ⇾ Ficheiro não foi encontrado, logo, não leu o ficheiro (ficheiro não existe ou o formato não é .csv)
    // retornou true ⇾ Ficheiro existe, logo, leu ficheiro (mesmo que tenha pequenos erros dentro do ficheiro, por exemplo: filmes com "id" repetidos, colunas a mais ou a menos, etc)
    //
    public static boolean parseFiles(File ficheiro) {

        // Verifico se representa a um diretório raiz (ou uma pasta)
        if (ficheiro.isDirectory()) {
            File[] ficheiros = ficheiro.listFiles();

            if (ficheiros != null) {
                for (File ficheiroAtual : ficheiros) {
                    if (ficheiroAtual.isFile()) {
                        parseFiles(ficheiroAtual);
                    }
                }
            }
        }

        Scanner scanner;
        try {
            scanner = new Scanner(ficheiro);
        } catch (FileNotFoundException e) {
            // Ficheiro não existe
            return false;
        }

        // Ficheiro existe, logo, irá "correr" o código abaixo
        String linha = scanner.nextLine(); // Ignora a 1a linha
        String[] dataLinha;


        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> actors
        if (ficheiro.getName().equals("actors.csv")) {
            listaAtores.clear();

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

            return true; // Ficheiro lido com sucesso!
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> directors
        if (ficheiro.getName().equals("directors.csv")) {
            listaDiretores.clear();

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

            return true; // Ficheiro lido com sucesso!
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> genres_movies
        if (ficheiro.getName().equals("genres_movies.csv")) {
            listaGeneroFilmes.clear();

            parseFiles(new File("test-file/genres.csv"));

            GeneroFilme novoGeneroFilme;
            int genreId;
            int movieId;

            while (scanner.hasNext()) {
                linha = scanner.nextLine();
                dataLinha = linha.split(",");

                if (dataLinha.length != 2) { // Tem mais ou menos informações do que deveria
                    acrescentarLinhaNOK(2);
                    continue;
                }

                genreId = Integer.parseInt(dataLinha[0].trim());
                movieId = Integer.parseInt(dataLinha[1].trim());
                novoGeneroFilme = new GeneroFilme(genreId, movieId);

                listaGeneroFilmes.add(novoGeneroFilme);

                linhaOK[2]++;
            }

            return true; // Ficheiro lido com sucesso!
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> genres
        if (ficheiro.getName().equals("genres.csv")) {
            listaGeneros.clear();

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

            return true; // Ficheiro lido com sucesso!
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> movie_votes
        if (ficheiro.getName().equals("movie_votes.csv")) {
            listaVotosFilmes.clear();

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

            return true; // Ficheiro lido com sucesso!
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> movies
        if (ficheiro.getName().equals("movies.csv")) {
            listaFilmes.clear();

            // TODO usar hashset futuramente
            // Usando uma variável que guarda todos os movieId consigo diminuir o tempo que o programa demora para "correr" com arquivos grandes
            ArrayList<String> movieIdUnicos = new ArrayList<>();

            Filme novoFilme;
            int movieId = -1;
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

                // Verifico se já existe um movieId na listaFilmes
                // Se não houver ⇾ adiciono à lista e a linha foi lida com sucesso
                // Se houver ⇾ não adiciono à lista, mas mesmo assim a linha foi lida com sucesso
                if (!movieIdUnicos.contains(movieId)) {

                    movieId = Integer.parseInt(dataLinha[0].trim());
                    movieName = dataLinha[1].trim();
                    movieDuration = Float.parseFloat(dataLinha[2].trim());
                    movieBudget = Long.parseLong(dataLinha[3].trim());
                    movieReleaseDate = dataLinha[4].trim();
                    novoFilme = new Filme(movieId, movieName, movieDuration, movieBudget, movieReleaseDate);

                    // Quando o movieId < 1000, terá que mostrar quantos atores estiveram nesse filme
                    if (movieId < 1000) {
                        for (Ator ator : listaAtores) {
                            if (ator.getMovieId() == movieId) {
                                novoFilme.accNumAtoresEnvolvidos();
                            }
                        }
                    }

                    listaFilmes.add(novoFilme);

                    movieIdUnicos.add(movieId + "");
                }

                linhaOK[5]++;
            }

            return true; // Ficheiro lido com sucesso!
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        return false;
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Existe algum erro nesta linha:
    // - dados a mais ou a menos
    static void acrescentarLinhaNOK(int posAtual) {
        if (linhaNOK[posAtual] == 0) {
            primeiraLinhaErrada[posAtual] = (linhaOK[posAtual] + 1);
        }
        linhaNOK[posAtual]++;
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Retorna um arraylist com algumas informações dos ficheiros na mesma ordem lido no ficheiro
    // Por exemplo: tipoEntidade.ATOR → irá devolver um arraylist com vários objetos relacionado com o ator
    public static ArrayList getObjects(TipoEntidade tipoEntidade) {
        switch (tipoEntidade) {
            case ATOR:
                return (ArrayList) listaAtores;

            case FILME:
                return (ArrayList) listaFilmes;

            case REALIZADOR:
                return (ArrayList) listaDiretores;

            case GENERO_CINEMATROGRAFICO:
                return (ArrayList) listaGeneroFilmes;

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
        long start = System.currentTimeMillis();
        parseFiles(new File("."));
        long end = System.currentTimeMillis();
        System.out.println("Tempo: " + (end - start) + " ms");

        System.out.println(listaAtores.size());
        System.out.println(listaDiretores.size());
        System.out.println(listaFilmes.size());
        System.out.println(listaGeneros.size());
        System.out.println(listaGeneroFilmes.size());
        System.out.println(listaVotosFilmes.size());
    }
}
