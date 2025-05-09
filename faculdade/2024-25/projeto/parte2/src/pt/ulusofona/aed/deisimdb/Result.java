package pt.ulusofona.aed.deisimdb;

public class Result {
    boolean success;
    String error;
    String result;



    public Result() {}



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

    public void comandoNaoEncontrouResultado() {
        success = true;
        error = null;
        result = "No results";
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
