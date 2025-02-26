import java.util.Scanner;
import java.util.SortedMap;

public class Mercado {
    private final int precoHamburguer = 20;
    private final int precoJornal = 5;
    private void mostrarItensMercado(Jogador jogador) {
        System.out.print(
                "\n=-=-=-=-=-=  MERCADO  =-=-=-=-=-=\n" +
                "Dinheiro: " + jogador.getDinheiro() + "$\n\n" +

                "1- Hambúrguer " + precoHamburguer + "$\n" +
                "2- Jornal " + precoJornal + "$\n" +
                "0- Voltar\n" +
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n" +
                "> "
        );
    }

    public void visitarMercado(Jogador jogador) {
        Scanner in = new Scanner(System.in);
        int escolha;
        boolean escolhaValida = false;

        mostrarItensMercado(jogador);

        escolha = in.nextInt();

        switch (escolha) {
            case 0:
                return;

            case 1:
                if ((jogador.getSaudeInt() <= 7) && (jogador.getDinheiro() >= 20)) {
                    jogador.accSaude(3);
                    jogador.diminuirDinheiro(precoHamburguer);
                }
                break;

            case 2:
                if (jogador.getDinheiro() >= 5) {
                    jogador.diminuirDinheiro(precoJornal);
                    mostrarOfertasDeTrabalho();
                }
                break;
        }
    }

    private void mostrarOfertasDeTrabalho() {
        System.out.print(
            "\n=-=-=-=-=-=  MERCADO / Jornal  =-=-=-=-=-=\n" +
            "Preciso de babá \n" +
            "Trabalho no caixa do mercado\n" +
            "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n"
        );
    }
}
