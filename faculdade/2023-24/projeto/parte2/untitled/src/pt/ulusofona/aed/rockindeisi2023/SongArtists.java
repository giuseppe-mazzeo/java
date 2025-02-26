package pt.ulusofona.aed.rockindeisi2023;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class SongArtists {
    String id;
    String nome;
    int numMusicas;
    ArrayList<String> tags;


    public SongArtists(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.numMusicas = 0;
        this.tags = new ArrayList<>();
    }




    @Override
    public String toString() {
        if (nome.charAt(0) <= 'D') {
            return "Artista: [" + nome + "]";
        } else {
            return "Artista: [" + nome + "] | " + numMusicas;
        }
    }


}





