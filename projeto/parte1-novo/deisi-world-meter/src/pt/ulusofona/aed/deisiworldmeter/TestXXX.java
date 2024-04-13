package pt.ulusofona.aed.deisiworldmeter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestXXX {
    static File nomeFicheiro = new File("test-files");
    static String paises = "\\paises.csv";
    static String cidades = "\\cidades.csv";
    static String populacao = "\\populacao.csv";
    static int linhasFicheiroPaises = 8;
    static int linhasFicheiroCidades = 21;
    static int linhasFicheiroPopulacao = 34;
    static int linhasErradasPaises = 1;
    static int linhasErradasCidades = 3;
    static int linhasErradasPopulacao = 12;

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
}
