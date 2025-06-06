package pt.ulusofona.aed;

import java.util.Arrays;

public class Main {

    static int[] arrayGrande = Gerador.criarArrayInts(50000);
    static int[] arrayPequeno = Gerador.criarArrayInts(10);
    static int[] array = Gerador.criarArrayInts(10);

    public static void main(String[] args) {
    }

    static int[] f01(int[] array) {
        boolean tudoOrdenado = false;
        int ultimoOrdenado = array.length-1;
        int[] resultado = new int[]{0,0};
        int comparacoes = 0;
        int trocas = 0;

        while (!tudoOrdenado) {
            tudoOrdenado = true;

            for (int i = 0; i < ultimoOrdenado; i++) {
                comparacoes++;
                if (array[i] > array[i+1]) {
                    tudoOrdenado = false;
                    trocas++;


                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                }
            }
            ultimoOrdenado--;
        }
        resultado[0] = trocas;
        resultado[1] = comparacoes;
        return resultado;
    }

    static int[] f02(int[] array) {
        int[] resultado = new int[]{0,0};
        int maiorOrdenadoPos = -1;
        int comparacoes = 0;
        int trocas = 0;

        while (maiorOrdenadoPos < array.length - 1) {

            int minPos = maiorOrdenadoPos + 1;

            for (int i = minPos+1; i < array.length; i++) {
                comparacoes++;
                if (array[i] < array[minPos]) {
                    minPos = i;

                }
            }

            maiorOrdenadoPos++;

            if (maiorOrdenadoPos != minPos) {
                int temp = array[maiorOrdenadoPos];
                array[maiorOrdenadoPos] = array[minPos];
                array[minPos] = temp;
                trocas++;
            }
        }
        resultado[0] = trocas;
        resultado[1] = comparacoes;

        return resultado;
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

    public static int[] estatisticas(int[] dados) {
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
                "sortSelection",
                "medirtempos"
        };
    }
}