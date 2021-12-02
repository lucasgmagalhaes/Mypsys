package model;

public class EstoqueModel {
    private int id_estoque;
    private double credito;
    private int empresa_cnpj;
    /**
     * @param id_estoque
     * @param credito
     * @param empresa_cnpj
     */
    public EstoqueModel(int id_estoque, double credito, int empresa_cnpj) {
        super();
        this.id_estoque = id_estoque;
        this.credito = credito;
        this.empresa_cnpj = empresa_cnpj;
    }
    @Override
    public String toString() {
        return "EstoqueModel [id_estoque=" + id_estoque + ", credito=" + credito + ", empresa_cnpj=" + empresa_cnpj + "]";
    }
    /**
     * @return the id_estoque
     */
    public int getIdEstoque() {
        return id_estoque;
    }
    /**
     * @param id_estoque the id_estoque to set
     */
    public void setIdEstoque(int id_estoque) {
        this.id_estoque = id_estoque;
    }
    /**
     * @return the credito
     */
    public double getCredito() {
        return credito;
    }
    /**
     * @param credito the credito to set
     */
    public void setCredito(double credito) {
        this.credito = credito;
    }
    /**
     * @return the empresa_cnpj
     */
    public int getEmpresa_cnpj() {
        return empresa_cnpj;
    }
    /**
     * @param empresa_cnpj the empresa_cnpj to set
     */
    public void setEmpresa_cnpj(int empresa_cnpj) {
        this.empresa_cnpj = empresa_cnpj;
    }
    
    
}