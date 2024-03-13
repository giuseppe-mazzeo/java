public class Main {
    public static void main(String[] args) {
        System.out.println(arvoreNatal(4));
    }

    static long multiplica(int n1, int n2) {
        if (n2 > 0) {
            return n1 + multiplica(n1, n2 - 1);
        } else {
            return 0;
        }
    }

    static String tabuadaEmLinha(int numero, int multiplicador) {
        if (multiplicador > 10) {
            return "";
        }

        if (multiplicador == 10) {
            return numero*multiplicador + tabuadaEmLinha(numero, multiplicador + 1);
        }

        return numero*multiplicador + "," + tabuadaEmLinha(numero , multiplicador + 1);
    }

    static String linhaDeAsteriscos(int dimensao) {
        if (dimensao == 0) {
            return "";
        }

        return "*" + linhaDeAsteriscos(dimensao - 1);
    }

    static String arvoreNatal(int altura) {
        if (altura == 0) {
            return "";
        }

        return arvoreNatal(altura - 1) + linhaDeAsteriscos(altura) + "\n";
    }
}
