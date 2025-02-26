package pt.ulusofona.aed.deisiworldmeter;

public class Populacao {
    private final int id,
                      ano;
    private final String popMasculina,
                         popFeminina,
                         densidade;


    public Populacao(int id, int ano, String popMasculina, String popFeminina, String densidade) {
        this.id = id;
        this.ano = ano;
        this.popMasculina = popMasculina;
        this.popFeminina = popFeminina;
        this.densidade = densidade;
    }

    public int getId() {
        return id;
    }

    public int getAno() {
        return ano;
    }

    public int getPopMasculina() {
        return Integer.parseInt(popMasculina.trim());
    }

    public int getPopFeminina() {
        return Integer.parseInt(popFeminina.trim());
    }
}
