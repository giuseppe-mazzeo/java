public class Main {
    public static void main(String[] args) {
    }

    static int f01(String texto, char letra, int inicio) {
        if (texto.length() == 0) {
            return 0;
        }
        if (texto.length() == 1) {
            if (texto.charAt(0) == letra) {
                return 1;
            } else {
                return 0;
            }
        }
        String textoEsq = texto.substring(0, 1);
        String textoDir = texto.substring(1, inicio);
        return f01(textoEsq,letra,inicio) + f01(textoDir,letra,inicio);
    }

    static int f02(String texto, char letra) {
        if (texto.length() == 0) {
            return 0;
        }
        if (texto.length() == 1) {
            if (texto.charAt(0) == letra) {
                return 1;
            } else {
                return 0;
            }
        }
        String textoEsq = texto.substring(0, 1);
        String textoDir = texto.substring(1, texto.length());
        return f02(textoEsq,letra) + f02(textoDir,letra);
    }

    static _______ f03(_______) {

    }

    static _______ f04(_______, ________) {

    }

    static String[] nomesDasFuncoes() {
        return new String[] {
                "procuraLetra", // nome verdadeiro da f01()
                "tocaMusica", // nome verdadeiro da f02()
        };
    }
}
