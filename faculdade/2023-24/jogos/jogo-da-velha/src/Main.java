import java.util.*;

public class Main {
    private final static String ERRO_ESCOLHA_INVALIDA = "Escolha inválida";
    private final static String ERRO_JA_FOI_USADA = "Já foi usada essa letra";
    private final static String ERRO_LETRA_INVALIDA = "Letra inválida";
    private final static String ERRO_PALAVRA_MUITO_GRANDE = "Excedeu o limite da palavra";
    private final static int modoFacil = 16;
    private final static int modoMedio = 10;
    private final static int modoDificil = 7;
    private final static int modoInfinito = -1;

    private final static ArrayList<Character> todosCaracteresAcentuados = new ArrayList<>(Arrays.asList('á', 'à', 'ã', 'â',
                                                                                                        'Á', 'À', 'Ã', 'Â',
                                                                                                        'é', 'è', 'ê',
                                                                                                        'É' ,'È', 'Ê',
                                                                                                        'í', 'ì', 'î',
                                                                                                        'Í', 'Ì', 'Î',
                                                                                                        'ó', 'ò', 'õ', 'ô',
                                                                                                        'Ó', 'Ò', 'Õ', 'Ô',
                                                                                                        'ú', 'ù', 'û',
                                                                                                        'Ú', 'Ù', 'Û',
                                                                                                        'ç', 'Ç'));

    private static int menuJogo() {
        Scanner in = new Scanner (System.in);
        String escolha;
        int limiteDeErros = 0;

        System.out.println(
                "\n\n\n||=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=||\n" +
                        "::                        J O G O   D A   V E L H A                          ::\n" +
                        "::                                                                           ::\n" +
                        "::\tBem-vindo(a) ao clássico Jogo da Velha                                   ::\n" +
                        "::                                                                           ::\n" +
                        "::\tPrimeiramente, escolha a dificuldade do jogo:                            ::\n" +
                        "::\t\t1.Fácil - " + modoFacil + " erros                                                   ::\n" +
                        "::\t\t2.Médio - " + modoMedio + " erros                                                   ::\n" +
                        "::\t\t3.Difícil - " + modoDificil + " erros                                                  ::\n" +
                        "::\t\t4.Infinito - oo erros. O jogo acaba quando adivinhar a palavra       ::\n" +
                        "::                                                                           ::\n" +
                        "||=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=||\n"
        );

        do {
            System.out.print("> ");
            escolha = in.next();

            switch (escolha) {
                case "1": limiteDeErros = modoFacil; break;
                case "2": limiteDeErros = modoMedio; break;
                case "3": limiteDeErros = modoDificil; break;
                case "4": limiteDeErros = modoInfinito; break;
                default: System.out.println(ERRO_ESCOLHA_INVALIDA);
            }
        } while (limiteDeErros == 0);

        //in.close();

        return limiteDeErros;
    }


    private static String[] criacaoDoJogo() {
        String[] escolhasDoCriador = new String[3];

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n" +
                "||=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=||\n" +
                "::                       C R I A Ç Ã O   D O   J O G O                       ::\n" +
                "::                                                                           ::\n" +
                "::\t\tEscreva uma palavra:                                                 ::\n" +
                "::                                                                           ::\n" +
                "||=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=||\n"
        );

