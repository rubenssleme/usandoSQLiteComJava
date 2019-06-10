package usandoSQLiteComJava;

import java.sql.*;

public class Banco {
	static Statement statement = null;
	static Connection connection = null;

	// Métodos conectar
	public static Statement conectar() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:banco.db");
			System.out.println("Conexão realizada !!!!");
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return statement;
	}

	// criando uma tabela
	public static void criarTabela() {
		try {
			// criando uma tabela
			statement.execute("CREATE TABLE IF NOT EXISTS RC_TEST( ID INTEGER, NOME VARCHAR )");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// inserindo registros
	public static void inserirRegistro() {
		try {
			// inserindo registros
			statement.execute("INSERT INTO RC_TEST( ID, NOME) VALUES (3, 'Rubens-3'), (3, 'Rubenito-3')");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// lendo os registros
	public static void lendoRegistro() {
		try {
			// lendo os registros
			PreparedStatement stmt = connection.prepareStatement("select * from RC_TEST");
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				Integer id = resultSet.getInt("ID");
				String nome = resultSet.getString("NOME");
				System.out.println(id + " - " + nome);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	
	public static void main(String[] args) {
		conectar();
		criarTabela();
		inserirRegistro();
		lendoRegistro();
	}
}