package pt.ulusofona.aed.deisiworldmeter;

public class Pais {
    private int id;
    private String  alfa2,
            alfa3,
            nome;
    private static int paisIdMaior700;


    public Pais(int id, String alfa2, String alfa3, String nome) {
        this.id = id;
        this.alfa2 = alfa2;
        this.alfa3 = alfa3;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void addPaisIdMaior700() {
        paisIdMaior700++;
    }

    public static void setPaisIdMaior700(int atual) {
        paisIdMaior700 = atual;
    }

    @Override
    public String toString() {
        if (id > 700) {
            return nome + " | " + id + " | " + alfa2.toUpperCase() + " | " + alfa3.toUpperCase() + " | " + paisIdMaior700;
        }

        return nome + " | " + id + " | " + alfa2.toUpperCase() + " | " + alfa3.toUpperCase();
    }
}
