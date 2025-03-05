package pt.ulusofona.aed;

public class Animal {
    String nome;
    String especie;
    int nrRegisto;



    public Animal() {}

    public Animal(String nome, String especie, int nrRegisto) {
        this.nome = nome;
        this.especie = especie;
        this.nrRegisto = nrRegisto;
    }



    @Override
    public String toString() {
        return especie + " chamado \"" + nome + "\" (nr. Reg " + nrRegisto + ")";
    }
}
