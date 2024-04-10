import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Gerador.criarArrayInts(10)));

        int[] nums = new int[]{5, 11, 9, 7};
        int[] nums2 = nums.clone();

        f02(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(f03()));
    }

    static int[] f01(int[] numeros) {
        boolean estaOrdenado = false;
        int ultimoOrdenado = numeros.length;

        while (!estaOrdenado) {
            estaOrdenado = true;

            for (int i = 0; i < ultimoOrdenado - 1; i++) {
                if (numeros[i] > numeros[i + 1]) {
                    estaOrdenado = false;

                    int auxiliar = numeros[i];
                    numeros[i] = numeros[i + 1];
                    numeros[i + 1] = auxiliar;
                }
            }

            ultimoOrdenado--;
        }

        return numeros;
    }

    static int[] f02(int[] numeros) {
        int maiorOrdenadoPos = -1;

        while (maiorOrdenadoPos < numeros.length - 1) {
            int minPos = maiorOrdenadoPos + 1;

            for (int i = minPos + 1; i < numeros.length; i++) {
                if (numeros[i] < numeros[minPos]) {
                    minPos = i;
                }
            }

            maiorOrdenadoPos++;

            if (maiorOrdenadoPos != minPos) {
                int auxiliar = numeros[maiorOrdenadoPos];
                numeros[maiorOrdenadoPos] = numeros[minPos];
                numeros[minPos] = auxiliar;
            }
        }

        return numeros;
    }

    static long[] f03() {
        long[] out = new long[4];
        int[] arrayGrande = Gerador.criarArrayInts(50000);
        int[] arrayGrandeCopia = arrayGrande.clone();
        int[] arrayPequeno = Gerador.criarArrayInts(10);
        int[] arrayPequenoCopia = arrayPequeno.clone();
        long start;
        long end;

        start  = System.currentTimeMillis();
        f01(arrayGrande);
        end = System.currentTimeMillis();
        out[0] = end - start;

        start  = System.currentTimeMillis();
        f01(arrayGrandeCopia);
        end = System.currentTimeMillis();
        out[1] = end - start;

        start  = System.currentTimeMillis();
        f01(arrayPequeno);
        end = System.currentTimeMillis();
        out[2] = end - start;

        start  = System.currentTimeMillis();
        f01(arrayPequenoCopia);
        end = System.currentTimeMillis();
        out[3] = end - start;

        return out;
    }

    static int[] bubbleSort(int[] numeros) {
        int[] out = new int[2];
        int numTroca = 0;
        int numComparacao = 0;
        boolean estaOrdenado = false;
        int ultimoOrdenado = numeros.length;

        while (!estaOrdenado) {
            estaOrdenado = true;

            for (int i = 0; i < ultimoOrdenado - 1; i++) {
                if (numeros[i] > numeros[i + 1]) {
                    numComparacao++;

                    estaOrdenado = false;

                    int auxiliar = numeros[i];
                    numeros[i] = numeros[i + 1];
                    numeros[i + 1] = auxiliar;

                    numTroca++;
                }
            }

            ultimoOrdenado--;
        }

        out[0] = numTroca;
        out[1] = numComparacao;
        return out;
    }

    static int[] selectionSort(int[] numeros) {
        int[] out = new int[2];
        int numTroca = 0;
        int numComparacao = 0;
        int maiorOrdenadoPos = -1;

        while (maiorOrdenadoPos < numeros.length - 1) {
            int minPos = maiorOrdenadoPos + 1;

            for (int i = minPos + 1; i < numeros.length; i++) {
                if (numeros[i] < numeros[minPos]) {
                    minPos = i;

                    numComparacao++;
                }
            }

            maiorOrdenadoPos++;

            if (maiorOrdenadoPos != minPos) {
                int auxiliar = numeros[maiorOrdenadoPos];
                numeros[maiorOrdenadoPos] = numeros[minPos];
                numeros[minPos] = auxiliar;

                numTroca++;
            }
        }

        out[0] = numTroca;
        out[1] = numComparacao;
        return out;
    }

    public static int[] estatisticas(int[] dados) {
        int[] estatistica = new int[4];
        int[] dadosCopia1 = dados.clone();
        int[] dadosCopia2 = dados.clone();

        int[] out = bubbleSort(dadosCopia1);
        estatistica[0] = out[0];
        estatistica[1] = out[1];

        out = bubbleSort(dadosCopia2);
        estatistica[2] = out[2];
        estatistica[3] = out[3];
        
        return estatistica;
    }

    static String[] nomesDasFuncoes() {
        return new String[]{
                "orderar", // nome verdadeiro da f01()
                "ordenar", // nome verdadeiro da f02()
                "testaOrdenacao", // nome verdadeiro da f03()
        };
    }
}
