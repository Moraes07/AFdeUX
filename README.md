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

### Descrição dos Nós e Arestas do Grafo de Fluxo  

Este gráfico representa a lógica do sistema de autenticação de usuários, detalhando cada passo crítico:  

1. *(1)*: Início do método conectarBD(). Este nó inicia o processo de conexão com o banco de dados.  

2. *(2)*: Configuração da string de conexão com o banco de dados. Aqui, definimos como nos conectamos ao MySQL.  

3. *(3)*: Início do bloco try para gerenciar possíveis exceções durante a conexão.  

4. *(4)*: Carregamos o driver JDBC necessário para a conexão com o MySQL.  

5. *(5)*: Tentativa de estabelecer a conexão com o banco de dados usando a string de conexão configurada.  

6. *(6)*: Se a conexão for estabelecida com sucesso, retornamos a conexão.  

7. *(7)*: Se a conexão falhar, tratamos a exceção lançando o erro e imprimindo a stack trace.  

8. *(8)*: Se a conexão foi bem-sucedida, o método retorna a conexão. Caso contrário, retorna null.  

9. *(9)*: Início do método verificarUsuario(), onde verificamos as credenciais de um usuário.  

10. *(10)*: Definimos a consulta SQL para verificar se o login e a senha informados existem no banco de dados.  

11. *(11)*: Início do bloco try-with para garantir que os recursos sejam fechados após o uso.  

12. *(12)*: Preparamos a instrução SQL utilizando PreparedStatement.  

13. *(13)*: O login do usuário é definido como um parâmetro na consulta SQL.  

14. *(14)*: Tratamos possíveis exceções que possam surgir ao preparar a consulta.  

15. *(15)*: A senha do usuário também é configurada como um parâmetro da consulta SQL.  

16. *(16)*: Executamos a consulta e obtemos um ResultSet que contém os resultados.  

17. *(17)*: Verificamos se o ResultSet retornou algum resultado (rs.next()).  

18. *(18)*: Se um resultado for encontrado, isto indica que o usuário existe. Nesse caso, o nome do usuário é armazenado e um valor verdadeiro (boolean) é retornado.  

19. *(19)*: Se não houver resultados, significa que o usuário não foi encontrado.  

20. *(20)*: O resultado da verificação de credenciais é retornado, indicando o sucesso ou fracasso da autenticação.

## Complexidade Ciclomática  

A complexidade ciclomática do sistema de autenticação é *5*.  

## Caminhos Possíveis  

Os caminhos identificados no grafo são:  

1. *Caminho 1:* (1) → (2) → (3) → (4) → (5) → (6) → (8) → (9) → (10) → (11) → (12) → (13) → (15) → (16) → (17) → (18) → (20)  
2. *Caminho 2:* (1) → (2) → (3) → (4) → (5) → (6) → (8) → (9) → (10) → (11) → (12) → (13) → (15) → (16) → (17) → (19) → (20)  
3. *Caminho 3:* (1) → (2) → (3) → (4) → (5) → (7)  
4. *Caminho 4:* (1) → (2) → (3) → (4) → (5) → (8) → (9)  
5. *Caminho 5:* (1) → (2) → (3) → (4) → (8) → (9)

Link do grafico: https://lucid.app/lucidchart/8005d24f-dc93-46cc-82f3-59a73e5daab7/edit?viewport_loc=-510%2C3660%2C3071%2C1341%2C0_0&invitationId=inv_38d88830-b7ec-4a38-8727-f8cd6708a786

# ETAPA 4

# Sistema de Autenticação de Usuários em Java  

## Descrição  

Este repositório contém um *sistema de autenticação de usuários* desenvolvido em *Java* que utiliza um banco de dados *MySQL* para armazenar e verificar as credenciais dos usuários. O propósito deste projeto é fornecer uma interface simples para que os usuários possam se autenticar de maneira segura, empregando *prepared statements* para evitar vulnerabilidades como *injeção de SQL*.  

## Funcionalidades  

- *Registro de Usuário*: Permite que novos usuários registrem-se no sistema.  
- *Autenticação*: Os usuários podem logar-se usando um nome de usuário e senha.  
- *Segurança*: Utiliza consultas SQL preparadas para proteger contra injeções de SQL.  
- *Interação com Banco de Dados*: Conecta-se a um banco de dados MySQL para gerenciamento de usuários.  

## Tecnologias Utilizadas  

- *Java*: Linguagem de programação usada para a implementação do sistema.  
- *MySQL*: Banco de dados relacional usado para armazenar as informações dos usuários.  
- *JDBC*: API utilizada para conectar o Java ao MySQL.  

## Estrutura do Projeto  

O projeto consiste em:  

- *Classe de Conexão*: Gerencia a conexão com o banco de dados.  
- *Classe de Usuário*: Contém métodos para registrar e verificar usuários.  
- *Métodos de Autenticação*: Implementa a lógica de autenticação.  
- *Tratamento de Exceções*: Garante que erros sejam devidamente tratados e logados.  

## Como Configurar o Projeto  

1. *Pré-requisitos*:  
   - Ter o *Java Development Kit (JDK)* instalado.  
   - Ter o *MySQL Server* instalado e rodando.  
   - Ter o *MySQL Connector/J* para permitir a conexão entre o Java e o MySQL.  

2. *Configurações do Banco de Dados*:  
   - Crie um banco de dados no MySQL. Você pode nomeá-lo como test.  
   - Crie uma tabela chamada usuarios com as seguintes colunas:  
     sql  
     CREATE TABLE usuarios (  
         id INT AUTO_INCREMENT PRIMARY KEY,  
         login VARCHAR(50) NOT NULL,  
         senha VARCHAR(255) NOT NULL,  
         nome VARCHAR(100) NOT NULL  
     );  
       
   - Atualize a string de conexão com suas credenciais no código. O trecho do código que contém a string de conexão pode se parecer com isso (substitua conforme necessário):  
     java  
     String url = "jdbc:mysql://localhost/test?user=seu_usuario&password=sua_senha";  
       

3. *Configuração do Ambiente*:  
   - Importe o projeto para sua IDE favorita (por exemplo, IntelliJ, Eclipse).  
   - Adicione a dependência do MySQL Connector no seu pom.xml se estiver usando Maven:  
     xml  
     <dependency>  
         <groupId>mysql</groupId>  
         <artifactId>mysql-connector-java</artifactId>  
         <version>8.0.31</version>  
     </dependency>  
       
