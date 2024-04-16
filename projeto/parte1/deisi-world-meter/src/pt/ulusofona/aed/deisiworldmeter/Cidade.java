package pt.ulusofona.aed.deisiworldmeter;

public class Cidade {
    private String  alfa2,
                    cidade,
                    regiao,
                    populacao,
                    latitude,
                    longitude;


    public Cidade(String alfa2, String cidade, String regiao, String populacao, String latitude, String longitude) {
        this.alfa2 = alfa2;
        this.cidade = cidade;
        this.regiao = regiao;
        this.populacao = populacao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return cidade + " | " + alfa2.toUpperCase() + " | " + regiao + " | " + populacao + " | (" + latitude + "," + longitude + ")";
    }
}
