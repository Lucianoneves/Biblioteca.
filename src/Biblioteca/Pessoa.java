package Biblioteca;

public  class Pessoa {
    private String nome;
    private String ID;
    private double multa;

    public Pessoa(String nome, String ID) {
        this.nome = nome;
        this.ID = ID;
        this.multa= 0.0;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa += multa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void adicionarMulta(double valor) {
        this.multa += valor;
    }




}






