package com.example.ofos.utils;

public class IdGenerator {
	private static int counter = 1000;
	
	public static synchronized int nextId() {
		return counter++;
	}
}
