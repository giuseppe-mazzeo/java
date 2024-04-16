package pt.ulusofona.aed.deisiworldmeter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/* ======================
      Índice de testes:

   -- Classe Cidade
   -- Classe Pais
   -- Classe Populacao
   -- Classe InputInvalido
   -- Enum TipoEntidade
   -- Classe Main
   ======================
*/

public class TestXXX {
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

        brasil.temPaisFicheiroPopulacao();
        brasil.temPaisFicheiroPopulacao();
        brasil.temPaisFicheiroPopulacao();

        assertEquals(701, brasil.getId());
        assertEquals("Brasil | 701 | BR | BRA | 3", brasil.toString());

        Pais asgard = new Pais(
                706,
                "as",
                "asg",
                "Asgard"
        );

        Pais.resetPaisIdMaior700();
        assertEquals(706, asgard.getId());
        assertEquals("Asgard | 706 | AS | ASG | 0", asgard.toString());

        asgard.temPaisFicheiroPopulacao();
        brasil.temPaisFicheiroPopulacao();

        assertEquals("Asgard | 706 | AS | ASG | 2", asgard.toString());
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
