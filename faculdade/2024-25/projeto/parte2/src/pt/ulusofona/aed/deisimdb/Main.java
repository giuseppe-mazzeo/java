package pt.ulusofona.aed.deisimdb;

import java.io.*;
import java.util.*;

public class Main {

    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Variáveis Globais (guarda todas as informações dos ficheiros que são lidos corretamente).
    //
    public static ArrayList<Ator> listaAtores = new ArrayList<>();
    public static ArrayList<Diretor> listaDiretores = new ArrayList<>();
    public static ArrayList<GeneroFilme> listaGeneroFilmes = new ArrayList<>();
    public static ArrayList<VotosFilme> listaVotosFilmes = new ArrayList<>();
    public static ArrayList<Filme> listaFilmes = new ArrayList<>();
    public static ArrayList<Genero> listaGeneros = new ArrayList<>();
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


        //long start = System.currentTimeMillis();
        //long end = System.currentTimeMillis();
        linhaOK = new int[6];
        linhaNOK = new int[6];
        primeiraLinhaErrada = new int[]{-1, -1, -1, -1, -1, -1};
        String linha;
        String[] dataLinha;
        BufferedReader reader;
        HashMap<Integer, HashSet<Ator>> movieIdActor = new HashMap<>(); // key - movieId ; value - atores relacionados ao filme
        HashMap<Integer, HashSet<Diretor>> movieIdDirector = new HashMap<>(); // key - movieId ; value - diretores relacionados ao filme
        HashMap<Integer, String> genreIdGenreName = new HashMap<>(); // key - genreId ; value - genreName
        HashMap<Integer, HashSet<String>> movieIdGenreName = new HashMap<>(); // key - movieId ; value - gêneros relacionados ao filme
        HashMap<Integer, Float> movieIdMovieVote = new HashMap<>(); // key - movieId ; value - movieVote


        //start = System.currentTimeMillis();
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

                // Tem mais ou menos informações do que deveria
                if (dataLinha.length != 4) {
                    acrescentarLinhaNOK(0);
                    continue;
                }

                actorId = Integer.parseInt(dataLinha[0].trim());
                actorName = dataLinha[1].trim();
                actorGender = dataLinha[2].trim();
                movieId = Integer.parseInt(dataLinha[3].trim());
                novoAtor = new Ator(actorId, actorName, actorGender, movieId);

                // Novo código da parte 2.
                // - - -
                // Verifico se já foi "encontrado" um movieId.
                // Se sim ⇾ adiociono um novo Ator relacionado com o movieId.
                // Se não ⇾ crio um novo elemento com o movieId.
                if (!movieIdActor.containsKey(movieId)) {
                    movieIdActor.put(movieId, new HashSet<>());
                }
                movieIdActor.get(movieId).add(novoAtor);
                // - - -

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
        //end = System.currentTimeMillis();
        //System.out.println("Ficheiro Atores " + (end - start) + "ms");


        //start = System.currentTimeMillis();
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

                // Tem mais ou menos informações do que deveria
                if (dataLinha.length != 3) {
                    acrescentarLinhaNOK(1);
                    continue;
                }

                directorId = Integer.parseInt(dataLinha[0].trim());
                directorName = dataLinha[1].trim();
                movieId = Integer.parseInt(dataLinha[2].trim());
                novoDiretor = new Diretor(directorId, directorName, movieId);

                // Novo código da parte 2.
                // - - -
                // Verifico se já foi "encontrado" um movieId.
                // Se sim ⇾ adiociono um novo Diretor relacionado com o movieId.
                // Se não ⇾ crio um novo elemento com o movieId.
                if (!movieIdDirector.containsKey(movieId)) {
                    movieIdDirector.put(movieId, new HashSet<>());
                }
                movieIdDirector.get(movieId).add(novoDiretor);
                // - - -

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
        //end = System.currentTimeMillis();
        //System.out.println("Ficheiro Diretores " + (end - start) + "ms");


        //start = System.currentTimeMillis();
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

                // Tem mais ou menos informações do que deveria
                if (dataLinha.length != 2) {
                    acrescentarLinhaNOK(3);
                    continue;
                }

                genreId = Integer.parseInt(dataLinha[0].trim());
                genreName = dataLinha[1].trim();
                novoGenero = new Genero(genreId, genreName);

