package it.polito.tdp.bar;

public class Gruppo {
	public int componenti;
	public Tavolo tavolo;

	public Gruppo(int componenti) {
		super();
		this.componenti = componenti;
	}

	public int getComponenti() {
		return componenti;
	}

	public void setComponenti(int componenti) {
		this.componenti = componenti;
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

	public Gruppo(int componenti, Tavolo tavolo) {
		super();
		this.componenti = componenti;
		this.tavolo = tavolo;
	}

}
