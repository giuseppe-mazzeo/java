package pt.ulusofona.aed;

public class Pessoa {
    String nome;
    String apelido;
    int nrBI;
    String paisMorada;

    public Pessoa() {}

    @Override
    public String toString() {
        return nome + " " + apelido + ", nrBI: " + nrBI + ", pais: " + paisMorada;
    }
}
