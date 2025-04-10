package pt.ulusofona.aed.deisimdb.classes_importantes;

public class Ator {
    private final int actorId;
    private final String actorName;
    private final String actorGender;
    private final int movieId;



    public Ator(int actorId, String actorName, String actorGender, int movieId) {
        this.actorId = actorId;
        this.actorName = actorName;
        this.actorGender = (actorGender.equals("M")) ? "Masculino" : "Feminino";
        this.movieId = movieId;
    }



    public int getActorId() {
        return actorId;
    }

    public String getActorName() {
        return actorName;
    }

    public String getActorGender() {
        return actorGender;
    }

    public int getMovieId() {
        return movieId;
    }



    @Override
    public String toString() {
        return actorId + " | " + actorName + " | " + actorGender + " | " + movieId;
    }
}
