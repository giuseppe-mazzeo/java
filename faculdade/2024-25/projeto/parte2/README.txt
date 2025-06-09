Todas as interações com LLMs a seguir foram feitas com o ChatGPT
----------------------------------------------------------------------------
URL do vídeo: https://youtu.be/G-7AvL8vhU4
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





|>>>Prompt: No hashmap, como eu percorro de um elemento para o outro?
|>>>Resposta:
|Ótima pergunta! Para percorrer (iterar) os elementos de um HashMap<K, V> em Java, você pode usar várias formas, dependendo do que você quer acessar: as chaves, os valores ou os pares chave–valor.
|
|Exemplo básico de HashMap<String, Integer>:
|
|Map<String, Integer> mapa = new HashMap<>();
|mapa.put("banana", 2);
|mapa.put("maçã", 5);
|mapa.put("laranja", 3);
|
|Opção 1: Percorrer todos os pares (chave e valor):
|
|for (Map.Entry<String, Integer> entrada : mapa.entrySet()) {
|    System.out.println("Fruta: " + entrada.getKey() + ", Quantidade: " + entrada.getValue());
|}
|
|Opção 2: Percorrer só as chaves:
|
|for (String chave : mapa.keySet()) {
|    System.out.println("Chave: " + chave);
|}
|
|Opção 3: Percorrer só os valores:
|
|for (Integer valor : mapa.values()) {
|    System.out.println("Valor: " + valor);
|}
|
|>>>Análise crítica: Como o meu objetivo é pegar chave e valor ao mesmo tempo, optei a opção 1.





|>>>Prompt: Olá, qual é a melhor opção para calcular uma porcentagem entre dois valores?
|>>>Resposta:
|Olá! A melhor forma de calcular uma porcentagem entre dois valores depende do que você quer descobrir. Aqui vão as opções mais comuns e corretas com exemplos claros:
|
|1. Porcentagem que um valor representa do total
|Situação:
|Você quer saber quantos % um valor A representa de um total T.
|
|Fórmula:
|porcentagem = (valor * 100.0) / total;
|
|Exemplo:
|int homens = 40;
|int total = 100;
|double porcentagemHomens = (homens * 100.0) / total; // 40.0%
|
|2. Porcentagem de diferença entre dois valores
|Situação:
|Você quer saber quanto um valor é maior ou menor que outro em porcentagem.
|
|Fórmula:
|porcentagem = ((valor1 - valor2) * 100.0) / valor2;
|
|Ou, se quiser a diferença percentual absoluta:
|porcentagem = (Math.abs(valor1 - valor2) * 100.0) / Math.max(valor1, valor2);
|
|Exemplo:
|int homens = 120;
|int mulheres = 100;
|double diferenca = ((homens - mulheres) * 100.0) / mulheres; // 20.0%
|
|Dica importante:
|Use 100.0 e não 100 para garantir divisão com casas decimais (double).
|
|Se você usar apenas inteiros (int / int), a divisão truncará os decimais, o que dá resultado incorreto em muitos casos.
|
|>>>Análise crítica: Irei utilizar a fórmula matemática: double diferencaPercentual = Math.abs(numMasculino - numFeminino) * 100.0 / Math.max(numMasculino, numFeminino);





|>>>Prompt:
|O que esse erro significa?
|
|java.lang.ClassCastException: class java.util.HashMap$Values cannot be cast to class java.util.HashSet (java.util.HashMap$Values and java.util.HashSet are in module java.base of loader 'bootstrap')
|>>>Resposta:
|Esse erro:
|
|java.lang.ClassCastException: class java.util.HashMap$Values cannot be cast to class java.util.HashSet
|significa que você tentou forçar (cast) um objeto do tipo HashMap.Values para o tipo HashSet, o que não é permitido, porque eles são tipos completamente diferentes, mesmo que ambos estejam relacionados a coleções.
|
|Causa comum:
|Você provavelmente tentou fazer algo assim:
|
|HashMap<String, Integer> mapa = new HashMap<>();
|// ...
|HashSet<Integer> conjunto = (HashSet<Integer>) mapa.values(); // ERRO AQUI
|
|mapa.values() retorna um objeto do tipo Collection<V>, mais precisamente uma instância de HashMap.Values, não um HashSet.
|
|Não é possível fazer cast direto entre esses tipos.
|
|Como corrigir:
|Se você quer transformar os valores do HashMap em um HashSet, faça assim:
|
|HashSet<Integer> conjunto = new HashSet<>(mapa.values());
|Isso cria um HashSet contendo todos os valores do mapa.
|
|Exemplo completo:
|HashMap<String, Integer> mapa = new HashMap<>();
|mapa.put("a", 1);
|mapa.put("b", 2);
|mapa.put("c", 3);
|
|// Correto:
|HashSet<Integer> valoresUnicos = new HashSet<>(mapa.values());
|
|System.out.println(valoresUnicos); // [1, 2, 3]
|
|>>>Análise crítica: Deixarei de usar cast e fazer como o ChatGPT me recomendou, fazer um novo HashSet (new Hashset<>()).





