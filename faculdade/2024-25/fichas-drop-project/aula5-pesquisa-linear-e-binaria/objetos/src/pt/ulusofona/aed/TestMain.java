package pt.ulusofona.aed;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.channels.AsynchronousServerSocketChannel;

public class TestMain {
    Pessoa[] pessoas = new Pessoa[] {
            new Pessoa("João", "Pereira", 1),
            new Pessoa("Pudim", "da Avó", 2),
            new Pessoa("Cato", "Preto", 3),
            new Pessoa("Asa", "de Morcego", 9),
            new Pessoa("Garrafa", "Laranja", 11),
    };


    /* = = = = = = = = = = = = = = = = = = = = = = = = =

    TESTE DA FUNÇÃO  f01

      = = = = = = = = = = = = = = = = = = = = = = = = = */
    @Test
    public void f01_nrBIProcurado_nao_existe() {
        int nrBIProcurado = 100;

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f01(pessoas, nrBIProcurado);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void f01_nrBIProcurado_existe() {
        int nrBIProcurado = 9;

        boolean resultadoEsperado = true;
        boolean resultadoAtual = Main.f01(pessoas, nrBIProcurado);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void f01_array_null() {
        Pessoa[] pessoasNull = null;
        int nrBIProcurado = 100;

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f01(pessoasNull, nrBIProcurado);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void f01_array_vazio() {
        Pessoa[] pessoasVazio = new Pessoa[] {};
        int nrBIProcurado = 100;

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f01(pessoasVazio, nrBIProcurado);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }



    /* = = = = = = = = = = = = = = = = = = = = = = = = =

    TESTE DA FUNÇÃO  f02

    = = = = = = = = = = = = = = = = = = = = = = = = = */
    @Test
    public void f02_nrBIProcurado_nao_existe() {
        int nrBIProcurado = 100;

        Pessoa resultadoEsperado = null;
        Pessoa resultadoAtual = Main.f02(pessoas, nrBIProcurado);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void f02_nrBIProcurado_existe() {
        int nrBIProcurado = 9;

        Pessoa resultadoEsperado = new Pessoa("Asa", "de Morcego", 9);
        Pessoa resultadoAtual = Main.f02(pessoas, nrBIProcurado);

        Assertions.assertEquals(resultadoEsperado.nrBI, resultadoAtual.nrBI);
    }


    @Test
    public void f02_array_null() {
        Pessoa[] pessoasNull = null;
        int nrBIProcurado = 100;

        Pessoa resultadoEsperado = null;
        Pessoa resultadoAtual = Main.f02(pessoasNull, nrBIProcurado);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void f02_array_vazio() {
        Pessoa[] pessoasVazio = new Pessoa[] {};
        int nrBIProcurado = 100;

        Pessoa resultadoEsperado = null;
        Pessoa resultadoAtual = Main.f02(pessoasVazio, nrBIProcurado);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }



    /* = = = = = = = = = = = = = = = = = = = = = = = = =

    TESTE DA FUNÇÃO  f03

     = = = = = = = = = = = = = = = = = = = = = = = = = */
    @Test
    public void f03_nrBIProcurado_nao_existe() {
        int nrBIProcurado = 100;
        int posInicio = 0;
        int posFim = 4;

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f03(pessoas, nrBIProcurado, posInicio, posFim);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void f03_nrBIProcurado_existe() {
        int nrBIProcurado = 9;
        int posInicio = 0;
        int posFim = 4;

        boolean resultadoEsperado = true;
        boolean resultadoAtual = Main.f03(pessoas, nrBIProcurado, posInicio, posFim);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void f03_nrBIProcurado_fora_do_limite() {
        int nrBIProcurado = 1;
        int posInicio = 1;
        int posFim = 4;

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f03(pessoas, nrBIProcurado, posInicio, posFim);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void f03_posInicio_maior_do_que_posFim() {
        int nrBIProcurado = 0;
        int posInicio = 5;
        int posFim = 4;

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f03(pessoas, nrBIProcurado, posInicio, posFim);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void f03_posInicio_maior_do_que_array() {
        int nrBIProcurado = 0;
        int posInicio = 10;
        int posFim = 4;

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f03(pessoas, nrBIProcurado, posInicio, posFim);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void f03_posFim_maior_do_que_array() {
        int nrBIProcurado = 0;
        int posInicio = 0;
        int posFim = 10;

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f03(pessoas, nrBIProcurado, posInicio, posFim);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void f03_array_null() {
        Pessoa[] pessoasNull = null;
        int nrBIProcurado = 100;
        int posInicio = 0;
        int posFim = 4;

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f03(pessoasNull, nrBIProcurado, posInicio, posFim);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void f03_array_vazio() {
        Pessoa[] pessoasVazio = new Pessoa[] {};
        int nrBIProcurado = 100;
        int posInicio = 0;
        int posFim = 4;

        boolean resultadoEsperado = false;
        boolean resultadoAtual = Main.f03(pessoasVazio, nrBIProcurado, posInicio, posFim);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }



    /* = = = = = = = = = = = = = = = = = = = = = = = = =

    TESTE DA FUNÇÃO  f02

    = = = = = = = = = = = = = = = = = = = = = = = = = */
    @Test
    public void f04_nrBIProcurado_nao_existe() {
        int nrBIProcurado = 100;

        Pessoa resultadoEsperado = null;
        Pessoa resultadoAtual = Main.f04(pessoas, nrBIProcurado);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void f04_nrBIProcurado_existe() {
        int nrBIProcurado = 9;

        Pessoa resultadoEsperado = new Pessoa("Asa", "de Morcego", 9);
        Pessoa resultadoAtual = Main.f04(pessoas, nrBIProcurado);

        Assertions.assertEquals(resultadoEsperado.nrBI, resultadoAtual.nrBI);
    }


    @Test
    public void f04_array_null() {
        Pessoa[] pessoasNull = null;
        int nrBIProcurado = 100;

        Pessoa resultadoEsperado = null;
        Pessoa resultadoAtual = Main.f04(pessoasNull, nrBIProcurado);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }


    @Test
    public void f04_array_vazio() {
        Pessoa[] pessoasVazio = new Pessoa[] {};
        int nrBIProcurado = 100;

        Pessoa resultadoEsperado = null;
        Pessoa resultadoAtual = Main.f04(pessoasVazio, nrBIProcurado);

        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }
}
