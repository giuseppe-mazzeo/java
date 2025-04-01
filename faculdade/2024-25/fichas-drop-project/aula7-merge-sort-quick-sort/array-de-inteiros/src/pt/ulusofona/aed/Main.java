package pt.ulusofona.aed;

import java.util.Arrays;

public class Main {
    static int[] ordenarNumerosMergeSort(int[] numeros) {
        if (numeros == null) {
            return null;
        }

        if (numeros.length <= 1) {
            return numeros;
        }

        int[] numerosLeft = Arrays.copyOfRange(numeros, 0, numeros.length / 2);
        int[] numerosRight = Arrays.copyOfRange(numeros, numeros.length / 2, numeros.length);

        numerosLeft = ordenarNumerosMergeSort(numerosLeft);
        numerosRight = ordenarNumerosMergeSort(numerosRight);

        return merge(numerosLeft, numerosRight);
    }

    static int[] merge(int[] arrayA, int[] arrayB) {
        int[] result = new int[arrayA.length + arrayB.length];

        // System.out.println(Arrays.toString(result));
        // System.out.println(result.length);

        int posicaoA = 0;
        int posicaoB = 0;
        int posicaoResult = 0;

        while(posicaoA < arrayA.length && posicaoB < arrayB.length) {
            if (arrayA[posicaoA] < arrayB[posicaoB]) {
                result[posicaoResult] = arrayA[posicaoA];
                posicaoA++;
            } else {
                result[posicaoResult] = arrayB[posicaoB];
                posicaoB++;
            }
            posicaoResult++;
        }

        while (posicaoA < arrayA.length) {
            result[posicaoResult] = arrayA[posicaoA];
            posicaoA++;
            posicaoResult++;
        }

        while (posicaoB < arrayB.length) {
            result[posicaoResult] = arrayB[posicaoB];
            posicaoB++;
            posicaoResult++;
        }

        return result;
    }



    static int[] ordenarNumerosQuickSort(int[] numeros) {
        return ordenar(numeros, 0, numeros.length);
    }

    static int[] ordenar(int[] numeros, int left, int right) {
        if (left < right) {
            int posicaoPivot = partition(numeros, left, right - 1);

            numeros = ordenar(numeros, left, posicaoPivot);
            numeros = ordenar(numeros, posicaoPivot + 1, right);
        }

        return numeros;
    }

    static int partition(int[] numeros, int left, int right) {
        int pivot = numeros[right];

        int leftIdx = left;
        int rightIdx = right - 1;

        while (leftIdx <= rightIdx) {
            if (numeros[leftIdx] > pivot && numeros[rightIdx] < pivot) {
                int temp = numeros[leftIdx];
                numeros[leftIdx] = numeros[rightIdx];
                numeros[rightIdx] = temp;
                leftIdx++;
                rightIdx--;
            } else {

                if (numeros[leftIdx] <= pivot) {
                    leftIdx++;
                }

                if (numeros[rightIdx] >= pivot) {
                    rightIdx--;
                }
            }
        }

        numeros[right] = numeros[leftIdx];
        numeros[leftIdx] = pivot;

        return leftIdx;
    }


    // Com base na análise do resultado, observamos que o QuickSort é mais rápido do que o MergeSort
    public static long[] medirTempos() {
        long[] result = new long[4];
        long start;
        long end;
        int[] arrayP = Gerador.criarArrayInts(10);
        int[] arrayG = Gerador.criarArrayInts(50_000);

        start = System.currentTimeMillis();
        ordenarNumerosMergeSort(arrayG);
        end = System.currentTimeMillis();
        result[0] = (end - start);

        start = System.currentTimeMillis();
        ordenarNumerosQuickSort(arrayG);
        end = System.currentTimeMillis();
        result[1] = (end - start);

        start = System.currentTimeMillis();
        ordenarNumerosMergeSort(arrayP);
        end = System.currentTimeMillis();
        result[2] = (end - start);

        start = System.currentTimeMillis();
        ordenarNumerosQuickSort(arrayP);
        end = System.currentTimeMillis();
        result[3] = (end - start);

        return result;
    }



    // Código feito pelo ChatGPT
    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return;
        }
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }
    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }



    public static void main(String[] args) {
        int[] numeros = new int[]{6, 1, 99, 2, 100, 5, 3,9,7,67,0};
        int[] numerosGrande = Gerador.criarArrayInts(1_000_000);

        System.out.println(Arrays.toString(numeros));

        int[] numeros2 = ordenarNumerosMergeSort(numeros);
        System.out.println(Arrays.toString(numeros2));

        int[] numeros3 = ordenarNumerosQuickSort(numeros);
        System.out.println(Arrays.toString(numeros3));

        /*
        System.out.println(Arrays.toString(medirTempos()));

        long start = System.currentTimeMillis();
        mergeSort(numerosGrande);
        long end = System.currentTimeMillis();
        System.out.println("Tempo para o MergeSort do ChatGPT: " + (end - start) + "s");

        start = System.currentTimeMillis();
        ordenarNumerosMergeSort(numerosGrande);
        end = System.currentTimeMillis();
        System.out.println("Tempo para o MergeSort feito em aula: " + (end - start) + "s");
        System.out.println("Conclusão que obtive: não confiar tanto assim no ChatGPT :)");
        */
    }
}
