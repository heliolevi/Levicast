package br.com.seuprojeto.Principal;

import java.util.Scanner;

public class Autenticador {
    private final String USUARIO_MESTRE = "Helio";
    private final String SENHA_MESTRE = "1234";

    public boolean validarAcesso() {
        Scanner leitura = new Scanner(System.in);

        System.out.println("\n--- 🛡️ Verificação de Identidade ---");
        System.out.println("Digite o usuário (ou ENTER para visitante):");
        String user = leitura.nextLine();

        if (user.equalsIgnoreCase(USUARIO_MESTRE)) {
            System.out.println("Digite a senha secreta:");
            String senha = leitura.nextLine();

            if (senha.equals(SENHA_MESTRE)) {
                System.out.println("✅ Acesso de Administrador concedido!");
                return true;
            } else {
                System.out.println("❌ Senha incorreta! Entrando como Visitante.");
            }
        } else {
            System.out.println("👋 Entrando no modo Leitura (Visitante).");
        }
        return false;
    }
}