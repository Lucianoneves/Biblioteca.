package Biblioteca;

public class Usuario extends Pessoa {

    private String tipoUsuario;



    public Usuario(String nome, String ID, String tipoUsuario) {
        super(nome, ID);
        this.tipoUsuario = tipoUsuario;
    }

    public String gettipoUsuario() {
        return tipoUsuario;
    }

    public void settipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }



    @Override
    public String toString() {
        return getNome() + "," + getID() + "," + tipoUsuario;
    }


}



