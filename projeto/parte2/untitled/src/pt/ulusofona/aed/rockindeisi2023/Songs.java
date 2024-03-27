package pt.ulusofona.aed.rockindeisi2023;

import java.util.ArrayList;
import java.util.Calendar;

public class Songs {
    String id;
    String nome;
    int ano;
    SongDetails details;
    ArrayList<SongArtists> artists;

    public Songs(String id, String nome, int ano) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.artists = new ArrayList<>();
    }



    @Override
    public String toString() {
        String texto = id + " | " + nome + " | " + ano;
        if (details != null && ano >= 1995) {
            long segundos = details.duracao / 1000;
            long minutos = segundos / 60;
            long segundosR = segundos % 60;
            String duracaoFormatada = String.format("%d:%02d", minutos, segundosR);
            texto += " | " + duracaoFormatada + " | " + details.popularidade;
            if (artists != null && ano >= 2000) {

                texto += " | " + artists.size();

            }

        }
        return texto;
    }

}

