package model.vo;

public class ProdutoVO {

	private int idProduto;
	private String nomeProduto;
	private double preco;
	
	public ProdutoVO(int idProduto, String nomeProduto, double preco) {
		super();
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
		this.preco = preco;
	}

	public ProdutoVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
	
}
