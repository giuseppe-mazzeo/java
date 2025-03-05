package pt.ulusofona.aed;

public class Main {
    static Pessoa[] tresAmigos() {
    return new Pessoa[] {
                new Pessoa("Oi", "Tchau"),
                new Pessoa("Hi", "Bey"),
                new Pessoa("Olá", "Adios")
        };
    }

    public static void main(String[] args) {
        System.out.println(tresAmigos()[2].toString());
    }
}
