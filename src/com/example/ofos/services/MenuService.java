package com.example.ofos.services;

import com.example.ofos.models.MenuItem;
import com.example.ofos.utils.IdGenerator; 

import java.util.List;

public class MenuService {
	private final DataStore store;
	
	public MenuService(DataStore store) {
		this.store = store;
	}
	
	public MenuItem addItem(String name, double price, String category) {
		MenuItem item = new MenuItem(IdGenerator.nextId(), name, price, category);
		store.getMenu().add(item);
		store.save();
		return item;
	}
	
	public boolean removeItem(int id) {
		return store.getMenu().removeIf(m-> m.getId() == id);
	}
	
	public List<MenuItem> listAll() {
		return store.getMenu();
	}
	
	public MenuItem findById(int id) {
		for (MenuItem m : store.getMenu()) {
			if(m.getId() == id)
				return m;
		}
		return null;
	}
}
