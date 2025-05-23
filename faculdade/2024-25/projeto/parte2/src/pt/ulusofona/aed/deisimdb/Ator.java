package pt.ulusofona.aed.deisimdb;

public class Ator {
    private final int actorId;
    private final String actorFullName;
    private final String actorGender;
    private final int movieId;



    public Ator(int actorId, String actorName, String actorGender, int movieId) {
        this.actorId = actorId;
        this.actorFullName = actorName;
        this.actorGender = (actorGender.equals("M")) ? "Masculino" : "Feminino";
        this.movieId = movieId;
    }



    public int getActorId() {
        return actorId;
    }

    public String getActorFullName() {
        return actorFullName;
    }

    public String getActorGender() {
        return actorGender;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getActorFirstName() {
        return actorFullName.split(" ")[0];
    }



    @Override
    public String toString() {
        return actorId + " | " + actorFullName + " | " + actorGender + " | " + movieId;
    }
}
