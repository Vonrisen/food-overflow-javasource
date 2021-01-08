import java.net.URL;

import javax.swing.ImageIcon;

public class Cibo {

	private String nome;
	private double prezzo;
	private String descrizione;
	
	
	public Cibo(String nome, double prezzo) {
		super();
		this.nome = nome;
		this.prezzo = prezzo;
	}

	public Cibo(String nome, double prezzo, String descrizione) {
		super();
		this.nome = nome;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public double getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	@Override
	public String toString() {
		return nome +" \n"+prezzo + "€ \n" + descrizione;
	}



}
