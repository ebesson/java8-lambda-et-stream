package org.ebesson.model;

public class Sale {
	private Person buyer;
	private Person seller;
	private Car car;
	private double cost;
	
	public Sale(Person buyer, Person seller, Car car, double cost) {
		this.buyer = buyer;
		this.seller = seller;
		this.car = car;
		this.cost = cost;
	}
	
	public Person getBuyer() {
		return this.buyer;
	}
	
	public Person getSeller() {
		return this.seller;
	}
	
	public Car getCar() {
		return this.car;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
}
