package com.ljunggren.reportGenerator;

import java.util.List;

public class Record {

	private List<Item> items;

	public Record(List<Item> items) {
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}

}
