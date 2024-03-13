import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(f03(new int[]{1,-2,0,3}));
    }

    static int f01(int[] numeros, int inicio) {
        if (numeros == null || inicio >= numeros.length) {
            return 0;
        }

        return numeros[inicio] + f01(numeros, inicio+1);
    }

    static int f02(int[] numeros, int inicio) {
        if (numeros == null || inicio >= numeros.length) {
            return 0;
        }

        if (numeros.length + inicio > numeros.length) {
            return numeros[inicio];
        }

        System.out.println(numeros[inicio]);

        if (numeros[inicio] > numeros[inicio+1]) {
            return numeros[inicio];
        } else {
            return f02(numeros, inicio+1);
        }
    }

    static int f03(int[] numeros) {
        if (numeros == null) {
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
                "calculaSoma", // nome verdadeiro da f01()
                "procuraMaior", // nome verdadeiro da f02()
                "calculaSoma", // nome verdadeiro da f03()
                };
    }
}
