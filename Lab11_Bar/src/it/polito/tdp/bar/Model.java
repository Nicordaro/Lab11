package it.polito.tdp.bar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Model {

	// Coda degli eventi
	private PriorityQueue<Event> queue = new PriorityQueue<>();

	// Parametri di simulazione
	private int time_max = 10;
	private int durata_min = 60;
	private int persone_max = 10;
	private int counterEventi = 0;
	private float tolleranza_max = (float) 0.9;

	// Modello del mondo
	private List<Tavolo> tavoliDisponibili = new ArrayList<>();
	private int num_tavoli_disponibili;
	private List<Gruppo> gruppi = new ArrayList<>();

	// Valori da calcolare
	private int numero_clienti_soddisfatti;
	private int numero_clienti_insoddisfatti;
	private int numero_totale_clienti;

	/**
	 * Crea la coda iniziale di eventi ed inizializza correttamente tutte le
	 * variabili di simulazione
	 * 
	 * @param durataMax
	 *            durata complessiva della simulazione, in minuti
	 */
	public void init(int maxEventi) {

		// inizializza la coda degli eventi
		queue.clear();
		while (counterEventi < maxEventi) {
			int time = (1 + (int) (Math.random() * time_max));
			int n_pers = (1 + (int) (Math.random() * persone_max));
			int durata = (int) (60 * (1 + (Math.random())));
			float tolleranza = (float) (0.9 * Math.random());
			Gruppo g = new Gruppo(n_pers);
			Event e = new Event(time, g, durata, tolleranza, EventType.ARRIVO_GRUPPO_CLIENTI);
			Event e1 = new Event(time + durata, g, 0, 0, EventType.TAVOLO_LIBERATO);
			queue.add(e1);
			queue.add(e);
			counterEventi++;
		}

		// inizializzo le variabili di simulazione
		tavoliDisponibili.add(new Tavolo(10));
		tavoliDisponibili.add(new Tavolo(10));
		tavoliDisponibili.add(new Tavolo(8));
		tavoliDisponibili.add(new Tavolo(8));
		tavoliDisponibili.add(new Tavolo(8));
		tavoliDisponibili.add(new Tavolo(8));
		tavoliDisponibili.add(new Tavolo(6));
		tavoliDisponibili.add(new Tavolo(6));
		tavoliDisponibili.add(new Tavolo(6));
		tavoliDisponibili.add(new Tavolo(6));
		tavoliDisponibili.add(new Tavolo(4));
		tavoliDisponibili.add(new Tavolo(4));
		tavoliDisponibili.add(new Tavolo(4));
		tavoliDisponibili.add(new Tavolo(4));
		tavoliDisponibili.add(new Tavolo(4));

		numero_clienti_soddisfatti = 0;
		numero_clienti_insoddisfatti = 0;
		numero_totale_clienti = 0;
		num_tavoli_disponibili = 15;
	}

	public int getNumero_clienti_soddisfatti() {
		return numero_clienti_soddisfatti;
	}

	public void setNumero_clienti_soddisfatti(int numero_clienti_soddisfatti) {
		this.numero_clienti_soddisfatti = numero_clienti_soddisfatti;
	}

	public int getNumero_clienti_insoddisfatti() {
		return numero_clienti_insoddisfatti;
	}

	public void setNumero_clienti_insoddisfatti(int numero_clienti_insoddisfatti) {
		this.numero_clienti_insoddisfatti = numero_clienti_insoddisfatti;
	}

	public int getNumero_totale_clienti() {
		return numero_totale_clienti;
	}

	public void setNumero_totale_clienti(int numero_totale_clienti) {
		this.numero_totale_clienti = numero_totale_clienti;
	}

	public int getCounterEventi() {
		return counterEventi;
	}

	public void setCounterEventi(int counterEventi) {
		this.counterEventi = counterEventi;
	}

	public void run() {
		Event e;
		while ((e = queue.poll()) != null) {
			processEvent(e);
		}
	}

	private void processEvent(Event e) {
		System.out.println(e);

		switch (e.getTipo()) {

		case ARRIVO_GRUPPO_CLIENTI:
			numero_totale_clienti = numero_totale_clienti + e.getG().getComponenti();

			if (num_tavoli_disponibili > 0) {
				int flag = 0;

				for (Tavolo t : tavoliDisponibili) {
					if (!t.isOccupato()) {
						if ((t.getCapienza() < e.getG().getComponenti() * 2)
								&& (t.getCapienza() >= e.getG().getComponenti())) {
							numero_clienti_soddisfatti = numero_clienti_soddisfatti + e.getG().getComponenti();
							num_tavoli_disponibili--;
							t.occupa();
							Gruppo g = new Gruppo(e.getG().getComponenti(), t);
							gruppi.add(g);
							flag = 1;
							break;
						}
					}

				}
				if (flag == 0) {
					float probabilità = (float) (Math.random());
					if (e.getTolleranza() < probabilità) {
						numero_clienti_soddisfatti = numero_clienti_soddisfatti + e.getG().getComponenti();
					} else {
						numero_clienti_insoddisfatti = numero_clienti_insoddisfatti + e.getG().getComponenti();
					}
				}
			}
			// se ho finito i tavoli e i clienti sono tolleranti li sposto al bancone
			else {
				float probabilità = (float) (Math.random());
				if (e.getTolleranza() < probabilità) {
					numero_clienti_soddisfatti = numero_clienti_soddisfatti + e.getG().getComponenti();
				} else {
					numero_clienti_insoddisfatti = numero_clienti_insoddisfatti + e.getG().getComponenti();
				}
			}
		case TAVOLO_LIBERATO:
			if (e.getG().getTavolo() != null) {
				e.getG().getTavolo().libera();
			}
			break;

		}

	}

}
