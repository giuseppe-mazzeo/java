import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(magia(new int[]{3, 4, 2}, 2)));
    }

    static int[] magia(int[] numeros, int x) {
        int[] result = new int[numeros.length];
        for (int i = 0, j = numeros.length - 1; i < numeros.length; i++, j--) {
            result[j] = numeros[i] + x;
        }
        return result;
    }
}
