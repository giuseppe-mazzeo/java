public class Main {
    public static int soma(int[] numeros) {
        int soma;

        if (numeros == null) {
            return 0;
        }

        soma = 0;
        for (int numeroAtual : numeros) {
            soma += numeroAtual;
        }

        return soma;
    }

    public static void main(String[] args) {
        System.out.println(soma(new int[]{0,1,2,3}));
    }
}
