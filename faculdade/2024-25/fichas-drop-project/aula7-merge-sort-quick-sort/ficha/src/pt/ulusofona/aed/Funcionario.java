package pt.ulusofona.aed;

public class Funcionario {
    String nome;
    int nivel;
    double salarioBase;
    int anoContratacao;

    public Funcionario(String nome, int nivel, double salarioBase, int anoContratacao) {
        this.nome = nome;
        this.nivel = nivel;
        this.salarioBase = salarioBase;
        this.anoContratacao = anoContratacao;
    }

    @Override
    public String toString() {
        if (anoContratacao >= 2024) {
            return nome + " | " + anoContratacao + " | " + nivel + " | " + salarioBase + " (Novato)";
        }

        return nome + " | " + anoContratacao + " | " + nivel + " | " + salarioBase;
    }
}
