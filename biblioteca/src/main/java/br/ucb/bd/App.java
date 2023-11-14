// App.java
package br.ucb.bd;

import br.ucb.bd.dao.AuditoriaDAO;
import br.ucb.bd.dao.AutorDAO;
import br.ucb.bd.dao.EmprestimoDAO;
import br.ucb.bd.dao.LivroDAO;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar Autor");
            System.out.println("2. Cadastrar Livro");
            System.out.println("3. Realizar Empréstimo");
            System.out.println("4. Devolução de Livro");
            System.out.println("5. Exibir Tudo do Banco de Dados");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); 

            switch (escolha) {
                case 1:
                    System.out.print("Digite o nome do autor: ");
                    String nomeAutor = scanner.nextLine();
                    AutorDAO.cadastrarAutor(nomeAutor);
                    break;
                case 2:
                    System.out.print("Digite o título do livro: ");
                    String tituloLivro = scanner.nextLine();
                    System.out.print("Digite o ID do autor: ");
                    int idAutorLivro = scanner.nextInt();
                    System.out.print("Digite a quantidade disponível: ");
                    int quantidadeDisponivel = scanner.nextInt();
                    LivroDAO.cadastrarLivro(tituloLivro, idAutorLivro, quantidadeDisponivel);
                    break;
                case 3:
                    System.out.print("Digite o ID do livro: ");
                    int idLivroEmprestimo = scanner.nextInt();
                    System.out.print("Digite a data de empréstimo (formato YYYY-MM-DD): ");
                    String dataEmprestimo = scanner.next();
                    System.out.print("Digite a data de devolução (formato YYYY-MM-DD): ");
                    String dataDevolucao = scanner.next();
                    EmprestimoDAO.realizarEmprestimo(idLivroEmprestimo, dataEmprestimo, dataDevolucao);
                    break;
                case 4:
                    System.out.print("Digite o ID do empréstimo: ");
                    int idEmprestimo = scanner.nextInt();
                    EmprestimoDAO.devolucaoLivro(idEmprestimo);
                    break;
                case 5:
                    exibirTudoDoBanco();
                    break;
                case 0:
                    System.out.println("Encerrando o programa. Até logo!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirTudoDoBanco() {
        System.out.println("------ Autores ------");
        AutorDAO.exibirAutores();

        System.out.println("------ Livros ------");
        LivroDAO.exibirLivros();

        System.out.println("------ Empréstimos ------");
        EmprestimoDAO.exibirEmprestimos();

        System.out.println("------ Auditoria ------");
        AuditoriaDAO.exibirAuditoria();
    }
}
