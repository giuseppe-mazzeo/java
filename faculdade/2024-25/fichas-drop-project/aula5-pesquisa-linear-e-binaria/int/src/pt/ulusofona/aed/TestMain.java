package pt.ulusofona.aed;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMain {

    /* = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

    TESTE PARA A FUNÇÃO f01

     = = = = = = = = = = = = = = = = = = = = = = = = = = = = = */
    @Test
    public void f01_numeroProcurado_nao_pertence_array() {
        int numeroProcurado = 1;
        int[] numeros = new int[] {2, 3};

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f01(numeros, numeroProcurado);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }



    @Test
    public void f01_numeroProcurado_pertence_array() {
        int numeroProcurado = 2;
        int[] numeros = new int[] {2, 3};

        boolean resultadoEsperado = true;
        boolean resultadoAtual = Main.f01(numeros, numeroProcurado);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }



    @Test
    public void f01_array_null() {
        int numeroProcurado = 2;
        int[] numeros = null;

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f01(numeros, numeroProcurado);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }



    @Test
    public void f01_array_vazio() {
        int numeroProcurado = 2;
        int[] numeros = new int[] {};

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f01(numeros, numeroProcurado);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }



    /* = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

    TESTE PARA A FUNÇÃO f02

     = = = = = = = = = = = = = = = = = = = = = = = = = = = = = */
    @Test
    public void f02_numeroProcurado_nao_pertence_array() {
        int numeroProcurado = 2;
        int posInicio = 0;
        int posFim = 3;
        int[] numeros = new int[] {5, 7, 9, 11};

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f02(numeros, numeroProcurado, posInicio, posFim);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }



    @Test
    public void f02_numeroProcurado_pertence_array() {
        int numeroProcurado = 5;
        int posInicio = 0;
        int posFim = 3;
        int[] numeros = new int[] {5, 7, 9, 11};

        boolean resultadoEsperado = true;
        boolean resultadoAtual = Main.f02(numeros, numeroProcurado, posInicio, posFim);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }



    @Test
    public void f02_array_null() {
        int numeroProcurado = 2;
        int posInicio = 0;
        int posFim = 3;
        int[] numeros = null;

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f02(numeros, numeroProcurado, posInicio, posFim);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }



    @Test
    public void f02_array_vazio() {
        int numeroProcurado = 2;
        int posInicio = 0;
        int posFim = 3;
        int[] numeros = new int[] {};

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f02(numeros, numeroProcurado, posInicio, posFim);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }



    @Test
    public void f02_numerosProcurado_fora_do_limite() {
        int numeroProcurado = 5;
        int posInicio = 1;
        int posFim = 3;
        int[] numeros = new int[] {5, 7, 9, 11};

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f02(numeros, numeroProcurado, posInicio, posFim);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }
}
