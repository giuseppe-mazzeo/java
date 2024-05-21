package pt.ulusofona.aed.deisiworldmeter;

import java.util.*;

public class Resultado {
    private boolean sucess;
    private String  error;
    private String result;
    private ArrayList<Pais> dadosPaises;
    private ArrayList<Cidade> dadosCidades;
    private ArrayList<Populacao> dadosPopulacao;


    public Resultado(String comando) {
        sucess = true;
        error = null;
        result = null;
        dadosPaises = new ArrayList<>();
        dadosCidades = new ArrayList<>();
        dadosPopulacao = new ArrayList<>();
    }

    public void executaComando(String comando) {
        String[] separador = comando.split(" ");
        int count = 0;
        int sum = 0;
        int id = -1;
        int year_current = -1;
        String alfa2 = "";
        String popMas = "";
        String popFem = "";
        boolean comecaContagem = false;
        ArrayList<String> paisHistoricoCompleto = new ArrayList<>();

        int min_population;
        int num_results;
        int year_start;
        int year_end;
        String country_name = "";
        ArrayList<String> countries_list = new ArrayList<>();

        /* Esta função substitui o papél do 'default' no 'switch', verificando a quantidade de argumentos necessários para cada comando */
        if (!comandoEscritoCorretamente(comando)) {
            sucess = false;
            error = "Comando inválido";
            return;
        }

        switch (separador[0]) {

            /* Exibe no ecrã todos os comandos possíveis para executar esse programa */
            case "HELP":
                result = (
                        "--------------------------\n" +
                        "Commands available:\n" +
                        "COUNT_CITIES <min-population>\n" +
                        "GET_CITIES_BY_COUNTRY <num-results> <country-name>\n" +
                        "SUM_POPULATIONS <countries-list>\n" +
                        "GET_HISTORY <year-start> <year-end> <country-name>\n" +
                        "GET_MISSING_HISTORY <year-start> <year-end>\n" +
                        "GET_MOST_POPULOUS <num-results>\n" +
                        "GET_TOP_CITIES_BY_COUNTRY <num-results> <country-name>\n" +
                        "GET_DUPLICATE_CITIES <min-population>\n" +
                        "GET_COUNTRIES_GENDER_GAP <min-gender-gap>\n" +
                        "GET_TOP_POPULATION_INCREASE <year-start> <year-end>\n" +
                        "GET_DUPLICATE_CITIES_DIFFERENT_COUNTRIES <min-population>\n" +
                        "GET_CITIES_AT_DISTANCE <distance> <country-name>\n" +
                        "INSERT_CITY <alfa2> <city-name> <region> <population>\n" +
                        "REMOVE_COUNTRY <country-name>\n" +
                        "HELP\n" +
                        "QUIT\n" +
                        "--------------------------\n"
                    );
                break;

            /* Quantas cidades eu tenho pelo mundo maior ou igual a X de habitantes (população) */
            case "COUNT_CITIES":
                min_population = Integer.parseInt(separador[1].trim());

                for (Cidade cidade : dadosCidades) {
                    if (cidade.getPopulacao() >= min_population) {
                        count++;
                    }
                }
                result = "" + count;
                break;

            /* Pode escolher quantas cidades quer que apareça (se houver o suficiente para mostrar) de um determinado país */
            case "GET_CITIES_BY_COUNTRY":
                num_results = Integer.parseInt(separador[1].trim());
                country_name = separador[2].trim();

                alfa2 = procuraAlfa2Pais(country_name);

                for (Cidade cidade : dadosCidades) { /* Percorro o banco de dados das Cidades para mostrar no ecrã as cidades do país */
                    if (count == num_results) {
                        break;
                    }

                    if (cidade.getAlfa2().equals(alfa2)) {
                        result += cidade.getCidade() + "\n";
                        count++;
                    }
                }
                break;

            /* Recebe um país (ou uma lista) e calcula a população masculina e feminina no ano de 2024 (atual) */
            case "SUM_POPULATIONS":
                countries_list.addAll(Arrays.asList(separador[1].split(",")));

                for (String current_country : countries_list) {
                    id = procuraIdPais(current_country);

                    for (Populacao populacao : dadosPopulacao) { /* Itero com um ciclo no ficheiro da população para buscar a pop. masc. e fem. no ano de 2024 */
                        if (populacao.getAno() == 2024 && id == populacao.getId()) {
                            sum += populacao.getPopMasculina() + populacao.getPopFeminina();
                        }
                    }
                }

                if (id != -1) {
                    result = "" + sum;
                }

                break;

            /* Recebe como argumento um ANO INÍCIO, um ANO FIM e um PAÍS */
            case "GET_HISTORY":
                year_start = Integer.parseInt(separador[1].trim());
                year_end = Integer.parseInt(separador[2].trim());
                year_current = year_start;
                country_name = separador[3];

                id = procuraIdPais(country_name);

                while (year_current < year_end) {
                    for (Populacao populacao : dadosPopulacao) {
                        if (populacao.getAno() == year_current && populacao.getId() == id) { /* Existe o ano no ficheiro População, então continuo o ciclo */
                            popMas = populacao.getPopMasculina() + "";
                            popMas = popMas.substring(0, popMas.length() - 3);

                            popFem = populacao.getPopFeminina() + "";
                            popFem = popFem.substring(0, popFem.length() - 3);

                            result += year_current + ":" + popMas + "k:" + popFem + "k\n";
                        }
                    }

                    year_current++;
                }

                break;

            /* Este comando fornece-nos os países que tem o histórico, no intervalo dos anos passado por parâmetro, que estam faltando */
            case "GET_MISSING_HISTORY":
                year_start = Integer.parseInt(separador[1].trim());
                year_end = Integer.parseInt(separador[2].trim());

                for (Populacao populacao : dadosPopulacao) { /* Itero pelo ficheiro do começo ao fim para analisar todos os países */
                    /* Vejo se trocou de país (outro país é igual à outro id). Se trocou, preciso atualizar as minhas variáveis para não houver erros */
                    if (id != populacao.getId()) {
                        count = 0;
                        country_name = procuraCountryNamePeloId(populacao.getId());
                        alfa2 = procuraAlfa2NamePeloId(populacao.getId());
                        paisHistoricoCompleto.add(alfa2 + ":" + country_name + "\n");
                        id = procuraIdPais(country_name);
                    }

                    if (populacao.getAno() == year_start) { /* Verifico se o país tem o histórico do 1o ano (passado no parâmetro), se sim, inicio uma contagem */
                        comecaContagem = true;
                    }

                    if (comecaContagem) { /* Essa contagem serve para saber se o país tem o histórico do intervalo dos anos, passado no parâmetro */
                        count++;
                    }

                    if (populacao.getAno() == year_end) { /* Caso verifique o fim do 2o ano (passado no parâmetro), paro a contagem */
                        comecaContagem = false;

                        if (count == (year_end - year_start + 1)) { /* Verifico se tem o histórico de todos os anos utilizando o cálculo matemático da diferença */
                            paisHistoricoCompleto.remove(paisHistoricoCompleto.size() - 1); /* Removo o último elemento adicionado, pois não será mais útil */
                        }
                    }
                }

                for (String data : paisHistoricoCompleto) {
                    result += data;
                }

                break;

            /* Apresenta as cidades mais populosas, em ordem decrescente. O parâmetro indica quantas cidades deve mostrar. Só aparece uma cidade por país */
            case "GET_MOST_POPULOUS":
                num_results = Integer.parseInt(separador[1].trim());

                int maxPopulacao = -1;
                ArrayList<String> data = new ArrayList<>();

                for (Cidade cidade : dadosCidades) { /* Analiso o bando de dados das Cidades por completo */
                    /* Vejo se trocou de país (outro país é igual à outro alfa2). Se trocou, preciso atualizar as minhas variáveis para não houver erros */
                    if (!alfa2.equals(cidade.getAlfa2())) {
                        maxPopulacao = -1;
                        country_name = procuraCountryNamePeloAlfa2(cidade.getAlfa2());
                        if (!country_name.equals("")) { /* Caso não houver o país no ficheiro País */
                            data.add(country_name + ":" + cidade.getCidade() + ":" + maxPopulacao + "\n");
                        }
                        alfa2 = cidade.getAlfa2();
                    }

                    if (alfa2.equals(cidade.getAlfa2())) {
                        /* Verifico qual é a cidade de um determinado país (existente no ficheiro País) com maior população */
                        if (cidade.getPopulacao() > maxPopulacao && !country_name.equals("")) {
                            maxPopulacao = cidade.getPopulacao();
                            data.remove(data.size() - 1);
                            data.add(country_name + ":" + cidade.getCidade() + ":" + maxPopulacao + "\n"); /* Atualizo o último elemento do arraylist */
                        }
                    }
                }

                data.sort((s1, s2) -> { /* Ordenando o arraylist com uma expressão lambda */
                    int maxPopulacao1 = Integer.parseInt(s1.split(":")[2].trim()); /* Extrair maxPopulacao de s1 */
                    int maxPopulacao2 = Integer.parseInt(s2.split(":")[2].trim()); /* Extrair maxPopulacao de s2 */

                    return Integer.compare(maxPopulacao2, maxPopulacao1); /* Ordenar em ordem decrescente */
                });

                for (int i = 0; i < num_results; i++) {
                    if (i >= data.size()) { /* Caso o valor passado como parâmetro seja maior que o tamanho do array */
                        break;
                    }
                    result += data.get(i);
                }

                break;
        }
    }


