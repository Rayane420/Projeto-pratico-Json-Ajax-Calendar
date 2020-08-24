package connection;

/**
 * Responsável pela conexão com o banco de dados
 * Conexão Postgresql para o banco curso-teste
 *@author Rayane 
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;

import javax.management.RuntimeErrorException;

public class ConnectionDataBaseBanco2 {

	private static String banco = "jdbc:postgresql://localhost:5432/curso-teste?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;

	// chamada stática para o método conectar
	static {
		conectar();
	}

	// construtor
	public ConnectionDataBaseBanco2() {
		conectar();
	}

	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar com o banco de dados" + e.getMessage());
		}
	}
	/**
	 * Retorna a conexão do banco de dados
	 *@return Connection SQL
	 */
	public static Connection getConnection() {
		return connection;
	}
}