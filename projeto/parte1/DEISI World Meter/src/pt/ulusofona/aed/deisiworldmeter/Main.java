package pt.ulusofona.aed.deisiworldmeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<String[]> dadosPaises = new ArrayList<>();
    static ArrayList<String[]> dadosCidades = new ArrayList<>();
    static ArrayList<String[]> dadosPopulacao = new ArrayList<>();
    static int[] leituraLinhas = new int[9];


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


    public static boolean parseFiles(File folder) {
        System.out.println(folder.getName());
        String[] dados;
        String[] separador;
        int linhaOK = 0;
        int linhaNOK = 0;
        int primeiraLinhaIncorreta = -1;
        int tamanhoArray;
        int tamanhoLeituraLinhas;
        boolean encontrado;

        if (folder.getName().equals("paises.csv")) {
            tamanhoArray = 4;
            tamanhoLeituraLinhas = 0;
        } else if (folder.getName().equals("populacao.csv")) {
            tamanhoArray = 5;
            tamanhoLeituraLinhas = 6;
        } else if (folder.getName().equals("cidades.csv")) {
            tamanhoArray = 6;
            tamanhoLeituraLinhas = 3;
        } else {
            return false;
        }

        dados = new String[tamanhoArray];

        try (BufferedReader br = new BufferedReader(new FileReader(folder))) {
            br.readLine();
            String linhaAtual;

            while ((linhaAtual = br.readLine()) != null) {
                encontrado = false;
                separador = linhaAtual.split(",");

                if (separador.length != tamanhoArray) {
                    if (primeiraLinhaIncorreta == -1) {
                        primeiraLinhaIncorreta = linhaOK + 1;
                    }
                    linhaNOK++;
                    continue;
                }

                for (int i = 0; i < tamanhoArray; i++) {
                    dados[i] = separador[i];
                    if ((dados[1] = separador[1]).equals("Medium")) dados[1] = "";
                    if (separador[3].equals("")) dados[3] = "0";
                }

                linhaOK++;
                if (folder.getName().equals("paises.csv")) {
                    for (String[] dadosPais : dadosPaises) {
                        if (dadosPais[0].equals(dados[0])) {
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        dadosPaises.add(dados);
                    }
                }

                if (folder.getName().equals("cidades.csv")) dadosCidades.add(dados);

                if (folder.getName().equals("populacao.csv")) dadosPopulacao.add(dados);

                dados = new String[tamanhoArray];
            }
        } catch (IOException e) {
            return false;
        }

        leituraLinhas[tamanhoLeituraLinhas] = linhaOK;
        leituraLinhas[tamanhoLeituraLinhas + 1] = linhaNOK;
        leituraLinhas[tamanhoLeituraLinhas + 2] = primeiraLinhaIncorreta;

        if (leituraLinhas[0] == 0 && leituraLinhas[2] == 0) {
            parseFiles(new File("paises.csv"));
        } else if (leituraLinhas[3] == 0 && leituraLinhas[5] == 0) {
            parseFiles(new File("cidades.csv"));
        } else if (leituraLinhas[6] == 0 && leituraLinhas[8] == 0) {
            parseFiles(new File("populacao.csv"));
        }

        return true;
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        boolean parseOk = parseFiles(new File("cidades.csv"));
        if (!parseOk) {
            System.out.println("Erro na leitura dos ficheiros");
            return;
        }
        long end = System.currentTimeMillis();

        for (int i = 0; i < dadosCidades.size(); i++) System.out.print(Arrays.toString(dadosCidades.get(i)) + " ");
        System.out.println();

        ArrayList aaa = getObjects(TipoEntidade.INPUT_INVALIDO);
        System.out.println(aaa.get(0));
        System.out.println(aaa.get(1));
        System.out.println(aaa.get(2));
        System.out.println();

        ArrayList bbb = getObjects(TipoEntidade.PAIS);
        System.out.println(bbb.get(0));
        System.out.println(bbb.get(1));
        System.out.println(bbb.get(2));
        System.out.println(bbb.get(5));
        System.out.println(bbb.get(6));
        System.out.println();

        ArrayList<String> ccc = getObjects(TipoEntidade.CIDADE);
        for (String cc : ccc) System.out.println(cc);

        System.out.println("Ficheiros lidos com sucesso em " + (end - start) + " ms.");
    }
}
