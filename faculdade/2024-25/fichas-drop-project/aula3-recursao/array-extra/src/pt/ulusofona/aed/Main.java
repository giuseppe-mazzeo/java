package pt.ulusofona.aed;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static int f01(int[] numeros, int inicio) {
        if (numeros == null || inicio >= numeros.length) {
            return 0;
        }

        if (numeros[inicio] > 5) {
            return f01(numeros, inicio + 1) + 1;
        }

        return f01(numeros, inicio + 1);
    }

    static boolean f02(char[] letras) {
        if (letras == null || letras.length == 0) {
            return false;
        }

        if (letras.length == 1) {
            return Character.toLowerCase(letras[0]) == 'a' ||
                    Character.toLowerCase(letras[0]) == 'e' ||
                    Character.toLowerCase(letras[0]) == 'i' ||
                    Character.toLowerCase(letras[0]) == 'o' ||
                    Character.toLowerCase(letras[0]) == 'u';
        }

        char[] letraEsq = Arrays.copyOfRange(letras, 0, 1);
        char[] letraDir = Arrays.copyOfRange(letras, 1, letras.length);

        return f02(letraEsq) || f02(letraDir);
    }

    //Muito difícil essa função... :(
    static int f03(int[] numeros) {
        if (numeros == null || numeros.length == 0) {
            return 0;
        }

        if (numeros.length == 1) {
            return numeros[0];
        }

        //Quando o array tem todos os mesmos elementos
        if (numeros.length == 2 && numeros[0] == numeros[1]) {
            return 1;
        }

        Arrays.sort(numeros);
        int numerosEsq = numeros[0];
        int[] numerosDir = Arrays.copyOfRange(numeros, 1, numeros.length);

        if (numerosEsq != numerosDir[0]) {
            return numerosEsq * f03(numerosDir);
        } else {
            //Verifico se não existe 3 elementos iguais seguidos.
            //Se não existir, transformo o próximo 1o elemento do 'numeroDir' para 1, assim, esse valor não irá interferir no cálculo de multiplicação,
            //Caso existir mais elementos iguais, a função irá continuar chamando ela própria até chegar ao fim ou até encontrar um elemento diferente.
            if (numerosEsq != numerosDir[1]) {
                numerosDir[0] = 1;
            }
            return f03(numerosDir);
        }
    }

    static String[] nomesDasFuncoes() {
        return new String[] {
                "contaMaioresDe5",
                "temVogal",
                "multiplicaNumerosUnicos"
        };
    }




    public static void main(String[] args) {
        System.out.println(f03(new int[]{}));
    }
}
