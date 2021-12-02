package model;

public class EmpresaModel {
    private int cnpj;
    private String nome;
    private String usuario_email;
    /**
     * @param cnpj
     * @param nome
     * @param usuario_email
     */
    public EmpresaModel(int cnpj, String nome, String usuario_email) {
        super();
        this.cnpj = cnpj;
        this.nome = nome;
        this.usuario_email = usuario_email;
    }
    @Override
    public String toString() {
        return "EmpresaModel [cnpj=" + cnpj + ", nome=" + nome + ", usuario_email=" + usuario_email + "]";
    }
    /**
     * @return the cnpj
     */
    public int getCnpj() {
        return cnpj;
    }
    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }
    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * @return the usuario_email
     */
    public String getUsuario_email() {
        return usuario_email;
    }
    /**
     * @param usuario_email the usuario_email to set
     */
    public void setUsuario_email(String usuario_email) {
        this.usuario_email = usuario_email;
    }
}
