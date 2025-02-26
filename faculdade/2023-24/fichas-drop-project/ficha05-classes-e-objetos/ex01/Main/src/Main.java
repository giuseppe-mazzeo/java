import java.sql.Array;

public class Main {
    public static void main(String[] args) {
    }

    static Pessoa[] tresAmigos() {
        Pessoa pessoa1 = new Pessoa("João", "Santos");
        Pessoa pessoa2 = new Pessoa("Maria", "Eduarda");
        Pessoa pessoa3 = new Pessoa("Catarina", "Souza");

        return new Pessoa[] {pessoa1, pessoa2, pessoa3};
    }
}
