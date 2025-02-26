public class Pessoa {
    String nome;
    String apelido;
    int nrBI;

    Animal[] animais;

    public Pessoa(String nome, String apelido, int nrBI, Animal[] animais) {
        this.nome = nome;
        this.apelido = apelido;
        this.nrBI = nrBI;
        this.animais = animais;
    }

    @Override
    public String toString() {
        String out = "O " + nome + " " + apelido + "(nr. BI: " + nrBI + ") é dono de um ";

        for (int i = 0, count = animais.length; i < animais.length; i++) {
            out += animais[i];

            if (count == 2) {
                out += " e de um ";
            } else if (count == 3) {
                out += ", de um ";
            }
            count--;
        }

        out += ".";

        return out;
    }
}