                // Novo código da parte 2.
                // - - -
                // Verifico se já foi "encontrado" um genreId.
                // Se não ⇾ adiociono um novo genreName relacionado com o genreId.
                if (!genreIdGenreName.containsKey(genreId)) {
                    genreIdGenreName.put(genreId, genreName);
                }
                // - - -

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
        //end = System.currentTimeMillis();
        //System.out.println("Ficheiro Generos " + (end - start) + "ms");


        //start = System.currentTimeMillis();
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

                // Tem mais ou menos informações do que deveria
                if (dataLinha.length != 2) {
                    acrescentarLinhaNOK(2);
                    continue;
                }

                genreId = Integer.parseInt(dataLinha[0].trim());
                movieId = Integer.parseInt(dataLinha[1].trim());

                // Novo código da parte 2.
                // - - -
                genreName = genreIdGenreName.get(genreId);
                // Verifico se já foi "encontrado" um movieId.
                // Se não ⇾ adiociono um novo genreName relacionado com o movieId.
                // Se não ⇾ crio um novo elemento com o movieId.
                if (!movieIdGenreName.containsKey(movieId)) {
                    movieIdGenreName.put(movieId, new HashSet<>());
                }
                movieIdGenreName.get(movieId).add(genreName);
                // - - -

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
        //end = System.currentTimeMillis();
        //System.out.println("Ficheiro Generos Filme " + (end - start) + "ms");


        //start = System.currentTimeMillis();
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

                // Tem mais ou menos informações do que deveria
                if (dataLinha.length != 3) {
                    acrescentarLinhaNOK(4);
                    continue;
                }

                movieId = Integer.parseInt(dataLinha[0].trim());
                movieRating = Float.parseFloat(dataLinha[1].trim());
                movieRatingCount = Integer.parseInt(dataLinha[2].trim());
                novoVotosFilme = new VotosFilme(movieId, movieRating, movieRatingCount);

                // Novo código da parte 2.
                // - - -
                // Verifico se já foi "encontrado" um movieId.
                // Se não ⇾ crio um novo elemento com o movieId e já relaciono a um movieVote.
                if (!movieIdMovieVote.containsKey(movieId)) {
                    movieIdMovieVote.put(movieId, movieRating);
                }
                // - - -

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
        //end = System.currentTimeMillis();
        //System.out.println("Ficheiro Votos Filme " + (end - start) + "ms");


        //start = System.currentTimeMillis();
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

