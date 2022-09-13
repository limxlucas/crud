package crud;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		while(true) {
			System.out.println("[1] - Criar usuário \n[2] - Detalhar usuário \n[3] - Atualiza usuário \n[4] - Deletar usuário \n[5] - Sair");
			Scanner teclado = new Scanner(System.in);
			String opcaoTemporaria = teclado.next();
			int opcao = Integer.parseInt(opcaoTemporaria);
			
			if(opcao == 1) {
				System.out.println("Digite o nome do usuario: ");
				String nome = teclado.next();

				System.out.println("Quantos anos " + nome + " tem?");
				String idadeTemporaria = teclado.next();
				int idade = Integer.parseInt(idadeTemporaria);
				
				System.out.println("Qual o username do " + nome + " ?");
				String usernameTemporario = teclado.next();
				String username = usernameTemporario.toLowerCase();
				
				String password = randomPassword();
				System.out.println("ATENÇÃO\n A senha gerada foi: " + password + "\nLembre-se! Pois sem ela, não será possível fazer nenhuma alteração posterior");
				Usuario usuario = new Usuario(nome, idade, username, password);
				Database.adicionaUsuario(usuario);
			}
			
			if(opcao == 2) {
				System.out.println("Qual o username do usuário: ");
				String username = teclado.next();
				Database.lerUsuario(username);
			}
			
			if(opcao == 3) {
				System.out.println("Digite o username para atualizar o usuário: ");
				String username = teclado.next();
				
				System.out.println("Digite a senha desse username: ");
				String password = teclado.next();
				Database.atualizaUsuario(username, password);
			}
			
			if(opcao == 4) {
				System.out.println("Digite o username de quem será apagado: ");
				String username = teclado.next();
				
				System.out.println("Digite a senha desse username: ");
				String password = teclado.next();
				
				Database.removeUsuario(username, password);
			}
			
			if(opcao == 5) {
				System.out.println("Saindo...");
				break;
			}
		}
	}
	
	public static String randomPassword() {
        String StringAlphaNumeric = "0123456789" + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(5);
  
        for (int i = 0; i < 5; i++) {
            int index = (int)(StringAlphaNumeric.length() * Math.random());
            sb.append(StringAlphaNumeric.charAt(index));
        }
        return sb.toString();
	}
}