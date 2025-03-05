package pt.ulusofona.aed;

public class Pessoa {
    String nome;
    String apelido;
    Apartamento apartamento = null;

    public Pessoa() {}

    public Pessoa(String nome, String apelido) {
        this.nome = nome;
        this.apelido = apelido;
    }

    public Pessoa(String nome, String apelido, Apartamento apartamento) {
        this.nome = nome;
        this.apelido = apelido;
        this.apartamento = apartamento;
    }


    @Override
    public String toString() {
        if (apartamento == null) {
            return nome + " " + apelido + " | " + "Morada: desconhecida";
        } else {
            return nome + " " + apelido + " | " + apartamento.toString();
        }
    }
}
