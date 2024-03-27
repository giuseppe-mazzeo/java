package pt.ulusofona.aed.deisiworldmeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<String[]> baseDado = new ArrayList<>();
    static int[] leituraLinhas = new int[3];


    /*
    public static ArrayList getObjects(TipoEntidade tipo) {
    }

     */
    
    public static boolean parseFiles(File folder) {
        String[] dados;
        int linhaOK = 0;
        int linhaNOK = 0;
        int primeiraLinhaIncorreta = -1;
        int tamanhoArray;
        String[] separador;

        if (folder.getName().equals("paises.csv")) {
            tamanhoArray = 4;
        } else if (folder.getName().equals("populacao.csv")) {
            tamanhoArray = 5;
        } else if (folder.getName().equals("cidades.csv")) {
            tamanhoArray = 6;
        } else {
            return false;
        }

        dados = new String[tamanhoArray];

        try (BufferedReader br = new BufferedReader(new FileReader(folder))) {
            br.readLine();
            String linhaAtual;

            while ((linhaAtual = br.readLine()) != null) {
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
                }

                linhaOK++;
                baseDado.add(dados);
                dados = new String[tamanhoArray];
            }

        } catch (IOException e) {
            return false;
        }

        leituraLinhas[0] = linhaOK;
        leituraLinhas[1] = linhaNOK;
        leituraLinhas[2] = primeiraLinhaIncorreta;
        System.out.println(Arrays.toString(leituraLinhas));

        return true;
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        boolean parseOk = parseFiles(new File("populacao.csv"));
        if (!parseOk) {
            System.out.println("Erro na leitura dos ficheiros");
            return;
        }
        long end = System.currentTimeMillis();

        for (int i = 0; i < baseDado.size(); i++) System.out.print(Arrays.toString(baseDado.get(i)) + " ");
        System.out.println();

        System.out.println("Ficheiros lidos com sucesso em " + (end - start) + " ms.");
    }
}
