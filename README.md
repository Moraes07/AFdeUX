# Sistema de Login - Erros e Correções

## Sobre o Projeto

Este é um sistema simples de login que se conecta a um banco de dados MySQL para validar usuários. Durante a revisão do código, foram encontrados vários problemas, e eu fiz as correções necessárias para melhorar o funcionamento e a segurança do sistema.

## Erros Encontrados e Correções

| **ID** | **Erro Encontrado**                                                                 | **O que Foi Corrigido**                                                                                  |
|--------|--------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------|
| **1**  | O código não estava documentado.                                                    | Adicionei comentários explicativos nos métodos e nas variáveis, seguindo o padrão de documentação Javadoc.|
| **2**  | Exceções não estavam sendo tratadas.                                                | Adicionei tratamento de exceções para que erros de conexão e de execução de consultas fossem corretamente informados.|
| **3**  | O `catch` estava vazio, o que dificultava o diagnóstico de problemas.                | Agora, se ocorrer um erro, uma mensagem clara é exibida no console, ajudando a identificar o problema.    |
| **4**  | Não havia validação para entradas inválidas (como login ou senha vazios).            | Implementei validação para evitar que o sistema aceite entradas inválidas, prevenindo alguns tipos de falhas.|
| **5**  | O driver JDBC estava errado (`com.mysql.Driver.Manager`).                           | Corrigi para o driver correto: `com.mysql.cj.jdbc.Driver`, que é o recomendado para versões recentes do MySQL.|
| **6**  | A lógica de conexão estava misturada com outras funcionalidades do código.           | Separei a lógica de conexão com o banco em um método específico, tornando o código mais organizado.       |
| **7**  | Objetos como `Connection`, `Statement` e `ResultSet` não estavam sendo fechados corretamente. | Agora, utilizo `try-with-resources` para garantir que esses objetos sejam fechados automaticamente.       |
| **8**  | O código podia lançar uma exceção `NullPointerException` ao usar a conexão.         | Adicionei verificações para garantir que a conexão não seja nula antes de usá-la.                        |

## O Que Foi Feito

- **Documentação**: Todos os métodos e variáveis agora têm comentários explicativos para facilitar a compreensão do código.
- **Tratamento de Exceções**: Coloquei mensagens de erro mais claras para que seja mais fácil identificar problemas com a conexão ou com as consultas SQL.
- **Boas Práticas com Recursos**: Usei o recurso `try-with-resources` para garantir que os objetos de banco de dados sejam fechados corretamente e evitar vazamento de memória.
- **Segurança**: Fiz validações para evitar entradas inválidas no método de login, o que ajuda a prevenir problemas de segurança como injeção de SQL.
- **Correção do Driver JDBC**: Atualizei o nome do driver JDBC para o mais recente, garantindo maior compatibilidade com o banco de dados.
- **Organização do Código**: Separei a lógica de conexão em um método próprio, deixando o código mais modular e fácil de entender.

# ETAPA 3 - Análise de Fluxo  

# ETAPA 3 - Análise de Fluxo  

## Grafo de Fluxo do Método verificarUsuario  

O grafo de fluxo do método verificarUsuario apresenta os seguintes pontos numerados:  

1. Método conectarBD inicia.  
2. Inicializa Connection conn como null.  
3. Tenta carregar o driver JDBC.  
4. Define a URL de conexão.  
5. Conecta ao banco de dados e inicializa conn.  
6. Captura exceção se falhar ao conectar.  
7. Retorna conn.  
8. Método verificarUsuario inicia.  
9. Inicializa sql como string vazia.  
10. Chama conectarBD().  
11. Adiciona a instrução SQL para selecionar nome.  
12. Adiciona a cláusula WHERE para o login.  
13. Adiciona a condição da senha.  
14. Tenta executar a instrução SQL.  
15. Cria o Statement.  
16. Executa a consulta SQL.  
17. Verifica se o ResultSet possui dados.  
18. Define result = true se um usuário for encontrado.  
19. Recupera o nome do ResultSet.  
20. Captura exceções ao executar a consulta.  
21. Retorna result.

### Complexidade Ciclomática  

A complexidade ciclomática do método verificarUsuario é *4*, indicando 4 caminhos independentes no fluxo.  

### Sequências de Caminhos  

As sequências dos caminhos identificados são:  

1. *Caminho 1*: Conexão bem-sucedida e usuário encontrado.  
2. *Caminho 2*: Conexão bem-sucedida e usuário não encontrado.  
3. *Caminho 3*: Falha na conexão ao banco de dados.  
4. *Caminho 4*: Exceção ao executar a consulta SQL.  

---  

Sinta-se à vontade para ajustar qualquer parte do texto conforme necessário! Se houver mais algo com que eu possa ajudar, não hesite em me avisar.
