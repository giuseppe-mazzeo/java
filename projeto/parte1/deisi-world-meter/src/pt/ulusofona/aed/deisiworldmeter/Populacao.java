package pt.ulusofona.aed.deisiworldmeter;

public class Populacao {
    private int id,
                ano;
    private String  popMasculina,
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
}
