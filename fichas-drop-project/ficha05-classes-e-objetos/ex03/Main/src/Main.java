import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(f07(new Pessoa[]{gerarAmigosDosAnimais()[1], gerarAmigosDosAnimais()[0]})));
    }

    static Pessoa[] gerarAmigosDosAnimais() {
        Animal animalP1 = new Animal("Bimbo", "Cão", 12345);
        Pessoa pessoa1 = new Pessoa("Victor", "Valente", 12388193, new Animal[]{animalP1});

        Animal animalP2 = new Animal("Planeta", "Cavalo", 33333);
        Animal animal2P2 = new Animal("Alfaiate", "Cão", 55555);
        Pessoa pessoa2 = new Pessoa("Rodrigo", "Correia", 12377341, new Animal[]{animalP2, animal2P2});

        Animal animalP3 = new Animal("Carapau", "Papagaio", 44155);
        Pessoa pessoa3 = new Pessoa("João", "Batalha", 12300545, new Animal[]{animalP3});

        return new Pessoa[]{pessoa1, pessoa2, pessoa3};
    }

    static boolean f02(Pessoa pessoa) {
        return pessoa.animais != null;
    }

    static boolean f03(Pessoa pessoa) {
        for (Animal animal : pessoa.animais) {
            if (animal.especie.equals("Cão")) {
                return true;
            }
        }

        return false;
    }

    static boolean f04(Pessoa pessoa, String especieAnimal) {
        for (Animal animal : pessoa.animais) {
            if (animal.especie.equals(especieAnimal)) {
                return true;
            }
        }

        return false;
    }

    static int f05(Pessoa pessoa, String especieAnimal) {
        int count = 0;

        for (Animal animal : pessoa.animais) {
            if (animal.especie.equals(especieAnimal)) {
                count++;
            }
        }

        return count;
    }

    static Pessoa f06(Pessoa pessoa1, Pessoa pessoa2) {
        if (pessoa1.animais.length == pessoa2.animais.length) {
            return null;
        }

        if (pessoa1.animais.length > pessoa2.animais.length) {
            return pessoa1;
        }

        return pessoa2;
    }

    static Animal[] f07(Pessoa[] pessoas) {
        Animal[] animals;
        int count = 0;

        for (Pessoa pessoa : pessoas) {
            if (pessoa.animais != null) {
                count += pessoa.animais.length;
            }
        }

        animals = new Animal[count];
        int i = 0;

        for (Pessoa pessoa : pessoas) {
            if (pessoa.animais != null) {
                for (int j = 0; j < pessoa.animais.length; j++) {
                    animals[i] = pessoa.animais[j];
                }
            }
        }

        return animals;
    }

    static String[] nomesDasFuncoes() {
        return new String[]{
                "temAnimais", // nome da f02()
                "temCaes", // nome da f03()
                "temAnimaisEspecie", // nome da f04()
                "contarAnimaisEspecie", // nome da f05()
                "maiorAmigoDosAnimais", // nome da f06()
                "juntarAnimais", // nome da f07()
        };
    }
}
