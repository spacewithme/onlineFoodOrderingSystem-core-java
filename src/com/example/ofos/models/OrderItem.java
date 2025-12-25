package com.example.ofos.models;

import java.io.Serializable;

public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final MenuItem menuItem;
	private final int quantity;
	
	public OrderItem(MenuItem menuItem, int quantity) {
		this.menuItem = menuItem;
		this.quantity = quantity;
	}
	
	public MenuItem getMenuItem() {
		return menuItem;
	}
	public int getQuantity() {
		return quantity;
	}
	
	public double getTotal() {
		return menuItem.getPrice() * quantity;
	}
	
	@Override
	public String toString() {
		return String.format("%s x%d = ₹%.2f", menuItem.getName(),quantity, getTotal());
	}
	
}
