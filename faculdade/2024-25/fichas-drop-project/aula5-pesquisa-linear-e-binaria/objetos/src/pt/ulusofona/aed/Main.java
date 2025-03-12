package pt.ulusofona.aed;

import java.util.Arrays;

public class Main {

    static boolean f01(Pessoa[] pessoas, int nrBIProcurado) {
        if (pessoas == null) {
            return false;
        }

        for (Pessoa pessoaAtual : pessoas) {
            if (pessoaAtual.nrBI == nrBIProcurado) {
                return true;
            }
        }

        return false;
    }



    static Pessoa f02(Pessoa[] pessoas, int nrBIProcurado) {
        if (pessoas == null) {
            return null;
        }

        for (Pessoa pessoaAtual : pessoas) {
            if (pessoaAtual.nrBI == nrBIProcurado) {
                return pessoaAtual;
            }
        }

        return null;
    }



    static boolean f03(Pessoa[] pessoas, int nrBIProcurado, int posInicio, int posFim) {
        if (pessoas == null || posInicio > pessoas.length || posFim > pessoas.length) {
            return false;
        }

        if (posInicio > posFim) {
            return false;
        }

        int posMeio = posInicio + (posFim - posInicio) / 2;

        if (pessoas[posMeio].nrBI == nrBIProcurado) {
            return true;
        } else if (nrBIProcurado < pessoas[posMeio].nrBI) {
            return f03(pessoas, nrBIProcurado, posInicio, posMeio - 1);
        } else {
            return f03(pessoas, nrBIProcurado, posMeio + 1, posFim);
        }
    }



    static Pessoa f04(Pessoa[] pessoas, int nrBIProcurado) {
        if (pessoas == null || pessoas.length == 0) {
            return null;
        }

        if (pessoas.length == 1) {
            if (pessoas[0].nrBI == nrBIProcurado) {
                return pessoas[0];
            } else {
                return null;
            }
        }

        Pessoa[] pessoasEsq = Arrays.copyOfRange(pessoas, 0 , 1);
        Pessoa[] pessoasDir = Arrays.copyOfRange(pessoas, 1 , pessoas.length);

        return f04(pessoasEsq, nrBIProcurado) == null ? f04(pessoasDir, nrBIProcurado) : pessoas[0];
    }



    static long[] f04() {
        long[] resultadosTempo = new long[4];

        long comeco;
        long fim;

        Pessoa[] arrayPequeno = GeradorPessoasOrdenadas.criarArrayPessoas(10);
        Pessoa[] arrayGrande = GeradorPessoasOrdenadas.criarArrayPessoas(1_000_000);

        comeco = System.currentTimeMillis();
        f01(arrayGrande, 800_000);
        fim = System.currentTimeMillis();
        resultadosTempo[0] = fim - comeco;

        comeco = System.currentTimeMillis();
        f03(arrayGrande, 800_000, 0, 999_999);
        fim = System.currentTimeMillis();
        resultadosTempo[1] = fim - comeco;

        comeco = System.currentTimeMillis();
        f01(arrayPequeno, 8);
        fim = System.currentTimeMillis();
        resultadosTempo[2] = fim - comeco;

        comeco = System.currentTimeMillis();;
        f03(arrayPequeno, 8, 0, 7);
        fim = System.currentTimeMillis();;
        resultadosTempo[3] = fim - comeco;

        return resultadosTempo;
    }



    public static void main(String[] args) {
        System.out.println(Arrays.toString(f04()));
    }
}
