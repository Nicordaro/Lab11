package it.polito.tdp.bar;

public class Event implements Comparable<Event> {
	private int time;
	private Gruppo g;
	private int durata;
	private float tolleranza;
	private EventType tipo;

	public Event(int time, Gruppo g, int durata, float tolleranza, EventType tipo) {
		this.time = time;
		this.g = g;
		this.durata = durata;
		this.tolleranza = tolleranza;
		this.tipo = tipo;
	}

	public int getTime() {
		return time;
	}

	public Gruppo getG() {
		return g;
	}

	public int getDurata() {
		return durata;
	}

	public float getTolleranza() {
		return tolleranza;
	}

	public EventType getTipo() {
		return tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + time;
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
		Event other = (Event) obj;
		if (time != other.time)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.tipo + " con " + this.g.getComponenti() + " persone.";
	}

	@Override
	public int compareTo(Event o) {
		return this.time - o.time;
	}

}
