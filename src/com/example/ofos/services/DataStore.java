package com.example.ofos.services;

import com.example.ofos.models.MenuItem;
import com.example.ofos.models.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStore implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<MenuItem> menu = new ArrayList<>();
	private List<Order> orders = new ArrayList<>();
	
	private static final String PATH = "data/datastore.bin";
	
	public List<MenuItem> getMenu() {
		return menu;
	}
	public List<Order> getOrders() {
		return orders;
	}
	
	public void save() {
		try {
			File dir = new File("data");
			if(!dir.exists()) {
				dir.mkdirs();
			}
			FileOutputStream fileConnection = new FileOutputStream(PATH); // job is to open tunnel to hard drive
			ObjectOutputStream oos = new ObjectOutputStream(fileConnection); // can be written as (new FileOutputStream) without need of first line. job is to pack your complex Java objects into boxes (bytes) that fit into the tunnel
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			System.out.println("Save failed!");
		}
	}
	
	public static DataStore load() {
		try {
			FileInputStream fileReverseConnection = new FileInputStream(PATH);
			ObjectInputStream ois = new ObjectInputStream(fileReverseConnection);
			ois.close();
			return (DataStore) ois.readObject();
		} catch (Exception e) {
			return new DataStore();
		}
	}
}
