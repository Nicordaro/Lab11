package it.polito.tdp.bar;

public class Tavolo {

	private int capienza;
	private boolean occupato;

	public Tavolo(int capienza) {
		this.capienza = capienza;
		occupato = false;
	}

	public int getCapienza() {
		return capienza;
	}

	public void occupa() {
		this.occupato = true;
	}

	public void libera() {
		this.occupato = false;
	}

	public boolean isOccupato() {
		return occupato;
	}
}
