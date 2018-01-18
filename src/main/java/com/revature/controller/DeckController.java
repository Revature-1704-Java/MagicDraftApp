package com.revature.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.bean.Card;
import com.revature.model.bean.Deck;
import com.revature.model.bean.User;
import com.revature.service.DBService;
import com.revature.service.PackGenerator;

import io.jsonwebtoken.Jwts;

@CrossOrigin
@RestController
public class DeckController {
	
	@Autowired
	DBService dbService;
	
	@Value("${key}")
	private String key;
	
	@Autowired
	private PackGenerator packGenerator;

	@RequestMapping(value="generate/pack/players/{number}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<List<Card>> getPacks(@PathVariable("number") int num){
		return packGenerator.generatePacks(num);
	}
	
	@RequestMapping(value="save/deck", method=RequestMethod.POST)
	public ResponseEntity<Deck> saveDeck(@RequestBody String json) throws JsonParseException, JsonMappingException, JSONException{
		
		JSONObject res = new JSONObject(json);
		JSONArray reqDeck = res.getJSONArray("deck");
		
		
		List<Card> cards = new ArrayList<Card>();
		for(int i = 0; i < reqDeck.length(); i++) {
			cards.add(dbService.cardFindByName(reqDeck.getString(i)));
		}
		
		User user = dbService.userFindByEmail(res.getString("email"));
		
		Deck newDeck = new Deck();
		newDeck.setCreationTime(new Date());
		newDeck.setOwner(user);
		newDeck.setCards(cards);
		Deck deck = dbService.createDeck(newDeck);
		
		return new ResponseEntity<Deck>(deck, HttpStatus.OK);
	}
	
	@RequestMapping(value="view/deck/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Deck> getDeck(@PathVariable("id") int id, Deck deck) {
		
		deck = dbService.deckFindById(id);
		return new ResponseEntity<Deck>(deck, HttpStatus.OK);
		
	}
	
}
