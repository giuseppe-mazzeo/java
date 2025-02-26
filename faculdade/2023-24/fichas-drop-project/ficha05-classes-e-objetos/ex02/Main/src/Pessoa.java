public class Pessoa {
    String nome;
    String apelido;
    Apartamento apartamento;

    Pessoa() {}

    Pessoa(String nome, String apelido) {
        this.nome = nome;
        this.apelido = apelido;
    }

    Pessoa(String nome, String apelido, Apartamento apartamento) {
        this.nome = nome;
        this.apelido = apelido;
        this.apartamento = apartamento;
    }

    @Override
    public String toString() {
        if (apartamento == null) {
            return nome + " " + apelido + " | Morada: desconhecida";
        }

        return nome + " " + apelido + " | Morada: " + apartamento.toString();
    }
}
