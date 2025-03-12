package pt.ulusofona.aed;

public class Pessoa {
    String nome;
    String apelido;
    int nrBI;


    public Pessoa() {}

    public Pessoa(String nome, String apelido, int nrBI) {
        this.nome = nome;
        this.apelido = apelido;
        this.nrBI = nrBI;
    }


    @Override
    public String toString() {
        return nome + " " + apelido + ", nrBI: " + nrBI;
    }
}
