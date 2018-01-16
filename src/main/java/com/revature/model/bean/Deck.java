package com.revature.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Decks")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Deck implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="deck_id_seq")
	@SequenceGenerator(allocationSize=1, name="deck_id_seq", sequenceName="deck_id_seq")
	private Integer id;
	
	@Column(name="CREATION_TIME")
	private Date creationTime;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USER_DECK")
	@JsonBackReference
	private User owner;
	
	@NotNull
	@ElementCollection
	private List<Card> cards;

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

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public Deck(Integer id, Date creationTime, User owner, List<Card> cards) {
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
