package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class TestGetObject {
    @Test
    public void inputInvalido() {
        Main.parseFiles(new File("test-files/actors.csv"));
        Main.parseFiles(new File("test-files/directors.csv"));
        Main.parseFiles(new File("test-files/genres.csv"));
        Main.parseFiles(new File("test-files/genres_movies.csv"));
        Main.parseFiles(new File("test-files/movie_votes.csv"));
        Main.parseFiles(new File("test-files/movies.csv"));

        ArrayList resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add("movies.csv | 4 | 0 | -1");
        resultadoEsperado.add("actors.csv | 3 | 0 | -1");
        resultadoEsperado.add("directors.csv | 3 | 0 | -1");
        resultadoEsperado.add("genres.csv | 20 | 0 | -1");
        resultadoEsperado.add("genres_movies.csv | 3 | 0 | -1");
        resultadoEsperado.add("movie_votes.csv | 4 | 0 | -1");

        ArrayList resultadoAtual = Main.getObjects(TipoEntidade.INPUT_INVALIDO);

        if (resultadoEsperado.size() != resultadoAtual.size()) {
            throw new IllegalArgumentException("Erro: Dados a mais ou a menos. Esperado: " + resultadoEsperado.size() + ". Atual: " + resultadoAtual.size());
        }

        for (int pos = 0; pos < resultadoEsperado.size(); pos++) {
            if (!resultadoEsperado.get(pos).equals(resultadoAtual.get(pos))) {
                throw new IllegalArgumentException("Erro: Dados errados na posição " + pos + ". Esperado: " + resultadoEsperado.get(pos) + ". Atual: " + resultadoAtual.get(pos));
            }
        }
    }
}
