package pt.ulusofona.aed;

public class Main {
    static Funcionario[] meusFuncs() {
        return new Funcionario[]{
                new Funcionario("Luis Oliveira", 42, 123.45, 2000),
                new Funcionario("Pedro", 22204542, 543.21, 2020),
                new Funcionario("Joao", 5, 451.23, 2021)
        };
    }


    static Funcionario[] ordenarFuncionariosBubbleSort(Funcionario[] funcionarios) {
        boolean tudoOrdenado = false;
        int ultimoOrdenado = funcionarios.length-1;
        Funcionario temp;

        while (!tudoOrdenado) {
            tudoOrdenado = true;

            for (int i = 0; i < ultimoOrdenado; i++) {
                if (((int)funcionarios[i].salarioBase*funcionarios[i].nivel) < ((int)funcionarios[i+1].salarioBase*funcionarios[i+1].nivel)) {
                    tudoOrdenado = false;

                    temp = funcionarios[i];
                    funcionarios[i] = funcionarios[i+1];
                    funcionarios[i+1] = temp;
                }
            }
            ultimoOrdenado--;
        }

        return funcionarios;
    }



    public static void main(String[] args) {
        Funcionario[] a = meusFuncs();

        System.out.println("a0 - " + a[0].nome + " " + a[0].salarioBase * a[0].nivel);
        System.out.println("a2 - " + a[2].nome + " " + a[2].salarioBase * a[2].nivel);
        System.out.println("a1 - " + a[1].nome + " " + a[1].salarioBase * a[1].nivel);

        System.out.println("\n");

        for (Funcionario b : a) {
            System.out.println(b);
        }

        System.out.println("\n");

        Funcionario[] c = ordenarFuncionariosBubbleSort(a);
        for (Funcionario b : c) {
            System.out.println(b);
        }
    }
}
