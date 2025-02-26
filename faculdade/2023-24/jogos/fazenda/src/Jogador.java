public class Jogador {
    private int dia = 1;
    private int mes = 1;
    private int dinheiro = 0;
    private String emprego = "Desempregado";
    private int saude = 7;


    // Aumentar valor:
    public void accDia() {
        if (dia >= 30) {
            dia = 1;
            mes++;
        } else {
            dia++;
        }

        diminuirSaude(1);
    }

    public void accDinheiro(int valor) {
        dinheiro += valor;
    }

    public void accSaude(int valor) {
        saude += valor;

        if (saude > 7) {
            saude = 7;
        }
    }


    // Diminuir valor:
    public void diminuirSaude(int saudePerdida) {
        saude -= saudePerdida;
    }

    public void diminuirDinheiro(int valor) {
        dinheiro -= valor;
    }


    // Sets:
    public void setEmprego(String novoEmprego) {
        emprego = novoEmprego;
    }

    // Gets:
    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getDinheiro() {
        return dinheiro;
    }

    public String getEmprego() {
        return emprego;
    }

    public String getSaude() {
        String vida = "";

        for (int i = 0; i < 7; i++) {
            if (i < saude) {
                vida += "*";
            } else {
                vida += "-";
            }
        }
        return vida;
    }

    public int getSaudeInt() {
        return saude;
    }
}
