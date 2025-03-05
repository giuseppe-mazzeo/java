package pt.ulusofona.aed;

import java.util.Arrays;


public class Main {
    static Pessoa[] gerarAmigosDosAnimais() {
        Animal cao1 = new Animal("Bimbo", "Cão", 12345);
        Animal cavalo = new Animal("Planeta", "Cavalo", 33333);
        Animal cao2 = new Animal("Alfaiate", "Cão", 55555);
        Animal papagaio = new Animal("Carapau", "Papagaio", 44155);

        Pessoa victor = new Pessoa("Victor", "Valente", 12388193, new Animal[] {cao1});
        Pessoa rodrigo = new Pessoa("Rodrigo", "Correia", 12377341, new Animal[] {cavalo, cao2});
        Pessoa joao = new Pessoa("João", "Batalha", 12300545, new Animal[] {papagaio});

        return new Pessoa[] {victor, rodrigo, joao};
    }



    static boolean f02(Pessoa pessoa) {
        if (pessoa.animais == null) {
            return false;
        }

        for (Animal animal : pessoa.animais) {
            if (animal != null) {
                return true;
            }
        }

        return false;
    }



    static boolean f03(Pessoa pessoa) {
        if (pessoa.animais == null) {
            return false;
        }

        for (Animal animal : pessoa.animais) {
            if (animal.especie.equals("Cão")) {
                return true;
            }
        }

        return false;
    }



    static boolean f04(Pessoa pessoa, String especie) {
        if (pessoa.animais == null) {
            return false;
        }

        for (Animal animal : pessoa.animais) {
            if (animal.especie.equals(especie)) {
                return true;
            }
        }

        return false;
    }



    static int f05(Pessoa pessoa, String especie) {
        if (pessoa.animais == null) {
            return 0;
        }

        int contador = 0;

        for (Animal animal : pessoa.animais) {
            if (animal.especie.equals(especie)) {
                contador++;
            }
        }

        return contador;
    }



    static Pessoa f06(Pessoa pessoaA, Pessoa pessoaB) {
        if ((pessoaA.animais == null || pessoaA.animais.length == 0) &&
            (pessoaB.animais == null || pessoaB.animais.length == 0))
        {
            return null;
        }

        if (pessoaA.animais == null || pessoaA.animais.length == 0) {
            return pessoaB;
        }
        if (pessoaB.animais == null || pessoaB.animais.length == 0) {
            return pessoaA;
        }

        int contAnimaisA = 0;
        int contAnimaisB = 0;

        for (Animal animal : pessoaA.animais) {
            if (animal != null) {
                contAnimaisA++;
            }
        }

        for (Animal animal : pessoaB.animais) {
            if (animal != null) {
                contAnimaisB++;
            }
        }

        if (contAnimaisA == contAnimaisB) {
            return null;
        } else if (contAnimaisA > contAnimaisB) {
            return pessoaA;
        } else {
            return pessoaB;
        }
    }



    //Função sem o uso do ArrayList<Animal>
    static Animal[] f07(Pessoa[] pessoas) {
        if (pessoas == null) {
            return null;
        }

        int contador = 0;
        for (Pessoa pessoa : pessoas) {
            if (pessoa.animais != null) {
                for (Animal animal : pessoa.animais) {
                    if (animal != null) {
                        contador++;
                    }
                }
            }
        }

        Animal[] animais = new Animal[contador];

        for (int posicaoP = 0, posicaoA = 0; posicaoP < pessoas.length; posicaoP++) {
            if (pessoas[posicaoP].animais != null) {
                for (Animal animal : pessoas[posicaoP].animais) {
                    if (animal != null) {
                        animais[posicaoA] = animal;
                        posicaoA++;
                    }
                }
            }
        }

        return animais;
    }



    static String[] nomesDasFuncoes() {
        return new String[] {
                "temAnimais",
                "temCaes",
                "temAnimaisEspecie",
                "contarAnimaisEspecie",
                "maiorAmigoDosAnimais",
                "juntarAnimais"
        };
    }



    public static void main(String[] args) {
        Animal cao1 = new Animal("Bimbo", "Cão", 12345);
        Animal cavalo = new Animal("Planeta", "Cavalo", 33333);
        Animal cao2 = new Animal("Alfaiate", "Cão", 55555);
        Animal papagaio = new Animal("Carapau", "Papagaio", 44155);
        Animal fantasma = new Animal();

        Pessoa victor = new Pessoa("Victor", "Valente", 12388193, new Animal[] {});
        Pessoa rodrigo = new Pessoa("Rodrigo", "Correia", 12377341, new Animal[] {null, cao2, null, cao1});
        System.out.println(rodrigo.toString());

        //System.out.println(f06(victor, rodrigo));
        //System.out.println(Arrays.toString(f07(new Pessoa[] {victor, rodrigo})));
    }
}
