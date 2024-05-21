package pt.ulusofona.aed.deisiworldmeter;

public class Cidade {
    private final String  alfa2,
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

    public String getAlfa2() {
        return alfa2;
    }

    public String getCidade() {
        return cidade;
    }


    /* Esta função além de retornar a população, também retira os dois últimos caracteres da string desnecessários (p.e. "20430.0", retira os caracteres ".0") */
    public int getPopulacao() {
        if (populacao.endsWith(".0")) {
            return Integer.parseInt(populacao.substring(0,populacao.length() - 2).trim());
        } else {
            return Integer.parseInt(populacao.trim());
        }
    }

    @Override
    public String toString() {
        return cidade + " | " + alfa2.toUpperCase() + " | " + regiao + " | " + populacao + " | (" + latitude + "," + longitude + ")";
    }
}
