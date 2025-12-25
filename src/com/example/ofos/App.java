package com.example.ofos;

import com.example.ofos.models.Order;
import com.example.ofos.models.MenuItem;
import com.example.ofos.services.DataStore;
import com.example.ofos.services.MenuService;
import com.example.ofos.services.OrderService;

import java.util.List;
import java.util.Scanner;

public class App {
	private static final Scanner sc = new Scanner(System.in);
	private final DataStore store;
	private final MenuService menuService;
	private final OrderService orderService;
	
	public App() {
		this.store = DataStore.load();
		this.menuService = new MenuService(store);
		this.orderService = new OrderService(store, menuService);
	}
	
	private void seedIfEmpty() {
		if (menuService.listAll().isEmpty()) {
			menuService.addItem("Margherita Pizza", 249.0, "Pizza");
			menuService.addItem("Veg Burger", 99.0, "Burger");
			menuService.addItem("Coke 500ml", 39.0, "Berverage");
		}
	}
	
	private void mainMenu() {
		while (true) {
			System.out.println("\n=== Online Food Ordering ===");
			System.out.println("1. Admin Menu");
			System.out.println("2. Customer Menu");
			System.out.println("3. Exit");
			System.out.println("Choose: ");
			String c = sc.nextLine().trim();
			switch(c) {
			case "1": adminMenu(); break;
			case "2": customerMenu(); break;
			case "3": System.out.println("Goodbye!"); return;
			default: System.out.println("Invalid Choice");
			}
		}
	}
	
	private void adminMenu() {
		while (true) {
			System.out.println("\n--- Admin ---");
			System.out.println("1. Add Menu Item");
			System.out.println("2. Remove Menu Item");
			System.out.println("3. List Menu");
			System.out.println("4. View Orders");
			System.out.println("5. Back");
			System.out.println("Choose: ");
			String c = sc.nextLine().trim();
			switch (c) {
			case "1": addMenuItem(); break;
			case "2": removeMenuItem(); break;
			case "3": listMenu(); break;
			case "4": viewOrders(); break;
			case "5": return;
			default: System.out.println("Invalid");
			}
		}
	}
	
	private void customerMenu() {
		System.out.println("Enter your name: ");
		String name = sc.nextLine().trim();
        Order order = orderService.createOrder(name);
        System.out.println("Created Order ID: " + order.getId());
        while (true) {
            System.out.println("\n--- Customer ---");
            System.out.println("1. Show Menu");
            System.out.println("2. Add Item to Order");
            System.out.println("3. View My Order");
            System.out.println("4. Checkout");
            System.out.println("5. Cancel");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();
            switch (c) {
            case "1": listMenu(); break;
            case "2": addItemToOrder(order.getId()); break;
            case "3": System.out.println(order); break;
            case "4": orderService.completeOrder(order.getId());
                	  System.out.println("Order placed. Total: ₹" + order.getTotalAmount());
                	  return;
            case "5": System.out.println("Order cancelled"); return;
            default: System.out.println("Invalid");
            }
        }
	}
	
	private void addMenuItem() {
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Price: ");
        double price = Double.parseDouble(sc.nextLine().trim());
        System.out.print("Category: ");
        String cat = sc.nextLine().trim();
        MenuItem m = menuService.addItem(name, price, cat);
        System.out.println("Added: " + m);
    }

    private void removeMenuItem() {
        System.out.print("Menu item id to remove: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        boolean ok = menuService.removeItem(id);
        System.out.println(ok ? "Removed" : "Not found");
    }
    
    private void listMenu() {
        List<MenuItem> all = menuService.listAll();
        if (all.isEmpty()) System.out.println("Menu is empty");
        else all.forEach(System.out::println);
    }

    private void viewOrders() {
        List<Order> orders = orderService.listOrders();
        if (orders.isEmpty()) System.out.println("No orders yet");
        else orders.forEach(System.out::println);
    }

    private void addItemToOrder(int orderId) {
        listMenu();
        System.out.print("Menu item id: ");
        int mid = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Quantity: ");
        int qty = Integer.parseInt(sc.nextLine().trim());
        boolean ok = orderService.addItemToOrder(orderId, mid, qty);
        System.out.println(ok ? "Added to order" : "Failed to add");
    }
    
    public static void main(String[] args) {
        App app = new App();
        app.seedIfEmpty();
        app.mainMenu();
    }
    
}
