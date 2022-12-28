package com.jackson_tutorial.Model;

/**
 * POJO (Plain Old Java Object) (Objeto Java PLano Antiguo).
 * Es una clase simple e independiente (No dependen de ningun famework), 
 * no posee restricciones especiales y su objetivo es simplificar 
 * la estructuracion de los desarrollos reduciendo la complejidad,
 * aumentando la legibilidad y facilitar su reutilizacion.
 * 
 * Los POJOs no pueden:
 *          - Implementar interfaces.
 *          - Extender de otras clases.
 *          - Contener decoradores.
 * 
 * 
 */
public class Country {


	/* los atributos pueden ser publico o privados */
	private String name;
	private long population;
	private int numberOfProvinces;
	private boolean developed;

	public Country(String name, long population, int numberOfProvinces,
						boolean developed) {
		this.name = name;
		this.population = population;
		this.numberOfProvinces = numberOfProvinces;
		this.developed = developed;
	}

	public Country() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

	public int getNumberOfProvinces() {
		return numberOfProvinces;
	}

	public void setNumberOfProvinces(int numberOfProvinces) {
		this.numberOfProvinces = numberOfProvinces;
	}

	public boolean isDeveloped() {
		return developed;
	}

	public void setDeveloped(boolean developed) {
		this.developed = developed;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", population=" + population + ", numberOfProvinces=" + numberOfProvinces
				+ ", developed=" + developed + "]";
	}
}
