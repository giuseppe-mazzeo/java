package pt.ulusofona.aed;

import java.util.Random;

class GeradorDePessoas {

    static String stringAleatoria(String[] hipoteses) {
        Random gerador = new Random();
        int indice = gerador.nextInt(hipoteses.length);
        return hipoteses[indice];
    }

    static String nomeAleatorio() {
        String[] nomes = new String[]{"Diogo", "Bruno", "Pedro", "Rui", "João", "Ivo", "Ruy", "Victor", "Luís", "Marta", "Filipa"};
        return stringAleatoria(nomes);
    }

    static String apelidoAleatorio() {
        String[] nomes = new String[]{"Tavares", "Leite", "Joia", "Alves", "Almeida", "Valente", "Sebastião", "Oliveira"};
        return stringAleatoria(nomes);
    }

    static String paisAleatorio() {
        String[] paises = new String[]{"Portugal", "Canadá", "Espanha", "Alemanha", "Grécia", "Itália", "Brasil"};
        return stringAleatoria(paises);
    }

    static Pessoa gerarPessoa(int i) {
        int nrBi = 10000 + i;
        Pessoa p = new Pessoa(nomeAleatorio(), apelidoAleatorio(), nrBi, paisAleatorio());
        return p;
    }

    static Pessoa[] desordenarArray(Pessoa[] pessoas) {
        Random r = new Random();
        for (int i = 0; i < pessoas.length; i++) {
            // deixar alguns no sítio
            if (r.nextInt(1) == 0) {
                int newPos = r.nextInt(pessoas.length);
                Pessoa temp = pessoas[i];
                pessoas[i] = pessoas[newPos];
                pessoas[newPos] = temp;
            }
        }
        return pessoas;
    }

    static Pessoa[] criarArrayPessoas(int nrPessoas) {
        Pessoa[] resultado = new Pessoa[nrPessoas];
        int i = 0;
        while (i < nrPessoas) {
            Pessoa p = gerarPessoa(i);
            //System.out.println(p.nome + " " + p.apelido + " " + p.paisMorada);
            resultado[i] = p;
            i++;
        }
        return desordenarArray(resultado);
    }

}