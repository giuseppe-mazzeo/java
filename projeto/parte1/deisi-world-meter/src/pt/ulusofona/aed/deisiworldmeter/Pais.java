package pt.ulusofona.aed.deisiworldmeter;

import java.util.ArrayList;
import java.util.HashMap;

public class Pais {
    private final int id;
    private final String alfa2,
                         alfa3,
                         nome;
    private int paisIdMaior700;


    public Pais(int id, String alfa2, String alfa3, String nome) {
        this.id = id;
        this.alfa2 = alfa2;
        this.alfa3 = alfa3;
        this.nome = nome;
        this.paisIdMaior700 = 0;
    }

    public int getId() {
        return id;
    }

    public void addPaisIdMaior700() {
        paisIdMaior700++;
    }

    @Override
    public String toString() {
        if (id > 700) {
            return nome + " | " + id + " | " + alfa2.toUpperCase() + " | " + alfa3.toUpperCase() + " | " + paisIdMaior700;
        }

        return nome + " | " + id + " | " + alfa2.toUpperCase() + " | " + alfa3.toUpperCase();
    }
}
