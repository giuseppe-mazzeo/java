package pt.ulusofona.aed.deisiworldmeter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static ArrayList<Pais> dadosPaises___;
    private static ArrayList<Cidade> dadosCidades___;
    private static ArrayList<Populacao> dadosPopulacao___;
    private static ArrayList<InputInvalido> inputInvalido = new ArrayList<>();

    public static ArrayList<String> getObjects(TipoEntidade tipo) {
        ArrayList<String> output = new ArrayList<>();

        if (tipo == TipoEntidade.INPUT_INVALIDO) {
            /*  .get(0) -> cidades.csv ||| .get(1) -> paises.csv ||| .get(2) -> populacao.csv */
            output.add(inputInvalido.get(1).toString());
            output.add(inputInvalido.get(0).toString());
            output.add(inputInvalido.get(2).toString());
        } else if (tipo == TipoEntidade.PAIS) {
            for (Pais dadosPais : dadosPaises___) {
                output.add(dadosPais.toString());
            }
        } else if (tipo == TipoEntidade.CIDADE) {
            for (Cidade dadosCidade : dadosCidades___) {
                output.add(dadosCidade.toString());
            }
        }

        return output;
    }


    private static void resetarBancoDados(File ficheiro) {
        /* Reseto o conteúdo de banco de dados do ficheiro que está sendo lido */

        if (ficheiro.getName().substring(0, Math.min(ficheiro.getName().length(), 6)).equals("paises")) {
            dadosPaises___ = new ArrayList<>();
        }

        if (ficheiro.getName().substring(0, Math.min(ficheiro.getName().length(), 9)).equals("populacao")) {
            dadosPopulacao___ = new ArrayList<>();
        }

        if (ficheiro.getName().substring(0, Math.min(ficheiro.getName().length(), 7)).equals("cidades")) {
            dadosCidades___ = new ArrayList<>();
        }

        if (inputInvalido.size() == 3) {
            inputInvalido = new ArrayList<>();
        }
    }


    public static boolean lerDadosFicheiro(File ficheiro) {
        String linhaAtual; /* Armazena o conteúdo da linha atual sendo lida do ficheiro */
        String[] dadosFicheiro; /* Guarda os dados separados por vírgulas. Guardas as colunas */
        int numeroDadosFicheiro; /* Quantidade de colunas de um ficheiro */
        InputInvalido inputInvalidoAtual = new InputInvalido(ficheiro.getName());
        boolean paisRepetido;
        boolean naoTemPaisFicheiroCidade;

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
            resetarBancoDados(ficheiro);
            numeroDadosFicheiro = br.readLine().split(",").length;

            while ((linhaAtual = br.readLine()) != null) {
                paisRepetido = false;
                naoTemPaisFicheiroCidade = true;
                dadosFicheiro = linhaAtual.split(",");
                inputInvalidoAtual.addNumLinhaLidaFicheiro(); /* Leu uma linha */

                /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
                /* Verifica linhas incorretas */
                if (dadosFicheiro.length != numeroDadosFicheiro) { /* Linhas com dados a mais ou a menos */
                    inputInvalidoAtual.addLinhaNOK();
                    continue;
                }

                /* paises.csv */
                if (numeroDadosFicheiro == 4) {
                    for (Pais dadosPais : dadosPaises___) {
                        if (dadosPais.getId() == Integer.parseInt(dadosFicheiro[0].trim())) { /* Verifica se tem país repetido */
                            paisRepetido = true;
                            break;
                        }
                    }
                    for (Cidade dadosCidade : dadosCidades___) { /* Verifica se existe o país no ficheiro cidades.csv */
                        if (dadosCidade.getAlfa2().equals(dadosFicheiro[1].trim())) {
                            naoTemPaisFicheiroCidade = false;
                            break;
                        }
                    }
                    if (paisRepetido || naoTemPaisFicheiroCidade) {
                        inputInvalidoAtual.addLinhaNOK();
                        continue;
                    }
                    if (Arrays.stream(dadosFicheiro).anyMatch(String::isEmpty)) { /* Verifica se tem todos os dados preenchidos */
                        inputInvalidoAtual.addLinhaNOK();
                        continue;
                    }
                }

                /* populacao.csv */
                if (numeroDadosFicheiro == 5 && (dadosFicheiro[1].equals("Medium") || Arrays.stream(dadosFicheiro).anyMatch(String::isEmpty))) {
                    inputInvalidoAtual.addLinhaNOK();
                    continue;
                }

                /* cidades.csv */
                if (numeroDadosFicheiro == 6 && Arrays.stream(dadosFicheiro).anyMatch(String::isEmpty)) {
                    inputInvalidoAtual.addLinhaNOK();
                    continue;
                }
                /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

                /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
                /* Guardando informações dos ficheiros em variáveis */

                /* paises.csv */
                if (numeroDadosFicheiro == 4) {
                    dadosPaises___.add(new Pais(
                            Integer.parseInt(dadosFicheiro[0].trim()),
                            dadosFicheiro[1],
                            dadosFicheiro[2],
                            dadosFicheiro[3]
                    ));
                }

                /* populacao.csv */
                if (numeroDadosFicheiro == 5) {
                    /* Verifica se tem dados de anos repetidos
                    for (Populacao dadosPop : dadosPopulacao___) {
                        if (dadosPop.getId() == Integer.parseInt(dadosFicheiro[0].trim()) && dadosPop.getAno() == Integer.parseInt(dadosFicheiro[1].trim())) {
                            anoPopulacaoRepetido = true;
                            break;
                        }
                    }
                    if (!anoPopulacaoRepetido) {}

                     */

                    if (Integer.parseInt(dadosFicheiro[0].trim()) > 700) {
                        for (Pais pais : dadosPaises___) {
                            if (Integer.parseInt(dadosFicheiro[0].trim()) == pais.getId()) {
                                pais.addPaisIdMaior700();
                            }
                        }
                    }

                    dadosPopulacao___.add(new Populacao(
                            Integer.parseInt(dadosFicheiro[0].trim()),
                            Integer.parseInt(dadosFicheiro[1].trim()),
                            dadosFicheiro[2],
                            dadosFicheiro[3],
                            dadosFicheiro[4]
                    ));
                }

                /* cidades.csv */
                if (numeroDadosFicheiro == 6) {
                    dadosCidades___.add(new Cidade(
                            dadosFicheiro[0],
                            dadosFicheiro[1],
                            dadosFicheiro[2],
                            dadosFicheiro[3],
                            dadosFicheiro[4],
                            dadosFicheiro[5]
                    ));
                }

                inputInvalidoAtual.addLinhaOK(); /* Linha lida com perfeição!!! */
                /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

        /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
        /* Guardar informações para o Enumerado do tipo "INPUT_INVALIDO" */
        inputInvalido.add(inputInvalidoAtual);
        /*  .get(0) -> cidades.csv ||| .get(1) -> paises.csv ||| .get(2) -> populacao.csv
        /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

        return true;
    }


    public static boolean parseFiles(File folder) {

        return lerDadosFicheiro(new File(folder, "cidades.csv"))
                && lerDadosFicheiro(new File(folder, "paises.csv"))
                && lerDadosFicheiro(new File(folder, "populacao.csv"));
    }


    public static Resultado execute(String comando) {
        Resultado out = new Resultado(comando);

        out.setDadosPaises(dadosPaises___);
        out.setDadosCidades(dadosCidades___);
        out.setDadosPopulacao(dadosPopulacao___);

        out.executaComando(comando);

        return out;
    }


    public static void main(String[] args) {
        Main.lerDadosFicheiro(new File("test-files", "cidades-tudo-correto.csv"));
        Main.lerDadosFicheiro(new File("test-files", "paises-tudo-correto.csv"));
        Main.lerDadosFicheiro(new File("test-files", "populacao-tudo-correto.csv"));

        Scanner in = new Scanner(System.in);
        String line;
        Resultado resultado = execute("HELP");
        System.out.println(resultado.getResult());

        do {
            System.out.print("> ");
            line = in.nextLine();

            if (line != null && !line.equals("QUIT")) {
                resultado = execute(line);

                if (resultado.isSucess()) {
                    System.out.println(resultado.getResult());
                } else {
                    System.out.println("Error: " + resultado.getError());
                }
            }
        } while (line != null && !line.equals("QUIT"));


        /*.
        Main.lerDadosFicheiro(new File("test-files", "cidades-tudo-correto.csv"));
        Main.lerDadosFicheiro(new File("test-files", "paises-tudo-correto.csv"));
        Main.lerDadosFicheiro(new File("test-files", "populacao-tudo-correto.csv"));

        System.out.println(getObjects(TipoEntidade.PAIS));
        System.out.println(getObjects(TipoEntidade.CIDADE));
        System.out.println(getObjects(TipoEntidade.INPUT_INVALIDO));

        long start = System.currentTimeMillis();
        boolean parseOk = parseFiles(new File("test-files"));
        if (!parseOk) {
            System.out.println("Erro na leitura dos ficheiros");
            return;
        }

        long end = System.currentTimeMillis();

        ArrayList<String> aaa = getObjects(TipoEntidade.INPUT_INVALIDO);
        System.out.println(aaa.get(0));
        System.out.println(aaa.get(1));
        System.out.println(aaa.get(2));
        System.out.println();


        ArrayList<String> bbb = getObjects(TipoEntidade.PAIS);
        for (String bb : bbb) {
            System.out.println(bb);
        }
        System.out.println();

        ArrayList<String> ccc = getObjects(TipoEntidade.CIDADE);
        for (String cc : ccc) {
            System.out.println(cc);
        }

        System.out.println("Ficheiros lidos com sucesso em " + (end - start) + " ms.");
         */
    }
}
