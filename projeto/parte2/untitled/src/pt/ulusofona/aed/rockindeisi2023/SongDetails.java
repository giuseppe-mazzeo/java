package pt.ulusofona.aed.rockindeisi2023;

public class SongDetails {
    String id;
    int duracao;
    int letraExplicita;
    int popularidade;
    double dancabilidade;
    double vivacidade;
    double volumeMedio;

        public SongDetails(String id, int duracao, int letraExplicita, int popularidade, double dancabilidade, double vivacidade, double volumeMedio) {
            this.id = id;
            this.duracao = duracao;
            this.letraExplicita = letraExplicita;
            this.popularidade = popularidade;
            this.dancabilidade = dancabilidade;
            this.vivacidade = vivacidade;
            this.volumeMedio = volumeMedio;

        }




    @Override
    public String toString() {


        return "";
    }


}
