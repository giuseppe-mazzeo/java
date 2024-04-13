package pt.ulusofona.aed.deisiworldmeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static ArrayList<String[]> dadosPaises = new ArrayList<>();
    static ArrayList<String[]> dadosCidades = new ArrayList<>();
    static ArrayList<String[]> dadosPopulacao = new ArrayList<>();
    static int[] leituraLinhas = new int[9]; /* [0-2] -> paises.csv || [3-5] -> cidades.csv || [6-8] -> populacao.csv */


    public static ArrayList<String> getObjects(TipoEntidade tipo) {
        ArrayList<String> output = new ArrayList<>();

        if (tipo == TipoEntidade.INPUT_INVALIDO) {
            output.add("paises.csv | " + leituraLinhas[0] + " | " + leituraLinhas[1] + " | " + leituraLinhas[2]);
            output.add("cidades.csv | " + leituraLinhas[3] + " | " + leituraLinhas[4] + " | " + leituraLinhas[5]);
            output.add("populacao.csv | " + leituraLinhas[6] + " | " + leituraLinhas[7] + " | " + leituraLinhas[8]);
        } else if (tipo == TipoEntidade.PAIS) {
            int quantidadeDadoPais = 0;

            for (String[] dadosPais : dadosPaises) {
                if (Integer.parseInt(dadosPais[0].trim()) > 700) {
                    for (String[] dadosPop : dadosPopulacao) {
                        if (dadosPais[0].equals(dadosPop[0])) {
                            quantidadeDadoPais++;
                        }
                    }
                    output.add(dadosPais[3] + " | " + dadosPais[0] + " | " + dadosPais[1].toUpperCase() + " | " + dadosPais[2].toUpperCase() + " | " + quantidadeDadoPais);
                } else {
                    output.add(dadosPais[3] + " | " + dadosPais[0] + " | " + dadosPais[1].toUpperCase() + " | " + dadosPais[2].toUpperCase());
                }
            }
        } else if (tipo == TipoEntidade.CIDADE) {
            for (String[] dadosCidade : dadosCidades) {
                for (String[] dadosPais : dadosPaises) {
                    if (dadosCidade[0].equals(dadosPais[1])) {
                        output.add(dadosCidade[1] + " | " + dadosCidade[0].toUpperCase() + " | " + dadosCidade[2] + " | " + dadosCidade[3] + " | (" + dadosCidade[4] + "," + dadosCidade[5] + ")");
                    }
                }
            }
        }

        return output;
    }


    private static void lerDadosFicheiro(File ficheiro) {
        String linhaAtual; /* Armazena o conteúdo da linha atual sendo lida do ficheiro */
        String[] dadosFicheiro; /* Guarda os dados separados das vírgulas. Guardas as colunas */
        int linhaOK = 0;
        int linhaNOK = 0;
        int primeiraLinhaIncorreta = -1;
        int numeroDadosFicheiro = 0; /* Quantidade de colunas de um ficheiro */
        int tamanhoLeituraLinhas = -1; /* Esta variável está relacionada com a variável global "leituraLinhas". Olhar comentário da linha 13 */
        boolean paisRepetido; /* Verificar se tem países duplicados no ficheiro paises.csv */

        /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
        /* Dependendo do ficheiro, tenho que ter dados diferentes */
        if (ficheiro.getName().equals("paises.csv")) {
            numeroDadosFicheiro = 4;
            tamanhoLeituraLinhas = 0;
        } else if (ficheiro.getName().equals("cidades.csv")) {
            numeroDadosFicheiro = 6;
            tamanhoLeituraLinhas = 3;
        } else if (ficheiro.getName().equals("populacao.csv")) {
            numeroDadosFicheiro = 5;
            tamanhoLeituraLinhas = 6;
        }
        /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
            br.readLine();

            while ((linhaAtual = br.readLine()) != null) {
                paisRepetido = false;
                dadosFicheiro = linhaAtual.split(",");

                /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
                /* Verifica linhas incorretas */
                if (dadosFicheiro.length != numeroDadosFicheiro) { /* Linhas com dados a mais e a menos */
                    if (primeiraLinhaIncorreta == -1) {
                        primeiraLinhaIncorreta = linhaOK + 1;
                    }
                    linhaNOK++;
                    continue;
                }
                if (dadosFicheiro[1].equals("Medium")) { //TODO acrescentar linha incorreta
                    continue;
                }

                if (dadosFicheiro[3].equals("")) { /* Linhas com dados vazio */ //TODO acrescentar linha incorreta
                    continue;
                }
                /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

                /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
                /* Guardando informações dos ficheiros em variáveis */
                if (numeroDadosFicheiro == 4) { /* pasta paises */
                    for (String[] dadosPais : dadosPaises) {
                        if (dadosPais[0].equals(dadosFicheiro[0])) {
                            paisRepetido = true;
                            break;
                        }
                    }
                    if (!paisRepetido) {
                        dadosPaises.add(dadosFicheiro);
                    }
                }
                if (numeroDadosFicheiro == 5) { /* pasta populacao */
                    dadosPopulacao.add(dadosFicheiro);
                }
                if (numeroDadosFicheiro == 6) { /* pasta cidades */
                    dadosCidades.add(dadosFicheiro);
                }
                /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

                linhaOK++; /* Linha lida com perfeição!!! */
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
        /* Guardar informações para o Enumerado do tipo "INPUT_INVALIDO" */
        leituraLinhas[tamanhoLeituraLinhas] = linhaOK;
        leituraLinhas[tamanhoLeituraLinhas + 1] = linhaNOK;
        leituraLinhas[tamanhoLeituraLinhas + 2] = primeiraLinhaIncorreta;
        /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    }


    public static boolean parseFiles(File folder) {
        try (BufferedReader reader = new BufferedReader(new FileReader(folder + "\\paises.csv"))) {
            lerDadosFicheiro(new File(folder + "\\paises.csv"));
        } catch (IOException e) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(folder + "\\cidades.csv"))) {
            lerDadosFicheiro(new File(folder + "\\cidades.csv"));
        } catch (IOException e) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(folder + "\\populacao.csv"))) {
            lerDadosFicheiro(new File(folder + "\\populacao.csv"));
        } catch (IOException e) {
            return false;
        }

        return true;
    }


    public static void main(String[] args) {

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
        for (String bb : bbb) System.out.println(bb);
        System.out.println();

        ArrayList<String> ccc = getObjects(TipoEntidade.CIDADE);
        for (String cc : ccc) System.out.println(cc);

        System.out.println("Ficheiros lidos com sucesso em " + (end - start) + " ms.");
    }
}
