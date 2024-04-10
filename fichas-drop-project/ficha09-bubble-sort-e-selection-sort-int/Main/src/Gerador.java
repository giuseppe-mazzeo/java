import java.util.Random;

class Gerador {

    public static int[] criarArrayInts(int nr) {
        int[] resultado = new int[nr];
        Random r = new Random();

        for (int i = 0; i < nr; i++) {
            resultado[i] = r.nextInt(nr * 2);
        }
        return resultado;
    }

}