package pt.ulusofona.aed;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Pessoa[] pessoas = new Pessoa[2];

        Pessoa p1 = new Pessoa("Eduar", "Kah", 9, "Alasca");
        Pessoa p2 = new Pessoa("Joao", "Ribeiro", 3, "Italia");

        pessoas[0] = p1;
        pessoas[1] = p2;
    }
    static Pessoa[] arrayGrande = GeradorDePessoas.criarArrayPessoas(30000);
    static Pessoa[] arrayPequeno = GeradorDePessoas.criarArrayPessoas(10);



    static int[] f01(Pessoa[] pessoas) {
        int[] resultado = new int[]{0,0};
        boolean ordenado = false;
        int n = pessoas.length;
        int comparacoes = 0;
        int trocas = 0;

        while (!ordenado) {
            ordenado = true;
            for (int i = 0; i < n - 1; i++) {
                comparacoes++;
                if (pessoas[i].nrBI > pessoas[i + 1].nrBI) {
                    Pessoa temp = pessoas[i];
                    pessoas[i] = pessoas[i + 1];
                    pessoas[i + 1] = temp;
                    trocas++;
                    ordenado = false;
                }
            }
            n--;
        }

        return new int[]{trocas, comparacoes};
    }


    public static int[] f02(Pessoa[] pessoas) {
        int n = pessoas.length;
        int comparacoes = 0;
        int trocas = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                comparacoes++;
                if (pessoas[j].nrBI < pessoas[minIndex].nrBI) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Pessoa temp = pessoas[i];
                pessoas[i] = pessoas[minIndex];
                pessoas[minIndex] = temp;
                trocas++;
            }
        }

        return new int[]{trocas, comparacoes};
    }


    static long[] f03() {
        long[] resultado = new long[4];
        long start = System.currentTimeMillis();
        f01(arrayGrande);
        long end = System.currentTimeMillis();
        long duration = end - start;
        resultado[0] = duration;

        start = System.currentTimeMillis();
        f02(arrayGrande);
        end = System.currentTimeMillis();
        duration = end - start;
        resultado[1] = duration;

        start = System.currentTimeMillis();
        f01(arrayPequeno);
        end = System.currentTimeMillis();
        duration = end - start;
        resultado[2] = duration;

        start = System.currentTimeMillis();
        f02(arrayPequeno);
        end = System.currentTimeMillis();
        duration = end - start;
        resultado[3] = duration;

        return resultado;
    }

    public static String analiseDaf03() {
        return Arrays.toString(f03());
    }

    public static int[] estatisticas(Pessoa[] dados) {
        int[] resultado = new int[4];

        int[] bubblesort = f01(dados.clone());
        int[] selectionsort = f02(dados.clone());

        resultado[0] = bubblesort[0];
        resultado[1] = bubblesort[1];
        resultado[2] = selectionsort[0];
        resultado[3] = selectionsort[1];

        return resultado;
    }



    static String[] nomesDasFuncoes() {
        return new String[] {
                "bubblesort",
                "sortselection",
                "medirTempos"
        };
    }
}