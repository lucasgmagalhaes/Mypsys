package model;

public class LoteModel {

    private int idLote;
    private String descricao;
    private int estoqueIdEstoque;
    private int categoriaIdCategoria;
    
    public LoteModel(int idLote, String descricao, int estoqueIdEstoque, int categoriaIdCategoria) {
        super();
        this.idLote = idLote;
        this.descricao = descricao;
        this.estoqueIdEstoque = estoqueIdEstoque;
        this.categoriaIdCategoria = categoriaIdCategoria;
    }
    @Override
        public String toString() {
            return "LoteModel [id_lote=" + idLote + ", descricao=" + descricao + ", estoque_id_estoque=" + estoqueIdEstoque
                    + ", categoria_id_categoria=" + categoriaIdCategoria + "]";
        }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoqueIdEstoque() {
        return estoqueIdEstoque;
    }

    public void setEstoqueIdEstoque(int estoqueIdEstoque) {
        this.estoqueIdEstoque = estoqueIdEstoque;
    }

    public int getCategoriaIdCategoria() {
        return categoriaIdCategoria;
    }

    public void setCategoriaIdCategoria(int categoriaIdCategoria) {
        this.categoriaIdCategoria = categoriaIdCategoria;
    }
}
