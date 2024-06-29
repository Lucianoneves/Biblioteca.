import Biblioteca.Biblioteca;
import Biblioteca.Usuario;

import java.time.LocalDate;
import java.util.Scanner;
import Biblioteca.Livro;


public class Main {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner( System.in );
        int opcao;

            do {
                System.out.println( "\n--- Sistema de Gerenciamento de Biblioteca ---" );
                System.out.println( "1. Adicionar Livro" );
                System.out.println( "2. Remover Livro" );
                System.out.println( "3. Listar Livros" );
                System.out.println( "4. Registrar Usuário" );
                System.out.println( "5. Remover Usuário" );
                System.out.println( "6. Listar Usuários" );
                System.out.println( "7. Realizar Empréstimo" );
                System.out.println( "8. Registrar Devolução" );
                System.out.println( "9. Exibir Especificações de um Livro" );
                System.out.println( "10. Listar Livros Emprestados" );
                System.out.println( "0. Sair" );
                System.out.print( "Escolha uma opção: " );
                opcao = scanner.nextInt();
                scanner.nextLine();  // Consumir nova linha

                switch (opcao) {
                    case 1:
                        System.out.print( "Título do Livro: " );
                        String titulo = scanner.nextLine();
                        System.out.print( "Autor do Livro: " );
                        String autor = scanner.nextLine();
                        System.out.print( "ISBN do Livro: " );
                        String ISBN = scanner.nextLine();
                        System.out.print( "Ano de Publicação do Livro: ");
                        String anoPublicacao = scanner.nextLine();
                        Livro livro = new Livro( titulo, autor, ISBN, anoPublicacao );
                        biblioteca.adicionarLivro( livro );
                        System.out.println( "Livro adicionado com sucesso!" );
                        break;
                    case 2:
                        System.out.print( "ISBN do Livro a remover: " );
                        ISBN = scanner.nextLine();
                        livro = biblioteca.buscarLivroPorISBN( ISBN );
                        if (livro != null) {
                            biblioteca.removerLivro( livro );
                            System.out.println( "Livro removido com sucesso!" );
                        } else {
                            System.out.println( "Livro não encontrado!" );
                        }
                        break;
                    case 3:
                        System.out.println( "Lista de Livros:" );
                        for (Livro l : biblioteca.listarLivros()) {
                            System.out.println( "Título: " + l.getTitulo() + ", Autor: " + l.getAutor() + ", ISBN: " + l.getISBN() + " anoPublicacao " + l.getAnoPublicacao() + ", Disponível: " + (l.isDisponivel() ? "Sim" : "Não") );
                        }
                        break;
                    case 4:
                        System.out.print( "Nome do Usuário: " );
                        String nome = scanner.nextLine();
                        System.out.print( "ID do Usuário: " );
                        String ID = scanner.nextLine();
                        System.out.print( "Tipo de Usuário: " );
                        String tipoUsuario = scanner.nextLine();
                        Usuario usuario = new Usuario( nome, ID, tipoUsuario );
                        biblioteca.registrarUsuario( usuario );
                        System.out.println( "Usuário registrado com sucesso!" );
                        break;
                    case 5:
                        System.out.print( "ID do Usuário a remover: " );
                        ID = scanner.nextLine();
                        usuario = biblioteca.buscarUsuarioPorID( ID );
                        if (usuario != null) {
                            biblioteca.removerUsuario( usuario );
                            System.out.println( "Usuário removido com sucesso!" );
                        } else {
                            System.out.println( "Usuário não encontrado!" );
                        }
                        break;
                    case 6:
                        System.out.println( "Lista de Usuários:" );
                        for (Usuario u : biblioteca.listarUsuarios()) {
                            System.out.println( "Nome: " + u.getNome() + ", ID: " + u.getID() + ", Tipo: " + u.gettipoUsuario());
                        }
                        break;
                    case 7:
                        System.out.print( "ISBN do Livro para empréstimo: " );
                        ISBN = scanner.nextLine();
                        livro = biblioteca.buscarLivroPorISBN( ISBN );
                        if (livro != null && livro.isDisponivel()) {
                            System.out.print( "ID do Usuário: " );
                            ID = scanner.nextLine();
                            usuario = biblioteca.buscarUsuarioPorID( ID );
                            if (usuario != null) {
                                biblioteca.realizarEmprestimo( livro, usuario,LocalDate.now().minusDays(20));
                            } else {
                                System.out.println( "Usuário não encontrado!" );
                            }
                        } else {
                            System.out.println( "Livro não disponível ou não encontrado!" );
                        }
                        break;
                    case 8:
                        System.out.print( "ISBN do Livro para devolução: " );
                        ISBN = scanner.nextLine();
                        livro = biblioteca.buscarLivroPorISBN( ISBN );
                        if (livro != null && !livro.isDisponivel()) {
                            biblioteca.registrarDevolucao( livro);
                        } else {
                            System.out.println( "Livro não encontrado ou já está disponível!" );
                        }
                        break;

                    case 9:
                        System.out.print( "ISBN do Livro para exibir especificações: " );
                        ISBN = scanner.nextLine();
                        livro = biblioteca.buscarLivroPorISBN( ISBN );
                        if (livro != null) {
                            biblioteca.exibirEspecificacoesLivro( livro );
                        } else {
                            System.out.println( "Livro não encontrado!" );
                        }

                        break;


                    case 10:
                        biblioteca.listarLivrosEmprestados();
                        break;
                    case 0:
                        System.out.println( "Saindo do sistema..." );
                        break;
                    default:
                        System.out.println( "Opção inválida!" );
                        break;
                }
            } while (opcao != 0);



        scanner.close();
        }
    }





