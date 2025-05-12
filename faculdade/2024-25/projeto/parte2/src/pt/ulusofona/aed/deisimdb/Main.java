package pt.ulusofona.aed.deisimdb;

import java.io.*;
import java.util.*;

public class Main {

    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Variáveis Globais (guarda todas as informações dos ficheiros que são lidos corretamente).
    //
    public static List<Ator> listaAtores = new ArrayList<>();
    public static List<Diretor> listaDiretores = new ArrayList<>();
    public static List<GeneroFilme> listaGeneroFilmes = new ArrayList<>();
    public static List<VotosFilme> listaVotosFilmes = new ArrayList<>();
    public static List<Filme> listaFilmes = new ArrayList<>();
    public static List<Genero> listaGeneros = new ArrayList<>();
    //
    public static int[] linhaOK;
    public static int[] linhaNOK;
    public static int[] primeiraLinhaErrada;
    //
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Função que lê ficheiros numa pasta e guarda as informações.
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
        BufferedReader reader;

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> actors
        File ficheiroActors = new File(pasta, "actors.csv");
        //
        try {
            listaAtores.clear();
            reader = new BufferedReader(new FileReader(ficheiroActors));
            reader.readLine(); // Ignora a 1.ª linha (cabeçalho)

            Ator novoAtor;
            int actorId;
            String actorName;
            String actorGender;
            int movieId;

            while ((linha = reader.readLine()) != null) {
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

            reader.close();
        } catch (FileNotFoundException e) {
            // Ficheiro não existe
            return false;
        } catch (IOException e) {
            // Ficheiro vazio ou não tem linhas
            return false;
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> directors
        File ficheiroDirectors = new File(pasta, "directors.csv");
        //
        try {
            listaDiretores.clear();
            reader = new BufferedReader(new FileReader(ficheiroDirectors));
            reader.readLine(); // Ignora a 1.ª linha (cabeçalho)

            Diretor novoDiretor;
            int directorId;
            String directorName;
            int movieId;

            while ((linha = reader.readLine()) != null) {
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

            reader.close();
        } catch (FileNotFoundException e) {
            // Ficheiro não existe
            return false;
        } catch (IOException e) {
            // Ficheiro vazio ou não tem linhas
            return false;
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> genres
        File ficheiroGenero = new File(pasta, "genres.csv");
        //
        try {
            listaGeneros.clear();
            reader = new BufferedReader(new FileReader(ficheiroGenero));
            reader.readLine(); // Ignora a 1.ª linha (cabeçalho)

            Genero novoGenero;
            int genreId;
            String genreName;

            while ((linha = reader.readLine()) != null) {
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

            reader.close();
        } catch (FileNotFoundException e) {
            // Ficheiro não existe
            return false;
        } catch (IOException e) {
            // Ficheiro vazio ou não tem linhas
            return false;
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> genres_movies
        File ficheiroGenerosFilme = new File(pasta, "genres_movies.csv");
        //
        try {
            listaGeneroFilmes.clear();
            reader = new BufferedReader(new FileReader(ficheiroGenerosFilme));
            reader.readLine(); // Ignora a 1.ª linha (cabeçalho)

            GeneroFilme novoGeneroFilme;
            int genreId;
            int movieId;
            String genreName = "";

            while ((linha = reader.readLine()) != null) {
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

            reader.close();
        } catch (FileNotFoundException e) {
            // Ficheiro não existe
            return false;
        } catch (IOException e) {
            // Ficheiro vazio ou não tem linhas
            return false;
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> movie_votes
        File ficheiroVotosFilmes = new File(pasta, "movie_votes.csv");
        //
        try {
            listaVotosFilmes.clear();
            reader = new BufferedReader(new FileReader(ficheiroVotosFilmes));
            reader.readLine(); // Ignora a 1.ª linha (cabeçalho)

            VotosFilme novoVotosFilme;
            int movieId;
            float movieRating;
            int movieRatingCount;

            while ((linha = reader.readLine()) != null) {
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

            reader.close();
        } catch (FileNotFoundException e) {
            // Ficheiro não existe
            return false;
        } catch (IOException e) {
            // Ficheiro vazio ou não tem linhas
            return false;
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ficheiro -> movies
        File ficheiroFilmes = new File(pasta, "movies.csv");
        //
        try {
            listaFilmes.clear();
            reader = new BufferedReader(new FileReader(ficheiroFilmes));
            reader.readLine(); // Ignora a 1.ª linha (cabeçalho)

            // Usando uma variável que guarda todos os movieId consigo diminuir o tempo que o programa demora para "correr" com arquivos grandes
            HashSet<Integer> movieIdUnicos = new HashSet<>();

            Filme novoFilme;
            int movieId;
            String movieName;
            float movieDuration;
            long movieBudget;
            String movieReleaseDate;

            while ((linha = reader.readLine()) != null) {
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

                    // Grava quantos atores (masculino e feminino) estiveram nesse filme.
                    for (Ator ator : listaAtores) {
                        if (ator.getMovieId() == movieId) {
                            novoFilme.accNumAtoresEnvolvidos(ator.getActorGender());
                        }
                    }

                    // Grava o(os) género(os) cinematográfico(os) desse filme.
                    for (GeneroFilme generoFilme : listaGeneroFilmes) {
                        if (generoFilme.getMovieId() == movieId) {
                            for (Genero genero : listaGeneros) {
                                if (genero.getGenreId() == generoFilme.getGenreId()) {
                                    novoFilme.accGenerosAssociados(genero.getGenreName());
                                    break;
                                }
                            }
                        }
                    }

                    // Grava o(os) diretor(es) dessse filme.
                    for (Diretor diretor : listaDiretores) {
                        if (diretor.getMovieId() == movieId) {
                            novoFilme.accDiretoresAssociados(diretor.getDirectorName());
                        }
                    }

                    novoFilme.verificarIdMaior1000();
                    listaFilmes.add(novoFilme);
                    movieIdUnicos.add(movieId);
                }

                linhaOK[5]++;
            }

            reader.close();
        } catch (FileNotFoundException e) {
            // Pasta não existe
            return false;
        } catch (IOException e) {
            // Ficheiro vazio ou não tem linhas
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
                return obterStringInputInvalido();

            default:
                return null;
        }
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Retorna a String como se deve ser para o comando INPUT_INVALIDO
    private static ArrayList<String> obterStringInputInvalido() {
        ArrayList<String> valores = new ArrayList<>();

        valores.add("movies.csv | " + linhaOK[5] + " | " + linhaNOK[5] + " | " + primeiraLinhaErrada[5]);
        valores.add("actors.csv | " + linhaOK[0] + " | " + linhaNOK[0] + " | " + primeiraLinhaErrada[0]);
        valores.add("directors.csv | " + linhaOK[1] + " | " + linhaNOK[1] + " | " + primeiraLinhaErrada[1]);
        valores.add("genres.csv | " + linhaOK[3] + " | " + linhaNOK[3] + " | " + primeiraLinhaErrada[3]);
        valores.add("genres_movies.csv | " + linhaOK[2] + " | " + linhaNOK[2] + " | " + primeiraLinhaErrada[2]);
        valores.add("movie_votes.csv | " + linhaOK[4] + " | " + linhaNOK[4] + " | " + primeiraLinhaErrada[4]);
        return valores;
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Função que serve para receber inputs, que no nosso caso são comandos.
    // Só obtemos falha (ou seja, sucesso = false) quando é introduzido um comando errado.
    // Detalhes dos valores retornados pelos comandos:
    // COUNT - dados numéricos ; GET - lista de resultado ; INSERT - altera os dados em memória (mas não os ficheiros)
    public static Result execute(String comando) {
        Result resultado = new Result();
        String[] comandoPorPartes = comando.split(" ");

        System.out.println(Arrays.toString(comandoPorPartes));

        switch (comandoPorPartes[0]) {
            // Conta quantos filmes foram feitos no mês e ano passado no parâmetro.
            case "COUNT_MOVIES_MONTH_YEAR":
                if (!quantidadeParametroComandoCorreta(comandoPorPartes.length, 3, resultado)) {
                    break;
                }

                String mes = retornaMesCom2Digitos(comandoPorPartes[1].trim());
                String ano = comandoPorPartes[2].trim();
                int contaFilmes = 0;

                for (Filme filme : listaFilmes) {
                    if (filme.getMovieReleaseOnlyMonthAndYear().equals(mes + "-" + ano)) {
                        contaFilmes++;
                    }
                }

                resultado.comandoCorreto(contaFilmes);
                break;


            // Conta quantos filmes o diretor fez.
            case "COUNT_MOVIES_DIRECTOR":
                if (!quantidadeParametroComandoCorreta(comandoPorPartes.length, 2, 4, resultado)) {
                    break;
                }

                String nomeDiretor = retornaNomeParametro(comandoPorPartes,1);
                int contaDiretores = 0;

                for (Diretor diretor : listaDiretores) {
                    if (diretor.getDirectorName().equals(nomeDiretor)) {
                        contaDiretores++;
                    }
                }

                resultado.comandoCorreto(contaDiretores);
                break;


            // Conta os números de atores que participaram em filmes desses dois anos.
            case "COUNT_ACTORS_IN_2_YEARS":
                if (!quantidadeParametroComandoCorreta(comandoPorPartes.length, 3, resultado)) {
                    break;
                }

                String ano1 = comandoPorPartes[1].trim();
                String ano2 = comandoPorPartes[2].trim();
                ArrayList<Integer> atoresAno1 = new ArrayList<>();
                ArrayList<Integer> atoresAno2 = new ArrayList<>();
                String anoFilmeAtual;
                int contaAtores = 0;

                for (Filme filme : listaFilmes) {
                    anoFilmeAtual = filme.getMovieReleaseOnlyYear();
                    if (!anoFilmeAtual.equals(ano1) && !anoFilmeAtual.equals(ano2)) {
                        continue;
                    }

                    if (anoFilmeAtual.equals(ano1)) {
                        for (Ator ator : listaAtores) {
                            if (ator.getMovieId() == filme.getMovieId()) {
                                atoresAno1.add(ator.getActorId());
                            }
                        }
                    }

                    if (anoFilmeAtual.equals(ano2)) {
                        for (Ator ator : listaAtores) {
                            if (ator.getMovieId() == filme.getMovieId()) {
                                atoresAno2.add(ator.getActorId());
                            }
                        }
                    }
                }

                for (int a : atoresAno1) {
                    for (int b : atoresAno2) {
                        if (a == b) {
                            contaAtores++;
                        }
                    }
                }

                resultado.comandoCorreto(contaAtores);
                break;


            // Conta todos os filmes no intervalo dos dois anos passado no parâmetro (exclusive) que tenham tido a quantidade de atores no intervalo de 'min' e 'max'.
            case "COUNT_MOVIES_BETWEEN_YEAR_WITH_N_ACTORS":
                if (!quantidadeParametroComandoCorreta(comandoPorPartes.length, 5, resultado)) {
                    break;
                }

                int anoInicio = Integer.parseInt(comandoPorPartes[1].trim());
                int anoFim = Integer.parseInt(comandoPorPartes[2].trim());
                int minAtores = Integer.parseInt(comandoPorPartes[3].trim());
                int maxAtores = Integer.parseInt(comandoPorPartes[4].trim());
                int anoFilmeAtualInt;
                contaFilmes = 0;

                for (Filme filme : listaFilmes) {
                    anoFilmeAtualInt = filme.getMovieReleaseOnlyYearInt();
                    if (anoInicio < anoFilmeAtualInt && anoFilmeAtualInt < anoFim) {
                        if (minAtores < filme.getAllActors() && filme.getAllActors() < maxAtores) {
                            contaFilmes++;
                        }
                    }
                }

                resultado.comandoCorreto(contaFilmes);
                break;


            // Retorna todos os nomes dos filmes de um certo ano em que participa o ator.
            // Filmes ordenados por ordem de lançamento TODO
            case "GET_MOVIES_ACTORS_YEAR":
                if (!quantidadeParametroComandoCorreta(comandoPorPartes.length, 3, 5, resultado)) {
                    break;
                }

                ano = comandoPorPartes[1].trim();
                String nomeAtor = retornaNomeParametro(comandoPorPartes, 2);
                HashSet<String> filmes = new HashSet<>();

                for (Filme filme : listaFilmes) {
                    if (filme.getMovieReleaseOnlyYear().equals(ano)) {
                        for (Ator ator : listaAtores) {
                            if (ator.getActorFullName().equals(nomeAtor) && ator.getMovieId() == filme.getMovieId()) {
                                filmes.add(filme.getMovieName());
                            }
                        }
                    }
                }

                if (filmes.isEmpty()) {
                    resultado.comandoNaoEncontrouResultado();
                } else {
                    resultado.comandoCorreto(filmes);
                }
                break;


            // Retorna todos os filmes cujo nome dos atores estão envolvidos.
            // Filmes ordenados por ordem alfabética TODO
            case "GET_MOVIES_WITH_ACTOR_CONTAINING":
                if (!quantidadeParametroComandoCorreta(comandoPorPartes.length, 2, 4, resultado)) {
                    break;
                }

                nomeAtor = retornaNomeParametro(comandoPorPartes, 1);
                filmes = new HashSet<>();

                for (Ator ator : listaAtores) {
                    if (ator.getActorFirstName().equals(nomeAtor)) {
                        for (Filme filme : listaFilmes) {
                            if (ator.getMovieId() == filme.getMovieId()) {
                                filmes.add(filme.getMovieName());
                            }
                        }
                    }
                }

                resultado.comandoCorreto(filmes);
                break;


            // Retorna quais os 4 anos (no máximo) em que foram realizados os filmes cujo título contenha uma certa string passada pelo parâmetro
            // Ordenado pelo n.º de ocorrencias, do maior ao menor (ou, se houver empate, pelo ano menor até ao maior) TODO
            case "GET_TOP_4_YEARS_WITH_MOVIES_CONTAINING":
                if (!quantidadeParametroComandoCorreta(comandoPorPartes.length, 2, resultado)) {
                    break;
                }

                String stringAlvo = comandoPorPartes[1].trim();
                int valor;
                HashMap<String, Integer> anoFilme = new HashMap<>();

                for (Filme filme : listaFilmes) {
                    if (filme.getMovieName().toLowerCase().contains(stringAlvo)) {
                        ano = filme.getMovieReleaseOnlyYear();
                        if (anoFilme.containsKey(ano)) {
                            valor = anoFilme.get(ano);
                            valor++;
                            anoFilme.put(ano, valor);
                        } else {
                            anoFilme.put(ano, 1);
                        }
                    }
                }

                if (anoFilme.isEmpty()) {
                    resultado.comandoNaoEncontrouResultado();
                } else {
                    resultado.comandoCorreto(anoFilme);
                }
                break;


            // Retorna todos os atores que trabalharam n (num) vezes ou mais com o diretor.
            case "GET_ACTORS_BY_DIRECTOR":
                if (!quantidadeParametroComandoCorreta(comandoPorPartes.length, 3, 5, resultado)) {
                    break;
                }

                HashMap<String, Integer> atorNumVezes = new HashMap<>();
                int numVezes = Integer.parseInt(comandoPorPartes[1].trim());
                nomeDiretor = retornaNomeParametro(comandoPorPartes, 2);

                for (Diretor diretor : listaDiretores) {
                    if (diretor.getDirectorName().equals(nomeDiretor)) {
                        for (Ator ator : listaAtores) {
                            if (ator.getMovieId() == diretor.getMovieId()) {
                                nomeAtor = ator.getActorFullName();
                                if (atorNumVezes.containsKey(nomeAtor)) {
                                    valor = atorNumVezes.get(nomeAtor);
                                    valor++;
                                    atorNumVezes.put(nomeAtor, valor);
                                } else {
                                    atorNumVezes.put(nomeAtor, 1);
                                }
                            }
                        }
                    }
                }

                if (atorNumVezes.isEmpty()) {
                    resultado.comandoNaoEncontrouResultado();
                } else {
                    HashMap<String, Integer> auxiliar = new HashMap<>();

                    for (Map.Entry<String, Integer> map : atorNumVezes.entrySet()) {
                        if (map.getValue() >= numVezes) {
                            auxiliar.put(map.getKey(), map.getValue());
                        }
                    }

                    resultado.comandoCorreto(auxiliar);
                }
                break;


            // Retorna qual o mês com mais filmes no ano.
            case "TOP_MONTH_MOVIE_COUNT":
                if (!quantidadeParametroComandoCorreta(comandoPorPartes.length, 2, resultado)) {
                    break;
                }

                HashMap<String, Integer> mesValor = new HashMap<>();
                ano = comandoPorPartes[1].trim();

                for (Filme filme : listaFilmes) {
                    if (filme.getMovieReleaseOnlyYear().equals(ano)) {
                        mes = filme.getMovieReleaseMonth();
                        if (mesValor.containsKey(mes)) {
                            valor = mesValor.get(mes);
                            valor++;
                            mesValor.put(mes, valor);
                        } else {
                            mesValor.put(mes, 1);
                        }
                    }
                }

                if (mesValor.isEmpty()) {
                    resultado.comandoNaoEncontrouResultado();
                } else {
                    String string = "";
                    int maior = 0;
                    int mapValor;

                    for (Map.Entry<String, Integer> map : mesValor.entrySet()) {
                        mapValor = map.getValue();
                        if (mapValor > maior) {
                            maior = mapValor;
                            string = map.getKey() + ":" + mapValor;
                        }
                    }

                    resultado.comandoCorreto(string);
                }
                break;

                
            // Mostra todos os comandos existentes.
            case "HELP":
                if (!quantidadeParametroComandoCorreta(comandoPorPartes.length, 1, resultado)) {
                    break;
                }

                System.out.println("""
                        \
                        Comandos disponíveis:
                        COUNT_MOVIES_MONTH_YEAR <month> <year>
                        COUNT_MOVIES_DIRECTOR <full-name>
                        COUNT_ACTORS_IN_2_YEARS <year-1> <year-2>
                        COUNT_MOVIES_BETWEEN_YEARS_WITH_N_ACTORS <year-start> <year-end> <min> <max>
                        GET_MOVIES_ACTOR_YEAR <year> <full-name>
                        GET_MOVIES_WITH_ACTOR_CONTAINING <name>
                        GET_TOP_4_YEARS_WITH_MOVIES_CONTAINING <search-string>
                        GET_ACTORS_BY_DIRECTOR <num> <full-name>
                        TOP_MONTH_MOVIE_COUNT <year>
                        TOP_VOTED_ACTORS <num> <year>
                        TOP_MOVIES_WITH_MORE_GENDER <num> <year> <gender>
                        TOP_MOVIES_WITH_GENDER_BIAS <num> <year>
                        TOP_6_DIRECTORS_WITHIN_FAMILY <year-start> <year-end>
                        INSERT_ACTOR <id>;<name>;<gender>;<movie-id>
                        INSERT_DIRECTOR <id>;<name>;<movie-id>
                        DISTANCE_BETWEEN_ACTORS <actor-1>,<actor-2>
                        HELP
                        QUIT""");
                break;

            default:
                resultado.comandoInvalido();
        }

        return resultado;
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Verificar se a quantidade de parâmetro está certa (incluindo o nome do comando).
    private static boolean quantidadeParametroComandoCorreta(int quantidadeParametroAtual, int quantidadeEsperada, Result resultado) {
        if (quantidadeParametroAtual != quantidadeEsperada) {
            resultado.comandoInvalido();
            return false;
        }
        return true;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Usamos essa função a baixo quando é passado um nome pelo parâmetro do comando.
    // Porque o nome pode ser um nome completo com dois ou três nomes, ou um nome simples com apenas um nome.
    // Nome completo - Leonardo DiCaprio, Samuel L. Jackson
    // Nome simples - Tom
    private static boolean quantidadeParametroComandoCorreta(int quantidadeParametroAtual, int quantidadeEsperadaMin, int quantidadeEsperadaMax, Result resultado) {
        if (!(quantidadeEsperadaMin <= quantidadeParametroAtual && quantidadeParametroAtual <= quantidadeEsperadaMax)) {
            resultado.comandoInvalido();
            return false;
        }
        return true;
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Corrigindo, deve ficar 06, por exemplo.
    private static String retornaMesCom2Digitos(String mes) {
        return (mes.length() == 1) ? ("0" + mes) : mes;
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Retorna o nome passado pelo parâmetro.
    // Verificamos se o nome é grande (com três ou dois nomes simples) ou um nome curto (com apenas um nome).
    // index começa no index do comandoPorPartes onde tem o nome.
    private static String retornaNomeParametro(String[] nome, int index) {
        return switch (nome.length - index) {
            case 1 -> nome[index].trim();
            case 2 -> nome[index].trim() + " " + nome[index + 1].trim();
            case 3 -> nome[index].trim() + " " + nome[index + 1].trim() + " " + nome[index + 2].trim();
            default -> "";
        };
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =



    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        Result resultado = execute("HELP");
        parseFiles(new File("test-files"));

        do {
            System.out.print("> ");
            line = in.nextLine();

            if (line != null && !line.equals("QUIT")) {
                resultado = execute(line);

                if (!resultado.getSuccess()) {
                    System.out.println("Error: " + resultado.getError());
                } else {
                    System.out.println(resultado.getResult());
                }
            }
        } while (line != null && !line.equals("QUIT"));

        /*
        long start = System.currentTimeMillis();
        parseFiles(new File("test-files"));
        long end = System.currentTimeMillis();
        System.out.println("Tempo: " + (end - start) + " ms");
        for (Object a : getObjects(TipoEntidade.FILME)) {
            System.out.println(a);
        }

        System.out.println("--");
        System.out.println(getObjects(TipoEntidade.REALIZADOR));
        System.out.println("--");
        System.out.println(getObjects(TipoEntidade.ATOR));
        System.out.println("--");
        System.out.println(getObjects(TipoEntidade.GENERO_CINEMATOGRAFICO));
        System.out.println("--");

        /*
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

        parseFiles(new File("."));

        parseFiles(new File("test-files"));

         */
    }
}