                // Tem mais ou menos informações do que deveria
                if (dataLinha.length != 5) {
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

                    // Novo código parte2.
                    // - - -
                    // Grava todos os Atores no respectivo movieId.
                    if (movieIdActor.containsKey(movieId)) {
                        novoFilme.setAtoresAssociados(movieIdActor.get(movieId));
                    }

                    // Faz o mesmo com os Diretores.
                    if (movieIdDirector.containsKey(movieId)) {
                        novoFilme.setDiretoresAssociados(movieIdDirector.get(movieId));
                    }

                    // Faz o mesmo com o movieVote.
                    if (movieIdMovieVote.containsKey(movieId)) {
                        novoFilme.setMovieVote(movieIdMovieVote.get(movieId));
                    }

                    // Faz o mesmo com o genreName.
                    if (movieIdGenreName.containsKey(movieId)) {
                        novoFilme.setGenerosAssociados(movieIdGenreName.get(movieId));
                    }
                    // - - -

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
        //end = System.currentTimeMillis();
        //System.out.println("Ficheiro Filmes " + (end - start) + "ms");

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
        return switch (tipoEntidade) {
            case ATOR -> listaAtores;
            case FILME -> listaFilmes;
            case REALIZADOR -> listaDiretores;
            case GENERO_CINEMATOGRAFICO -> listaGeneros;
            case INPUT_INVALIDO -> obterStringInputInvalido();
            default -> null;
        };
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

        // Gambiarras... 🥲
        if (comandoPorPartes[0].equals("INSERT_ACTOR") || comandoPorPartes[0].equals("INSERT_DIRECTOR")) {
            String dadosNovoElemento = "";
            for (int i = 1; i < comandoPorPartes.length; i++) {
                dadosNovoElemento += " " + comandoPorPartes[i];
            }
            comandoPorPartes[1] = dadosNovoElemento;
        }

        if (comandoPorPartes[0].equals("DISTANCE_BETWEEN_ACTORS")) {
            String atores = "";
            for (int i = 1; i < comandoPorPartes.length; i++) {
                atores += comandoPorPartes[i] + " ";
            }
            comandoPorPartes[1] = atores.split(",")[0];
            comandoPorPartes[2] = atores.split(",")[1];
        }

        switch (comandoPorPartes[0]) {
            // Conta quantos filmes foram feitos no mês e ano passado no parâmetro.
            case "COUNT_MOVIES_MONTH_YEAR":
                String mes = retornaMesCom2Digitos(comandoPorPartes[1]);
                String ano = comandoPorPartes[2];
                int contador = 0;

                for (Filme filme : listaFilmes) {
                    if (filme.getMovieReleaseOnlyMonth().equals(mes) && filme.getMovieReleaseOnlyYear().equals(ano)) {
                        contador++;
                    }
                }

                resultado.comandoCorreto(contador);
                break;


            // Conta quantos filmes o diretor fez.
            case "COUNT_MOVIES_DIRECTOR":
                String nomeDiretor = retornaNomeParametro(comandoPorPartes, 1);
                contador = 0;

                for (Diretor diretor : listaDiretores) {
                    if (diretor.getDirectorName().equals(nomeDiretor)) {
                        contador++;
                    }
                }

                resultado.comandoCorreto(contador);
                break;


            // Conta os números de atores que participaram em filmes desses dois anos.
            case "COUNT_ACTORS_IN_2_YEARS":
                String ano1 = comandoPorPartes[1];
                String ano2 = comandoPorPartes[2];
                HashSet<Integer> actorsIdAno1 = new HashSet<>();
                HashSet<Integer> actorsIdAno2 = new HashSet<>();
                contador = 0;

                for (Filme filme : listaFilmes) {
                    if (filme.getMovieReleaseOnlyYear().equals(ano1)) {
                        actorsIdAno1.addAll(filme.getAllActorsId());
                    }

                    if (filme.getMovieReleaseOnlyYear().equals(ano2)) {
                        actorsIdAno2.addAll(filme.getAllActorsId());
                    }
                }

                for (int actorId : actorsIdAno1) {
                    if (actorsIdAno2.contains(actorId)) {
                        contador++;
                    }
                }

                resultado.comandoCorreto(contador);
                break;


            // Conta todos os filmes no intervalo dos dois anos passado no parâmetro (exclusive) que tenham tido a quantidade de atores no intervalo de 'min' e 'max' (exclusive).
            case "COUNT_MOVIES_BETWEEN_YEARS_WITH_N_ACTORS":
                int anoInicio = Integer.parseInt(comandoPorPartes[1]);
                int anoFim = Integer.parseInt(comandoPorPartes[2]);
                int minAtores = Integer.parseInt(comandoPorPartes[3]);
                int maxAtores = Integer.parseInt(comandoPorPartes[4]);
                int anoFilmeAtual;
                int quantTodosAtores;
                contador = 0;

                for (Filme filme : listaFilmes) {
                    anoFilmeAtual = Integer.parseInt(filme.getMovieReleaseOnlyYear());
                    if (anoInicio < anoFilmeAtual && anoFilmeAtual < anoFim) {
                        quantTodosAtores = filme.getQuantAllActors();
                        if (minAtores < quantTodosAtores && quantTodosAtores < maxAtores) {
                            contador++;
                        }
                    }
                }

                resultado.comandoCorreto(contador);
                break;


            // Retorna todos os nomes dos filmes de um certo ano em que participa o ator.
            // Filmes ordenados por ordem de lançamento
            case "GET_MOVIES_ACTOR_YEAR":
                ano = comandoPorPartes[1];
                String nome = retornaNomeParametro(comandoPorPartes, 2);
                int actorId = -1;
                ArrayList<String> filmes = new ArrayList<>();

                for (Ator ator : listaAtores) {
                    if (ator.getActorFullName().equals(nome)) {
                        actorId = ator.getActorId();
                        break;
                    }
                }

                for (Filme filme : listaFilmes) {
                    if (filme.getMovieReleaseOnlyYear().equals(ano)) {
                        if (filme.getAllActorsId().contains(actorId)) {
                            filmes.add(filme.getMovieReleaseOnlyDay() + filme.getMovieReleaseOnlyMonth() + "-" + filme.getMovieName());
                        }
                    }
                }

                if (filmes.isEmpty()) {
                    resultado.comandoNaoEncontrouResultado();
                } else {
                    Collections.sort(filmes);
                    for (int i = 0; i < filmes.size(); i++) {
                        filmes.set(i, filmes.get(i).substring(5));
                    }

                    resultado.comandoCorreto(filmes);
                }
                break;


            // Retorna todos os filmes cujo nome ou apelidos, ou até letras que pertençam ao nome e/ou apelido, dos atores estão envolvidos.
            // Filmes ordenados por ordem alfabética.
            case "GET_MOVIES_WITH_ACTOR_CONTAINING": // <name>
                nome = retornaNomeParametro(comandoPorPartes, 1);
                filmes = new ArrayList<>();
                HashSet<Integer> actorsId = new HashSet<>();

                for (Ator ator : listaAtores) {
                    if (ator.getActorFullName().contains(nome)) {
                        actorsId.add(ator.getActorId());
                    }
                }

                for (Filme filme : listaFilmes) {
                    for (int currentActorId : actorsId) {
                        if (filme.getAllActorsId().contains(currentActorId) && !filmes.contains(filme.getMovieName())) {
                            filmes.add(filme.getMovieName());
                        }
                    }
                }

                if (filmes.isEmpty()) {
                    resultado.comandoNaoEncontrouResultado();
                } else {
                    Collections.sort(filmes);

                    resultado.comandoCorreto(filmes);
                }
                break;


            // Retorna quais os 4 anos (no máximo) em que foram realizados os filmes cujo título contenha uma certa string passada pelo parâmetro
            // Ordenado pelo n.º de ocorrências, do maior ao menor (ou, se houver empate, pelo ano menor até ao maior).
            case "GET_TOP_4_YEARS_WITH_MOVIES_CONTAINING": // <search-string>
                String stringAlvo = comandoPorPartes[1];
                int valor;
                HashMap<String, Integer> anoOcorrencia = new HashMap<>();
                ArrayList<String> anos = new ArrayList<>();

                for (Filme filme : listaFilmes) {
                    if (filme.getMovieName().contains(stringAlvo)) {
                        ano = filme.getMovieReleaseOnlyYear();
                        if (anoOcorrencia.containsKey(ano)) {
                            valor = anoOcorrencia.get(ano);
                            valor++;
                            anoOcorrencia.put(ano, valor);
                        } else {
                            anoOcorrencia.put(ano, 1);
                        }
                    }
                }

                if (anoOcorrencia.isEmpty()) {
                    resultado.comandoNaoEncontrouResultado();
                } else {
                    int esquerda;
                    int direita;
                    int meio;

                    // Ordenando os anos em forma crescente
                    for (Map.Entry<String, Integer> map : anoOcorrencia.entrySet()) {
                        if (anos.isEmpty()) {
                            anos.add(map.getKey()+":"+map.getValue());
                            continue;
                        }

                        esquerda = 0;
                        direita = anos.size();

                        // Ordenação usando a lógica da pesquisa binária
                        while (esquerda < direita) {
                            meio = (esquerda + direita) / 2;
                            String[] partes = anos.get(meio).split(":");
                            String filme = partes[0];
                            int ocorrencia = Integer.parseInt(partes[1]);

                            if (ocorrencia > map.getValue()) {
                                esquerda = meio + 1;
                            } else if (ocorrencia < map.getValue()) {
                                direita = meio;
                            } else {
                                if (filme.compareTo(map.getKey()) < 0) {
                                    esquerda = meio + 1;
                                } else {
                                    direita = meio;
                                }
                            }
                        }

                        anos.add(esquerda, map.getKey()+":"+map.getValue());
                    }

                    if (anos.size() > 4) {
                        ArrayList<String> primeirosQuatroAnos = new ArrayList<>();

                        for (int i = 0; i < 4; i++) {
                            primeirosQuatroAnos.add(anos.get(i));
                        }

                        resultado.comandoCorreto(primeirosQuatroAnos);
                        break;
                    }

                    resultado.comandoCorreto(anos);
                }
                break;


            // Retorna todos os atores que trabalharam n ou mais vezes com um diretor.
            // Ordenação é indiferente
            case "GET_ACTORS_BY_DIRECTOR":
                int numVezes = Integer.parseInt(comandoPorPartes[1]);
                nomeDiretor = retornaNomeParametro(comandoPorPartes, 2);
                HashMap<String, Integer> nomeAtorOcorrencias = new HashMap<>();

                for (Filme filme : listaFilmes) {
                    if (filme.getAllDirectorsName().contains(nomeDiretor)) {
                        for (String string : filme.getAllActorsName()) {
                            if (nomeAtorOcorrencias.containsKey(string)) {
                                valor = nomeAtorOcorrencias.get(string);
                                valor++;
                                nomeAtorOcorrencias.put(string, valor);
                            } else {
                                nomeAtorOcorrencias.put(string, 1);
                            }
                        }
                    }
                }

                if (nomeAtorOcorrencias.isEmpty()) {
                    resultado.comandoNaoEncontrouResultado();
                } else {
                    ArrayList<String> atores = new ArrayList<>();

                    for (Map.Entry<String, Integer> map : nomeAtorOcorrencias.entrySet()) {
                        if (map.getValue() >= numVezes) {
                            atores.add(map.getKey() + ":" + map.getValue());
                        }
                    }

                    resultado.comandoCorreto(atores);
                }
                break;


            // Retorna qual o mês com mais filmes no ano.
            case "TOP_MONTH_MOVIE_COUNT": // <year>
                ano = comandoPorPartes[1];
                HashMap<String, Integer> mesOcorrencias = new HashMap<>();

                for (Filme filme : listaFilmes) {
                    if (filme.getMovieReleaseOnlyYear().equals(ano)) {
                        mes = filme.getMovieReleaseOnlyMonth();
                        if (mesOcorrencias.containsKey(mes)) {
                            valor = mesOcorrencias.get(mes);
                            valor++;
                            mesOcorrencias.put(mes, valor);
                        } else {
                            mesOcorrencias.put(mes, 1);
                        }
                    }
                }

                if (mesOcorrencias.isEmpty()) {
                    resultado.comandoNaoEncontrouResultado();
                } else {
                    String topMovie = "";
                    int maior = 0;
                    int mapValor;

                    // Procuro pelo hashmap com maior value
                    for (Map.Entry<String, Integer> map : mesOcorrencias.entrySet()) {
                        mapValor = map.getValue();
                        if (mapValor > maior) {
                            maior = mapValor;
                            topMovie = map.getKey() + ":" + mapValor;
                        }
                    }

                    // Remove o '0' no caso de for, por exemplo, 09:2.
                    // Para devolver apenas 9:2.
                    if (topMovie.charAt(0) == '0') {
                        topMovie = topMovie.substring(1);
                    }

                    resultado.comandoCorreto(topMovie);
                }
                break;


            // Retorna os atores com maior classificação do ano de um filme, juntamente com a avaliação/classificação desse mesmo filme.
            // O parâmetro num indica o número máximo de resultados.
            // Ordenado pelo valor de classificação (se houver empate não importa a ordem dos atores).
            case "TOP_VOTED_ACTORS": // <num> <year>
                int num = Integer.parseInt(comandoPorPartes[1]);
                ano = comandoPorPartes[2];
                HashMap<String, Float> atoresVotos = new HashMap<>();
                int esquerda;
                int direita;
                int meio;
                float media;

                for (Filme filme : listaFilmes) {
                    if (filme.getMovieReleaseOnlyYear().equals(ano)) {
                        for (String nomeAtor : filme.getAllActorsName()) {
                            if (atoresVotos.containsKey(nomeAtor)) {
                                media = atoresVotos.get(nomeAtor);
                                media = (media + filme.getMovieVote()) / 2;
                                atoresVotos.put(nomeAtor, media);
                            } else {
                                atoresVotos.put(nomeAtor, filme.getMovieVote());
                            }
                        }
                    }
                }

                if (atoresVotos.isEmpty()) {
                    resultado.comandoNaoEncontrouResultado();
                } else {
                    ArrayList<String> atores = new ArrayList<>();

                    for (Map.Entry<String, Float> map : atoresVotos.entrySet()) {
                        if (atores.isEmpty()) {
                            atores.add(map.getKey()+":"+map.getValue());
                            continue;
                        }

                        esquerda = 0;
                        direita = atores.size();

                        // Ordenação usando a lógica da pesquisa binária
                        while (esquerda < direita) {
                            meio = (esquerda + direita) / 2;

                            if (Float.parseFloat(atores.get(meio).split(":")[1]) > map.getValue()) {
                                esquerda = meio + 1;
                            } else {
                                direita = meio;
                            }
                        }

                        atores.add(esquerda, map.getKey()+":"+map.getValue());
                    }

                    if (atores.size() > num) {
                        ArrayList<String> maximoValores = new ArrayList<>();

                        for (int i = 0; i < num; i++) {
                            maximoValores.add(atores.get(i));
                        }

                        resultado.comandoCorreto(maximoValores);
                        break;
                    }

                    resultado.comandoCorreto(atores);
                }

                break;


            // Retorna os filmes do ano indicado com mais atores do gênero indicado.
            // O parâmetro num indica o número máximo de resultado.
            // O parâmetro gender pode ser M ou F.
            // Ordenado pelo número de atores de forma decrescente (se houver empate, os filmes devem ser ordenados de forma alfabética).
            case "TOP_MOVIES_WITH_MORE_GENDER":
                num = Integer.parseInt(comandoPorPartes[1]);
                ano = comandoPorPartes[2];
                char genero = comandoPorPartes[3].charAt(0);
                HashMap<String, Integer> nomeFilmeOcorrencias = new HashMap<>();
                ArrayList<String> todosFilmes = new ArrayList<>();

                for (Filme filme : listaFilmes) {
                    if (filme.getMovieReleaseOnlyYear().equals(ano)) {
                        if (genero == 'M') {
                            nomeFilmeOcorrencias.put(filme.getMovieName(), filme.getNumMaleActors());
                            //todosFilmes.add(filme.getMovieName() + ":" + filme.getNumMaleActors());
                        } else {
                            nomeFilmeOcorrencias.put(filme.getMovieName(), filme.getNumFemaleActors());
                            //todosFilmes.add(filme.getMovieName() + ":" + filme.getNumFemaleActors());
                        }
                    }
                }

                if (nomeFilmeOcorrencias.isEmpty()) {
                    resultado.comandoNaoEncontrouResultado();
                } else {
                    // Ordenando a partir do número dos gêneros dos(as) atores/atrizes
                    for (Map.Entry<String, Integer> map : nomeFilmeOcorrencias.entrySet()) {
                        if (todosFilmes.isEmpty()) {
                            todosFilmes.add(map.getKey() + ":" + map.getValue());
                            continue;
                        }

                        esquerda = 0;
                        direita = todosFilmes.size();

                        // Ordenação usando a lógica da pesquisa binária
                        while (esquerda < direita) {
                            meio = (esquerda + direita) / 2;
                            String[] partes = todosFilmes.get(meio).split(":");
                            String filme = partes[0];
                            int ocorrencia = Integer.parseInt(partes[1]);

                            if (ocorrencia > map.getValue()) {
                                esquerda = meio + 1;
                            } else if (ocorrencia < map.getValue()) {
                                direita = meio;
                            } else {
                                if (filme.compareTo(map.getKey()) < 0) {
                                    esquerda = meio + 1;
                                } else {
                                    direita = meio;
                                }
                            }
                        }

                        todosFilmes.add(esquerda, map.getKey() + ":" + map.getValue());
                    }

                    if (todosFilmes.size() > num) {
                        ArrayList<String> maximoFilmes = new ArrayList<>();

                        for (int i = 0; i < num; i++) {
                            maximoFilmes.add(todosFilmes.get(i));
                        }

                        resultado.comandoCorreto(maximoFilmes);
                        break;
                    }

                    resultado.comandoCorreto(todosFilmes);
                }
                break;


            // Retorna os filmes do ano indicado que tem uma maior deferença entre os gêneros dos atores.
            // São apenas consideredos filmes com 11 ou mais atores.
            // Ordenado pela porcentagem de atores masculinos e femininos (ignorar empates).
            // Porcentagem:
            case "TOP_MOVIES_WITH_GENDER_BIAS": // <num> <year>
                num = Integer.parseInt(comandoPorPartes[1]);
                ano = comandoPorPartes[2];
                ArrayList<String> generosPorcentagem = new ArrayList<>();
                int numMasculino;
                int numFeminino;
                int porcentagem;

                for (Filme filme : listaFilmes) {
                    if (filme.getMovieReleaseOnlyYear().equals(ano)) {
                        if (filme.getQuantAllActors() < 11) {
                            continue;
                        }

                        numMasculino = filme.getNumMaleActors();
                        numFeminino = filme.getNumFemaleActors();
                        porcentagem = (int) Math.round((Math.max(numMasculino, numFeminino) * 100.0) / (numMasculino + numFeminino));

                        if (numMasculino > numFeminino) {
                            genero = 'M';
                        } else {
                            genero = 'F';
                        }

                        if (generosPorcentagem.isEmpty()) {
                            generosPorcentagem.add(filme.getMovieName() + ":" + genero + ":" + (porcentagem));
                            continue;
                        }

                        esquerda = 0;
                        direita = generosPorcentagem.size();

                        // Ordenação usando a lógica da pesquisa binária
                        while (esquerda < direita) {
                            meio = (esquerda + direita) / 2;

                            if (Integer.parseInt(generosPorcentagem.get(meio).split(":")[2]) > porcentagem) {
                                esquerda = meio + 1;
                            } else {
                                direita = meio;
                            }
                        }

                        generosPorcentagem.add(esquerda, filme.getMovieName() + ":" + genero + ":" + (porcentagem));
                    }
                }

                if (generosPorcentagem.isEmpty()) {
                    resultado.comandoNaoEncontrouResultado();
                } else {
                    if (generosPorcentagem.size() > num) {
                        ArrayList<String> maximoPorcentagem = new ArrayList<>();

                        for (int i = 0; i < num; i++) {
                            maximoPorcentagem.add(generosPorcentagem.get(i));
                        }

                        resultado.comandoCorreto(maximoPorcentagem);
                        break;
                    }

                    resultado.comandoCorreto(generosPorcentagem);
                }
                break;


            // Retorna 6 nomes de diretores.
            // Procura por todos os filmes contidos no intervalo de dois anos (inclusive).
            // Para cada filme, será analisado se têm 2 ou mais diretores com o mesmo apelido/sobrenome (último nome). Assim sendo, assumimos que são da mesma família.
            // Ordernado pelo número de ocorrência, quantos filmes os diretores fizeram juntos (se houver empate é irrelevante a ordem).
            case "TOP_6_DIRECTORS_WITHIN_FAMILY":
                anoInicio = Integer.parseInt(comandoPorPartes[1]);
                anoFim = Integer.parseInt(comandoPorPartes[2]);
                HashMap<String, Integer> nomeDiretorOcorrencias = new HashMap<>();
                ArrayList<String> nomesDiretores = new ArrayList<>();
                int an0;

                for (Filme filme : listaFilmes) {
                    an0 = Integer.parseInt(filme.getMovieReleaseOnlyYear());
                    if (anoInicio <= an0 && an0 <= anoFim) {
                        if (filme.getAllDirectorsName().size() < 2) {
                            continue;
                        }

                        for (String nomeDiretorr : filme.getAllDirectorsName()) {
                            String[] nomePartes = nomeDiretorr.split(" ");
                            String ultimoNome = nomePartes[nomePartes.length - 1];

                            for (String nomeDiretoor : filme.getAllDirectorsName()) {
                                String[] nomePartes2 = nomeDiretoor.split(" ");
                                String ultimoNome2 = nomePartes2[nomePartes2.length - 1];

                                if (nomeDiretoor.equals(nomeDiretorr)) {
                                    continue;
                                }

                                if (ultimoNome.equals(ultimoNome2)) {
                                    if (nomeDiretorOcorrencias.containsKey(nomeDiretoor)) {
                                        valor = nomeDiretorOcorrencias.get(nomeDiretoor);
                                        valor++;
                                        nomeDiretorOcorrencias.put(nomeDiretoor, valor);
                                    } else {
                                        nomeDiretorOcorrencias.put(nomeDiretoor, 1);
                                    }
                                }
                            }
                        }
                    }
                }

                for (Map.Entry<String, Integer> map : nomeDiretorOcorrencias.entrySet()) {
                    if (nomesDiretores.isEmpty()) {
                        nomesDiretores.add(map.getKey() + ":" + map.getValue());
                        continue;
                    }

                    esquerda = 0;
                    direita = nomesDiretores.size();

                    // Ordenação usando a lógica da pesquisa binária
                    while (esquerda < direita) {
                        meio = (esquerda + direita) / 2;

                        if (Integer.parseInt(nomesDiretores.get(meio).split(":")[1]) > map.getValue()) {
                            esquerda = meio + 1;
                        } else {
                            direita = meio;
                        }
                    }

                    nomesDiretores.add(esquerda, map.getKey() + ":" + map.getValue());
                }

                if (nomesDiretores.isEmpty()) {
                    resultado.comandoNaoEncontrouResultado();
                } else {
                    if (nomesDiretores.size() > 6) {
                        ArrayList<String> maximoNomes = new ArrayList<>();

                        for (int i = 0; i < 6; i++) {
                            maximoNomes.add(nomesDiretores.get(i));
                        }

                        resultado.comandoCorreto(maximoNomes);
                        break;
                    }

                    resultado.comandoCorreto(nomesDiretores);
                }

                break;


            // O camando insert altera os dados de memória dos atores, mas não os ficheiros.
            // Insere um novo ator na memória do programa (variável global).
            // Não se pode adicionar ator com id existente.
            // Caso a inserção do novo ator der certo, será mostrada uma mensagem 'OK'. Caso contrário, será mostrada 'Erro'.
            // Os dados do ator passado no comando devem ser separados por ponto e vígula.
            case "INSERT_ACTOR":
                String[] dadosNovoAtor = comandoPorPartes[1].split(";");

                int id = Integer.parseInt(dadosNovoAtor[0].trim());
                String name = dadosNovoAtor[1];
                String gender = dadosNovoAtor[2];
                int movieId = Integer.parseInt(dadosNovoAtor[3]);
                boolean dadosInvalidos = false;
                Ator novoAtor = new Ator(id, name, gender, movieId);

                for (Ator ator : listaAtores) {
                    if (ator.getActorId() == id) {
                        dadosInvalidos = true;
                        break;
                    }
                }

                if (dadosInvalidos) {
                    resultado.comandoInsercaoInvalido();
                    break;
                }

                listaAtores.add(novoAtor);

                for (Filme filme : listaFilmes) {
                    if (filme.getMovieId() == movieId) {
                        filme.adicionarNovoAtor(id, name, gender);
                        break;
                    }
                }

                resultado.comandoInsercaoCorreto();
                break;


            // O camando insert altera os dados de memória dos atores, mas não os ficheiros.
            // Insere um novo diretor na memória do programa (variável global).
            // Não se pode adicionar diretor com id existente.
            // Caso a inserção do novo diretor der certo, será mostrada uma mensagem 'OK'. Caso contrário, será mostrada 'Erro'.
            // Os dados do diretor passado no comando devem ser separados por ponto e vígula.
            case "INSERT_DIRECTOR":
                String[] dadosNovoDiretor = comandoPorPartes[1].split(";");

                id = Integer.parseInt(dadosNovoDiretor[0].trim());
                name = dadosNovoDiretor[1];
                movieId = Integer.parseInt(dadosNovoDiretor[2]);
                dadosInvalidos = false;
                Diretor novoDiretor = new Diretor(id, name, movieId);

                for (Diretor diretor : listaDiretores) {
                    if (diretor.getDirectorId() == id) {
                        dadosInvalidos = true;
                        break;
                    }
                }

                if (dadosInvalidos) {
                    resultado.comandoInsercaoInvalido();
                    break;
                }

                listaDiretores.add(novoDiretor);

                for (Filme filme : listaFilmes) {
                    if (filme.getMovieId() == movieId) {
                        filme.adicionarNovoDiretor(id, name);
                        break;
                    }
                }

                resultado.comandoInsercaoCorreto();
                break;


            // Retorna a distância que entre dois atores que participaram no mesmo filme ou que trabalharam com atores que, por sua vez, trabalharam com outros atores.
            // Ou seja, retorna a distância que o ator1 tem para o ator2.
            // Distância 1 ⇾ tem um ator em que já trabalharam em comum.
            // Distância 0 ⇾ trabalharam juntos no mesmo filme.
            // No result ⇾ Não tem ligação nenhuma ou licações muito próximas.
            case "DISTANCE_BETWEEN_ACTORS": // <actor-1> <actor-2>
                // DISTANCE_BETWEEN_ACTORS John Travolta,Samuel L. Jackson
                int maximaDistancia = 1;
                int distancia;
                String ator1 = comandoPorPartes[1].trim();
                String ator2 = comandoPorPartes[2].trim();

                for (Filme filme : listaFilmes) {
                    if (filme.getAllActorsName().contains(ator1) && filme.getAllActorsName().contains(ator2)) {
                        distancia = 0;
                        break;
                    }
                    
                }

                break;


            // Mostra todos os comandos existentes.
            case "HELP":
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
                break;
        }

        return resultado;
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
            case 1 -> nome[index];
            case 2 -> nome[index] + " " + nome[index + 1];
            case 3 -> nome[index] + " " + nome[index + 1] + " " + nome[index + 2];
            default -> "";
        };
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =


    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        parseFiles(new File("test-files"));
        Result resultado = execute("HELP");

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
