package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.bean.Card;
import com.revature.service.PackGenerator;

@RestController
public class DeckController {
	
	@Autowired
	private PackGenerator packGenerator;

	@RequestMapping(value="generate/pack/players/{number}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<List<Card>> getPacks(@PathVariable("number") int num){
		return packGenerator.generatePacks(num);
	}
	
}
