package com.example.ofos.services;

import com.example.ofos.models.MenuItem;
import com.example.ofos.models.Order;
import com.example.ofos.models.OrderItem;
import com.example.ofos.utils.IdGenerator;

import java.util.List;

public class OrderService {
	private final DataStore store;
	private final MenuService menuService;
	
	public OrderService(DataStore store, MenuService menuService) {
		this.store = store;
		this.menuService = menuService;
	}
	
	public Order createOrder(String customerName) {
		Order o = new Order(IdGenerator.nextId(), customerName);
		store.getOrders().add(o);
		store.save();
		return o;
	}
	
	public boolean addItemToOrder(int orderId, int menuItemId, int qty) {
		Order order = store.getOrders().stream().filter(o -> o.getId() == orderId).findFirst().orElse(null);
		MenuItem item = menuService.findById(menuItemId);
		if (order == null || item == null) return false;
		order.addItem(new OrderItem(item, qty));
		store.save();
		return true;
	}
	
	public List<Order> listOrders() {
		return store.getOrders();
	}
	
	public boolean completeOrder(int orderId) {
		for (Order o: store.getOrders()) {
			if(o.getId() == orderId) {
				o.setCompleted(true);
				store.save();
				return true;
			}
		}
		return false;
	}
}
