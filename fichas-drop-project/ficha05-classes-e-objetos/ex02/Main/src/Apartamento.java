public class Apartamento {
    String nomeDaRua;
    int nrPorta;
    String localidade;
    String pais;

    Apartamento() {}

    public Apartamento(String nomeDaRua, int nrPorta, String localidade, String pais) {
        this.nomeDaRua = nomeDaRua;
        this.nrPorta = nrPorta;
        this.localidade = localidade;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return nomeDaRua + " "+ nrPorta + ", " + localidade + ", " + pais;
    }
}
