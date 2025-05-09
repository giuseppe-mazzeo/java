Todas as interações com LLMs a seguir foram feitas com o ChatGPT
----------------------------------------------------------------------------

|>>>Prompt: Como faço para o conteudo de um ArrayList do tipo String sair as [ e ] quando faço print?
|>>>Resposta:
|Opção 1: Usar String.join() (modo simples e limpo):
|
|   ArrayList<String> nomes = new ArrayList<>();
|   nomes.add("Ana");
|   nomes.add("Bruno");
|   nomes.add("Carlos");
|
|   System.out.println(String.join(", ", nomes));
|
|   Saída: Ana, Bruno, Carlos
|   Essa é a forma mais recomendada quando os elementos são String.
|
|Opção 2: Usar for ou forEach manualmente
|   for (String nome : nomes) {
|       System.out.print(nome + " ");
|   }
|
|Opção 3: Remover os colchetes manualmente (menos elegante)
|    System.out.println(nomes.toString().replace("[", "").replace("]", ""));
|    Funciona, mas não é a forma mais recomendada, pois depende da forma como toString() imprime os elementos.
|
|>>>Análise crítica:
|Optei pela primeira opção pela praticidade. Além de retirar os parênteses retos, consigo adicionar as vírgulas para separar elemento por elemento mais facilmente.




|>>>Prompt: Existe uma forma para separar string com base num caracter que gaste menos tempo e que seja mais prática do que fazer split?
|>>>Resposta:
|Excelente pergunta! A função split() em Java é prática, mas não é a mais performática — principalmente quando você a usa com um caractere simples, como vírgula, dois-pontos ou ponto e vírgula.
|
|Por que o split() pode ser mais lento?
|Porque split(String regex) usa expressões regulares (regex) internamente, o que adiciona uma sobrecarga desnecessária se tudo o que você quer é separar por um caractere simples.
|
|Alternativa mais rápida e leve ao split():
|
|Opção 1: Use StringTokenizer (mais leve e antigo):
|
|import java.util.StringTokenizer;
|String texto = "Ana;Bruno;Carlos";
|StringTokenizer tokenizer = new StringTokenizer(texto, ";");
|while (tokenizer.hasMoreTokens()) {
|    System.out.println(tokenizer.nextToken());
|}
|
|Prós: Mais leve que split(), ideal para casos simples.
|Contras: Menos flexível e mais limitado.
|
|Opção 2: Use indexOf() + substring() manualmente (mais controle, mais performático):
|
|String texto = "Ana;Bruno;Carlos";
|int inicio = 0;
|int fim;
|while ((fim = texto.indexOf(';', inicio)) != -1) {
|    System.out.println(texto.substring(inicio, fim));
|    inicio = fim + 1;
|}
|// Último elemento
|System.out.println(texto.substring(inicio));
|Prós: Mais rápido e não usa regex.
|Contras: Código mais verboso (mas ótimo para grandes volumes de dados).
|
|Opção 3: Java 8+ (rápido e prático, mas ainda com regex):
|
|Arrays.stream(texto.split(";")).forEach(System.out::println);
|Ainda usa regex internamente, então não é mais rápido — só mais elegante.
|
|Conclusão:
|Método	                Velocidade	    Facilidade	    Usa regex?
|split()	            Média	        Alta	        Sim
|StringTokenizer	    Rápida	        Média	        Não
|indexOf + substring	Mais rápida	    Baixa           Não
|
|>>>Análise crítica:
|Como estou preocupado com um desempenho mais prática e que seja a mais rápida, optei pela escolha do indexOf() com substring(). Ainda mais, essa escolha é bem simples de programar.



|>>>Prompt:
|>>>Resposta:
|
|
|
|>>>Análise crítica: