public class Main {
    public static void main(String[] args) {
        System.out.println(obtemSubStringEmMaiuscula("bcde", 1,3));
    }

    static String obtemSubStringEmMaiuscula(String s, int inicio, int fim) {
        if (s == null || s.equals("") || s.length() < fim) {
            return "";
        }

        if (s.length() == 1) {
            return s.toUpperCase();
        }

        if (s.length() == fim) {
            return s;
        }

        String textoEsq = s.substring(0, inicio).toUpperCase();
        String textoDir = s.substring(inicio, fim).toUpperCase();

        return obtemSubStringEmMaiuscula(textoEsq, inicio, fim) + obtemSubStringEmMaiuscula(textoDir, inicio, fim);
    }

    static int aednacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        return aednacci(n - 1) + aednacci(n -2) + n;
    }

    static boolean sequenciaAlgarismos(String s, int pos) {
        if (s == null || s.equals("") || pos > s.length()) {
            return false;
        }

        if (s.length() == 1) {
            return Character.isDigit(s.charAt(0));
        }

        if (s.length() == pos) {
            return Character.isDigit(s.charAt(pos-1));
        }

        if (!Character.isDigit(s.charAt(pos))) {
            return false;
        } else {
            return sequenciaAlgarismos(s, pos + 1);
        }
    }
}
