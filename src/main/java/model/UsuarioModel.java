package model;

public class UsuarioModel {
    
    private String email;
    private String senha;
    private String nome;
    
    public UsuarioModel(String email, String senha, String nome) {
        super();
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }
    @Override
    public String toString() {
        return "UsuarioModel [email=" + email + ", senha=" + senha + ", nome=" + nome + "]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
 