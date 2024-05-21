package pt.ulusofona.aed.deisiworldmeter;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/* ======================
      Índice de testes:

   -- Classe Main
   -- Classe Cidade
   -- Classe Pais
   -- Classe Populacao
   -- Classe InputInvalido
   -- Enum TipoEntidade
   ======================
*/

public class TestXXX {

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /* Testa classe Main */
    @Test
    public void testFicheiroInexistente() {
        boolean retorno = Main.parseFiles(new File("blabla"));

        assertFalse(retorno);
    }

    @Test
    public void testFicheiroNull() {
        boolean retorno = Main.parseFiles(null);

        assertFalse(retorno);
    }

    @Test
    public void testFicheiroEsperados() {
        boolean retorno = Main.parseFiles(new File("test-files"));

        assertTrue(retorno);
    }

    @Test
    public void testDadosCorretos() {
        Main.lerDadosFicheiro(new File("test-files", "cidades-tudo-correto.csv"));
        Main.lerDadosFicheiro(new File("test-files", "paises-tudo-correto.csv"));
        Main.lerDadosFicheiro(new File("test-files", "populacao-tudo-correto.csv"));

        ArrayList<String> paises = Main.getObjects(TipoEntidade.PAIS);
        ArrayList<String> cidades = Main.getObjects(TipoEntidade.CIDADE);
        ArrayList<String> inputInvalido = Main.getObjects(TipoEntidade.INPUT_INVALIDO);

        assertEquals("Brasil | 76 | BR | BRA", paises.get(0));
        assertEquals("Portugal | 720 | PT | PRT | 19", paises.get(1));
        assertEquals("Angola | 24 | AO | AGO", paises.get(2));

        assertEquals("amrode sufla | AF | 05 | 66 | (34.198303,66.955414)", cidades.get(0));
        assertEquals("abrantes | PT | 18 | 13646.0 | (39.466667,-8.2)", cidades.get(4));
        assertEquals("rio de janeiro | BR | 12 | 21.6023742.0 | (-22.9,-43.233333)", cidades.get(19));

        assertEquals("paises-tudo-correto.csv | 3 | 10 | 5", inputInvalido.get(0));
        assertEquals("cidades-tudo-correto.csv | 21 | 0 | -1", inputInvalido.get(1));
        assertEquals("populacao-tudo-correto.csv | 44 | 0 | -1", inputInvalido.get(2));
    }

    @Test
    public void testLinhasErradas() {
        Main.lerDadosFicheiro(new File("test-files", "cidades-dados-errados.csv"));
        Main.lerDadosFicheiro(new File("test-files", "paises-dados-errados.csv"));
        Main.lerDadosFicheiro(new File("test-files", "populacao-dados-errados.csv"));

        ArrayList<String> paises = Main.getObjects(TipoEntidade.PAIS);
        ArrayList<String> cidades = Main.getObjects(TipoEntidade.CIDADE);
        ArrayList<String> inputInvalido = Main.getObjects(TipoEntidade.INPUT_INVALIDO);

        assertEquals("Brasil | 76 | BR | BRA", paises.get(0));
        assertEquals("Portugal | 720 | PT | PRT | 21", paises.get(1));
        assertEquals("Angola | 24 | AO | AGO", paises.get(2));

        assertEquals("abacate | XX | 20 | 2776125.0 | (-8.836803999999999,13.23317399999998)",cidades.get(0));
        assertEquals("amrode sufla | AF | 05 | 66 | (34.198303,66.955414)", cidades.get(1));
        assertEquals("ola | YY | 20 | 2776125.0 | (-8.836803999999999,13.23317399999998)", cidades.get(5));
        assertEquals("zombie | ZZ | 20 | 2776125.0 | (-8.836803999999999,13.23317399999998)", cidades.get(16));

        assertEquals("paises-dados-errados.csv | 4 | 14 | 2", inputInvalido.get(0));
        assertEquals("cidades-dados-errados.csv | 17 | 7 | 10", inputInvalido.get(1));
        assertEquals("populacao-dados-errados.csv | 43 | 4 | 8", inputInvalido.get(2));
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - */



    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /* Testa classe Cidade */
    @Test
    public void testCidadeToString() {
        Cidade cidade = new Cidade(
                "pt",
                "alcabideche",
                "14",
                "33315.0",
                "38.73366",
                "-9.409278"
        );

        assertEquals("alcabideche | PT | 14 | 33315.0 | (38.73366,-9.409278)", cidade.toString());
    }
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - */



    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /* Testa classe Pais */
    @Test
    public void testPaisToString() {
        Pais pais = new Pais(
                76,
                "br",
                "bra",
                "Brasil"
        );

        assertEquals(76, pais.getId());
        assertEquals("Brasil | 76 | BR | BRA", pais.toString());
    }

    @Test
    public void testPaisIdMaior700ToString() {
        Pais brasil = new Pais(
                701,
                "br",
                "bra",
                "Brasil"
        );

        brasil.addPaisIdMaior700();
        brasil.addPaisIdMaior700();
        brasil.addPaisIdMaior700();

        assertEquals(701, brasil.getId());
        assertEquals("Brasil | 701 | BR | BRA | 3", brasil.toString());

        Pais asgard = new Pais(
                706,
                "as",
                "asg",
                "Asgard"
        );

        assertEquals(706, asgard.getId());
        assertEquals("Asgard | 706 | AS | ASG | 0", asgard.toString());

        asgard.addPaisIdMaior700();
        brasil.addPaisIdMaior700();

        assertEquals("Asgard | 706 | AS | ASG | 1", asgard.toString());
        assertEquals("Brasil | 701 | BR | BRA | 4", brasil.toString());
    }
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - */



    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /* Testa classe Populacao */
    @Test
    public void testMetodosPopulacao() {
        Populacao populacao = new Populacao(
                100,
                1950,
                "1234",
                "123456",
                "69.690"
        );

        assertEquals(1950, populacao.getAno());
        assertEquals(100, populacao.getId());
    }
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - */



    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /* Testa classe InputInvalido */
    @Test
    public void testInputInvalidoToString() {
        InputInvalido inputInvalido = new InputInvalido("blabla.bla");

        assertEquals("blabla.bla | 0 | 0 | -1", inputInvalido.toString());

        inputInvalido.addNumLinhaLidaFicheiro();
        inputInvalido.addNumLinhaLidaFicheiro();
        inputInvalido.addNumLinhaLidaFicheiro();
        inputInvalido.addNumLinhaLidaFicheiro();

        inputInvalido.addLinhaNOK();

        inputInvalido.addLinhaOK();
        inputInvalido.addLinhaOK();
        inputInvalido.addLinhaOK();

        inputInvalido.addNumLinhaLidaFicheiro();

        inputInvalido.addLinhaNOK();

        assertEquals("blabla.bla | 3 | 2 | 5", inputInvalido.toString());

        InputInvalido inputInvalidoNovo = new InputInvalido("bimbom.ola");

        assertEquals("bimbom.ola | 0 | 0 | -1", inputInvalidoNovo.toString());
    }
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - */



    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /* Testa enum TipoEntidade */
    @Test
    public void testEnumValores() {
        assertNotNull(TipoEntidade.PAIS);
        assertNotNull(TipoEntidade.CIDADE);
        assertNotNull(TipoEntidade.INPUT_INVALIDO);
    }

    @Test
    public void testEnumToString() {
        assertEquals("PAIS", TipoEntidade.PAIS.toString());
        assertEquals("CIDADE", TipoEntidade.CIDADE.toString());
        assertEquals("INPUT_INVALIDO", TipoEntidade.INPUT_INVALIDO.toString());
    }
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - */
}
