import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
    }

    static String f1(int numero1, int numero2) {
        return numero1 + " " + numero2;
    }

    static int[] f2(int tamanhoArray) {
        if (tamanhoArray < 0) {
            return new int[0];
        }

        return new int[tamanhoArray];
    }

    static int[] f3(int[] numeros) {
        if (numeros == null) {
            return null;
        }

        for (int i = 1; i <= numeros.length; i++) {
            numeros[i] = i;
        }
        return numeros;
    }

    static String f4(int[] numeros) {
        if (numeros == null) {
            return null;
        }
        if (numeros.length == 0) {
            return "";
        }

        String out = "[";
        for (int i = 0; i < numeros.length; i++) {
            out += numeros[i];
            if (i < numeros.length - 1) {
                out += ", ";
            }
        }
        out += "]";

        return out;
    }

    static int f5(int[] numeros) {
        if ((numeros == null) || (numeros.length == 0)) {
            return 0;
        }

        int soma = 0;
        for (int numero : numeros) {
            soma += numero;
        }

        return soma;
    }

    static int f6(int[] numeros) {
        if ((numeros == null) || (numeros.length == 0)) {
            return 0;
        }

        int maior = numeros[0];
        for (int numero : numeros) {
            if (maior < numero) {
                maior = numero;
            }
        }

        return maior;
    }

    static int[] f7(int[] arrayNumeros1, int[] arrayNumeros2) {
        if ((arrayNumeros1 == null) || (arrayNumeros1.length == 0)) {
            return null;
        }
        if ((arrayNumeros2 == null) || (arrayNumeros2.length == 0)) {
            return null;
        }

        int[] soma = new int[arrayNumeros1.length];
        for (int i = 0; i < arrayNumeros1.length; i++) {
            soma[i] = arrayNumeros1[i] + arrayNumeros2[i];
        }

        return soma;
    }

    static int[] f8(int[] arrayNumeros1, int[] arrayNumeros2) {
        if ((arrayNumeros1 == null) || (arrayNumeros1.length == 0)) {
            return null;
        }
        if ((arrayNumeros2 == null) || (arrayNumeros2.length == 0)) {
            return null;
        }

        int[] concatenacao = new int[arrayNumeros1.length + arrayNumeros2.length];
        for (int i = 0; i < arrayNumeros1.length; i++) {
            concatenacao[i] = arrayNumeros1[i];
        }
        for (int i = arrayNumeros1.length; i < arrayNumeros2.length; i++) {
            concatenacao[i] = arrayNumeros2[i];
        }

        return concatenacao;
    }


    static int[] f9(int[] arrayNumeros, int numero) {
        if ((arrayNumeros == null) || (arrayNumeros.length == 0)) {
            return null;
        }

        int[] multiplos = new int[arrayNumeros.length];
        for (int i = 0; i < arrayNumeros.length; i++) {
            multiplos[i] = arrayNumeros[i] * numero;
        }

        return multiplos;
    }

    static ArrayList<Integer> f10(int[] arrayNumeros1, int[] arrayNumeros2) {
        if ((arrayNumeros1 == null) || (arrayNumeros1.length == 0)) {
            return null;
        }
        if ((arrayNumeros2 == null) || (arrayNumeros2.length == 0)) {
            return null;
        }

        ArrayList<Integer> out = new ArrayList<>();
        for (int numProcurando : arrayNumeros1) {
            for (int numSendoProcurado : arrayNumeros2) {
                if (numProcurando == numSendoProcurado) {
                    out.add(numProcurando);
                    break;
                }
            }
        }

        return out;
    }

    static int[] f11(int[] arrayNumeros1, int[] arrayNumeros2) {
        if ((arrayNumeros1 == null) || (arrayNumeros1.length == 0)) {
            return null;
        }
        if ((arrayNumeros2 == null) || (arrayNumeros2.length == 0)) {
            return null;
        }

        int[] out = new int[arrayNumeros1.length];
        for (int i = 0; i < arrayNumeros1.length; i++) {
            if (i % 2 == 0) {
                out[i] = arrayNumeros1[i];
            } else {
                out[i] = arrayNumeros2[i];
            }
        }

        return out;
    }

    static String[] nomesDasFuncoes() {
        return new String[] {
                "abacate",
                "abacate",
                "abacate",
                "abacate",
                "abacate",
                "abacate",
                "abacate",
                "abacate",
                "abacate",
                "abacate",
                "abacate",
        };
    }
}
