package pt.ulusofona.aed;

public class Apartamento {
    String nomeDarua;
    int nrPorta;
    String localidade;
    String pais;


    public Apartamento(String nome_da_rua, int nr_porta, String localidade, String pais) {
        this.nomeDarua = nome_da_rua;
        this.nrPorta = nr_porta;
        this.localidade = localidade;
        this.pais = pais;
    }


    @Override
    public String toString() {
        return nomeDarua + " " + nrPorta + ", " + localidade + ", " + pais;
    }
}
