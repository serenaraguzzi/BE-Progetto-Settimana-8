package it.contocorrente.entity;

import java.time.LocalDate;

public class Contocorrente {

	private String Iban;
	private String intestatario;
	private Double saldo;
	private String dataCreazione = LocalDate.now().toString();
	
	public String getIban() {return Iban;}
	public void setIban(String iban) {Iban = iban;}
	
	public String getIntestatario() {return intestatario;}
	public void setIntestatario(String intestatario) {this.intestatario = intestatario;}
	
	public Double getSaldo() {return saldo;}
	public void setSaldo(Double saldo) {this.saldo = saldo;}
	
	public String getDataCreazione() {return dataCreazione;}
	public void setDataCreazione(String dataCreazione) {this.dataCreazione = dataCreazione;}
}