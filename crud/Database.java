package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Database {

	public static void adicionaUsuario(Usuario usuario) throws SQLException {
		PreparedStatement stm = abreConexao("insert into usuario(nome, idade, username, psswrd) values (?, ?, ?, ?)");
		stm.setString(1, usuario.getNome());
		stm.setInt(2, usuario.getIdade());
		stm.setString(3, usuario.getUsername());
		stm.setString(4, usuario.getPassword());
		stm.execute();
		System.out.println("Usuario adicionado");
	}
	
	public static void removeUsuario(String username, String password) throws SQLException {
		PreparedStatement stm = abreConexao("delete from usuario where username = ?");
		if(verifyPassword(username, password)) {
			stm.setString(1, username);
			stm.execute();
			System.out.println("Usuário removido.");
		}
	}
	
	public static void atualizaUsuario(String username, String password) throws SQLException {
		if(verifyPassword(username, password)) {
			Scanner teclado = new Scanner(System.in);
			System.out.println("Digite o novo nome: ");
			String nome = teclado.nextLine();
			
			System.out.println("A idade de " + nome + " :");
			String idadeTemporaria = teclado.next();
			int idade = Integer.parseInt(idadeTemporaria);
			
			System.out.println("Digite o novo username: ");
			String novoUsername = teclado.next();
			
			PreparedStatement stm = abreConexao("update usuario set nome = ?, idade = ?, username = ? where username = ?");
			stm.setString(1, nome);
			stm.setInt(2, idade);
			stm.setString(3, novoUsername);
			stm.setString(4, username);
			stm.execute();
			System.out.println("Usuário atualizado.");
		}
	}
	
	public static void lerUsuario(String username) throws SQLException {
		PreparedStatement stm = abreConexao("select * from usuario where username = ?");
		stm.setString(1, username);
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			String nome = rs.getString("Nome");
			System.out.println("Nome: " + nome);
			
			int idade = rs.getInt("idade");
			System.out.println("Idade: " + idade);
			
			String databaseUsername = rs.getString("username");
			System.out.println("Username: " + databaseUsername + "\n");
		}
	}
	
	public static String getPassword(String username) throws SQLException {
		PreparedStatement stm = abreConexao("select psswrd from usuario where username = ?");
		stm.setString(1, username);
		ResultSet rs = stm.executeQuery();
		String password = null;
		if(rs.next()) {
			password = rs.getString("psswrd");
		}
		return password;
	}
	
	public static PreparedStatement abreConexao(String msg) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperarConexao();
		PreparedStatement stm = connection.prepareStatement(msg);
		return stm;
	}
	
	public static boolean verifyPassword(String username, String password) throws SQLException {
		String databasePassword = getPassword(username);
		return(databasePassword.equals(password));
	}
}
