package com.example.ofos.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final int id;
	private final String customerName;
	private final LocalDateTime createdAt;
	private final List<OrderItem> items = new ArrayList<>(); // needs clarity
	private boolean completed = false;
	
	public Order(int id, String customerName) {
		this.id = id;
		this.customerName = customerName;
		this.createdAt = LocalDateTime.now();
	}
	
	public int getId() {
		return id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public boolean isCompleted() {
		return completed;
	}
	
	public void addItem(OrderItem item) {
		items.add(item);
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public double getTotalAmount() {
		return items.stream().mapToDouble(OrderItem::getTotal).sum(); // needs clarity
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Order #%d for %s - %s\n", id, customerName, createdAt));
		for (OrderItem it : items) {
			sb.append(" ").append(it).append("\n");
		}
		sb.append(String.format("Total: ₹%.2f | %s\n", getTotalAmount(), completed ? "Completed" : "Pending"));
		return sb.toString();
	}
	
}
