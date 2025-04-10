package pt.ulusofona.aed.deisimdb.classes_testes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.aed.deisimdb.classes_importantes.Ator;

public class TestAtor {
    Ator ator = new Ator(1,"Joao", "M", 1234);
    Ator atriz = new Ator(2,"Joana", "F", 1234);

    @Test
    public void VerificarFuncoesGetters() {
        if (ator.getActorId() != 1 && atriz.getActorId() != 2) {
            Assertions.fail("\n\nErro ao testar getActorId()");
        }

        if (!ator.getActorName().equals("Joao") && !atriz.getActorName().equals("Joana")) {
            Assertions.fail("\n\nErro ao testar getActorName()");
        }

        if (!ator.getActorGender().equals("Masculino") && !atriz.getActorGender().equals("Feminino")) {
            Assertions.fail("\n\nErro ao testar getActorGender()");
        }

        if (ator.getMovieId() != 1234 && atriz.getMovieId() != 1234) {
            Assertions.fail("\n\nErro ao testar getMovieId()");
        }
    }


    @Test
    public void VerificarToString() {
        if (!ator.toString().equals("1 | Joao | Masculino | 1234") &&
                !atriz.toString().equals("2 | Joana | Femino | 1234")) {
            Assertions.fail("\n\nErro ao testar toString()");
        }
    }
}
