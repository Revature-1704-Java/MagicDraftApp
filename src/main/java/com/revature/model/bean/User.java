package com.revature.model.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Users")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_id_seq")
	@SequenceGenerator(allocationSize=1, name="user_id_seq", sequenceName="user_id_seq")
	private Integer id;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	
	@OneToMany(mappedBy="owner", fetch=FetchType.LAZY)
	private List<Deck> decks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Deck> getDecks() {
		return decks;
	}

	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}

	public User(Integer id, String email, String password, List<Deck> decks) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.decks = decks;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", decks=" + decks + "]";
	}
	
	
}
