package pt.ulusofona.aed;

public class Main {
    static Pessoa[] tresAmigos() {
        return new Pessoa[] {
                new Pessoa("Oi", "Tchau"),
                new Pessoa("Hi", "Bey"),
                new Pessoa("Olá", "Adios")
        };
    }

    static Pessoa[] novosAmigos() {
        return new Pessoa[] {
                new Pessoa("Moro", "na rua"),
                new Pessoa("Tenho", "Casa", new Apartamento("Rua", 1, "Local", "País"))
        };
    }

    public static void main(String[] args) {
        System.out.println(novosAmigos()[1].toString());
    }
}
