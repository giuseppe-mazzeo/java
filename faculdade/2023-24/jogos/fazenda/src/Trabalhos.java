public class Trabalhos {
    public String mostrarTrabalho(Jogador jogador) {
        switch (jogador.getEmprego()) {
            case "Desempregado":
                return "Capinar terreno";

            default:
                return "N/A";
        }
    }


    public void trabalhar(Jogador jogador) {
        switch (jogador.getEmprego()) {
            case "Desempregado":
                jogador.accDinheiro(10);
                break;
        }

        jogador.accDia();
    }
}
