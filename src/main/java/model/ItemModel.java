package model;
import java.time.LocalDate;

public class ItemModel {
	private int idItem;
	private LocalDate dataDeAquisicao;
	private LocalDate dataDeValidade;
	private double valorVenda;
	private int quantidade;
	private int lote_idLote;
	private int fornecedor_cnpj;
	private double valorCompra;
	/**
	 * @param idItem
	 * @param dataDeAquisicao
	 * @param dataDeValidade
	 * @param valorVenda
	 * @param quantidade
	 * @param lote_idLote
	 * @param fornecedor_cnpj
	 * @param valorCompra
	 */
	public ItemModel(int idItem,LocalDate dataDeAquisicao, LocalDate dataDeValidade, double valorVenda, int quantidade,
			int lote_idLote, int fornecedor_cnpj, double valorCompra) {
		super();
		this.idItem = idItem;
		this.dataDeAquisicao = dataDeAquisicao;
		this.dataDeValidade = dataDeValidade;
		this.valorVenda = valorVenda;
		this.quantidade = quantidade;
		this.lote_idLote = lote_idLote;
		this.fornecedor_cnpj = fornecedor_cnpj;
		this.valorCompra = valorCompra;
	}
	@Override
	public String toString() {
		return "ItemModel [id_item=" + idItem + ", data_de_aquisicao=" + dataDeAquisicao + ", data_de_validade="
				+ dataDeValidade + ", valor_venda=" + valorVenda + ", quantidade=" + quantidade + ", lote_id_lote="
				+ lote_idLote + ", fornecedor_cnpj=" + fornecedor_cnpj + ", valor_compra=" + valorCompra + "]";
	}
	/**
	 * @return the idItem
	 */
	public int getIdItem() {
		return idItem;
	}
	/**
	 * @return the dataDeAquisicao
	 */
	public LocalDate getDataDeAquisicao() {
		return dataDeAquisicao;
	}
	/**
	 * @return the dataDeValidade
	 */
	public LocalDate getDataDeValidade() {
		return dataDeValidade;
	}
	/**
	 * @return the valorVenda
	 */
	public double getValorVenda() {
		return valorVenda;
	}
	/**
	 * @return the quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}
	/**
	 * @return the lote_idLote
	 */
	public int getLote_idLote() {
		return lote_idLote;
	}
	/**
	 * @return the fornecedor_cnpj
	 */
	public int getFornecedor_cnpj() {
		return fornecedor_cnpj;
	}
	/**
	 * @return the valorCompra
	 */
	public double getValorCompra() {
		return valorCompra;
	}
	/**
	 * @param idItem the idItem to set
	 */
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	/**
	 * @param dataDeAquisicao the dataDeAquisicao to set
	 */
	public void setDataDeAquisicao(LocalDate dataDeAquisicao) {
		this.dataDeAquisicao = dataDeAquisicao;
	}
	/**
	 * @param dataDeValidade the dataDeValidade to set
	 */
	public void setDataDeValidade(LocalDate dataDeValidade) {
		this.dataDeValidade = dataDeValidade;
	}
	/**
	 * @param valorVenda the valorVenda to set
	 */
	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}
	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	/**
	 * @param lote_idLote the lote_idLote to set
	 */
	public void setLote_idLote(int lote_idLote) {
		this.lote_idLote = lote_idLote;
	}
	/**
	 * @param fornecedor_cnpj the fornecedor_cnpj to set
	 */
	public void setFornecedor_cnpj(int fornecedor_cnpj) {
		this.fornecedor_cnpj = fornecedor_cnpj;
	}
	/**
	 * @param valorCompra the valorCompra to set
	 */
	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	
	
}
