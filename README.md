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

## Como Testar as Correções

1. Clone o repositório e acesse a branch onde as correções foram feitas:
   ```bash
   git checkout main
