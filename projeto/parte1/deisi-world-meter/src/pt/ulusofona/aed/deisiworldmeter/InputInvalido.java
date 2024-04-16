package pt.ulusofona.aed.deisiworldmeter;

public class InputInvalido {
    private int linhaOK,
                linhaNOK,
                primeiraLinhaErrada,
                numLinhaLidaFicheiro; /* Acrescenta um valor à linha lida, estando certa ou errada */

    private final String nomeFicheiro;


    public InputInvalido(String nomeFicheiro) {
        this.nomeFicheiro = nomeFicheiro;
        linhaOK = 0;
        linhaNOK = 0;
        primeiraLinhaErrada = -1;
        numLinhaLidaFicheiro = 1; /* Primeira linha de cada ficheiro é ignorada */
    }

    public void addLinhaOK() {
        linhaOK++;
    }

    public void addLinhaNOK() {
        if (linhaNOK == 0) { /* Guarda o número da primeira linha errada */
            primeiraLinhaErrada = numLinhaLidaFicheiro;
        }

        linhaNOK++;
    }

    public void addNumLinhaLidaFicheiro() {
        numLinhaLidaFicheiro++;
    }

    @Override
    public String toString() {
        return nomeFicheiro + " | " + linhaOK + " | " + linhaNOK + " | " + primeiraLinhaErrada;
    }
}
