package pt.ulusofona.aed;

import java.util.Arrays;
import java.util.Random;

public class Main {

    static boolean f01(int[] numeros, int numeroProcurado) {
        if (numeros == null || numeros.length == 0) {
            return false;
        }

        for (int pos = 0; pos < numeros.length; pos++) {
            if (numeros[pos] == numeroProcurado) {
                return true;
            }
        }

        return false;
    }



    static boolean f02(int[] numeros,  int numeroProcurado, int posInicio, int posFim) {
        if (numeros == null || numeros.length == 0) {
            return false;
        }

        if (posInicio > posFim) {
            return false;
        }

        int posMeio = posInicio + (posFim - posInicio) / 2;

        if (numeros[posMeio] == numeroProcurado) {
            return true;
        } else if (numeroProcurado < numeros[posMeio]) {
            return f02(numeros, numeroProcurado, posInicio, posMeio - 1);
        } else {
            return f02(numeros, numeroProcurado, posMeio + 1, posFim);
        }
    }



    static long[] f03() {
        long[] resultadosTempo = new long[4];

        long comeco;
        long fim;

        int[] arrayPequeno = new int[] {1,2,3,4,5,6,7,8,9,10};
        int[] arrayGrande = new int[1_000_000];
        for (int i = 0; i < arrayGrande.length; i++) {
            arrayGrande[i] = i + 1;
        }

        comeco = System.currentTimeMillis();
        f01(arrayGrande, 80000);
        fim = System.currentTimeMillis();
        resultadosTempo[0] = fim - comeco;

        comeco = System.currentTimeMillis();
        f02(arrayGrande, 80000, 0, 999999);
        fim = System.currentTimeMillis();
        resultadosTempo[1] = fim - comeco;

        comeco = System.currentTimeMillis();
        f01(arrayPequeno, 8);
        fim = System.currentTimeMillis();
        resultadosTempo[2] = fim - comeco;

        comeco = System.currentTimeMillis();;
        f02(arrayPequeno, 8, 0, 7);
        fim = System.currentTimeMillis();;
        resultadosTempo[3] = fim - comeco;

        return resultadosTempo;
    }



    public static void main(String[] args) {
        System.out.println(Arrays.toString(f03()));
    }
}