|>>>Prompt:
|Como faço para deletar todos os elementos de um array e deixar apenas alguns guardados?
|
|>>>Resposta:
|Para deletar todos os elementos de um ArrayList e manter apenas alguns específicos, a forma mais simples é usar retainAll() ou criar um novo ArrayList com os elementos que deseja manter.
|
|Opção 1: Usar retainAll() (mantém apenas os elementos desejados)
|
|ArrayList<String> nomes = new ArrayList<>(List.of("João", "Ana", "Carlos", "Maria"));
|
|// Suponha que você quer manter apenas "João" e "Maria"
|nomes.retainAll(List.of("João", "Maria"));
|
|System.out.println(nomes);  // [João, Maria]
|
|Opção 2: Criar um novo ArrayList com os elementos desejados
|
|ArrayList<String> nomes = new ArrayList<>(List.of("João", "Ana", "Carlos", "Maria"));
|
|// Mantém os dois primeiros
|ArrayList<String> nomesFiltrados = new ArrayList<>(nomes.subList(0, 2));
|
|// Substitui a lista original pelos elementos filtrados
|nomes = nomesFiltrados;
|
|System.out.println(nomes);  // [João, Ana]
|
|Opção 3: Se quiser remover todos menos os 10 primeiros
|
|if (nomes.size() > 10) {
|    nomes.subList(10, nomes.size()).clear();  // remove do 11º em diante
|}
|
|>>>Análise crítica: Optei pela 3ª opção porque me parece mais prática e fácil de aplicar.
----------------------------------------------------------------------------
-
-
-
-
-
----------------------------------------------------------------------------
COMANDO CRIATIVO
----------------------------------------------------------------------------
Nome: QUIZZ_TIME
Descrição:
A ideia é utilizar, de forma dinâmica, os comandos desenvolvidos no Projeto 2, estruturando-os como um quiz, semelhante ao programa "Show do Milhão", exibido na televisão.
O programa exibirá uma aba explicativa previamente, descrevendo como cada comando funciona.
Exemplo:
> QUIZZ_TIME
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
                   Bem-vindo ao QUIZZ TIME!

    Prepare-se para desafiar seus conhecimentos sobre o universo
    do entretenimento: filmes, atores, diretores e muito mais!

    Convide seus amigos e entre na disputa para descobrir
    quem realmente é o mestre da sétima arte.

    Dica: Use ficheiros grandes para ser mais divertido.

    Comandos disponíveis:
    QUIZZ_TIME history
    QUIZZ_TIME play
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
----------------------------------------------------------------------------
Nome: QUIZZ_TIME history
Descrição:
Nesta parte do programa, é exibido um placar com todos os jogadores que participaram do quiz.
Caso nenhum jogador tenha iniciado o jogo, será exibida uma mensagem informando que não há registros no histórico.
São apresentados os seguintes dados:
NOME: nome do jogador que realizou a partida;
PERGUNTAS CERTAS: número de perguntas respondidas corretamente, variando de 0 a 10;
TEMPO: tempo, em segundos, que o jogador levou para concluir o quiz;
TENTATIVA: indica a vez em que o jogador participou do quiz. Se for a primeira vez, a tentativa é 1. Caso jogue novamente com o mesmo nome, será a tentativa 2, e assim por diante.
Os dados são exibidos de forma ordenada, priorizando o número de acertos (em ordem decrescente). Em caso de empate na pontuação, o critério de desempate será o menor tempo. Se ainda assim houver empate, a ordem entre esses jogadores será irrelevante.
Exemplo:
> QUIZZ_TIME history
Nenhum histórico de jogador
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     NOME    PERGUNTAS CERTAS    TEMPO (seg.)    TENTATIVA
     ----         ---                ---            ---
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Outro exemplo:
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     NOME    PERGUNTAS CERTAS    TEMPO (seg.)    TENTATIVA
   jogador1          6               38               1
   jogador2          4               26               1
   jogador4          2               31               1
   jogador3          2               45               1
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
----------------------------------------------------------------------------
Nome: QUIZZ_TIME play
Descrição:
É neste momento que o comando realiza sua principal funcionalidade.
O programa possui um total de 18 perguntas cadastradas, das quais apenas 10 são selecionadas aleatoriamente para compor o quiz de cada jogador.
Para iniciar um quiz, é necessário registrar um jogador. Cada jogador é identificado por um nome (limitado a 10 caracteres). Caso um nome já utilizado anteriormente seja inserido novamente, o programa reconhece o retorno do jogador e exibe uma mensagem de boas-vindas.
Cada pergunta possui quatro alternativas de resposta — embora algumas possam ter apenas três opções.
Ao acertar uma pergunta, o jogador recebe um ponto. Em caso de erro, nenhum ponto é atribuído.
Exemplo:
QUIZZ_TIME play

