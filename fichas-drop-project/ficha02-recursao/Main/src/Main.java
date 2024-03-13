public class Main {
    public static void main(String[] args) {
        System.out.println(f03(-1000,5));
    }

    static int f01(int numero) {
        if (numero <= 0) {
            return 0;
        }

        if (numero == 1) {
            return 1;
        }

        return f01(numero - 1) + numero;
    }

    static int f02(int numero) {
        if (numero <= 0) {
            return 0;
        }

        return f02(numero - 1) + 2;
    }

    static String f03(int num1, int num2) {
        if (num1 == num2) {
            return "" + num1;
        }

        if (num1 < num2) {
            return num1 + f03(num1 + 1, num2);
        }

        return num2 + f03(num1, num2 + 1);
    }

    static String[] nomesDasFuncoes() {
        return new String[] {
                "matematicaMaluca", // nome verdadeiro da f01()
                "contaOrelhasCoelhos", // nome verdadeiro da f02()
                "escreveEcraIntervaloNumero" // nome verdadeiro da f02()
	    };
    }
}
