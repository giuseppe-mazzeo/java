package pt.ulusofona.aed.deisimdb.test_classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.aed.deisimdb.*;
import pt.ulusofona.aed.deisimdb.data_classes.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMain {

    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    //
    // Testes para a função parseFiles()
    //
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    @Test
    public void PastaNaoExiste() {
        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.parseFiles(new File("abacate"));
        Assertions.assertEquals(resultadoEsperado, resultadoAtual, "\n\n-> A pasta deveria não existir.");
    }


    @Test
    public void PastaVazia() {
        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.parseFiles(new File("test-files/pasta-vazia"));
        Assertions.assertEquals(resultadoEsperado, resultadoAtual, "\n\n-> ParseFiles não deveria ler uma pasta vazia.");
    }


    @Test
    public void PastaLeituraCorreta() {
        boolean resultadoEsperado = true;
        boolean resultadoAtual = Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(resultadoEsperado, resultadoAtual, "\n\n-> Leitura incorreta dos ficheiros.");
    }


    @Test
    public void VariasChamadasDeParseFiles() {
        boolean resultadoEsperado = true;
        boolean resultadoAtual;

        Main.parseFiles(new File("test-files"));

        int listaAtoresEsperado = Main.listaAtores.size();
        int listaDiretoresEsperado = Main.listaDiretores.size();
        int listaGeneroFilmesEsperado = Main.listaGeneroFilmes.size();
        int listaFilmesEsperado = Main.listaFilmes.size();
        int listaVotosFilmesEsperado = Main.listaVotosFilmes.size();
        int listaGenerosEsperado = Main.listaGeneros.size();
        String linhaOKEsperado = Arrays.toString(Main.linhaOK);
        String linhaNOKEsperado = Arrays.toString(Main.linhaNOK);
        String primeiraLinhaErradaEsperado = Arrays.toString(Main.primeiraLinhaErrada);

        Main.parseFiles(new File("test-files"));
        Main.parseFiles(new File("test-files"));
        Main.parseFiles(new File("test-files"));
        Main.parseFiles(new File("test-files"));
        Main.parseFiles(new File("test-files"));
        Main.parseFiles(new File("test-files"));

        int listaAtoresAtual = Main.listaAtores.size();
        int listaDiretoresAtual = Main.listaDiretores.size();
        int listaGeneroFilmesAtual = Main.listaGeneroFilmes.size();
        int listaVotosFilmesAtual = Main.listaVotosFilmes.size();
        int listaFilmesAtual = Main.listaFilmes.size();
        int listaGenerosAtual = Main.listaGeneros.size();
        String linhaOKAtual = Arrays.toString(Main.linhaOK);
        String linhaNOKAtual = Arrays.toString(Main.linhaNOK);
        String  primeiraLinhaErradaAtual = Arrays.toString(Main.primeiraLinhaErrada);

        if (listaAtoresEsperado == listaAtoresAtual && listaDiretoresEsperado == listaDiretoresAtual &&
            listaGeneroFilmesEsperado == listaGeneroFilmesAtual && listaVotosFilmesEsperado == listaVotosFilmesAtual &&
            listaFilmesEsperado == listaFilmesAtual && listaGenerosEsperado == listaGenerosAtual &&
            linhaOKEsperado.equals(linhaOKAtual) && linhaNOKEsperado.equals(linhaNOKAtual) &&
            primeiraLinhaErradaEsperado.equals(primeiraLinhaErradaAtual)
        ) {
            resultadoAtual = true;
        } else {
            resultadoAtual = false;
        }

        Assertions.assertEquals(resultadoEsperado, resultadoAtual,
                "\n\n-> As variáveis globais devem ser reinicializadas quando parseFile é novamente chamado." +
                "\n\nlistaAtoresEsperado - " + listaAtoresEsperado + " | listaAtoresAtual - " + listaAtoresAtual +
                "\nlistaDiretoresEsperado - " + listaDiretoresEsperado + " | listaDiretoresAtual - " + listaDiretoresAtual +
                "\nlistaGeneroFilmesEsperado - " + listaGeneroFilmesEsperado + " | listaGeneroFilmesAtual - " + listaGeneroFilmesAtual +
                "\nlistaVotosFilmesEsperado - " + listaVotosFilmesEsperado + " | listaVotosFilmesAtual - " + listaVotosFilmesAtual +
                "\nlistaFilmesEsperado - " + listaFilmesEsperado + " | listaFilmesAtual - " + listaFilmesAtual +
                "\nlistaGenerosEsperado - " + listaGenerosEsperado + " | listaGenerosAtual - " + listaGenerosAtual +
                "\nlinhaOKEsperado - " + linhaOKEsperado + " | linhaOKAtual - " + linhaOKAtual +
                "\nlinhaNOKEsperado - " + linhaNOKEsperado + " | linhaNOKAtual - " + linhaNOKAtual +
                "\nprimeiraLinhaErradaEsperado - " + primeiraLinhaErradaEsperado + " | primeiraLinhaErradaAtual - " + primeiraLinhaErradaAtual);
    }


    @Test
    public void VerificarFilmesComIdsMaioresOuMenoresDoQue1000() {
        Main.parseFiles(new File("test-files"));

        List<Filme> resultadoAtual = Main.listaFilmes;

        Assertions.assertEquals("1000 | Up from the Depths | 1979-06-01" ,resultadoAtual.get(0).toString());
        Assertions.assertEquals("999 | Up from the Depths | 1979-06-01 | 2" ,resultadoAtual.get(1).toString());
        Assertions.assertEquals("95853 | Bunny Drop | 2011-08-20" ,resultadoAtual.get(2).toString());
        Assertions.assertEquals("90532 | Shala | 2011-01-20" ,resultadoAtual.get(3).toString());
        Assertions.assertEquals("13460 | Doomsday | 2008-03-14" ,resultadoAtual.get(4).toString());
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =






    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    //
    // Testes para a função getObject()
    //
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    @Test
    public void TestarTipoEntidadeInputAtor() {
        Main.parseFiles(new File("test-files"));

        List<Ator> resultadoEsperado = Main.listaAtores;
        ArrayList resultadoAtual = Main.getObjects(TipoEntidade.ATOR);

        if (resultadoAtual == null) {
            Assertions.fail("\n\nArrayList do TipoEntidade.ATOR retornou null.");
        }

        for (int pos = 0; pos < resultadoEsperado.size(); pos++) {
            if (!resultadoEsperado.get(pos).equals(resultadoAtual.get(pos))) {
                Assertions.fail("\n\nDados errados na posição " + pos + ". Esperado - " + resultadoEsperado.get(pos) + ". Atual - " + resultadoAtual.get(pos));
            }
        }
    }


    @Test
    public void TestarTipoEntidadeInputFilme() {
        Main.parseFiles(new File("test-files"));

        List<Filme> resultadoEsperado = Main.listaFilmes;
        ArrayList resultadoAtual = Main.getObjects(TipoEntidade.FILME);

        if (resultadoAtual == null) {
            Assertions.fail("\n\nArrayList do TipoEntidade.FILME retornou null.");
        }

        for (int pos = 0; pos < resultadoEsperado.size(); pos++) {
            if (!resultadoEsperado.get(pos).equals(resultadoAtual.get(pos))) {
                Assertions.fail("\n\nDados errados na posição " + pos + ". Esperado - " + resultadoEsperado.get(pos) + ". Atual - " + resultadoAtual.get(pos));
            }
        }
    }


    @Test
    public void TestarTipoEntidadeRealizador() {
        Main.parseFiles(new File("test-files"));

        List<Diretor> resultadoEsperado = Main.listaDiretores;
        ArrayList resultadoAtual = Main.getObjects(TipoEntidade.REALIZADOR);

        if (resultadoAtual == null) {
            Assertions.fail("\n\nArrayList do TipoEntidade.REALIZADOR retornou null.");
        }

        for (int pos = 0; pos < resultadoEsperado.size(); pos++) {
            if (!resultadoEsperado.get(pos).equals(resultadoAtual.get(pos))) {
                Assertions.fail("\n\nDados errados na posição " + pos + ". Esperado - " + resultadoEsperado.get(pos) + ". Atual - " + resultadoAtual.get(pos));
            }
        }
    }


    @Test
    public void TestarTipoEntidadeGeneroCinematograficos() {
        Main.parseFiles(new File("test-files"));

        List<Genero> resultadoEsperado = Main.listaGeneros;
        ArrayList resultadoAtual = Main.getObjects(TipoEntidade.GENERO_CINEMATOGRAFICO);

        if (resultadoAtual == null) {
            Assertions.fail("\n\nArrayList do TipoEntidade.GENERO_CINEMATOGRAFICO retornou null.");
        }

        for (int pos = 0; pos < resultadoEsperado.size(); pos++) {
            if (!resultadoEsperado.get(pos).equals(resultadoAtual.get(pos))) {
                Assertions.fail("\n\nDados errados na posição " + pos + ". Esperado - " + resultadoEsperado.get(pos) + ". Atual - " + resultadoAtual.get(pos));
            }
        }
    }


    @Test
    public void TestarTipoEntidadeInputInvalido() {
        Main.parseFiles(new File("test-files"));

        ArrayList resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add("movies.csv | 6 | 2 | 3");
        resultadoEsperado.add("actors.csv | 4 | 2 | 4");
        resultadoEsperado.add("directors.csv | 3 | 2 | 2");
        resultadoEsperado.add("genres.csv | 20 | 2 | 4");
        resultadoEsperado.add("genres_movies.csv | 3 | 2 | 2");
        resultadoEsperado.add("movie_votes.csv | 4 | 2 | 2");

        ArrayList resultadoAtual = Main.getObjects(TipoEntidade.INPUT_INVALIDO);

        if (resultadoAtual == null) {
            Assertions.fail("\n\nArrayList do TipoEntidade.INPUT_INVALIDO retornou null.");
        }

        for (int pos = 0; pos < resultadoEsperado.size(); pos++) {
            if (!resultadoEsperado.get(pos).equals(resultadoAtual.get(pos))) {
                Assertions.fail("\n\nDados errados na posição " + pos + ". Esperado - " + resultadoEsperado.get(pos) + ". Atual - " + resultadoAtual.get(pos));
            }
        }
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
}