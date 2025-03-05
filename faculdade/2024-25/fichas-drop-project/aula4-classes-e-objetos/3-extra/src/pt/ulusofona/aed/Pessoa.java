package pt.ulusofona.aed;

public class Pessoa {
    String nome;
    String apelido;
    int nrBI;
    Animal[] animais = null;


    public Pessoa() {}

    public Pessoa(String nome, String apelido, int nrBI) {
        this.nome = nome;
        this.apelido = apelido;
        this.nrBI = nrBI;
    }

    public Pessoa(String nome, String apelido, int nrBI, Animal[] animais) {
        this.nome = nome;
        this.apelido = apelido;
        this.nrBI = nrBI;
        this.animais = animais;
    }



    @Override
    public String toString() {
        if (animais == null) {
            return "O " + nome + " " + apelido + " (nr. BI: " + nrBI + ")";
        }

        String textoAnimais = "";

        for (int posicao = 0; posicao < animais.length; posicao++) {
            if (animais[posicao] == null) {
                continue;
            }

            textoAnimais += "de um " + animais[posicao].toString();

            if (posicao < animais.length - 2) { // + 2 animais
                textoAnimais += ", ";
            } else if (posicao == animais.length - 2) { // 3 animais
                textoAnimais += " e ";
            }
        }

        return "O " + nome + " " + apelido + " (nr. BI: " + nrBI + ") é dono " + textoAnimais;
    }
}
