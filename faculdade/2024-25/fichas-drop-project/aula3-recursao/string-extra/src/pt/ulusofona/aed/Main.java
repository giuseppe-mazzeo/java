package pt.ulusofona.aed;

public class Main {
    static int f01(String palavra) {
        if (palavra.isEmpty()) {
            return 0;
        }

        if (palavra.length() == 1) {
            return Character.isUpperCase(palavra.charAt(0)) ? 1 : 0;
        }

        String palavraEsq = palavra.substring(0, 1);
        String palavraDir = palavra.substring(1, palavra.length());

        return f01(palavraEsq) + f01(palavraDir);
    }

    static boolean f02(String palavra, char letraAlvo) {
        if (palavra.isEmpty()) {
            return false;
        }

        if (palavra.length() == 1) {
            return (palavra.charAt(0) == letraAlvo);
        }

        String palavraEsq = palavra.substring(0, 1);
        String palavraDir = palavra.substring(1, palavra.length());

        return f02(palavraEsq, letraAlvo) || f02(palavraDir, letraAlvo);
    }

    static String[] nomesDasFuncoes() {
        return new String[] {
                "contaMaiuscula",
                "existeLetra"
        };
    }

    public static void main(String[] args) {
        System.out.println(f02("AED DAD", 'E'));
    }
}
