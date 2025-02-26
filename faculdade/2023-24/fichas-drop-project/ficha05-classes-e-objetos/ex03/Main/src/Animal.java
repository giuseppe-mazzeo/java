public class Animal {
    String nome;
    String especie;
    int nrRegisto;

    public Animal(String nome, String especia, int nrRegisto) {
        this.nome = nome;
        this.especie = especia;
        this.nrRegisto = nrRegisto;
    }

    @Override
    public String toString() {
        return especie + " chamado \"" + nome + "\" (nr. Reg " + nrRegisto + ")";
    }
}
