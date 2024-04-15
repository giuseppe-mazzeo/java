package pt.ulusofona.aed.deisiworldmeter;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestXXX {
    static File nomeFicheiro = new File("test-files");
    static String paises = "\\paises.csv";
    static String cidades = "\\cidades.csv";
    static String populacao = "\\populacao.csv";
    static int linhasFicheiroPaises = 14;
    static int linhasFicheiroCidades = 22;
    static int linhasFicheiroPopulacao = 42;
    static int linhasErradasPaises = 1;
    static int linhasErradasCidades = 3;
    static int linhasErradasPopulacao = 10;

    @Test
    public void testPaisesRepetidos() {
        Main.lerDadosFicheiro(new File(nomeFicheiro + paises));
        ArrayList<String[]> dadosPaises = Main.dadosPaises;

        assertEquals(linhasFicheiroPaises - linhasErradasPaises, dadosPaises.size());
    }

    @Test
    public void testLinhasIncorretas() {
        Main.parseFiles(nomeFicheiro);

        ArrayList<String[]> dadosPaises = Main.dadosPaises;
        assertEquals(linhasFicheiroPaises - linhasErradasPaises, dadosPaises.size());


        ArrayList<String[]> dadosPopulacao = Main.dadosPopulacao;
        assertEquals(linhasFicheiroPopulacao -linhasErradasPopulacao, dadosPopulacao.size());

        ArrayList<String[]> dadosCidades = Main.dadosCidades;
        assertEquals(linhasFicheiroCidades - linhasErradasCidades, dadosCidades.size());
    }

    @Test
    public void testFicheiroInexistente() {
        boolean out = Main.parseFiles(new File(nomeFicheiro + "blabla"));

        assertFalse(out);
    }

    @Test
    public void testInputInvalidoToString() {
        Main.parseFiles(nomeFicheiro);

        assertEquals("paises.csv | 13 | 0 | -1",Main.getObjects(TipoEntidade.INPUT_INVALIDO).get(0));
        assertEquals("cidades.csv | 19 | 2 | 5",Main.getObjects(TipoEntidade.INPUT_INVALIDO).get(1));
        assertEquals("populacao.csv | 32 | 7 | 24",Main.getObjects(TipoEntidade.INPUT_INVALIDO).get(2));
    }

    @Test
    public void testPaisToString() {
        Main.parseFiles(nomeFicheiro);

        assertEquals("Brasil | 76 | BR | BRA",Main.getObjects(TipoEntidade.PAIS).get(0));
        assertEquals("Portugal | 720 | PT | PRT | 6",Main.getObjects(TipoEntidade.PAIS).get(1));
        assertEquals("Angola | 24 | AO | AGO",Main.getObjects(TipoEntidade.PAIS).get(2));
    }

    @Test
    public void testGetObjectsInputInvalido() {
        assertEquals(3, Main.getObjects(TipoEntidade.INPUT_INVALIDO).size());
    }
}
