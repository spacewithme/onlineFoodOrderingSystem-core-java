package com.example.ofos.models;

import java.io.Serializable;

public class MenuItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private final int id;
	private String name;
	private double price;
	private String category;
	
	public MenuItem(int id, String name, double price, String category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
	}
	
	//getter
	public int getId() { 
		return id;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public String getCategory() {
		return category;
	}
	
	//setter
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return String.format("[%d] %s - ₹%.2f (%s)", id,name,price, category);
	}
}
