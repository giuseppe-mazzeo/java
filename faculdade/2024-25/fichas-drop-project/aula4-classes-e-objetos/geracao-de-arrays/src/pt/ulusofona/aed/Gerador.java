package pt.ulusofona.aed;

class Gerador {

    /*
      Esta função gera um array de números não ordenados.
    */
    static int[] gerarNumeros(int max) {
        int[] resultado = new int[max];
        int i = 0;
        while (i < max) {
            if (i % 2 == 0) {
                resultado[i] = i + 1;
            } else {
                resultado[i] = i + 3;
            }
            i++;
        }
        return resultado;
    }

}