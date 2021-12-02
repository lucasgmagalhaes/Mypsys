package model;

public class FornecedorModel {
    private int cnpj;
    private String nome;
    /**
     * 
     */
    /**
     * @param cnpj
     * @param nome
     */
    public FornecedorModel(int cnpj, String nome) {
        super();
        this.cnpj = cnpj;
        this.nome = nome;
    }
    @Override
    public String toString() {
        return "ForncedorModel [cnpj=" + cnpj + ", nome=" + nome + "]";
    }
    /**
     * @return the cnpj
     */
    public int getCnpj() {
        return cnpj;
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }
    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }
    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}