    private int procuraIdPais(String country_name) {
        int id = -1;

        for (Pais pais : dadosPaises) { /* Procuro o id do país em questão no banco de dado dos Países */
            if (pais.getNome().equals(country_name)) {
                result = "";
                id = pais.getId();
                break;
            }
        }

        if (id == -1) { /* Caso o país não existir no banco de dados, sai com mensagem de erro */
            result = "Pais invalido: " + country_name;
        }

        return id;
    }


    private String procuraAlfa2Pais(String country_name) {
        String alfa2 = "";

        for (Pais pais : dadosPaises) { /* Procuro o alfa2 do país em questão no banco de dado dos Países */
            if (pais.getNome().equals(country_name)) {
                result = "";
                alfa2 = pais.getAlfa2();
                break;
            }
        }

        if (alfa2.equals("")) { /* Caso o país não existir no banco de dados, sai com mensagem de erro */
            result = "Pais invalido: " + country_name;
        }

        return alfa2;
    }


    private String procuraCountryNamePeloId(int id) {
        String country_name = "";

        for (Pais pais : dadosPaises) { /* Procuro o nome do país em questão no banco de dado dos Países */
            if (pais.getId() == id) {
                result = "";
                country_name = pais.getNome();
                break;
            }
        }

        if (country_name.equals("")) { /* Caso o país não existir no banco de dados, sai com mensagem de erro */
            result = "Id invalido: " + id;
        }

        return country_name;
    }


