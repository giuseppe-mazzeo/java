package pt.ulusofona.aed.deisimdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Result {
    boolean success;
    String error;
    String result;



    public Result() {}



    public void verificarComando(ArrayList<String> conteudo) {
        if (conteudo.isEmpty()) {
            comandoNaoEncontrouResultado();
        } else {
            comandoCorreto(conteudo);
        }
    }



    public void comandoInvalido() {
        success = false;
        error = "Comando invalido";
        result = null;
    }

    public void comandoCorreto(String resultado) {
        success = true;
        error = null;
        this.result = resultado;
    }

    public void comandoCorreto(int resultado) {
        comandoCorreto(resultado + "");
    }

    public void comandoCorreto(ArrayList<String> resultado) {
        comandoCorreto(String.join("\n", resultado));
    }

    public void comandoNaoEncontrouResultado() {
        success = true;
        error = null;
        result = "No results";
    }

    public void comandoInsercaoCorreto() {
        success = true;
        error = null;
        result = "OK";
    }

    public void comandoInsercaoInvalido() {
        success = false;
        error = null;
        result = "Erro";
    }


    public boolean getSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public String getResult() {
        return result;
    }



    @Override
    public String toString() {
        return "Sucesso: " + success + "\nError: " + error + "\nResultado: " + result;
    }
}
