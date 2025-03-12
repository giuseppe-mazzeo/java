package pt.ulusofona.aed;

import java.util.Arrays;

public class Main {
    static boolean primeiroIgualAoUltimo(String s) {
        if (s == null || s == "") {
            return false;
        }

        return (s.charAt(0) == s.charAt(s.length() - 1));
    }



    static String fazCoisas(int n) {
        if (n < 1) {
            return "";
        }

        if (n == 1 || n == 2) {
            return n + "";
        }

        return fazCoisas(n/2) + "," + n;
    }



    static boolean sequenciaCrescente(int[] numeros) {
        if (numeros == null || numeros.length == 0) {
            return false;
        }

        if (numeros.length == 1) {
            return true;
        }

        if (numeros[0] >= numeros[1]) {
            return false;
        }

        int[] numerosEsq = Arrays.copyOfRange(numeros, 0, 1);
        int[] numerosDir = Arrays.copyOfRange(numeros, 1, numeros.length);

        return sequenciaCrescente(numerosEsq) && sequenciaCrescente(numerosDir);
    }



    public static void main(String[] args) {
        System.out.println(fazCoisas(6));
    }
}
