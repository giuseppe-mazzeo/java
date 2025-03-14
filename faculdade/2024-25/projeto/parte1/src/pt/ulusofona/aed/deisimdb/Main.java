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
    public static String[][] todosGeneros = new String[20][2];
    //
    static int[] linhaOK = new int[6];
    static int[] linhaNOK = new int[6];
    static int[] primeiraLinhaErrada = new int[] {-1, -1, -1, -1, -1, -1};
    //
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Função que lê ficheiros e guarda as informações
    // retornou false -> Ficheiro não foi encontrado, logo, não leu o ficheiro (ficheiro não existe ou o formato não é .csv)
    // retornou true -> Ficheiro existe, logo, leu ficheiro (mesmo que tenha pequenos erros dentro do ficheiro, por exemplo: filmes com id repetidos, colunas a mais ou a menos, etc)
    //
    public static boolean parseFiles(File ficheiro) {
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


        // Ficheiro -> actors
        if (ficheiro.getName().equals("actors.csv")) {
            listaAtores.clear();

            while (scanner.hasNext()) {
                linha = scanner.nextLine();
                dataLinha = linha.split(",");


                if (dataLinha.length != 4) { // Tem mais ou menos informações do que deveria
                    acrescentarLinhaNOK(0);
                    continue;
                }

                listaAtores.add(new Ator(
                        Integer.parseInt(dataLinha[0].trim()),
                        dataLinha[1].trim(),
                        dataLinha[2].trim(),
                        Integer.parseInt(dataLinha[3].trim())
                ));

                linhaOK[0]++;
            }
        }


        // Ficheiro -> directors
        if (ficheiro.getName().equals("directors.csv")) {
            listaDiretores.clear();

            while (scanner.hasNext()) {
                linha = scanner.nextLine();
                dataLinha = linha.split(",");

                if (dataLinha.length != 3) { // Tem mais ou menos informações do que deveria
                    acrescentarLinhaNOK(1);
                    continue;
                }

                listaDiretores.add(new Diretor(
                        Integer.parseInt(dataLinha[0].trim()), // directorId
                        dataLinha[1].trim(), // directorName
                        Integer.parseInt(dataLinha[2].trim()) // movieId
                ));

                linhaOK[1]++;
            }
        }


        // Ficheiro -> genres_movies
        if (ficheiro.getName().equals("genres_movies.csv")) {
            listaGeneroFilmes.clear();

            parseFiles(new File("test-file/genres.csv"));

            while (scanner.hasNext()) {
                linha = scanner.nextLine();
                dataLinha = linha.split(",");

                if (dataLinha.length != 2) { // Tem mais ou menos informações do que deveria
                    acrescentarLinhaNOK(2);
                    continue;
                }

                listaGeneroFilmes.add(new GeneroFilme(
                        Integer.parseInt(dataLinha[0].trim()), // genreId
                        Integer.parseInt(dataLinha[1].trim()) // movieId
                ));

                linhaOK[2]++;
            }
        }


        // Ficheiro -> genres
        if (ficheiro.getName().equals("genres.csv")) {
            if (todosGeneros[0][0] != null) { // Ficheiro já foi lido
                return true;
            }

            for (int linhaAtual = 0; linhaAtual < 20; linhaAtual++) {
                linha = scanner.nextLine();
                dataLinha = linha.split(",");

                if (dataLinha.length != 2) { // Tem mais ou menos informações do que deveria
                    acrescentarLinhaNOK(3);
                    continue;
                }

                todosGeneros[linhaAtual][0] = dataLinha[0].trim(); // genreId
                todosGeneros[linhaAtual][1] = dataLinha[1].trim(); // genreName

                linhaOK[3]++;
            }
        }


        // Ficheiro -> movie_votes
        if (ficheiro.getName().equals("movie_votes.csv")) {
            listaVotosFilmes.clear();

            while (scanner.hasNext()) {
                linha = scanner.nextLine();
                dataLinha = linha.split(",");

                if (dataLinha.length != 3) { // Tem mais ou menos informações do que deveria
                    acrescentarLinhaNOK(4);
                    continue;
                }

                listaVotosFilmes.add(new VotosFilme(
                        Integer.parseInt(dataLinha[0].trim()), // movieId
                        Float.parseFloat(dataLinha[1].trim()), // movieRating
                        Integer.parseInt(dataLinha[2].trim()) // movieRatingCount
                ));


                linhaOK[4]++;
            }
        }


        // Ficheiro -> movies
        if (ficheiro.getName().equals("movies.csv")) {
            listaFilmes.clear();

            while (scanner.hasNext()) {
                linha = scanner.nextLine();
                dataLinha = linha.split(",");

                if (dataLinha.length != 5) { // Tem mais ou menos informações do que deveria
                    acrescentarLinhaNOK(5);
                    continue;
                }

                listaFilmes.add(new Filme(
                        Integer.parseInt(dataLinha[0].trim()), // movieId
                        dataLinha[1].trim(), // movieName
                        Float.parseFloat(dataLinha[2].trim()), // movieDuration
                        Long.parseLong(dataLinha[3].trim()), // movieBudget
                        dataLinha[4].trim() // movieReleaseDate
                ));

                linhaOK[5]++;
            }
        }


        return true; // Ficheiro(os) lido(os) com sucesso!
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Existe algum erro nesta linha:
    // - dados a mais ou a menos
    static void acrescentarLinhaNOK(int ficheiroAtual) {
        if (linhaNOK[ficheiroAtual] == 0) {
            primeiraLinhaErrada[ficheiroAtual] = (linhaOK[ficheiroAtual] + 1);
        }
        linhaNOK[ficheiroAtual]++;
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =




    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Retorna um arraylist com algumas informações dos ficheiros na mesma ordem que é lido no ficheiro
    // Por exemplo: tipoEntidade.ATOR -> irá devolver um arraylist com vários objetos relacionado com o ator
    public static ArrayList getObjects(TipoEntidade tipoEntidade) {
        switch (tipoEntidade) {
            case ATOR:
                return (ArrayList) listaAtores;


            case FILME:
                break;


            case REALIZADOR:
                break;


            case GENERO_CINEMATROGRAFICO:
                break;


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



    public static void main(String[] args) {}
}
