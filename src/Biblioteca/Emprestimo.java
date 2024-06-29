package Biblioteca;
import java.time.LocalDate;
import java.time.Period;




public class Emprestimo implements  Emprestavel {

    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;
    private static final double MULTA_POR_DIA = 1.5;


    public Emprestimo(Livro livro, Usuario usuario, LocalDate dataEmprestimo) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = null;

    }

    @Override
    public void emprestar(Usuario usuario) {
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.livro.setDisponivel( false );
    }

    @Override
    public void devolver() {
        this.dataDevolucao = dataDevolucao;
        this.livro.setDisponivel( true );
    }


    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
        long diasAtraso = calcularDiasAtraso();
        if (diasAtraso > 0) {
            double multa = diasAtraso * MULTA_POR_DIA;
            usuario.adicionarMulta( multa );
        }
    }

    private long calcularDiasAtraso() {
        if (dataDevolucao == null) {
            return 0;
        }

        LocalDate dataPrevistaDevolucao = dataEmprestimo.plusDays( 14 );
        if (dataDevolucao.isAfter( dataPrevistaDevolucao )) {
            return Period.between(dataPrevistaDevolucao, dataDevolucao).getDays();
        }
        return 0;

    }

}



