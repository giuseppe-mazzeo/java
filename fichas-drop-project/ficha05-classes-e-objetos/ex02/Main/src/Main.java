import java.sql.Array;

public class Main {
    public static void main(String[] args) {
        System.out.println(novosAmigos()[0].toString());
    }

    static Pessoa[] novosAmigos() {
        Apartamento apartamento = new Apartamento("Rua da Feirinha", 123, "LogoAli", "BemAqui");

        Pessoa pessoa1 = new Pessoa("João", "Santos");
        Pessoa pessoa2 = new Pessoa("Maria", "Eduarda", apartamento);

        return new Pessoa[] {pessoa1, pessoa2};
    }
}
