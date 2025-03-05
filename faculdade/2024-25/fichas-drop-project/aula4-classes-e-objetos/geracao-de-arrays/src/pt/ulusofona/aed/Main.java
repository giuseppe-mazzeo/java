package pt.ulusofona.aed;

import java.util.Arrays;

public class Main {
    static int[] array10Elem() {
        return Gerador.gerarNumeros(10);
    }



    static int[] array15Elem() {
        return Gerador.gerarNumeros(15);
    }



    static int[] array100Elem() {
        return Gerador.gerarNumeros(100);
    }



    public static void main(String[] args) {
        System.out.println(Arrays.toString(array10Elem()));
    }
}
