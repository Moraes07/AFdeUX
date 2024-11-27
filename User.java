package aaaaa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private String nome = "";

    private boolean result = false;

    /**
     * Método responsável por estabelecer conexão com o banco de dados.
     * 
     * @return Conexão ativa com o banco de dados ou null se a conexão falhar.
     */
    public Connection conectarBD() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return conn;
    }

    /**
     * Método para verificar se o usuário existe no banco de dados.
     * 
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @return true se o usuário for encontrado, false caso contrário.
     */
    public boolean verificarUsuario(String login, String senha) {
        if (login == null || senha == null || login.isEmpty() || senha.isEmpty()) {
            System.err.println("Login ou senha inválidos.");
            return false;
        }

        String sql = "SELECT nome FROM usuarios WHERE login = ? AND senha = ?";
        
        try (Connection conn = conectarBD();
             Statement st = conn != null ? conn.createStatement() : null;
             ResultSet rs = st != null ? st.executeQuery(sql) : null) {

            if (conn == null || st == null) {
                System.err.println("Conexão com o banco de dados falhou.");
                return false;
            }

            if (rs != null && rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta SQL: " + e.getMessage());
        }
        return result;
    }

    /**
     * Método para obter o nome do usuário autenticado.
     * 
     * @return Nome do usuário, ou string vazia se não autenticado.
     */
    public String getNome() {
        return nome;
    }
}
