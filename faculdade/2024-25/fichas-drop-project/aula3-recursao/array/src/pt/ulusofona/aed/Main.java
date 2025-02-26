package pt.ulusofona.aed;

import java.util.Arrays;

public class Main {
    static int f01(int[] numeros, int inicio) {
        if (numeros == null || inicio >= numeros.length) {
            return 0;
        }

        return f01(numeros, inicio + 1) + numeros[inicio];
    }

    static int f02(int[] numeros, int inicio) {
        if (numeros == null || numeros.length == 0) {
            return 0;
        }

        if (inicio == numeros.length - 1) {
            return numeros[inicio];
        }

        int maior = f02(numeros, inicio + 1);

        if (numeros[inicio] > maior) {
            return numeros[inicio];
        } else {
            return maior;
        }
    }

    static int f03(int[] numeros) {
        if (numeros == null || numeros.length == 0) {
            return 0;
        }

        if (numeros.length == 1) {
            return numeros[0];
        }

        int[] arrayEsq = Arrays.copyOfRange(numeros, 0, numeros.length / 2);
        int[] arrayDir = Arrays.copyOfRange(numeros, numeros.length / 2, numeros.length);

        return f03(arrayEsq) + f03(arrayDir);
    }

    static String[] nomesDasFuncoes() {
        return new String[] {
                "somaArray",
                "encontraMaior",
                "somaArray"
        };
    }

    public static void main(String[] args) {
        System.out.println(f02(new int[]{1,-2,3,0},0));
    }
}