        escolhasDoCriador[0] = verificaCaracteresValidos();

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n" +
                "||=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=||\n" +
                "::                       C R I A Ç Ã O   D O   J O G O                       ::\n" +
                "::                                                                           ::\n" +
                "::\t\tEscreva uma palavra: " + escolhasDoCriador[0] + preencherEspaco(48 - escolhasDoCriador[0].length()) + "::\n" +
                "::                                                                           ::\n" +
                "::\t\tAgora um tema relacionado:                                           ::\n" +
                "::                                                                           ::\n" +
                "||=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=||\n"
        );

        escolhasDoCriador[1] = verificaCaracteresValidos();

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n" +
                "||=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=||\n" +
                "::                       C R I A Ç Ã O   D O   J O G O                       ::\n" +
                "::                                                                           ::\n" +
                "::\t\tEscreva uma palavra: " + escolhasDoCriador[0] + preencherEspaco(48 - escolhasDoCriador[0].length()) + "::\n" +
                "::                                                                           ::\n" +
                "::\t\tAgora um tema relacionado: " + escolhasDoCriador[1] + preencherEspaco(42 - escolhasDoCriador[1].length()) + "::\n" +
                "::                                                                           ::\n" +
                "||=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=||\n"
        );

        System.out.println("Deseja alterar alguma configuração? (S / N)");

        if (recriarJogo()) {
            escolhasDoCriador = criacaoDoJogo();
        }

        return escolhasDoCriador;
    }


    private static String preencherEspaco(int preenchimento) {
        return " ".repeat(Math.max(0, preenchimento));
    }


    private static String verificaCaracteresValidos() {
        Scanner in = new Scanner (System.in);
        String escolha;

        do {
            System.out.print("> ");
            escolha = in.next();

            for (int i = 0; i < escolha.length(); i++) {
                if (!(escolha.charAt(i) >= 'a' && escolha.charAt(i) <= 'z') &&
                        !(escolha.charAt(i) >= 'A' && escolha.charAt(i) <= 'Z') &&
                        !todosCaracteresAcentuados.contains(escolha.charAt(i))
                ) {
                    escolha = "";
                    System.out.println(ERRO_ESCOLHA_INVALIDA);
                } else if (escolha.length() > 35) {
                    escolha = "";
                    System.out.println(ERRO_PALAVRA_MUITO_GRANDE);
                }
            }
        } while (escolha.equals(""));

        //in.close();

        return escolha;
    }


    private static boolean recriarJogo() {
        Scanner in = new Scanner(System.in);
        String escolha;
        Boolean veracidade = null;

        do {
            System.out.print("> ");
            escolha = in.next();

            if (escolha.equals("S") || escolha.equals("s") ||
                    escolha.equals("SIM") || escolha.equals("sim"))
            {
                veracidade = true;
            } else if (escolha.equals("N") || escolha.equals("n") ||
                    escolha.equals("NAO") || escolha.equals("nao") ||
                    escolha.equals("NÃO") || escolha.equals("não"))
            {
                veracidade = false;
            } else {
                System.out.println(ERRO_ESCOLHA_INVALIDA);
            }
        } while (veracidade == null);

        //in.close();

        return veracidade;
    }


    private static int jogoEmAndamento(String palavraMisteriosa, String tema, int tamanhoPalavra, int limiteDeErros) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> desenhoBoneco = new ArrayList<>(Collections.nCopies(3, "       "));
        ArrayList<Character> letrasJaUsadas = new ArrayList<>();
        char[] palavraOcultada = new char[tamanhoPalavra];
        char letra;
        String aumentoDaMargem = "";
        String aumentoDoEspacamento = "";
        String aumentoDaMargemTemaEsq = "";
        String aumentoDaMargemTemaDir = "";
        int numLetrasCertas = 0;
        int numCaracteresAcentuados = 0;
        int tentativasErradas = 0;
        boolean acertou = false;

        Arrays.fill(palavraOcultada, '_');

        for (int i = 0; i < tamanhoPalavra; i++) {
            if (todosCaracteresAcentuados.contains(palavraMisteriosa.charAt(i))) {
                numCaracteresAcentuados++;
            }
        }

        if (tamanhoPalavra >= 21) {
            aumentoDaMargem = "-" + "=-".repeat(Math.max(0, tamanhoPalavra - 21));
            aumentoDoEspacamento = " " + preencherEspaco(tamanhoPalavra - 21) + preencherEspaco(tamanhoPalavra - 21);
            for (int i = 0; i < 29 + ((tamanhoPalavra - 21) - tema.length()/2); i++) {
                if (i % 2 == 0) {
                    aumentoDaMargemTemaEsq += "=";
                    aumentoDaMargemTemaDir += "-";
                } else {
                    aumentoDaMargemTemaEsq += "-";
                    aumentoDaMargemTemaDir += "=";
                }
            }

            if (tema.length() % 4 == 0) {
                aumentoDaMargemTemaEsq += "=";
                aumentoDaMargemTemaDir += "-";
            } else if (tema.length() % 2 == 0) {
                if (tamanhoPalavra % 2 == 0) {
                    aumentoDaMargemTemaEsq += "-";
                    aumentoDaMargemTemaDir += "=";
                } else {
                    aumentoDaMargemTemaEsq += "=";
                    aumentoDaMargemTemaDir += "-";
                }
            } else {
                if (tamanhoPalavra % 2 == 0) {
                    aumentoDaMargemTemaDir += "-";
                } else {
                    aumentoDaMargemTemaDir += "=";
                }
            }

        } else {
            for (int i = 0; i < (29 - (tema.length()/2)); i++) {
                if (i % 2 == 0) {
                    aumentoDaMargemTemaEsq += "=";
                    aumentoDaMargemTemaDir += "-";
                } else {
                    aumentoDaMargemTemaEsq += "-";
                    aumentoDaMargemTemaDir += "=";
                }
            }

            if (tema.length() % 4 == 0) {
                aumentoDaMargemTemaDir += "=";
            } else if (tema.length() % 2 == 0) {
                aumentoDaMargemTemaDir += "-";
            }
        }

        do {
            System.out.println("||" + aumentoDaMargemTemaEsq + "   O tema é: " + tema + "   " + aumentoDaMargemTemaDir + "||");

            if (numCaracteresAcentuados != 0) {
                System.out.print("::\t  Dica: essa palavra tem " + numCaracteresAcentuados);

                if (numCaracteresAcentuados == 1) {
                    System.out.println(" letra acentuada                               ::");
                } else if (numCaracteresAcentuados > 9) {
                    System.out.println(" letras acentuadas                            ::");
                } else {
                    System.out.println(" letras acentuadas                             ::");
                }
            } else {
                System.out.print("::" + preencherEspaco(75) +  aumentoDoEspacamento + "::\n");
            }

            if (tentativasErradas == 1) desenhoBoneco.set(0, limiteDeErros == modoDificil ? "  ( )  " : " (   ) ");
            if (tentativasErradas == 2) desenhoBoneco.set(1, "   I   ");
            if (tentativasErradas == 3) desenhoBoneco.set(1, " - I   ");
            if (tentativasErradas == 4) desenhoBoneco.set(1, " - I - ");
            if (tentativasErradas == 5) desenhoBoneco.set(2, "  /    ");
            if (tentativasErradas == 6) desenhoBoneco.set(2, "  / \\  ");

            if (tentativasErradas == 7) desenhoBoneco.set(0, " (º  ) ");
            if (tentativasErradas == 8) desenhoBoneco.set(0, " (º º) ");
            if (tentativasErradas == 9) desenhoBoneco.set(0, " (º_º) ");

            if (tentativasErradas == 10) desenhoBoneco.set(1, "o- I - ");
            if (tentativasErradas == 11) desenhoBoneco.set(1, "o- I -o");
            if (tentativasErradas == 12) desenhoBoneco.set(2, " _/ \\  ");
            if (tentativasErradas == 13) desenhoBoneco.set(2, " _/ \\_ ");
            if (tentativasErradas == 14) desenhoBoneco.set(0, "<(º_º) ");
            if (tentativasErradas == 15) desenhoBoneco.set(0, "<(º_º)>");

            System.out.print(
                    "::          ,_______." + preencherEspaco(56) + aumentoDoEspacamento + "::\n" +
                    "::          |       |" + preencherEspaco(56) + aumentoDoEspacamento + "::\n" +
                    "::          |    " + desenhoBoneco.get(0) + preencherEspaco(53) + aumentoDoEspacamento + "::\n" +
                    "::          |    " + desenhoBoneco.get(1) + preencherEspaco(53) + aumentoDoEspacamento + "::\n" +
                    "::          |    " + desenhoBoneco.get(2) + preencherEspaco(53) + aumentoDoEspacamento + "::\n" +
                    "::     _ __-¨-__ _\t\t\t\t");

            for (int i = 0; i < tamanhoPalavra; i++) {
                System.out.print(palavraOcultada[i]);

                if (i != tamanhoPalavra - 1) {
                    System.out.print(" ");
                }
            }

            System.out.println(
                    preencherEspaco(39 - (tamanhoPalavra*2 - 2)) + "     ::\n" +
                    "::                                                                           " +  aumentoDoEspacamento + "::\n" +
                    "||=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + aumentoDaMargem + "||\n"
            );


            if (letrasJaUsadas.size() != 0) {
                for (Character i : letrasJaUsadas) {
                    System.out.print(i + "  ");
                }

                System.out.println("\n");
            }

            System.out.print("> ");
            letra = in.next().charAt(0);

            System.out.println("\n\n\n\n\n");

            if ((letra >= 'a' && letra <= 'z') || (letra >= 'A' && letra <= 'Z') || todosCaracteresAcentuados.contains(letra)) {
                for (int i = 0; i < tamanhoPalavra; i++) {
                    if (tentativasErradas < limiteDeErros - 1 &&
                            (Character.toLowerCase(letra) == palavraOcultada[i] || letrasJaUsadas.contains(Character.toLowerCase(letra))))
                    {
                        System.out.println(ERRO_JA_FOI_USADA);
                        break;
                    }

                    if (palavraMisteriosa.charAt(i) == Character.toLowerCase(letra)) {
                        palavraOcultada[i] = Character.toLowerCase(letra);

                        if (todosCaracteresAcentuados.contains(letra)) {
                            numCaracteresAcentuados--;
                        }

                        acertou = true;
                        numLetrasCertas++;
                    }
                }

                if (!letrasJaUsadas.contains(Character.toLowerCase(letra)) && !palavraMisteriosa.contains(Character.toLowerCase(letra)+"")) {
                    letrasJaUsadas.add(Character.toLowerCase(letra));
                    Collections.sort(letrasJaUsadas);
                }
            } else {
                if (tentativasErradas < limiteDeErros - 1) {
                    System.out.println(ERRO_LETRA_INVALIDA);
                }
            }

            if (acertou) {
                acertou = false;
            } else {
                tentativasErradas++;
            }

            if ((tentativasErradas == limiteDeErros)) {
                tentativasErradas = -1;
                break;
            }
        } while ((numLetrasCertas != tamanhoPalavra));

        //in.close();

        return tentativasErradas;
    }


    private static void jogoCompleto() {
        String palavraMisteriosa;
        String[] escolhasDoCriador;
        String tema;
        int tentativasErradas;
        int limiteDeErros = modoInfinito;

        //limiteDeErros = menuJogo();

        escolhasDoCriador = criacaoDoJogo();

        palavraMisteriosa = escolhasDoCriador[0].toLowerCase(new Locale("pt", "BR"));
        tema = escolhasDoCriador[1];

        for (int i = 0; i < 30; i++) System.out.println();

        tentativasErradas = jogoEmAndamento(palavraMisteriosa, tema, palavraMisteriosa.length(), limiteDeErros);

        if (tentativasErradas != -1) {
            System.out.println(
                    "||=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=||\n" +
                    "::                       V I T Ó R I A                       ::\n" +
                    "::                                                           ::\n" +
                    "::     Meus parabéns! Você acertou a palavra!                ::\n" +
                    "::                                                           ::\n" +
                    "::     A palavra era " + palavraMisteriosa + preencherEspaco(35 - palavraMisteriosa.length()) + "     ::\n" +
                    "::                                                           ::\n" +
                    "::     Total de erros: " + tentativasErradas + preencherEspaco(38 - (tentativasErradas < 9 ? 1 : 2 )) + "::\n" +
                    "::                                                           ::\n" +
                    "||=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=||"
            );
        } else {
            System.out.println(
                    "||=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=||\n" +
                    "::                       D E R R O T A                       ::\n" +
                    "::                                                           ::\n" +
                    "::     Infelizmente você perdeu... Seu burro!                ::\n" +
                    "::                                                           ::\n" +
                    "::     A palavra era " + palavraMisteriosa + preencherEspaco(35 - palavraMisteriosa.length()) + "     ::\n" +
                    "::                                                           ::\n" +
                    "||=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=||"
            );
        }
    }


    public static void main(String[] args) {
        jogoCompleto();
    }
}
