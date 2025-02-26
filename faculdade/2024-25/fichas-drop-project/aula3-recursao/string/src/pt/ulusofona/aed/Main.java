package pt.ulusofona.aed;

public class Main {
    static int f01(String palavra, char letraAlvo, int inicio) {
        if (palavra.isEmpty() || inicio >= palavra.length()) {
            return 0;
        }

        if (palavra.charAt(inicio) == letraAlvo) {
            return f01(palavra, letraAlvo, inicio + 1) + 1;
        }

        return f01(palavra, letraAlvo, inicio + 1);
    }

    static int f02(String palavra, char letraAlvo) {
        if (palavra.isEmpty()) {
            return 0;
        }

        if (palavra.length() == 1) {
            if (palavra.charAt(0) == letraAlvo) {
                return 1;
            } else {
                return 0;
            }
        }

        String palavraEsq = palavra.substring(0, 1);
        String palavraDir = palavra.substring(1, palavra.length());

        return f02(palavraEsq, letraAlvo) + f02(palavraDir, letraAlvo);
    }

    static String f03(String palavra) {
        if (palavra.isEmpty()) {
            return "";
        }

        if (palavra.length() == 1) {
            return palavra;
        }

        String palavraEsq = palavra.substring(0, 1);
        String palavraDir = palavra.substring(1, palavra.length());

        return f03(palavraDir) + f03(palavraEsq);
    }

    static String f04(String palavra, char eliminarLetra) {
        if (palavra.isEmpty()) {
            return "";
        }

        if (palavra.length() == 1) {
            if (palavra.charAt(0) == eliminarLetra) {
                return "";
            } else {
                return palavra;
            }
        }

        String palavraEsq = palavra.substring(0, 1);
        String palavraDir = palavra.substring(1, palavra.length());

        return f04(palavraEsq, eliminarLetra) + f04(palavraDir, eliminarLetra);
    }

    static String[] nomesDasFuncoes() {
        return new String[] {
                "contadorLetra",
                "contadorLetra",
                "inverterPalavra",
                "removerLetra"
        };
    }


    public static void main(String[] args) {
        System.out.println(f04("fortnite", 'e'));
    }
}
