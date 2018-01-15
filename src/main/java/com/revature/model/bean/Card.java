package com.revature.model.bean;

public class Card {

	private String name;
	private double draftValue;

	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Card(String name, double draftValue) {
		super();
		this.name = name;
		this.draftValue = draftValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDraftValue() {
		return draftValue;
	}

	public void setDraftValue(double draftValue) {
		this.draftValue = draftValue;
	}

	@Override
	public String toString() {
		return "Card [name=" + name + ", draftValue=" + draftValue + "]";
	}

}
