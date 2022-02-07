package it.contocorrente.entity;

public class Movimento {

	private String data;
	private String Iban;
	private Double importo;
	private Operazione tipo;

	public String getData() {return data;}
	public void setData(String data) {this.data = data;}
	
	public String getIban() {return Iban;}
	public void setIban(String Iban) {this.Iban = Iban;}
	
	public Double getImporto() {return importo;}
	public void setImporto(Double importo) {this.importo = importo;}
	
	public Operazione getTipo() {return tipo;}
	public void setTipo(Operazione tipo) {this.tipo = tipo;}
}