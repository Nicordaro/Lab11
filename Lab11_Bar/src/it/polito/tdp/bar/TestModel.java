package it.polito.tdp.bar;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		model.init(2000);
		model.run();
		System.out.println(model.getCounterEventi());

		System.out.format("Arrivati %d clienti, soddisfatti %d, insoddisfatti %d\n", model.getNumero_totale_clienti(),
				model.getNumero_clienti_soddisfatti(), model.getNumero_clienti_insoddisfatti());

	}

}
