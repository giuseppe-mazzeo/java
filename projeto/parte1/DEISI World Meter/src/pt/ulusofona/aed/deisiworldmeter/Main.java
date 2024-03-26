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


    public static boolean lerFicheiroPaises(File folder) {
        String[] dados = new String[4];
        int linhaOK = 0;
        int linhaNOK = 0;
        int primeiraLinhaIncorreta = -1;
        String[] separador;

        try (BufferedReader br = new BufferedReader(new FileReader(folder))) {
            br.readLine();
            String linhaAtual;

            while ((linhaAtual = br.readLine()) != null) {
                separador = linhaAtual.split(",");

                if (separador.length != 4) {
                    if (primeiraLinhaIncorreta == -1) {
                        primeiraLinhaIncorreta = linhaOK + 1;
                    }
                    linhaNOK++;
                    continue;
                }

                dados[0] = separador[0];
                dados[1] = separador[1];
                dados[2] = separador[2];
                dados[3] = separador[3];

                linhaOK++;
                baseDado.add(dados);
                dados = new String[4];
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


    public static boolean lerFicheiroCidades(File folder) {
        String[] dados = new String[6];
        int linhaOK = 0;
        int linhaNOK = 0;
        int primeiraLinhaIncorreta = -1;
        String[] separador;

        try (BufferedReader br = new BufferedReader(new FileReader(folder))) {
            br.readLine();
            String linhaAtual;

            while ((linhaAtual = br.readLine()) != null) {
                separador = linhaAtual.split(",");

                if (separador.length != 6) {
                    if (primeiraLinhaIncorreta == -1) {
                        primeiraLinhaIncorreta = linhaOK + 1;
                    }
                    linhaNOK++;
                    continue;
                }

                dados[0] = separador[0];
                dados[1] = separador[1];
                dados[2] = separador[2];
                dados[3] = separador[3];
                dados[4] = separador[4];
                dados[5] = separador[5];

                linhaOK++;
                baseDado.add(dados);
                dados = new String[6];
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


    static public boolean lerFicheiroPopulacao(File folder) {
        String[] dados = new String[5];
        int linhaOK = 0;
        int linhaNOK = 0;
        int primeiraLinhaIncorreta = -1;
        String[] separador;

        try (BufferedReader br = new BufferedReader(new FileReader(folder))) {
            br.readLine();
            String linhaAtual;

            while ((linhaAtual = br.readLine()) != null) {
                separador = linhaAtual.split(",");

                if (separador.length != 5) {
                    if (primeiraLinhaIncorreta == -1) {
                        primeiraLinhaIncorreta = linhaOK + 1;
                    }
                    linhaNOK++;
                    continue;
                }

                dados[0] = separador[0];
                if ((dados[1] = separador[1]).equals("Medium")) dados[1] = "";
                dados[2] = separador[2];
                dados[3] = separador[3];
                dados[4] = separador[4];

                linhaOK++;
                baseDado.add(dados);
                dados = new String[5];
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


    public static boolean parseFiles(File folder) {
        if (folder.getName().equals("paises.csv")) {
            return lerFicheiroPaises(folder);
        } else if (folder.getName().equals("cidades.csv")) {
            return lerFicheiroCidades(folder);
        } else if (folder.getName().equals("populacao.csv")) {
            return lerFicheiroPopulacao(folder);
        } else {
            return false;
        }
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
