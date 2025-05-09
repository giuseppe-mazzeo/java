package pt.ulusofona.aed.deisimdb.testClasses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.aed.deisimdb.Diretor;

public class TestDiretor {
    Diretor diretor = new Diretor(1, "Carlos", 1234);

    @Test
    public void VerificarFuncoesGetters() {
        if (diretor.getDirectorId() != 1) {
            Assertions.fail("\n\nErro ao testar getDirectorId()");
        }

        if (!diretor.getDirectorName().equals("Carlos")) {
            Assertions.fail("\n\nErro ao testar getDirectorName()");
        }

        if (diretor.getMovieId() != 1234) {
            Assertions.fail("\n\nErro ao testar getMovieId()");
        }
    }


    @Test
    public void VerificarToString() {
        if (!diretor.toString().equals("1 | Carlos | 1234")) {
            Assertions.fail("\n\nErro ao testar toString()");
        }
    }
}
