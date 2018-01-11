package com.revature.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="DECKS")
public class Deck implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="deck_id_seq")
	@SequenceGenerator(allocationSize=1, name="deck_id_seq", sequenceName="deck_id_seq")
	private Integer id;
	
	@Column(name="CREATION_TIME")
	private Date creationTime;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USER_DECK")
	private User owner;
	
	@ElementCollection
	private List<String> cards;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<String> getCards() {
		return cards;
	}

	public void setCards(List<String> cards) {
		this.cards = cards;
	}

	public Deck(Integer id, Date creationTime, User owner, List<String> cards) {
		super();
		this.id = id;
		this.creationTime = creationTime;
		this.owner = owner;
		this.cards = cards;
	}

	public Deck() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Deck [id=" + id + ", creationTime=" + creationTime + ", owner=" + owner + ", cards=" + cards + "]";
	}
	
	
}
