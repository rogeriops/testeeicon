package br.com.teste.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long numControle;

	@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dtCadastro;

	private String nomeProduto;
	
	private Double vrProduto;
	
	private int qtdProduto;
	
	private long codCliente;
	
	private Double vrTotal;
	
	
	@PrePersist
	void preInsert() {
	   if (this.dtCadastro == null) {
	       this.dtCadastro = new Date();
	   }
	   if (this.qtdProduto == 0) {
	       this.qtdProduto = 1;
	   }
	   if(this.qtdProduto > 5 || this.qtdProduto < 10) {
		   this.vrTotal =  (this.qtdProduto * this.vrProduto) - ((this.qtdProduto * this.vrProduto) * 0.05);
	   }
	   if(this.qtdProduto >= 10) {
		   this.vrTotal =  (this.qtdProduto * this.vrProduto) - ((this.qtdProduto * this.vrProduto) * 0.10);
	   }
	   
	   if(this.qtdProduto <=5 ) {
		   this.vrTotal =  (this.qtdProduto * this.vrProduto);
	   }
	}
	

	
		
	public Pedido() {
	}


	public long getNumControle() {
		return numControle;
	}


	public void setNumControle(long numControle) {
		this.numControle = numControle;
	}


	public Date getDtCadastro() {
		return dtCadastro;
	}


	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}


	public String getNomeProduto() {
		return nomeProduto;
	}


	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}


	public Double getVrProduto() {
		return vrProduto;
	}


	public void setVrProduto(Double vrProduto) {
		this.vrProduto = vrProduto;
	}


	public int getQtdProduto() {
		return qtdProduto;
	}


	public void setQtdProduto(int qtdProduto) {
		this.qtdProduto = qtdProduto;
	}


	public long getCodCliente() {
		return codCliente;
	}


	public void setCodCliente(long codCliente) {
		this.codCliente = codCliente;
	}


	public Double getVrTotal() {
		return vrTotal;
	}




	public void setVrTotal(Double vrTotal) {
		this.vrTotal = vrTotal;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codCliente ^ (codCliente >>> 32));
		result = prime * result + (int) (numControle ^ (numControle >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (codCliente != other.codCliente)
			return false;
		if (numControle != other.numControle)
			return false;
		return true;
	}

	
}
