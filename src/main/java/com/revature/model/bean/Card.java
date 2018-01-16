package com.revature.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="Cards")
public class Card {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="card_id_seq")
	@SequenceGenerator(allocationSize=1, name="card_id_seq", sequenceName="card_id_seq")
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	@Column(name="DRAFTVALUE")
	private Double draftValue;

//	@ManyToOne(fetch=FetchType.EAGER)
//	@JoinColumn(name="DECK_CARD")
//	@JsonBackReference
//	private Deck ownerDeck;

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
