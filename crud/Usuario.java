package crud;

public class Usuario {

	private String nome;
	private int idade;
	private String username;
	private String password;
	
	public Usuario(String nome, int idade, String username, String password) {
		this.nome = nome;
		this.idade = idade;
		this.username = username;
		this.password = password;
	}
	
	public String getNome() {
		return nome;
	}
	public int getIdade() {
		return idade;
	}
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
