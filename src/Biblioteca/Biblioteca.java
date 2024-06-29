package Biblioteca;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


public class Biblioteca {
    private HashMap<String, Livro> livros;
    private HashMap<String, Usuario> usuarios;
    private ArrayList<Emprestimo> emprestimos;
    private static final String LIVROS_FILE = "livros.txt";
    private static final String USUARIOS_FILE = "usuarios.txt";



    public Biblioteca() {
        this.livros = new HashMap<>();
        this.usuarios = new HashMap<>();
        this.emprestimos = new ArrayList<>();

    }

    public void adicionarLivro(Livro livro) {
        livros.put(livro.getISBN(), livro);
        salvarLivros();
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro.getISBN());
        salvarLivros();
    }

    public ArrayList<Livro> listarLivros() {
        return new ArrayList<>(livros.values());
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getID(), usuario);
        salvarUsuarios();
    }

    public void removerUsuario(Usuario usuario) {
        usuarios.remove(usuario.getID());
        salvarUsuarios();
    }

    public ArrayList<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

    public void realizarEmprestimo(Livro livro, Usuario usuario,  LocalDate dataEmprestimo) {
        if (livro.isDisponivel()) {
            Emprestimo emprestimo = new Emprestimo(livro, usuario,dataEmprestimo);
            emprestimos.add(emprestimo);
            salvarLivros(); // Atualiza o estado de disponibilidade do livro
            System.out.println("Empréstimo realizado com sucesso!");
        } else {
            System.out.println("Livro não disponível para empréstimo.");
        }
    }

    public void registrarDevolucao(Livro livro) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().getISBN().equals(livro.getISBN()) && emprestimo.getDataDevolucao() == null) {
                emprestimo.devolver();
                livro.setDisponivel(true); // Atualiza o estado de disponibilidade do livro
                System.out.println("Devolução registrada com sucesso! Multa");
                return;
            }
        }
        System.out.println("Livro não encontrado nos empréstimos ativos.");
    }

    public void gerarRelatorio() {
        for (Usuario usuario :usuarios.values() )
            System.out.println( "Usuário: " + usuario.getNome() + " - Multa Total: " + usuario.getMulta() );
    }


    public void exibirEspecificacoesLivro(Livro livro) {
        livro.exibirEspecificacoes();
    }

    public Livro buscarLivroPorISBN(String ISBN) {
        return livros.get(ISBN);
    }

    public Usuario buscarUsuarioPorID(String ID) {
        return usuarios.get(ID);
    }

    public void listarLivrosEmprestados() {
        System.out.println("Lista de Livros Emprestados:");
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucao() == null) {
                Livro livro = emprestimo.getLivro();
                System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", ISBN: " + livro.getISBN() + ", Emprestado para: " + emprestimo.getUsuario().getNome());
            }
        }
    }

    private void salvarLivros() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LIVROS_FILE))) {
            for (Livro livro : livros.values()) {
                writer.write(livro.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar livros: " + e.getMessage());
        }
    }



    private void salvarUsuarios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USUARIOS_FILE))) {
            for (Usuario usuario : usuarios.values()) {
                writer.write(usuario.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }






    }







