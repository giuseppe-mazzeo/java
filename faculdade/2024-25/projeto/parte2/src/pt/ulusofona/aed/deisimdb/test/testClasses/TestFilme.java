package pt.ulusofona.aed.deisimdb.test.testClasses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.aed.deisimdb.Filme;

public class TestFilme {
    Filme filme1000 = new Filme(1, "O Sonho de um Programador", 1.0f, 10, "11-22-3333");
    Filme filme500 = new Filme(2, "O Pesadelo de um Programador", 1.0f, 10, "11-22-3333");

    @Test
    public void VerificarFuncoesGetters() {
        if (filme1000.getMovieId() != 1 && filme500.getMovieId() != 2) {
            Assertions.fail("\n\nErro ao testar getMovieId()");
        }

        if (!filme1000.getMovieName().equals("O Sonho de um Programador") && !filme500.getMovieName().equals("O Pesadelo de um Programador")) {
            Assertions.fail("\n\nErro ao testar getMovieName()");
        }

    }


    @Test
    public void VerificarToString() {
        if (!filme1000.toString().equals("1 | O Sonho de um Programador | 3333-22-11") &&
            !filme500.toString().equals("2 | O Pesadelo de um Programador | 3333-22-11 | 0")) {
            Assertions.fail("\n\nErro ao testar toString()");
        }

        //filme500.accNumAtoresEnvolvidos();
        //filme500.accNumAtoresEnvolvidos();
        //filme500.accNumAtoresEnvolvidos();

        if (!filme500.toString().equals("2 | O Pesadelo de um Programador | 3333-22-11 | 3")) {
            Assertions.fail("\n\nErro ao testar toString()");
        }
    }
}