Olá jogador, poderia dizer-me o seu nome?
> jogador

1) Quantos filmes foram realizados no mês 11 do ano 1996?
1- 41		2- 59
3- 2		4- 8
> 1
Resposta errada

2) Qual o filme que teve o maior orçamento?
1- Lover's Knot		2- Dirty Grandpa
3- Pirates of the Caribbean: On Stranger Tides		4- Honolulu
> 2
Resposta errada

3) Quantos filme o diretor King Vidor já fez?
1- 30		2- 22
3- 7		4- 8
> 1
Resposta certa

4) Qual desses filmes teve a maior votação?
1- Demidovy		2- Greetings From Fukushima
3- In China They Eat Dogs		4- Nevalyashka
> 2
Resposta errada

5) Qual desses filmes pertence ao gênero Mystery?
1- Octane		2- The Binding
3- Japanese Girls at the Harbor		4- Zappa
> 2
Resposta errada

6) Calvin Poon Yuen-Leung já contracenou alguma vez com Wanda De Jesus?
1- Sim, no mesmo filme		2- Não, mas eles têm um ator em que já trabalharam em comum
3- Nenhuma das anteriores
> 3
Resposta certa

7) Quantos atores participaram em filmes lançados nos anos 1905 e 1912?
1- 3		2- 1
3- 0		4- 9
> 1
Resposta errada

8) Quantos filmes foram realizados entre os anos 1986 e 2012, e que tenha atuado entre 52 e 53 atores?
1- 1		2- 9
3- 0		4- 6
> 4
Resposta errada

9) Qual filme, lançado em 2011, o ator Matt Olsen participou?
1- Up from the Depths		2- The Oregonian
3- Bunny Drop		4- Shala
> 4
Resposta errada

10) Qual o mês com mais filmes lançados no ano 2002?
1- 2		2- 0
3- 3		4- 1
> 1
Resposta errada
Obrigado por jogar!

Outro exemplo:
Olá jogador, poderia dizer-me o seu nome?
> jogador
Olá jogador, é bom vê-lo de volta. Boa sorte!

1) Quantos filmes foram realizados entre os anos 1906 e 1918, e que tenha atuado entre 43 e 59 atores?
1- 3		2- 0
3- 1		4- 9
> 1
Resposta errada

2) Qual o filme que teve o maior orçamento?
1- Pushing Hands		2- King of the Roaring 20's: The Story of Arnold Rothstein
3- The Texas Chain Saw Massacre		4- Pirates of the Caribbean: On Stranger Tides
> 2
Resposta errada

3) Qual o mês com mais filmes lançados no ano 1983?
1- 1		2- 0
3- 2		4- 3
> 2
Resposta errada

4) O filme Mrs. Brown's Boys D'Movie é realizado por mais atores do sexo masculino ou feminino?
1- Up from the Depths		2- Bunny Drop
3- Shala		4- Into the Storm
> 3
Resposta errada

5) Qual desses filmes tem a maior duração?
1- Russendisko		2- Snow on The Blades
3- Little Hamlet		4- Black Snow
> 1
Resposta errada

6) Qual foi o filme lançado em 2011 com mais atores do gênero F?
1- Bunny Drop, com 0 atores		2- Real Steel, com 29 atores
3- Up from the Depths, com 2 atores		4- Shala, com 1 atores
> 2
Resposta certa

7) Ricky Paull Goldin já contracenou alguma vez com Margaret O'Brien?
1- Sim, no mesmo filme		2- Não, mas eles têm um ator em que já trabalharam em comum
3- Nenhuma das anteriores
> 4
Resposta errada

8) Qual desses filmes foi dirigido por Kenneth Anger?
1- Air Buddies		2- Nurse Betty
3- Lucifer Rising		4- Eraserhead
> 4
Resposta errada

9) Em que ano foi lançado o filme Lift?
1- 2006		2- 1666
3- 478		4- 805
> 2
Resposta errada

10) Quantos filme o diretor George Seaton já fez?
1- 11		2- 10
3- 4		4- 2
> 1
Resposta certa
Obrigado por jogar!