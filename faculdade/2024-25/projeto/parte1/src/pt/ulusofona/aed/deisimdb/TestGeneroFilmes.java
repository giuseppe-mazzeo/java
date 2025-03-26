package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestGeneroFilmes {
    GeneroFilme generoFilme = new GeneroFilme(1, 1, "GeneroFilme");

    @Test
    public void VerificarFuncoesGetters() {
        if (generoFilme.getGenreId() != 1) {
            Assertions.fail("\n\nErro ao testar getGenreId()");
        }

        if (generoFilme.getMovieId() != 1) {
            Assertions.fail("\n\nErro ao testar getMovieId()");
        }

        if (!generoFilme.getGenreName().equals("GeneroFilme")) {
            Assertions.fail("\n\nErro ao testar getGenreName()");
        }
    }


    @Test
    public void VerificarToString() {
        if (!generoFilme.toString().equals("1 | GeneroFilme")) {
            Assertions.fail("\n\nErro ao testar toString()");
        }
    }
}