    private String procuraAlfa2NamePeloId(int id) {
        String alfa2 = "";

        for (Pais pais : dadosPaises) { /* Procuro o alfa2 do país em questão no banco de dado dos Países */
            if (pais.getId() == id) {
                result = "";
                alfa2 = pais.getAlfa2();
                break;
            }
        }

        if (alfa2.equals("")) { /* Caso o país não existir no banco de dados, sai com mensagem de erro */
            result = "Id invalido: " + id;
        }

        return alfa2;
    }

    private String procuraCountryNamePeloAlfa2(String alfa2) {
        String country_name = "";

        for (Pais pais : dadosPaises) { /* Procuro o nome do país em questão no banco de dado dos Países */
            if (pais.getAlfa2().equals(alfa2)) {
                result = "";
                country_name = pais.getNome();
                break;
            }
        }

        return country_name;
    }



    private boolean comandoEscritoCorretamente(String comando) {
        String[] separador = comando.split(" ");

        /* Este comando não precisa de argumentos */
        if (separador[0].equals("HELP")) {
            return true;

        /* Comandos que precisam de UM argumento */
        } else if ((
                separador[0].equals("COUNT_CITIES") ||
                separador[0].equals("SUM_POPULATIONS") ||
                separador[0].equals("GET_MOST_POPULOUS") ||
                separador[0].equals("GET_DUPLICATE_CITIES") ||
                separador[0].equals("GET_COUNTRIES_GENDER_GAP") ||
                separador[0].equals("GET_DUPLICATE_CITIES_DIFFERENT_COUNTRIES") ||
                separador[0].equals("REMOVE_COUNTRY")
                ) && separador.length == 2)
        {
            return true;

        /* Comandos que precisam de DOIS argumentos */
        } else if ((
                separador[0].equals("GET_CITIES_BY_COUNTRY") ||
                separador[0].equals("GET_MISSING_HISTORY") ||
                separador[0].equals("GET_TOP_CITIES_BY_COUNTRY") ||
                separador[0].equals("GET_TOP_POPULATION_INCREASE") ||
                separador[0].equals("GET_CITIES_AT_DISTANCE")
                ) && separador.length == 3)
        {
            return true;

        /* Este comando precisa de TRÊS argumentos */
        } else if ((separador[0].equals("GET_HISTORY")) && separador.length == 4) {
            return true;

        /* Este comando precisa de QUATRO argumentos */
        } else {
            return ((separador[0].equals("INSERT_CITY")) && separador.length == 5);
        }
    }



    public boolean isSucess() {
        return sucess;
    }

    public String getError() {
        return error;
    }

    public String getResult() {
        return result;
    }

    public void setDadosPaises(ArrayList<Pais> dadosPaises) {
        this.dadosPaises = dadosPaises;
    }

    public void setDadosCidades(ArrayList<Cidade> dadosCidades) {
        this.dadosCidades = dadosCidades;
    }

    public void setDadosPopulacao(ArrayList<Populacao> dadosPopulacao) {
        this.dadosPopulacao = dadosPopulacao;
    }
}