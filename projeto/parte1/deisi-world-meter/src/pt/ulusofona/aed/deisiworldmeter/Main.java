package pt.ulusofona.aed.deisiworldmeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static ArrayList<Pais> dadosPaises___;
    private static ArrayList<Cidade> dadosCidades___;
    private static ArrayList<Populacao> dadosPopulacao___;
    private static ArrayList<InputInvalido> inputInvalido = new ArrayList<>();

    public static ArrayList<String> getObjects(TipoEntidade tipo) {
        ArrayList<String> output = new ArrayList<>();

        if (tipo == TipoEntidade.INPUT_INVALIDO) {
            output.add(inputInvalido.get(0).toString());
            output.add(inputInvalido.get(1).toString());
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
        HashMap<Integer, Integer> novoMap;
        String linhaAtual; /* Armazena o conteúdo da linha atual sendo lida do ficheiro */
        String[] dadosFicheiro; /* Guarda os dados separados por vírgulas. Guardas as colunas */int numeroDadosFicheiro; /* Quantidade de colunas de um ficheiro */
        boolean paisRepetido; /* Verificar se tem países duplicados no ficheiro paises.csv */
        boolean anoPopulacaoRepetido;
        InputInvalido inputInvalidoAtual = new InputInvalido(ficheiro.getName());

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
            resetarBancoDados(ficheiro);
            numeroDadosFicheiro = br.readLine().split(",").length;


            while ((linhaAtual = br.readLine()) != null) {
                paisRepetido = false;
                anoPopulacaoRepetido = false;
                dadosFicheiro = linhaAtual.split(",");
                inputInvalidoAtual.addNumLinhaLidaFicheiro(); /* Leu uma linha */

                /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
                /* Verifica linhas incorretas */
                if (dadosFicheiro.length != numeroDadosFicheiro) { /* Linhas com dados a mais e a menos */
                    inputInvalidoAtual.addLinhaNOK();
                    continue;
                }
                if (numeroDadosFicheiro == 5 && (dadosFicheiro[1].equals("Medium") || dadosFicheiro[1].equals("") || dadosFicheiro[2].equals("") || dadosFicheiro[3].equals("") || dadosFicheiro[4].equals(""))) { /* Ficheiro populacao */
                    inputInvalidoAtual.addLinhaNOK();
                    continue;
                }
                if (numeroDadosFicheiro == 6 && (dadosFicheiro[0].equals("") || dadosFicheiro[3].equals(""))) { /* Ficheiro cidades */
                    inputInvalidoAtual.addLinhaNOK();
                    continue;
                }
                /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

                /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
                /* Guardando informações dos ficheiros em variáveis */
                if (numeroDadosFicheiro == 4) { /* pasta paises */
                    for (Pais dadosPais : dadosPaises___) {
                        if (dadosPais.getId() == Integer.parseInt(dadosFicheiro[0].trim())) { /* Verificar se tem país repetido */
                            paisRepetido = true;
                            inputInvalidoAtual.addLinhaNOK();
                            break;
                        }
                    }
                    if (!paisRepetido) {
                        dadosPaises___.add(new Pais(
                                Integer.parseInt(dadosFicheiro[0].trim()),
                                dadosFicheiro[1],
                                dadosFicheiro[2],
                                dadosFicheiro[3]
                        ));
                    }
                }

                if (numeroDadosFicheiro == 5) { /* pasta populacao */
                    for (Populacao dadosPop : dadosPopulacao___) {
                        if (dadosPop.getId() == Integer.parseInt(dadosFicheiro[0].trim()) && dadosPop.getAno() == Integer.parseInt(dadosFicheiro[1].trim())) {
                            anoPopulacaoRepetido = true;
                            break;
                        }
                    }
                    if (!anoPopulacaoRepetido) {
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
                }

                if (numeroDadosFicheiro == 6) { /* pasta cidades */
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
        /* .get(0) -> paises.csv ||| .get(1) -> populacao.csv ||| .get(2) -> cidades.csv */
        /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

        return true;
    }


    public static boolean parseFiles(File folder) {

        return lerDadosFicheiro(new File(folder, "paises.csv"))
                && lerDadosFicheiro(new File(folder, "populacao.csv"))
                && lerDadosFicheiro(new File(folder, "cidades.csv"));
    }


    public static void main(String[] args) {
        Main.lerDadosFicheiro(new File("test-files", "paises-tudo-correto.csv"));
        Main.lerDadosFicheiro(new File("test-files", "cidades-tudo-correto.csv"));
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
    }
}
