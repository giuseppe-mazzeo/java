package pt.ulusofona.aed.deisimdb.testClasses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.aed.deisimdb.Genero;

public class TestGenero {
    Genero genero = new Genero(1, "Ficção Científica");

    @Test
    public void VerificarFuncoesGetters() {
        if (genero.getGenreId() != 1) {
            Assertions.fail("\n\nErro ao testar getGenreId()");
        }

        if (!genero.getGenreName().equals("Ficção Científica")) {
            Assertions.fail("\n\nErro ao testar getGenreName()");
        }
    }


    @Test
    public void VerificarToString() {
        if (!genero.toString().equals("1 | Ficção Científica")) {
            Assertions.fail("\n\nErro ao testar toString()");
        }
    }
}
