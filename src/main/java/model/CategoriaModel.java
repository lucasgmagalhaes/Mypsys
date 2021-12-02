package model;

public class CategoriaModel {

	private int id_categoria;
	private boolean perecivel;
	private String nome;
	/**
	 * @param id_categoria
	 * @param perecivel
	 * @param nome
	 */
	public CategoriaModel(int id_categoria, boolean perecivel, String nome) {
		super();
		this.id_categoria = id_categoria;
		this.perecivel = perecivel;
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "CategoriaModel [id_categoria=" + id_categoria + ", perecivel=" + perecivel + ", nome=" + nome + "]";
	}
	/**
	 * @return the id_categoria
	 */
	public int getIdCategoria() {
		return id_categoria;
	}
	/**
	 * @return the perecivel
	 */
	public boolean getPerecivel() {
		return perecivel;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param id_categoria the id_categoria to set
	 */
	public void setIdCategoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	/**
	 * @param perecivel the perecivel to set
	 */
	public void setPerecivel(boolean perecivel) {
		this.perecivel = perecivel;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
