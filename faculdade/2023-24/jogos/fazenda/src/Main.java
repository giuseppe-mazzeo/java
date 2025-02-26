import java.util.Scanner;
import java.util.SortedMap;

public class Main {
    static Jogador jogador = new Jogador();
    static Trabalhos trabalhos = new Trabalhos();
    static Mercado mercado = new Mercado();

    public static void mostrarMenu() {
        System.out.print(
                "\n=-=-=-=-=-=  Dia: " + mostrarData() + "  =-=-=-=-=-=\n" +
                "Emprego: " + jogador.getEmprego() + "  Dinheiro: " + jogador.getDinheiro() + "$  Saúde: " + jogador.getSaude() + "\n\n" +
                "1- " + trabalhos.mostrarTrabalho(jogador) + "\n" +
                "2- Ir no mercado\n" +
                "3- Ir ao Banco\n" +
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n" +
                "> "
        );
    }


    public static String mostrarData() {
        if (jogador.getDia() < 10 && jogador.getMes() < 10) {
            return "0" + jogador.getDia() + "/" + "0" + jogador.getMes();
        }
        if (jogador.getDia() < 10) {
            return "0" + jogador.getDia() + "/" + jogador.getMes();
        }
        if (jogador.getMes() < 10) {
            return jogador.getDia() + "/" + "0" + jogador.getMes();
        }

        return jogador.getDia() + "/" + jogador.getMes();
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int escolha;

        do {
            mostrarMenu();

            escolha = in.nextInt(); //ver como tratar se não for números

            switch (escolha) {
                case 1:
                    trabalhos.trabalhar(jogador);
                    break;

                case 2:
                    mercado.visitarMercado(jogador);
                    break;

                case 3:

                    break;
            }
        } while (jogador.getSaudeInt() != 0);

        System.out.println("\nMORREU");
    }
}
