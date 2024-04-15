package pt.ulusofona.aed.deisiworldmeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<String[]> dadosPaises;
    static ArrayList<String[]> dadosCidades;
    static ArrayList<String[]> dadosPopulacao;
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
                        if (dadosPais[0].equals(dadosPop[0]) && (Integer.parseInt(dadosPop[1].trim()) >= 1950 && Integer.parseInt(dadosPop[1].trim()) <= 2100)) {
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
                        dadosCidade[3] = dadosCidade[3].substring(0, dadosCidade[3].length() - 2);
                        output.add(dadosCidade[1] + " | " + dadosCidade[0].toUpperCase() + " | " + dadosCidade[2] + " | " + dadosCidade[3] + " | (" + dadosCidade[4] + "," + dadosCidade[5] + ")");
                    }
                }
            }
        }

        return output;
    }


    public static boolean lerDadosFicheiro(File ficheiro) {
        String linhaAtual; /* Armazena o conteúdo da linha atual sendo lida do ficheiro */
        String[] dadosFicheiro; /* Guarda os dados separados das vírgulas. Guardas as colunas */
        int linhaOK = 0;
        int linhaNOK = 0;
        int primeiraLinhaIncorreta = -1;
        int numLinhasFicheiro; /* Acrescenta um valor à linha lida, estando certa ou errada */
        int numeroDadosFicheiro = 0; /* Quantidade de colunas de um ficheiro */
        int tamanhoLeituraLinhas = -1; /* Esta variável está relacionada com a variável global "leituraLinhas". Olhar comentário da linha 13 */
        boolean paisRepetido; /* Verificar se tem países duplicados no ficheiro paises.csv */
        boolean anoPopulacaoRepetido;

        /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
        /* Dependendo do ficheiro, tenho que ter dados diferentes */
        if (ficheiro.getName().equals("paises.csv")) {
            dadosPaises = new ArrayList<>();
            numeroDadosFicheiro = 4;
            tamanhoLeituraLinhas = 0;
        } else if (ficheiro.getName().equals("cidades.csv")) {
            dadosCidades = new ArrayList<>();
            numeroDadosFicheiro = 6;
            tamanhoLeituraLinhas = 3;
        } else if (ficheiro.getName().equals("populacao.csv")) {
            dadosPopulacao = new ArrayList<>();
            numeroDadosFicheiro = 5;
            tamanhoLeituraLinhas = 6;
        }
        /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
            br.readLine();
            numLinhasFicheiro = 1; /* Primeira linha de cada ficheiro é ignorada */

            while ((linhaAtual = br.readLine()) != null) {
                paisRepetido = false;
                anoPopulacaoRepetido = false;
                dadosFicheiro = linhaAtual.split(",");
                numLinhasFicheiro++; /* Leu uma linha */

                /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
                /* Verifica linhas incorretas */
                if (dadosFicheiro.length != numeroDadosFicheiro) { /* Linhas com dados a mais e a menos */
                    if (primeiraLinhaIncorreta == -1) {
                        primeiraLinhaIncorreta = numLinhasFicheiro;
                    }
                    linhaNOK++;
                    continue;
                }
                if (ficheiro.getName().equals("populacao.csv") && (dadosFicheiro[1].equals("Medium") || dadosFicheiro[1].equals("") || dadosFicheiro[2].equals("") || dadosFicheiro[3].equals("") || dadosFicheiro[4].equals(""))) { /* Ficheiro populacao */
                    if (primeiraLinhaIncorreta == -1) {
                        primeiraLinhaIncorreta = numLinhasFicheiro;
                    }
                    linhaNOK++;
                    continue;
                }
                if (ficheiro.getName().equals("cidades.csv") && (dadosFicheiro[0].equals("") || dadosFicheiro[3].equals(""))) { /* Ficheiro cidades */
                    if (primeiraLinhaIncorreta == -1) {
                        primeiraLinhaIncorreta = numLinhasFicheiro;
                    }
                    linhaNOK++;
                    continue;
                }
                /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

                /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
                /* Guardando informações dos ficheiros em variáveis */
                if (ficheiro.getName().equals("paises.csv")) { /* pasta paises */
                    for (String[] dadosPais : dadosPaises) {
                        if (dadosPais[0].trim().equals(dadosFicheiro[0].trim())) { /* Verificar se tem país repetido */
                            if (primeiraLinhaIncorreta == -1) {
                                primeiraLinhaIncorreta = numLinhasFicheiro;
                            }
                            paisRepetido = true;
                            linhaNOK++;
                            break;
                        }
                    }
                    if (!paisRepetido) {
                        dadosPaises.add(dadosFicheiro);
                        linhaOK++; /* Linha lida com perfeição!!! */
                    }
                }
                if (ficheiro.getName().equals("populacao.csv")) { /* pasta populacao */
                    for (String[] dadosPop : dadosPopulacao) {
                        if (dadosPop[0].trim().equals(dadosFicheiro[0].trim()) && dadosPop[1].trim().equals(dadosFicheiro[1].trim())) {
                            anoPopulacaoRepetido = true;
                            break;
                        }
                    }
                    if (!anoPopulacaoRepetido) {
                        dadosPopulacao.add(dadosFicheiro);
                        linhaOK++; /* Linha lida com perfeição!!! */
                    }
                }
                if (ficheiro.getName().equals("cidades.csv")) { /* pasta cidades */
                    dadosCidades.add(dadosFicheiro);
                    linhaOK++; /* Linha lida com perfeição!!! */
                }
                /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

        /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
        /* Guardar informações para o Enumerado do tipo "INPUT_INVALIDO" */
        leituraLinhas[tamanhoLeituraLinhas] = linhaOK;
        leituraLinhas[tamanhoLeituraLinhas + 1] = linhaNOK;
        leituraLinhas[tamanhoLeituraLinhas + 2] = primeiraLinhaIncorreta;
        /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

        return true;
    }


    public static boolean parseFiles(File folder) {

        return lerDadosFicheiro(new File(folder, "paises.csv"))
                && lerDadosFicheiro(new File(folder, "cidades.csv"))
                && lerDadosFicheiro(new File(folder, "populacao.csv"));
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
        /* for (String bb : bbb){
         System.out.println(bb);
         }


        System.out.println(bbb.get(0));
        System.out.println(bbb.get(1));
        System.out.println(bbb.get(2));
        System.out.println();


        ArrayList<String> ccc = getObjects(TipoEntidade.CIDADE);

        for (String cc : ccc) {
         System.out.println(cc);

        System.out.println(ccc.get(0));
        System.out.println(ccc.get(1));
        System.out.println(ccc.get(2));
        System.out.println();





        for (String[] cc : dadosPopulacao) {
            System.out.println(Arrays.toString(cc));
        }

         */



        System.out.println("Ficheiros lidos com sucesso em " + (end - start) + " ms.");
    }
}
