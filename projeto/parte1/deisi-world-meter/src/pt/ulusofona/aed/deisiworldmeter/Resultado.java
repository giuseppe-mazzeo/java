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
        String alfa2 = "";
        String popMas = "";
        String popFem = "";
        String popCidade = "";
        int count = 0;
        int sum = 0;
        int id = -1;
        int year_current = -1;
        int maxPopulacao = -1;
        int populacaoCidade = 0;
        int popMasInt = 0;
        int popFemInt = 0;
        int popTotalAtual = 0;
        int popTotalAnterior = 0;
        float resultadoFormula = 0;
        boolean comecaContagem = false;
        boolean existePais = false;
        ArrayList<String> paisHistoricoCompleto = new ArrayList<>();
        ArrayList<String> cidadesMaisPopulosas = new ArrayList<>();
        ArrayList<String> cidadesNaoDuplicadas = new ArrayList<>();
        ArrayList<String> cidadesDupli = new ArrayList<>();
        ArrayList<String> paisesGenderGap = new ArrayList<>();
        ArrayList<String> guardaPopEAno = new ArrayList<>();
        ArrayList<String> guardaResultadosDaFormula = new ArrayList<>();

        int min_population = 0;
        int num_results = 0;
        int year_start = 0;
        int year_end = 0;
        int min_gender_gap = 0;
        String country_name = "";
        String city_name = "";
        String region = "";
        String population = "";
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

                for (Cidade cidade : dadosCidades) { /* Analiso o bando de dados das Cidades por completo */
                    /* Vejo se trocou de país (outro país é igual à outro alfa2). Se trocou, preciso atualizar as minhas variáveis para não houver erros */
                    if (!alfa2.equals(cidade.getAlfa2())) {
                        maxPopulacao = -1;
                        country_name = procuraCountryNamePeloAlfa2(cidade.getAlfa2());
                        if (!country_name.equals("")) { /* Caso não houver o país no ficheiro País */
                            cidadesMaisPopulosas.add(country_name + ":" + cidade.getCidade() + ":" + maxPopulacao + "\n");
                        }
                        alfa2 = cidade.getAlfa2();
                    }

                    if (alfa2.equals(cidade.getAlfa2())) {
                        /* Verifico qual é a cidade de um determinado país (existente no ficheiro País) com maior população */
                        if (cidade.getPopulacao() > maxPopulacao && !country_name.equals("")) {
                            maxPopulacao = cidade.getPopulacao();
                            cidadesMaisPopulosas.remove(cidadesMaisPopulosas.size() - 1);
                            cidadesMaisPopulosas.add(country_name + ":" + cidade.getCidade() + ":" + maxPopulacao + "\n"); /* Atualizo o último elemento do arraylist */
                        }
                    }
                }

                cidadesMaisPopulosas.sort((cidade1, cidade2) -> { /* Ordenando o arraylist com uma expressão lambda */
                    int maxPopulacao1 = Integer.parseInt(cidade1.split(":")[2].trim()); /* Extrair maxPopulacao de cidade1 */
                    int maxPopulacao2 = Integer.parseInt(cidade2.split(":")[2].trim()); /* Extrair maxPopulacao de cidade2 */

                    return Integer.compare(maxPopulacao2, maxPopulacao1); /* Ordenar em ordem decrescente */
                });

                for (int i = 0; i < num_results; i++) {
                    if (i >= cidadesMaisPopulosas.size()) { /* Caso o valor passado como parâmetro seja maior que o tamanho do array */
                        break;
                    }
                    result += cidadesMaisPopulosas.get(i);
                }

                break;

            /* Apresenta as cidades mais populosas, em ordem decrescente, de um determinado país. O parâmetro numérico indica quantas cidades deve mostrar. */
            case "GET_TOP_CITIES_BY_COUNTRY":
                num_results = Integer.parseInt(separador[1].trim());
                country_name = separador[2];

                alfa2 = procuraAlfa2Pais(country_name);

                for (Cidade cidade : dadosCidades) { /* Itero pelas cidades */
                    if (alfa2.equals(cidade.getAlfa2())) { /* Percorre as cidades até encontrar o país passado como parâmetro */
                        popCidade = cidade.getPopulacao() + "";
                        if (popCidade.length() > 3) { /* Caso a cidade tiver mais que 999 habitantes */
                            popCidade = popCidade.substring(0, popCidade.length() - 3) + "K";
                        }
                        cidadesMaisPopulosas.add(cidade.getCidade() + ":" + popCidade + "\n"); /* Adiciono a cidade ao array */
                    }
                }

                cidadesMaisPopulosas.sort((cidade1, cidade2) -> { /* Ordenando o arraylist com uma expressão lambda */
                    String popCidade1 = cidade1.split(":")[1].trim();
                    String popCidade2 = cidade2.split(":")[1].trim();

                    if (popCidade1.charAt(popCidade1.length() -1 ) == 'K') { /* Retiro o último caracter, 'K', para não ter problema */
                        popCidade1 = popCidade1.substring(0, popCidade1.length() - 1);
                    }

                    if (popCidade2.charAt(popCidade2.length() -1 ) == 'K') { /* Retiro o último caracter, 'K', para não ter problema */
                        popCidade2 = popCidade2.substring(0, popCidade2.length() - 1);
                    }

                    int maxPopulacao1 = Integer.parseInt(popCidade1.trim()); /* Extrair maxPopulacao de cidade1 */
                    int maxPopulacao2 = Integer.parseInt(popCidade2.trim()); /* Extrair maxPopulacao de cidade2 */

                    /* Ordenar por população em ordem decrescente*/
                    int comparePopulacao = Integer.compare(maxPopulacao2, maxPopulacao1);
                    if (comparePopulacao != 0) {
                        return comparePopulacao;
                    }

                    /* Se as populações são iguais, ordenar por nome da cidade em ordem alfabética */
                    String nomeCidade1 = cidade1.split(":")[0].trim();
                    String nomeCidade2 = cidade2.split(":")[0].trim();
                    return nomeCidade1.compareTo(nomeCidade2);
                });

                if (num_results == -1) { /* Mostrar todas as cidades de um país caso o parâmetro numérico seja -1 */
                    for (String cidadePopulosa : cidadesMaisPopulosas) {
                        result += cidadePopulosa;
                    }
                    break;
                }

                for (int i = 0; i < num_results; i++) {
                    if (i >= cidadesMaisPopulosas.size()) { /* Caso o valor passado como parâmetro seja maior que o tamanho do array */
                        break;
                    }
                    result += cidadesMaisPopulosas.get(i);
                }

                break;

            /* Descobrir todas as cidades cujo nome estaja duplicado a nível mundial, porém, tem que ter, pelo menos, a quantidade da população passado como parâmetro. */
            /* A primeira cidade que aparece no ficheiro é a original, as demais com o mesmo nome são as cópias  */
            case "GET_DUPLICATE_CITIES":
                min_population = Integer.parseInt(separador[1].trim());

                for (Cidade cidade : dadosCidades) {
                    if (cidadesNaoDuplicadas.size() == 0) {
                        cidadesNaoDuplicadas.add(cidade.getCidade());
                        continue;
                    }

                    if (!cidadesNaoDuplicadas.get(count).equals(cidade.getCidade())) {
                        cidadesNaoDuplicadas.add(cidade.getCidade());
                        count++;
                    } else {
                        country_name = procuraCountryNamePeloAlfa2(cidade.getAlfa2());

                        cidadesDupli.add(cidade.getCidade() + " (" + country_name + "," + cidade.getRegiao() + ")" + "-" + cidade.getPopulacao());
                    }
                }

                for (String cidade : cidadesDupli) {
                    populacaoCidade = Integer.parseInt(cidade.split("-")[1]);

                    if (populacaoCidade >= min_population) {
                        result += cidade.split("-")[0] + "\n";
                    }
                }

                if (result.equals("")) {
                    result = "Sem resultados";
                }

                break;

            /* Este comando analisa a discrepância entre a popMas e popFem em cada país (no ano de 2024), a nível mundial */
            case "GET_COUNTRIES_GENDER_GAP":
                min_gender_gap = Integer.parseInt(separador[1].trim());

                for (Populacao populacao : dadosPopulacao) { /* Percorre os dados da População */
                    if (populacao.getAno() == 2024) { /* Vejo apenas os dados do ano atual, 2024 */
                        country_name = procuraCountryNamePeloId(populacao.getId());
                        popMasInt = populacao.getPopMasculina();
                        popFemInt = populacao.getPopFeminina();

                        /* Fórmula -> |popMas - popFem| / popMas + popFem * 100 (valor com 2 casas decimais, sem arredondamentos) */
                        resultadoFormula = (float) Math.abs(popMasInt - popFemInt) / (popMasInt + popFemInt) * 100;

                        /* Locale.US serve para garantir que o ponto seja utilizado como separador decimal, independentes das configurações regionais */
                        paisesGenderGap.add(country_name + ":" + String.format(Locale.US, "%.2f",resultadoFormula));
                    }
                }

                for (String pais : paisesGenderGap) {
                    /* Vejo se a parte inteira do resultado da fórmula é maior ou igual ao número passado como parâmetro */
                    if (Integer.parseInt(pais.split(":")[1].split("\\.")[0]) >= min_gender_gap) {
                        result += pais + "\n";
                    }
                }

                if (result.equals("")) {
                    result = "Sem resultados";
                }

                break;

            /* Revela o aumento da população em um intervalor de anos. Relativamente ao nível mundial, quais foram os CINCO países que aumentaram a sua populção */
            /* É mostrado de forma decrescente. Países com maior diferença em primeiro e com menor fica em último */
            /* popTotal = popMas + popFem */
            /* popTotal (ano) - popTotal (anoAnterior) / popTotal (ano) * 100 (valor com 2 casas decimais, sem arredondamentos) */
            /* Se <0, devemos descartar, pois, não é um aumento populacional */
            case "GET_TOP_POPULATION_INCREASE":
                year_start = Integer.parseInt(separador[1].trim());
                year_end = Integer.parseInt(separador[2].trim());

                for (Populacao populacao : dadosPopulacao) { /* Itero pelo ficheiro do começo ao fim para analisar todos os países */
                    /* Vejo se trocou de país (outro país é igual à outro id). Se trocou, preciso atualizar as minhas variáveis para não houver erros */
                    if (id != populacao.getId()) {
                        country_name = procuraCountryNamePeloId(populacao.getId());
                        id = populacao.getId();
                    }

                    if (populacao.getAno() >= year_start && populacao.getAno() <= year_end) {
                        guardaPopEAno.add(country_name + "-" + populacao.getAno() + "-" + populacao.getPopMasculina() + "-" + populacao.getPopFeminina());
                    }
                }

                for (int i = 0; i < guardaPopEAno.size(); i++) {
                    popTotalAnterior = Integer.parseInt(guardaPopEAno.get(i).split("-")[2].trim()) + Integer.parseInt(guardaPopEAno.get(i).split("-")[2].trim());
                    country_name = guardaPopEAno.get(i).split("-")[0];

                    for (int f = i; f < guardaPopEAno.size(); f++) {
                        if (!country_name.equals(guardaPopEAno.get(f).split("-")[0])) {
                            break;
                        }

                        if (guardaPopEAno.get(i).split("-")[1].trim().equals(guardaPopEAno.get(f).split("-")[1].trim())) {
                            continue;
                        }

                        popTotalAtual = Integer.parseInt(guardaPopEAno.get(f).split("-")[2].trim()) + Integer.parseInt(guardaPopEAno.get(f).split("-")[2].trim());

                        if (popTotalAtual - popTotalAnterior < 0) {
                            continue;
                        }

                        resultadoFormula = (float) (popTotalAtual - popTotalAnterior) / popTotalAtual * 100;

                        guardaResultadosDaFormula.add(country_name + ":" + guardaPopEAno.get(i).split("-")[1].trim() + "-"
                                + guardaPopEAno.get(f).split("-")[1].trim() + ":" + String.format(Locale.US, "%.2f",resultadoFormula) + "%\n");
                    }

                    //TODO ordenar do maior para o menor

                }

                for (int i = 0; i < 5; i++) {
                    result += guardaResultadosDaFormula.get(i);
                }

                break;

            //Adiciona uma cidade para um país correspondente (se existir) somente na estrutura de memória do programa. Este comando não altera o ficheiro!
            case "INSERT_CITY":
                alfa2 = separador[1].trim();
                city_name = separador[2].trim();
                region = separador[3].trim();
                population = separador[4].trim();

                for (Pais pais : dadosPaises) {
                    if (pais.getAlfa2().equals(alfa2)) {
                        existePais = true;
                    }
                }

                if (!existePais) {
                    result = "Pais invalido";
                    break;
                }

                Cidade cidade = new Cidade(alfa2, city_name, region, population, "0.0", "0.0");

                Main.inserirCidade(cidade);

                result = "Inserido com sucesso";
                break;

            //Remove o país passado como parâmetro. Se o país não existir o programa devolve uma mensagem de erro.
            case "REMOVE_COUNTRY":
                country_name = separador[1].trim();

                for (Pais pais : dadosPaises) {
                    if (pais.getNome().equals(country_name)) {
                        existePais = true;
                        Main.removerPais(count);
                        break;
                    }
                    count++;
                }

                if (!existePais) {
                    result = "Pais invalido";
                    break;
                }

                result = "Removido com sucesso";
                break;

            //
            case "GET_DUPLICATE_CITIES_DIFFERENT_COUNTRIES":
                result = "a";
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