package pt.ulusofona.aed;

import java.util.Arrays;

public class Main {
    static Pessoa[] ordenarPessoasPorBIMergeSort(Pessoa[] pessoas) {
        if (pessoas == null) {
            return null;
        }

        if (pessoas.length <= 1) {
            return pessoas;
        }

        Pessoa[] pessoasLeft = Arrays.copyOfRange(pessoas, 0, pessoas.length / 2);
        Pessoa[] pessoasRight = Arrays.copyOfRange(pessoas, pessoas.length / 2, pessoas.length);

        pessoasLeft = ordenarPessoasPorBIMergeSort(pessoasLeft);
        pessoasRight = ordenarPessoasPorBIMergeSort(pessoasRight);

        return merge(pessoasLeft, pessoasRight);
    }

    static Pessoa[] merge(Pessoa[] arrayA, Pessoa[] arrayB) {
        Pessoa[] result = new Pessoa[arrayA.length + arrayB.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = new Pessoa();
        }

        int posicaoA = 0;
        int posicaoB = 0;
        int posicaoResult = 0;

        while(posicaoA < arrayA.length && posicaoB < arrayB.length) {
            if (arrayA[posicaoA].nrBI < arrayB[posicaoB].nrBI) {
                result[posicaoResult].nome = arrayA[posicaoA].nome;
                result[posicaoResult].apelido = arrayA[posicaoA].apelido;
                result[posicaoResult].paisMorada = arrayA[posicaoA].paisMorada;
                result[posicaoResult].nrBI = arrayA[posicaoA].nrBI;
                posicaoA++;
            } else {
                result[posicaoResult].nome = arrayB[posicaoB].nome;
                result[posicaoResult].apelido = arrayB[posicaoB].apelido;
                result[posicaoResult].paisMorada = arrayB[posicaoB].paisMorada;
                result[posicaoResult].nrBI = arrayB[posicaoB].nrBI;
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



    static Pessoa[] ordenarPessoasPorBIQuickSort(Pessoa[] pessoas) {
        return ordenar(pessoas, 0, pessoas.length);
    }

    static Pessoa[] ordenar(Pessoa[] pessoas, int left, int right) {
        if (left < right) {
            int posicaoPivot = partition(pessoas, left, right - 1);

            pessoas = ordenar(pessoas, left, posicaoPivot);
            pessoas = ordenar(pessoas, posicaoPivot + 1, right);
        }

        return pessoas;
    }

    static int partition(Pessoa[] pessoas, int left, int right) {
        Pessoa pivot = pessoas[right];

        int leftIdx = left;
        int rightIdx = right - 1;

        while (leftIdx <= rightIdx) {
            if (pessoas[leftIdx].nrBI > pivot.nrBI && pessoas[rightIdx].nrBI < pivot.nrBI) {
                Pessoa temp = pessoas[leftIdx];
                pessoas[leftIdx] = pessoas[rightIdx];
                pessoas[rightIdx] = temp;
                leftIdx++;
                rightIdx--;
            } else {

                if (pessoas[leftIdx].nrBI <= pivot.nrBI) {
                    leftIdx++;
                }

                if (pessoas[rightIdx].nrBI >= pivot.nrBI) {
                    rightIdx--;
                }
            }
        }

        pessoas[right] = pessoas[leftIdx];
        pessoas[leftIdx] = pivot;

        return leftIdx;
    }



    // Houve algo parecido na ficha de arrays de inteiros onde observamos que o QuickSort é mais rápido do que o MergeSort
    public static long[] medirTempos() {
        long[] result = new long[4];
        long start;
        long end;
        Pessoa[] arrayP = Gerador.criarArrayPessoas(10);
        Pessoa[] arrayG = Gerador.criarArrayPessoas(30_000);

        start = System.currentTimeMillis();
        ordenarPessoasPorBIMergeSort(arrayG);
        end = System.currentTimeMillis();
        result[0] = (end - start);

        start = System.currentTimeMillis();
        ordenarPessoasPorBIQuickSort(arrayG);
        end = System.currentTimeMillis();
        result[1] = (end - start);

        start = System.currentTimeMillis();
        ordenarPessoasPorBIMergeSort(arrayP);
        end = System.currentTimeMillis();
        result[2] = (end - start);

        start = System.currentTimeMillis();
        ordenarPessoasPorBIQuickSort(arrayP);
        end = System.currentTimeMillis();
        result[3] = (end - start);

        return result;
    }



    public static void mergeSort(Pessoa[] pessoas) {
        if (pessoas.length < 2) {
            return;
        }
        int mid = pessoas.length / 2;
        Pessoa[] left = Arrays.copyOfRange(pessoas, 0, mid);
        Pessoa[] right = Arrays.copyOfRange(pessoas, mid, pessoas.length);

        mergeSort(left);
        mergeSort(right);
        merge(pessoas, left, right);
    }

    private static void merge(Pessoa[] pessoas, Pessoa[] left, Pessoa[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].nrBI <= right[j].nrBI) {
                pessoas[k++] = left[i++];
            } else {
                pessoas[k++] = right[j++];
            }
        }
        while (i < left.length) {
            pessoas[k++] = left[i++];
        }
        while (j < right.length) {
            pessoas[k++] = right[j++];
        }
    }



    public static void main(String[] args) {
        Pessoa[] pessoas = Gerador.criarArrayPessoas(10);
        Pessoa[] pessoasGrande = Gerador.criarArrayPessoas(1_000_000);

        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa);
        }
        System.out.println("= = = = = = = = =");
        /*
        Pessoa[] pessoasMerge = ordenarPessoasPorBIMergeSort(pessoas);
        for (Pessoa pessoa : pessoasMerge) {
            System.out.println(pessoa);
        }
         */
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = ");

        Pessoa[] pessoasQuick = ordenarPessoasPorBIQuickSort(pessoas);
        for (Pessoa pessoa : pessoasQuick) {
            System.out.println(pessoa);
        }

        System.out.println(Arrays.toString(medirTempos()));

        long start = System.currentTimeMillis();
        mergeSort(pessoasGrande);
        long end = System.currentTimeMillis();
        System.out.println("Tempo para o MergeSort do ChatGPT: " + (end - start) + "s");

        start = System.currentTimeMillis();
        ordenarPessoasPorBIMergeSort(pessoasGrande);
        end = System.currentTimeMillis();
        System.out.println("Tempo para o MergeSort feito em aula: " + (end - start) + "s");
        System.out.println("E novamente obtivemos a mesma conclusão: que não se deve confiar tanto assim no ChatGPT :)");
    }
}
